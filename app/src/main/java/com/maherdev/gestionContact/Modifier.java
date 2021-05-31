package com.maherdev.gestionContact;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.support.v7.app.AppCompatActivity;

public class Modifier extends AppCompatActivity implements View.OnClickListener {

    Button btn_val_mod,btn_qte_mod;
    EditText new_name,new_prenom,new_numero;
    long id;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifier);
        btn_val_mod=findViewById(R.id.btm_valider_mod);
        btn_qte_mod=findViewById(R.id.btm_quitter_mod);
        new_name=findViewById(R.id.ed_name_mod);
        new_prenom=findViewById(R.id.ed_prenom_mod);
        new_numero=findViewById(R.id.ed_numero_mod);
        btn_qte_mod.setOnClickListener(this);
        btn_val_mod.setOnClickListener(this);
        id = getIntent().getExtras().getLong("id");
    }

    @Override
    public void onClick(View v) {
        if(v==btn_qte_mod) {
            Modifier.this.finish();
        }
        if (v==btn_val_mod)
        {
            String name=new_name.getText().toString();
            String prenom=new_prenom.getText().toString();
            String numero=new_numero.getText().toString();
            Contact c=new Contact(name,prenom,numero);
            Accueil.data.remove(id);
            Accueil.data.add(c);
            Modifier.this.finish();

        }
    }
}
