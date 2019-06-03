package com.example.barbosa.tpdm_unidad5_sms;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView  opciones;
    private static final int MY_PERMISSIONS_REQUEST_RECIEVER_SMS = 0;
    private static final int PERMISSION_REQUEST_CODE = 1;
    BaseDatos dmbs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dmbs = new BaseDatos(this, "PRACTICA1", null, 1);

        opciones = findViewById(R.id.mensaje);
        opciones.setText("1. SUERTE signo\n2. AMOR signo\n3. RESULTADOS equipo\n4. CHISME");


        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {

            if (checkSelfPermission(Manifest.permission.SEND_SMS)
                    == PackageManager.PERMISSION_DENIED) {

                Log.d("permission", "permission denied to SEND_SMS - requesting it");
                String[] permissions = {Manifest.permission.SEND_SMS};

                requestPermissions(permissions, PERMISSION_REQUEST_CODE);

            }
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.RECEIVE_SMS)) {

            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECEIVE_SMS}, MY_PERMISSIONS_REQUEST_RECIEVER_SMS);
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, MY_PERMISSIONS_REQUEST_RECIEVER_SMS);
            }
        }// if 1

        // insercion();
    }// onCreate


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {

            case MY_PERMISSIONS_REQUEST_RECIEVER_SMS: {

                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Permiso concedido", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "SIN PERMISO", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    private void insercion() {
        String var = "ARIES";
        try {
             SQLiteDatabase inser=dmbs.getWritableDatabase();
            //SQLiteDatabase inser = dmbs.getWritableDatabase();
            String SQL = "INSERT INTO SIGNOS VALUES(NULL, 'Los Acuario son tímidos y callados, pero al mismo tiempo, pueden ser excéntricos y enérgicos. Sin embargo, en ambos casos, son pensadores y muy intelectuales, y aman ayudar a otros. Son capaces de ver las dos caras de una situación sin prejuicios, lo cual les permite resolver problemas.')";

            inser.execSQL(SQL);

            inser.close();

            System.out.println("SE PUDO INSERTAR! "+var);

        } catch (SQLException e) {
            System.out.println("ERROR! " + e.getMessage());
        }
    }// insercion
    }

