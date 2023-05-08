package com.example.tartattack;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class TartasSinGluten extends HomeActivity implements AdapterView.OnItemClickListener{

    ListView miLista;
    private String [] nombresTarta =new String [] {"Tarta de Chocate Sin Gluten", "Tarta de Queso sin Gluten", "Tarta de Fresa Sin Gluten"};
    private String [] precio = new String [] {"25,99 €", "24,99 €", "24,99 €"};
    private  int [] imagenes = new int [] {R.drawable.tarta_chocolate, R.drawable.tarta_de_queso, R.drawable.tartafresa};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ArrayList<Tarta> tartas = new ArrayList<Tarta>();
        for(int i = 0; i < nombresTarta.length; i++){
            Tarta t = new Tarta(nombresTarta[i], precio[i], imagenes[i]);
            tartas.add(t);
        }


        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_tartas_sin_gluten);

        miLista = findViewById(R.id.miLista);

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


   /* public class MiAdaptador extends ArrayAdapter<Tarta> {
        private int mResource;
        private ArrayList<Tarta> misTartas;

        public MiAdaptador(@NonNull Context context, int resource, @NonNull List<Tarta> objects) {
            super(context, resource, objects);
            mResource = resource;
            misTartas = (ArrayList<Tarta>) objects;
        }

        //Este método sólo es necesario reescribirlo si el adaptador se enchufa a un spinner
        @Override
        public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            return crearFila(position, convertView, parent);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            //indiceCancion = misCanciones.get(position).titulo;
            return crearFila(position, convertView, parent);
        }

        private View crearFila(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            //Este método es invocado tantas veces como "filas" se pinten en la actividad

            LayoutInflater miInflador = getLayoutInflater();
            View miFila = miInflador.inflate(mResource, parent, false);

            TextView txtTarta = miFila.findViewById(R.id.txtTarta);
            TextView txtPrecio = miFila.findViewById(R.id.txtPrecio);
            ImageView imgTarta = miFila.findViewById(R.id.imgTarta);

            txtTarta.setText(misTartas.get(position).nombreTarta);
            txtPrecio.setText(misTartas.get(position).precio);
            imgTarta.setImageResource(misTartas.get(position).imagen);

            return miFila;
        }
    }



    //**Clase Tarta para añadir a la lista personalizada**
    public class Tarta {

        String nombreTarta;
        String precio;
        int imagen;

        public  Tarta (String nombreTarta, String precio, int imagen){  //Constructor de la clase Tarta

            this.nombreTarta = nombreTarta;
            this.precio = precio;
            this.imagen = imagen;

        }


    }*/


}



