package youssefboufaied.com.betavib.Adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import youssefboufaied.com.betavib.Charts;
import youssefboufaied.com.betavib.Holder.ChartHolder;
import youssefboufaied.com.betavib.R;

/**
 * Created by amous on 30/05/17.
 */
public class ChartsAdapter extends ArrayAdapter<LineChart> {
    ChartHolder holder;
    Context context ;
    SharedPreferences pf;
    List<LineChart> lineCharts;
    Map<Integer, View> views = new HashMap<Integer, View>();
    private LayoutInflater inflater;
    public ChartsAdapter(Context context, int resource, List<LineChart> objects) {
        super(context, resource, objects);
        this.context = context;
        lineCharts=objects;
    }
    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (views.containsKey(position)) {
            return views.get(position);
        }
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.chart_lv, null);



        holder = (ChartHolder) convertView.getTag();
        if(holder == null){
            holder = new ChartHolder();
            holder.lineChart = (LineChart) convertView.findViewById(R.id.chartt);
            final LineChart lineChart = getItem(position);
        }

        System.out.println("end");
        int greencolor = context.getResources().getColor(R.color.colorLogo);
        int yellowcolor = context.getResources().getColor(R.color.coloryellow);
        int orangecolor = context.getResources().getColor(R.color.colororange);
        int linecolor = context.getResources().getColor(R.color.colorlines);
        int chartcolor = context.getResources().getColor(R.color.colorcharts);
        int colorred = context.getResources().getColor(R.color.colorred);
        TextView tv=(TextView) convertView.findViewById(R.id.txtlg);
        if(position==0) {

            tv.setText("VELOCITY (mm/s)");
            LimitLine llg9 = new LimitLine(Charts.refovelo , "");
            llg9.setLineWidth(2f);
            llg9.setLineColor(greencolor);
            holder.lineChart.getAxisLeft().addLimitLine(llg9);
            LimitLine llr9 = new LimitLine(Charts.dgovelo , "");
            llr9.setLineWidth(2f);
            holder.lineChart.getAxisLeft().addLimitLine(llr9);
            LimitLine lly9 = new LimitLine(Charts.pralovelo , "");
            lly9.setLineWidth(2f);
            lly9.setLineColor(yellowcolor);
            holder.lineChart.getAxisLeft().addLimitLine(lly9);
            LimitLine llo9 = new LimitLine(Charts.alovelo , "");
            llo9.setLineWidth(2f);
            llo9.setLineColor(orangecolor);
            holder.lineChart.getAxisLeft().addLimitLine(llo9);
            holder.lineChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
            holder.lineChart.getAxisRight().setEnabled(false);
            LineDataSet dataset = new LineDataSet(Charts.ovelo, "");
            //dataset.setDrawCubic(true);
//            dataset.setDrawFilled(true);
//            dataset.setColors(ColorTemplate.COLORFUL_COLORS);
            dataset.setCircleSize(3);
            dataset.setCircleColor(linecolor);
            dataset.setColor(linecolor);
            dataset.setDrawValues(false);
            holder.lineChart.animateY(2000);
            LineData data = new LineData(Charts.measdate, dataset);
            holder.lineChart.setData(data);
            holder.lineChart.setDescription("");
            holder.lineChart.setDescriptionTextSize(20);
            float max=holder.lineChart.getYMax();
            if(max>Charts.dgovelo){
                holder.lineChart.getAxisLeft().setAxisMaxValue((float)(max+(0.2*max)));
                holder.lineChart.getAxisLeft().setAxisMinValue(0f);

            }
            else{
                holder.lineChart.getAxisLeft().setAxisMaxValue((float)(Charts.dgovelo+(0.2*Charts.dgovelo)));
                holder.lineChart.getAxisLeft().setAxisMinValue(0f);

            }

            holder.lineChart.getLegend().setEnabled(false);
            holder.lineChart.setBackgroundColor(chartcolor);
            float e=Charts.ovelo.get(Charts.ovelo.size()-1).getVal();
if (e>Charts.dgovelo){

    LinearLayout linearlayout=(LinearLayout) convertView.findViewById(R.id.legend);
    GradientDrawable backgroundGradient= (GradientDrawable) linearlayout.getBackground();
    backgroundGradient.setColor(colorred);
}
            else if (e< Charts.dgovelo && e>Charts.alovelo){
    LinearLayout linearlayout=(LinearLayout) convertView.findViewById(R.id.legend);
    GradientDrawable backgroundGradient= (GradientDrawable) linearlayout.getBackground();
    backgroundGradient.setColor(colorred);
}
            else if(e<Charts.alovelo && e>Charts.pralovelo){
    LinearLayout linearlayout=(LinearLayout) convertView.findViewById(R.id.legend);
    GradientDrawable backgroundGradient= (GradientDrawable) linearlayout.getBackground();
    backgroundGradient.setColor(yellowcolor);

}

            else if (e<Charts.pralovelo){

    LinearLayout linearlayout=(LinearLayout) convertView.findViewById(R.id.legend);
    GradientDrawable backgroundGradient= (GradientDrawable) linearlayout.getBackground();
    backgroundGradient.setColor(greencolor);
}

        }

        if(position==1) {
            tv.setText("RMS [g]");
            LimitLine llg1 = new LimitLine(Charts.refrms, "");
            llg1.setLineWidth(2f);
            llg1.setLineColor(greencolor);
            holder.lineChart.getAxisLeft().addLimitLine(llg1);
            LimitLine llr1 = new LimitLine(Charts.dgrms, "");
            llr1.setLineWidth(2f);
            holder.lineChart.getAxisLeft().addLimitLine(llr1);
            LimitLine lly1 = new LimitLine(Charts.pralrms , "");
            lly1.setLineWidth(2f);
            lly1.setLineColor(yellowcolor);
            holder.lineChart.getAxisLeft().addLimitLine(lly1);
            LimitLine llo1 = new LimitLine(Charts.alrms, "");
            llo1.setLineWidth(2f);
            llo1.setLineColor(orangecolor);
            holder.lineChart.getAxisLeft().addLimitLine(llo1);
            holder.lineChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
            holder.lineChart.getAxisRight().setEnabled(false);
            LineDataSet dataset = new LineDataSet(Charts.rms, "");
            //dataset.setDrawCubic(true);
//            dataset.setDrawFilled(true);
//            dataset.setColors(ColorTemplate.COLORFUL_COLORS);
            dataset.setCircleSize(3);
            dataset.setCircleColor(linecolor);
            dataset.setColor(linecolor);
            dataset.setDrawValues(false);
            holder.lineChart.animateY(2000);
            LineData data = new LineData(Charts.measdate, dataset);
            holder.lineChart.setData(data);
            holder.lineChart.setDescription("");
            holder.lineChart.setDescriptionTextSize(20);
            float max=holder.lineChart.getYMax();
            if(max>Charts.dgrms){
                holder.lineChart.getAxisLeft().setAxisMaxValue((float)(max+(0.2*max)));
                holder.lineChart.getAxisLeft().setAxisMinValue(0f);

            }
            else{
                holder.lineChart.getAxisLeft().setAxisMaxValue((float)(Charts.dgrms+(0.2*Charts.dgrms)));
                holder.lineChart.getAxisLeft().setAxisMinValue(0f);

            }
            holder.lineChart.getLegend().setEnabled(false);
           float e=Charts.rms.get(Charts.rms.size()-1).getVal();
            if (e>Charts.dgrms){

                LinearLayout linearlayout=(LinearLayout) convertView.findViewById(R.id.legend);
                GradientDrawable backgroundGradient= (GradientDrawable) linearlayout.getBackground();
                backgroundGradient.setColor(colorred);
            }
            else if (e< Charts.dgovelo && e>Charts.alrms){
                LinearLayout linearlayout=(LinearLayout) convertView.findViewById(R.id.legend);
                GradientDrawable backgroundGradient= (GradientDrawable) linearlayout.getBackground();
                backgroundGradient.setColor(colorred);
            }
            else if(e<Charts.alrms && e>Charts.pralrms){
                LinearLayout linearlayout=(LinearLayout) convertView.findViewById(R.id.legend);
                GradientDrawable backgroundGradient= (GradientDrawable) linearlayout.getBackground();
                backgroundGradient.setColor(yellowcolor);

            }

            else if (e<Charts.pralrms){

                LinearLayout linearlayout=(LinearLayout) convertView.findViewById(R.id.legend);
                GradientDrawable backgroundGradient= (GradientDrawable) linearlayout.getBackground();
                backgroundGradient.setColor(greencolor);
            }


        }
        if(position==2) {
            tv.setText("PEAK [g]");
            LimitLine llg11 = new LimitLine(Charts.refpeak , "");
            llg11.setLineWidth(2f);
            llg11.setLineColor(greencolor);
            holder.lineChart.getAxisLeft().addLimitLine(llg11);
            LimitLine llr11 = new LimitLine(Charts.dgpeak , "");
            llr11.setLineWidth(2f);
            holder.lineChart.getAxisLeft().addLimitLine(llr11);
            LimitLine lly11 = new LimitLine(Charts.pralpeak , "");
            lly11.setLineWidth(2f);
            lly11.setLineColor(yellowcolor);
            holder.lineChart.getAxisLeft().addLimitLine(lly11);
            LimitLine llo = new LimitLine(Charts.alpeak , "");
            llo.setLineWidth(2f);
            llo.setLineColor(orangecolor);
            holder.lineChart.getAxisLeft().addLimitLine(llo);
            holder.lineChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
            holder.lineChart.getAxisRight().setEnabled(false);
            LineDataSet dataset = new LineDataSet(Charts.peak, "");
            //dataset.setDrawCubic(true);
//            dataset.setDrawFilled(true);
            //dataset.setColors(ColorTemplate.COLORFUL_COLORS);
            dataset.setCircleSize(3);
            dataset.setCircleColor(linecolor);
            dataset.setColor(linecolor);
            dataset.setDrawValues(false);
            holder.lineChart.animateY(2000);
            LineData data = new LineData(Charts.measdate, dataset);
            holder.lineChart.setData(data);
            holder.lineChart.setDescription("");
            holder.lineChart.setDescriptionTextSize(20);
            float max=holder.lineChart.getYMax();
            if(max>Charts.dgpeak){
                holder.lineChart.getAxisLeft().setAxisMaxValue((float)(max+(0.2*max)));
                holder.lineChart.getAxisLeft().setAxisMinValue(0f);

            }
            else{
                holder.lineChart.getAxisLeft().setAxisMaxValue((float)(Charts.dgpeak+(0.2*Charts.dgpeak)));
                holder.lineChart.getAxisLeft().setAxisMinValue(0f);

            }
            holder.lineChart.getLegend().setEnabled(false);
            float e=Charts.peak.get(Charts.peak.size()-1).getVal();
            if (e>Charts.dgpeak){

                LinearLayout linearlayout=(LinearLayout) convertView.findViewById(R.id.legend);
                GradientDrawable backgroundGradient= (GradientDrawable) linearlayout.getBackground();
                backgroundGradient.setColor(colorred);
            }
            else if (e< Charts.dgpeak && e>Charts.alpeak){
                LinearLayout linearlayout=(LinearLayout) convertView.findViewById(R.id.legend);
                GradientDrawable backgroundGradient= (GradientDrawable) linearlayout.getBackground();
                backgroundGradient.setColor(colorred);
            }
            else if(e<Charts.alpeak && e>Charts.pralpeak){
                LinearLayout linearlayout=(LinearLayout) convertView.findViewById(R.id.legend);
                GradientDrawable backgroundGradient= (GradientDrawable) linearlayout.getBackground();
                backgroundGradient.setColor(yellowcolor);

            }

            else if (e<Charts.pralpeak){

                LinearLayout linearlayout=(LinearLayout) convertView.findViewById(R.id.legend);
                GradientDrawable backgroundGradient= (GradientDrawable) linearlayout.getBackground();
                backgroundGradient.setColor(greencolor);
            }


        }
        if(position==3) {
            tv.setText("KU");
            LimitLine llg3 = new LimitLine(Charts.refku , "");
            llg3.setLineWidth(2f);
            llg3.setLineColor(greencolor);
            holder.lineChart.getAxisLeft().addLimitLine(llg3);
            LimitLine llr3 = new LimitLine(Charts.dgku , "");
            llr3.setLineWidth(2f);
            holder.lineChart.getAxisLeft().addLimitLine(llr3);
            LimitLine lly3 = new LimitLine(Charts.pralku , "");
            lly3.setLineWidth(2f);
            lly3.setLineColor(yellowcolor);
            holder.lineChart.getAxisLeft().addLimitLine(lly3);
            LimitLine llo3 = new LimitLine(Charts.alku , "");
            llo3.setLineWidth(2f);
            llo3.setLineColor(orangecolor);
            holder.lineChart.getAxisLeft().addLimitLine(llo3);
            holder.lineChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
            holder.lineChart.getAxisRight().setEnabled(false);
            LineDataSet dataset = new LineDataSet(Charts.ku, "");
            // dataset.setDrawCubic(true);
//            dataset.setDrawFilled(true);
//            dataset.setColors(ColorTemplate.COLORFUL_COLORS);
            dataset.setCircleSize(3);
            dataset.setCircleColor(linecolor);
            dataset.setColor(linecolor);
            dataset.setDrawValues(false);
            holder.lineChart.animateY(2000);
            LineData data = new LineData(Charts.measdate, dataset);
            holder.lineChart.setData(data);
            holder.lineChart.setDescription("");
            holder.lineChart.setDescriptionTextSize(20);
            float max=holder.lineChart.getYMax();
            if(max>Charts.dgku){
                holder.lineChart.getAxisLeft().setAxisMaxValue((float)(max+(0.2*max)));
                holder.lineChart.getAxisLeft().setAxisMinValue(0f);

            }
            else{
                holder.lineChart.getAxisLeft().setAxisMaxValue((float)(Charts.dgku+(0.2*Charts.dgku)));
                holder.lineChart.getAxisLeft().setAxisMinValue(0f);

            }
            holder.lineChart.getLegend().setEnabled(false);
            float e=Charts.ku.get(Charts.ku.size()-1).getVal();
            if (e>Charts.dgku){

                LinearLayout linearlayout=(LinearLayout) convertView.findViewById(R.id.legend);
                GradientDrawable backgroundGradient= (GradientDrawable) linearlayout.getBackground();
                backgroundGradient.setColor(colorred);
            }
            else if (e< Charts.dgku && e>Charts.alku){
                LinearLayout linearlayout=(LinearLayout) convertView.findViewById(R.id.legend);
                GradientDrawable backgroundGradient= (GradientDrawable) linearlayout.getBackground();
                backgroundGradient.setColor(colorred);
            }
            else if(e<Charts.dgku && e>Charts.pralku){
                LinearLayout linearlayout=(LinearLayout) convertView.findViewById(R.id.legend);
                GradientDrawable backgroundGradient= (GradientDrawable) linearlayout.getBackground();
                backgroundGradient.setColor(yellowcolor);

            }

            else if (e<Charts.pralku){

                LinearLayout linearlayout=(LinearLayout) convertView.findViewById(R.id.legend);
                GradientDrawable backgroundGradient= (GradientDrawable) linearlayout.getBackground();
                backgroundGradient.setColor(greencolor);
            }


        }
        if(position==4) {
            tv.setText("CF");

            LimitLine llg2 = new LimitLine(Charts.refcf , "");
            llg2.setLineWidth(2f);
            llg2.setLineColor(greencolor);
            holder.lineChart.getAxisLeft().addLimitLine(llg2);
            LimitLine llr2 = new LimitLine(Charts.dgcf , "");
            llr2.setLineWidth(2f);
            holder.lineChart.getAxisLeft().addLimitLine(llr2);
            LimitLine lly2 = new LimitLine(Charts.pralcf , "");
            lly2.setLineWidth(2f);
            lly2.setLineColor(yellowcolor);
            holder.lineChart.getAxisLeft().addLimitLine(lly2);
            LimitLine llo2 = new LimitLine(Charts.alcf , "");
            llo2.setLineWidth(2f);
            llo2.setLineColor(orangecolor);
            holder.lineChart.getAxisLeft().addLimitLine(llo2);
            holder.lineChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
            holder.lineChart.getAxisRight().setEnabled(false);
            LineDataSet dataset = new LineDataSet(Charts.cf, "");
            //dataset.setDrawCubic(true);
            //dataset.setDrawFilled(true);
//            dataset.setColors(ColorTemplate.COLORFUL_COLORS);
            dataset.setCircleSize(3);
            dataset.setCircleColor(linecolor);
            dataset.setColor(linecolor);
            dataset.setDrawValues(false);
            holder.lineChart.animateY(2000);
            LineData data = new LineData(Charts.measdate,dataset);
            holder.lineChart.setData(data);
            holder.lineChart.setDescription("");
            holder.lineChart.setDescriptionTextSize(20);
            float max=holder.lineChart.getYMax();
            if(max>Charts.dgcf){
                holder.lineChart.getAxisLeft().setAxisMaxValue((float)(max+(0.2*max)));
                holder.lineChart.getAxisLeft().setAxisMinValue(0f);

            }
            else{
                holder.lineChart.getAxisLeft().setAxisMaxValue((float)(Charts.dgcf+(0.2*Charts.dgcf)));
                holder.lineChart.getAxisLeft().setAxisMinValue(0f);

            }
            holder.lineChart.getLegend().setEnabled(false);
            float e=Charts.cf.get(Charts.cf.size()-1).getVal();
            if (e>Charts.dgcf){

                LinearLayout linearlayout=(LinearLayout) convertView.findViewById(R.id.legend);
                GradientDrawable backgroundGradient= (GradientDrawable) linearlayout.getBackground();
                backgroundGradient.setColor(colorred);
            }
            else if (e< Charts.dgcf && e>Charts.alcf){
                LinearLayout linearlayout=(LinearLayout) convertView.findViewById(R.id.legend);
                GradientDrawable backgroundGradient= (GradientDrawable) linearlayout.getBackground();
                backgroundGradient.setColor(colorred);
            }
            else if(e<Charts.alcf && e>Charts.pralcf){
                LinearLayout linearlayout=(LinearLayout) convertView.findViewById(R.id.legend);
                GradientDrawable backgroundGradient= (GradientDrawable) linearlayout.getBackground();
                backgroundGradient.setColor(yellowcolor);

            }

            else if (e<Charts.pralcf){

                LinearLayout linearlayout=(LinearLayout) convertView.findViewById(R.id.legend);
                GradientDrawable backgroundGradient= (GradientDrawable) linearlayout.getBackground();
                backgroundGradient.setColor(greencolor);
            }

        }
        if(position==5) {
            tv.setText("B1");
            LimitLine llg4 = new LimitLine(Charts.refb1 , "");
            llg4.setLineWidth(2f);
            llg4.setLineColor(greencolor);
            holder.lineChart.getAxisLeft().addLimitLine(llg4);
            LimitLine llr4 = new LimitLine(Charts.dgb1 , "");
            llr4.setLineWidth(2f);
            holder.lineChart.getAxisLeft().addLimitLine(llr4);
            LimitLine lly4 = new LimitLine(Charts.pralb1 , "");
            lly4.setLineWidth(2f);
            lly4.setLineColor(yellowcolor);
            holder.lineChart.getAxisLeft().addLimitLine(lly4);
            LimitLine llo4 = new LimitLine(Charts.alb1 , "");
            llo4.setLineWidth(2f);
            llo4.setLineColor(orangecolor);
            holder.lineChart.getAxisLeft().addLimitLine(llo4);
            holder.lineChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
            holder.lineChart.getAxisRight().setEnabled(false);
            LineDataSet dataset = new LineDataSet(Charts.b1, "");
            //dataset.setDrawCubic(true);
//            dataset.setDrawFilled(true);
//            dataset.setColors(ColorTemplate.COLORFUL_COLORS);
            dataset.setCircleSize(3);
            dataset.setCircleColor(linecolor);
            dataset.setColor(linecolor);
            dataset.setDrawValues(false);
            holder.lineChart.animateY(2000);
            LineData data = new LineData(Charts.measdate, dataset);
            holder.lineChart.setData(data);
            holder.lineChart.setDescription("");
            holder.lineChart.setDescriptionTextSize(20);
            float max=holder.lineChart.getYMax();
            if(max>Charts.dgb1){
                holder.lineChart.getAxisLeft().setAxisMaxValue((float)(max+(0.2*max)));
                holder.lineChart.getAxisLeft().setAxisMinValue(0f);

            }
            else{
                holder.lineChart.getAxisLeft().setAxisMaxValue((float)(Charts.dgb1+(0.2*Charts.dgb1)));
                holder.lineChart.getAxisLeft().setAxisMinValue(0f);

            }
            holder.lineChart.getLegend().setEnabled(false);
            float e=Charts.b1.get(Charts.b1.size()-1).getVal();
            if (e>Charts.dgb1){

                LinearLayout linearlayout=(LinearLayout) convertView.findViewById(R.id.legend);
                GradientDrawable backgroundGradient= (GradientDrawable) linearlayout.getBackground();
                backgroundGradient.setColor(colorred);
            }
            else if (e< Charts.dgb1 && e>Charts.alb1){
                LinearLayout linearlayout=(LinearLayout) convertView.findViewById(R.id.legend);
                GradientDrawable backgroundGradient= (GradientDrawable) linearlayout.getBackground();
                backgroundGradient.setColor(colorred);
            }
            else if(e<Charts.alb1 && e>Charts.pralb1){
                LinearLayout linearlayout=(LinearLayout) convertView.findViewById(R.id.legend);
                GradientDrawable backgroundGradient= (GradientDrawable) linearlayout.getBackground();
                backgroundGradient.setColor(yellowcolor);

            }

            else if (e<Charts.pralb1){

                LinearLayout linearlayout=(LinearLayout) convertView.findViewById(R.id.legend);
                GradientDrawable backgroundGradient= (GradientDrawable) linearlayout.getBackground();
                backgroundGradient.setColor(greencolor);
            }

        }
        if(position==6) {
            tv.setText("B2");
            LimitLine llg5 = new LimitLine(Charts.refb2 , "");
            llg5.setLineWidth(2f);
            llg5.setLineColor(greencolor);
            holder.lineChart.getAxisLeft().addLimitLine(llg5);
            LimitLine llr5 = new LimitLine(Charts.dgb2 , "");
            llr5.setLineWidth(2f);
            holder.lineChart.getAxisLeft().addLimitLine(llr5);
            LimitLine lly5 = new LimitLine(Charts.pralb2 , "");
            lly5.setLineWidth(2f);
            lly5.setLineColor(yellowcolor);
            holder.lineChart.getAxisLeft().addLimitLine(lly5);
            System.out.println(Charts.alb2);
            LimitLine llo5 = new LimitLine(Charts.alb2 , "");
            llo5.setLineWidth(2f);
            llo5.setLineColor(orangecolor);
            holder.lineChart.getAxisLeft().addLimitLine(llo5);
            holder.lineChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
            holder.lineChart.getAxisRight().setEnabled(false);
            LineDataSet dataset = new LineDataSet(Charts.b2, "");
            //dataset.setDrawCubic(true);
//            dataset.setDrawFilled(true);
//            dataset.setColors(ColorTemplate.COLORFUL_COLORS);
            dataset.setCircleSize(3);
            dataset.setCircleColor(linecolor);
            dataset.setColor(linecolor);
            dataset.setDrawValues(false);
            holder.lineChart.animateY(2000);
            LineData data = new LineData(Charts.measdate, dataset);
            holder.lineChart.setData(data);
            holder.lineChart.setDescription("");
            holder.lineChart.setDescriptionTextSize(20);
            float max=holder.lineChart.getYMax();
            if(max>Charts.dgb2){
                holder.lineChart.getAxisLeft().setAxisMaxValue((float)(max+(0.2*max)));
                holder.lineChart.getAxisLeft().setAxisMinValue(0f);

            }
            else{
                holder.lineChart.getAxisLeft().setAxisMaxValue((float)(Charts.dgb2+(0.2*Charts.dgb2)));
                holder.lineChart.getAxisLeft().setAxisMinValue(0f);

            }
            holder.lineChart.getLegend().setEnabled(false);
            float e=Charts.b2.get(Charts.b2.size()-1).getVal();
            if (e>Charts.dgb2){

                LinearLayout linearlayout=(LinearLayout) convertView.findViewById(R.id.legend);
                GradientDrawable backgroundGradient= (GradientDrawable) linearlayout.getBackground();
                backgroundGradient.setColor(colorred);
            }
            else if (e< Charts.dgb2 && e>Charts.alb2){
                LinearLayout linearlayout=(LinearLayout) convertView.findViewById(R.id.legend);
                GradientDrawable backgroundGradient= (GradientDrawable) linearlayout.getBackground();
                backgroundGradient.setColor(colorred);
            }
            else if(e<Charts.alb2 && e>Charts.pralb2){
                LinearLayout linearlayout=(LinearLayout) convertView.findViewById(R.id.legend);
                GradientDrawable backgroundGradient= (GradientDrawable) linearlayout.getBackground();
                backgroundGradient.setColor(yellowcolor);

            }

            else if (e<Charts.pralb2){

                LinearLayout linearlayout=(LinearLayout) convertView.findViewById(R.id.legend);
                GradientDrawable backgroundGradient= (GradientDrawable) linearlayout.getBackground();
                backgroundGradient.setColor(greencolor);
            }

        }
        if(position==7) {
            tv.setText("B3");

            LimitLine llg6 = new LimitLine(Charts.refb3 , "");
            llg6.setLineWidth(2f);
            llg6.setLineColor(greencolor);
            holder.lineChart.getAxisLeft().addLimitLine(llg6);
            LimitLine llr6 = new LimitLine(Charts.dgb3 , "");
            llr6.setLineWidth(2f);
            holder.lineChart.getAxisLeft().addLimitLine(llr6);
            LimitLine lly6 = new LimitLine(Charts.pralb3 , "");
            lly6.setLineWidth(2f);
            lly6.setLineColor(yellowcolor);
            holder.lineChart.getAxisLeft().addLimitLine(lly6);
            LimitLine llo6 = new LimitLine(Charts.alb3 , "");
            llo6.setLineWidth(2f);
            llo6.setLineColor(orangecolor);
            holder.lineChart.getAxisLeft().addLimitLine(llo6);
            holder.lineChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
            holder.lineChart.getAxisRight().setEnabled(false);
            LineDataSet dataset = new LineDataSet(Charts.b3, "");
            //dataset.setDrawCubic(true);
//            dataset.setDrawFilled(true);
//            dataset.setColors(ColorTemplate.COLORFUL_COLORS);
            dataset.setCircleSize(3);
            dataset.setCircleColor(linecolor);
            dataset.setColor(linecolor);
            dataset.setDrawValues(false);
            holder.lineChart.animateY(2000);
            LineData data = new LineData(Charts.measdate, dataset);
            holder.lineChart.setData(data);
            holder.lineChart.setDescription("");
            holder.lineChart.setDescriptionTextSize(20);
            float max=holder.lineChart.getYMax();
            if(max>Charts.dgb3){
                holder.lineChart.getAxisLeft().setAxisMaxValue((float)(max+(0.2*max)));
                holder.lineChart.getAxisLeft().setAxisMinValue(0f);

            }
            else{
                holder.lineChart.getAxisLeft().setAxisMaxValue((float)(Charts.dgb3+(0.2*Charts.dgb3)));
                holder.lineChart.getAxisLeft().setAxisMinValue(0f);

            }
            holder.lineChart.getLegend().setEnabled(false);
            float e=Charts.b3.get(Charts.b3.size()-1).getVal();
            if (e>Charts.dgb3){

                LinearLayout linearlayout=(LinearLayout) convertView.findViewById(R.id.legend);
                GradientDrawable backgroundGradient= (GradientDrawable) linearlayout.getBackground();
                backgroundGradient.setColor(colorred);
            }
            else if (e< Charts.dgb3 && e>Charts.alb3){
                LinearLayout linearlayout=(LinearLayout) convertView.findViewById(R.id.legend);
                GradientDrawable backgroundGradient= (GradientDrawable) linearlayout.getBackground();
                backgroundGradient.setColor(colorred);
            }
            else if(e<Charts.alb3 && e>Charts.pralb3){
                LinearLayout linearlayout=(LinearLayout) convertView.findViewById(R.id.legend);
                GradientDrawable backgroundGradient= (GradientDrawable) linearlayout.getBackground();
                backgroundGradient.setColor(yellowcolor);

            }

            else if (e<Charts.pralb3){

                LinearLayout linearlayout=(LinearLayout) convertView.findViewById(R.id.legend);
                GradientDrawable backgroundGradient= (GradientDrawable) linearlayout.getBackground();
                backgroundGradient.setColor(greencolor);
            }

        }
        if(position==8) {
            tv.setText("B4");
            LimitLine llg6 = new LimitLine(Charts.refb4 , "");
            llg6.setLineWidth(2f);
            llg6.setLineColor(greencolor);
            holder.lineChart.getAxisLeft().addLimitLine(llg6);
            LimitLine llr6 = new LimitLine(Charts.dgb4 , "");
            llr6.setLineWidth(2f);
            holder.lineChart.getAxisLeft().addLimitLine(llr6);
            LimitLine lly6 = new LimitLine(Charts.pralb4 , "");
            lly6.setLineWidth(2f);
            lly6.setLineColor(yellowcolor);
            holder.lineChart.getAxisLeft().addLimitLine(lly6);
            LimitLine llo6 = new LimitLine(Charts.alb4 , "");
            llo6.setLineWidth(2f);
            llo6.setLineColor(orangecolor);
            holder.lineChart.getAxisLeft().addLimitLine(llo6);
            holder.lineChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
            holder.lineChart.getAxisRight().setEnabled(false);
            LineDataSet dataset = new LineDataSet(Charts.b4, "");
            //dataset.setDrawCubic(true);
//            dataset.setDrawFilled(true);
//            dataset.setColors(ColorTemplate.COLORFUL_COLORS);
            dataset.setCircleSize(3);
            dataset.setCircleColor(linecolor);
            dataset.setColor(linecolor);
            dataset.setDrawValues(false);
            holder.lineChart.animateY(2000);
            LineData data = new LineData(Charts.measdate, dataset);
            holder.lineChart.setData(data);
            holder.lineChart.setDescription("");
            holder.lineChart.setDescriptionTextSize(20);
            float max=holder.lineChart.getYMax();
            if(max>Charts.dgb4){
                holder.lineChart.getAxisLeft().setAxisMaxValue((float)(max+(0.2*max)));
                holder.lineChart.getAxisLeft().setAxisMinValue(0f);

            }
            else{
                holder.lineChart.getAxisLeft().setAxisMaxValue((float)(Charts.dgb4+(0.2*Charts.dgb4)));
                holder.lineChart.getAxisLeft().setAxisMinValue(0f);

            }
            holder.lineChart.getLegend().setEnabled(false);
            float e=Charts.b4.get(Charts.b4.size()-1).getVal();
            if (e>Charts.dgb4){

                LinearLayout linearlayout=(LinearLayout) convertView.findViewById(R.id.legend);
                GradientDrawable backgroundGradient= (GradientDrawable) linearlayout.getBackground();
                backgroundGradient.setColor(colorred);
            }
            else if (e< Charts.dgb4 && e>Charts.alb4){
                LinearLayout linearlayout=(LinearLayout) convertView.findViewById(R.id.legend);
                GradientDrawable backgroundGradient= (GradientDrawable) linearlayout.getBackground();
                backgroundGradient.setColor(colorred);
            }
            else if(e<Charts.alb4 && e>Charts.pralb4){
                LinearLayout linearlayout=(LinearLayout) convertView.findViewById(R.id.legend);
                GradientDrawable backgroundGradient= (GradientDrawable) linearlayout.getBackground();
                backgroundGradient.setColor(yellowcolor);

            }

            else if (e<Charts.pralb4){

                LinearLayout linearlayout=(LinearLayout) convertView.findViewById(R.id.legend);
                GradientDrawable backgroundGradient= (GradientDrawable) linearlayout.getBackground();
                backgroundGradient.setColor(greencolor);
            }

        }

        if(position==9) {
            tv.setText("B5");
            LimitLine llg7 = new LimitLine(Charts.refb5 , "");
            llg7.setLineWidth(2f);
            llg7.setLineColor(greencolor);
            holder.lineChart.getAxisLeft().addLimitLine(llg7);
            LimitLine llr7 = new LimitLine(Charts.dgb5 , "");
            llr7.setLineWidth(2f);
            holder.lineChart.getAxisLeft().addLimitLine(llr7);
            LimitLine lly7 = new LimitLine(Charts.pralb5 , "");
            lly7.setLineWidth(2f);
            lly7.setLineColor(yellowcolor);
            holder.lineChart.getAxisLeft().addLimitLine(lly7);
            LimitLine llo7= new LimitLine(Charts.alb5 , "");
            llo7.setLineWidth(2f);
            llo7.setLineColor(orangecolor);
            holder.lineChart.getAxisLeft().addLimitLine(llo7);
            holder.lineChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
            holder.lineChart.getAxisRight().setEnabled(false);
            LineDataSet dataset = new LineDataSet(Charts.b5, "");
            //dataset.setDrawCubic(true);
//            dataset.setDrawFilled(true);
//            dataset.setColors(ColorTemplate.COLORFUL_COLORS);
            dataset.setCircleSize(3);
            dataset.setCircleColor(linecolor);
            dataset.setColor(linecolor);
            dataset.setDrawValues(false);
            holder.lineChart.animateY(2000);
            LineData data = new LineData(Charts.measdate, dataset);
            holder.lineChart.setData(data);
            holder.lineChart.setDescription("");
            holder.lineChart.setDescriptionTextSize(20);
            float max=holder.lineChart.getYMax();
            if(max>Charts.dgb5){
                holder.lineChart.getAxisLeft().setAxisMaxValue((float)(max+(0.2*max)));
                holder.lineChart.getAxisLeft().setAxisMinValue(0f);

            }
            else{
                holder.lineChart.getAxisLeft().setAxisMaxValue((float)(Charts.dgb5+(0.2*Charts.dgb5)));
                holder.lineChart.getAxisLeft().setAxisMinValue(0f);

            }
            holder.lineChart.getLegend().setEnabled(false);
            float e=Charts.b5.get(Charts.b5.size()-1).getVal();
            if (e>Charts.dgb5){

                LinearLayout linearlayout=(LinearLayout) convertView.findViewById(R.id.legend);
                GradientDrawable backgroundGradient= (GradientDrawable) linearlayout.getBackground();
                backgroundGradient.setColor(colorred);
            }
            else if (e< Charts.dgb5 && e>Charts.alb5){
                LinearLayout linearlayout=(LinearLayout) convertView.findViewById(R.id.legend);
                GradientDrawable backgroundGradient= (GradientDrawable) linearlayout.getBackground();
                backgroundGradient.setColor(colorred);
            }
            else if(e<Charts.alb5 && e>Charts.pralb5){
                LinearLayout linearlayout=(LinearLayout) convertView.findViewById(R.id.legend);
                GradientDrawable backgroundGradient= (GradientDrawable) linearlayout.getBackground();
                backgroundGradient.setColor(yellowcolor);

            }

            else if (e<Charts.pralb5){

                LinearLayout linearlayout=(LinearLayout) convertView.findViewById(R.id.legend);
                GradientDrawable backgroundGradient= (GradientDrawable) linearlayout.getBackground();
                backgroundGradient.setColor(greencolor);
            }

        }
        if(position==10) {
            tv.setText("B6");
            LimitLine llg8 = new LimitLine(Charts.refb6 , "");
            llg8.setLineWidth(2f);
            llg8.setLineColor(greencolor);
            holder.lineChart.getAxisLeft().addLimitLine(llg8);
            LimitLine llr8 = new LimitLine(Charts.dgb6 , "");
            llr8.setLineWidth(2f);
            holder.lineChart.getAxisLeft().addLimitLine(llr8);
            LimitLine lly8 = new LimitLine(Charts.pralb6 , "");
            lly8.setLineWidth(2f);
            lly8.setLineColor(yellowcolor);
            holder.lineChart.getAxisLeft().addLimitLine(lly8);
            LimitLine llo8 = new LimitLine(Charts.alb6 , "");
            llo8.setLineWidth(2f);
            llo8.setLineColor(orangecolor);
            holder.lineChart.getAxisLeft().addLimitLine(llo8);
            holder.lineChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
            holder.lineChart.getAxisRight().setEnabled(false);
            LineDataSet dataset = new LineDataSet(Charts.b6, "");
            //dataset.setDrawCubic(true);
//            dataset.setDrawFilled(true);
//            dataset.setColors(ColorTemplate.COLORFUL_COLORS);
            dataset.setCircleSize(3);
            dataset.setCircleColor(linecolor);
            dataset.setColor(linecolor);
            dataset.setDrawValues(false);
            holder.lineChart.animateY(2000);
            LineData data = new LineData(Charts.measdate, dataset);
            holder.lineChart.setData(data);
            holder.lineChart.setDescription("");
            holder.lineChart.setDescriptionTextSize(20);
            float max=holder.lineChart.getYMax();
            if(max>Charts.dgb6){
                holder.lineChart.getAxisLeft().setAxisMaxValue((float)(max+(0.2*max)));
                holder.lineChart.getAxisLeft().setAxisMinValue(0f);

            }
            else{
                holder.lineChart.getAxisLeft().setAxisMaxValue((float)(Charts.dgb6+(0.2*Charts.dgb6)));
                holder.lineChart.getAxisLeft().setAxisMinValue(0f);

            }
            holder.lineChart.getLegend().setEnabled(false);
            float e=Charts.b6.get(Charts.b6.size()-1).getVal();
            if (e>Charts.dgb6){

                LinearLayout linearlayout=(LinearLayout) convertView.findViewById(R.id.legend);
                GradientDrawable backgroundGradient= (GradientDrawable) linearlayout.getBackground();
                backgroundGradient.setColor(colorred);
            }
            else if (e< Charts.dgb6 && e>Charts.alb6){
                LinearLayout linearlayout=(LinearLayout) convertView.findViewById(R.id.legend);
                GradientDrawable backgroundGradient= (GradientDrawable) linearlayout.getBackground();
                backgroundGradient.setColor(colorred);
            }
            else if(e<Charts.alb6 && e>Charts.pralb6){
                LinearLayout linearlayout=(LinearLayout) convertView.findViewById(R.id.legend);
                GradientDrawable backgroundGradient= (GradientDrawable) linearlayout.getBackground();
                backgroundGradient.setColor(yellowcolor);

            }

            else if (e<Charts.pralb6){

                LinearLayout linearlayout=(LinearLayout) convertView.findViewById(R.id.legend);
                GradientDrawable backgroundGradient= (GradientDrawable) linearlayout.getBackground();
                backgroundGradient.setColor(greencolor);
            }

        }
        if(position==11) {
            tv.setText("SPEED");
            holder.lineChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
            holder.lineChart.getAxisRight().setEnabled(false);
            LineDataSet dataset = new LineDataSet(Charts.speed, "");
            //dataset.setDrawCubic(true);
//            dataset.setDrawFilled(true);
//            dataset.setColors(ColorTemplate.COLORFUL_COLORS);
            dataset.setCircleSize(3);
            dataset.setCircleColor(linecolor);
            dataset.setColor(linecolor);
            dataset.setDrawValues(false);
//            dataset.setDrawFilled(true);
            holder.lineChart.animateY(2000);
            LineData data = new LineData(Charts.measdate, dataset);
            holder.lineChart.setData(data);
            holder.lineChart.setDescription("");
            holder.lineChart.setDescriptionTextSize(20);
            holder.lineChart.getLegend().setEnabled(false);
            LinearLayout linearlayout=(LinearLayout) convertView.findViewById(R.id.legend);
            GradientDrawable backgroundGradient= (GradientDrawable) linearlayout.getBackground();
            backgroundGradient.setColor(linecolor);

        }
        views.put(position, convertView);


        return convertView;}
}
