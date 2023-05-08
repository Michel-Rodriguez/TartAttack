package com.example.tartattack;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


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
            openM4();
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

    public void openCambio() {
        Intent intentCambio = new Intent(this, MainActivity3.class);
        startActivity(intentCambio);
    }

    public void openM4(){
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


}

