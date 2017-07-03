package jp.ac.asojuku.jousisenb.mrwakeup;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.FileNotFoundException;

/**
 * Created by yabumoto on 2017/06/02.
 */

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class DBManager extends SQLiteOpenHelper  {

    private static SQLiteDatabase db;

    public DBManager(Context context) {
        super(context, "Horiguthi", null, 5);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS tabira" +
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " phone_number TEXT," +
                " day TEXT," +
                " setHour TEXT,"   +
                " setMinitue TEXT )");

        db.execSQL("INSERT INTO tabira VALUES(1,'08044445555','2121122','17','00')");

    }

    //ユーザーからセットされた時間を得る
    public String getSetTime(SQLiteDatabase db) {
        String hour = "";
        String minitue = "";
        String result = "";
        String select = "SELECT * FROM tabira WHERE _id = 1";

        SQLiteCursor cursor = (SQLiteCursor)db.rawQuery(select,null);
        if(cursor.getCount() != 0){
            cursor.moveToFirst();
            hour = cursor.getString(3);
            minitue = cursor.getString(4);
        }
        cursor.close();

        result = hour + ":" + minitue;
        return result;
    }


    public SQLiteCursor selectTime(SQLiteDatabase db){
        String selectSQL ="SELECT * FROM tabira WHERE _id=1";
        SQLiteCursor cursor =(SQLiteCursor)db.rawQuery(selectSQL,null);
        return cursor;
    }


    //ユーザーからセットされた時間を得る
    public String getSetHour(SQLiteDatabase db) {
        String result = "";
        String select = "SELECT * FROM tabira WHERE _id = 1";

        SQLiteCursor cursor = (SQLiteCursor)db.rawQuery(select,null);
        if(cursor.getCount() != 0){
            cursor.moveToFirst();
            result = cursor.getString(3);
        }
        cursor.close();
        return result;
    }

    //ユーザーからセットされた時間を得る
    public String getday(SQLiteDatabase db) {
        String result = "";
        String select = "SELECT * FROM tabira WHERE _id = 1";

        SQLiteCursor cursor = (SQLiteCursor)db.rawQuery(select,null);
        if(cursor.getCount() != 0){
            cursor.moveToFirst();
            result = cursor.getString(2);
        }
        cursor.close();
        return result;
    }
    public String getphone(SQLiteDatabase db) {
        String result = "";
        String select = "SELECT * FROM tabira WHERE _id = 1";

        SQLiteCursor cursor = (SQLiteCursor)db.rawQuery(select,null);
        if(cursor.getCount() != 0){
            cursor.moveToFirst();
            result = cursor.getString(1);
        }
        cursor.close();
        return result;
    }

    public String getSetMinitue(SQLiteDatabase db) {
        String result = "";
        String select = "SELECT * FROM tabira WHERE _id = 1";

        SQLiteCursor cursor = (SQLiteCursor)db.rawQuery(select,null);
        if(cursor.getCount() != 0){
            cursor.moveToFirst();
            result = cursor.getString(4);
        }
        cursor.close();
        return result;
    }



    //セットされた曜日を得る
    public String getSetWeek(SQLiteDatabase db) {
        String result = "";
        String select = "SELECT * FROM tabira WHERE _id = 1";
        SQLiteCursor cursor = (SQLiteCursor)db.rawQuery(select,null);
        if(cursor.getCount() != 0){
            cursor.moveToFirst();
            result = cursor.getString(2);
        }
        cursor.close();
        return result;
    }

    //変更処理
    public void sethenkou(SQLiteDatabase db,String hairetu[]){

        String h=hairetu[0];
        String m=hairetu[1];
        String p=hairetu[2];
        String y=hairetu[3];


        db.execSQL("UPDATE tabira SET phone_number="+p+", setHour="+h+", setMinitue="+m+", day="+y+" WHERE _id=1 ");
    }

    //データベース削除
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE tabira");
        onCreate(db);
    }
}
