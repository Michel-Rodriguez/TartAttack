package com.example.tartattack;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    ImageButton buttTA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttTA = (ImageButton)findViewById(R.id.buttPortada);
        buttTA.setOnClickListener((View.OnClickListener) this);
    }

    public void onClick(View view) {
        //se cambia de actividad
        if (buttTA.isPressed()) {
            openCambio();
        }
    }


    //Method openCambio que cambia la actividad si se pulsa el boton (mirar en method onClick)
    public void openCambio() {
        Intent intentCambio = new Intent(this, MainActivity2.class);
        startActivity(intentCambio);

    }
}