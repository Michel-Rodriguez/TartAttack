package com.example.tartattack;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Sesion_Usuario_Home extends HomeActivity {

    Button logoutButton, saveButton, deleteButton;
    ImageView img;
    TextView tvEmail, tvName;
    EditText direccion, tlfn;
    FirebaseAuth mAuth;
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;

    private FirebaseFirestore dbFire;

    //private ArrayList<Tarta> listaT;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sesion_usuario_home);

        dbFire = FirebaseFirestore.getInstance();
        logoutButton = findViewById(R.id.logoutButt);
        saveButton = findViewById(R.id.saveButton);
        deleteButton = findViewById(R.id.deleteButton);
        tvEmail = findViewById(R.id.mail);
        tvName = findViewById(R.id.name);
        direccion = findViewById(R.id.direccion);
        tlfn = findViewById(R.id.telfn);

        //listaT = new ArrayList<>();
        //Tarta t = new Tarta("Choco", 29.00, R.drawable.tarta_personalizada);
        //listaT.add(t);


        img = findViewById(R.id.profile);


        mAuth = FirebaseAuth.getInstance();
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this, gso);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);



        if(acct!=null) {  //si es por cuenta de google
            tvEmail.setText(acct.getEmail());
            tvName.setText(acct.getDisplayName());
            setup(acct.getEmail());
            String url = String.valueOf(acct.getPhotoUrl());
            Glide.with(this).load(url).into(img);

        }else{
            //si no es por cuenta de google obtenemos los datos del intent y se lo pasamos al metodo septup()
            Bundle operador = getIntent().getExtras();
            String email1 = operador.getString("email");
            String provider1 = operador.getString("provider");
            setup(email1);
            tvName.setText(Objects.requireNonNull(mAuth.getCurrentUser()).getDisplayName());
            tvEmail.setText(email1);

            //Guardado de datos
            SharedPreferences prefS = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = prefS.edit();
            editor.putString("email", email1);
            editor.putString("provider", provider1);
            editor.apply();

        }

        correo = tvEmail.getText().toString();
    }

    private void setup(String email) {

        comprobarDatos(email);

        logoutButton.setOnClickListener(view -> {

            SharedPreferences prefS = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = prefS.edit();
            editor.clear();
            editor.apply();

            signOut();
            mAuth.signOut();
            onBackPressed();

        });

        saveButton.setOnClickListener(view1 -> {

            if (direccion.getText().toString().isEmpty() || tlfn.getText().toString().isEmpty() ){
                Toast.makeText(this, "Rellene dato que falta", Toast.LENGTH_SHORT).show();
            }else{
                addCollection();

            }

        });

        deleteButton.setOnClickListener(view1 -> {

            dbFire.collection("Usuarios").document(email).delete();
            direccion.setText("");
            tlfn.setText("");
        });
    }


    public void signOut(){
        gsc.signOut().addOnCompleteListener(task -> {
            startActivity(new Intent(Sesion_Usuario_Home.this, Sesion_Usuario_Aut.class));
        });
    }



    public void addCollection(){

        Map<String, Object> userData = new HashMap<>();
        //userData.put("listaTartas", listaT);
        userData.put("user", tvEmail.getText());
        userData.put("telefono", tlfn.getText().toString().trim());
        userData.put("direccion", direccion.getText().toString());

        dbFire.collection("Usuarios").document(tvEmail.getText().toString())
                .set(userData)
                .addOnSuccessListener(aVoid -> Toast.makeText(Sesion_Usuario_Home.this, "Datos añadidos exitosamente", Toast.LENGTH_SHORT).show())
                .addOnFailureListener(e -> Toast.makeText(Sesion_Usuario_Home.this, "Error al ingresar", Toast.LENGTH_SHORT).show());

    }

    public void comprobarDatos(String id){
        DocumentReference docRef = dbFire.collection("Usuarios").document("id");
        docRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DocumentSnapshot document = task.getResult();
                if (document.exists()) {
                    Log.d("get", "DocumentSnapshot data: " + document.getData());
                    direccion.setText(document.getString("direccion"));
                } else {
                    Log.d("no get", "No such document");
                }
            } else {
                Log.d("FAIL", "get failed with ", task.getException());
            }
        });

    }



}