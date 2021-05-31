package com.maherdev.gestionContact;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Ajout extends AppCompatActivity implements View.OnClickListener {

    Button btn_val,btn_qte;
    EditText ed_name,ed_prenom,ed_numero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout);
        btn_val=findViewById(R.id.btm_valider_mod);
        btn_qte=findViewById(R.id.btm_quitter_mod);
        ed_name=findViewById(R.id.ed_name_mod);
        ed_prenom=findViewById(R.id.ed_prenom_mod);
        ed_numero=findViewById(R.id.ed_numero_mod);
        btn_qte.setOnClickListener(this);
        btn_val.setOnClickListener(this);
    }
    public void onClick(View v) {
        if(v==btn_qte) {
            Ajout.this.finish();
        }
        if (v==btn_val)
        {
            String name=ed_name.getText().toString();
            String prenom=ed_prenom.getText().toString();
            String numero=ed_numero.getText().toString();
            Contact c=new Contact(name,prenom,numero);
            Accueil.data.add(c);
        }
    }
}