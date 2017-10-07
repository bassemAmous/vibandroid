package youssefboufaied.com.betavib;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.sdsmdg.tastytoast.TastyToast;
import com.squareup.okhttp.Connection;
import com.squareup.okhttp.OkHttpClient;
import com.wang.avi.AVLoadingIndicatorView;

import java.net.URL;
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
import youssefboufaied.com.betavib.Entity.Machine;
import youssefboufaied.com.betavib.Entity.User;
import youssefboufaied.com.betavib.Service.APIService;
import youssefboufaied.com.betavib.connection.connection;
import java.io.*;

public class LoginActivity extends AppCompatActivity {

    EditText passwordET, nameET,ipAddressET;
    Spinner dropdown;
    List items;
    static List<Machine> machines;
    SharedPreferences pf;
    AVLoadingIndicatorView avi;
    Button loginbutton;
    TextView dataBaseTextView;
    int compteurClick=0;
   static String adresseLocal="https://betavib.herokuapp.com";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
      //  createandDisplayPdf("ahhaahah");
/*
        String FILE = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_MOVIES).toString();
// Add Permission into Manifest.xml
//
// Create New Blank Document
        Document document = new Document(PageSize.A4);
// Create Directory in External Storage
        String root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).toString();

        System.out.println(root.toString()+"pdfefffffffffffffffffff");
        File myDir = new File(root + "/PDF");
        System.out.println(myDir.getAbsolutePath() );

        myDir.mkdirs();
        String fname = FILE;
        File file = new File (myDir, fname);
        if (file.exists ()) file.delete ();
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
// Create Pdf Writer for Writting into New Created Document
        try {
            PdfWriter.getInstance(document, new FileOutputStream(FILE));
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
// Open Document for Writting into document
        document.open();
// User Define Method
        addMetaData(document);
        try {
            addTitlePage(document);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
// Close Document after writting all content
        document.close();*/
       // createandDisplayPdf();
if(!connection.isOnline(this.getApplicationContext()))
    TastyToast.makeText(getApplicationContext(), " Check your connection", TastyToast.LENGTH_LONG, TastyToast.ERROR);


        pf = getSharedPreferences("pref", MODE_PRIVATE);
        dataBaseTextView = (TextView) findViewById(R.id.textView);

