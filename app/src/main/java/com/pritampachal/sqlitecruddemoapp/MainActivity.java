package com.pritampachal.sqlitecruddemoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    TextView textViewTopBar,textViewAllContacts;
    Button button;
    EditText editText;
    ListView listView;
    DatabaseClass databaseClass;
    List<ProjectClass> list;
    ArrayList<String> arrayList;
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN); //do not show key-board, when Activity-Launch
        textViewTopBar=findViewById(R.id.textViewTopBar);
        textViewAllContacts=findViewById(R.id.textViewAllContacts);
        button=findViewById(R.id.buttonCreateNewContact);
        editText=findViewById(R.id.editTextTextPersonNameSearchContact);
        listView=findViewById(R.id.listViewReadAllContacts);
        textViewTopBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,MainActivity4.class);
                startActivity(intent);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,MainActivity2.class);
                startActivity(intent);
            }
        });
        databaseClass=new DatabaseClass(MainActivity.this);
        list=databaseClass.readAllData();
        arrayList=new ArrayList<>();
        for(ProjectClass projectClass:list) {
            arrayList.add(projectClass.getName()+"\n"+projectClass.getSalary());
        }
        textViewAllContacts.setText("All Contacts ("+databaseClass.getCountTotalData()+")");
        arrayAdapter=new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_expandable_list_item_1,arrayList);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String str=((TextView) view).getText().toString().trim();
                String str1=str.split(Pattern.quote("\n"))[0]; // Name
                String str2=str.split(Pattern.quote("\n"))[1]; // Contact
                Intent intent=new Intent(MainActivity.this,MainActivity3.class);
                intent.putExtra("str1",str1); //.putExtra(Key,Value)
                intent.putExtra("str2",str2);
                startActivity(intent);
            }
        });
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //none
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                MainActivity.this.arrayAdapter.getFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {
                //none
            }
        });
    }
}
