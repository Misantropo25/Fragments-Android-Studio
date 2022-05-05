package com.example.guiadefragmentos.lectorqr;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import com.example.guiadefragmentos.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link infoQr#newInstance} factory method to
 * create an instance of this fragment.
 */
public class infoQr extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public infoQr() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment infoQr.
     */
    // TODO: Rename and change types and number of parameters
    public static infoQr newInstance(String param1, String param2) {
        infoQr fragment = new infoQr();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    String lininfo;
    WebView wb1;
    TextView txtmuestra1;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getParentFragmentManager().setFragmentResultListener("key", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                lininfo = result.getString("enlace");
                Toast.makeText(getActivity(), "Scanned: " + lininfo, Toast.LENGTH_LONG).show();
                txtmuestra1.setText(lininfo);
                if(lininfo!=null){
                    wb1.setWebViewClient(new WebViewClient());
                    wb1.loadUrl(lininfo);
                    txtmuestra1.setText(lininfo);
                }
            }
        });


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_info_qr, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        txtmuestra1 = view.findViewById(R.id.txtmuestra1);
        wb1 = view.findViewById(R.id.visualizacion1);




    }
}