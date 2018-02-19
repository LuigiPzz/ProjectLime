package com.app.projectLime;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class RatingActivity extends AppCompatActivity {

    private static TextView txt_ratingBar;
    private static RatingBar ratingBar;
    private static Button bt_ratingApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);

        listenerForRatingBar();
        onButtonClickListener();
    }

    public void listenerForRatingBar(){
        ratingBar = findViewById(R.id.ratingBar);
        txt_ratingBar = findViewById(R.id.txt_ratingBar);

        ratingBar.setOnRatingBarChangeListener(
                new RatingBar.OnRatingBarChangeListener() {
                    @Override
                    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                        txt_ratingBar.setText(String.valueOf(rating));
                    }
                }
        );
    }

    public void onButtonClickListener(){
        ratingBar = findViewById(R.id.ratingBar);
        bt_ratingApp = findViewById(R.id.bt_ratingApp);

        bt_ratingApp.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(RatingActivity.this,
                                String.valueOf(ratingBar.getRating()),
                                Toast.LENGTH_SHORT).show();
                    }
                }
        );

    }
}
