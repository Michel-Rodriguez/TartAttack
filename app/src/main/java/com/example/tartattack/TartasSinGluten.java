package com.example.tartattack;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import java.util.ArrayList;

public class TartasSinGluten extends HomeActivity implements AdapterView.OnItemClickListener{

    ListView miLista;
    private final String [] nombresTarta =new String [] {"Tarta de Chocate Sin Gluten", "Tarta de Queso Sin Gluten", "Tarta de Fresa Sin GLuten",
            "Tarta Zanahoria Sin Gluten", "Tarta 3 Chocolates Sin Gluten"};
    private final double [] precio = new double[] {25.99, 25.99, 25.99, 25.99, 25.99};
    private final int [] imagenes = new int [] {R.drawable.tarta_chocolate, R.drawable.tarta_de_queso, R.drawable.tarta_crema_vainilla_fresa,
            R.drawable.tarta_zan, R.drawable.tarta3chocolats};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this, "bd_tartaspedido", null,1);
        ArrayList<Tarta> tartas = new ArrayList<>();

        for(int i = 0; i < nombresTarta.length; i++){
            Tarta t = new Tarta(nombresTarta[i], precio[i], imagenes[i]);
            tartas.add(t);
        }


        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_tartas_sin_gluten);
        miLista = findViewById(R.id.miListaD);
        MiAdaptador adapter= new MiAdaptador(this,R.layout.mi_fila_personalizada, tartas);

        miLista.setAdapter(adapter);
        miLista.setOnItemClickListener(this);

    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

        String s = nombresTarta[position];
        double p = precio[position];
        int img = imagenes[position];
        //Tarta t = new Tarta (s, p, img);
        enviarCesta(s,p, img);  //metodo para añadir los elementos de la clase Tarta al activity de Shopping Car

    }

    public void enviarCesta(String s, double p, int img){

        Intent intent = new Intent(this, TartaVisualizacionDetalle.class);

        intent.putExtra("sabor", s);
        intent.putExtra("precio", p);
        intent.putExtra("img", img);
        startActivity(intent);

    }


}



