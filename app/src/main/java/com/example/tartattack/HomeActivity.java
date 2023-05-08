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
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class HomeActivity extends AppCompatActivity {   //Clase Padre

    //Bundle operadores = getIntent().getExtras();
    //int numI = operadores.getInt("numero Intent");
    int i = 8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

       //Bundle operadores = getIntent().getExtras();
       //if(operadores.isEmpty()){
           //Log.i("Bunde", "Bundle vacio");
       //}
       //int numI = operadores.getInt("numIntent");

        /*if(numI != 1){
            try {
                Thread.sleep(3000);
                setTheme(R.style.Theme_TartAttack);
               // Log.i("Tag ciclo vida", String.valueOf(numI));
                //Log.i("msj", String.valueOf(numI));


            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }else{
            Log.i("Tag Prueba IF => ELSE", String.valueOf(numI));
        }*/

        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);


    }
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull  MenuItem item){

        int id = item.getItemId();

        if(id == R.id.idClasicas){
            openClasicas();
            Log.i("Tag intent", "Intent a t.Clasicas");
            return true;
        }
        if(id == R.id.idIVeganas){
            return true;
        }
        if (id == R.id.idHome){
           openHome();
            return true;
        }
        if (id == R.id.idSinGluten){
            Intent SinGluten = new Intent(this, TartasSinGluten.class);
            startActivity(SinGluten);
            return true;
        }
        return super.onOptionsItemSelected(item);

    }


    //****Metodos que heredan al resto de clases

    public void accesoImg(View vv) {
        Intent intentCambio = new Intent(this, TartasClasicas.class);
        startActivity(intentCambio);
    }

    public void openClasicas(){
        Intent intentClasicas = new Intent(this, TartasClasicas.class);
        startActivity(intentClasicas);
    }

    public void openHome() {      //Method que cambia a la actividad principal
        Intent intentCambio = new Intent(this, HomeActivity.class);
        startActivity(intentCambio);
    }

    public void comprar(View vv){  //Metodo para añadir elemento a la cesta de compra
        Toast.makeText(this, "XXXXXXXXXXXXXXXX", Toast.LENGTH_SHORT).show();
        Intent inte = new Intent(this, MainActivity3.class); //Creamos un intent el cual pasara a la Cesta
        startActivity(inte);   //al iniciar el intent tambien se pasaran archivos a la cesta
    }


    // Clase Tarta y Metodos para Adaptar ListView,


    public class MiAdaptador extends ArrayAdapter<Tarta> {
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


    }


}

