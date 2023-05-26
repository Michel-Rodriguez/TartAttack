package com.example.tartattack;

import static com.example.tartattack.utilidades.Utilidades.CAMPO_ID;
import static com.example.tartattack.utilidades.Utilidades.CAMPO_IMAGEN;
import static com.example.tartattack.utilidades.Utilidades.CAMPO_PRECIO;
import static com.example.tartattack.utilidades.Utilidades.CAMPO_SABOR;
import static com.example.tartattack.utilidades.Utilidades.TABLA_TARTAPEDIDO;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.tartattack.utilidades.Utilidades;

import java.util.ArrayList;

public class TartasClasicas extends HomeActivity implements AdapterView.OnItemClickListener{

    ListView miLista;
    private  int pos;
    private String [] nombresTarta =new String [] {"Tarta de Chocate", "Tarta de Queso", "Tarta de Fresa",
            "Tarta Dulce Leche", "Tarta 3 Chocolates"};
    private String [] precio = new String [] {"25,99 €", "25,99 €", "25,99 €", "25,99 €", "25,99"};
    private  int [] imagenes = new int [] {R.drawable.tarta_chocolate, R.drawable.tarta_de_queso, R.drawable.tartafresa,
            R.drawable.tartadulcelche, R.drawable.tarta3ch};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ArrayList<Tarta> tartas = new ArrayList<>();
        for(int i = 0; i < nombresTarta.length; i++){
            Tarta t = new Tarta(nombresTarta[i], precio[i], imagenes[i]);
            tartas.add(t);
        }



        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_tartas_clasicas);

        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this, "bd_tartaspedido", null,1);

        miLista = findViewById(R.id.miLista);

        MiAdaptador adapter= new MiAdaptador(this,R.layout.mi_fila_personalizada, tartas);
        ////support_simple_spinner_dropdown_item
        //enfuchar adaptador a la vista
        miLista.setAdapter(adapter);
        miLista.setOnItemClickListener(this);

    }



    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        Toast.makeText(this, "Se ha seleccionado: "+nombresTarta[position],Toast.LENGTH_SHORT).show();
        pos = position;
        Log.i("Click", "click en el elemento " + position + " de mi ListView");
    }

    @Override
    public void comprar(View v) {
        Toast.makeText(this, "Se ha seleccionado: "+nombresTarta[pos],Toast.LENGTH_SHORT).show();
        //addTartaDB();
    }

    public void addTartaDB(){
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this, "bd_tartaspedido", null,1);
        SQLiteDatabase db = conn.getWritableDatabase();
        //db.execSQL("DROP TABLE "+TABLA_TARTAPEDIDO);
        //db.execSQL("CREATE TABLE "+""+TABLA_TARTAPEDIDO+
        //        " ("+CAMPO_ID+" INT, "+CAMPO_SABOR+" TEXT,"+CAMPO_PRECIO+" TEXT, "+CAMPO_IMAGEN+" INT)");
        //limpiarTabla(db);
        ContentValues values = new ContentValues();
        values.put(Utilidades.CAMPO_ID, idTarta++);   //campo IDlISTA
        values.put(Utilidades.CAMPO_SABOR, nombresTarta[pos]);
        values.put(Utilidades.CAMPO_PRECIO, precio[pos]);
        values.put(Utilidades.CAMPO_IMAGEN, imagenes[pos]);

        long idResultante = db.insert(Utilidades.TABLA_TARTAPEDIDO, Utilidades.CAMPO_ID, values);
        Toast.makeText(this, "ID REGISTRO: "+idResultante, Toast.LENGTH_SHORT).show();
        db.close();
    }

    public void limpiarTabla(SQLiteDatabase db){  //METODO PARA LIMPIAR TABLA

        db.execSQL("DELETE FROM "+TABLA_TARTAPEDIDO);
    }






}

