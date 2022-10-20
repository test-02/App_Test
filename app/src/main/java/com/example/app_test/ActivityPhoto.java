package com.example.app_test;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class ActivityPhoto extends AppCompatActivity {

    /*Declaracion de Variables*/
    static final int peticion_captura_imagen = 100;
    static final int peticion_acceso_cam = 201;

    ImageView ObjetoImagen;
    Button btntakephoto;
    String PathImagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        ObjetoImagen = (ImageView) findViewById(R.id.imageView);
        btntakephoto = (Button) findViewById(R.id.btntakephoto);

        btntakephoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                permisos();
            }
        });
    }

    private void permisos() {
        // Validar si el permiso esta otorgado o no para tomar fotos
        if(ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            // Otorgar el permiso si no se tiene el mismo
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, peticion_acceso_cam);

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == peticion_acceso_cam) {
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                tomarfoto();
            }
        }

    }

    private void tomarfoto() {
        Intent intentfoto = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if(intentfoto.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intentfoto, peticion_captura_imagen);

        } else {
            tomarfoto();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == peticion_captura_imagen) {
            Bundle extras = data.getExtras();
            Bitmap imagen = (Bitmap) extras.get("data");
            ObjetoImagen.setImageBitmap(imagen);
        }
    }
}