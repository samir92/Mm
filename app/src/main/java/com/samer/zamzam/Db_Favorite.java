package com.samer.zamzam;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by samer on 5/6/2017.
 */

public class Db_Favorite extends SQLiteOpenHelper {
    public static final String Db_name="favDB";

    public Db_Favorite(Context context) {
        super(context, Db_name,null,1);}

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table IF NOT EXISTS favTable(id INTEGER PRIMARY KEY AUTOINCREMENT,title TEXT) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table IF EXISTS favTable");
        onCreate(db);
    }

    public boolean insert(String title){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cntnt=new ContentValues();
        cntnt.put("title",title);
        Long res=db.insert("favTable",null,cntnt);
        if(res==-1)
            return false;
        else return true;}

    public boolean updateData(String id,String title){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cntnt=new ContentValues();
        cntnt.put("title",title);
        db.update("favTable",cntnt,"id= ?",new String[]{id});
        return true;}

    public Integer Delete(String title){
        SQLiteDatabase db=this.getWritableDatabase();
        return db.delete("favTable","title=?",new String[]{title});}

    public ArrayList getAllFavorite(){
    ArrayList<AppList> DbArray=new ArrayList<>();
    SQLiteDatabase db=this.getReadableDatabase();
    Cursor cursor=db.rawQuery("select * from favTable",null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            int id=cursor.getInt(cursor.getColumnIndex("id"));
            String title=cursor.getString(cursor.getColumnIndex("title"));
            DbArray.add(new AppList(id,title));
            cursor.moveToNext();}
        return DbArray;}


public int getCheckListFavorite(String title){
    SQLiteDatabase db=getReadableDatabase();
    Cursor cursor=db.rawQuery("select * from favTable Where title Like '"+title+"'",null);
    int count=cursor.getCount();
    return count;}


}
