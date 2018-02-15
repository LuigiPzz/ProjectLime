package com.app.projectLime;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;


public class BrandConsoleActivity extends AppCompatActivity {

    View.OnClickListener getImageBtnOnClick = new View.OnClickListener() {
        public void onClick(View view) {
            Intent ac = new Intent(getApplicationContext(), ConsoleMicrosoftActivity.class);
            startActivity(ac);

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand_console);

        ImageView getImageButton = findViewById(R.id.logomicrosoft);
        getImageButton.setOnClickListener(getImageBtnOnClick);

    }
}
