package com.Johana.paint;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static Lienzo lienzo;
    ImageButton nuevo,borrar,pintar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lienzo = (Lienzo) findViewById(R.id.lienzo);
        nuevo = (ImageButton) findViewById(R.id.IbNuevo);
        borrar = (ImageButton) findViewById(R.id.IbBorrar);
        pintar = (ImageButton) findViewById(R.id.IbPintar);

        nuevo.setOnClickListener(this);
        borrar.setOnClickListener(this);
        pintar.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.IbNuevo:
                AlertDialog.Builder newDialog = new AlertDialog.Builder(this);
                newDialog.setTitle("Nuevo Dibujo");
                newDialog.setMessage("¿Desea borrar el dibujo (perderás el dibujo actual)?");
                newDialog.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        lienzo.NuevoDibujo();
                        dialog.dismiss();
                    }
                });
                newDialog.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                newDialog.show();
                break;
            case R.id.IbBorrar:
                    lienzo.Borrar();
                break;
            case R.id.IbPintar:
                    lienzo.Pintar();
                break;

                default:
        }

    }
}
