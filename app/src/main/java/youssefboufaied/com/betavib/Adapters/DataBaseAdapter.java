package youssefboufaied.com.betavib.Adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import youssefboufaied.com.betavib.Entity.DataBase;
import youssefboufaied.com.betavib.Holder.DataBaseHolder;
import youssefboufaied.com.betavib.R;

/**
 * Created by bof on 26/05/2017.
 */

public class DataBaseAdapter extends ArrayAdapter<DataBase> {
    DataBaseHolder holder;
    Context context ;
    SharedPreferences pf;
    List<DataBase> machines;
    public DataBaseAdapter(Context context, int resource, List<DataBase> objects) {
        super(context, resource, objects);
        this.context = context;
        machines=objects;
    }
    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {


        if(convertView == null){

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.machine_grid,parent, false);

        }

        final DataBase machine = getItem(position);
        holder = (DataBaseHolder) convertView.getTag();
        if(holder == null){
            holder = new DataBaseHolder();
            holder.nom = (TextView) convertView.findViewById(R.id.nomText);

            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });


            //   pf=this.getContext().getApplicationContext().getSharedPreferences("pref", MODE_PRIVATE);
            // pf = getContext().getSharedPreferences("pref", MODE_PRIVATE);



        }
        holder.nom.setText(" N°\n"+machine.getName());
        System.out.println("end");

        return convertView;}
}
