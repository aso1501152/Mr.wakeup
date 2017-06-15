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

public class DBManager extends SQLiteOpenHelper {

    private static SQLiteDatabase db;

    public DBManager(Context context) {
        super(context, "Horiguthi", null, 4);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS tabira" +
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " phone_number TEXT," +
                " day TEXT," +
                " time TEXT)");

        db.execSQL("INSERT INTO tabira VALUES(1,'08044445555','0101100','17:00')");

    }

    //ユーザーからセットされた時間を得る
    public String getSetTime(SQLiteDatabase db) {
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

    //idが１のデータを全て取り、G002に送る
    public SQLiteCursor selectTime(SQLiteDatabase db){
        String selectSQL ="SELECT * FROM tabira WHERE _id=1";
        SQLiteCursor cursor =(SQLiteCursor)db.rawQuery(selectSQL,null);
        return cursor;

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE tabira");
        onCreate(db);
    }
}
