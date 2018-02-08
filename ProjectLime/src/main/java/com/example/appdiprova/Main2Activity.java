package com.example.appdiprova;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        Button bt = findViewById(R.id.bt_console);

        //registra la callback dell'evento del click (codice eseguito al click)
        bt.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        Intent ac = new Intent(getApplicationContext(), OwnerConsoleActivity.class);
                        startActivity(ac);
                    }
                }
        );
    }

    @Override
    protected void onStop(){
        super.onStop();
    }

}
