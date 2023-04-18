package com.example.tartattack;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity3 extends AppCompatActivity implements View.OnClickListener {

    Button botonClas;
    ImageButton botonHome;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        botonClas = (Button) findViewById(R.id.buttClas);
        botonClas.setOnClickListener((View.OnClickListener) this);
        botonHome = (ImageButton) findViewById(R.id.buttHome);



    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onClick(View view) {
        if (botonClas.isPressed()) {
            openCambio();
        }
        if(botonHome.isPressed()){
            openHome();
        }
    }

    //Method openCambio que cambia la actividad si se pulsa el boton (mirar en method onClick)
    public void openCambio() {
        Intent intentCambio = new Intent(this, MainActivity4.class);
        startActivity(intentCambio);

    }

    public  void openHome(){
        Intent intentHome = new Intent(this, MainActivity2.class);
    }




}