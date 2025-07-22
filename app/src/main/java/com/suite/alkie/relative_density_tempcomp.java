package com.suite.alkie;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class relative_density_tempcomp extends AppCompatActivity {

    RadioButton radcelsius;
    RadioButton radfahrenheit;
    TextView tempsymbol, tempsymbol2, tempsymbol3, ogcorr, fgcorr, abvview, act_title;
    ImageView infostack;
    RadioGroup radtempunits;
    EditText caltemp, calog, calogtemp, calfg, calfgtemp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_relative_density_tempcomp);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.RelativeLayout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);

            radcelsius = findViewById(R.id.rbtn_celsius);
            radfahrenheit = findViewById(R.id.rbtn_fahrenheit);
            radtempunits = findViewById(R.id.rad_group_temp);
            tempsymbol = findViewById(R.id.tv_tempsymbol);
            tempsymbol2 = findViewById(R.id.tv_tempsymbol2);
            tempsymbol3 = findViewById(R.id.tv_tempsymbol3);
            caltemp = findViewById(R.id.et_caltemp);
            act_title=findViewById(R.id.tv_act_title);

            act_title.setText(getString(R.string.temperature_compensated_nrelative_density));;

            infostack=findViewById(R.id.btn_info);
            infostack.setOnClickListener(a -> {
                PopupMenu infomenu = new PopupMenu(relative_density_tempcomp.this, infostack);
                infomenu.getMenuInflater().inflate(R.menu.info_menu, infomenu.getMenu());
                infomenu.setOnMenuItemClickListener(menuItem -> {

                    switch (menuItem.getItemId()){

                        case R.id.about:
                            Intent infabout=new Intent(relative_density_tempcomp.this,
                                    info_about.class);
                            startActivity(infabout);
                            break;

                        case R.id.privacy:
                            Intent infprivacy=new Intent(relative_density_tempcomp.this,
                                    info_privacy.class);
                            startActivity(infprivacy);
                            break;

                        case R.id.accuracy:
                            Intent infaccuracy=new Intent(relative_density_tempcomp.this,
                                    info_accuracy.class);
                            startActivity(infaccuracy);
                            break;

                    }

                    return true;
                });
                infomenu.show();
            });

            radtempunits.setOnCheckedChangeListener((group, checkedId) -> {
                RadioButton ts = findViewById(checkedId);
                if (ts == radcelsius){
                    tempsymbol.setText("°C");
                    tempsymbol2.setText("°C");
                    tempsymbol3.setText("°C");
                    caltemp.setText("20");
                }else if (ts == radfahrenheit){
                    tempsymbol.setText("°F");
                    tempsymbol2.setText("°F");
                    tempsymbol3.setText("°F");
                    caltemp.setText("68");
                }

            });

            return insets;
        });
    }

    @SuppressLint("DefaultLocale")
    public void reldenstempcomp(View view){

        calog = findViewById(R.id.et_calOG);
        calogtemp = findViewById(R.id.et_OGtemp);
        calfg = findViewById(R.id.et_calFG);
        calfgtemp = findViewById(R.id.et_FGtemp);
        ogcorr = findViewById(R.id.tv_corOG);
        fgcorr = findViewById(R.id.tv_corFG);
        abvview = findViewById(R.id.tv_abvview);
        radcelsius = findViewById(R.id.rbtn_celsius);
        radfahrenheit = findViewById(R.id.rbtn_fahrenheit);
        radtempunits = findViewById(R.id.rad_group_temp);

        String caltempstring, ogstring, ogtempstring, fgstring, fgtempstring;
        caltempstring = caltemp.getText().toString();
        ogstring = calog.getText().toString();
        ogtempstring = calogtemp.getText().toString();
        fgstring = calfg.getText().toString();
        fgtempstring = calfgtemp.getText().toString();

        try{
            double cal_t, og, ogtemp, fg, fgtemp;
            cal_t = Double.parseDouble(caltempstring);
            og = Double.parseDouble(ogstring);
            ogtemp = Double.parseDouble(ogtempstring);
            fg = Double.parseDouble(fgstring);
            fgtemp = Double.parseDouble(fgtempstring);

            if (radcelsius.isChecked()){
                cal_t = ((cal_t * 9/5) + 32);
                ogtemp = ((ogtemp * 9/5) + 32);
                fgtemp = ((fgtemp * 9/5) + 32);
            }

            double og_corr, fg_corr;
            og_corr = (og * ((1.00130346 - 0.000134722124 * ogtemp + 0.00000204052596
                    * Math.pow(ogtemp, 2) - 0.00000000232820948 * Math.pow(ogtemp, 3)
                    / (1.00130346 - 0.000134722124 * cal_t + 0.00000204052596
                    * Math.pow(cal_t, 2) - 0.00000000232820948 * Math.pow(cal_t, 3)))));

            fg_corr = (fg * ((1.00130346 - 0.000134722124 * fgtemp + 0.00000204052596
                    * Math.pow(fgtemp, 2) - 0.00000000232820948
                    * Math.pow(fgtemp, 3) / (1.00130346 - 0.000134722124
                    * cal_t + 0.00000204052596 * Math.pow(cal_t, 2) - 0.00000000232820948
                    * Math.pow(cal_t, 3)))));

            double abv = ((og_corr - fg_corr) * 131.25);
            double abv2 = ((og_corr - fg_corr) * 133);

            if (abv >= 6){
                ogcorr.setText(String.format("%.3f", og_corr));
                fgcorr.setText(String.format("%.3f", fg_corr));
                abvview.setText(String.format("%.2f%%", abv2));

            }else{
                ogcorr.setText(String.format("%.3f", og_corr));
                fgcorr.setText(String.format("%.3f", fg_corr));
                abvview.setText(String.format("%.2f%%", abv));
            }

        } catch (NumberFormatException e) {
            Toast.makeText(relative_density_tempcomp.this,
                    R.string.invalid_input, Toast.LENGTH_LONG).show();
        }


    }
}