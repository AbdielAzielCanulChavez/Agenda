package com.abdiel.itinerariojuntas.Adaptador;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.abdiel.itinerariojuntas.BasedeDatos.daoSala;
import com.abdiel.itinerariojuntas.Entidades.Sala;
import com.abdiel.itinerariojuntas.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Adaptador extends BaseAdapter {

    ArrayList<Sala> lista;
    daoSala db;
    Sala s;
    Activity a;
    int id= 0;


    public Adaptador(Activity a, ArrayList<Sala> lista, daoSala db){
        this.lista = lista;
        this.a=a;
        this.db = db;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Sala getItem(int i) {

        s=lista.get(i);
        return null;
    }

    @Override
    public long getItemId(int i) {
        s=lista.get(i);
        return s.getIdsala();
    }

    //visualizamos el contenido

    @Override
    public View getView(int posision, View view, ViewGroup viewGroup) {

        View v = view;
        if (v == null) {

            LayoutInflater li = (LayoutInflater) a.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = li.inflate(R.layout.item_lista_reservaciones, null);
        }

        s=lista.get(posision);

        TextView area = (TextView)v.findViewById(R.id.t_area);
        TextView fecha = (TextView)v.findViewById(R.id.t_fecha);
        TextView horainicio = (TextView)v.findViewById(R.id.t_horainicio);
        TextView horafin = (TextView)v.findViewById(R.id.t_horafin);
        TextView nombrereservo = (TextView)v.findViewById(R.id.t_empleadoReserva);
        TextView actividadrealizar = (TextView)v.findViewById(R.id.t_actividadRealizar);
        ImageView telefono = (ImageView)v.findViewById(R.id.t_telefono);

        area.setText(s.getArea());
        fecha.setText(s.getFecha());
        horainicio.setText(s.getHorainicio());
        horafin.setText(s.getHorafin());
        nombrereservo.setText(s.getEmpleado());
        actividadrealizar.setText(s.getActividad());


        db.verReservaciones();
        notifyDataSetChanged();
        return v;


    }
}
