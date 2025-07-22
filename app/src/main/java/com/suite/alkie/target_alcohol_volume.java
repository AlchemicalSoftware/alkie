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

public class target_alcohol_volume extends AppCompatActivity {

    Button option1;
    EditText targalc;
    String name_plur, mat_unit;
    TextView tvsucrose, tvmaltose, tvglucose, tvfructose, tvethanol, tvco2, tvtotal, reqamt,
            reqtitle, tv5abv, tv10abv, tv15abv, tv20abv, act_title;
    ImageView infostack;
    double eth_den = 0.78945;
    double suc = 0, mal = 0, glu = 0, fru = 0;
    double suc_mol = 342.30, glu_mol = 180.156, eth_mol = 46.096, co2_mol = 44.009, amt = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_target_alcohol_volume);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.RelativeLayout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);

            option1 = findViewById(R.id.btn_matselect_3);
            reqamt = findViewById(R.id.tv_reqamt2);
            reqtitle = findViewById(R.id.tv_reqtitle);
            targalc = findViewById(R.id.et_targ_alc);
            tvsucrose = findViewById(R.id.tv_sucrose3);
            tvmaltose = findViewById(R.id.tv_maltose3);
            tvglucose = findViewById(R.id.tv_glucose3);
            tvfructose = findViewById(R.id.tv_fructose3);
            tvethanol = findViewById(R.id.tv_alc_tot3);
            tvco2 = findViewById(R.id.tv_co23);
            tvtotal = findViewById(R.id.tv_total3);
            tv5abv = findViewById(R.id.tv_5abv);
            tv10abv = findViewById(R.id.tv_10abv);
            tv15abv = findViewById(R.id.tv_15abv);
            tv20abv = findViewById(R.id.tv_20abv);

            act_title=findViewById(R.id.tv_act_title);
            act_title.setText(getString(R.string.target_alcohol_volume));

            infostack=findViewById(R.id.btn_info);
            infostack.setOnClickListener(a -> {
                PopupMenu infomenu = new PopupMenu(target_alcohol_volume.this, infostack);
                infomenu.getMenuInflater().inflate(R.menu.info_menu, infomenu.getMenu());
                infomenu.setOnMenuItemClickListener(menuItem -> {

                    switch (menuItem.getItemId()){
                        case R.id.about:
                            Intent infabout=new Intent(target_alcohol_volume.this,
                                    info_about.class);
                            startActivity(infabout);
                            break;
                        case R.id.privacy:
                            Intent infprivacy=new Intent(target_alcohol_volume.this,
                                    info_privacy.class);
                            startActivity(infprivacy);
                            break;

                        case R.id.accuracy:
                            Intent infaccuracy=new Intent(target_alcohol_volume.this,
                                    info_accuracy.class);
                            startActivity(infaccuracy);
                            break;
                    }

                    return true;
                });
                infomenu.show();
            });

            option1.setOnClickListener(a ->{
                PopupMenu select = new PopupMenu(target_alcohol_volume.this, option1);
                select.getMenuInflater().inflate(R.menu.raw_mats_popup, select.getMenu());
                select.setOnMenuItemClickListener(menuItem ->{

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
                select.show();
            });

            Button btn_run = findViewById(R.id.btn_targalc_run);
            btn_run.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    try{

                        String targalcstring = targalc.getText().toString();
                        double talcvol = Double.parseDouble(targalcstring);
                        //Calculating ethanol amount.
                        double alc = (((amt*fru)/glu_mol)*(eth_mol*2)/eth_den+
                                ((amt*glu)/glu_mol)*(eth_mol*2)/eth_den+
                                ((amt*mal)/suc_mol)*(eth_mol*4)/eth_den+
                                ((amt*suc)/suc_mol)*(eth_mol*4)/eth_den);
                        double targ_alc = (talcvol / alc);
                        double t_suc = (suc * targ_alc);
                        double t_mal = (mal * targ_alc);
                        double t_glu = (glu * targ_alc);
                        double t_fru = (fru * targ_alc);
                        double t_tot = (t_suc + t_mal + t_glu + t_fru);
                        //Calculating CO2 amount.
                        double cdo = (targ_alc*fru/glu_mol*co2_mol*2)+(targ_alc*glu/glu_mol*
                                co2_mol*2)+(targ_alc*mal/suc_mol*co2_mol*4)+(targ_alc*suc/
                                suc_mol*co2_mol*4);
                        double abv5vol = (talcvol / 5 * 100);
                        double abv10vol = (talcvol / 10 * 100);
                        double abv15vol = (talcvol / 15 * 100);
                        double abv20vol = (talcvol / 20 * 100);

                        if (suc == 0 && mal == 0 && glu == 0 && fru == 0) {
                            Toast.makeText(target_alcohol_volume.this, R.string.select_ingredient,
                                    Toast.LENGTH_LONG).show();
                        }else {

                            reqamt.setText(String.format("%.2f", targ_alc) + mat_unit);
                            reqtitle.setText(String.format(name_plur + " required:"));
                            tvsucrose.setText(String.format("%.2f", t_suc) + " g");
                            tvmaltose.setText(String.format("%.2f", t_mal) + " g");
                            tvglucose.setText(String.format("%.2f", t_glu) + " g");
                            tvfructose.setText(String.format("%.2f", t_fru) + " g");
                            tvethanol.setText(String.format("%.2f", talcvol) + " mL");
                            tvco2.setText(String.format("%.2f", cdo) + " g");
                            tvtotal.setText(String.format("%.2f", t_tot) + " g");
                            tv5abv.setText(String.format("%.2f", abv5vol) + " mL");
                            tv10abv.setText(String.format("%.2f", abv10vol) + " mL");
                            tv15abv.setText(String.format("%.2f", abv15vol) + " mL");
                            tv20abv.setText(String.format("%.2f", abv20vol) + " mL");
                        }
                    }catch (NumberFormatException e){
                        Toast.makeText(target_alcohol_volume.this,
                                R.string.invalid_input, Toast.LENGTH_LONG).show();
                    }
                }
            });
            return insets;
        });
    }
}