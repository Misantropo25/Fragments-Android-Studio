package com.example.guiadefragmentos.maravillasModernas;

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
 * Use the {@link maravillasModernas#newInstance} factory method to
 * create an instance of this fragment.
 */
public class maravillasModernas extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public maravillasModernas() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment maravillasModernas.
     */
    // TODO: Rename and change types and number of parameters
    public static maravillasModernas newInstance(String param1, String param2) {
        maravillasModernas fragment = new maravillasModernas();
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

    ImageButton chiche,petra,coliseo,cristo,machu,muralla;

    String nombreMaravilla;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_maravillas_modernas, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        chiche = view.findViewById(R.id.chiche);
        petra = view.findViewById(R.id.petra);
        coliseo = view.findViewById(R.id.coliseo);
        cristo = view.findViewById(R.id.cristo);
        machu = view.findViewById(R.id.machu);
        muralla = view.findViewById(R.id.muralla);


        chiche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nombreMaravilla = "Chiche";
                Bundle bundle = new Bundle();
                bundle.putString("nombre",nombreMaravilla);
                getParentFragmentManager().setFragmentResult("key",bundle);
                Navigation.findNavController(view).navigate(R.id.infoMaravilla);
            }
        });

        petra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nombreMaravilla = "Petra";
                Bundle bundle = new Bundle();
                bundle.putString("nombre",nombreMaravilla);
                getParentFragmentManager().setFragmentResult("key",bundle);
                Navigation.findNavController(view).navigate(R.id.infoMaravilla);
            }
        });

        coliseo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nombreMaravilla = "Coliseo";
                Bundle bundle = new Bundle();
                bundle.putString("nombre",nombreMaravilla);
                getParentFragmentManager().setFragmentResult("key",bundle);
                Navigation.findNavController(view).navigate(R.id.infoMaravilla);
            }
        });

        cristo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nombreMaravilla = "Cristo";
                Bundle bundle = new Bundle();
                bundle.putString("nombre",nombreMaravilla);
                getParentFragmentManager().setFragmentResult("key",bundle);
                Navigation.findNavController(view).navigate(R.id.infoMaravilla);
            }
        });

        machu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nombreMaravilla = "Machu";
                Bundle bundle = new Bundle();
                bundle.putString("nombre",nombreMaravilla);
                getParentFragmentManager().setFragmentResult("key",bundle);
                Navigation.findNavController(view).navigate(R.id.infoMaravilla);
            }
        });

        muralla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nombreMaravilla = "Muralla";
                Bundle bundle = new Bundle();
                bundle.putString("nombre",nombreMaravilla);
                getParentFragmentManager().setFragmentResult("key",bundle);
                Navigation.findNavController(view).navigate(R.id.infoMaravilla);
            }
        });
    }
}