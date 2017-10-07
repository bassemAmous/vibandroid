package youssefboufaied.com.betavib;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.sdsmdg.tastytoast.TastyToast;
import com.squareup.okhttp.OkHttpClient;
import com.wang.avi.AVLoadingIndicatorView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;
import youssefboufaied.com.betavib.Adapters.SelectedMachineAdapter;
import youssefboufaied.com.betavib.Entity.DetailMachine;
import youssefboufaied.com.betavib.Entity.Limit;
import youssefboufaied.com.betavib.Entity.Machine;
import youssefboufaied.com.betavib.Entity.SelectMachine;
import youssefboufaied.com.betavib.Service.APIService;
import youssefboufaied.com.betavib.connection.connection;

import static android.R.id.list;

public class ScrollingActivity extends AppCompatActivity {
    final Random rnd = new Random();
    SharedPreferences pf;
    static List<SelectMachine> selectedMachines;
  public static GridView lv;
    static SelectedMachineAdapter adapter;
    AVLoadingIndicatorView avi;
    TextView installed,powerTxt,inputSpeedTxt,pointnb;

   List<DetailMachine> detailMachines;

     Machine m;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_scrolling);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        if(!connection.isOnline(this.getApplicationContext()))
            TastyToast.makeText(getApplicationContext(), " Check your connection", TastyToast.LENGTH_LONG, TastyToast.ERROR);
        powerTxt=(TextView) findViewById(R.id.power);
        inputSpeedTxt=(TextView) findViewById(R.id.inputspeed);
        pointnb=(TextView) findViewById(R.id.pointnb);
        installed=(TextView) findViewById(R.id.installed);
        CollapsingToolbarLayout toolbar = (CollapsingToolbarLayout ) findViewById(R.id.toolbar_layout);



        final ImageView image = (ImageView) findViewById(R.id.imageRandom);
        final String str = "slide"+(rnd.nextInt(11)+1);
        image.setImageDrawable
                (
                        getResources().getDrawable(getResourceID(str, "drawable",
                                this))
                );


         m =(Machine) getIntent().getSerializableExtra("machine");



                toolbar.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar);
        toolbar.setExpandedTitleTextAppearance(R.style.ExpandedAppBar);
        toolbar.setTitle(m.getMachineName());
        pf = getApplicationContext().getSharedPreferences("pref", MODE_PRIVATE);
        lv= (GridView) findViewById(R.id.listviewselectedmachine);
        avi=(AVLoadingIndicatorView)findViewById(R.id.loaaderselectmachine);
        avi.show();
        try {
            OkHttpClient client = new OkHttpClient();
            client.setConnectTimeout(5, TimeUnit.MINUTES); // connect timeout
            client.setReadTimeout(5, TimeUnit.MINUTES);
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(LoginActivity.adresseLocal).addConverterFactory(GsonConverterFactory.create()).client(client)
                    .build();
            APIService service = retrofit.create(APIService.class);


            Call<List<SelectMachine>> call = service.getSelectMachine(pf.getString("ip",""),pf.getString("login",""),pf.getString("password",""),pf.getString("database",""),m.getMachineName());
            call.enqueue(new Callback<List<SelectMachine>>() {
                @Override
                public void onResponse(Response<List<SelectMachine>> response, Retrofit retrofit) {



                    selectedMachines = response.body();


                    pointnb.setText("Points :" +selectedMachines.size());

                    for (SelectMachine smm:selectedMachines
                         ) {


                    }
                    System.out.println(selectedMachines.size());
                    ArrayList<SelectMachine>selectMachiness=skipLinnes();
                    for (SelectMachine smm:selectMachiness
                            ) {
                        if(!smm.getPointDirName().equals("fergha"))
                        System.out.println(smm+"ok");

                    }




                    System.out.println( selectMachiness.size());
                    adapter = new SelectedMachineAdapter(getApplicationContext(),R.layout.machine_grid2, selectMachiness);


                    lv.setAdapter(adapter);
                    lv.setNumColumns(3);

                    lv.setOnTouchListener(new View.OnTouchListener() {

                        @Override
                        public boolean onTouch(View v, MotionEvent event) {
                            NestedScrollView scrollview = (NestedScrollView) findViewById(R.id.scroool);
                            scrollview.setNestedScrollingEnabled(false);
                            v.getParent().requestDisallowInterceptTouchEvent(true);

                            return false;
                        }
                    });







                    //***************************



                    OkHttpClient client = new OkHttpClient();
                    client.setConnectTimeout(5, TimeUnit.MINUTES); // connect timeout
                    client.setReadTimeout(5, TimeUnit.MINUTES);
                    retrofit = new Retrofit.Builder()
                            .baseUrl(LoginActivity.adresseLocal).addConverterFactory(GsonConverterFactory.create()).client(client)
                            .build();
                    APIService service = retrofit.create(APIService.class);

                    Call<List<DetailMachine>> call = service.getDetail(pf.getString("ip",""),pf.getString("login",""),pf.getString("password",""),pf.getString("database",""),m.getMachineName());
                    call.enqueue(new Callback<List<DetailMachine>>() {
                        @Override
                        public void onResponse(Response<List<DetailMachine>> response, Retrofit retrofit) {



                                detailMachines=response.body();



                            if(response.body() != null) {
                                detailMachines.get(0);
                                powerTxt.setText("Power : "+detailMachines.get(0).getPower() );
                                inputSpeedTxt.setText("Speed : "+detailMachines.get(0).getInputSpeed() );
//                                installed.setText("installed :"+detailMachines.get(0).getInstallationDate());
                            }
                            avi.hide();
                            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                        }

                        @Override
                        public void onFailure(Throwable t) {
                            System.out.println("no");
                            System.out.println(t.getCause()+" "+t.getMessage()+" "+t.getStackTrace()+t.toString());
                            avi.hide();
                            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                        }
                    });



                //*****************************






                }
                @Override
                public void onFailure(Throwable t) {
                    avi.hide();
                    getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                    System.out.println("failure");


                }
            });
        } catch (Exception e) {
            Log.d("onResponse", "There is an error");
            e.printStackTrace();
        }


    }

    protected final static int getResourceID
            (final String resName, final String resType, final Context ctx)
    {
        final int ResourceID =
                ctx.getResources().getIdentifier(resName, resType,
                        ctx.getApplicationInfo().packageName);
        if (ResourceID == 0)
        {
            throw new IllegalArgumentException
                    (
                            "No resource string found with name " + resName
                    );
        }
        else
        {
            return ResourceID;
        }
    }
    public ArrayList<SelectMachine> skipLinnes(){
    ArrayList<SelectMachine> sms=new ArrayList<>();
        int j=1;
        //sms.add(selectedMachines.get(0));
        char firstSm=selectedMachines.get(0).getPointDirName().charAt(0);
                for(int i=0;i<selectedMachines.size()-1;i++){

                    sms.add(selectedMachines.get(i));

                        if (firstSm == selectedMachines.get(i + 1).getPointDirName().charAt(0))
                            j++;
                        else {
                            if (j <= 3)
                                j = 3 - j;
                            else
                                j = j % 3;
                            for (int k = 0; k < j; k++) {
                                sms.add(new SelectMachine("fergha"));
                            }
                            j = 1;
                            firstSm = selectedMachines.get(i+1).getPointDirName().charAt(0);
                        }
                    }
        sms.add(selectedMachines.get(selectedMachines.size()-1));
return sms;
                }































    }




