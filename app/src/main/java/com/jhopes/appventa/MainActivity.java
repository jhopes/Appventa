package com.jhopes.appventa;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import controller.ProductoCtrl;
import modell.Producto;

public class MainActivity extends AppCompatActivity {

    private ProductoCtrl prodctrl=null;
    EditText name, desc, precio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prodctrl = new ProductoCtrl(MainActivity.this);
        name =  findViewById(R.id.edtName);
        desc =  findViewById(R.id.edtDesc);
        precio = findViewById(R.id.edtPrecio);

    }
    public void createProducto(View view){
        //Log.i("texto name: ",name.getText().toString());
        if(name.getText().length()>0 && desc.getText().length()>0 && precio.getText().length()>0) {
            if (prodctrl.createProducto(new Producto("", name.getText().toString(), desc.getText().toString(), precio.getText().toString()))) {
                limpiar();
                Snackbar.make(view, "Datos guardados correctamente", Toast.LENGTH_SHORT).show();
            } else {
                Snackbar.make(view, "No se guardaron los datos", Toast.LENGTH_SHORT).show();
            }
        }else{
            Snackbar.make(view, "Llene el formulario", Toast.LENGTH_SHORT).show();
        }

    }
    public void listarDialog(View view){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Info");
        // this is set the view from XML inside AlertDialog
        alert.setView(R.layout.dialoglistaproducto);
        // disallow cancel of AlertDialog on click of back button and outside touch
        alert.setCancelable(false);
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getBaseContext(), "Cancel clicked", Toast.LENGTH_SHORT).show();
            }
        });

        alert.setPositiveButton("Done", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                /*String user = etUsername.getText().toString();
                String pass = etEmail.getText().toString();*/
                Toast.makeText(getBaseContext(), "", Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();

    }
    public void limpiar(){
        name.setText("");
        desc.setText("");
        precio.setText("");
    }

}
