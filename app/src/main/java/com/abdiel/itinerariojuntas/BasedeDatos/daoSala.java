package com.abdiel.itinerariojuntas.BasedeDatos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.abdiel.itinerariojuntas.Entidades.Sala;

import java.util.ArrayList;

public class daoSala extends SQLiteOpenHelper {

    SQLiteDatabase db = this.getReadableDatabase();
    ArrayList<Sala> lista = new ArrayList<Sala>();
    Sala s;
    Context ct;

    public static final String DATABASE_NAME="BDSala.db";
    public static final String TABLE_NAME="itinerario";
    public static final String COL_1="idsala";
    public static final String COL_2="area";
    public static final String COL_3="fecha";
    public static final String COL_4="horainicio";
    public static final String COL_5="horafin";
    public static final String COL_6="telefono";
    public static final String COL_7="empleado";
    public static final String COL_8="actividad";

    public daoSala(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+TABLE_NAME+" (idsala integer primary key autoincrement, area TEXT, fecha TEXT, horainicio TEXT, horafin TEXT, telefono TEXT, empleado TEXT, actividad TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);

    }

    //agregar reservacion
    public long agregarReservacion(String area, String fecha, String horainicio, String horafin, String telefono, String empleado, String actividad){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("area",area);
        contentValues.put("fecha",fecha);
        contentValues.put("horainicio",horainicio);
        contentValues.put("horafin",horafin);
        contentValues.put("telefono",telefono);
        contentValues.put("empleado",empleado);
        contentValues.put("actividad",actividad);

        long res = db.insert(TABLE_NAME, null,contentValues);

        db.close();
        return res;

    }//agregar reservacion

    //creamos un metodo para verificar si la sala esta ocupada en el horario pedido

    public boolean checkhorariofecha(String horainicio, String fecha){
        String[] columns = new String[] {COL_1};
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = COL_3 + "=?" + " and " + COL_4 + "=?";
        String[] seleccionArgs = {horainicio, fecha};
        Cursor cursor = db.query(TABLE_NAME, columns, selection,seleccionArgs, null, null, null);

        int count = cursor.getCount();
        cursor.close();

        if(count==0){
            return true;

        }else {return false;}

    } //check de fecha  horario

    //listamos todas las reservaciones

    public ArrayList<Sala> verReservaciones(){
        SQLiteDatabase db = this.getReadableDatabase();
        lista.clear();
        Cursor cursor = db.rawQuery("SELECT * FROM "+ TABLE_NAME, null);
        if(cursor != null && cursor.getCount()>0){
            cursor.moveToNext();
            do{
                lista.add(new Sala(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6),
                        cursor.getString(7)
                ));
            }while (cursor.moveToNext());
        }
        return lista;
    }//listado de reservaciones

    //eliminar una reservacion
    public boolean eliminarReservacion(int idsala){
        SQLiteDatabase db = this.getReadableDatabase();
        return (db.delete(TABLE_NAME, "idsala="+idsala, null))>0;
    }//eliminar reservacion

    //editar registro de sala

    public boolean editarReservacion(Sala s){

        ContentValues contentValues = new ContentValues();


        contentValues.put("area",s.getArea());
        contentValues.put("fecha",s.getFecha());
        contentValues.put("horainicio",s.getHorainicio());
        contentValues.put("horafin",s.getHorafin());
        contentValues.put("telefono",s.getTelefono());
        contentValues.put("empleado",s.getEmpleado());
        contentValues.put("actividad",s.getActividad());


        return  (db.update(TABLE_NAME, contentValues, "idsala="+s.getIdsala(), null))>0;
    }//editar reservacion

}
