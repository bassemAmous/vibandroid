package youssefboufaied.com.betavib.Adapters;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import youssefboufaied.com.betavib.Entity.DataBase;
import youssefboufaied.com.betavib.Entity.Machine;
import youssefboufaied.com.betavib.Holder.MachineHolder;
import youssefboufaied.com.betavib.R;
import youssefboufaied.com.betavib.ScrollingActivity;
import youssefboufaied.com.betavib.SelectedMachineActivity;

/**
 * Created by bof on 26/05/2017.
 */

public class MachineAdapter  extends ArrayAdapter<Machine> {
    MachineHolder holder;
    Context context ;
    SharedPreferences pf;
    List<Machine> machines;

    public MachineAdapter(Context context, int resource, List<Machine> objects) {
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

        final Machine machine = getItem(position);
        holder = (MachineHolder) convertView.getTag();
        if(holder == null){
            holder = new MachineHolder();
            holder.nom = (TextView) convertView.findViewById(R.id.nomText);
            holder.backgroundImage = (LinearLayout) convertView.findViewById(R.id.linearMachinGrid);

            switch (machine.getMachineState()){
                case "Gray":holder.backgroundImage.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.database3));break;
                case "Green":holder.backgroundImage.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.database4));break;
                case "Orange":holder.backgroundImage.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.database5));break;
                case "Yellow":holder.backgroundImage.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.database1));break;
                case "Red":holder.backgroundImage.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.database2));break;

            }



            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent i2 =new Intent(getContext(),ScrollingActivity.class);
                    i2.putExtra("machine",  machine);
                    i2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(i2);

                }
            });


            //   pf=this.getContext().getApplicationContext().getSharedPreferences("pref", MODE_PRIVATE);
            // pf = getContext().getSharedPreferences("pref", MODE_PRIVATE);



        }
        holder.nom.setText(machine.getMachineName());
        System.out.println("end");

        return convertView;}
}
