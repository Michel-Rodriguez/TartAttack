package com.example.tartattack;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class TartasVeganas extends HomeActivity implements AdapterView.OnItemClickListener{

    ListView miLista;
    private String [] nombresTarta =new String [] {"Tarta de Chocate SG", "Tarta de Queso SG", "Tarta de Fresa SG",
            "Tarta Dulce Leche SG", "Tarta 3 Chocolates SG"};
    private String [] precio = new String [] {"25,99 €", "25,99 €", "25,99 €", "25,99 €", "25,99"};
    private  int [] imagenes = new int [] {R.drawable.tarta_chocolate, R.drawable.tarta_de_queso, R.drawable.tartafresa,
            R.drawable.tartadulcelche, R.drawable.tarta3ch};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ArrayList<Tarta> tartas = new ArrayList<Tarta>();
        for(int i = 0; i < nombresTarta.length; i++){
            Tarta t = new Tarta(nombresTarta[i], precio[i], imagenes[i]);
            tartas.add(t);
        }


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tartas_veganas);

        miLista = findViewById(R.id.miListaD);

        MiAdaptador adapter=
                new MiAdaptador(this,R.layout.mi_fila_personalizada, tartas);
        ////support_simple_spinner_dropdown_item
        //enfuchar adaptador a la vista
        miLista.setAdapter(adapter);
        miLista.setOnItemClickListener(this);

    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

        Toast.makeText(this, "Se ha seleccionado: "+nombresTarta[position],Toast.LENGTH_SHORT).show();
    }

}