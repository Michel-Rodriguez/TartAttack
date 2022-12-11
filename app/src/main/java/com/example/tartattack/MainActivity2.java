package com.example.tartattack;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener{

    ImageButton botonMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        botonMenu = (ImageButton) findViewById(R.id.menubotoni);
        botonMenu.setOnClickListener((View.OnClickListener) this);

    }

    @Override
    public void onClick(View view) {
        if (botonMenu.isPressed()) {
            openCambio();
        }
    }

    //Method openCambio que cambia la actividad si se pulsa el boton (mirar en method onClick)
    public void openCambio() {
        Intent intentCambio = new Intent(this, MainActivity3.class);
        startActivity(intentCambio);

    }

}