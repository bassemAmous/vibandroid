package youssefboufaied.com.betavib;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.Highlight;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import com.github.mikephil.charting.listener.*;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.github.mikephil.charting.data.LineDataSet;

import im.dacer.androidcharts.LineView;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;
import youssefboufaied.com.betavib.Adapters.MachineAdapter;
import youssefboufaied.com.betavib.DB.MachineDB;
import youssefboufaied.com.betavib.Entity.AssetsCr;
import youssefboufaied.com.betavib.Entity.Departement;
import youssefboufaied.com.betavib.Entity.Measurement;
import youssefboufaied.com.betavib.Entity.OverAll;
import youssefboufaied.com.betavib.Entity.Point;
import youssefboufaied.com.betavib.Service.APIService;

import com.github.mikephil.charting.data.LineDataSet;
import com.squareup.okhttp.OkHttpClient;

public class DashboardActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    Context context;
    int[] colors;
    private LineChart mChart;
    SharedPreferences pf;
    ArrayList overAllVelocitYear;
    PieChart pieChartg;
    LineChart lineChart;
    LineChart lineChart1;

    LineView lineView;



    private List itemsPlant;
    private List<String> itemsDep;
    private List<String> itemsState;
    private List<String> itemsSite;
    Spinner sp;
    Spinner spDep;
    Spinner spState ;
    Spinner spSite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        pieChartg = (PieChart) findViewById(R.id.chartgood);
        lineChart = (LineChart) findViewById(R.id.chartt);
        lineChart1 = (LineChart) findViewById(R.id.charttt);
        lineView = (LineView) findViewById(R.id.line_view);
        pf = getApplicationContext().getSharedPreferences("pref", MODE_PRIVATE);
        MachineDB machineDB;
        machineDB = new MachineDB(getBaseContext());
        machineDB.open();
        int[] counts = machineDB.getAllMachineByState();
        ;
        setTitle("VibWorks-"+pf.getString("database",""));

        OkHttpClient client = new OkHttpClient();
        client.setConnectTimeout(5, TimeUnit.MINUTES); // connect timeout
        client.setReadTimeout(5, TimeUnit.MINUTES);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(LoginActivity.adresseLocal).addConverterFactory(GsonConverterFactory.create()).client(client)
                .build();
        APIService service = retrofit.create(APIService.class);
        Call<List<OverAll>> call = service.getOverYearOvelo(pf.getString("ip", ""), pf.getString("login", ""), pf.getString("password", ""), pf.getString("database", ""));
        call.enqueue(new Callback<List<OverAll>>() {
            @Override
            public void onResponse(Response<List<OverAll>> response, Retrofit retrofit) {

                int greencolor = getApplicationContext().getResources().getColor(R.color.colorLogo);
                int yellowcolor = getApplicationContext().getResources().getColor(R.color.coloryellow);
                int orangecolor = getApplicationContext().getResources().getColor(R.color.colororange);
                int linecolor = getApplicationContext().getResources().getColor(R.color.colorlines);
                int chartcolor = getApplicationContext().getResources().getColor(R.color.colorcharts);
                int colorred = getApplicationContext().getResources().getColor(R.color.colorred);

                TextView tv = (TextView) findViewById(R.id.txtlg);
                tv.setText("OVER ALL DYNAMIC BEHAVIOR");
                LinearLayout linearlayout = (LinearLayout) findViewById(R.id.legend);
                GradientDrawable backgroundGradient = (GradientDrawable) linearlayout.getBackground();
                backgroundGradient.setColor(linecolor);
                lineChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
                lineChart.getAxisRight().setEnabled(false);
                ArrayList<Entry> overallvelociyyear = new ArrayList<>();
                ArrayList<String> labels = new ArrayList<String>();

                int i = 0;
                for (OverAll o : response.body()) {

                    Double dl = (Double) o.content.get(0);
                    float f1 = Double.valueOf(dl).floatValue();
                    overallvelociyyear.add(new Entry((float) f1, i));
                    labels.add(o.content.get(1) + "");
                    i++;
                }

                LineDataSet dataset = new LineDataSet(overallvelociyyear, "");
                dataset.setDrawCubic(true);
                dataset.setDrawFilled(true);
                dataset.setColors(ColorTemplate.COLORFUL_COLORS);
                dataset.setCircleSize(3);
                dataset.setCircleColor(linecolor);
                dataset.setColor(linecolor);
                dataset.setDrawValues(false);
                lineChart.animateY(2000);
                LineData data = new LineData(labels, dataset);
                lineChart.setData(data);
                lineChart.setDescription("");
                lineChart.setDescriptionTextSize(20);



//*********************************************************************************************************
                OkHttpClient client = new OkHttpClient();
                client.setConnectTimeout(5, TimeUnit.MINUTES); // connect timeout
                client.setReadTimeout(5, TimeUnit.MINUTES);


                retrofit = new Retrofit.Builder()
                        .baseUrl(LoginActivity.adresseLocal).addConverterFactory(GsonConverterFactory.create()).client(client)
                        .build();
                APIService service = retrofit.create(APIService.class);
                Call<List<Measurement>> call = service.getMeasurementsYear(pf.getString("ip", ""), pf.getString("login", ""), pf.getString("password", ""), pf.getString("database", ""));
                call.enqueue(new Callback<List<Measurement>>() {
                    @Override
                    public void onResponse(Response<List<Measurement>> response, Retrofit retrofit) {

                        int greencolor = getApplicationContext().getResources().getColor(R.color.colorLogo);
                        int yellowcolor = getApplicationContext().getResources().getColor(R.color.coloryellow);
                        int orangecolor = getApplicationContext().getResources().getColor(R.color.colororange);
                        int linecolor = getApplicationContext().getResources().getColor(R.color.colorlines);
                        int chartcolor = getApplicationContext().getResources().getColor(R.color.colorcharts);
                        int colorred = getApplicationContext().getResources().getColor(R.color.colorred);

                        TextView tv = (TextView) findViewById(R.id.txtlg1);
                        tv.setText("");
                        LinearLayout linearlayout = (LinearLayout) findViewById(R.id.legend1);
                        GradientDrawable backgroundGradient = (GradientDrawable) linearlayout.getBackground();
                        backgroundGradient.setColor(linecolor);
                        lineChart1.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
                        lineChart1.getAxisRight().setEnabled(false);
                        ArrayList<Entry> measurmentyear = new ArrayList<>();

                        ArrayList<String> labels1 = new ArrayList<String>();

                        int i = 0;
                        for (Measurement m : response.body()) {

                            Double dl = (Double) m.content.get(0);
                            float f1 = Double.valueOf(dl).floatValue();
                            measurmentyear.add(new Entry((float) f1, i));
                            labels1.add(m.content.get(1) + "");
                            i++;
                        }

                        LineDataSet datasets = new LineDataSet(measurmentyear, "");
                        datasets.setDrawCubic(true);
                        datasets.setDrawFilled(true);
                        datasets.setColors(ColorTemplate.COLORFUL_COLORS);
                        datasets.setCircleSize(3);
                        datasets.setCircleColor(linecolor);
                        datasets.setColor(linecolor);
                        datasets.setDrawValues(false);
                        lineChart1.animateY(2000);
                        LineData datas = new LineData(labels1, datasets);
                        lineChart1.setData(datas);
                        lineChart1.setDescription("");
                        lineChart1.setDescriptionTextSize(20);


                        OkHttpClient client = new OkHttpClient();
                        client.setConnectTimeout(5, TimeUnit.MINUTES); // connect timeout
                        client.setReadTimeout(5, TimeUnit.MINUTES);


                        retrofit = new Retrofit.Builder()
                                .baseUrl(LoginActivity.adresseLocal).addConverterFactory(GsonConverterFactory.create()).client(client)
                                .build();
                        APIService service = retrofit.create(APIService.class);
                        Call<List<AssetsCr>> call = service.getAssetsYear(pf.getString("ip", ""), pf.getString("login", ""), pf.getString("password", ""), pf.getString("database", ""));
                        call.enqueue(new Callback<List<AssetsCr>>() {
                            @Override
                            public void onResponse(Response<List<AssetsCr>> response, Retrofit retrofit) {

                                ArrayList<ArrayList<Integer>> values = new ArrayList<>();
                                ArrayList<String> labels = new ArrayList<>();
                                ArrayList<Integer> values1 = new ArrayList<>();
                                ArrayList<Integer> values2 = new ArrayList<>();
                                ArrayList<Integer> values3 = new ArrayList<>();
                                ArrayList<Integer> values4 = new ArrayList<>();
                                ArrayList<Integer> values5 = new ArrayList<>();
                                for (AssetsCr o : response.body()) {
                                    labels.add(o.OperationYear + "");
                                    values1.add(o.Good);
                                    values2.add(o.Alarm);
                                    values3.add(o.PreAlarm);
                                    values4.add(o.Danger);
                                    values5.add(o.NA);


                                }


                                values.add(values1);
                                values.add(values2);
                                values.add(values3);
                                values.add(values4);

                                lineView.setBottomTextList(labels);
                                lineView.setDrawDotLine(false); //optional
                                lineView.setShowPopup(LineView.SHOW_POPUPS_MAXMIN_ONLY); //optional
                                lineView.setColorArray(new int[]{Color.BLACK, Color.GREEN, Color.GRAY, Color.CYAN});
                                lineView.setDataList(values); //or lineView.setFloatDataList(floatDataLists)


                            }

                            @Override
                            public void onFailure(Throwable t) {
                                System.out.println("failre" + t);

                                //  getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

                            }
                        });






            /*    if(!(response.body() == null)){
                    for (Departement d : response.body()
                            ) {
                        itemsDep.add(d.getDepartementName());
                    }
                    ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_element,R.id.spinnerElement, itemsDep); //selected item will look like a spinner set from XML

                    spDep.setAdapter(spinnerArrayAdapter);
                }

                avi.hide();
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);*/
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        System.out.println("failre" + t);

                        //  getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

                    }
                });


            }

            @Override
            public void onFailure(Throwable t) {
                System.out.println("failre" + t);

                //  getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

            }
        });


        int greencolor = getApplicationContext().getResources().getColor(R.color.colorLogo);
        int yellowcolor = getApplicationContext().getResources().getColor(R.color.coloryellow);
        int orangecolor = getApplicationContext().getResources().getColor(R.color.colororange);
        int linecolor = getApplicationContext().getResources().getColor(R.color.colorlines);
        int chartcolor = getApplicationContext().getResources().getColor(R.color.colorcharts);
        int colorred = getApplicationContext().getResources().getColor(R.color.colorred);


        System.out.println("counts" + counts[0]);

        // MultiLineChart multiLineChart = (MultiLineChart) findViewById(R.id.chartm);
        ArrayList<Entry> entriesg = new ArrayList<>();

        entriesg.add(new Entry(counts[0], 0));
        entriesg.add(new Entry(counts[1], 0));
        entriesg.add(new Entry(counts[2], 0));
        entriesg.add(new Entry(counts[3], 0));
        PieDataSet datasetg = new PieDataSet(entriesg, "");

        datasetg.setColors(ColorTemplate.VORDIPLOM_COLORS);
        ArrayList<String> labelsg = new ArrayList<String>();
        labelsg.add("Good");
        labelsg.add("Alarm");
        labelsg.add("Warning");
        labelsg.add("Danger");
        PieData datag = new PieData(labelsg, datasetg);
        pieChartg.setData(datag);
        pieChartg.setDescription("");
        pieChartg.setDrawCenterText(true);
        pieChartg.setCenterText("");
        pieChartg.animateY(1000);
        Paint p1 = pieChartg.getPaint(Chart.PAINT_HOLE);
