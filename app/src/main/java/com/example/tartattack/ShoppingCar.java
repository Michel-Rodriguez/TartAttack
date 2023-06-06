package com.example.tartattack;

import static com.example.tartattack.utilidades.Utilidades.TABLA_TARTAPEDIDO;
import android.app.AlertDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;
import com.example.tartattack.utilidades.Utilidades;
import java.util.ArrayList;

public class ShoppingCar extends HomeActivity {

    ConexionSQLiteHelper conn;
    ArrayList<Tarta> listaTartas;
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

        miLista.setOnItemLongClickListener((adapterView, view, i, l) -> {

            AlertDialog.Builder dialogo1 = new AlertDialog.Builder(ShoppingCar.this);
            dialogo1.setTitle("Importante");
            dialogo1.setMessage("¿Desea eliminar este producto de la cesta?");
            dialogo1.setCancelable(false);

            dialogo1.setPositiveButton("Confirmar", (dialogo11, id) -> {
                listaTartas.remove(i);
                adapter.notifyDataSetChanged();
            });
            dialogo1.setNegativeButton("Cancelar", (dialogo112, id) -> {
            });
            dialogo1.show();
            return false;
        });


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
            //obtenerLista();

        }catch (Exception e){
            Toast.makeText(getApplicationContext(),"El documento no existe",Toast.LENGTH_LONG).show();
        }

    }

    public void limpiarTabla(View v){  //METODO PARA LIMPIAR TABLA

        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this, "bd_tartaspedido", null,1);
        SQLiteDatabase db = conn.getWritableDatabase();
        db.execSQL("DELETE FROM "+TABLA_TARTAPEDIDO);
        setContentView(R.layout.activity_shoppingcar);
        consultarLista();

    }


}