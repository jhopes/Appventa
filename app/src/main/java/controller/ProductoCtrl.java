package controller;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import configdb.Sqlite;
import modell.Producto;

public class ProductoCtrl {
    private Sqlite cx;
    private String sql;
    public ProductoCtrl(Context context) {
        cx = new Sqlite(context);
    }

    public boolean createProducto(Producto prod){
        //sql="INSERT INTO";
        boolean estado;
        try {
            cx.getWritableDatabase().execSQL("INSERT INTO producto(nombre_product, descripcion , precio) " +
                    "VALUES ('" + prod.getNombre_producto() + "', '" + prod.getDescripcion() + "','" + prod.getPrecio() + "')");
            estado = true;
        }catch (Exception e){
            Log.e("Error en guardar: ",e.getMessage());
            estado = false;
        }
        return estado;
    }
    public Cursor readProducto(){
        return cx.getReadableDatabase().rawQuery("SELECT id_producto, nombre_product, descripcion , precio FROM producto ",null);
    }
}
