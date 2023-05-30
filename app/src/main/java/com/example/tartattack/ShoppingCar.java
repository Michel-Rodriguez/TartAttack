package com.example.tartattack;


import static com.example.tartattack.utilidades.Utilidades.TABLA_TARTAPEDIDO;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import com.example.tartattack.utilidades.Utilidades;
import java.util.ArrayList;


public class ShoppingCar extends HomeActivity {

    ConexionSQLiteHelper conn;
    ArrayList<Tarta> listaTartas;
    ArrayList<String> listaInformacion;
    ListView miLista;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        conn = new ConexionSQLiteHelper(this, "bd_tartaspedido", null, 1);
        setContentView(R.layout.activity_shoppingcar);
        miLista = findViewById(R.id.miListaD);
        consultarLista();


        MiAdaptador adapter = new MiAdaptador(this, R.layout.mi_fila_personalizada, listaTartas);
        miLista.setAdapter(adapter);



    }

    private void consultarLista(){
        SQLiteDatabase db = conn.getReadableDatabase();

        Tarta Tarta = null;
        listaTartas=new ArrayList<Tarta>();
        try {
            Cursor cursor=db.rawQuery("SELECT * FROM " + Utilidades.TABLA_TARTAPEDIDO,null);
            while (cursor.moveToNext()){
                Tarta = new Tarta();
                Tarta.setId(cursor.getInt(0));
                Log.i("A VER PISHITA", String.valueOf(cursor.getInt(0)));
                Tarta.setSabor(cursor.getString(1));
                Tarta.setPrecio(cursor.getString(2));
                Tarta.setImagen(cursor.getInt(3));

                listaTartas.add(Tarta);
            }
            //obtenerLista();

        }catch (Exception e){
            Toast.makeText(getApplicationContext(),"El documento no existe",Toast.LENGTH_LONG).show();
        }


    }




    private void eliminarProducto() {
        SQLiteDatabase db=conn.getWritableDatabase();
        //String[] parametros={campoId.getText().toString()};

       // db.delete(Utilidades.TABLA_TARTAPEDIDO,Utilidades.CAMPO_ID+"=?",parametros);
        Toast.makeText(getApplicationContext(),"Ya se Eliminó el producto",Toast.LENGTH_LONG).show();

        db.close();
    }

    public void limpiarTabla(){  //METODO PARA LIMPIAR TABLA
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this, "bd_tartaspedido", null,1);
        SQLiteDatabase db = conn.getWritableDatabase();
        db.execSQL("DELETE FROM "+TABLA_TARTAPEDIDO);
    }











}