package com.example.appdiprova;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ConsoleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_console);

        String[] console_list=new String[]{"Atari","Sony","Microsoft","Nintendo","Sega"};
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, R.layout.console_row,console_list);
        ListView listView = (ListView) findViewById(R.id.listviewConsole);
        listView.setAdapter(adapter);


    }
}
