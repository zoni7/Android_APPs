package com.example.remember;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Button boton;
    private EditText txt;
    private ArrayList<String> itemlist;
    private ArrayAdapter<String> adapter;
    private ListView list;
    // variables to create notifications
    private PendingIntent pendingIntent;
    private final static String CHANNEL_ID = "NOTIFICACION";
    private int NOTIFICACION_ID = 0; // number for creating more than one notifications


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
                onListItemPressed(position);
                return true;
            }
        });

    }

    // method private to complete method "crearEtiqueta", this method is necessary
    private void createNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID);
        builder.setSmallIcon(R.drawable.ic_turned_in_black_24dp);
        builder.setContentTitle("Your awesome tag");
        builder.setContentText(txt.getText().toString());
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
     */
    public void crearEtiqueta(View view) {
        if (!txt.getText().toString().isEmpty()) {
            String etiqueta = txt.getText().toString();
            itemlist.add(itemlist.size(), etiqueta);
            Guardar();
            Toast.makeText(this, "new Tag PRO ", Toast.LENGTH_SHORT).show();

            createNotificationChannel();
            createNotification();
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
    public void onListItemPressed(final int position) {
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

}
