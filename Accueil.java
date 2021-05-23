package com.example.gestioncontacts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.inspector.StaticInspectionCompanionProvider;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Accueil extends AppCompatActivity implements View.OnClickListener {
Button btn_ajt_acc,btn_aff_acc;
TextView tv_user_acc;
static ArrayList<Contact> data=new
            ArrayList<Contact>();
boolean write_permission=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);

        //gestion des permission
        //tester si la permission est deja accordée

        if(ContextCompat.checkSelfPermission(Accueil.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED)
        {
            write_permission=true;
        }
        else {
            write_permission=false;
            ActivityCompat.requestPermissions(Accueil.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1); //affiche un boite de dialogue (allow or denied)
            // ===> pour gerer le resultat , redefinir la methode onrequestpermissionresult
        }

        if (write_permission)
            importer();

        btn_aff_acc=findViewById(R.id.btn_aff_acc);
        btn_ajt_acc=findViewById(R.id.btn_ajt_acc);
        tv_user_acc=findViewById(R.id.tv_user_acc);
        btn_ajt_acc.setOnClickListener(this);
        btn_aff_acc.setOnClickListener(this);


    }

    public void sauvgarder()
    {
        //ecrire dans un fichier
        String root= Environment.getExternalStorageDirectory().getPath();
        File f=new File(root, "mescontacts.csv");
         // ouverture du fichier en mode ecriture
        try {
            FileWriter fw=new FileWriter(f,false); // cree le fichier s'il nexiste pas
            BufferedWriter bw=new BufferedWriter(fw);

            for (int i=0; i<data.size();i++)
            bw.write(data.get(i).nom+","+data.get(i).prenom+","+data.get(i).numero+"\n");
            bw.close();
            fw.close();//sauvegarde


        } catch (IOException e) {
            Log.e("msg","erreur sauvegarde"+e.getMessage());
        }

    }


    public void importer()
    {
        //lire depuis un fichier
        String root= Environment.getExternalStorageDirectory().getPath();
        File f=new File(root, "mescontacts.csv");
        // ouverture du fichier en mode ecriture
        try {
            FileReader fr=new FileReader(f);
            BufferedReader br=new BufferedReader(fr);
            String ligne=null;
            while( (ligne=br.readLine()) !=null)
            {
                //nom,prenom,12345
                String[] t = ligne.split(",");
                Contact c = new Contact(t[0], t[1], t[2]);
                data.add(c);

            }
            br.close();
            fr.close();//sauvegarde


        } catch (IOException e) {
            Log.e("msg","erreur sauvegarde"+e.getMessage());
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "Wellcom", Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onStop() {

        if(write_permission)
            sauvgarder();

        super.onStop();
    }

    @Override
    protected void onDestroy() {


        super.onDestroy();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //gerer ici la reponse utilisateur
        if (requestCode==1)
        {
            if(grantResults.length>0)
            {
                if(grantResults[0]==PackageManager.PERMISSION_GRANTED)
                {
                    write_permission=true;
                }
                else {
                    write_permission=false;
                }
            }
        }


    }

    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_ajt_acc:
                Toast.makeText(this,"Vous étes dans la page Ajout", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(Accueil.this, Ajout.class);
                startActivity(i);
                break;
            case R.id.btn_aff_acc:
                Toast.makeText(this,"Vous étes dans la page affichage", Toast.LENGTH_SHORT).show();
                Intent j = new Intent(Accueil.this, Edition.class);
                startActivity(j);
                break;
        }
    }
}