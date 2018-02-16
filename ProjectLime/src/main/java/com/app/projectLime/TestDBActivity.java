package com.app.projectLime;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TestDBActivity extends AppCompatActivity {

    DatabaseHelper appDB;
    EditText editID, editName;
    Button bt_adddata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_db);

        appDB = new DatabaseHelper(this);

        editID = findViewById(R.id.editText_id);
        editName = findViewById(R.id.editText_name);
        bt_adddata = findViewById(R.id.bt_adddata);

        AddData();
    }

    public void AddData(){
        bt_adddata.setOnClickListener(
            new View.OnClickListener(){

                @Override
                public void onClick(View v){
                    boolean isInserted = appDB.insertData(editName.getText().toString());

                    if(isInserted=true)
                        Toast.makeText(TestDBActivity.this,"Data Inserted",Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(TestDBActivity.this,"Data not Inserted",Toast.LENGTH_LONG).show();

                }
            }

        );
    }
}
