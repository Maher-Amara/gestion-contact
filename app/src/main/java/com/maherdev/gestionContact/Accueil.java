package com.maherdev.gestionContact;


import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Accueil extends AppCompatActivity implements View.OnClickListener{
    static ArrayList<Contact> data= new ArrayList<Contact>();
    Button btn_ajt,btn_aff;

    boolean write_permission=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);

        // gestion des autorisation
        // tester si la permission est deja accordee
        if(ContextCompat.checkSelfPermission(Accueil.this,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED)
        {
            //permission deja accordee
            write_permission=true;
        }
        else{
            write_permission=false;
            // demende permission
            ActivityCompat.requestPermissions(Accueil.this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
            // affiche une boite de dialog(allow or denied)
            // ==> pour gerer le resultat redefinir onrequestpermissionresultat
        }
        if(write_permission==true)
            importer();

        btn_ajt=findViewById(R.id.btm_ajout_acc);
        btn_aff=findViewById(R.id.btm_affiche_acc);
        btn_ajt.setOnClickListener(this);
        btn_aff.setOnClickListener(this);
    }
    public void onClick(View v) {
        if(v==btn_aff) {
            Intent i= new Intent(this,Affiche.class);
            startActivity(i);
        }
        if (v==btn_ajt)
        {
            Intent i= new Intent(this,Ajout.class);
            startActivity(i);

        }
    }

    public void sauvgarder()
    {
        // ecritue dans un fichier
        String root= Environment.getExternalStorageDirectory().getPath();
        File f=new File(root,"mescontact.csv");

        try {
            FileWriter fw=new FileWriter(f,false);//creation du ficher s'il n existe pas
            BufferedWriter bw =new BufferedWriter(fw);//memoire tampon permet d'ecrire
            for (int i=0;i<data.size();i++)
            {
                bw.write(data.get(i).nom+","+data.get(i).prenom+","+data.get(i).numero+"\n");
            }

            bw.close();
            fw.close();//sauvegarde

        } catch (IOException e) {
            Log.e("msg","erreur ecriture "+e.getMessage());
        }
    }
    public void importer()
    {
        //lecture depuis un ficher
        String root= Environment.getExternalStorageDirectory().getPath();
        File f=new File(root,"mescontact.csv");
        if(f.exists())
        {
            try {
                FileReader fr=new FileReader(f);
                BufferedReader br=new BufferedReader(fr);
                String ligne=null;
                while ((ligne=br.readLine())!=null){
                    String[] t=ligne.split(",");
                    Contact c=new Contact(t[0],t[1],t[2]);
                    data.add(c);
                }
                br.close();
                fr.close();
            } catch (IOException e) {
                Log.e("msg","erreur lecture "+e.getMessage());
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this,"accueil started",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        if(write_permission)
            sauvgarder();
        Toast.makeText(this,"accueil stoped",Toast.LENGTH_SHORT).show();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==1)
        {
            if(grantResults.length>0)
            {
                if(grantResults[0]==PackageManager.PERMISSION_GRANTED)
                {
                    write_permission=true;
                }
                else
                {
                    write_permission=false;
                }
            }
        }
    }
}