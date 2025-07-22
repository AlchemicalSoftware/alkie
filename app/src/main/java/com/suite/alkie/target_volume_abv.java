package com.suite.alkie;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;

public class target_volume_abv extends AppCompatActivity {
    Button option1;
    TextView reqamt, tvreq, tvsucrose, tvmaltose, tvglucose, tvfructose, tvtotal, tvalctot, tvco2,
            tv250ml, tv330ml, tv500ml, tv700ml, tv750ml, tv1000ml, act_title;
    EditText etbatch, ettabv;
    double suc = 0, mal = 0, glu = 0, fru = 0;
    double suc_mol = 342.30, glu_mol = 180.156, eth_mol = 46.096, co2_mol = 44.009, amt = 1;
    //Molar masses of relevant base chemicals. Maltose has the same value as sucrose, and fructose
    //has the same value as glucose.
    String name_plur, mat_unit;
    double eth_den = 0.78945;
    //Densities at 20 celsius, measured in grams per cubic centimeter.
    ImageView infostack;


    @SuppressLint("NonConstantResourceId")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_target_volume_abv);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.RelativeLayout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            ;return insets;

            act_title=findViewById(R.id.tv_act_title);
            act_title.setText(getString(R.string.target_volume_amp_abv));

            option1 = findViewById(R.id.btn_matselect_1);
            tvreq = findViewById(R.id.tv_req);
            etbatch = findViewById(R.id.et_batch);
            ettabv = findViewById(R.id.et_tabv);
            reqamt = findViewById(R.id.tv_req_amt);
            tvsucrose = findViewById(R.id.tv_sucrose);
            tvmaltose = findViewById(R.id.tv_maltose);
            tvglucose = findViewById(R.id.tv_glucose);
            tvfructose = findViewById(R.id.tv_fructose);
            tvtotal = findViewById(R.id.tv_total);
            tvalctot = findViewById(R.id.tv_alc_tot);
            tvco2 = findViewById(R.id.tv_co2);
            tv250ml = findViewById(R.id.bot2_250ml);
            tv330ml = findViewById(R.id.bot2_330ml);
            tv500ml = findViewById(R.id.bot2_500ml);
            tv700ml = findViewById(R.id.bot2_700ml);
            tv750ml = findViewById(R.id.bot2_750ml);
            tv1000ml = findViewById(R.id.bot2_1000ml);

            infostack=findViewById(R.id.btn_info);
            infostack.setOnClickListener(a -> {
                PopupMenu infomenu = new PopupMenu(target_volume_abv.this, infostack);
                infomenu.getMenuInflater().inflate(R.menu.info_menu, infomenu.getMenu());
                infomenu.setOnMenuItemClickListener(menuItem -> {

                    switch (menuItem.getItemId()){

                        case R.id.about:
                            Intent infabout=new Intent(target_volume_abv.this,
                                    info_about.class);
                            startActivity(infabout);
                            break;

                        case R.id.privacy:
                            Intent infprivacy=new Intent(target_volume_abv.this,
                                    info_privacy.class);
                            startActivity(infprivacy);
                            break;

                        case R.id.accuracy:
                            Intent infaccuracy=new Intent(target_volume_abv.this,
                                    info_accuracy.class);
                            startActivity(infaccuracy);
                            break;

                    }

                    return true;
                });
                infomenu.show();
            });

            option1.setOnClickListener(a -> {
                PopupMenu popupMenu = new PopupMenu(target_volume_abv.this, option1);
                popupMenu.getMenuInflater().inflate(R.menu.raw_mats_popup, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(menuItem -> {

                    switch(menuItem.getItemId()){

                        case R.id.sucrose:
                            suc = 1;
                            mal = 0;
                            glu = 0;
                            fru = 0;
                            option1.setText(R.string.sucrose);
                            mat_unit = (" g");
                            name_plur = ("Sucrose");
                            break;

                        case R.id.maltose:
                            suc = 0;
                            mal = 1;
                            glu = 0;
                            fru = 0;
                            option1.setText(R.string.maltose);
                            mat_unit = (" g");
                            name_plur = ("Maltose");
                            break;

                        case R.id.glucose:
                            suc = 0;
                            mal = 0;
                            glu = 1;
                            fru = 0;
                            option1.setText(R.string.glucose);
                            mat_unit = (" g");
                            name_plur = ("Glucose");
                            break;

                        case R.id.fructose:
                            suc = 0;
                            mal = 0;
                            glu = 0;
                            fru = 1;
                            option1.setText(R.string.fructose);
                            mat_unit = (" g");
                            name_plur = ("Fructose");
                            break;

                        case R.id.apple1:
                            suc = 0.0161;
                            mal = 0.0014;
                            glu = 0.0268;
                            fru = 0.0636;
                            option1.setText(R.string.apple);
                            mat_unit = (" g");
                            name_plur = ("Apples");
                            break;

                        case R.id.banana1:
                            suc = 0.0239;
                            mal = 0.001;
                            glu = 0.0498;
                            fru = 0.0485;
                            option1.setText(R.string.banana_cavendish);
                            mat_unit = (" g");
                            name_plur = ("Bananas");
                            break;

                        case R.id.wblueberry:
                            suc = 0.0001;
                            mal = 0.0;
                            glu = 0.0310;
                            fru = 0.0335;
                            option1.setText(R.string.blueberry_wild);
                            mat_unit = (" g");
                            name_plur = ("Blueberries");
                            break;

                        case R.id.br_sugar:
                            suc = 0.9456;
                            mal = 0;
                            glu = 0.0135;
                            fru = 0.0111;
                            option1.setText(R.string.brown_sugar);
                            mat_unit = (" g");
                            name_plur = ("Brown sugar");
                            break;


                        case R.id.honey1:
                            suc = 0.013;
                            mal = 0.071;
                            glu = 0.313;
                            fru = 0.382;
                            option1.setText(R.string.honey1);
                            mat_unit = (" g");
                            name_plur = ("Honey");
                            break;

                        case R.id.msyrup1:
                            suc = 0.5832;
                            mal = 0.0;
                            glu = 0.0160;
                            fru = 0.0052;
                            option1.setText(R.string.msyrup1);
                            mat_unit = (" g");
                            name_plur = ("Maple syrup");
                            break;

                        case R.id.molasses1:
                            suc = 0.2940;
                            mal = 0;
                            glu = 0.1192;
                            fru = 0.1279;
                            option1.setText(R.string.molasses);
                            mat_unit = (" g");
                            name_plur=("Molasses");
                            break;

                        case R.id.plum:
                            suc = 0.0157;
                            mal = 0.0008;
                            glu = 0.0507;
                            fru = 0.0307;
                            option1.setText(R.string.plum);
                            mat_unit = (" g");
                            name_plur = ("Plums");
                            break;

                        case R.id.wraspberry:
                            suc = 0.0007;
                            mal = 0.0;
                            glu = 0.0243;
                            fru = 0.0304;
                            option1.setText(R.string.raspberry_wild);
                            mat_unit = (" g");
                            name_plur = ("Raspberries");
                            break;

                        case R.id.strawberry:
                            suc = 0.0047;
                            mal = 0.0;
                            glu = 0.0199;
                            fru = 0.0244;
                            option1.setText(R.string.strawberry);
                            mat_unit = (" g");
                            name_plur = ("Strawberries");
                            break;

                    }
                    return true;
                });
                popupMenu.show();
            });

            Button btn_run = findViewById(R.id.vol_abv_run);
            btn_run.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {

                    String batchstring, abvstring;
                    batchstring = etbatch.getText().toString();
                    abvstring = ettabv.getText().toString();

                    try{

                        double batch, tabv;
                        batch = Double.parseDouble(batchstring);
                        tabv = Double.parseDouble(abvstring);

                        double alc = (((amt*fru)/glu_mol)*(eth_mol*2)/eth_den+
                                ((amt*glu)/glu_mol)*(eth_mol*2)/eth_den+
                                ((amt*mal)/suc_mol)*(eth_mol*4)/eth_den+
                                ((amt*suc)/suc_mol)*(eth_mol*4)/eth_den);

                        // double raw calculates the required amount of raw material.
                        double raw = (batch*(tabv/100)/alc);
                        double t_suc = (suc * raw);
                        double t_mal = (mal * raw);
                        double t_glu = (glu * raw);
                        double t_fru = (fru * raw);
                        double t_alc = (batch * (tabv/100));
                        double t_tot = (t_suc + t_mal + t_glu + t_fru);
                        //Calculating CO2 amount
                        double cdo = (raw*fru/glu_mol*co2_mol*2)+(raw*glu/glu_mol*
                                co2_mol*2)+(raw*mal/suc_mol*co2_mol*4)+(raw*suc/
                                suc_mol*co2_mol*4);

                        double bot250 = (batch / 250);
                        double bot330 = (batch / 330);
                        double bot500 = (batch / 500);
                        double bot700 = (batch / 700);
                        double bot750 = (batch / 750);
                        double bot1000 = (batch / 1000);

                        if (suc == 0 && mal == 0 && glu == 0 && fru == 0){
                            Toast.makeText(target_volume_abv.this,
                                    R.string.select_ingredient, Toast.LENGTH_LONG).show();
                        }
                        else {
                            tvreq.setText(String.format(name_plur + " required:"));
                            reqamt.setText(String.format("%.2f", raw) + mat_unit);
                            tvsucrose.setText(String.format("%.2f", t_suc) + " g");
                            tvmaltose.setText(String.format("%.2f", t_mal) + " g");
                            tvglucose.setText(String.format("%.2f", t_glu) + " g");
                            tvfructose.setText(String.format("%.2f", t_fru) + " g");
                            tvtotal.setText(String.format("%.2f", t_tot) + " g");
                            tvalctot.setText(String.format("%.2f", t_alc) + " mL");
                            tvco2.setText(String.format("%.2f", cdo) + " g");
                            tv250ml.setText(String.format("%.2f", bot250));
                            tv330ml.setText(String.format("%.2f", bot330));
                            tv500ml.setText(String.format("%.2f", bot500));
                            tv700ml.setText(String.format("%.2f", bot700));
                            tv750ml.setText(String.format("%.2f", bot750));
                            tv1000ml.setText(String.format("%.2f", bot1000));
                        }
                    }catch (NumberFormatException e) {
                        Toast.makeText(target_volume_abv.this,
                                R.string.invalid_input, Toast.LENGTH_LONG).show();
                    }
                }
            });
            return insets;
        });
    }
}