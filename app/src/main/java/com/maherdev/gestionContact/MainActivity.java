package com.maherdev.gestionContact;

import android.support.v7.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn_val,btn_qte;
    EditText ed_name,ed_mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //mettre une interface xml sur l'ecrant
        btn_qte=findViewById(R.id.btm_quitter_auth);
        btn_val=findViewById(R.id.btm_valider_auth);
        ed_name=findViewById(R.id.ed_name_auth);
        ed_mp=findViewById(R.id.ed_pass_auth);
        btn_qte.setOnClickListener(this);
        btn_val.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v==btn_qte) {
            MainActivity.this.finish();
        }
         if (v==btn_val)
         {
             String name=ed_name.getText().toString();
             String mp=ed_mp.getText().toString();
             if (name.equalsIgnoreCase("test") && mp.equals("000"))
             {
                 Intent i= new Intent(this,Accueil.class);
                 startActivity(i);
             }
             else
             {
                 Toast.makeText(this, "wrong password", Toast.LENGTH_SHORT).show();
             }
         }
    }
}