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


public class Databasehelper2 extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 3;
    private static final String DATABASE_NAME = "GCM3";
    private static final String TABLE_NAME = "clg";
    //public static final String KEY_ID = "_id";
    //public static final String MESSAGE = "Content";
    //public static final String DT = "Content1";
    public static final String DT = "DT";

    // public static final String KEY_CONTENT = "Content";


   /* private static final String SCRIPT_CREATE_DATABASE =
            "create table if not exists " + TABLE_NAME + " ("
                    + KEY_ID + " integer primary key autoincrement, "
                    + KEY_CONTENT + " atext not null);";*/

    //private SQLiteHelper sqLiteHelper;
    //private SQLiteDatabase sqLiteDatabase;

    //private Context context;

    public Databasehelper2(Context context) {
        //context=c;
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        // TODO Auto-generated constructor stub
    }
  /*  public Databasehelper openToRead() throws android.database.SQLException {
        sqLiteHelper = new SQLiteHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
        sqLiteDatabase = sqLiteHelper.getReadableDatabase();
        return this;
    }
    public Databasehelper openToWrite() throws android.database.SQLException {
        sqLiteHelper = new SQLiteHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
        sqLiteDatabase = sqLiteHelper.getWritableDatabase();
        return this;
    }

    public void close(){
       sqLiteHelper.close();
   }

    public class SQLiteHelper extends SQLiteOpenHelper {

        public SQLiteHelper(Context context, String name,Conte
                            SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }}*/

    @Override
    public void onCreate(SQLiteDatabase db2) {
        // TODO Auto-generated method stub
        // db.execSQL(SCRIPT_CREATE_DATABASE);
        db2.execSQL("CREATE TABLE " + TABLE_NAME
                + "(KEY_ID INTEGER PRIMARY KEY AUTOINCREMENT, MESSAGE STRING, DT STRING)");
        //String sql="CREATE TABLE "+TABLE_NAME+
        //.execSQL("CREATE TABLE " + TABLE_NAME
        // + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, MESSAGE STRING)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db2, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
       // if (oldVersion >= newVersion)
           // return;
        db2.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db2);
    }
    //}

    public void insertMsg(String msg) {

        SQLiteDatabase db2 = getWritableDatabase();

        ContentValues cv = new ContentValues();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();

        //cv.put(KEY_CONTENT, msg);
        cv.put("MESSAGE" , msg);
        cv.put("DT" , dateFormat.format(date).toString());
        Log.d("insert","got u insert");

        // return sqLiteDatabase.insert(TABLE_NAME, null, cv);
        db2.insert(TABLE_NAME, null, cv);
        db2.close();


    }
    public int deleteAll(){
        SQLiteDatabase db2 = this.getReadableDatabase();
        return db2.delete(TABLE_NAME, null, null);
    }
    public Cursor queueAll() {
        SQLiteDatabase db2 = this.getReadableDatabase();

        //String query = "SELECT  *  FROM " + TABLE_NAME;
        String query = "SELECT  *  FROM " + TABLE_NAME  + " ORDER BY " + DT + " DESC ";
        Cursor cursor = db2.rawQuery(query, null);
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

        // return books
        return books;
    }*/

}