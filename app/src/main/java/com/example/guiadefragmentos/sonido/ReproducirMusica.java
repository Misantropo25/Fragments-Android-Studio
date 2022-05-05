package com.example.guiadefragmentos.sonido;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.guiadefragmentos.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ReproducirMusica#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ReproducirMusica extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ReproducirMusica() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ReproducirMusica.
     */
    // TODO: Rename and change types and number of parameters
    public static ReproducirMusica newInstance(String param1, String param2) {
        ReproducirMusica fragment = new ReproducirMusica();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    ImageView album;

    MediaPlayer musica;

    Spinner spinnerMusica;

    TextView titulo;

    SeekBar seekBar;
    Runnable runnable;
    Handler handler;

    ImageButton btnanterior,btnsiguiente,btnpausa,btncontinuar,btnrepetir,btnregresar,btninfo;

    String nombreMusic,_url;
    Integer repetir=2,posicion=0,posicionSpinner=0;

    MediaPlayer arregloMusica[] = new MediaPlayer[5];
    String[] arregloParaSpinner = new String[5];

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getParentFragmentManager().setFragmentResultListener("key", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                nombreMusic = result.getString("musica");
                //Cargamos las canciones
                if(nombreMusic!=null){
                    cargarCanciones(nombreMusic);
                    cargarPortadas(nombreMusic);
                    cargarCancionesSpinner(nombreMusic);
                    titulo.setText(nombreMusic);
                }
            }
        });

    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnanterior = view.findViewById(R.id.anterior);
        btnsiguiente = view.findViewById(R.id.siguiente);
        btnpausa = view.findViewById(R.id.pausa);
        btncontinuar = view.findViewById(R.id.continuar);
        btnrepetir = view.findViewById(R.id.repetir);
        btnregresar = view.findViewById(R.id.regresar);
        btninfo = view.findViewById(R.id.info);

        titulo = view.findViewById(R.id.titulo);

        seekBar = view.findViewById(R.id.seekBar);

        album = (ImageView) view.findViewById(R.id.album);

        spinnerMusica = view.findViewById(R.id.spinnerMusicas);

        musica = new MediaPlayer();
        handler = new Handler();




        btncontinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playPause(view);
            }
        });

        btnrepetir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Repetir(view);
            }
        });

        btnpausa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Stop(view);
            }
        });

        btnsiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Siguiente(view);
            }
        });

        btnanterior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Anterior(view);
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser){
                    arregloMusica[posicion].seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        spinnerMusica.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                escogerCancion();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnregresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arregloMusica[posicion].stop();
            }
        });

        btninfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                redireccionar(nombreMusic);
            }
        });


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_reproducir_musica, container, false);
        return view;
    }

    public void recibirDatos(){
        Bundle bundle = this.getArguments();
        nombreMusic = bundle.getString("1");

    }

    public void playPause(View view){
        if(arregloMusica[posicion].isPlaying()){
            arregloMusica[posicion].pause();
            btncontinuar.setImageResource(R.drawable.ic_baseline_stop_24);
            Toast.makeText(getActivity(),"Pausa",Toast.LENGTH_SHORT).show();
        }else{
            arregloMusica[posicion].start();
            btncontinuar.setImageResource(R.drawable.ic_baseline_play_circle_filled_24);
            Toast.makeText(getActivity(),"Play",Toast.LENGTH_SHORT).show();
            seekBar.setMax(arregloMusica[posicion].getDuration());
            cargaBarra();

        }
    }

    public void Stop(View view){
        if(arregloMusica[posicion] != null){
            arregloMusica[posicion].stop();
            //Cargamos las canciones
            cargarCanciones(nombreMusic);
            posicion = 0;
            posicionSpinner = 0;
            btncontinuar.setImageResource(R.drawable.ic_baseline_play_circle_filled_24);
            Toast.makeText(getActivity(),"Stop",Toast.LENGTH_SHORT).show();
            seekBar.setProgress(0);
            spinnerMusica.setSelection(posicion);
        }
    }


    public void Repetir(View view){
        if(repetir == 1){
            btnrepetir.setImageResource(R.drawable.ic_baseline_repeat_24);
            Toast.makeText(getActivity(),"No repetir",Toast.LENGTH_SHORT).show();
            arregloMusica[posicion].setLooping(false);
            repetir=2;
        }else{
            btnrepetir.setImageResource(R.drawable.ic_baseline_repeat_24o);
            Toast.makeText(getActivity(),"Repetir la cancion",Toast.LENGTH_SHORT).show();
            arregloMusica[posicion].setLooping(true);
            repetir=1;
        }
    }

    public void Siguiente(View view){
        if(posicion < arregloMusica.length -1){
            if(arregloMusica[posicion].isPlaying()){
                arregloMusica[posicion].stop();
                posicion++;
                arregloMusica[posicion].start();
                //Cambio de portada
                cargarPortadas(nombreMusic);
                spinnerMusica.setSelection(posicion);
            }
            else{
                posicion++;
                spinnerMusica.setSelection(posicion);
                //Volvemos a cargar la portada
                cargarPortadas(nombreMusic);
            }
        }else {
            Toast.makeText(getActivity(),"No hay mas canciones",Toast.LENGTH_SHORT).show();
        }
    }

    public void Anterior(View view){
        if(posicion>=1){
            if(arregloMusica[posicion].isPlaying()){
                arregloMusica[posicion].stop();
                //Cargar las canciones
                cargarCanciones(nombreMusic);
                posicion--;
                arregloMusica[posicion].start();
                //Volvemos a cargar la portada
                cargarPortadas(nombreMusic);
                spinnerMusica.setSelection(posicion);
            }else{
                posicion--;
                //Volvemos a cargar la portada
                cargarPortadas(nombreMusic);
                spinnerMusica.setSelection(posicion);
            }
        }else{
            Toast.makeText(getActivity(),"No hay mas canciones",Toast.LENGTH_SHORT).show();
        }
    }

    public void cargaBarra(){
        seekBar.setProgress(arregloMusica[posicion].getCurrentPosition());
        if(arregloMusica[posicion].isPlaying()){
            runnable = new Runnable() {
                @Override
                public void run() {
                    cargaBarra();
                }
            };
            handler.postDelayed(runnable,1000);
        }
    }

    public void cargarCanciones(String musicaNombre){
        switch (musicaNombre){
            case "Salsa":
                arregloMusica[0] = MediaPlayer.create(getActivity(), R.raw.comoseperdona);
                arregloMusica[1] = MediaPlayer.create(getActivity(), R.raw.lloraras);
                arregloMusica[2] = MediaPlayer.create(getActivity(), R.raw.ahoraquien);
                arregloMusica[3] = MediaPlayer.create(getActivity(), R.raw.deseandote);
                arregloMusica[4] = MediaPlayer.create(getActivity(), R.raw.vivirmivida);
                break;
            case "Bachata":
                arregloMusica[0] = MediaPlayer.create(getActivity(), R.raw.tuscartasllegan);
                arregloMusica[1] = MediaPlayer.create(getActivity(), R.raw.bachatarosa);
                arregloMusica[2] = MediaPlayer.create(getActivity(), R.raw.situestuvieras);
                arregloMusica[3] = MediaPlayer.create(getActivity(), R.raw.pormitimidez);
                arregloMusica[4] = MediaPlayer.create(getActivity(), R.raw.propuestaindecente);
                break;
            case "Huayno":
                arregloMusica[0] = MediaPlayer.create(getActivity(), R.raw.quelindaflor);
                arregloMusica[1] = MediaPlayer.create(getActivity(), R.raw.piopio);
                arregloMusica[2] = MediaPlayer.create(getActivity(), R.raw.lamatarina);
                arregloMusica[3] = MediaPlayer.create(getActivity(), R.raw.miliendrosa);
                arregloMusica[4] = MediaPlayer.create(getActivity(), R.raw.florderetama);
                break;
            case "Blues":
                arregloMusica[0] = MediaPlayer.create(getActivity(), R.raw.memphisblues);
                arregloMusica[1] = MediaPlayer.create(getActivity(), R.raw.crazyblues);
                arregloMusica[2] = MediaPlayer.create(getActivity(), R.raw.pinetopswogie);
                arregloMusica[3] = MediaPlayer.create(getActivity(), R.raw.dustmybroom);
                arregloMusica[4] = MediaPlayer.create(getActivity(), R.raw.boogiechillun);
                break;
            case "Country":
                arregloMusica[0] = MediaPlayer.create(getActivity(), R.raw.iwalktheline);
                arregloMusica[1] = MediaPlayer.create(getActivity(), R.raw.dollyparton);
                arregloMusica[2] = MediaPlayer.create(getActivity(), R.raw.thegambler);
                arregloMusica[3] = MediaPlayer.create(getActivity(), R.raw.alwaysonmymind);
                arregloMusica[4] = MediaPlayer.create(getActivity(), R.raw.imsolonesom);
                break;
            default:
                break;
        }

    }

    public void cargarPortadas(String musicaNombre){
        switch (musicaNombre){
            case "Salsa":
                switch (posicion){
                    case 0:
                        album.setImageResource(R.drawable.comoseperdona);
                        break;
                    case 1:
                        album.setImageResource(R.drawable.lloraras);
                        break;
                    case 2:
                        album.setImageResource(R.drawable.ahoraquien);
                        break;
                    case 3:
                        album.setImageResource(R.drawable.deseandote);
                        break;
                    case 4:
                        album.setImageResource(R.drawable.vivirmivida);
                        break;
                    default:
                        break;
                }
                break;
            case "Bachata":
                switch (posicion){
                    case 0:
                        album.setImageResource(R.drawable.tuscartasllegan);
                        break;
                    case 1:
                        album.setImageResource(R.drawable.bachatarosa);
                        break;
                    case 2:
                        album.setImageResource(R.drawable.situestuvieras);
                        break;
                    case 3:
                        album.setImageResource(R.drawable.pormitimidez);
                        break;
                    case 4:
                        album.setImageResource(R.drawable.propuestaindecente);
                        break;
                    default:
                        break;
                }
                break;
            case "Huayno":
                switch (posicion){
                    case 0:
                        album.setImageResource(R.drawable.quelindaflor);
                        break;
                    case 1:
                        album.setImageResource(R.drawable.piopio);
                        break;
                    case 2:
                        album.setImageResource(R.drawable.matarinadeindio);
                        break;
                    case 3:
                        album.setImageResource(R.drawable.miliendrosa);
                        break;
                    case 4:
                        album.setImageResource(R.drawable.florderetama);
                        break;
                    default:
                        break;
                }
                break;
            case "Blues":
                switch (posicion){
                    case 0:
                        album.setImageResource(R.drawable.memphisblues);
                        break;
                    case 1:
                        album.setImageResource(R.drawable.crazyblues);
                        break;
                    case 2:
                        album.setImageResource(R.drawable.pinetopboogie);
                        break;
                    case 3:
                        album.setImageResource(R.drawable.dustmybroom);
                        break;
                    case 4:
                        album.setImageResource(R.drawable.boogiechillun);
                        break;
                    default:
                        break;
                }
                break;
            case "Country":
                switch (posicion){
                    case 0:
                        album.setImageResource(R.drawable.iwalktheline);
                        break;
                    case 1:
                        album.setImageResource(R.drawable.dollyparton);
                        break;
                    case 2:
                        album.setImageResource(R.drawable.thegambler);
                        break;
                    case 3:
                        album.setImageResource(R.drawable.alwaysonmymind);
                        break;
                    case 4:
                        album.setImageResource(R.drawable.imsolonesome);
                        break;
                    default:
                        break;
                }
                break;
            default:
                break;
        }

    }

    //metodo para escoger cancion del spinner
    public void escogerCancion(){
        if(arregloMusica[posicion].isPlaying()){
            arregloMusica[posicion].pause();
            btncontinuar.setImageResource(R.drawable.ic_baseline_play_circle_filled_24);
            posicion = spinnerMusica.getSelectedItemPosition();
            arregloMusica[posicion].start();
            cargarPortadas(nombreMusic);
            seekBar.setMax(arregloMusica[posicion].getDuration());
            cargaBarra();

        }else{
            btncontinuar.setImageResource(R.drawable.ic_baseline_play_circle_filled_24);
            posicion = spinnerMusica.getSelectedItemPosition();
            arregloMusica[posicion].start();
            cargarPortadas(nombreMusic);
            seekBar.setMax(arregloMusica[posicion].getDuration());
            cargaBarra();
        }
    }

    public void cargarCancionesSpinner(String musicaNombre){
        switch (musicaNombre){
            case "Salsa":
                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.Salsa, android.R.layout.simple_list_item_1);
                spinnerMusica.setAdapter(adapter);
                arregloParaSpinner = getResources().getStringArray(R.array.Salsa);
                break;
            case "Bachata":
                ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(getActivity(), R.array.Bachata, android.R.layout.simple_list_item_1);
                spinnerMusica.setAdapter(adapter1);
                arregloParaSpinner = getResources().getStringArray(R.array.Bachata);
                break;
            case "Huayno":
                ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(getActivity(), R.array.Huayno, android.R.layout.simple_list_item_1);
                spinnerMusica.setAdapter(adapter2);
                arregloParaSpinner = getResources().getStringArray(R.array.Huayno);
                break;
            case "Blues":
                ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(getActivity(), R.array.Blues, android.R.layout.simple_list_item_1);
                spinnerMusica.setAdapter(adapter3);
                arregloParaSpinner = getResources().getStringArray(R.array.Blues);
                break;
            case "Country":
                ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource(getActivity(), R.array.Country, android.R.layout.simple_list_item_1);
                spinnerMusica.setAdapter(adapter4);
                arregloParaSpinner = getResources().getStringArray(R.array.Country);
                break;
            default:
                break;
        }
    }

    public void redireccionar(String nombreMusic){
        switch (nombreMusic){
            case "Salsa":
                switch (posicion){
                    case 0:
                        _url = "https://www.buenamusica.com/you-salsa/biografia";
                        Uri _link = Uri.parse(_url);
                        Intent a = new Intent(Intent.ACTION_VIEW,_link);
                        startActivity(a);
                        break;
                    case 1:
                        _url = "https://oscardleon.net/biografia/";
                        Uri _link1 = Uri.parse(_url);
                        Intent b = new Intent(Intent.ACTION_VIEW,_link1);
                        startActivity(b);
                        break;
                    case 2:
                    case 4:
                        _url = "https://www.biografiasyvidas.com/biografia/m/marc_anthony.htm";
                        Uri _link2 = Uri.parse(_url);
                        Intent c = new Intent(Intent.ACTION_VIEW,_link2);
                        startActivity(c);
                        break;
                    case 3:
                        _url = "https://www.biografiasyvidas.com/biografia/r/ruiz_frankie.htm";
                        Uri _link3 = Uri.parse(_url);
                        Intent e = new Intent(Intent.ACTION_VIEW,_link3);
                        startActivity(e);
                        break;
                    default:
                        break;
                }
                break;
            case "Bachata":
                switch (posicion){
                    case 0:
                        _url = "https://www.buenamusica.com/ramon-torres/biografia";
                        Uri _link = Uri.parse(_url);
                        Intent a = new Intent(Intent.ACTION_VIEW,_link);
                        startActivity(a);
                        break;
                    case 1:
                        _url = "https://www.biografiasyvidas.com/biografia/g/guerra_juan_luis.htm";
                        Uri _link1 = Uri.parse(_url);
                        Intent b = new Intent(Intent.ACTION_VIEW,_link1);
                        startActivity(b);
                        break;
                    case 2:
                        _url = "https://www.buenamusica.com/hector-acosta";
                        Uri _link2 = Uri.parse(_url);
                        Intent c = new Intent(Intent.ACTION_VIEW,_link2);
                        startActivity(c);
                        break;
                    case 3:
                        _url = "https://bachatarepublic.com/biografia-de-anthony-santos/";
                        Uri _link3 = Uri.parse(_url);
                        Intent d = new Intent(Intent.ACTION_VIEW,_link3);
                        startActivity(d);
                        break;
                    case 4:
                        _url = "https://historia-biografia.com/romeo-santos/";
                        Uri _link4 = Uri.parse(_url);
                        Intent e = new Intent(Intent.ACTION_VIEW,_link4);
                        startActivity(e);
                        break;
                    default:
                        break;
                }
                break;
            case "Huayno":
                switch (posicion){
                    case 0:
                        _url = "http://www.rincondelhuayno.com/paginas/silverio-urbina-biografia.htm";
                        Uri _link = Uri.parse(_url);
                        Intent a = new Intent(Intent.ACTION_VIEW,_link);
                        startActivity(a);
                        break;
                    case 1:
                        _url = "https://es.wikidat.com/info/chato-grados";
                        Uri _link1 = Uri.parse(_url);
                        Intent b = new Intent(Intent.ACTION_VIEW,_link1);
                        startActivity(b);
                        break;
                    case 2:
                        _url = "https://www.musica.com/letras.asp?biografia=23847";
                        Uri _link2 = Uri.parse(_url);
                        Intent c = new Intent(Intent.ACTION_VIEW,_link2);
                        startActivity(c);
                        break;
                    case 3:
                        _url = "https://www.musica.com/letras.asp?biografia=37491";
                        Uri _link3 = Uri.parse(_url);
                        Intent d = new Intent(Intent.ACTION_VIEW,_link3);
                        startActivity(d);
                        break;
                    case 4:
                        _url = "https://www.biografias.es/famosos/william-luna.html";
                        Uri _link4 = Uri.parse(_url);
                        Intent e = new Intent(Intent.ACTION_VIEW,_link4);
                        startActivity(e);
                        break;
                    default:
                        break;
                }
                break;
            case "Blues":
                switch (posicion){
                    case 0:
                        _url = "https://www.buscabiografias.com/biografia/verDetalle/2609/Nat%20King%20Cole";
                        Uri _link = Uri.parse(_url);
                        Intent a = new Intent(Intent.ACTION_VIEW,_link);
                        startActivity(a);
                        break;
                    case 1:
                        _url = "https://apoloybaco.com/jazz/index.php?option=com_content&view=article&id=3126:mamie-smith&catid=93&Itemid=260";
                        Uri _link1 = Uri.parse(_url);
                        Intent b = new Intent(Intent.ACTION_VIEW,_link1);
                        startActivity(b);
                        break;
                    case 2:
                        _url = "https://hmong.es/wiki/Pinetop_Smith";
                        Uri _link2 = Uri.parse(_url);
                        Intent c = new Intent(Intent.ACTION_VIEW,_link2);
                        startActivity(c);
                        break;
                    case 3:
                        _url = "https://www.mcnbiografias.com/app-bio/do/show?key=james-elmore";
                        Uri _link3 = Uri.parse(_url);
                        Intent d = new Intent(Intent.ACTION_VIEW,_link3);
                        startActivity(d);
                        break;
                    case 4:
                        _url = "https://historia-biografia.com/john-lee-hooker/";
                        Uri _link4 = Uri.parse(_url);
                        Intent e = new Intent(Intent.ACTION_VIEW,_link4);
                        startActivity(e);
                        break;
                    default:
                        break;
                }
                break;
            case "Country":
                switch (posicion){
                    case 0:
                        _url = "https://www.buscabiografias.com/biografia/verDetalle/2512/Johnny%20Cash";
                        Uri _link = Uri.parse(_url);
                        Intent a = new Intent(Intent.ACTION_VIEW,_link);
                        startActivity(a);
                        break;
                    case 1:
                        _url = "https://www.todomusica.org/dolly_parton/";
                        Uri _link1 = Uri.parse(_url);
                        Intent b = new Intent(Intent.ACTION_VIEW,_link1);
                        startActivity(b);
                        break;
                    case 2:
                        _url = "https://www.buscabiografias.com/biografia/verDetalle/7723/Kenny%20Rogers";
                        Uri _link2 = Uri.parse(_url);
                        Intent c = new Intent(Intent.ACTION_VIEW,_link2);
                        startActivity(c);
                        break;
                    case 3:
                        _url = "https://www.buscabiografias.com/biografia/verDetalle/2916/Willie%20Nelson";
                        Uri _link3 = Uri.parse(_url);
                        Intent d = new Intent(Intent.ACTION_VIEW,_link3);
                        startActivity(d);
                        break;
                    case 4:
                        _url = "https://www.buscabiografias.com/biografia/verDetalle/4715/Hank%20Williams";
                        Uri _link4 = Uri.parse(_url);
                        Intent e = new Intent(Intent.ACTION_VIEW,_link4);
                        startActivity(e);
                        break;
                    default:
                        break;
                }
                break;
            default:
                break;
        }
    }


}