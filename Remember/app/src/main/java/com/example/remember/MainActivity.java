package com.example.remember;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.time.Month;
import java.time.Year;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{
    private Button boton;
    private EditText txt;
    private ArrayList<String> itemlist;
    private ArrayAdapter<String> adapter;
    private ListView list;
    // variables to create notifications
    private PendingIntent pendingIntent;
    private final static String CHANNEL_ID = "NOTIFICACION";
    private int NOTIFICACION_ID = 0; // number for creating more than one notifications
    // atribute to store the dates from the tags
    private String date = "";


    @Override


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boton = (Button) findViewById(R.id.button);
        txt = (EditText) findViewById(R.id.txt);
        list = (ListView) findViewById(R.id.lista);
        itemlist = new ArrayList<>();

        // create adapter
        adapter = new  ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, itemlist);
        list.setAdapter(adapter);

        // read tags from "Remember.txt"
        String archivos [] = fileList();

        if(ArchivoExiste(archivos, "Remember.txt")) {
            try {
                InputStreamReader archivo = new InputStreamReader(openFileInput("Remember.txt"));
                BufferedReader br = new BufferedReader(archivo);
                String linea = br.readLine();


                while(linea != null) {
                    itemlist.add(linea);
                    linea = br.readLine();
                }
                br.close();
                archivo.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // delete data
        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int  position, long id) {
                onListItemLongPressed(position);
                return true;
            }
        });
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                    try {
                        onListItemPressed(position);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    createNotificationChannel();
                    createNotification(itemlist.get(position), date);


            }
        });


    }



    // method private to complete method "crearEtiqueta", this method is necessary
    private void createNotification(String tag ,String date) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID);
        builder.setSmallIcon(R.drawable.ic_turned_in_black_24dp);
        builder.setContentTitle(tag);
        builder.setContentText(date);
        builder.setColor(Color.BLUE);
        builder.setPriority(NotificationCompat.PRIORITY_HIGH);
        builder.setDefaults(Notification.DEFAULT_SOUND);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(getApplicationContext());
        notificationManagerCompat.notify(NOTIFICACION_ID, builder.build());
        NOTIFICACION_ID = NOTIFICACION_ID + 1;
    }
    // method private to complete method "crearEtiqueta", this method is only necessary if
    //the android version is higher than 8.0
    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Notificacion";
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, name, NotificationManager.IMPORTANCE_HIGH);
            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(notificationChannel);

        }
    }

    /**
     * Method to create the Tag pushing the Main Button
     * @param view
     * @return
     */
    public void crearEtiqueta(View view) {

        if (!txt.getText().toString().isEmpty()) {
            String etiqueta = txt.getText().toString();
            itemlist.add(itemlist.size(), etiqueta);
            Guardar();
            Toast.makeText(this, "new Tag PRO ", Toast.LENGTH_SHORT).show();


        }
    }

    /**
     *Method to save data in the file "Remember.txt" by the button "Guardar"
     */

    public void Guardar() {
        OutputStreamWriter archivo = null;
        try {
            archivo = new OutputStreamWriter(openFileOutput("Remember.txt", Activity.MODE_PRIVATE));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            for (int i = 0; i < itemlist.size(); i++) {
                archivo.write(itemlist.get(i) + "\n");

            }
            archivo.flush();
            archivo.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Toast.makeText(this, "Dades guardades amb exit", Toast.LENGTH_SHORT).show();

    }

    /**
     * Method to validate that "Archivo" exists in order to
     * read the data from the file
     * @param archivos Array where it saves data
     * @param NombreArchivos name of the file where data is saved
     * @return a boolean
     */
    private boolean ArchivoExiste(String archivos[], String NombreArchivos) {
        for (int i = 0; i < archivos.length; i++)
            if (NombreArchivos.equals(archivos[i]))
                return true;
        return false;
    }

    /**
     *Method to ask if deleting data is needed
     */
    public void onListItemLongPressed(final int position) {
        AlertDialog.Builder myBuild = new AlertDialog.Builder(this);
        myBuild.setMessage("Are you sure you want to delete this ?");
        myBuild.setTitle("Delete");
        myBuild.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                itemlist.remove(position);
                adapter.notifyDataSetChanged();
                Guardar();
            }
        });

        myBuild.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog dialog = myBuild.create();
        dialog.show();

    }
    /**
     *Method to ask a date to end the task
     */
    public void onListItemPressed(final int position) throws InterruptedException {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)

        );

        datePickerDialog.show();



    }

    /**
     *This method stores de date selected by the user at the general atribute "date"
     */
    public  void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        date =  year + "/" + (month + 1) + "/" + dayOfMonth;

    }

}
