package presenter;



import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.jhopes.appventa.R;

import java.util.List;

import modell.Producto;

public class AdapterListProducto extends ArrayAdapter {
    private Context context;
    private List<Producto> data;

    public AdapterListProducto(Activity context, List<Producto> data) {
        super(context, R.layout.adapterlistaproducto, data);
        this.context = context;
        this.data = data;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View item = inflater.inflate(R.layout.adapterlistaproducto, null);

        TextView titulo = (TextView) item.findViewById(R.id.tvtitle);
        titulo.setText(data.get(position).getNombre_producto());

        TextView desc = (TextView) item.findViewById(R.id.tvdesc);
        desc.setText(data.get(position).getDescripcion());
        return (View) item;
    }

}
