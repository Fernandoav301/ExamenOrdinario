package com.example.examenordinario;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ventas extends AppCompatActivity {
    TextView contenidob;
    Button leerb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventas);
        contenidob = (TextView) findViewById(R.id.contenidob);
        leerb = (Button) findViewById(R.id.leerb);


        leerb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LeerRegistro();
            }
        });

    }
    private void LeerRegistro() {
        AdminSQLITEHelper admin = new AdminSQLITEHelper(this, "OrdinarioBD", null, 1);
        SQLiteDatabase basedatos = admin.getReadableDatabase();
        try{
            Cursor cursor = basedatos.rawQuery("SELECT * FROM productos", null);
            String cont="";
            while (cursor.moveToNext()){
                cont+="Nombre :"+cursor.getString(1)+" Precio:"+cursor.getString(2)+"Cantidad:"+cursor.getString(3)+"Imagen:"+cursor.getString(4)+"\n";
            }
            cursor.close();
            contenidob.setText(cont);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Error"+e.getMessage(), Toast.LENGTH_SHORT).show();

        }
    }
}