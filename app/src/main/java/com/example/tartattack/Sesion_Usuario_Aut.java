package com.example.tartattack;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import java.util.ArrayList;

public class Sesion_Usuario_Aut extends HomeActivity {

    Button butRegistrar, butLogin, butGoogle;
    EditText etEmail, etPassw;
    FirebaseAuth mAuth;
    GoogleSignInOptions gso;  //Options de Inicio de sesion
    GoogleSignInClient gsc;  //Cliente de Google

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sesion_usuario_aut);

        butGoogle = findViewById(R.id.buttGoogle);
        butLogin = findViewById(R.id.butIniciar);
        butRegistrar = findViewById(R.id.buttRegistrar);
        etEmail = findViewById(R.id.textEmail);
        etPassw = findViewById(R.id.textPassw);
        mAuth = FirebaseAuth.getInstance();

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        gsc = GoogleSignIn.getClient(this,gso);
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);

        if(acct!=null){
            Toast.makeText(this, "ACCEDIENDO POR GOOGLE USER", Toast.LENGTH_SHORT).show();
            cambioH();
        }

        butGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Sesion_Usuario_Aut.this, "ON CLIIIICCCCCK", Toast.LENGTH_SHORT).show();
                resultLauncher.launch(new Intent(gsc.getSignInIntent()));
            }
        });

        setup();
        sesion();
    }

    private void sesion() {
        //Metodo para mantener la sesion iniciada
        SharedPreferences prefS = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE);
        String email = prefS.getString("email", null);
        String provider = prefS.getString("provider", null);

        if (email != null && provider != null) {

            showHome(email,ProviderType.valueOf(provider));
        }
    }

    private void setup() {
        butGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultLauncher.launch(new Intent(gsc.getSignInIntent()));
            }
        });

        butRegistrar.setOnClickListener(view -> {
            if (!etEmail.getText().toString().isEmpty() && !etPassw.getText().toString().isEmpty()) {
                mAuth.createUserWithEmailAndPassword(  //Metodo parar registrar
                                etEmail.getText().toString(),
                                etPassw.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(Sesion_Usuario_Aut.this, "Acceso exitoso", Toast.LENGTH_SHORT).show();
                                    showHome(task.getResult().getUser().getEmail(), ProviderType.BASIC); //@toString eliminad
                                } else {  //App va aqui
                                    Toast.makeText(Sesion_Usuario_Aut.this, "Error en el registro", Toast.LENGTH_LONG).show();
                                }
                            }

                        });
            } else {
                Toast.makeText(Sesion_Usuario_Aut.this, "Error en el registro", Toast.LENGTH_LONG).show();
            }
        });

        butLogin.setOnClickListener(view -> {
            if (!etEmail.getText().toString().isEmpty() && !etPassw.getText().toString().isEmpty()) {
                mAuth.signInWithEmailAndPassword(    //Metodo para iniciar sesion
                                etEmail.getText().toString(),
                                etPassw.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    showHome(task.getResult().getUser().getEmail(), ProviderType.BASIC);
                                    Toast.makeText(Sesion_Usuario_Aut.this, "Acceso exitoso", Toast.LENGTH_LONG).show();
                                } else {  //App va aqui
                                    Toast.makeText(Sesion_Usuario_Aut.this, "Error al logar", Toast.LENGTH_LONG).show();
                                    //cambioH();
                                }
                            }
                        });
            } else {
                Toast.makeText(Sesion_Usuario_Aut.this, "Error en el registro", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void showHome(String email, ProviderType provider) {

        Intent intentHome = new Intent(this, Sesion_Usuario_Home.class);
        intentHome.putExtra("email", email);
        intentHome.putExtra("provider", provider.name());
        startActivity(intentHome);
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //if(currentUser != null)
        updateUI(currentUser);
    }

    private void updateUI(FirebaseUser user) {
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if(currentUser != null) {
            Intent intent = new Intent(Sesion_Usuario_Aut.this, Sesion_Usuario_Home.class);
            startActivity(intent);
            finish();
        }
    }

    private void cambioH() {
        //finish();
        Intent intentHome = new Intent(this, Sesion_Usuario_Home.class);
        startActivity(intentHome);
    }

    ActivityResultLauncher<Intent> resultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK){
                        Intent intent = result.getData();
                        Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(intent);

                        try {
                            Log.i("PASA EL TRY", "PASA EL TRY");
                            GoogleSignInAccount account = task.getResult(ApiException.class);
                            firebaseAuthWithGoogle(account.getIdToken());
                            updateUI(mAuth.getCurrentUser());

                        } catch (ApiException e) {
                            Log.w("ERROR", "Error con google");
                            Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
    );


    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("EXITOSO", "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("FALLOOOO", "signInWithCredential:failure", task.getException());
                            updateUI(null);
                        }
                    }
                });
    }

}