package com.example.tartattack;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class TartasPersonalizadas extends HomeActivity {

    private final String [] saboresTarta = new String[]{"","Chocolate", "Vainilla", "Fresa", "Cheesecake", "Dulce Leche", "Nutella"};
    private Spinner spinnerCant, spinnerSabor1, spinnerSabor2, spinnerSabor3;
    private TextView textoSabor1, textoSabor2, textoSabor3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tartas_personalizadas);

        spinnerCant = findViewById(R.id.spinner_cant_sabores);
        spinnerSabor1 = findViewById(R.id.spinner_sabor_1);
        spinnerSabor2 = findViewById(R.id.spinner_sabor_2);
        spinnerSabor3 = findViewById(R.id.spinner_sabor_3);

        textoSabor1 = findViewById(R.id.sabor1);
        textoSabor2 = findViewById(R.id.sabor2);
        textoSabor3 = findViewById(R.id.sabor3);

        ArrayList<String> cantidades = new ArrayList<>();
        ArrayList<String>sabores = new ArrayList<>();

        for(int i = 1; i < 4; i++){
            cantidades.add(String.valueOf(i));
        }

        for(int i = 0; i < saboresTarta.length; i++){
            sabores.add(saboresTarta[i]);
        }



        ArrayAdapter myAdapter = new ArrayAdapter<>(TartasPersonalizadas.this, android.R.layout.simple_spinner_dropdown_item, cantidades);
        spinnerCant.setAdapter(myAdapter);
        spinnerCant.setSelection(-1);

        spinnerCant.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String cant = (String) spinnerCant.getAdapter().getItem(i);
                Toast.makeText(TartasPersonalizadas.this, "Has elegido "+cant, Toast.LENGTH_SHORT).show();

                if (cant.equals("1")){

                    textoSabor2.setVisibility(View.INVISIBLE);
                    spinnerSabor2.setVisibility(View.INVISIBLE);
                    textoSabor3.setVisibility(View.INVISIBLE);
                    spinnerSabor3.setVisibility(View.INVISIBLE);
                } else if (cant.equals("2")) {

                    textoSabor2.setVisibility(View.VISIBLE);
                    spinnerSabor2.setVisibility(View.VISIBLE);
                    textoSabor3.setVisibility(View.INVISIBLE);
                    spinnerSabor3.setVisibility(View.INVISIBLE);
                }else if (cant.equals("3")) {
                    textoSabor2.setVisibility(View.VISIBLE);
                    spinnerSabor2.setVisibility(View.VISIBLE);
                    textoSabor3.setVisibility(View.VISIBLE);
                    spinnerSabor3.setVisibility(View.VISIBLE);
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


    }
}