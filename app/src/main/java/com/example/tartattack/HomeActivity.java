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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class HomeActivity extends AppCompatActivity {   //Clase Padre

    public int idTarta = 00000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);

        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this, "bd_tartaspedido", null,1);

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
        Intent intentCambio = new Intent(this, TartasClasicas.class);
        startActivity(intentCambio);
    }

    public void openClasicas() {
        Intent intentClasicas = new Intent(this, TartasClasicas.class);
        startActivity(intentClasicas);
    }

    public void openHome() {      //Method que cambia a la actividad principal
        Intent intentCambio = new Intent(this, HomeActivity.class);
        startActivity(intentCambio);
    }

    public void comprar(View v) {  //Metodo para añadir elemento a la cesta de compra
        Toast.makeText(this, "Ha añadido producto a la cesta", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, ShoppingCar.class); //Creamos un intent el cual pasara a la Cesta


        startActivity(intent);   //al iniciar el intent tambien se pasaran archivos a la cesta
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
            return crearFila(position, convertView, parent);
        }

        private View crearFila(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            //Este método es invocado tantas veces como "filas" se pinten en la actividad

            LayoutInflater miInflador = getLayoutInflater();
            View miFila = miInflador.inflate(mResource, parent, false);

            TextView txtTarta = miFila.findViewById(R.id.txtTarta);
            TextView txtPrecio = miFila.findViewById(R.id.txtPrecio);
            ImageView imgTarta = miFila.findViewById(R.id.imgTarta);

            txtTarta.setText(misTartas.get(position).sabor);
            txtPrecio.setText(misTartas.get(position).precio);
            imgTarta.setImageResource(misTartas.get(position).imagen);

            return miFila;
        }
    }
    //**Clase Tarta para añadir a la lista personalizada**
    public static class Tarta implements Serializable {
        String sabor, precio;
        int imagen, id;
        public Tarta(String sabor, String precio, int imagen) {  //Constructor de la clase Tarta

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

        public String getPrecio() {
            return precio;
        }

        public void setPrecio(String precio) {
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

