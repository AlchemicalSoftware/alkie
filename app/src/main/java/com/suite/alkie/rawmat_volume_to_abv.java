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

public class rawmat_volume_to_abv extends AppCompatActivity {

    EditText targvol, rawmat;
    TextView estabv, matunit, tvsucrose, tvmaltose, tvglucose, tvfructose, tvethanol, tvco2,
            fermcarbs, bot250, bot330, bot500, bot700, bot750, bot1000, act_title;
    Button option1, btn_run;
    ImageView infostack;
    String name_plur;
    double eth_den = 0.78945;
    double suc = 0, mal = 0, glu = 0, fru = 0;
    double suc_mol = 342.30, glu_mol = 180.156, eth_mol = 46.096, co2_mol = 44.009;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_rawmat_volume_to_abv);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.RelativeLayout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);

            targvol = findViewById(R.id.et_targvol2);
            rawmat = findViewById(R.id.et_rawmat2);
            matunit = findViewById(R.id.tv_matunit_2);
            estabv = findViewById(R.id.tv_abv);
            option1 = findViewById(R.id.btn_matselect_4);
            btn_run = findViewById(R.id.btn_run_4);
            tvsucrose = findViewById(R.id.tv_sucrose5);
            tvmaltose = findViewById(R.id.tv_maltose5);
            tvglucose = findViewById(R.id.tv_glucose5);
            tvfructose = findViewById(R.id.tv_fructose5);
            tvethanol = findViewById(R.id.tv_alc_tot5);
            tvco2 = findViewById(R.id.tv_co25);
            fermcarbs = findViewById(R.id.tv_fermcarbs);
            bot250 = findViewById(R.id.bot5_250ml);
            bot330 = findViewById(R.id.bot5_330ml);
            bot500 = findViewById(R.id.bot5_500ml);
            bot700 = findViewById(R.id.bot5_700ml);
            bot750 = findViewById(R.id.bot5_750ml);
            bot1000 = findViewById(R.id.bot5_1000ml);

            act_title=findViewById(R.id.tv_act_title);
            act_title.setText(getString(R.string.raw_material_amp_volume_to_abv));

            infostack=findViewById(R.id.btn_info);
            infostack.setOnClickListener(a -> {
                PopupMenu infomenu = new PopupMenu(rawmat_volume_to_abv.this, infostack);
                infomenu.getMenuInflater().inflate(R.menu.info_menu, infomenu.getMenu());
                infomenu.setOnMenuItemClickListener(menuItem -> {

                    switch (menuItem.getItemId()){

                        case R.id.about:
                            Intent infabout=new Intent(rawmat_volume_to_abv.this,
                                    info_about.class);
                            startActivity(infabout);
                            break;

                        case R.id.privacy:
                            Intent infprivacy=new Intent(rawmat_volume_to_abv.this,
                                    info_privacy.class);
                            startActivity(infprivacy);
                            break;

                        case R.id.accuracy:
                            Intent infaccuracy=new Intent(rawmat_volume_to_abv.this,
                                    info_accuracy.class);
                            startActivity(infaccuracy);
                            break;

                    }

                    return true;
                });
                infomenu.show();
            });

            option1.setOnClickListener(a -> {
                PopupMenu select = new PopupMenu(rawmat_volume_to_abv.this, option1);
                select.getMenuInflater().inflate(R.menu.raw_mats_popup, select.getMenu());
                select.setOnMenuItemClickListener(menuItem -> {

                    switch (menuItem.getItemId()) {

                        case R.id.sucrose:
                            suc = 1;
                            mal = 0;
                            glu = 0;
                            fru = 0;
                            option1.setText(R.string.sucrose);
                            matunit.setText("g");
                            name_plur = ("Sucrose");
                            break;

                        case R.id.maltose:
                            suc = 0;
                            mal = 1;
                            glu = 0;
                            fru = 0;
                            option1.setText(R.string.maltose);
                            matunit.setText("g");
                            name_plur = ("Maltose");
                            break;

                        case R.id.glucose:
                            suc = 0;
                            mal = 0;
                            glu = 1;
                            fru = 0;
                            option1.setText(R.string.glucose);
                            matunit.setText("g");
                            name_plur = ("Glucose");
                            break;

                        case R.id.fructose:
                            suc = 0;
                            mal = 0;
                            glu = 0;
                            fru = 1;
                            option1.setText(R.string.fructose);
                            matunit.setText("g");
                            name_plur = ("Fructose");
                            break;

                        case R.id.apple1:
                            suc = 0.0161;
                            mal = 0.0014;
                            glu = 0.0268;
                            fru = 0.0636;
                            option1.setText(R.string.apple);
                            matunit.setText("g");
                            name_plur = ("Apples");
                            break;

                        case R.id.banana1:
                            suc = 0.0239;
                            mal = 0.001;
                            glu = 0.0498;
                            fru = 0.0485;
                            option1.setText(R.string.banana_cavendish);
                            matunit.setText("g");
                            name_plur = ("Bananas");
                            break;

                        case R.id.wblueberry:
                            suc = 0.0001;
                            mal = 0.0;
                            glu = 0.0310;
                            fru = 0.0335;
                            option1.setText(R.string.blueberry_wild);
                            matunit.setText("g");
                            name_plur = ("Blueberries");
                            break;

                        case R.id.br_sugar:
                            suc = 0.9456;
                            mal = 0;
                            glu = 0.0135;
                            fru = 0.0111;
                            option1.setText(R.string.brown_sugar);
                            matunit.setText("g");
                            name_plur = ("Brown sugar");
                            break;


                        case R.id.honey1:
                            suc = 0.013;
                            mal = 0.071;
                            glu = 0.313;
                            fru = 0.382;
                            option1.setText(R.string.honey1);
                            matunit.setText("g");
                            name_plur = ("Honey");
                            break;

                        case R.id.msyrup1:
                            suc = 0.5832;
                            mal = 0.0;
                            glu = 0.0160;
                            fru = 0.0052;
                            option1.setText(R.string.msyrup1);
                            matunit.setText("g");
                            name_plur = ("Maple syrup");
                            break;

                        case R.id.molasses1:
                            suc = 0.2940;
                            mal = 0;
                            glu = 0.1192;
                            fru = 0.1279;
                            option1.setText(R.string.molasses);
                            matunit.setText("g");
                            name_plur = ("Molasses");
                            break;

                        case R.id.plum:
                            suc = 0.0157;
                            mal = 0.0008;
                            glu = 0.0507;
                            fru = 0.0307;
                            option1.setText(R.string.plum);
                            matunit.setText("g");
                            name_plur = ("Plums");
                            break;

                        case R.id.wraspberry:
                            suc = 0.0007;
                            mal = 0.0;
                            glu = 0.0243;
                            fru = 0.0304;
                            option1.setText(R.string.raspberry_wild);
                            matunit.setText("g");
                            name_plur = ("Raspberries");
                            break;

                        case R.id.strawberry:
                            suc = 0.0047;
                            mal = 0.0;
                            glu = 0.0199;
                            fru = 0.0244;
                            option1.setText(R.string.strawberry);
                            matunit.setText("g");
                            name_plur = ("Strawberries");
                            break;
                    }

                    return true;
                });
                select.show();
            });

            btn_run.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {

                    String volstring, rawstring;
                    volstring = targvol.getText().toString();
                    rawstring = rawmat.getText().toString();

                    try{

                        double targ_vol = Double.parseDouble(volstring);
                        double amt = Double.parseDouble(rawstring);

                        double alc = (((amt*fru)/glu_mol)*(eth_mol*2)/eth_den+
                                ((amt*glu)/glu_mol)*(eth_mol*2)/eth_den+
                                ((amt*mal)/suc_mol)*(eth_mol*4)/eth_den+
                                ((amt*suc)/suc_mol)*(eth_mol*4)/eth_den);

                        double cdo = (amt*fru/glu_mol*co2_mol*2)+(amt*glu/glu_mol*
                                co2_mol*2)+(amt*mal/suc_mol*co2_mol*4)+(amt*suc/
                                suc_mol*co2_mol*4);

                        double abv = ((alc / targ_vol) * 100);

                        double t_suc = (suc * amt);
                        double t_mal = (mal * amt);
                        double t_glu = (glu * amt);
                        double t_fru = (fru * amt);
                        double ferms = (t_suc + t_mal + t_glu + t_fru);

                        double cal250 = (targ_vol / 250);
                        double cal330 = (targ_vol / 330);
                        double cal500 = (targ_vol / 500);
                        double cal700 = (targ_vol / 700);
                        double cal750 = (targ_vol / 750);
                        double cal1000 = (targ_vol / 1000);

                        if (suc == 0 && mal == 0 && glu == 0 && fru == 0){
                            Toast.makeText(rawmat_volume_to_abv.this,
                                    R.string.select_ingredient, Toast.LENGTH_LONG).show();
                        }
                        else {
                            estabv.setText(String.format("%.2f", abv) + " %");
                            tvsucrose.setText(String.format("%.2f", t_suc) + " g");
                            tvmaltose.setText(String.format("%.2f", t_mal) + " g");
                            tvglucose.setText(String.format("%.2f", t_glu) + " g");
                            tvfructose.setText(String.format("%.2f", t_fru) + " g");
                            fermcarbs.setText(String.format("%.2f", ferms) + " g");
                            tvethanol.setText(String.format("%.2f", alc) + " mL");
                            tvco2.setText(String.format("%.2f", cdo) + " g");
                            bot250.setText(String.format("%.2f", cal250));
                            bot330.setText(String.format("%.2f", cal330));
                            bot500.setText(String.format("%.2f", cal500));
                            bot700.setText(String.format("%.2f", cal700));
                            bot750.setText(String.format("%.2f", cal750));
                            bot1000.setText(String.format("%.2f", cal1000));
                        }


                    }catch(NumberFormatException e){
                        Toast.makeText(rawmat_volume_to_abv.this, R.string.invalid_input,
                                Toast.LENGTH_LONG).show();
                    }

                }
            });


            return insets;
        });
    }
}