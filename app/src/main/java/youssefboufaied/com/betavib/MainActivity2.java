package youssefboufaied.com.betavib;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.sdsmdg.tastytoast.TastyToast;
import com.squareup.okhttp.OkHttpClient;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;
import youssefboufaied.com.betavib.Adapters.MachineAdapter;
import youssefboufaied.com.betavib.DB.MachineDB;
import youssefboufaied.com.betavib.Entity.DataBase;
import youssefboufaied.com.betavib.Entity.Departement;
import youssefboufaied.com.betavib.Entity.Machine;
import youssefboufaied.com.betavib.Entity.Plant;
import youssefboufaied.com.betavib.Service.APIService;
import youssefboufaied.com.betavib.connection.connection;

public class MainActivity2 extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private List itemsPlant;
    private List<String> itemsDep;
    private List<String> itemsState;
    private List<String> itemsSite;
    SharedPreferences pf;
    static List<Machine> machines;
    static List<Machine> machines2;
    ListView gv;
    static MachineAdapter adapter;
    AVLoadingIndicatorView avi;
    ArrayAdapter<String> adapterPlantSpinner;
    SearchView sv;
    Spinner sp;
    Spinner spDep;
    Spinner spState ;
    Spinner spSite;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        pf = getApplicationContext().getSharedPreferences("pref", MODE_PRIVATE);
        if(!connection.isOnline(this.getApplicationContext()))
            TastyToast.makeText(getApplicationContext(), " Check your connection", TastyToast.LENGTH_LONG, TastyToast.ERROR);
        gv= (ListView) findViewById(R.id.gridView);
        avi=(AVLoadingIndicatorView)findViewById(R.id.loaader);
        sv=(SearchView)findViewById(R.id.recheche) ;
        avi.show();
        itemsSite = new ArrayList<>();
        itemsPlant = new ArrayList();
        itemsDep = new ArrayList<>();
        itemsState = new ArrayList<>();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

setTitle("VibWorks-"+pf.getString("database",""));


            sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {

                        callSearch(query);
                        MachineDB machineDB;
                        machineDB = new MachineDB(getBaseContext());
                        machineDB.open();
                        machines2 = machineDB.getAllMachineLike(query, convertMachinesToNames());
                        adapter = new MachineAdapter(getApplicationContext(), R.layout.machine_grid, machines2);
                        adapter.notifyDataSetChanged();
                        gv.setAdapter(adapter);

                    return true;
                }

                @Override
                public boolean onQueryTextChange(String query) {


                    callSearch(query);
                    MachineDB machineDB;
                    machineDB = new MachineDB(getBaseContext());
                    machineDB.open();

                    machines2 = machineDB.getAllMachineLike(query,convertMachinesToNames());
                    adapter = new MachineAdapter(getApplicationContext(), R.layout.machine_grid, machines2);
                    adapter.notifyDataSetChanged();
                    gv.setAdapter(adapter);
                    getWindow().setSoftInputMode(
                            WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
                    return true;
                }

                public void callSearch(String query) {
                    //Do searching
                }
            });




        MachineDB machineDB=new MachineDB(getApplicationContext());
        machineDB.open();
        machines = machineDB.getAllMachine();
        // System.out.println("afficheeeee"+machines.get(0));
        adapter = new MachineAdapter(getApplicationContext(),R.layout.machine_grid, machines);

        gv.setAdapter(adapter);

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

                avi.show();
                getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
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

                        avi.hide();
                        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        System.out.println("failre");
                        avi.hide();
                        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

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

                    case "Danger": machines=machineDB.getAllMachineWithState("Red");break;
                    case "Alarm": machines=machineDB.getAllMachineWithState("Orange");break;
                    case "Warning" : machines=machineDB.getAllMachineWithState("Yellow");break;
                    case "Normal": machines=machineDB.getAllMachineWithState("Green");break;
                    case "Undefined": machines=machineDB.getAllMachineWithState("Gray"); break;
                    default: machines=machineDB.getAllMachineWithState("");break;
                }
                adapter = new MachineAdapter(getApplicationContext(),R.layout.machine_grid, machines);
                adapter.notifyDataSetChanged();
                gv.setAdapter(adapter);
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
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



        machines=machineDB.getAllMachineWithDep(itemsDep.get(i));
        adapter = new MachineAdapter(getApplicationContext(),R.layout.machine_grid, machines);
        adapter.notifyDataSetChanged();
        gv.setAdapter(adapter);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
});




        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();



    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
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
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public  String convertMachinesToNames(){




        String machineNames="";
       for(int i=0;i<machines.size();i++)
       {
           if(i!=machines.size()-1)
           machineNames+="'"+machines.get(i).getMachineName()+"'"+",";
           else
               machineNames+="'"+machines.get(i).getMachineName()+"'";
       }

        machineNames= "( "+machineNames+" )";
        return machineNames;
    }
}
