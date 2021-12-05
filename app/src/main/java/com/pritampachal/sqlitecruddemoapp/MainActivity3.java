package com.pritampachal.sqlitecruddemoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity3 extends AppCompatActivity {
    TextView textView;
    EditText editText;
    Button buttonUpdate,buttonDelete;
    ProjectClass projectClass;
    DatabaseClass databaseClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        textView=findViewById(R.id.textViewUpdateName);
        editText=findViewById(R.id.editTextTextPersonNameUpdateContact);
        buttonUpdate=findViewById(R.id.buttonUpdate);
        buttonDelete=findViewById(R.id.buttonDelete);
        String str1=getIntent().getStringExtra("str1"); //name
        String str2=getIntent().getStringExtra("str2"); //contact
        textView.setText(str1);
        editText.setText(str2);
        editText.requestFocus();
        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editText.getText().toString().trim().equals("")) {
                    Toast.makeText(MainActivity3.this, "Contact field Required", Toast.LENGTH_SHORT).show();
                } else {
                    projectClass=new ProjectClass();
                    projectClass.setName(str1);
                    projectClass.setSalary(editText.getText().toString().trim());
                    databaseClass=new DatabaseClass(MainActivity3.this);
                    int int1=databaseClass.updateOneData(projectClass);
                    Toast.makeText(MainActivity3.this, int1+" Contact UPDATE Successfully", Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(MainActivity3.this,MainActivity.class);
                    startActivity(intent);
                }
            }
        });
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity3.this, "DELETE Successfully\n"+str1, Toast.LENGTH_LONG).show();
                projectClass=new ProjectClass();
                projectClass.setName(str1);
                databaseClass=new DatabaseClass(MainActivity3.this);
                databaseClass.deleteOneData(projectClass);
                Intent intent=new Intent(MainActivity3.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
