package com.abdiel.itinerariojuntas.Actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.abdiel.itinerariojuntas.BasedeDatos.daoSala;
import com.abdiel.itinerariojuntas.MainActivity;
import com.abdiel.itinerariojuntas.R;

public class RegistroActivity extends AppCompatActivity {

    daoSala db;
    EditText area;
    EditText fecha;
    EditText iniciohora;
    EditText finhora;
    EditText telefono;
    EditText empleado;
    EditText actividad;
    Button agregar;
    Button cancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        db = new daoSala(this);

        //enlazamos los componentes
        area = (EditText)findViewById(R.id.area);
        fecha = (EditText)findViewById(R.id.fecha);
        iniciohora =(EditText)findViewById(R.id.inicioHora);
        finhora = (EditText)findViewById(R.id.finHora);
        telefono = (EditText)findViewById(R.id.telefono);
        empleado =(EditText)findViewById(R.id.empleado);
        actividad = (EditText)findViewById(R.id.actividad);

        //rescatamos los botones
        agregar = (Button)findViewById(R.id.agregarBTN);
        cancelar = (Button)findViewById(R.id.cancelarBTN);


        //boton cancelar
        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent regresarmenu = new Intent(RegistroActivity.this, MainActivity.class);
                startActivity(regresarmenu);
            }
        });//boton cancelar

        //boton agregar reservacion

        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(area.getText().toString().length() != 0 && fecha.getText().toString().length() != 0
                && iniciohora.getText().toString().length() != 0 && finhora.getText().toString().length() != 0
                && telefono.getText().toString().length() != 0 && empleado.getText().toString().length() != 0
                && actividad.getText().toString().length() != 0){

                String horaInicio1 = iniciohora.getText().toString().trim();
                String fecha1 = fecha.getText().toString().trim();

                Boolean res = db.checkhorariofecha(horaInicio1,fecha1);

                if(res == true){
                    //insertamos
                    String area1 = area.getText().toString().trim();
                    String fecha2 = fecha.getText().toString().trim();
                    String horaInicio2 = iniciohora.getText().toString().trim();
                    String finhora1 = finhora.getText().toString().trim();
                    String telefono1 = telefono.getText().toString().trim();
                    String empleado1 = empleado.getText().toString().trim();
                    String actividad1 = actividad.getText().toString().trim();

                    long val = db.agregarReservacion(area1,fecha2, horaInicio2, finhora1, telefono1, empleado1, actividad1);

                    if(val > 0 ){
                        Toast.makeText(RegistroActivity.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                        Intent moveToRegister = new Intent(RegistroActivity.this, MainActivity.class);
                        startActivity(moveToRegister);
                    }else {
                        Toast.makeText(RegistroActivity.this, "Error al registrar", Toast.LENGTH_SHORT).show();
                    }


                }else {
                    Toast.makeText(RegistroActivity.this, "La fecha y la hora ya se encuentran ocupadas", Toast.LENGTH_SHORT).show();
                }


                }else{
                    Toast.makeText(RegistroActivity.this, "Debe llenar todos los campos", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}
