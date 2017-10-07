package youssefboufaied.com.betavib.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import youssefboufaied.com.betavib.Entity.Machine;
import youssefboufaied.com.betavib.Entity.Plant;

/**
 * Created by amous on 31/05/17.
 */

public class MachineDB {

    private static final int VERSION_BDD = 1;
    protected static final String NOM_BDDMachine = "machine.db";
    protected static final String TABLE_Machine= "table_machine";

    protected static final String COL_STATEID= "StateID";
    private static final int NUM_COL_STATEID = 0;
    protected static final String COL_DEPNAME = "DepName";
    private static final int NUM_COL_DEPNAME = 1;

    protected static final String COL_SITENAME = "SiteName";
    private static final int NUM_COL_SITENAME = 2;
    protected static final String COL_PLANTNAME = "PlantName";
    private static final int NUM_COL_PLANTNAME = 3;
    protected static final String COL_MACHINENAME= "MachineName";
    private static final int NUM_COL_MACHINENAME = 4;
    protected static final String COL_MACHINESTATE = "MachineState";
    private static final int NUM_COL_MACHINESTATE = 5;


    private SQLiteDatabase bdd;

    private MySqlLiteDatabase msld;
    private Context ctx;

    public MachineDB(Context context){
        //On crée la BDD et sa table
        ctx = context;
        msld = new MySqlLiteDatabase(context, NOM_BDDMachine, null, VERSION_BDD);
    }

    public void open(){
        //on ouvre la BDD en écriture
        bdd = msld.getWritableDatabase();
    }

    public void close(){
        //on ferme l'accès à la BDD
        bdd.close();
    }

    public SQLiteDatabase getBDD(){
        return bdd;
    }
    public void upgradeBDD(){ msld.onUpgrade(bdd,0,1);}
    public long insertMachine(Machine machine){
        //Création d'un ContentValues (fonctionne comme une HashMap)
        ContentValues values = new ContentValues();
        //on lui ajoute une valeur associée à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
        /*
            private String depName;

    private String StateID;

    private String SiteName;

    private String PlantName;

    private String MachineName;

    private String MachineState;
         */

        values.put(COL_STATEID,machine.getStateID());
        values.put(COL_DEPNAME,machine.getDepName());
        values.put(COL_SITENAME, machine.getSiteName());
        values.put(COL_PLANTNAME, machine.getPlantName());
        values.put(COL_MACHINENAME, machine.getMachineName());
        values.put(COL_MACHINESTATE, machine.getMachineState());


        //System.out.println("hahhahahahahah");

        //on insère l'objet dans la BDD via le ContentValues
        return bdd.insert(TABLE_Machine, null, values);
    }


    public int updateContact(int stateId,Machine machine){
        //La mise à jour d'un livre dans la BDD fonctionne plus ou moins comme une insertion
        //il faut simplement préciser quel livre on doit mettre à jour grâce à l'ID
        ContentValues values = new ContentValues();
        //values.put(COL_STATEID,machine.getStateID());
        values.put(COL_DEPNAME,machine.getDepName());
        values.put(COL_SITENAME, machine.getSiteName());
        values.put(COL_PLANTNAME, machine.getPlantName());
        values.put(COL_MACHINENAME, machine.getMachineName());
        values.put(COL_MACHINESTATE, machine.getMachineState());


        return bdd.update(TABLE_Machine, values, COL_STATEID + " = " +stateId, null);
    }


