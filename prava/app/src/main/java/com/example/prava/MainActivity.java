package com.example.prava;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;

import com.example.prava.ui.main.MainFragment;


import android.widget.Button;
import android.widget.Toast;
import android.view.View;
public class MainActivity extends AppCompatActivity {

    MediaPlayer mp;
    Button btnContar;

// Aquest mètode mostra un text
    public void patata(){
            Toast.makeText(this, "Eres un Crack y lo sabes", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow();

        }
// Aquest es el botó PULSAME
        btnContar = findViewById(R.id.btnContar);

        mp = MediaPlayer.create(this,R.raw.musica);
        btnContar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();
                patata();

            }
        });
    }
}
