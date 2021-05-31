package com.maherdev.gestionContact;
import android.support.v7.app.AppCompatActivity;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class Affiche extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {
    EditText ed_recherche;
    ListView lv;
       @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affiche);
        ed_recherche=findViewById(R.id.ed_recherche_aff);
        lv =findViewById(R.id.list_aff);
        ContactAdapter ad=new ContactAdapter(Affiche.this,Accueil.data);
        lv.setAdapter(ad);
        lv.setOnItemClickListener(this);
        lv.setOnItemLongClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, final int position, final long id) {
        AlertDialog.Builder alert = new AlertDialog.Builder(Affiche.this);
        alert.setTitle("Attention!!");
        alert.setMessage("Choisir une action:");
        alert.setPositiveButton("supprimer", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Accueil.data.remove(position);//supprimer
                lv.invalidateViews();//raffrechissement
                Toast.makeText(getApplicationContext(),"bouton confirmation", Toast.LENGTH_SHORT).show();
            }
        });
        alert.setNeutralButton("Modifie", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent i =new Intent(Affiche.this , Modifier.class);
                i.putExtra("id",id);
                startActivity(i);
            }
        }).setNegativeButton("Supprimer tous", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Accueil.data.clear();//supprimer tout
                lv.invalidateViews();//raffrechissement
            }
        });

        AlertDialog dialog=alert.create();
        dialog.show();
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        Contact c=Accueil.data.get(position);
        //Action dial
        //Action call( do this)
           return false;
    }
}