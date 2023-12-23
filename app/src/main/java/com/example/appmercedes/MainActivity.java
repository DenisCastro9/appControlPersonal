package com.example.appmercedes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private final String base = "base1";
    private EditText etnombre, etdni, etcuil, etmail, etcargo, etanti,ethoras, etcampo, etsitu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etnombre = findViewById(R.id.etnombre);
        etdni = findViewById(R.id.etdni);
        etcuil = findViewById(R.id.etcuil);
        etmail = findViewById(R.id.etmail);
        etcargo = findViewById(R.id.etcargo);
        etanti = findViewById(R.id.etanti);
        ethoras = findViewById(R.id.ethoras);
        etcampo = findViewById(R.id.etcampo);
        etsitu = findViewById(R.id.etsitu);
    }


    public void cargar (View v)
    {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,base,null,1);
        SQLiteDatabase db = admin.getWritableDatabase();
        ContentValues registro = new ContentValues();
        registro.put("nombre", etnombre.getText().toString());
        registro.put("dni", etdni.getText().toString());
        registro.put("cuil", etcuil.getText().toString());
        registro.put("mail",etmail.getText().toString());
        registro.put("cargo",etcargo.getText().toString());
        registro.put("antiguedad",etanti.getText().toString());
        registro.put("horas",ethoras.getText().toString());
        registro.put("campo",etcampo.getText().toString());
        registro.put("situacion",etsitu.getText().toString());
        db.insert("docentes",null,registro);
        db.close();
        admin.close();
        etnombre.setText("");
        etdni.setText("");
        etcuil.setText("");
        etmail.setText("");
        etcargo.setText("");
        etanti.setText("");
        ethoras.setText("");
        etcampo.setText("");
        etsitu.setText("");
    }


    public void consultar (View v)
    {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,base,null,1);
        SQLiteDatabase db = admin.getReadableDatabase();
        Cursor registro = db.rawQuery("select nombre, cuil, mail, cargo, antiguedad, horas, campo, situacion from docentes where dni=" + etdni.getText().toString(),null);
        if(registro.moveToFirst())
        {
            etnombre.setText(registro.getString(0));
            etcuil.setText(registro.getString(1));
            etmail.setText(registro.getString(2));
            etcargo.setText(registro.getString(3));
            etanti.setText(registro.getString(4));
            ethoras.setText(registro.getString(5));
            etcampo.setText(registro.getString(6));
            etsitu.setText(registro.getString(7));
        }
        else
        {
            Toast.makeText(this, "No existe la persona con el DNI: " + etdni.getText().toString(), Toast.LENGTH_SHORT).show();
            etnombre.setText("");
            etcuil.setText("");
            etmail.setText("");
            etcargo.setText("");
            etanti.setText("");
            ethoras.setText("");
            etcampo.setText("");
            etsitu.setText("");
        }
        db.close();
        admin.close();
    }


    public void modificar (View v)
    {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,base,null,1);
        SQLiteDatabase db = admin.getWritableDatabase();
        ContentValues registro = new ContentValues();
        registro.put("nombre", etnombre.getText().toString());
        registro.put("cuil", etcuil.getText().toString());
        registro.put("mail",etmail.getText().toString());
        registro.put("cargo",etcargo.getText().toString());
        registro.put("antiguedad",etanti.getText().toString());
        registro.put("horas",ethoras.getText().toString());
        registro.put("campo",etcargo.getText().toString());
        registro.put("situacion",etsitu.getText().toString());
        int cant = db.update("docentes", registro,"dni=" + etdni.getText().toString(),null);
        if(cant == 1)
        {
            Toast.makeText(this, "El/Los datos fueron modificados", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, "No existe la persona con el DNI: " + etdni.getText().toString(), Toast.LENGTH_SHORT).show();
        }
        db.close();
        admin.close();
    }


    public void borrar (View v)
    {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,base,null,1);
        SQLiteDatabase db = admin.getWritableDatabase();
        int cant = db.delete("docentes","dni=" + etdni.getText().toString(),null);
        if(cant == 1)
        {
            Toast.makeText(this, "Se elimino la persona con el DNI: " + etdni.getText().toString(), Toast.LENGTH_SHORT).show();
            etnombre.setText("");
            etcuil.setText("");
            etmail.setText("");
            etcargo.setText("");
            etanti.setText("");
            ethoras.setText("");
            etcampo.setText("");
            etsitu.setText("");
        }
        else
        {
            Toast.makeText(this, "No existe la persona con el DNI: " + etdni.getText().toString(), Toast.LENGTH_SHORT).show();
        }
    }

    public void info (View v)
    {
        Intent intento = new Intent(this,informacion.class);
        startActivity(intento);
    }


    public void volverCosultar (View v)
    {
        etnombre.setText("");
        etdni.setText("");
        etcuil.setText("");
        etmail.setText("");
        etcargo.setText("");
        etanti.setText("");
        ethoras.setText("");
        etcampo.setText("");
        etsitu.setText("");
    }
}