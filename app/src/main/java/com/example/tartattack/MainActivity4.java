package com.example.tartattack;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity4 extends AppCompatActivity implements View.OnClickListener{

    ImageButton botonMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        //botonMenu = (ImageButton)findViewById(R.id.menubotoni2);
        //botonMenu.setOnClickListener((View.OnClickListener) this);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == R.id.idHome) {
            openCambio();
            return true;
        }
        /*int id = item.getItemId();
        if (id == R.id.idHome) {
            openHome();
            //return true;
        }if (id == R.id.idIVeganas) {
            return true;
        }*/
        /*switch(item.getItemId()){
            case R.id.idHome:
                openHome();
                break;

        }*/

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        if (botonMenu.isPressed()) {
            openCambio();
        }
    }

    //Method openCambio que cambia la actividad si se pulsa el boton (mirar en method onClick)
   public void openCambio() {
        Intent intentCambio = new Intent(this, MainActivity2.class);
        startActivity(intentCambio);
    }

    public void openHome(){
        Intent intentHome = new Intent(this, MainActivity2.class);
        startActivity(intentHome);
    }


}

