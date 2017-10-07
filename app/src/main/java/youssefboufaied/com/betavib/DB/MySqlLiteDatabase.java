package youssefboufaied.com.betavib.DB;

/**
 * Created by bof on 12/10/2016.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import youssefboufaied.com.betavib.Entity.Machine;

import  static  youssefboufaied.com.betavib.DB.MachineDB.TABLE_Machine;


public class MySqlLiteDatabase extends SQLiteOpenHelper {
   private static final String CREATE_BDD_MACHINE = "CREATE TABLE " + TABLE_Machine + " ("
           + MachineDB.COL_STATEID + ", " + MachineDB.COL_DEPNAME + " TEXT NOT NULL, "
           + MachineDB.COL_SITENAME + " TEXT NOT NULL," + MachineDB.COL_PLANTNAME+ " TEXT NOT NULL," + MachineDB.COL_MACHINENAME +
           " TEXT NOT NULL,"+ MachineDB.COL_MACHINESTATE +" TEXT NOT NULL);";


    /*
   protected static final String TABLE_PANIER = "table_panier";
   protected static final String COL_ID = "ID";
   private static final int NUM_COL_ID = 0;
   protected static final String COL_NUMTABLE= "table";
   private static final int NUM_COL_NUMTABLE = 1;
   protected static final String COL_PRODUIT = "produit";
   private static final int NUM_COL_PRODUIT = 2;
   protected static final String COL_QUANTITE = "quantite";
   private static final int NUM_COL_QUANTITE = 3;
*/



   public MySqlLiteDatabase(Context context, String name, CursorFactory factory, int version) {
       super(context, name, factory, version);
       System.out.println(CREATE_BDD_MACHINE+" categoryokokok ");
       System.out.println("versssiiiiiiioon"+version);
   }

   @Override
   public void onCreate(SQLiteDatabase db) {

       db.execSQL(CREATE_BDD_MACHINE);

   }

   @Override
   public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

       db.execSQL("DROP TABLE " + TABLE_Machine + ";");
       System.out.println(oldVersion+" new "+newVersion);
       onCreate(db);

   }
}
