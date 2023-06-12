package com.example.tartattack;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tartattack.utilidades.Utilidades;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Pago_Activity extends HomeActivity {

    TextView textoPrecio;
    EditText textoNombre, textoTlf, textoDireccion, textoCP, textoCiudad, textoProvincia;
    ConexionSQLiteHelper conn;
    ArrayList<Tarta> listaTartas;

    public static  String urlPay = "https://buy.stripe.com/dR67uC6OvcX8dj2000";

    DecimalFormat df = new DecimalFormat("#.00");


    Button buttPay;


    private FirebaseFirestore dbFire;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pago);
        Bundle bundle = getIntent().getExtras();
        double cont = bundle.getDouble("cuenta");

        conn = new ConexionSQLiteHelper(this, "bd_tartaspedido", null, 1);
        dbFire = FirebaseFirestore.getInstance();

        textoNombre = findViewById(R.id.textoNombre);
        textoTlf = findViewById(R.id.textoTelefono);
        textoDireccion = findViewById(R.id.textoDireccion);
        textoCP = findViewById(R.id.textoCodigoPostal);
        textoCiudad = findViewById(R.id.textoCiudad);
        textoProvincia = findViewById(R.id.textoProvincia);
        textoPrecio = findViewById(R.id.textoPrecio);
        buttPay = findViewById(R.id.buttPay);

        conn = new ConexionSQLiteHelper(this, "bd_tartaspedido", null, 1);
        consultarLista();

        textoPrecio.setText(String.format("Importe total: %s", df.format(cont)));

        buttPay.setOnClickListener(v -> {
            if(textoNombre.getText().toString().isEmpty() || textoTlf.getText().toString().isEmpty() || textoDireccion.getText().toString().isEmpty()
            || textoCP.getText().toString().isEmpty() || textoCiudad.getText().toString().isEmpty() || textoProvincia.getText().toString().isEmpty()){

                Toast.makeText(this, "Falta introducir algun dato", Toast.LENGTH_LONG).show();
            }else{
                addPedido();
                Toast.makeText(this, "Se ha realizado pedido exitosamente", Toast.LENGTH_LONG).show();
                navPay();
                Intent intent = new Intent(this, CompraRealizada.class);
                //startActivity(intent);

            }


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

        }catch (Exception e){
            Toast.makeText(getApplicationContext(),"El documento no existe",Toast.LENGTH_LONG).show();
        }

    }

    public void addPedido(){

        Map<String, Object> map = new HashMap<>();

        map.put("user", correo);
        map.put("nombre", textoNombre.getText().toString());
        map.put("telefo", textoTlf.getText().toString().trim());
        map.put("direccion", textoDireccion.getText().toString());
        map.put("cp", textoCP.getText().toString());
        map.put("ciudad", textoCiudad.getText().toString());
        map.put("provincia", textoProvincia.getText().toString());
        map.put("listaTartas", listaTartas);
        map.put("total", textoPrecio.getText().toString());


       dbFire.collection("Pedidos").add(map).addOnSuccessListener(documentReference -> {
            Toast.makeText(this, "Creado exitosamente", Toast.LENGTH_SHORT).show();
            //finish();
        }).addOnFailureListener(e -> Toast.makeText(this, "Error al ingresar", Toast.LENGTH_SHORT).show());


    }

    public void navPay (){
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(urlPay));
        startActivity(i);
        //NO OLVIDAR DAR PERMISO EN EL MANIFEST
        // <uses-permission android:name="android.permission.INTERNET"/>
    }
}