package com.example.guiadefragmentos.maravillasModernas;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.guiadefragmentos.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link infoMaravilla#newInstance} factory method to
 * create an instance of this fragment.
 */
public class infoMaravilla extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public infoMaravilla() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment infoMaravilla.
     */
    // TODO: Rename and change types and number of parameters
    public static infoMaravilla newInstance(String param1, String param2) {
        infoMaravilla fragment = new infoMaravilla();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    ImageView imgmaravilla;
    TextView txtNombreMaravilla, txtInfo;
    String nombreMaravilla;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getParentFragmentManager().setFragmentResultListener("key", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                nombreMaravilla = result.getString("nombre");
                //Cargamos las canciones
                if(nombreMaravilla!=null){
                    cargarDatos();
                }
            }
        });
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_info_maravilla, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        imgmaravilla = view.findViewById(R.id.imagenMaravilla);
        txtNombreMaravilla =  view.findViewById(R.id.nombreMaravilla);
        txtInfo = view.findViewById(R.id.infoMaravilla);



    }
    public void cargarDatos(){
        switch (nombreMaravilla){
            case "Chiche":
                imgmaravilla.setImageResource(R.drawable.chichenitza);
                txtNombreMaravilla.setText("Chichén Itzá");
                txtInfo.setText("En el idioma Maya, su nombre significa “Boca del Pozo de los Brujos del Agua” ya que, según la creencia de esa época, el cenote sagrado servía como entrada al inframundo. Inaugurada en el año 525 d.C., este “castillo”, como denominaron los conquistadores españoles a esta maravilla mundial, sirvió como templo para el dios Kukulkán y consiste en una pirámide con una serie de terrazas cuadradas con escaleras que suben desde cada uno de los cuatro lados la misma hasta la parte superior. Fue declarada Patrimonio de la Humanidad por la UNESCO en el año 1988.");
                break;
            case "Petra":
                imgmaravilla.setImageResource(R.drawable.ciudaddepetra);
                txtNombreMaravilla.setText("Ciudad de Petra");
                txtInfo.setText("Originalmente conocida como Raqmu, la ciudad de Piedra le valió un lugar entre las 7 maravillas del mundo moderno por su arquitectura excavada en la roca, así como también por su avanzado sistema de conductos de agua. La ciudad de Petra, construida muy posiblemente ya en el año 312 a.C como la capital de los nabateos árabes, era el lugar de paso de las caravanas que transportaban especias, incienso y productos de lujo entre Arabia, Siria, Egipto y el Sur del Mediterráneo. Sus altos muros, los cuales albergaban tanto agua potable como seguridad a los mercaderes, son un símbolo definitivo de Jordania. Fue declarada Patrimonio de la Humanidad por la UNESCO en 1985.");
                break;
            case "Coliseo":
                imgmaravilla.setImageResource(R.drawable.coliseoromano);
                txtNombreMaravilla.setText("Coliseo Romano");
                txtInfo.setText("Este anfiteatro, el cual es el tesoro que el Imperio Romano dejó como herencia a la Ciudad Eterna, es una de las 7 maravillas del mundo moderno debido a que es el más grande nunca antes construido en el mundo. Su nombre original fue Anfiteatro Flavio y aquí se organizaban las luchas de los gladiadores, entre otros espectáculos. Funcionó durante más de 500 años y fue construido para soportar a más de 50 mil espectadores. Hoy en día es un atractivo turístico que atrae cada año a más de 100 mil turistas a la ciudad de la luz.");
                break;
            case "Cristo":
                imgmaravilla.setImageResource(R.drawable.cristoredentor);
                txtNombreMaravilla.setText("Cristo redentor");
                txtInfo.setText("Otra de las 7 maravillas del mundo moderno es la gran estatua de Jesús de Nazaret con los brazos abiertos que se ve desde cualquier punto de la ciudad de Río de Janeiro en Brasil. Este monumento Art Decó de 30 metros de altura en lo más alto del cerro Corcovado fue inaugurado en 1931 y es el fruto del trabajo en conjunto del escultor polaco-francés Paul Landowski y del ingeniero brasileño Heitor da Silva Costa. Como dato curioso, la estatua fue construida en Francia y llegó a Brasil en cientos de partes solo para ser ensamblada desde la cabeza hasta los pies para poder ser levantada en el cerro.");
                break;
            case "Machu":
                imgmaravilla.setImageResource(R.drawable.machupicchu);
                txtNombreMaravilla.setText("Machupicchu");
                txtInfo.setText("Descubierta en el año 1902 por Agustín Lizárraga y dada a conocer al mundo en 1911 por Hiram Bingham, la ciudad “perdida” de los Incas fue construida a mediados del siglo XV d. C por órdenes del Inca Pachacútec. Si bien este centro arqueológico no tiene nombre, Machu Picchu, la montaña donde se encuentra ubicada, significa en quechua “Montaña Vieja”. Según investigaciones, este lugar servía como un espacio de reposo para el Inca y estaba destinado a albergar un aproximado de 300 personas.");
                break;
            case "Muralla":
                imgmaravilla.setImageResource(R.drawable.murallachina);
                txtNombreMaravilla.setText("La gran muralla china");
                txtInfo.setText("Esta maravilla del mundo moderno, la cual es la construcción emblemática de todo China, sirvió como fortaleza a base de piedra, ladrillo, madera y tierra apisonada para proteger a este país asiático de las posibles invasiones de los mongoles por el norte. Originalmente llegó a medir más de 21 mil kilómetros cuando fue construida entre los siglos V a.c. y XVI d.c. (¡Más de 2000 años!) y millones de obreros perdieron la vida durante su construcción, lo que la convierte también en el mayor y más grande cementerio del mundo ya que estas personas fueron enterradas ahí. Existe una creencia que dicta que esta construcción puede ser divisada desde el espacio, pero fue desmentida por distintos astronautas.");
                break;
            default:
                break;
        }
    }
}
