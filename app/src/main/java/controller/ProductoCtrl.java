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

    public boolean createProducto(Producto prod) {
        //sql="INSERT INTO";
        boolean estado;
        try {
            cx.getWritableDatabase().execSQL("INSERT INTO producto(nombre_product, descripcion , precio) " +
                    "VALUES ('" + prod.getNombre_producto() + "', '" + prod.getDescripcion() + "','" + prod.getPrecio() + "')");
            estado = true;
        } catch (Exception e) {
            Log.e("Error en guardar: ", e.getMessage());
            estado = false;
        }
        return estado;
    }

    public Cursor readProducto() {
        return cx.getReadableDatabase().rawQuery("SELECT id_producto, nombre_product, descripcion , precio FROM producto ", null);
    }

    public boolean updateProducto(Producto prod) {
        boolean estado;
        try {
            cx.getWritableDatabase().execSQL("UPDATE producto " +
                    "SET nombre_product ='" + prod.getNombre_producto() + "', descripcion ='" + prod.getDescripcion() + "', precio ='" + prod.getPrecio() + "' " +
                    "WHERE id_producto=" + prod.getId_producto() + "");
            estado = true;
        } catch (Exception e) {
            Log.e("Error en apdate: ", e.getMessage());
            estado = false;
        }
        return estado;
    }
    public boolean deleteProducto(int id){
        boolean estado;
        try {
            cx.getWritableDatabase().delete("producto", "id_producto = " + id, null);
            estado = true;
        }catch (Exception e) {
            Log.e("Error en eliminar: ", e.getMessage());
            estado = false;
        }
        return estado;
    }
}
