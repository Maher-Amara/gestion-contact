package com.example.gestioncontacts;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Edition extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ListView lv_aff_edi;
    EditText ed_rech_edi;
    ArrayAdapter<String> arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Edition.this.setContentView(R.layout.activity_edition);
        ed_rech_edi=findViewById(R.id.ed_rech_edi);
        lv_aff_edi=findViewById(R.id.lv_aff_edi);
        //ArrayAdapter ad=new ArrayAdapter(Edition.this, android.R.layout.simple_expandable_list_item_1,Accueil.data);
        //lv_aff_edi.setAdapter(ad);

        //affichage des données
        ContactAdapter ad=new ContactAdapter(Edition.this, Accueil.data);
        lv_aff_edi.setAdapter(ad);
        lv_aff_edi.setOnItemClickListener(this);




    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
// Creation d'une instance builder d'une alert dialog
        AlertDialog.Builder alert=new AlertDialog.Builder(Edition.this );
        alert.setTitle("Action!");// titre
        alert.setMessage("Choisir une action:"); // message
        alert.setPositiveButton("supprimer", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Accueil.data.remove(position); //supprimer de lelement
                lv_aff_edi.invalidateViews(); //raffraichissement
            }
        });



        /* Ajout d'un bouton avec action utilisant une classe anonyme */
        alert.setPositiveButton("supprimer", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Accueil.data.remove(position); //suppression de lelement
                        lv_aff_edi.invalidateViews(); //raffraichissement
                    }
                });


        alert.setNegativeButton("modifier", new DialogInterface.OnClickListener() {
            @Override public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "bouton annulation", Toast.LENGTH_SHORT).show(); } });


        alert.setNeutralButton("supprimer tous ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Accueil.data.clear();
                lv_aff_edi.invalidate();
            }
        });

    }
}