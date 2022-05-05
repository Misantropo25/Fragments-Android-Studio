package com.example.guiadefragmentos.direccionamiento;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.guiadefragmentos.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link direccionamiento#newInstance} factory method to
 * create an instance of this fragment.
 */
public class direccionamiento extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public direccionamiento() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment direccionamiento.
     */
    // TODO: Rename and change types and number of parameters
    public static direccionamiento newInstance(String param1, String param2) {
        direccionamiento fragment = new direccionamiento();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    ImageButton face,whats,you,tik,disc;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_direccionamiento, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        face=view.findViewById(R.id.facebook);
        whats=view.findViewById(R.id.whatsapp);
        you=view.findViewById(R.id.youtube);
        tik=view.findViewById(R.id.tiktok);
        disc=view.findViewById(R.id.discord);

        face.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent facebook = getActivity().getPackageManager().getLaunchIntentForPackage("com.facebook.katana");
                startActivity(facebook);
            }
        });

        whats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent whats = getActivity().getPackageManager().getLaunchIntentForPackage("com.whatsapp");
                startActivity(whats);
            }
        });

        you.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent youtube = getActivity().getPackageManager().getLaunchIntentForPackage("com.google.android.youtube");
                startActivity(youtube);
            }
        });

        tik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent tiktok = getActivity().getPackageManager().getLaunchIntentForPackage("com.zhiliaoapp.musically");
                startActivity(tiktok);
            }
        });

        disc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent discord = getActivity().getPackageManager().getLaunchIntentForPackage("com.discord");
                startActivity(discord);
            }
        });


    }
}