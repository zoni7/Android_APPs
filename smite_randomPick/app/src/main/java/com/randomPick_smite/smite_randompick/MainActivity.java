package com.randomPick_smite.smite_randompick;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button btn_guardian;
    private Button btn_warrior;
    private Button btn_assassin;
    private Button btn_mage;
    private Button btn_hunter;
    private Button btn_any_role;

    private Animation scaleUp,scaleDown;

    private TextView txt;

    private String [] guardians = {"Ares", "Bacchus", "Athena", "Sobek", "Geb", "Sylvanus", "Ymir", "Cabrakan", "Khepri" , "Xing Tian", "Cerberus" , "Cthulhu" , "Kuzembo" , "Yemoja" , "Fafnir" , "Ganesha" , "Jormungander", "Terra", "Kumbhakarna", "Artio"};
    private String [] warriors = {"Gilgamesh","Sun Wukong", "Chaac", "Guan Yu", "Belona", "Tyr", "Amaterasu", "Hercules", "Odin" , "Osiris", "Vamana", "Nike", "King Arthur", "Erlang Shen", "Cu Chulainn", "Achiles", "Mulan", "Horus"};
    private String [] assasins = {"Tsukuyomi","Loki", "Fenrir", "Awilix", "Ratatoskr", "Mercurio", "Hun Batz", "Nemesis", "Aracne", "Bakasura", "Bastet", "Thor", "Kali", "Ne Zha", "Ravana", "Set", "Thanatos","Camazotz", "Da ji", "Pele", "Serqet", "Susano" };
    private String [] mages = {"Morgan","Tiamat","Ao Kuang", "Agni", "Afrodita", "Zeus", "Anubis", "Ra", "Chronos", "Eset", "Kukulkan", "Ah Puch", "Hel", "Freya", "Janus", "Nox", "Hades", "Nu Wa", "Poseidon", "Vulcan", "Change", "Scylla", "He Bo", "Raijin", "Sol", "Baba Yaga", "Baron Samedi", "Discordia", "Merlin", "Olorun", "Persephone", "The Morrigan", "Thoth", "Hera" } ;
    private String [] hunters = {"Charybdis", "Apollo", "Ah Muzen Cab", "Hou Yi", "Neith", "Anhur", "Artemis", "Cupid", "Xbalanque", "Medusa", "Skadi", "Rama", "Quiron", "Ullr", "Cernunnos", "Hachiman", "Heimdallr", "Chernobog", "Izanami", "Jing Wei", "Danzaburou"};
    private String [] anyRoles = {
            "Tsukuyomi","Ares", "Bacchus", "Athena", "Sobek", "Geb", "Sylvanus", "Ymir", "Cabrakan", "Khepri" , "Xing Tian", "Cerberus" , "Cthulhu" , "Kuzembo" , "Yemoja" , "Fafnir" , "Ganesha" ,
            "Jormungander", "Terra", "Kumbhakarna", "Artio" ,"Sun Wukong", "Chaac", "Guan Yu", "Belona", "Tyr", "Amaterasu", "Hercules", "Odin" , "Osiris", "Vamana", "Nike", "King Arthur", "Erlang Shen", "Cu Chulainn",
            "Achiles", "Mulan", "Horus", "Loki", "Fenrir", "Awilix", "Ratatoskr", "Mercurio", "Hun Batz", "Nemesis", "Aracne", "Bakasura", "Bastet", "Thor", "Kali", "Ne Zha", "Ravana", "Set", "Thanatos","Camazotz",
            "Da ji", "Pele", "Serqet", "Susano", "Ao Kuang", "Agni", "Afrodita", "Zeus", "Anubis", "Ra", "Chronos", "Eset", "Kukulkan", "Ah Puch", "Hel", "Freya", "Janus", "Nox", "Hades", "Nu Wa", "Poseidon", "Vulcan",
            "Change", "Scylla", "He Bo", "Raijin", "Sol", "Baba Yaga", "Baron Samedi", "Discordia", "Merlin", "Olorun", "Persephone", "The Morrigan", "Thoth", "Hera" , "Apollo", "Ah Muzen Cab", "Hou Yi", "Neith", "Anhur",
            "Artemis", "Cupid", "Xbalanque", "Medusa", "Skadi", "Rama", "Quiron", "Ullr", "Cernunnos", "Hachiman", "Heimdallr", "Chernobog", "Izanami", "Jing Wei","Danzaburou", "Tiamat", "Gilgamesh", "Morgan", "Charybdis"
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

        // Instances of the button animations (UP and DOWN)
        scaleUp = AnimationUtils.loadAnimation(this, R.anim.scale_up);
        scaleDown = AnimationUtils.loadAnimation(this, R.anim.scale_down);

        // Set the animations to each button
        animate(btn_guardian, guardians);
        animate(btn_warrior, warriors );
        animate(btn_assassin, assasins);
        animate(btn_mage, mages);
        animate(btn_hunter, hunters);
        animate(btn_any_role, anyRoles);

    }

    /**
     * Method to animate the buttons and make the random pick
     * @param b , v
     */
    public void animate(final Button b,final String[] v) {
        b.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction()==MotionEvent.ACTION_DOWN) {
                    b.startAnimation(scaleUp);
                } else if (motionEvent.getAction()==MotionEvent.ACTION_UP) {
                    b.startAnimation(scaleDown);
                    // Get a random God
                    double index = Math.random() * v.length;
                    txt.setText(v[(int)index]);
                }

                return true;
            }
        });
    }


}
