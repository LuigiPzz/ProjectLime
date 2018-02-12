package com.example.appdiprova;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button bt = findViewById(R.id.bt_accedi);

        //registra la callback dell'evento del click (codice eseguito al click)
        bt.setOnClickListener(
            new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    Intent ac = new Intent(getApplicationContext(), PrimaActivity.class);
                    startActivity(ac);
                }
            }
        );

    }

}
