package com.suite.alkie;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.widget.Toast;

public class relative_density extends AppCompatActivity {

    EditText en_ogravity, en_fgravity;
    TextView reldens_output, act_title;
    ImageView infostack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_relative_density);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.RelativeLayout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);

            act_title=findViewById(R.id.tv_act_title);
            act_title.setText(getString(R.string.relative_density));

            infostack=findViewById(R.id.btn_info);
            infostack.setOnClickListener(a -> {
                PopupMenu infomenu = new PopupMenu(relative_density.this, infostack);
                infomenu.getMenuInflater().inflate(R.menu.info_menu, infomenu.getMenu());
                infomenu.setOnMenuItemClickListener(menuItem -> {

                    switch (menuItem.getItemId()){

                        case R.id.about:
                            Intent infabout=new Intent(relative_density.this,
                                    info_about.class);
                            startActivity(infabout);
                            break;

                        case R.id.privacy:
                            Intent infprivacy=new Intent(relative_density.this,
                                    info_privacy.class);
                            startActivity(infprivacy);
                            break;

                        case R.id.accuracy:
                            Intent infaccuracy=new Intent(relative_density.this,
                                    info_accuracy.class);
                            startActivity(infaccuracy);
                            break;

                    }

                    return true;
                });
                infomenu.show();
            });

            return insets;




        });
    }
    @SuppressLint("DefaultLocale")
    public void abvcalc(View view){
        en_ogravity = findViewById(R.id.en_ogravity);
        en_fgravity = findViewById(R.id.en_fgravity);
        reldens_output = findViewById(R.id.reldens_output);

        String ogstring, fgstring;
        ogstring = en_ogravity.getText().toString();
        fgstring = en_fgravity.getText().toString();

        try{
            double og, fg;
            og = Double.parseDouble(ogstring);
            fg = Double.parseDouble(fgstring);

            double abv = ((og - fg) * 131.25);
            double abv2 = ((og - fg) * 133);

            if (abv >= 6){
                reldens_output.setText(String.format("%.2f%%", abv2));
            }
            else{
                reldens_output.setText(String.format("%.2f%%",abv));
            }

        } catch (NumberFormatException e) {
            Toast.makeText(this, R.string.invalid_input, Toast.LENGTH_LONG).show();
        }
    }
}