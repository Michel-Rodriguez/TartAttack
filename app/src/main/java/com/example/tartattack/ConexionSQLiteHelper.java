package com.example.tartattack;

import static com.example.tartattack.utilidades.Utilidades.CAMPO_ID;
import static com.example.tartattack.utilidades.Utilidades.CAMPO_IMAGEN;
import static com.example.tartattack.utilidades.Utilidades.CAMPO_PRECIO;
import static com.example.tartattack.utilidades.Utilidades.CAMPO_SABOR;
import static com.example.tartattack.utilidades.Utilidades.TABLA_TARTAPEDIDO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

import com.example.tartattack.utilidades.Utilidades;

public class ConexionSQLiteHelper extends SQLiteOpenHelper {


    public ConexionSQLiteHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(Utilidades.CREAR_TABLA_TARTAPEDIDO);
        //db.execSQL("CREATE TABLE "+""+TABLA_TARTAPEDIDO+
          //      " ("+CAMPO_ID+" INT, "+CAMPO_SABOR+" TEXT,"+CAMPO_PRECIO+" TEXT, "+CAMPO_IMAGEN+" INT)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAntigua, int versionNueva) {
        db.execSQL("DROP TABLE IF EXISTS tartapedido");
        onCreate(db);
    }

}
