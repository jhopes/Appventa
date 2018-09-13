package com.jhopes.appventa;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import configdb.Sqlite;
import controller.ProductoCtrl;
import modell.Producto;

public class MainActivity extends AppCompatActivity {

    private ProductoCtrl prodctrl=null;
    private Producto prod = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prodctrl = new ProductoCtrl(MainActivity.this);
        prod = new Producto();
        prod.setNombre_producto("INKA COLA");
        prod.setDescripcion("Muerte lenta y dolorosa");
        prod.setPrecio("2.30");
        prodctrl.createProducto(prod);

    }

}
