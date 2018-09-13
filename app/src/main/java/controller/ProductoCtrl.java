package controller;

import android.content.Context;
import android.database.Cursor;

import configdb.Sqlite;
import modell.Producto;

public class ProductoCtrl {
    Sqlite cx;
    private String sql;
    public ProductoCtrl(Context context) {
        cx = new Sqlite(context);
    }

    public void createProducto(Producto prod){
        //sql="INSERT INTO";
        cx.getWritableDatabase().execSQL("INSERT INTO producto(nombre_product, descripcion , precio) " +
                "VALUES ('"+prod.getNombre_producto()+"', '"+prod.getDescripcion()+"','"+prod.getPrecio()+"')");
    }
    public Cursor readProducto(){
        return cx.getReadableDatabase().rawQuery("SELECT id_producto, nombre_product, descripcion , precio FROM producto ",null);
    }
}