        dropdown = (Spinner) findViewById(R.id.spinner);
        passwordET = (EditText) findViewById(R.id.editTextPassword);
        nameET = (EditText)findViewById(R.id.ip);
        ipAddressET =(EditText) findViewById(R.id.editText);
        loginbutton =(Button) findViewById(R.id.button);
        ipAddressET.setText("64.228.7.206");
        nameET.setText("OussLog");
        passwordET.setText("senzo1988");
        nameET.addTextChangedListener(new TextWatcher() {



    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        compteurClick=0;
        loginbutton.setText("Login");
        dropdown.setVisibility(View.INVISIBLE);
        dataBaseTextView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
});
        ipAddressET.addTextChangedListener(new TextWatcher() {


            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                compteurClick=0;
                loginbutton.setText("Login");
                dropdown.setVisibility(View.INVISIBLE);
                dataBaseTextView.setVisibility(View.INVISIBLE);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        passwordET.addTextChangedListener(new TextWatcher() {


            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            compteurClick=0;
                loginbutton.setText("Login");
                dropdown.setVisibility(View.INVISIBLE);
                dataBaseTextView.setVisibility(View.INVISIBLE);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        avi=(AVLoadingIndicatorView)findViewById(R.id.loaaderlogin);
      //  avi.setVisibility(View.INVISIBLE);
   avi.show();
        items = new ArrayList<String>();

        if(!pf.getString("ip","").equals("")){
            Intent i = new Intent(getApplicationContext(), DashboardActivity.class);


            startActivity(i);
            finish();
        }else {
            try {

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(LoginActivity.adresseLocal).
                                addConverterFactory(GsonConverterFactory.create())
                        .build();
                APIService service = retrofit.create(APIService.class);
                Call<List<DataBase>> call = service.getDataBase();
                ArrayList<String> spinnerArray = new ArrayList<String>();
                call.enqueue(new Callback<List<DataBase>>() {
                    @Override
                    public void onResponse(Response<List<DataBase>> response, Retrofit retrofit) {


                        System.out.println("machine d5al");

                        for (DataBase m : response.body()
                                ) {
                            items.add(m.getName());

                        }



                        avi.setVisibility(View.INVISIBLE);
                        avi.hide();
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        avi.setVisibility(View.INVISIBLE);
                        avi.hide();
                    }
                    //System.out.println(response.body());


                });


            } catch (Exception e) {
                System.out.println("EXCEEPTION" + e);
            }
        }
    }



    public void addMetaData(Document document)
    {
        document.addTitle("RESUME");
        document.addSubject("Person Info");
        document.addKeywords("Personal, Education, Skills");
                document.addAuthor("TAG");
        document.addCreator("TAG");
    }
    public void addTitlePage(Document document) throws DocumentException
    {
// Font Style for Document
        Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
        Font titleFont = new Font(Font.FontFamily.TIMES_ROMAN, 22, Font.BOLD
                | Font.UNDERLINE, BaseColor.GRAY);
        Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
        Font normal = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL);
// Start New Paragraph
        Paragraph prHead = new Paragraph();
// Set Font in this Paragraph
        prHead.setFont(titleFont);
// Add item into Paragraph
        prHead.add("RESUME – Name\n");
// Create Table into Document with 1 Row
        PdfPTable myTable = new PdfPTable(1);
// 100.0f mean width of table is same as Document size
        myTable.setWidthPercentage(100.0f);
// Create New Cell into Table
        PdfPCell myCell = new PdfPCell(new Paragraph(""));
        myCell.setBorder(Rectangle.BOTTOM);
// Add Cell into Table
        myTable.addCell(myCell);
        prHead.setFont(catFont);
        prHead.add("\nName1 Name2\n");
        prHead.setAlignment(Element.ALIGN_CENTER);
// Add all above details into Document
        document.add(prHead);
        document.add(myTable);
        document.add(myTable);
// Now Start another New Paragraph
        Paragraph prPersinalInfo = new Paragraph();
        prPersinalInfo.setFont(smallBold);
        prPersinalInfo.add("Address 1\n");
        prPersinalInfo.add("Address 2\n");
        prPersinalInfo.add("City: SanFran. State: CA\n");
        prPersinalInfo.add("Country: USA Zip Code: 000001\n");
        prPersinalInfo.add("Mobile: 9999999999 Fax: 1111111 Email: john_pit@gmail.com \n");
        prPersinalInfo.setAlignment(Element.ALIGN_CENTER);
        document.add(prPersinalInfo);
        document.add(myTable);
        document.add(myTable);
        Paragraph prProfile = new Paragraph();
        prProfile.setFont(smallBold);
        prProfile.add("\n \n Profile : \n ");
        prProfile.setFont(normal);
        prProfile.add("\nI am Mr. XYZ. I am Android Application Developer at TAG.");
        prProfile.setFont(smallBold);
        document.add(prProfile);
// Create new Page in PDF
        document.newPage();
    }
    public void loginAction(View view) {

        loginbutton.setEnabled(false);
            avi.setVisibility(View.VISIBLE);

            // loginbutton.setEnabled(false);
            avi = (AVLoadingIndicatorView) findViewById(R.id.loaaderlogin);
            avi.show();
            // //avi.show();

            try {

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(LoginActivity.adresseLocal).
                                addConverterFactory(GsonConverterFactory.create())
                        .build();
                APIService service = retrofit.create(APIService.class);

                //     System.out.println("ip"+ipAddressET.getText().toString()+" name : "+nameET.getText().toString()+" password : "+passwordET.getText().toString()+" database : "+ dropdown.getSelectedItem().toString());

                service.connect(ipAddressET.getText().toString(), nameET.getText().toString(), passwordET.getText().toString(), "ABI-FONDERIE").enqueue(new Callback<User>() {


                    @Override
                    public void onResponse(Response<User> response, Retrofit retrofit) {
                        // //avi.show();


                        if (response.body().getSuccess().equals("false")) {
                            Toast.makeText(LoginActivity.this, "Error Credentials", Toast.LENGTH_SHORT).show();
                            loginbutton.setEnabled(true);
                            avi.setVisibility(View.INVISIBLE);

                            avi = (AVLoadingIndicatorView) findViewById(R.id.loaaderlogin);
                            avi.hide();
                        } else {
                            System.out.println("succeeeeeeeeeeeeessss" + compteurClick);

                            if (compteurClick == 0) {
                                loginbutton.setEnabled(true);

                                ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_element,R.id.spinnerElement, items); //selected item will look like a spinner set from XML
                                spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_element);
                                dropdown.setAdapter(spinnerArrayAdapter);
                                avi.hide();
                                loginbutton.setText("Next");
                                compteurClick++;
                                dropdown.setVisibility(View.VISIBLE);
                                dataBaseTextView.setVisibility(View.VISIBLE);
                                TastyToast.makeText(getApplicationContext(), "please choose a database", TastyToast.LENGTH_LONG, TastyToast.INFO
                                );


                            } else {

                                machineLocalDataBaseInsert();
                            }


                        }
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        loginbutton.setEnabled(false);
                        avi.setVisibility(View.INVISIBLE);

                        avi.hide();
                        System.out.println("KAKA" + t.toString());
                        Toast.makeText(LoginActivity.this, "connection failure", Toast.LENGTH_SHORT).show();

                    }
                });


            } catch (Exception e) {
                // avi.hide();
                Toast.makeText(LoginActivity.this, "error verify your login and password", Toast.LENGTH_SHORT).show();

                Log.d("onResponse", "There is an error");
                e.printStackTrace();
            }




    }
    public void machineLocalDataBaseInsert(){

        //avi.show();
        System.out.println("d5al lel inseeeeeeert");
        pf = getSharedPreferences("pref", MODE_PRIVATE);
        SharedPreferences.Editor e = pf.edit();
        e.putString("database", dropdown.getSelectedItem().toString());
        e.putString("password",passwordET.getText().toString());
        e.putString("login",nameET.getText().toString());
        e.putString("ip",ipAddressET.getText().toString());
        e.commit();
        OkHttpClient client = new OkHttpClient();
        client.setConnectTimeout(5, TimeUnit.MINUTES); // connect timeout
        client.setReadTimeout(5, TimeUnit.MINUTES);
       Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(LoginActivity.adresseLocal).addConverterFactory(GsonConverterFactory.create()).client(client)
                .build();
        APIService service = retrofit.create(APIService.class);
        Call<List<Machine>> call = service.getAllMachines(pf.getString("ip",""),pf.getString("login",""),pf.getString("password",""),pf.getString("database",""));
        call.enqueue(new Callback<List<Machine>>() {
            @Override
            public void onResponse(Response<List<Machine>> response, Retrofit retrofit) {
                //avi.show();
                machines = response.body();
                new AsyncT().execute();
               // avi.setVisibility(View.INVISIBLE);


                //avi.hide();
                //avi.hide();
            }
            @Override
            public void onFailure(Throwable t) {
                avi.setVisibility(View.INVISIBLE);

                avi=(AVLoadingIndicatorView)findViewById(R.id.loaaderlogin);
                avi.hide();
                //avi.hide();
                System.out.println("failure");

            }
        });
    }

