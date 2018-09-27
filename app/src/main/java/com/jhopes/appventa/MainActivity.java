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

import static com.google.android.material.snackbar.Snackbar.make;

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
                make(view, "Datos guardados correctamente", Toast.LENGTH_SHORT).show();
            } else {
                make(view, "No se guardaron los datos", Toast.LENGTH_SHORT).show();
            }
        }else{
            make(view, "Llene el formulario", Toast.LENGTH_SHORT).show();
        }

    }
    public void listarDialog(final View v){
        ListView lista;
        final List<Producto> data = new ArrayList<>();
        Cursor cur_lista;

        cur_lista = prodctrl.readProducto();
        if(cur_lista.moveToFirst()){
            while(cur_lista.moveToNext()){
                data.add(new Producto(cur_lista.getString(0),cur_lista.getString(1),cur_lista.getString(2),cur_lista.getString(3)));
            }
        }

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Info");

        View view = (View) getLayoutInflater().inflate(R.layout.dialoglistaproducto,null);
        alert.setView(view);

        lista =(ListView) view.findViewById(R.id.lvproducto);
        lista.setAdapter(new AdapterListProducto(MainActivity.this,data));
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, final View view, int i, long l) {
                final Producto prod = data.get(i);
                CharSequence[] items = {"Edit","Delete"};
                new AlertDialog.Builder(MainActivity.this).setTitle("Opciones")
                        .setItems(items,new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                switch (i){
                                    case 0:
                                        Snackbar.make(view,"Actualizar = "+prod.getId_producto(), Toast.LENGTH_SHORT).show();
                                        break;
                                    case 1:
                                        prodctrl.deleteProducto(Integer.parseInt(prod.getId_producto()));
                                        break;
                                }
                                dialogInterface.dismiss();
                            }
                        }).show();
            }
        });
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
