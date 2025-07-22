package com.suite.alkie;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class blending extends AppCompatActivity {

    EditText b1vol = null, b2vol, b3vol, b4vol, b1abv = null, b2abv, b3abv, b4abv;
    TextView abvview, ethtot, totvol;
    TextView bot250, bot330, bot500, bot700, bot750, bot1000, act_title;
    ImageView infostack;
    Button btn_run;
    double b1v, b2v, b3v, b4v;
    double b1a, b2a, b3a, b4a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_blending);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.RelativeLayout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);

            btn_run = findViewById(R.id.btn_blending_run);
            b1vol = findViewById(R.id.et_batch1_vol);
            b2vol = findViewById(R.id.et_batch2_vol);
            b3vol = findViewById(R.id.et_batch3_vol);
            b4vol = findViewById(R.id.et_batch4_vol);
            b1abv = findViewById(R.id.et_batch1_abv);
            b2abv = findViewById(R.id.et_batch2_abv);
            b3abv = findViewById(R.id.et_batch3_abv);
            b4abv = findViewById(R.id.et_batch4_abv);
            abvview = findViewById(R.id.tv_abv2);
            ethtot = findViewById(R.id.tv_tot_eth2);
            totvol = findViewById(R.id.tv_tot_vol2);
            bot250 = findViewById(R.id.bot6_250ml);
            bot330 = findViewById(R.id.bot6_330ml);
            bot500 = findViewById(R.id.bot6_500ml);
            bot700 = findViewById(R.id.bot6_700ml);
            bot750 = findViewById(R.id.bot6_750ml);
            bot1000 = findViewById(R.id.bot6_1000ml);

            act_title=findViewById(R.id.tv_act_title);
            act_title.setText(getString(R.string.blending));

            infostack=findViewById(R.id.btn_info);
            infostack.setOnClickListener(a -> {
                PopupMenu infomenu = new PopupMenu(blending.this, infostack);
                infomenu.getMenuInflater().inflate(R.menu.info_menu, infomenu.getMenu());
                infomenu.setOnMenuItemClickListener(menuItem -> {

                    switch (menuItem.getItemId()){

                        case R.id.about:
                            Intent infabout=new Intent(blending.this,
                                    info_about.class);
                            startActivity(infabout);
                            break;

                        case R.id.privacy:
                            Intent infprivacy=new Intent(blending.this,
                                    info_privacy.class);
                            startActivity(infprivacy);
                            break;

                        case R.id.accuracy:
                            Intent infaccuracy=new Intent(blending.this,
                                    info_accuracy.class);
                            startActivity(infaccuracy);
                            break;
                    }
                    return true;
                });
                infomenu.show();
            });

            btn_run.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {

                    try{
                        String b1volstring = b1vol.getText().toString().trim();
                        String b2volstring = b2vol.getText().toString().trim();
                        String b3volstring = b3vol.getText().toString().trim();
                        String b4volstring = b4vol.getText().toString().trim();
                        String b1abvstring = b1abv.getText().toString().trim();
                        String b2abvstring = b2abv.getText().toString().trim();
                        String b3abvstring = b3abv.getText().toString().trim();
                        String b4abvstring = b4abv.getText().toString().trim();



                        if (TextUtils.isEmpty(b1volstring)){
                            b1v = 0;
                        }else{
                            b1v = Double.parseDouble(b1volstring);
                        }

                        if (TextUtils.isEmpty(b2volstring)){
                            b2v = 0;
                        }else{
                            b2v = Double.parseDouble(b2volstring);
                        }

                        if (TextUtils.isEmpty(b3volstring)){
                            b3v = 0;
                        }else{
                            b3v = Double.parseDouble(b3volstring);
                        }

                        if (TextUtils.isEmpty(b4volstring)){
                            b4v = 0;
                        }else{
                            b4v = Double.parseDouble(b4volstring);
                        }



                        if (TextUtils.isEmpty(b1abvstring)){
                            b1a = 0;
                        }else{
                            b1a = Double.parseDouble(b1abvstring);
                        }

                        if (TextUtils.isEmpty(b2abvstring)){
                            b2a = 0;
                        }else{
                            b2a = Double.parseDouble(b2abvstring);
                        }

                        if (TextUtils.isEmpty(b3abvstring)){
                            b3a = 0;
                        }else{
                            b3a = Double.parseDouble(b3abvstring);
                        }

                        if (TextUtils.isEmpty(b4abvstring)){
                            b4a = 0;
                        }else{
                            b4a = Double.parseDouble(b4abvstring);
                        }

                        double b1alc = (b1v * (b1a / 100));
                        double b2alc = (b2v * (b2a / 100));
                        double b3alc = (b3v * (b3a / 100));
                        double b4alc = (b4v * (b4a / 100));
                        double allvol = (b1v + b2v + b3v + b4v);
                        double allalc = (b1alc + b2alc + b3alc + b4alc);
                        double allabv = ((allalc / allvol) * 100);
                        double cal250 = (allvol / 250);
                        double cal330 = (allvol / 330);
                        double cal500 = (allvol / 500);
                        double cal700 = (allvol / 700);
                        double cal750 = (allvol / 750);
                        double cal1000 = (allvol / 1000);

                        if (allabv == 0 || allvol == 0){
                            Toast.makeText(blending.this,
                                    R.string.invalid_input, Toast.LENGTH_LONG).show();
                        }else {
                            abvview.setText(String.format("%.2f", allabv) + " %");
                            totvol.setText(String.format("%.2f", allvol));
                            ethtot.setText(String.format("%.2f", allalc));
                            bot250.setText(String.format("%.2f", cal250));
                            bot330.setText(String.format("%.2f", cal330));
                            bot500.setText(String.format("%.2f", cal500));
                            bot700.setText(String.format("%.2f", cal700));
                            bot750.setText(String.format("%.2f", cal750));
                            bot1000.setText(String.format("%.2f", cal1000));
                        }


                    }catch(NumberFormatException e){
                        Toast.makeText(blending.this,
                                R.string.invalid_input, Toast.LENGTH_LONG).show();
                    }
                }
            });

            return insets;
        });
    }
}