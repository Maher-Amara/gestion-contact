package com.example.gestioncontacts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class Ajout extends AppCompatActivity implements View.OnClickListener {
    Button btn_qte_ajt, btn_val_ajt;
    EditText ed_nom_ajt, ed_pre_ajt, num_num_ajt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout);
        btn_qte_ajt = findViewById(R.id.btn_qte_ajt);
        btn_val_ajt = findViewById(R.id.btn_val_ajt);
        ed_nom_ajt = findViewById(R.id.ed_nom_ajt);
        ed_pre_ajt = findViewById(R.id.ed_pre_ajt);
        num_num_ajt = findViewById(R.id.num_num_ajt);

        btn_qte_ajt.setOnClickListener(this);
        btn_val_ajt.setOnClickListener(this);

    }



    @Override
    public void onClick(View v) {

        if (v == btn_qte_ajt) {
            Ajout.this.finish();
            Toast.makeText(this,"Quitter l'Ajout sans valider", Toast.LENGTH_SHORT).show();


        }
        if (v == btn_val_ajt) {
            // Recuper le text saisir
            String nom = ed_nom_ajt.getText().toString();
            String prenom = ed_pre_ajt.getText().toString();
            String num = num_num_ajt.getText().toString();
            Accueil.data.add(new Contact(nom,prenom,num));
            Toast.makeText(this,"contact Ajouter", Toast.LENGTH_SHORT).show();
            Ajout.this.finish();


        }
    }


}