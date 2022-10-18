package com.example.app_test;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.app_test.configuracion.Personas;
import com.example.app_test.configuracion.SQLiteConexion;
import com.example.app_test.tablas.Transacciones;

import java.util.ArrayList;

public class ActivityCombo2 extends AppCompatActivity {

    /*Variables Globales*/
    SQLiteConexion conexion;
    Spinner sppersonas;
    EditText nombres, apellidos, correo;

    ArrayList<Personas> lista;
    ArrayList<String> listaString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combo2);

        conexion = new SQLiteConexion(this, Transacciones.NameDatabase, null, 1);

        sppersonas = (Spinner) findViewById(R.id.sppersonas2);
        nombres = (EditText) findViewById(R.id.txtnombres_aco);
        apellidos = (EditText) findViewById(R.id.txtapellidos_aco);
        correo = (EditText) findViewById(R.id.txtcorreo_aco);

        ObtenerPersonas();

        ArrayAdapter<CharSequence> adp =  new ArrayAdapter(this, android.R.layout.simple_spinner_item, listaString);
        sppersonas.setAdapter(adp);

        sppersonas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    nombres.setText(lista.get(i).getNombres());
                    apellidos.setText(lista.get(i).getApellidos());
                    correo.setText(lista.get(i).getCorreo());

                } catch (Exception ex) {

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //
            }
        });

    }

    private void ObtenerPersonas() {
        SQLiteDatabase db = conexion.getReadableDatabase();
        Personas listapersonas = null;
        lista = new ArrayList<Personas>();

        Cursor cursor = db.rawQuery("SELECT * FROM "+ Transacciones.TbPersonas, null);

        while (cursor.moveToNext()) {
            listapersonas = new Personas();
            listapersonas.setId(cursor.getInt(0));
            listapersonas.setNombres(cursor.getString(1));
            listapersonas.setApellidos(cursor.getString(2));
            listapersonas.setEdad(cursor.getInt(3));
            listapersonas.setCorreo(cursor.getString(4));

            lista.add(listapersonas);
        }

        cursor.close();

        fillcombo();

    }

    private void fillcombo() {
        listaString = new ArrayList<String>();

        for(int i=0; i<lista.size(); i++) {
            listaString.add(lista.get(i).getNombres() + " | " +
                    lista.get(i).getApellidos() + " | " +
                    lista.get(i).getCorreo() + " | ");
        }

    }

}