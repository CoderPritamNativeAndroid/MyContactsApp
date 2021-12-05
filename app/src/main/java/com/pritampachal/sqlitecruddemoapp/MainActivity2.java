package com.pritampachal.sqlitecruddemoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {
    EditText editTextName,editTextContact;
    Button buttonSave,buttonReset;
    ProjectClass projectClass;
    DatabaseClass databaseClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        editTextName=findViewById(R.id.editTextTextPersonNameCreateName);
        editTextContact=findViewById(R.id.editTextTextPersonNameCreateContact);
        buttonSave=findViewById(R.id.buttonCreateSave);
        buttonReset=findViewById(R.id.buttonCreateReset);
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editTextName.setText("");
                editTextContact.setText("");
                editTextName.requestFocus();
            }
        });
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editTextName.getText().toString().trim().equals("")) {
                    Toast.makeText(MainActivity2.this, "Name field Required", Toast.LENGTH_SHORT).show();
                } else if(editTextContact.getText().toString().trim().equals("")) {
                    Toast.makeText(MainActivity2.this, "Contact field Required", Toast.LENGTH_SHORT).show();
                } else {
                    projectClass=new ProjectClass();
                    String str1=editTextName.getText().toString().trim();
                    String str2=editTextContact.getText().toString().trim();
                    projectClass.setName(str1);
                    projectClass.setSalary(str2);
                    databaseClass=new DatabaseClass(MainActivity2.this);
                    int int1=databaseClass.getCountTotalData();
                    databaseClass.createOneData(projectClass);
                    int int2=databaseClass.getCountTotalData();
                    if(int1!=int2) {
                        Toast.makeText(MainActivity2.this, "SAVE Successfully", Toast.LENGTH_LONG).show();
                        editTextName.setText("");
                        editTextContact.setText("");
                        editTextName.requestFocus();
                    } else {
                        editTextName.requestFocus();
                        Toast.makeText(MainActivity2.this, "This NAME already Exist", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}
