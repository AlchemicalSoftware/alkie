package com.suite.alkie;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button button1, button2, button3, button4, button4a, button5, button6, button7, button8,
            button9;

    TextView act_title;
    ImageView infostack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.RelativeLayout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);

            act_title=findViewById(R.id.tv_act_title);
            act_title.setText(getString(R.string.app_ver));

            infostack=findViewById(R.id.btn_info);
            infostack.setOnClickListener(a -> {
                        PopupMenu infomenu = new PopupMenu(MainActivity.this, infostack);
                        infomenu.getMenuInflater().inflate(R.menu.info_menu, infomenu.getMenu());
                        infomenu.setOnMenuItemClickListener(menuItem -> {

                            switch (menuItem.getItemId()){

                                case R.id.about:
                                    Intent infabout=new Intent(MainActivity.this,
                                            info_about.class);
                                    startActivity(infabout);
                                    break;

                                case R.id.privacy:
                                    Intent infprivacy=new Intent(MainActivity.this,
                                            info_privacy.class);
                                    startActivity(infprivacy);
                                    break;

                                case R.id.accuracy:
                                    Intent infaccuracy=new Intent(MainActivity.this,
                                            info_accuracy.class);
                                    startActivity(infaccuracy);
                                    break;

                            }

                            return true;
                });
                infomenu.show();
            });

            button1=findViewById(R.id.btn_reldens);
            button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(MainActivity.this,
                            relative_density.class);
                    startActivity(intent);
                }
            });

            button2=findViewById(R.id.btn_tempcomp);
            button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(MainActivity.this,
                            relative_density_tempcomp.class);
                    startActivity(intent);
                }
            });

            button3=findViewById(R.id.btn_vol_abv);
            button3.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(MainActivity.this,
                            target_volume_abv.class);
                    startActivity(intent);
                }
            });

            button4=findViewById(R.id.btn_rawmat_abv);
            button4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(MainActivity.this,
                            rawmat_to_abv.class);
                    startActivity(intent);
                }
            });

            button4a=findViewById(R.id.btn_raw_vol_abv);
            button4a.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this,
                            rawmat_volume_to_abv.class);
                    startActivity(intent);
                }
            });

            button5=findViewById(R.id.btn_tot_alc_vol);
            button5.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this,
                            target_alcohol_volume.class);
                    startActivity(intent);
                }
            });

            button6=findViewById(R.id.btn_highwine_reduction);
            button6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this,
                            high_wine_abv_reduction.class);
                    startActivity(intent);
                }
            });

            button7=findViewById(R.id.btn_wine_fort);
            button7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this,
                            wine_fortification.class);
                    startActivity(intent);
                }
            });
            button8=findViewById(R.id.btn_blending);
            button8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this,
                            blending.class);
                    startActivity(intent);
                }
            });
            button9=findViewById(R.id.btn_weight_vol);
            button9.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this,
                            weight_vol_abv.class);
                    startActivity(intent);
                }
            });

            return insets;
        });
    }
}