    public void createandDisplayPdf(String text) {

        Document doc = new Document();

        try {
            String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Dir";

            File dir = new File(path);
            if(!dir.exists())
                dir.mkdirs();

            File file = new File(dir, "newFile.pdf");
            FileOutputStream fOut = new FileOutputStream(file);

            PdfWriter.getInstance(doc, fOut);

            //open the document
            doc.open();

            Paragraph p1 = new Paragraph(text);
            Font paraFont= new Font();

            p1.setAlignment(Paragraph.ALIGN_CENTER);


            //add paragraph to document
            doc.add(p1);

        } catch (DocumentException de) {
            Log.e("PDFCreator", "DocumentException:" + de);
        } catch (IOException e) {
            Log.e("PDFCreator", "ioException:" + e);
        }
        finally {
            doc.close();
        }

        viewPdf("newFile.pdf", "Dir");
    }
    // Method for opening a pdf file
    private void viewPdf(String file, String directory) {

        File pdfFile = new File(Environment.getExternalStorageDirectory() + "/" + directory + "/" + file);
        Uri path = Uri.fromFile(pdfFile);

        // Setting the intent for pdf reader
        Intent pdfIntent = new Intent(Intent.ACTION_VIEW);
        pdfIntent.setDataAndType(path, "application/pdf");
        pdfIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        try {
            startActivity(pdfIntent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(LoginActivity.this, "Can't read pdf file", Toast.LENGTH_SHORT).show();
        }
    }
  private   class AsyncT extends AsyncTask<URL, Integer, Long> {
      @Override
      protected void onPreExecute() {
          avi.setVisibility(View.VISIBLE);

          avi=(AVLoadingIndicatorView)findViewById(R.id.loaaderlogin);
   avi.show();
      }


      protected Long doInBackground(URL... urls) {
        MachineDB machineDB=new MachineDB(getApplicationContext());
          machineDB.open();
          machineDB.upgradeBDD();
          if(machines != null) {
              for (int i = 0; i < machines.size(); i++) {
                  machineDB.insertMachine(machines.get(i));
                  System.out.println("affiche"+machines.get(i));
              }
          }
          machineDB.close();
          // System.out.println("afficheeeee"+machines.get(0));
          //    adapter = new MachineAdapter(getApplicationContext(),R.layout.machine_grid, machines);
          //  gv.setNumColumns(4);
          //gv.setAdapter(adapter);
//          Toast.makeText(LoginActivity.this, "Connecting...", Toast.LENGTH_SHORT).show();
          Intent i = new Intent(getApplicationContext(), DashboardActivity.class);


          startActivity(i);
          finish();
          return 1l;
            }



        protected void onProgressUpdate(Integer... progress) {
            avi.setVisibility(View.VISIBLE);

            avi=(AVLoadingIndicatorView)findViewById(R.id.loaaderlogin);
            avi.show();
            //setProgressPercent(progress[0]);
        }

        protected void onPostExecute(Long result) {
           // avi.setVisibility(View.INVISIBLE);

            avi=(AVLoadingIndicatorView)findViewById(R.id.loaaderlogin);
            avi.show();
            TastyToast.makeText(getApplicationContext(), "connecting", TastyToast.LENGTH_LONG, TastyToast.SUCCESS);
            //showDialog("Downloaded " + result + " bytes");
            //avi.hide();
        }

    }







}
