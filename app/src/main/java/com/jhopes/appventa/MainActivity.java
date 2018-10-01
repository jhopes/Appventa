package com.jhopes.appventa;

import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import controller.ProductoCtrl;
import modell.Producto;
import presenter.AdapterListProducto;
import presenter.DialogListProducto;

import static com.google.android.material.snackbar.Snackbar.make;

public class MainActivity extends AppCompatActivity {

    private ProductoCtrl prodctrl=null;
    EditText name, desc, precio;

    final List<Producto> data = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prodctrl = new ProductoCtrl(MainActivity.this);
        name =  findViewById(R.id.edtName);
        desc =  findViewById(R.id.edtDesc);
        precio = findViewById(R.id.edtPrecio);

        //Bundle bundle = getArguments();

        /*if(){

        }*/

    }
    public void createProducto(View view){
        //Log.i("texto name: ",name.getText().toString());
        if(name.getText().length()>0 && desc.getText().length()>0 && precio.getText().length()>0) {
            if (prodctrl.createProducto(new Producto("", name.getText().toString(), desc.getText().toString(), precio.getText().toString()))) {
                limpiar();
                make(view, "Datos guardados correctamente", Toast.LENGTH_SHORT).show();
            } else {
                make(view, "No se guardaron los datos", Toast.LENGTH_SHORT).show();
            }
        }else{
            make(view, "Llene el formulario", Toast.LENGTH_SHORT).show();
        }

    }
    public void listarDialog(View v){
        new DialogListProducto().show(getSupportFragmentManager(),null);
    }
    public void limpiar(){
        name.setText("");
        desc.setText("");
        precio.setText("");
    }

}
