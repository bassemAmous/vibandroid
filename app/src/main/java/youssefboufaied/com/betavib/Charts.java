package youssefboufaied.com.betavib;

import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;


import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.sdsmdg.tastytoast.TastyToast;
import com.squareup.okhttp.OkHttpClient;
import com.wang.avi.AVLoadingIndicatorView;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;
import youssefboufaied.com.betavib.Adapters.ChartsAdapter;
import youssefboufaied.com.betavib.Entity.Limit;
import youssefboufaied.com.betavib.Entity.Point;
import youssefboufaied.com.betavib.Entity.SelectMachine;
import youssefboufaied.com.betavib.Service.APIService;
import youssefboufaied.com.betavib.connection.connection;

import java.util.ArrayList;
public class Charts extends AppCompatActivity {
    static List<Point> points;
    static List<Limit> limits;
    ChartsAdapter adapter;
    ListView lv;
    AVLoadingIndicatorView avi;
    SharedPreferences pf;
    public   static ArrayList<String> measdate = new ArrayList<>();
    public   static ArrayList<Entry> peak = new ArrayList<>();
    public   static ArrayList<Entry> rms = new ArrayList<>();
    public   static ArrayList<Entry> ku = new ArrayList<>();
    public   static ArrayList<Entry> cf = new ArrayList<>();
    public   static ArrayList<Entry> ovelo = new ArrayList<>();
    public   static  ArrayList<Entry> b1 = new ArrayList<>();
    public   static  ArrayList<Entry> b2 = new ArrayList<>();
    public   static  ArrayList<Entry> b3 = new ArrayList<>();
    public   static ArrayList<Entry> b4 = new ArrayList<>();
    public   static ArrayList<Entry> b5 = new ArrayList<>();
    public   static ArrayList<Entry> b6 = new ArrayList<>();
    public   static ArrayList<Entry> speed = new ArrayList<>();

    public   static float refpeak;
    public   static float refrms;
    public   static float refku;
    public   static float refcf;
    public   static float refovelo;
    public   static float refb1;
    public  static float refb2;
    public  static float refb3;
    public  static float refb4;
    public static float refb5;
    public  static float refb6;
    public static float pralpeak;
    public static float pralrms;
    public static float pralku;
    public static float pralcf;
    public static float pralovelo;
    public static float pralb1;
    public static float pralb2;
    public static float pralb3;
    public static float pralb4;
    public static float pralb5;
    public static float pralb6;


