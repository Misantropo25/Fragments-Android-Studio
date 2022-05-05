package com.example.guiadefragmentos.sonido;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.guiadefragmentos.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link sonido#newInstance} factory method to
 * create an instance of this fragment.
 */
public class sonido extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public sonido() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment sonido.
     */
    // TODO: Rename and change types and number of parameters
    public static sonido newInstance(String param1, String param2) {
        sonido fragment = new sonido();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    ImageButton btnsalsa,btnhuayno,btnbachata,btnblues,btncountry,btnSalir;

    String nombreMusica;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_sonido, container, false);


        return view;

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnsalsa = view.findViewById(R.id.btnsalsa);
        btnbachata = view.findViewById(R.id.btnbachata);
        btnhuayno = view.findViewById(R.id.btnhuayno);
        btnblues = view.findViewById(R.id.btnblues);
        btncountry = view.findViewById(R.id.btncountry);
        btnSalir = view.findViewById(R.id.salirPrincipal);

        btnsalsa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nombreMusica = "Salsa";
                Bundle bundle = new Bundle();
                bundle.putString("musica",nombreMusica);
                getParentFragmentManager().setFragmentResult("key",bundle);
                Navigation.findNavController(view).navigate(R.id.reproducirMusica);
            }
        });

        btnbachata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nombreMusica = "Bachata";
                Bundle bundle = new Bundle();
                bundle.putString("musica",nombreMusica);
                getParentFragmentManager().setFragmentResult("key",bundle);
                Navigation.findNavController(view).navigate(R.id.reproducirMusica);
            }
        });

        btnhuayno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nombreMusica = "Huayno";
                Bundle bundle = new Bundle();
                bundle.putString("musica",nombreMusica);
                getParentFragmentManager().setFragmentResult("key",bundle);
                Navigation.findNavController(view).navigate(R.id.reproducirMusica);
            }
        });

        btnblues.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nombreMusica = "Blues";
                Bundle bundle = new Bundle();
                bundle.putString("musica",nombreMusica);
                getParentFragmentManager().setFragmentResult("key",bundle);
                Navigation.findNavController(view).navigate(R.id.reproducirMusica);
            }
        });

        btncountry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nombreMusica = "Country";
                Bundle bundle = new Bundle();
                bundle.putString("musica",nombreMusica);
                getParentFragmentManager().setFragmentResult("key",bundle);
                Navigation.findNavController(view).navigate(R.id.reproducirMusica);
            }
        });

        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.nav_home);

            }
        });

    }
}