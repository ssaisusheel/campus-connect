package com.sample.sai.single;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Databasehelper1 extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 3;
    private static final String DATABASE_NAME = "GCM2";
    private static final String TABLE_NAME = "dept";
    public static final String DT = "DT";



    public Databasehelper1(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        // TODO Auto-generated constructor stub
    }


    @Override
    public void onCreate(SQLiteDatabase db1) {
        // TODO Auto-generated method stub

        db1.execSQL("CREATE TABLE " + TABLE_NAME
                + "(KEY_ID INTEGER PRIMARY KEY AUTOINCREMENT, MESSAGE STRING, DT STRING)");

        }

    @Override
    public void onUpgrade(SQLiteDatabase db1, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
          db1.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db1);
    }

    public void insertMsg(String msg) {

        SQLiteDatabase db1 = getWritableDatabase();
        ContentValues cv = new ContentValues();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        cv.put("MESSAGE" , msg);
        cv.put("DT" , dateFormat.format(date).toString());
        Log.d("insert", "got u insert");

         db1.insert(TABLE_NAME, null, cv);
        db1.close();

        }
   /* public int deleteAll(){

        return sqLiteDatabase.delete(TABLE_NAME, null, null);
    }*/

    public Cursor queueAll() {
        SQLiteDatabase db1 = this.getReadableDatabase();
        //String query = "SELECT  *  FROM " + TABLE_NAME;
        String query = "SELECT  *  FROM " + TABLE_NAME  + " ORDER BY " + DT + " DESC ";
        Cursor cursor = db1.rawQuery(query, null);

              return cursor;
    }

    /*public Cursor getAllBooks() {
        //List<message> books = new LinkedList<message>();

        // 1. build the query
        String query = "SELECT  * FROM " + TABLE_NAME;

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        return cursor;

        // 3. go over each row, build book and add it to list
        message book = null;
        if (cursor.moveToFirst()) {
            do {
                book = new message();
                book.setId(Integer.parseInt(cursor.getString(0)));

                book.setTitle(cursor.getString(1));
                //  book.setAuthor(cursor.getString(2));

                // Add book to books
                books.add(book);
            } while (cursor.moveToNext());
        }


        Log.d("getAllBooks()", books.toString());

          return books;
    }*/

}