package presenter;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.jhopes.appventa.MainActivity;
import com.jhopes.appventa.R;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import controller.ProductoCtrl;
import modell.Producto;

public class DialogListProducto extends DialogFragment {
    ListView lista;
    List<Producto> data = new ArrayList<>();
    Cursor cur_lista;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.dialoglistaproducto, container, false);

        final ProductoCtrl prodctrl = new ProductoCtrl(getActivity());
        /*Bundle bundle = getArguments();
        numero = bundle.getString("Numero");
        titulo = bundle.getString("Titulo");
        letra = bundle.getString("Letra");*/



        cur_lista = prodctrl.readProducto();
        if(cur_lista.moveToFirst()){
            while(cur_lista.moveToNext()){
                data.add(new Producto(cur_lista.getString(0),cur_lista.getString(1),cur_lista.getString(2),cur_lista.getString(3)));
            }
        }
        lista =(ListView) view.findViewById(R.id.lvproducto);
        lista.setAdapter(new AdapterListProducto(getActivity(),data));
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, final View view, int i, long l) {
                final Producto prod = data.get(i);
                final int i1 =i;
                Log.i("Id","Guauu "+i1);
                CharSequence[] items = {"Edit","Delete"};
                AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
                alert.setTitle("Opciones").
                setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        switch (i) {
                            case 0:
                                dismiss();

                                Bundle args = new Bundle();
                                args.putString("Nombre", prod.getNombre_producto());
                                args.putString("Desc", prod.getDescripcion());
                                args.putString("Precio", prod.getPrecio());
                                setArguments(args);
                                break;
                            case 1:
                                prodctrl.deleteProducto(Integer.parseInt(prod.getId_producto()));
                                dismiss();
                                new DialogListProducto().show(getFragmentManager(),null);
                                break;
                        }
                        dialogInterface.dismiss();
                    }
                });
                alert.show();
            }
        });
        return view;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            dismiss();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
