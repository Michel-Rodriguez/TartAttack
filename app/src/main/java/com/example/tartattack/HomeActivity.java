package com.example.tartattack;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class HomeActivity extends AppCompatActivity {   //Clase Padre

    public static int idTarta = 0;
    ImageButton tarta1, tarta2, tarta3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);
        tarta1 = findViewById(R.id.tarta1);
        tarta2 = findViewById(R.id.tarta2);
        tarta3 = findViewById(R.id.tarta3);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.idClasicas) {
            openClasicas();
            Log.i("Tag intent", "Intent a t.Clasicas");
            return true;
        }
        else if (id == R.id.idIVeganas) {
            Intent Veganas = new Intent(this, TartasVeganas.class);
            startActivity(Veganas);
            return true;
        }
        else if (id == R.id.idHome) {
            openHome();
            return true;
        }
        else if (id == R.id.idSinGluten) {
            Intent SinGluten = new Intent(this, TartasSinGluten.class);
            startActivity(SinGluten);
            return true;
        }
        else if (id == R.id.idUser) {
            Intent User = new Intent(this, Sesion_Usuario_Aut.class);
            startActivity(User);
            return true;
        }else if (id == R.id.idPersonalizadas) {
            Intent personalizadas = new Intent(this, TartasPersonalizadas.class);
            startActivity(personalizadas);
            return true;
        }else if (id == R.id.idCesta) {
            Intent shop = new Intent(this, ShoppingCar.class);
            startActivity(shop);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void accesoImg(View vv) {
        //Intent intentCambio = new Intent(this, TartasClasicas.class);
        String s = "sabor";
        double p = 29.99;
        int img = 0;


        if(tarta1.isPressed()){
            s = "Tart 3 chocolate";
            img = R.drawable.tarta3chocolats;
        }else if(tarta2.isPressed()){
            s = "Tarta de Fresa";
            img = R.drawable.tarta_crema_vainilla_fresa;

        }else if(tarta3.isPressed()){
            s = "Tarta de Dulce de leche";
            img = R.drawable.tarta_dulcelexe;
        }

        Intent intent = new Intent(HomeActivity.this, TartaVisualizacionDetalle.class);
        intent.putExtra("sabor", s);
        intent.putExtra("precio", p);
        intent.putExtra("img", img);
        startActivity(intent);

    }

    public void openClasicas() {
        Intent intentClasicas = new Intent(this, TartasClasicas.class);
        startActivity(intentClasicas);
    }

    public void openHome() {      //Method que cambia a la actividad principal
        Intent intentCambio = new Intent(this, HomeActivity.class);
        startActivity(intentCambio);
    }

    // Clase Tarta y Metodos para Adaptar ListView,

    public class MiAdaptador extends ArrayAdapter<Tarta> {
        private final int mResource;
        private final ArrayList<Tarta> misTartas;


        public MiAdaptador(@NonNull Context context, int resource, @NonNull List<Tarta> objects) {
            super(context, resource, objects);
            mResource = resource;
            misTartas = (ArrayList<Tarta>) objects;
        }

        //Este método sólo es necesario reescribirlo si el adaptador se enchufa a un spinner
        @Override
        public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            return crearFila(position, parent);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            return crearFila(position, parent);
        }

        private View crearFila(int position, @NonNull ViewGroup parent) {
            //Este método es invocado tantas veces como "filas" se pinten en la actividad

            LayoutInflater miInflador = getLayoutInflater();
            View miFila = miInflador.inflate(mResource, parent, false);

            TextView txtTarta = miFila.findViewById(R.id.txtTarta);
            TextView txtPrecio = miFila.findViewById(R.id.txtPrecio);
            ImageView imgTarta = miFila.findViewById(R.id.imgTarta);

            txtTarta.setText(misTartas.get(position).sabor);
            txtPrecio.setText(String.valueOf(misTartas.get(position).precio));
            imgTarta.setImageResource(misTartas.get(position).imagen);

            return miFila;
        }

    }
    //**Clase Tarta para añadir a la lista personalizada**
    public static class Tarta implements Serializable {
        String sabor;
        double precio;
        int imagen, id;
        public Tarta(String sabor, double precio, int imagen) {  //Constructor de la clase Tarta

            this.sabor = sabor;
            this.precio = precio;
            this.imagen = imagen;
        }

        public Tarta() {

        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getSabor() {
            return sabor;
        }

        public void setSabor(String sabor) {
            this.sabor = sabor;
        }

        public double getPrecio() {
            return precio;
        }

        public void setPrecio(double precio) {
            this.precio = precio;
        }

        public int getImagen() {
            return imagen;
        }

        public void setImagen(int imagen) {
            this.imagen = imagen;
        }
    }

}

