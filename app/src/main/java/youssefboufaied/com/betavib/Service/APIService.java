/*
 * Created by Shaon on 8/15/16 8:06 PM
 * Copyright (c) 2016. This is free to use in any software.
 * You must provide developer name on your project.
 */

package youssefboufaied.com.betavib.Service;


import java.util.List;


import retrofit.Call;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import youssefboufaied.com.betavib.Entity.AssetsCr;
import youssefboufaied.com.betavib.Entity.DataBase;
import youssefboufaied.com.betavib.Entity.Departement;
import youssefboufaied.com.betavib.Entity.DetailMachine;
import youssefboufaied.com.betavib.Entity.Limit;
import youssefboufaied.com.betavib.Entity.Machine;
import youssefboufaied.com.betavib.Entity.Measurement;
import youssefboufaied.com.betavib.Entity.OverAll;
import youssefboufaied.com.betavib.Entity.Plant;
import youssefboufaied.com.betavib.Entity.Point;
import youssefboufaied.com.betavib.Entity.SelectMachine;
import youssefboufaied.com.betavib.Entity.User;

/**
 * Created by Shaon on 8/15/2016.
 */
public interface APIService {
    @GET("/betavib/databases")
    Call<List<DataBase>>  getDataBase();
    @GET("/betavib/selectmachine/{server}/{user}/{password}/{database}/{nameMachine}")
    Call<List<SelectMachine>>  getSelectMachine(@Path("server") String server,@Path("user") String user,@Path("password") String password,@Path("database") String database,@Path("nameMachine") String nameMachine);
    @GET("/betavib/machines/{serveur}/{user}/{password}/{database}")
    Call<List<Machine>>  getAllMachines(@Path("serveur") String serveur,@Path("user") String user,@Path("password") String password,@Path("database") String database);
    @POST("/connect")
    @FormUrlEncoded
    Call<User> connect(@Field("server") String ipaddress,
                        @Field("user") String name,
                        @Field("password") String password,
                       @Field("database") String database);

    @GET("/betavib/selectpoint/{server}/{user}/{password}/{database}/{MachineID}/{PointNumber}/{Dir}")
    Call<List<Point>>  getPoints(@Path("server") String serveur, @Path("user") String user, @Path("password") String password, @Path("database") String database, @Path("MachineID") String MachineID, @Path("PointNumber") String PointNumber, @Path("Dir") String Dir);

    @GET("betavib/Plant/{server}/{user}/{password}/{database}")
   Call<List<Plant>> getPlants(@Path("server") String serveur, @Path("user") String user, @Path("password") String password, @Path("database") String database);


    @GET("betavib/Dep/{server}/{user}/{password}/{database}/{plant}")
    Call<List<Departement>> getDeps(@Path("server") String serveur, @Path("user") String user, @Path("password") String password, @Path("database") String database,@Path("plant") String plantName);
    @GET("/betavib/alarmes/{server}/{user}/{password}/{database}/{MachineID}/{PointNumber}/{Dir}")
    Call<List<Limit>>  getLimit(@Path("server") String serveur, @Path("user") String user, @Path("password") String password, @Path("database") String database, @Path("MachineID") String MachineID, @Path("PointNumber") String PointNumber, @Path("Dir") String Dir);
    @GET("/betavib/infomachine/{server}/{user}/{password}/{database}/{MachineName}")
    Call<List<DetailMachine>>  getDetail(@Path("server") String serveur, @Path("user") String user, @Path("password") String password, @Path("database") String database, @Path("MachineName") String Machinename);
    @GET("/betavib/OverYearOvelo/{server}/{user}/{password}/{database}")
    Call<List<OverAll>>  getOverYearOvelo(@Path("server") String serveur, @Path("user") String user, @Path("password") String password, @Path("database") String database);
    @GET("/betavib/Measurements/{server}/{user}/{password}/{database}")
    Call<List<Measurement>>  getMeasurementsYear(@Path("server") String serveur, @Path("user") String user, @Path("password") String password, @Path("database") String database);
    @GET("/betavib/AssetsCrYear/{server}/{user}/{password}/{database}")
    Call<List<AssetsCr>>  getAssetsYear(@Path("server") String serveur, @Path("user") String user, @Path("password") String password, @Path("database") String database);
    @GET("/betavib/AssetsCrMonth/{server}/{user}/{password}/{database}")
    Call<List<AssetsCr>>  getAssetsMonth(@Path("server") String serveur, @Path("user") String user, @Path("password") String password, @Path("database") String database);
}
