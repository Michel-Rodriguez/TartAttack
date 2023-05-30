package com.example.tartattack;

import static com.example.tartattack.utilidades.Utilidades.TABLA_TARTAPEDIDO;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tartattack.utilidades.Utilidades;

import java.util.ArrayList;
import java.util.List;

public class TartaVisualizacionDetalle extends HomeActivity {


    private ArrayList<HomeActivity.Tarta> misTartasX;
    ListView miLista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tarta_layout_detallado);

        Bundle bundle = getIntent().getExtras();
        String s = bundle.getString("sabor");
        String p = bundle.getString("precio");
        int i = bundle.getInt("img");



        miLista = findViewById(R.id.miListaD);
        misTartasX = new ArrayList<>();

        //HomeActivity.Tarta e = new HomeActivity.Tarta("COCACOLA", "55 €", R.drawable.tarta_chocolate);
        HomeActivity.Tarta t = new HomeActivity.Tarta(s,p,i);

        misTartasX.add(t);


        MiAdaptador adapter= new MiAdaptador(this,R.layout.tarta_layout_detallado_base, misTartasX);
        miLista.setAdapter(adapter);
        //miLista.setOnItemClickListener(this);


    }




    public class MiAdaptador extends ArrayAdapter<HomeActivity.Tarta> {
        private int mResource;
        private ArrayList<HomeActivity.Tarta> misTartas;


        public MiAdaptador(@NonNull Context context, int resource, @NonNull List<HomeActivity.Tarta> objects) {
            super(context, resource, objects);
            mResource = resource;
            misTartas = (ArrayList<HomeActivity.Tarta>) objects;
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

            TextView txtTarta = miFila.findViewById(R.id.textTitulo);
            TextView txtPrecio = miFila.findViewById(R.id.textViewPrecio);
            ImageView imgTarta = miFila.findViewById(R.id.imageViewTarta);

            txtTarta.setText(misTartasX.get(position).sabor);
            txtPrecio.setText(misTartasX.get(position).precio);
            imgTarta.setImageResource(misTartasX.get(position).imagen);

            return miFila;
        }
    }





    public void addTartaDB(View v){
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this, "bd_tartaspedido", null,1);
        SQLiteDatabase db = conn.getWritableDatabase();
        //db.execSQL("DROP TABLE "+TABLA_TARTAPEDIDO);
        //db.execSQL("CREATE TABLE "+""+TABLA_TARTAPEDIDO+
        //        " ("+CAMPO_ID+" INT, "+CAMPO_SABOR+" TEXT,"+CAMPO_PRECIO+" TEXT, "+CAMPO_IMAGEN+" INT)");
        //limpiarTabla(db);
        ContentValues values = new ContentValues();
        values.put(Utilidades.CAMPO_ID, HomeActivity.idTarta++);   //campo IDlISTA
        values.put(Utilidades.CAMPO_SABOR, misTartasX.get(0).getSabor());
        values.put(Utilidades.CAMPO_PRECIO, misTartasX.get(0).getPrecio());
        values.put(Utilidades.CAMPO_IMAGEN, misTartasX.get(0).getImagen());

        long idResultante = db.insert(Utilidades.TABLA_TARTAPEDIDO, Utilidades.CAMPO_ID, values);
        Toast.makeText(this, "ID REGISTRO: "+idResultante, Toast.LENGTH_SHORT).show();
        db.close();
    }









}