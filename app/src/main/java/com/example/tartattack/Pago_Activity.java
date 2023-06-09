package com.example.tartattack;

import android.app.AlertDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tartattack.utilidades.Utilidades;

import java.util.ArrayList;

public class Pago_Activity extends HomeActivity {

    TextView textoPrecio;
    EditText textoNombre, textoDireccion;
    ConexionSQLiteHelper conn;
    ArrayList<Tarta> listaTartas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pago);
        Bundle bundle = getIntent().getExtras();
        double cont = bundle.getDouble("cuenta");

        conn = new ConexionSQLiteHelper(this, "bd_tartaspedido", null, 1);

        textoDireccion = findViewById(R.id.textoDireccion);
        textoPrecio = findViewById(R.id.textoPrecio);

        conn = new ConexionSQLiteHelper(this, "bd_tartaspedido", null, 1);
        consultarLista();





        textoPrecio.setText(String.format("Importe total: %s", String.valueOf(cont)));

    }









    private void consultarLista(){
        SQLiteDatabase db = conn.getReadableDatabase();

        Tarta Tarta;
        listaTartas= new ArrayList<>();
        try {
            Cursor cursor=db.rawQuery("SELECT * FROM " + Utilidades.TABLA_TARTAPEDIDO,null);
            while (cursor.moveToNext()){
                Tarta = new Tarta();
                Tarta.setId(cursor.getInt(0));
                Tarta.setSabor(cursor.getString(1));
                Tarta.setPrecio(cursor.getDouble(2));
                Tarta.setImagen(cursor.getInt(3));

                listaTartas.add(Tarta);
            }
            cursor.close();

        }catch (Exception e){
            Toast.makeText(getApplicationContext(),"El documento no existe",Toast.LENGTH_LONG).show();
        }

    }
}