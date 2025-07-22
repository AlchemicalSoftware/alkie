package com.suite.alkie;

import android.content.Intent;
import android.os.Bundle;
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

public class high_wine_abv_reduction extends AppCompatActivity {
TextView reqwater, ethtot, finalvol, bot250, bot330, bot500, bot700, bot750, bot1000, act_title;
EditText highwines, highwinesabv, final_tabv;
ImageView infostack;
Button btn_run;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_high_wine_abv_reduction);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.RelativeLayout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);

            highwines = findViewById(R.id.tv_highwines);
            highwinesabv = findViewById(R.id.tv_highwines_abv);
            final_tabv = findViewById(R.id.tv_final_abv);
            reqwater = findViewById(R.id.tv_reqwater);
            ethtot = findViewById(R.id.tv_eth_tot);
            finalvol = findViewById(R.id.tv_final_volume);
            btn_run = findViewById(R.id.btn_highwine_run);
            bot250 = findViewById(R.id.bot_250ml);
            bot330 = findViewById(R.id.bot_330ml);
            bot500 = findViewById(R.id.bot_500ml);
            bot700 = findViewById(R.id.bot_700ml);
            bot750 = findViewById(R.id.bot_750ml);
            bot1000 = findViewById(R.id.bot_1000ml);

            act_title=findViewById(R.id.tv_act_title);
            act_title.setText(getString(R.string.high_wines_abv_reduction));

            infostack=findViewById(R.id.btn_info);
            infostack.setOnClickListener(a -> {
                PopupMenu infomenu = new PopupMenu(high_wine_abv_reduction.this, infostack);
                infomenu.getMenuInflater().inflate(R.menu.info_menu, infomenu.getMenu());
                infomenu.setOnMenuItemClickListener(menuItem -> {

                    switch (menuItem.getItemId()){

                        case R.id.about:
                            Intent infabout=new Intent(high_wine_abv_reduction.this,
                                    info_about.class);
                            startActivity(infabout);
                            break;

                        case R.id.privacy:
                            Intent infprivacy=new Intent(high_wine_abv_reduction.this,
                                    info_privacy.class);
                            startActivity(infprivacy);
                            break;

                        case R.id.accuracy:
                            Intent infaccuracy=new Intent(high_wine_abv_reduction.this,
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
                        String hwstring = highwines.getText().toString();
                        double hw = Double.parseDouble(hwstring);
                        String hwabvstring = highwinesabv.getText().toString();
                        double hwabv = Double.parseDouble(hwabvstring);
                        String ftabvstring = final_tabv.getText().toString();
                        double ftabv = Double.parseDouble(ftabvstring);

                        double alcvol = (hw * (hwabv / 100));
                        double hwwater = (hw * ((100-hwabv) / 100));
                        double t_water = ((alcvol / ftabv * 100) - (hwwater + alcvol));
                        double fin_vol = (hw + t_water);
                        double cal250 = (fin_vol / 250);
                        double cal330 = (fin_vol / 330);
                        double cal500 = (fin_vol / 500);
                        double cal700 = (fin_vol / 700);
                        double cal750 = (fin_vol / 750);
                        double cal1000 = (fin_vol / 1000);

                        ethtot.setText(String.format("%.2f", alcvol) + " mL");
                        finalvol.setText(String.format("%.2f", fin_vol) + " mL");
                        reqwater.setText(String.format("%.2f", t_water) + " mL");
                        bot250.setText(String.format("%.2f", cal250));
                        bot330.setText(String.format("%.2f", cal330));
                        bot500.setText(String.format("%.2f", cal500));
                        bot700.setText(String.format("%.2f", cal700));
                        bot750.setText(String.format("%.2f", cal750));
                        bot1000.setText(String.format("%.2f", cal1000));

                    }catch(NumberFormatException e){
                        Toast.makeText(high_wine_abv_reduction.this,
                                R.string.invalid_input, Toast.LENGTH_LONG).show();
                    }
                }
            });
            return insets;
        });
    }
}