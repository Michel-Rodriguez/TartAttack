package com.example.tartattack;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class TartasPersonalizadas extends HomeActivity implements Serializable{

    private final String [] saboresTarta = new String[]{"Seleccione un sabor","Chocolate", "Vainilla", "Fresa", "Cheesecake", "Dulce Leche", "Nutella"};
    private Spinner spinnerCant, spinnerSabor1, spinnerSabor2, spinnerSabor3;
    private TextView  textoSabor2, textoSabor3;
    private String saborFinal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tartas_personalizadas);

        spinnerCant = findViewById(R.id.spinner_cant_sabores);
        spinnerSabor1 = findViewById(R.id.spinner_sabor_1);
        spinnerSabor2 = findViewById(R.id.spinner_sabor_2);
        spinnerSabor3 = findViewById(R.id.spinner_sabor_3);

        textoSabor2 = findViewById(R.id.sabor2);
        textoSabor3 = findViewById(R.id.sabor3);

        Button buttonAdd = findViewById(R.id.buttAddCart);


        ArrayList<String> cantidades = new ArrayList<>();
        ArrayList<String>sabores = new ArrayList<>();

        for(int i = 1; i < 4; i++){
            cantidades.add(String.valueOf(i));
        }

        sabores.addAll(Arrays.asList(saboresTarta));


        ArrayAdapter<String> myAdapter = new ArrayAdapter<>(TartasPersonalizadas.this, android.R.layout.simple_spinner_dropdown_item, cantidades);
        spinnerCant.setAdapter(myAdapter);
        spinnerCant.setSelection(-1);

        spinnerCant.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String cant = (String) spinnerCant.getAdapter().getItem(i);

                switch (cant) {
                    case "1":

                        textoSabor2.setVisibility(View.INVISIBLE);
                        spinnerSabor2.setVisibility(View.INVISIBLE);
                        spinnerSabor2.setSelection(0);

                        textoSabor3.setVisibility(View.INVISIBLE);
                        spinnerSabor3.setVisibility(View.INVISIBLE);
                        spinnerSabor3.setSelection(0);
                        break;
                    case "2":

                        textoSabor2.setVisibility(View.VISIBLE);
                        spinnerSabor2.setVisibility(View.VISIBLE);

                        textoSabor3.setVisibility(View.INVISIBLE);
                        spinnerSabor3.setVisibility(View.INVISIBLE);
                        spinnerSabor3.setSelection(0);
                        break;
                    case "3":

                        textoSabor2.setVisibility(View.VISIBLE);
                        spinnerSabor2.setVisibility(View.VISIBLE);
                        textoSabor3.setVisibility(View.VISIBLE);
                        spinnerSabor3.setVisibility(View.VISIBLE);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter myAdapterSabores = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, sabores);
        spinnerSabor1.setAdapter(myAdapterSabores);
        spinnerSabor2.setAdapter(myAdapterSabores);
        spinnerSabor3.setAdapter(myAdapterSabores);

        spinnerSabor1.setOnItemSelectedListener(myListener);
        spinnerSabor2.setOnItemSelectedListener(myListener);
        spinnerSabor3.setOnItemSelectedListener(myListener);

        buttonAdd.setOnClickListener(view -> {

            String [] saboresC = {(String) spinnerSabor1.getSelectedItem(), (String) spinnerSabor2.getSelectedItem(), (String) spinnerSabor3.getSelectedItem()};
            saborFinal = "Tarta ";

            for (int i = 0; i < saboresC.length; i++){
                if(saboresC[i].contains("Seleccione")) {
                    Log.i("String vacio ", saboresC[i]);
                }else {
                    saborFinal += saboresC[i] + "-";
                    Log.i("sabor añadido ",saboresC[i] );
                }
            }

            if (saborFinal.contains("Seleccione"))
                Toast.makeText(this, "Falta algun sabor por añadir, o revise cantidad de sabores elegida", Toast.LENGTH_LONG).show();
            else {
                Toast.makeText(this, "Ha preseleccionado tarta personalizada: " + saborFinal, Toast.LENGTH_LONG).show();
                //Tarta tartaPedida = new Tarta(saborFinal, "30,00 €", R.drawable.tarta_personalizada);
                enviarCesta(saborFinal, "30.00 €", R.drawable.tarta_personalizada);

            }

        });

    }


    AdapterView.OnItemSelectedListener myListener = new AdapterView.OnItemSelectedListener() {

        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i,
                                   long l) {
            String sab =  (String) spinnerSabor1.getAdapter().getItem(i);

            if ((sab != "" ) && !(sab.contains("Seleccione")))
                Toast.makeText(TartasPersonalizadas.this, "Ha elegido sabor "+sab, Toast.LENGTH_SHORT).show();


        }

        @Override
        public void onNothingSelected(AdapterView<?> arg0) {
            // TODO Auto-generated method stub

        }
    };

    public void enviarCesta(String s, String p, int img){

        Intent intent = new Intent(this, TartaVisualizacionDetalle.class);

        intent.putExtra("sabor", s);
        intent.putExtra("precio", p);
        intent.putExtra("img", img);
        startActivity(intent);

    }
}