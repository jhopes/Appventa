package configdb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Sqlite extends SQLiteOpenHelper {

    private static String namedb="ventadb";
    private static int versiondb=1;
    private String table_product="CREATE TABLE producto(id_producto INTEGER PRIMARY KEY AUTOINCREMENT, " +
            " nombre_product TEXT, descripcion TEXT, precio TEXT)";

    public Sqlite(Context context) {
        super(context, namedb, null, versiondb);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(table_product);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
    }
}
