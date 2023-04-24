package com.example.tartattack;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener{

    ImageButton botonMenu;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        try {
            Thread.sleep(3000);
            setTheme(R.style.Theme_TartAttack);

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


    }
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull  MenuItem item){
        /*switch(item.getItemId()) {
            case R.id.idClasicas:
                //Toast.makeText(this, "Se ha pulsado el menu de clasicas", Toast.LENGTH_SHORT).show();
                //openM4();
                break;
            default:
                //Toast.makeText(this, "Se ha pulsado " + item.getTitle().toString(), Toast.LENGTH_SHORT).show();
        }*/
        int id = item.getItemId();

        if(id == R.id.idClasicas){
            openM4();
            return true;
        }
        if(id == R.id.idIVeganas){
            return true;
        }
        if (id == R.id.idHome){
            openCambio();
            return true;
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    public void onClick(View view) {
        if (botonMenu.isPressed()) {
            openCambio();
        }else
            openM4();
    }

    //Method openCambio que cambia la actividad si se pulsa el boton (mirar en method onClick)
    public void openCambio() {
        Intent intentCambio = new Intent(this, MainActivity3.class);
        startActivity(intentCambio);

    }

    public void openM4(){
        Intent intentClasicas = new Intent(this, MainActivity4.class);
        startActivity(intentClasicas);
    }


}