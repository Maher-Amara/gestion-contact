package com.example.gestioncontacts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn_val_auth, btn_qte_auth;  //declaration global des buttons
    EditText ed_nom_auth, ed_mp_auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //methode principal
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // mettre une interface sur l ecran
        btn_qte_auth = findViewById(R.id.btn_qte_auth);
        btn_val_auth = findViewById(R.id.btn_val_auth);
        ed_nom_auth = findViewById(R.id.ed_nom_auth);
        ed_mp_auth = findViewById(R.id.ed_mp_auth);
        btn_val_auth.setOnClickListener(this);
        btn_qte_auth.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

        if (v == btn_qte_auth) {
            MainActivity.this.finish();
            Toast.makeText(this, "ByBy", Toast.LENGTH_SHORT).show();
        }
        if (v == btn_val_auth) {
            // Recuper le text saisir
            String nom = ed_nom_auth.getText().toString();
            String mp = ed_mp_auth.getText().toString();
            if (nom.equalsIgnoreCase("azerty") && mp.equalsIgnoreCase("000000")) {// lancer Accer
                Intent i = new Intent(MainActivity.this, Accueil.class);
                startActivity(i);

            } else {
                //affiche un msg
                Toast.makeText(this, "Nom d'utulisateur ou mot de passe est incorrect", Toast.LENGTH_SHORT).show();
            }
        }
    }
}