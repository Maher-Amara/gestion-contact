package com.example.gestioncontacts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ContactAdapter extends BaseAdapter {
    Context con;
    ArrayList<Contact>data;
    ContactAdapter(Context con, ArrayList<Contact>data)
    {
        this.con=con;
        this.data=data;
    }


    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //cree un view
        LayoutInflater inf=LayoutInflater.from(con);
        View v=inf.inflate(R.layout.view_contact,null);
        // Recuperer des cpts/ Holders
        TextView tnom = v.findViewById(R.id.tv_nom_contact);
        TextView tprenom = v.findViewById(R.id.tv_prenom_contact);
        TextView tnum = v.findViewById(R.id.tv_numero_contact);

        Contact c=data.get(position);
        tnom.setText(c.nom);
        tprenom.setText(c.prenom);
        tnum.setText(c.numero);


        return v;
    }
}
