package youssefboufaied.com.betavib;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;
import android.widget.ListView;

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
import youssefboufaied.com.betavib.Adapters.MachineAdapter;
import youssefboufaied.com.betavib.Adapters.SelectedMachineAdapter;
import youssefboufaied.com.betavib.Entity.Machine;
import youssefboufaied.com.betavib.Entity.SelectMachine;
import youssefboufaied.com.betavib.Service.APIService;
import youssefboufaied.com.betavib.connection.connection;

public class SelectedMachineActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_selected_machine);
        if(!connection.isOnline(this.getApplicationContext()))
            TastyToast.makeText(getApplicationContext(), " Check your connection", TastyToast.LENGTH_LONG, TastyToast.ERROR);

    }

}
