package com.suite.alkie;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class weight_vol_abv extends AppCompatActivity {

    EditText volume, weight;
    TextView abvview;
    Button btn_run;
    double ethden_std = 0.78945;
    double eth_ex_coef = 0.00109;
    double watden_std = 0.99823;
    double wat_ex_coef = 0.000214;
    //both of these densities are at 20 degrees Celsius.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_weight_vol_abv);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.RelativeLayout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);

            volume=findViewById(R.id.et_wva_volume);
            weight=findViewById(R.id.et_wva_weight);
            abvview=findViewById(R.id.tv_wva_abv);
            btn_run=findViewById(R.id.btn_weight_vol_run);

            btn_run.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {

                    try{

                        String volumestring = volume.getText().toString();
                        String weightstring = weight.getText().toString();
                        double vl = Double.parseDouble(volumestring);
                        double wt = Double.parseDouble(weightstring);
                        double sample_density = (wt / vl);
                        double sample_density_adj = sample_density;



                        if (sample_density >= 0.99823){
                            sample_density_adj = sample_density + 2;
                        }



                        double density_diff = (watden_std - ethden_std);
                        double x_sample = (watden_std - sample_density);
                        double x_percentage = ((x_sample / density_diff) * 100);

                        abvview.setText(String.format("%.2f", x_percentage));

                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }

                }
            });

            return insets;
        });
    }
}