    public List<Machine> getAllMachine(){

        List<Machine> machineList = new ArrayList<Machine>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_Machine;


        Cursor cursor = bdd.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Machine machine = new Machine();
                machine.setDepName((cursor.getString(NUM_COL_DEPNAME)));
                machine.setMachineName(cursor.getString(NUM_COL_MACHINENAME));
                machine.setMachineState(cursor.getString(NUM_COL_MACHINESTATE));
                machine.setPlantName(cursor.getString(NUM_COL_PLANTNAME));
                machine.setSiteName(cursor.getString(NUM_COL_SITENAME));
                machine.setStateID(cursor.getString(NUM_COL_STATEID));


                System.out.println("hihihihihihihih");



                machineList.add(machine);
            } while (cursor.moveToNext());
        }

        // return contact list
        return machineList;
    }


      /*
      int i=0;
        String[] produitId={};

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                produitId[i]=cursor.getString(0);
     */






    //Cette méthode permet de convertir un cursor en un livre
    private Machine cursorToMachine(Cursor cursor){
        //si aucun élément n'a été retourné dans la requête, on renvoie null
        if (cursor.getCount() == 0)
            return null;
        cursor.moveToFirst();
        Machine machine = new Machine();
        machine.setDepName((cursor.getString(NUM_COL_DEPNAME)));
        machine.setMachineName(cursor.getString(NUM_COL_MACHINENAME));
        machine.setMachineState(cursor.getString(NUM_COL_MACHINESTATE));
        machine.setPlantName(cursor.getString(NUM_COL_PLANTNAME));
        machine.setSiteName(cursor.getString(NUM_COL_SITENAME));
        machine.setStateID(cursor.getString(NUM_COL_STATEID));

        cursor.close();
        return machine;
    }

    public List<Machine> getAllMachineLike(String query,String machineNames) {

        List<Machine> machineList = new ArrayList<Machine>();
        // Select All Query
        String selectQuery = "SELECT  * FROM "+ TABLE_Machine+" where "+COL_MACHINENAME+" LIKE '%"+query+"%' and "+COL_MACHINENAME+" in  "+machineNames ;
        try {
            Cursor cursor = bdd.rawQuery(selectQuery, null);
            if (cursor.moveToFirst()) {
                do {
                    Machine machine = new Machine();
                    machine.setDepName((cursor.getString(NUM_COL_DEPNAME)));
                    machine.setMachineName(cursor.getString(NUM_COL_MACHINENAME));
                    machine.setMachineState(cursor.getString(NUM_COL_MACHINESTATE));
                    machine.setPlantName(cursor.getString(NUM_COL_PLANTNAME));
                    machine.setSiteName(cursor.getString(NUM_COL_SITENAME));
                    machine.setStateID(cursor.getString(NUM_COL_STATEID));


                    //System.out.println(NUM_COL_IMAGE+"  "+cursor.getString(NUM_COL_IMAGE)+"  Prix :"+NUM_COL_PRIX+"  "+cursor.getString(NUM_COL_PRIX));

                    machineList.add(machine);
                } while (cursor.moveToNext());
            }
        }  catch (Exception e){
            Toast.makeText(ctx, "Semi-colons not allowed here", Toast.LENGTH_SHORT).show();
            System.out.println(e);

        }
        // looping through all rows and adding to list

       // System.out.println("ti haw el produit"+produitsList);
        // return contact list
        return machineList;


    }
    public List<Machine> getAllMachineWithDep(String query) {

        List<Machine> machineList = new ArrayList<Machine>();
        // Select All Query
        String selectQuery = "SELECT  * FROM "+ TABLE_Machine+" where "+COL_DEPNAME+" LIKE '%"+query+"%' " ;


        Cursor cursor = bdd.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Machine machine = new Machine();
                machine.setDepName((cursor.getString(NUM_COL_DEPNAME)));
                machine.setMachineName(cursor.getString(NUM_COL_MACHINENAME));
                machine.setMachineState(cursor.getString(NUM_COL_MACHINESTATE));
                machine.setPlantName(cursor.getString(NUM_COL_PLANTNAME));
                machine.setSiteName(cursor.getString(NUM_COL_SITENAME));
                machine.setStateID(cursor.getString(NUM_COL_STATEID));


                //System.out.println(NUM_COL_IMAGE+"  "+cursor.getString(NUM_COL_IMAGE)+"  Prix :"+NUM_COL_PRIX+"  "+cursor.getString(NUM_COL_PRIX));
                System.out.println("MACHIIINE"+machine.toString());
                machineList.add(machine);
            } while (cursor.moveToNext());

        }
        // System.out.println("ti haw el produit"+produitsList);
        // return contact list
        return machineList;


    }
    public List<Machine> getAllMachineWithState(String query) {

        List<Machine> machineList = new ArrayList<Machine>();
        // Select All Query
        String selectQuery = "SELECT  * FROM "+ TABLE_Machine+" where "+COL_MACHINESTATE+"  LIKE '%"+query+"%' " ;


        Cursor cursor = bdd.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Machine machine = new Machine();
                machine.setDepName((cursor.getString(NUM_COL_DEPNAME)));
                machine.setMachineName(cursor.getString(NUM_COL_MACHINENAME));
                machine.setMachineState(cursor.getString(NUM_COL_MACHINESTATE));
                machine.setPlantName(cursor.getString(NUM_COL_PLANTNAME));
                machine.setSiteName(cursor.getString(NUM_COL_SITENAME));
                machine.setStateID(cursor.getString(NUM_COL_STATEID));


                //System.out.println(NUM_COL_IMAGE+"  "+cursor.getString(NUM_COL_IMAGE)+"  Prix :"+NUM_COL_PRIX+"  "+cursor.getString(NUM_COL_PRIX));
                System.out.println("MACHIIINE"+machine.toString());
                machineList.add(machine);
            } while (cursor.moveToNext());

        }
        // System.out.println("ti haw el produit"+produitsList);
        // return contact list
        return machineList;


    }
    public List<String> getMachinesPlants(){
        List<String> plants = new ArrayList<String>();
        String selectQuery = "SELECT DISTINCT "+COL_PLANTNAME+" FROM "+ TABLE_Machine ;

        Cursor cursor = bdd.rawQuery(selectQuery, null);
        plants.add("");
        if (cursor.moveToFirst()) {
            do {

                plants.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        return plants;
    }

    public List<String> getMachinesStates(){
        List<String> plants = new ArrayList<String>();
        String selectQuery = "SELECT DISTINCT "+COL_MACHINESTATE+" FROM "+ TABLE_Machine ;

        Cursor cursor = bdd.rawQuery(selectQuery, null);
        plants.add("");
        if (cursor.moveToFirst()) {
            do {

                plants.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        return plants;
    }
    public List<String> getMachinesSite(){
        List<String> plants = new ArrayList<String>();
        String selectQuery = "SELECT DISTINCT "+COL_SITENAME+" FROM "+ TABLE_Machine ;

        Cursor cursor = bdd.rawQuery(selectQuery, null);
        plants.add("");
        if (cursor.moveToFirst()) {
            do {

                plants.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        return plants;
    }








    public  int[] getAllMachineByState(){

        List<Machine> machineList = new ArrayList<Machine>();
        // Select All Query

        String selectQuery = "SELECT  * FROM " + TABLE_Machine;

        int good=0;
        int warning=0;
        int alarm=0;
        int danger=0;


        Cursor cursor = bdd.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                if (cursor.getString(NUM_COL_MACHINESTATE).equals("Green")){good++;
                }
                else if (cursor.getString(NUM_COL_MACHINESTATE).equals("Orange")){warning++;}
                else if (cursor.getString(NUM_COL_MACHINESTATE).equals("Yellow")){alarm++;}
                else if (cursor.getString(NUM_COL_MACHINESTATE).equals("Red")){danger++;}

            } while (cursor.moveToNext());
        }
        int []  counts={good,warning,alarm,danger};

        // return contact list
        return counts;
    }








}
