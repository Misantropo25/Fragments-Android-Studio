package com.example.guiadefragmentos.calculadora;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.guiadefragmentos.R;

import java.util.ArrayList;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link calculadora#newInstance} factory method to
 * create an instance of this fragment.
 */
public class calculadora extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public calculadora() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment calculadora.
     */
    // TODO: Rename and change types and number of parameters
    public static calculadora newInstance(String param1, String param2) {
        calculadora fragment = new calculadora();
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

    Button btnLimpiar,btnPotencia,btnDivision,btnSiete,btnOcho,btnNueve,btnMultiplicacion,btnCuatro,btnCinco,btnSeis,btnMenos,btnUno,btnDos,btnTres,btnMas,btnZero,btnPunto,btnIgual,btnParentesis,btnPorcentaje ;
    ImageButton btnBorrarUltimo;
    TextView txtView1,txtView2;

    String ecuacion="";
    String formula = "";
    String formulaTemporal="";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_calculadora, container, false);


        btnParentesis = view.findViewById(R.id.btnParentesis);
        btnLimpiar = view.findViewById(R.id.btnLimpiar);
        btnBorrarUltimo = view.findViewById(R.id.btnBorrarUltimo);
        btnPotencia = view.findViewById(R.id.btnPotencia);
        btnDivision = view.findViewById(R.id.btnDivision);
        btnSiete = view.findViewById(R.id.btnSiete);
        btnOcho = view.findViewById(R.id.btnOcho);
        btnNueve = view.findViewById(R.id.btnNueve);
        btnMultiplicacion = view.findViewById(R.id.btnMultiplicacion);
        btnPorcentaje = view.findViewById(R.id.btnPorcentaje);
        btnCinco = view.findViewById(R.id.btnCinco);
        btnCuatro = view.findViewById(R.id.btnCuatro);
        btnSeis = view.findViewById(R.id.btnSeis);
        btnMenos = view.findViewById(R.id.btnMenos);
        btnUno = view.findViewById(R.id.btnUno);
        btnDos = view.findViewById(R.id.btnDos);
        btnTres = view.findViewById(R.id.btnTres);
        btnMas = view.findViewById(R.id.btnMas);
        btnZero = view.findViewById(R.id.btnZero);
        btnPunto = view.findViewById(R.id.btnPunto);
        btnIgual = view.findViewById(R.id.btnIgual);
        txtView1 = view.findViewById(R.id.textView1);
        txtView2 = view.findViewById(R.id.textView2);


        btnZero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setEcuacion("0");
            }
        });

        btnUno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setEcuacion("1");
            }
        });

        btnDos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setEcuacion("2");
            }
        });

        btnTres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setEcuacion("3");
            }
        });

        btnCuatro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setEcuacion("4");
            }
        });

        btnCinco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setEcuacion("5");
            }
        });

        btnSeis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setEcuacion("6");
            }
        });

        btnSiete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setEcuacion("7");
            }
        });

        btnOcho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setEcuacion("8");
            }
        });

        btnNueve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setEcuacion("9");
            }
        });

        btnPorcentaje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setEcuacion("/100");
            }
        });

        btnPotencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setEcuacion("^");
            }
        });


        btnMas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setEcuacion("+");
            }
        });

        btnMenos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setEcuacion("-");
            }
        });

        btnMultiplicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setEcuacion("*");
            }
        });

        btnDivision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setEcuacion("/");
            }
        });

        btnPunto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setEcuacion(".");
            }
        });


        btnLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtView1.setText("");
                txtView2.setText("");
                ecuacion="";
                parenIzquierdo = true;
            }
        });


        btnIgual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Double result = null;
                ScriptEngine engine = new ScriptEngineManager().getEngineByName("rhino");
                Revisar();

                try {
                    result = (double)engine.eval(formula);
                } catch (ScriptException e)
                {
                    Toast.makeText(view.getContext(),"Valor ingresados invalidos", Toast.LENGTH_SHORT).show();
                }

                if(result != null)
                    txtView2.setText(String.valueOf(result.doubleValue()));
            }
        });

        btnBorrarUltimo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BorrarUltimo(view);
            }
        });

        btnParentesis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                parentesisRevision(view);
            }
        });
        return view;
    }

    public void BorrarUltimo(View view)
    {
        String ecuaTemp ;
        ecuaTemp = ecuacion;
        ecuacion = "";
        ecuaTemp= ecuaTemp.replaceFirst(".$","");
        setEcuacion(ecuaTemp);
    }


    private void setEcuacion(String valorIngresado)
    {

        ecuacion = ecuacion + valorIngresado;
        txtView1.setText(ecuacion);
    }


    boolean parenIzquierdo = true;
    public void parentesisRevision(View view)
    {
        if(parenIzquierdo)
        {
            setEcuacion("(");
            parenIzquierdo = false;
        }
        else
        {
            setEcuacion(")");
            parenIzquierdo = true;
        }
    }

    private void Revisar()
    {
        ArrayList<Integer> indexOfPowers = new ArrayList<>();
        for(int i = 0; i < ecuacion.length(); i++)
        {
            if (ecuacion.charAt(i) == '^')
                indexOfPowers.add(i);
        }

        formula = ecuacion;
        formulaTemporal = ecuacion;
        for(Integer index: indexOfPowers)
        {
            CambiarFormula(index);
        }
        formula = formulaTemporal;
    }

    private void CambiarFormula(Integer index)
    {
        String numeroIzquierdo = "";
        String numeroDerecho = "";

        for(int i = index + 1; i< ecuacion.length(); i++)
        {
            if(esNumerico(ecuacion.charAt(i)))
                numeroDerecho = numeroDerecho + ecuacion.charAt(i);
            else
                break;
        }

        for(int i = index - 1; i >= 0; i--)
        {
            if(esNumerico(ecuacion.charAt(i)))
                numeroIzquierdo = numeroIzquierdo + ecuacion.charAt(i);
            else
                break;
        }

        String original = numeroIzquierdo + "^" + numeroDerecho;
        String changed = "Math.pow("+numeroIzquierdo+","+numeroDerecho+")";

        formulaTemporal = formulaTemporal.replace(original,changed);

    }


    private boolean esNumerico(char c)
    {
        if((c <= '9' && c >= '0') || c == '.')
            return true;

        return false;
    }
}