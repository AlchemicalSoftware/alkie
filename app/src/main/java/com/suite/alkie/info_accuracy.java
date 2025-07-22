package com.suite.alkie;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class info_accuracy extends AppCompatActivity {

    WebView accuracy;
    ImageView infostack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_info_accuracy);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);

            accuracy = findViewById(R.id.wv_accuracy);
            accuracy.loadUrl("https://sites.google.com/view/alkieapp/home/accuracy");

            infostack = findViewById(R.id.btn_info);

            infostack.setOnClickListener(a -> {
                PopupMenu infomenu = new PopupMenu(info_accuracy.this, infostack);
                infomenu.getMenuInflater().inflate(R.menu.info_menu, infomenu.getMenu());
                infomenu.setOnMenuItemClickListener(menuItem -> {

                    switch (menuItem.getItemId()){

                        case R.id.about:
                            Intent infabout=new Intent(info_accuracy.this,
                                    info_about.class);
                            startActivity(infabout);
                            break;

                        case R.id.privacy:
                            Intent infprivacy=new Intent(info_accuracy.this,
                                    info_privacy.class);
                            startActivity(infprivacy);
                            break;

                        case R.id.accuracy:
                            Toast.makeText(info_accuracy.this,
                                    R.string.already_there, Toast.LENGTH_LONG).show();
                            break;

                    }

                    return true;
                });
                infomenu.show();
            });

            return insets;
        });
    }
}