//**************************************************************


//***********************************************************

//***************************************************************************

//
//        ArrayList<String> labels=new ArrayList<>();
//        labels.add("ok");
//        labels.add("ok");
//        labels.add("ok");
//        labels.add("ok");
//        labels.add("ok");
//        ArrayList<ArrayList<Integer>> values=new ArrayList<>();
//        ArrayList<Integer> values1=new ArrayList<>();
//        values1.add(15);
//        values1.add(10);
//        values1.add(11);
//        values1.add(13);
//        values1.add(22);
//        ArrayList<Integer> values2=new ArrayList<>();
//        values2.add(44);
//        values2.add(33);
//        values2.add(25);
//        values2.add(13);
//        values2.add(22);
//        ArrayList<Integer> values3=new ArrayList<>();
//        values3.add(58);
//        values3.add(43);
//        values3.add(36);
//        values3.add(13);
//        values3.add(22);
//        ArrayList<Integer> values4=new ArrayList<>();
//        values4.add(54);
//        values4.add(33);
//        values4.add(75);
//        values4.add(13);
//        values4.add(12);
//        values.add(values1);
//        values.add(values2);
//        values.add(values3);
//        values.add(values4);
//
//        lineView.setBottomTextList(labels);
//        lineView.setDrawDotLine(false); //optional
//        lineView.setShowPopup(LineView.SHOW_POPUPS_All); //optional
//
//        lineView.setColorArray(new int[]{Color.BLACK,Color.GREEN,Color.GRAY,Color.CYAN});
//        lineView.setDataList(values); //or lineView.setFloatDataList(floatDataLists)
//
//
//
//


        //*******************************************************************************************Filrer
        itemsSite = new ArrayList<>();
        itemsPlant = new ArrayList();
        itemsDep = new ArrayList<>();
        itemsState = new ArrayList<>();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_dash);
        navigationView.setNavigationItemSelectedListener(this);


        machineDB.open();

        // System.out.println("afficheeeee"+machines.get(0));




        itemsState.add("");
        itemsState.add("Danger");
        itemsState.add("Alarm");
        itemsState.add("Warning");
        itemsState.add("Normal");
        itemsState.add("Undefined");


        itemsPlant = machineDB.getMachinesPlants();
        itemsSite = machineDB.getMachinesSite();

        sp = (Spinner) navigationView.getMenu().findItem(R.id.nav_plant).getActionView();
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.spinner_element,R.id.spinnerElement, itemsPlant); //selected item will look like a spinner set from XML

        sp.setAdapter(spinnerArrayAdapter);
        spDep = (Spinner) navigationView.getMenu().findItem(R.id.nav_departement).getActionView();
        spState = (Spinner) navigationView.getMenu().findItem(R.id.nav_state).getActionView();
        spSite = (Spinner) navigationView.getMenu().findItem(R.id.nav_site).getActionView();
        ArrayAdapter<String> spinnerArrayAdapterSite = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_element,R.id.spinnerElement, itemsSite); //selected item will look like a spinner set from XML

        spSite.setAdapter(spinnerArrayAdapterSite);
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(LoginActivity.adresseLocal).addConverterFactory(GsonConverterFactory.create())
                        .build();
                APIService service = retrofit.create(APIService.class);
                Call<List<Departement>> call = service.getDeps(pf.getString("ip",""),pf.getString("login",""),pf.getString("password",""),pf.getString("database",""),""+itemsPlant.get(position));
                call.enqueue(new Callback<List<Departement>>() {
                    @Override
                    public void onResponse(Response<List<Departement>> response, Retrofit retrofit) {

                        itemsDep.clear();

                        if(!(response.body() == null)){
                            for (Departement d : response.body()
                                    ) {
                                itemsDep.add(d.getDepartementName());
                            }
                            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_element,R.id.spinnerElement, itemsDep); //selected item will look like a spinner set from XML

                            spDep.setAdapter(spinnerArrayAdapter);
                        }


                    }

                    @Override
                    public void onFailure(Throwable t) {
                        System.out.println("failre");

                       // getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        ArrayAdapter<String> spinnStateAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_element,R.id.spinnerElement, itemsState); //selected item will look like a spinner set from XML

        spState.setAdapter(spinnStateAdapter);

        spState.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                MachineDB machineDB;
                machineDB = new MachineDB(getBaseContext());
                machineDB.open();

                switch(itemsState.get(i)){

                    case "Danger":
                        System.out.println("Danger");
                    case "Alarm": System.out.println("Alarm");break;
                    case "Warning" : System.out.println("Warning");break;
                    case "Normal":System.out.println("Nooomal");break;
                    case "Undefined": System.out.println("Undefined"); break;
                    default: System.out.println("-");break;
                }
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.activity_dashboard);
                drawer.closeDrawer(GravityCompat.START);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        spDep.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                MachineDB machineDB;
                machineDB = new MachineDB(getBaseContext());
                machineDB.open();
                System.out.println("deeeeeep---");
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.activity_dashboard);
                drawer.closeDrawer(GravityCompat.START);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });





        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.activity_dashboard);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();



    }

    @Override
    public void onBackPressed() {
        System.out.println("loooool");
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.activity_dashboard);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        System.out.println("jaajajajaj");
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if(id == R.id.nav_setting){
            pf.edit().clear().commit();
            Intent i = new Intent(this,LoginActivity.class);
            startActivity(i);
            finish();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.activity_dashboard);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }




    public void AssetsCriticalityMonth(View v) {

        OkHttpClient client = new OkHttpClient();
        client.setConnectTimeout(5, TimeUnit.MINUTES); // connect timeout
        client.setReadTimeout(5, TimeUnit.MINUTES);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(LoginActivity.adresseLocal).addConverterFactory(GsonConverterFactory.create()).client(client)
                .build();
        APIService service = retrofit.create(APIService.class);
        Call<List<AssetsCr>> call = service.getAssetsMonth(pf.getString("ip", ""), pf.getString("login", ""), pf.getString("password", ""), pf.getString("database", ""));
        call.enqueue(new Callback<List<AssetsCr>>() {
            @Override
            public void onResponse(Response<List<AssetsCr>> response, Retrofit retrofit) {


                ArrayList<ArrayList<Integer>> values = new ArrayList<>();
                ArrayList<String> labels = new ArrayList<>();
                ArrayList<Integer> values1 = new ArrayList<>();
                ArrayList<Integer> values2 = new ArrayList<>();
                ArrayList<Integer> values3 = new ArrayList<>();
                ArrayList<Integer> values4 = new ArrayList<>();
                ArrayList<Integer> values5 = new ArrayList<>();
                for (AssetsCr o : response.body()) {
                    labels.add(o.OperationYear + "");
                    values1.add(o.Good);
                    values2.add(o.Alarm);
                    values3.add(o.PreAlarm);
                    values4.add(o.Danger);
                    values5.add(o.NA);


                }


                values.add(values1);
                values.add(values2);
                values.add(values3);
                values.add(values4);

                lineView.setBottomTextList(labels);
                lineView.setDrawDotLine(false); //optional
                lineView.setShowPopup(LineView.SHOW_POPUPS_MAXMIN_ONLY); //optional
                lineView.setColorArray(new int[]{Color.BLACK, Color.GREEN, Color.GRAY, Color.CYAN});
                lineView.setDataList(values); //or lineView.setFloatDataList(floatDataLists)


            }


            @Override
            public void onFailure(Throwable t) {
                System.out.println("failre" + t);

                //  getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

            }

        });
    }







    public void AssetsCriticalityYear(View v) {

        OkHttpClient client = new OkHttpClient();
        client.setConnectTimeout(5, TimeUnit.MINUTES); // connect timeout
        client.setReadTimeout(5, TimeUnit.MINUTES);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(LoginActivity.adresseLocal).addConverterFactory(GsonConverterFactory.create()).client(client)
                .build();
        APIService service = retrofit.create(APIService.class);
        Call<List<AssetsCr>> call = service.getAssetsYear(pf.getString("ip", ""), pf.getString("login", ""), pf.getString("password", ""), pf.getString("database", ""));
        call.enqueue(new Callback<List<AssetsCr>>() {
            @Override
            public void onResponse(Response<List<AssetsCr>> response, Retrofit retrofit) {


                ArrayList<ArrayList<Integer>> values = new ArrayList<>();
                ArrayList<String> labels = new ArrayList<>();
                ArrayList<Integer> values1 = new ArrayList<>();
                ArrayList<Integer> values2 = new ArrayList<>();
                ArrayList<Integer> values3 = new ArrayList<>();
                ArrayList<Integer> values4 = new ArrayList<>();
                ArrayList<Integer> values5 = new ArrayList<>();
                for (AssetsCr o : response.body()) {
                    labels.add(o.OperationYear + "");
                    values1.add(o.Good);
                    values2.add(o.Alarm);
                    values3.add(o.PreAlarm);
                    values4.add(o.Danger);
                    values5.add(o.NA);


                }


                values.add(values1);
                values.add(values2);
                values.add(values3);
                values.add(values4);

                lineView.setBottomTextList(labels);
                lineView.setDrawDotLine(false); //optional
                lineView.setShowPopup(LineView.SHOW_POPUPS_MAXMIN_ONLY); //optional
                lineView.setColorArray(new int[]{Color.BLACK, Color.GREEN, Color.GRAY, Color.CYAN});
                lineView.setDataList(values); //or lineView.setFloatDataList(floatDataLists)


            }


            @Override
            public void onFailure(Throwable t) {
                System.out.println("failre" + t);

                //  getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

            }

        });
    }
    public void gomachine(View v){
        Intent i = new Intent(this,MainActivity2.class);
        startActivity(i);

    }

}











