package com.example.smite_randompick;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ClipData;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Button btn_guardian;
    private Button btn_warrior;
    private Button btn_assassin;
    private Button btn_mage;
    private Button btn_hunter;
    private Button btn_any_role;

    private TextView txt;

    private String [] guardians = {"Ares", "Baco", "Atenea", "Sobek", "Geb", "Silvano", "Ymir", "Cabrakan", "Jepri" , "Xing Tian", "Cerberus" , "Cthulhu" , "Kuzembo" , "Yemoja" , "Fafnir" , "Ganesha" , "Jormungander", "Terra", "Kumbhákarna", "Artio"};
    private String [] warriors = {"Gilgamesh","Sun Wukong", "Chaac", "Guan Yu", "Belona", "Tyr", "Amaterusa", "Hércules", "Odín" , "Osiris", "Vámana", "Nike", "King Arthur", "Erlang Shen", "Cu Chulainn", "Achiles", "Mulan", "Horus"};
    private String [] assasins = {"Tsukuyomi","Loki", "Fenrir", "Awilix", "Ratatoskr", "Mercurio", "Hun Batz", "Nemesis", "Aracne", "Bakasura", "Bastet", "Thor", "Kali", "Ze Zha", "Ravana", "set", "Thanatos","Camazotz", "Da ji", "Pele", "Serqet", "Susano" };
    private String [] mages = {"Tiamat","Ao Kuang", "Agni", "Afrodita", "Zeus", "Anubis", "Ra", "Cronos", "Isis", "Kukulkan", "Ah Puch", "Hel", "Freya", "Jano", "Nox", "Hades", "Nu Wa", "Poseidon", "Vulcano", "Change", "Escila", "He Bo", "Raijin", "Sol", "Baba Yaga", "Baron Samedi", "Discordia", "Merlin", "Olorun", "Persephone", "The Morrigan", "Thoth", "Hera" } ;
    private String [] hunters = {"Apolo", "Ah Muzen Cab", "Hou Yi", "Neith", "Anhur", "Artemisa", "Cupido", "Xbalanque", "Medusa", "Skadi", "Rama", "Quiron", "Ullr", "Cernunnos", "Hachiman", "Heimdallr", "Chernobog", "Izanami", "Jing Wei", "Danzaburou"};
    private String [] anyRoles = {
            "Tsukuyomi","Ares", "Baco", "Atenea", "Sobek", "Geb", "Silvano", "Ymir", "Cabrakan", "Jepri" , "Xing Tian", "Cerberus" , "Cthulhu" , "Kuzembo" , "Yemoja" , "Fafnir" , "Ganesha" ,
            "Jormungander", "Terra", "Kumbhákarna", "Artio" ,"Sun Wukong", "Chaac", "Guan Yu", "Belona", "Tyr", "Amaterusa", "Hércules", "Odín" , "Osiris", "Vámana", "Nike", "King Arthur", "Erlang Shen", "Cu Chulainn",
            "Achiles", "Mulan", "Horus", "Loki", "Fenrir", "Awilix", "Ratatoskr", "Mercurio", "Hun Batz", "Nemesis", "Aracne", "Bakasura", "Bastet", "Thor", "Kali", "Ze Zha", "Ravana", "set", "Thanatos","Camazotz",
            "Da ji", "Pele", "Serqet", "Susano", "Ao Kuang", "Agni", "Afrodita", "Zeus", "Anubis", "Ra", "Cronos", "Isis", "Kukulkan", "Ah Puch", "Hel", "Freya", "Jano", "Nox", "Hades", "Nu Wa", "Poseidon", "Vulcano",
            "Change", "Escila", "He Bo", "Raijin", "Sol", "Baba Yaga", "Baron Samedi", "Discordia", "Merlin", "Olorun", "Persephone", "The Morrigan", "Thoth", "Hera" , "Apolo", "Ah Muzen Cab", "Hou Yi", "Neith", "Anhur",
            "Artemisa", "Cupido", "Xbalanque", "Medusa", "Skadi", "Rama", "Quiron", "Ullr", "Cernunnos", "Hachiman", "Heimdallr", "Chernobog", "Izanami", "Jing Wei","Danzaburou", "Tiamat", "Gilgamesh"
    }; ;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_guardian = (Button) findViewById(R.id.btn_guardian);
        btn_warrior = (Button) findViewById(R.id.btn_warrior);
        btn_assassin = (Button) findViewById(R.id.btn_assassin);
        btn_mage = (Button) findViewById(R.id.btn_mage);
        btn_hunter = (Button) findViewById(R.id.btn_hunter);
        btn_any_role = (Button) findViewById(R.id.btn_any_role);

        txt = (TextView) findViewById(R.id.txt);


    }

    public void generarGuardian(View view){
        double index = Math.random() * guardians.length;
        txt.setText(guardians[(int)index]);
    }

    public void generarWarrior(View view){
        double index = Math.random() * warriors.length;
        txt.setText(warriors[(int)index]);
    }

    public void generarAssessin(View view){
        double index = Math.random() * assasins.length;
        txt.setText(assasins[(int)index]);
    }

    public void generarMage(View view){
        double index = Math.random() * mages.length;
        txt.setText(mages[(int)index]);
    }

    public void generarHunter(View view){
        double index = Math.random() * hunters.length;
        txt.setText(hunters[(int)index]);
    }

    public void generarAnyRole(View view){
        double index = Math.random() * anyRoles.length;
        txt.setText(anyRoles[(int)index]);
    }

}
