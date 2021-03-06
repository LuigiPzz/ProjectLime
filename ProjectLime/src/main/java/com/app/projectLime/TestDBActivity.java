package com.app.projectLime;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
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
    Button bt_viewall;
    Button bt_update;
    Button bt_delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_db);

        appDB = new DatabaseHelper(this);

        editID = findViewById(R.id.editText_id);
        editName = findViewById(R.id.editText_name);
        bt_adddata = findViewById(R.id.bt_adddata);
        bt_viewall = findViewById(R.id.bt_viewall);
        bt_update = findViewById(R.id.bt_update);
        bt_delete = findViewById(R.id.bt_delete);

        AddData();
        UpdateData();
        DeleteData();
        viewAll();
    }

    public void AddData(){
        bt_adddata.setOnClickListener(
            new View.OnClickListener(){

                @Override
                public void onClick(View v){
                    boolean isInserted = appDB.insertData(editName.getText().toString());

                    if(isInserted)
                        Toast.makeText(TestDBActivity.this,"Data Inserted",Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(TestDBActivity.this,"Data not Inserted",Toast.LENGTH_LONG).show();

                }
            }

        );
    }

    public void UpdateData(){
        bt_update.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isUpdated = appDB.updateData(editID.getText().toString(),
                                                                editName.getText().toString());
                    if(isUpdated)
                        Toast.makeText(TestDBActivity.this,"Data Updated",Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(TestDBActivity.this,"Data not Updated",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void DeleteData() {
        bt_delete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Integer deletedRows = appDB.deleteData(editID.getText().toString());
                        if(deletedRows>0)
                            Toast.makeText(TestDBActivity.this,"Data Deleted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(TestDBActivity.this,"Data not Deleted",Toast.LENGTH_LONG).show();

                    }
                }
        );
    }

    public void viewAll(){
        bt_viewall.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor result = appDB.getAllData();
                        if(result.getCount() == 0){
                            //show Error
                            showMessage("ERROR","Nothing found");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while(result.moveToNext()){
                            buffer.append("id :"+result.getString(0) + "\n");
                            buffer.append("name :"+result.getString(1) + "\n\n");
                        }

                        //show all data
                        showMessage("Date: ",buffer.toString());
                    }
                }
        );
    }


    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

}
