package com.example.tartattack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

enum ProviderType {
    BASIC,
    GOOGLE
}

public class Sesion_Usuario_Home extends HomeActivity {

    Button logoutButton;
    ImageView img;
    TextView tvEmail, tvName;
    FirebaseAuth mAuth;
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sesion_usuario_home);

        logoutButton = findViewById(R.id.logoutButt);
        tvEmail = findViewById(R.id.mail);
        tvName = findViewById(R.id.name);
        img = findViewById(R.id.profile);


        mAuth = FirebaseAuth.getInstance();
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this, gso);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);

        if(acct!=null) {  //si es por cuenta de google
            tvEmail.setText(acct.getEmail());
            tvName.setText(acct.getDisplayName());
            setup(acct.getEmail(), "Google");
            String url = String.valueOf(acct.getPhotoUrl());
            Glide.with(this).load(url).into(img);

            Toast.makeText(this, "GOOGLE ACOUNT", Toast.LENGTH_SHORT).show();
        }else{
            //si no es por cuenta de google obtenemos los datos del intent y se lo pasamos al metodo septup()
            Toast.makeText(this, "POR EMAAAIL", Toast.LENGTH_SHORT).show();
            Bundle operador = getIntent().getExtras();
            String email1 = operador.getString("email");
            String provider1 = operador.getString("provider");

            setup(email1, provider1);
            tvName.setText(mAuth.getCurrentUser().getDisplayName());
            tvEmail.setText(email1);

            //Guardado de datos
            SharedPreferences prefS = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = prefS.edit();
            editor.putString("email", email1);
            editor.putString("provider", provider1);
            editor.apply();

        }
    }

    private void setup(String email, String provider) {

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences prefS = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = prefS.edit();
                editor.clear();
                editor.apply();

                signOut();
                mAuth.signOut();
                onBackPressed();
            }
        });

    }


    void signOut(){
        gsc.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(Task<Void> task) {
                startActivity(new Intent(Sesion_Usuario_Home.this, Sesion_Usuario_Aut.class));
                finish();
            }
        });
    }
}