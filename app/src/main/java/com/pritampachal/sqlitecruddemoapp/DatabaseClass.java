package com.pritampachal.sqlitecruddemoapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

//extends Below class, Below all Functions created, manually by Me
public class DatabaseClass extends SQLiteOpenHelper {
    SQLiteDatabase sqLiteDatabase;
    ContentValues contentValues;
    Cursor cursor;
    List<ProjectClass> list;
    ProjectClass projectClass;
    public DatabaseClass(Context context) {
        super(context,"DatabasePritam.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS table_pritam (id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT UNIQUE,salary TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        if(i!=i1) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS table_pritam");
            onCreate(sqLiteDatabase);
        }
    }
    public void createOneData(ProjectClass projectClass) {
        sqLiteDatabase=this.getWritableDatabase();
        contentValues=new ContentValues();
        contentValues.put("name",projectClass.getName());
        contentValues.put("salary",projectClass.getSalary());
        sqLiteDatabase.insert("table_pritam", null, contentValues);
        sqLiteDatabase.close();
    }
    public List<ProjectClass> readAllData() {
        sqLiteDatabase=this.getReadableDatabase();
        cursor=sqLiteDatabase.rawQuery( "SELECT * FROM table_pritam", null);
        list=new ArrayList<>();
        if(cursor.moveToFirst()) {
            do {
                projectClass=new ProjectClass();
                projectClass.setName(cursor.getString(1)); //here, into Database, 0==id, 1==name, 2==salary
                projectClass.setSalary(cursor.getString(2));
                list.add(projectClass);
            } while(cursor.moveToNext());
        }
        return list;
    }
    public int updateOneData(ProjectClass projectClass) {
        sqLiteDatabase=this.getWritableDatabase();
        contentValues=new ContentValues();
        contentValues.put("salary",projectClass.getSalary());
        return sqLiteDatabase.update("table_pritam",contentValues,"name=?",new String[] { String.valueOf(projectClass.getName()) });
    }

    public void deleteOneData(ProjectClass projectClass) {
        sqLiteDatabase=this.getWritableDatabase();
        sqLiteDatabase.delete("table_pritam","name=?",new String[] { String.valueOf(projectClass.getName()) });
        sqLiteDatabase.close();
    }

    public int getCountTotalData() {
        sqLiteDatabase=this.getReadableDatabase();
        cursor=sqLiteDatabase.rawQuery("SELECT * FROM table_pritam",null);
        return cursor.getCount();
    }
}
