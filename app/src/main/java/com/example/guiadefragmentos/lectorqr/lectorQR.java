package com.example.guiadefragmentos.lectorqr;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.guiadefragmentos.R;
import com.example.guiadefragmentos.databinding.ActivityMainBinding;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link lectorQR#newInstance} factory method to
 * create an instance of this fragment.
 */
public class lectorQR extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static ActivityMainBinding binding = null;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public lectorQR() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment lectorQR.
     */
    // TODO: Rename and change types and number of parameters
    public static lectorQR newInstance(String param1, String param2) {
        lectorQR fragment = new lectorQR();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    TextView linktxt,txtDato2;
    Button btnAbrirScaner,btnBuscarInfo;
    List<String> testList;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lector_q_r, container, false);
    }
    String link;
    String listaEnlaces;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        linktxt = view.findViewById(R.id.txtDato);
        btnAbrirScaner = view.findViewById(R.id.abrirScaner);
        btnBuscarInfo = view.findViewById(R.id.buscarInfo);
        txtDato2 = view.findViewById(R.id.txtDato2);


        cargarEnlaces();


    // Register the launcher and result handler
        final ActivityResultLauncher<ScanOptions> barcodeLauncher = registerForActivityResult(new ScanContract(),
                result -> {
                    if(result.getContents() == null) {
                        Toast.makeText(getActivity(), "Cancelled", Toast.LENGTH_LONG).show();

                    } else {
                        Toast.makeText(getActivity(), "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
                        linktxt.setText(result.getContents().toString());
                        link = result.getContents().toString();
//                        testList.add(result.getContents().toString());
                    }
                });
        ScanOptions options = new ScanOptions();
        options.setOrientationLocked(false);
        btnAbrirScaner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                barcodeLauncher.launch(options);

            }
        });

        btnBuscarInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("enlace",link);
                getParentFragmentManager().setFragmentResult("key",bundle);
                Navigation.findNavController(view).navigate(R.id.infoQr);
            }
        });


    }
    public void cargarEnlaces(){
        if(testList!=null){
            for (int i=0;i<testList.size();i++) {
                listaEnlaces+=testList.get(i);
                listaEnlaces+="\n";
            }
            txtDato2.setText(listaEnlaces);
        }
    }

}