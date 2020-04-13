package com.abdiel.itinerariojuntas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.abdiel.itinerariojuntas.Actividades.RegistroActivity;
import com.abdiel.itinerariojuntas.Adaptador.Adaptador;
import com.abdiel.itinerariojuntas.BasedeDatos.daoSala;
import com.abdiel.itinerariojuntas.Entidades.Sala;
import com.getbase.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import javax.microedition.khronos.egl.EGLDisplay;

public class MainActivity extends AppCompatActivity {


    private static final int REQUEST_CALL = 1;
    daoSala db;
    Adaptador adapter;
    ArrayList<Sala> lista;
    Sala s;
    int id =0;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent moveToRegister = new Intent(MainActivity.this, RegistroActivity.class);
                startActivity(moveToRegister);
            }
        });


        db = new daoSala(this);
        lista = db.verReservaciones();
        adapter = new Adaptador(this, lista, db);

        final ListView list = (ListView) findViewById(R.id.lista);
        if (lista != null && lista.size() > 0) {
            list.setAdapter(adapter);
        }


        //click para editar registros
        //para hacer las llamadas
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //mandamos al dialogo para hacer los respectivos cambios

                final Dialog dialog = new Dialog(MainActivity.this);
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.dialogo);
                dialog.show();

                //mandamos a llammar a todos los datos que estan dentro de dialogo

                final EditText area = (EditText)dialog.findViewById(R.id.d_area);
                final EditText fecha = (EditText)dialog.findViewById(R.id.d_fecha);
                final EditText horainicio = (EditText)dialog.findViewById(R.id.d_inicioHora);
                final EditText horafin = (EditText)dialog.findViewById(R.id.d_finHora);
                final EditText tel = (EditText)dialog.findViewById(R.id.d_telefono);
                final EditText nombrereservacion = (EditText)dialog.findViewById(R.id.d_empleado);
                final EditText actividad = (EditText)dialog.findViewById(R.id.d_actividad);

                //traemos los botones

                Button guardar = (Button)dialog.findViewById(R.id.d_guardarBTN);
                Button cancelar = (Button)dialog.findViewById(R.id.d_cancelarBTN);

                s=lista.get(position);
                setId(s.getIdsala());

                //cambiamos datos
                area.setText(s.getArea());
                fecha.setText(s.getFecha());
                horainicio.setText(s.getHorainicio());
                horafin.setText(s.getHorafin());
                tel.setText(s.getTelefono());
                nombrereservacion.setText(s.getEmpleado());
                actividad.setText(s.getActividad());

                guardar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        try {


                        s=new Sala(getId(), area.getText().toString(), fecha.getText().toString(),
                                horainicio.getText().toString(),horafin.getText().toString(),
                                tel.getText().toString(), nombrereservacion.getText().toString(), actividad.getText().toString());

                        db.editarReservacion(s);
                        adapter.notifyDataSetChanged();
                        dialog.dismiss();
                        db.verReservaciones();
                    }catch (Exception e){
                            Toast.makeText(MainActivity.this, "Error al guardar", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                cancelar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });


               // makePhoneCall();
            }
        });

        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                final int which = position;
                s=lista.get(position);
                setId(s.getIdsala());
                new AlertDialog.Builder(MainActivity.this).setIcon(android.R.drawable.ic_delete)
                        .setTitle("Esta seguro de eliminar este registro?")
                        .setMessage("Se eliminara el registro")
                        .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                db.eliminarReservacion(getId());
                                adapter.notifyDataSetChanged();
                                db.verReservaciones();
                            }
                        })
                        .setNegativeButton("NO", null)
                        .show();

                return true;


            }

        });
    }


    /*
    public void makePhoneCall(){



        if(number.trim().length() > 0){
            if (ContextCompat.checkSelfPermission(MainActivity.this,
                    Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
            }else {
                String dial = "Tel: " + number;
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
            }
        }

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CALL) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                makePhoneCall();
            }
        }
    }


     */
}
