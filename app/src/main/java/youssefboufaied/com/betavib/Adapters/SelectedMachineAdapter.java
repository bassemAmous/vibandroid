package youssefboufaied.com.betavib.Adapters;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import youssefboufaied.com.betavib.Charts;
import youssefboufaied.com.betavib.Entity.SelectMachine;
import youssefboufaied.com.betavib.Holder.MachineHolder;
import youssefboufaied.com.betavib.R;
import youssefboufaied.com.betavib.ScrollingActivity;

/**
 * Created by amous on 30/05/17.
 */

public class SelectedMachineAdapter extends ArrayAdapter<SelectMachine> {
    MachineHolder holder;
    Context context ;
    SharedPreferences pf;
    List<SelectMachine> selectMachines;
    private LayoutInflater inflater;
    Map<Integer, View> views = new HashMap<Integer, View>();
    public SelectedMachineAdapter(Context context, int resource, List<SelectMachine> objects) {
        super(context, resource, objects);
        this.context = context;
        selectMachines=objects;
    }
    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if (views.containsKey(position)) {
            return views.get(position);
        }
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.machine_grid2, null);


        final SelectMachine selectMachine = getItem(position);



            holder = (MachineHolder) convertView.getTag();
            if (holder == null) {
                holder = new MachineHolder();
                holder.nom = (TextView) convertView.findViewById(R.id.nomText);
                holder.backgroundImage = (LinearLayout) convertView.findViewById(R.id.linearMachinGrid);

                switch (selectMachine.getPointState()) {
                    case "Gray":
                        holder.backgroundImage.setBackgroundColor(Color.rgb(207, 207, 207));
                        break;
                    case "Green":
                        holder.backgroundImage.setBackgroundColor(Color.rgb(85, 170, 103));
                        break;
                    case "Orange":
                        holder.backgroundImage.setBackgroundColor(Color.rgb(247, 165, 34));
                        break;
                    case "Yellow":
                        holder.backgroundImage.setBackgroundColor(Color.rgb(239, 237, 81));
                        break;
                    case "Red":
                        holder.backgroundImage.setBackgroundColor(Color.rgb(239, 56, 35));
                        break;

                }

                convertView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i2 = new Intent(getContext(), Charts.class);
                        i2.putExtra("selectMachine", selectMachine);

                        i2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(i2);

                    }
                });


                //   pf=this.getContext().getApplicationContext().getSharedPreferences("pref", MODE_PRIVATE);
                // pf = getContext().getSharedPreferences("pref", MODE_PRIVATE);


            }
            holder.nom.setText(selectMachine.getPointDirName());
            System.out.println("end");

        if(selectMachine.getPointDirName().equals("fergha")) {
            convertView.setVisibility(View.INVISIBLE);
            convertView.setEnabled(false);
        }

        views.put(position, convertView);


        return convertView;}
}