    public static float alpeak;
    public static float alrms;
    public static float alku;
    public static float alcf;
    public static float alovelo;
    public static float alb1;
    public static float alb2;
    public static float alb3;
    public static float alb4;
    public static float alb5;
    public static float alb6;
    List lineCharts;
    public static float dgpeak;
    public static float dgrms;
    public static float dgku;
    public static float dgcf;
    public static float dgovelo;
    public static float dgb1;
    public static float dgb2;
    public static float dgb3;
    public static float dgb4;
    public static float dgb5;
    public static float dgb6;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charts);
        pf = getApplicationContext().getSharedPreferences("pref", MODE_PRIVATE);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        if(!connection.isOnline(this.getApplicationContext()))
            TastyToast.makeText(getApplicationContext(), " Check your connection", TastyToast.LENGTH_LONG, TastyToast.ERROR);
        final SelectMachine sm = (SelectMachine) getIntent().getSerializableExtra("selectMachine");

        setTitle(sm.getMachineName() + " " + sm.getPointDirName());
        measdate.clear();
        peak.clear();
        rms.clear();
        cf.clear();
        ku.clear();
        ovelo.clear();
        b1.clear();
        b2.clear();
        b3.clear();
        b4.clear();
        b5.clear();
        b6.clear();
        speed.clear();



        lv = (ListView) findViewById(R.id.lvchart);
        avi = (AVLoadingIndicatorView) findViewById(R.id.loadercharts);
        avi.show();
        try {

            OkHttpClient client = new OkHttpClient();
            client.setConnectTimeout(5, TimeUnit.MINUTES); // connect timeout
            client.setReadTimeout(5, TimeUnit.MINUTES);
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(LoginActivity.adresseLocal).addConverterFactory(GsonConverterFactory.create()).client(client)
                    .build();
            APIService service = retrofit.create(APIService.class);
            Call<List<Point>> call = service.getPoints(pf.getString("ip", ""), pf.getString("login", ""), pf.getString("password", ""), pf.getString("database", ""), sm.getMachineID(), sm.getPointNumber(), sm.getDir());
            call.enqueue(new Callback<List<Point>>() {
                @Override
                public void onResponse(Response<List<Point>> response, Retrofit retrofit) {

                    System.out.println("response" + "" + response.code());
                    Gson gson = new Gson();
                    TypeAdapter<Point> adapterr = gson.getAdapter(Point.class);
                    try {
                        Point point;

                        if (response.errorBody() != null)
                            point =
                                    adapterr.fromJson(
                                            response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                    points = response.body();
try{
//                    if (points.size() <= 3) {
//                        measdate.add("-");
//                        measdate.add("-");
//                        measdate.add("-");
//                        rms.add(new Entry(0, 0));
//                        ku.add(new Entry(0, 0));
//                        peak.add(new Entry(0, 0));
//                        cf.add(new Entry(0, 0));
//                        ovelo.add(new Entry(0, 0));
//                        b1.add(new Entry(0, 0));
//                        b2.add(new Entry(0, 0));
//                        b3.add(new Entry(0, 0));
//                        b4.add(new Entry(0, 0));
//                        b5.add(new Entry(0, 0));
//                        b6.add(new Entry(0, 0));
//                        speed.add(new Entry(0, 0));
//                        rms.add(new Entry(0, 0));
//                        ku.add(new Entry(0, 0));
//                        peak.add(new Entry(0, 0));
//                        cf.add(new Entry(0, 0));
//                        ovelo.add(new Entry(0, 0));
//                        b1.add(new Entry(0, 0));
//                        b2.add(new Entry(0, 0));
//                        b3.add(new Entry(0, 0));
//                        b4.add(new Entry(0, 0));
//                        b5.add(new Entry(0, 0));
//                        b6.add(new Entry(0, 0));
//                        speed.add(new Entry(0, 0));
//                        rms.add(new Entry(0, 0));
//                        ku.add(new Entry(0, 0));
//                        peak.add(new Entry(0, 0));
//                        cf.add(new Entry(0, 0));
//                        ovelo.add(new Entry(0, 0));
//                        b1.add(new Entry(0, 0));
//                        b2.add(new Entry(0, 0));
//                        b3.add(new Entry(0, 0));
//                        b4.add(new Entry(0, 0));
//                        b5.add(new Entry(0, 0));
//                        b6.add(new Entry(0, 0));
//                        speed.add(new Entry(0, 0));
//                        int i = 0;
//                        for (Point p : response.body()) {
//
//                            i++;
//                            System.out.println(p.getRms());
//                            System.out.println(p.getPeak());
//                            measdate.add(p.getMeasdate().substring(0, Math.min(p.getMeasdate().length(), 7)));
//                            rms.add(new Entry(p.getRms(), i));
//                            ku.add(new Entry(p.getKu(), i));
//                            peak.add(new Entry(p.getPeak(), i));
//                            cf.add(new Entry(p.getCf(), i));
//                            ovelo.add(new Entry(p.getOvelo(), i));
//                            b1.add(new Entry(p.getB1(), i));
//                            b2.add(new Entry(p.getB2(), i));
//                            b3.add(new Entry(p.getB3(), i));
//                            b4.add(new Entry(p.getB4(), i));
//                            b5.add(new Entry(p.getB5(), i));
//                            b6.add(new Entry(p.getB6(), i));
//                            speed.add(new Entry(p.getSpeed(), i));
//
//                        }
//
//
//                    } else {
                        int i = 0;
                        for (Point p : response.body()) {

                            System.out.println(p.getRms());
                            System.out.println(p.getPeak());
                            measdate.add(p.getMeasdate().substring(0, Math.min(p.getMeasdate().length(), 10)));
                            rms.add(new Entry(p.getRms(), i));
                            ku.add(new Entry(p.getKu(), i));
                            peak.add(new Entry(p.getPeak(), i));
                            cf.add(new Entry(p.getCf(), i));
                            ovelo.add(new Entry(p.getOvelo(), i));
                            b1.add(new Entry(p.getB1(), i));
                            b2.add(new Entry(p.getB2(), i));
                            b3.add(new Entry(p.getB3(), i));
                            b4.add(new Entry(p.getB4(), i));
                            b5.add(new Entry(p.getB5(), i));
                            b6.add(new Entry(p.getB6(), i));
                            speed.add(new Entry(p.getSpeed(), i));
                            i++;

                        }



} catch (Exception e) {
    System.out.println("EXCEEPTION" + e);
}

                    LineChart lineChart = null;


                     lineCharts = new ArrayList<LineChart>();
                    lineCharts.add(lineChart);
                    lineCharts.add(lineChart);
                    lineCharts.add(lineChart);
                    lineCharts.add(lineChart);
                    lineCharts.add(lineChart);
                    lineCharts.add(lineChart);
                    lineCharts.add(lineChart);
                    lineCharts.add(lineChart);
                    lineCharts.add(lineChart);
                    lineCharts.add(lineChart);
                    lineCharts.add(lineChart);
                    lineCharts.add(lineChart);


















                    OkHttpClient client = new OkHttpClient();
                    client.setConnectTimeout(5, TimeUnit.MINUTES); // connect timeout
                    client.setReadTimeout(5, TimeUnit.MINUTES);
                    retrofit = new Retrofit.Builder()
                            .baseUrl(LoginActivity.adresseLocal).addConverterFactory(GsonConverterFactory.create()).client(client)
                            .build();
                    APIService service = retrofit.create(APIService.class);
                    Call<List<Limit>> call1 = service.getLimit(pf.getString("ip", ""), pf.getString("login", ""), pf.getString("password", ""), pf.getString("database", ""), sm.getMachineID(), sm.getPointNumber(), sm.getDir());

                    call1.enqueue(new Callback<List<Limit>>() {
                        @Override
                        public void onResponse(Response<List<Limit>> response, Retrofit retrofit) {

                            System.out.println("response" + "" + response.code());
                            Gson gson = new Gson();
                            TypeAdapter<Limit> adapterrr = gson.getAdapter(Limit.class);
                            try {
                                Limit limit;

                                if (response.errorBody() != null)
                                    limit =
                                            adapterrr.fromJson(
                                                    response.errorBody().string());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }


                            limits = response.body();


                            for (Limit l : response.body()) {
                                System.out.println("ici peak"+l.getRefpeak());

                                refpeak=l.getRefpeak();
                                refb1=l.getRefb1();
                                refb2=l.getRefb2();
                                refb3=l.getRefb3();
                                refb4=l.getRefb5();
                                refb6=l.getRefb6();
                                refcf=l.getRefcf();
                                refrms=l.getRefrms();
                                refku=l.getRefku();
                                refovelo=l.getRefovelo();





                                pralpeak=l.getPralpeak();
                                pralb1=l.getPralb1();
                                pralb2=l.getPralb2();
                                pralb3=l.getPralb3();
                                pralb4=l.getPralb4();
                                pralb5=l.getPralb5();
                                pralb6=l.getPralb6();
                                pralcf=l.getRefcf();
                                pralrms=l.getPralrms();
                                pralku=l.getPralku();
                                pralovelo=l.getPralovelo();




                                alpeak=l.getAlpeak();
                                alb1=l.getAlb1();
                                alb2=l.getAlb2();
                                alb3=l.getAlb3();
                                alb4=l.getAlb4();
                                alb5=l.getAlb5();
                                alb6=l.getAlb6();
                                alcf=l.getAlcf();
                                alrms=l.getAlrms();
                                alku=l.getAlku();
                                alovelo=l.getAlovelo();




                                dgpeak=l.getDgpeak();
                                dgb1=l.getDgb1();
                                dgb2=l.getDgb2();
                                dgb3=l.getDgb3();
                                dgb4=l.getDgb4();
                                dgb5=l.getDgb5();
                                dgb6=l.getDgb6();
                                dgcf=l.getDgcf();
                                dgrms=l.getDgrms();
                                dgku=l.getDgku();
                                dgovelo=l.getDgovelo();


                            }
                            avi.hide();
                            adapter = new ChartsAdapter(getApplicationContext(), R.layout.chart_lv, lineCharts);
                            lv.setAdapter(adapter);

                        }






                        @Override
                        public void onFailure(Throwable t) {
                            //avi.hide();

                        }
                    });





















                }

                @Override
                public void onFailure(Throwable t) {
                    //avi.hide();
                    System.out.println("failure");

                }
            });
        } catch (Exception e) {
            Log.d("onResponse", "There is an error");
            e.printStackTrace();
        }


//
















    }


}



