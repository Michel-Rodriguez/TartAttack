package com.example.tartattack;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class TartasVeganas extends HomeActivity implements AdapterView.OnItemClickListener{

    ListView miLista;
    private String [] nombresTarta =new String [] {"Tarta de Chocate Vegana", "Tarta de Manzana Vegana", "Tarta de Queso Vegana",
            "Tarta Fresa Vegana", "Tarta 3 Chocolates Vegana"};
    private String [] precio = new String [] {"25,99 €", "25,99 €", "25,99 €", "25,99 €", "25,99"};
    private  int [] imagenes = new int [] {R.drawable.tarta_chocolate, R.drawable.tarta_manzana_sobre_fondo_blanco_67394_227, R.drawable.tarta_de_queso,
            R.drawable.tarta_crema_vainilla_fresa, R.drawable.tarta3chocolats};

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
        MiAdaptador adapter= new MiAdaptador(this,R.layout.mi_fila_personalizada, tartas);
        miLista.setAdapter(adapter);
        miLista.setOnItemClickListener(this);

    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

        String s = nombresTarta[position];
        String p = precio[position];
        int img = imagenes[position];
        Tarta t = new Tarta (s, p, img);
        enviarCesta(s,p, img);  //metodo para añadir los elementos de la clase Tarta al activity de Shopping Car
    }

    public void enviarCesta(String s, String p, int img){

        Intent intent = new Intent(this, TartaVisualizacionDetalle.class);

        intent.putExtra("sabor", s);
        intent.putExtra("precio", p);
        intent.putExtra("img", img);
        startActivity(intent);

    }


}