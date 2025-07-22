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

public class wine_fortification extends AppCompatActivity {

    EditText winevol, wineabv, fortabv, targabv;
    TextView fortreq, ethtot, finalvol, bot250ml, bot330ml, bot500ml, bot700ml, bot750ml, bot1000ml,
    act_title;
    ImageView infostack;
    Button fortrun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_wine_fortification);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.RelativeLayout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);

            winevol = findViewById(R.id.et_winevol);
            wineabv = findViewById(R.id.et_wineabv);
            fortabv = findViewById(R.id.et_fortabv);
            targabv = findViewById(R.id.et_targabv);
            ethtot = findViewById(R.id.tv_eth_tot2);
            finalvol = findViewById(R.id.tv_final_volume2);
            bot250ml = findViewById(R.id.bot4_250ml);
            bot330ml = findViewById(R.id.bot4_330ml);
            bot500ml = findViewById(R.id.bot4_500ml);
            bot700ml = findViewById(R.id.bot4_700ml);
            bot750ml = findViewById(R.id.bot4_750ml);
            bot1000ml = findViewById(R.id.bot4_1000ml);
            fortreq = findViewById(R.id.tv_fortreq);
            fortrun = (findViewById(R.id.btn_fort_run));

            act_title=findViewById(R.id.tv_act_title);
            act_title.setText(getString(R.string.wine_fortification_target_abv));

            infostack=findViewById(R.id.btn_info);
            infostack.setOnClickListener(a -> {
                PopupMenu infomenu = new PopupMenu(wine_fortification.this, infostack);
                infomenu.getMenuInflater().inflate(R.menu.info_menu, infomenu.getMenu());
                infomenu.setOnMenuItemClickListener(menuItem -> {

                    switch (menuItem.getItemId()){

                        case R.id.about:
                            Intent infabout=new Intent(wine_fortification.this,
                                    info_about.class);
                            startActivity(infabout);
                            break;

                        case R.id.privacy:
                            Intent infprivacy=new Intent(wine_fortification.this,
                                    info_privacy.class);
                            startActivity(infprivacy);
                            break;

                        case R.id.accuracy:
                            Intent infaccuracy=new Intent(wine_fortification.this,
                                    info_accuracy.class);
                            startActivity(infaccuracy);
                            break;

                    }

                    return true;
                });
                infomenu.show();
            });

            fortrun.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {

                    try{

                        String wvolstring = winevol.getText().toString();
                        double winevol = Double.parseDouble(wvolstring);
                        String wabvstring = wineabv.getText().toString();
                        double wineabv = Double.parseDouble(wabvstring);
                        String fabvstring = fortabv.getText().toString();
                        double fortabv = Double.parseDouble(fabvstring);
                        String tabv2string = targabv.getText().toString();
                        double targabv = Double.parseDouble(tabv2string);

                        double wine_abv = (wineabv / 100);
                        double fort_abv = (fortabv / 100);
                        double targ_abv = (targabv / 100);
                        double req_fort = (winevol * (targ_abv - wine_abv) / (fort_abv - targ_abv));
                        double fin_vol = (winevol + req_fort);
                        double alcvol = (fin_vol * targ_abv);
                        double cal250 = (fin_vol / 250);
                        double cal330 = (fin_vol / 330);
                        double cal500 = (fin_vol / 500);
                        double cal700 = (fin_vol / 700);
                        double cal750 = (fin_vol / 750);
                        double cal1000 = (fin_vol / 1000);

                        ethtot.setText(String.format("%.2f", alcvol) + " mL");
                        finalvol.setText(String.format("%.2f", fin_vol) + " mL");
                        bot250ml.setText(String.format("%.2f", cal250));
                        bot330ml.setText(String.format("%.2f", cal330));
                        bot500ml.setText(String.format("%.2f", cal500));
                        bot700ml.setText(String.format("%.2f", cal700));
                        bot750ml.setText(String.format("%.2f", cal750));
                        bot1000ml.setText(String.format("%.2f", cal1000));
                        fortreq.setText(String.format("%.2f", req_fort) + " mL");

                    }catch(NumberFormatException e){
                        Toast.makeText(wine_fortification.this,
                                R.string.invalid_input, Toast.LENGTH_LONG).show();
                    }
                }
            });
            return insets;
        });
    }
}