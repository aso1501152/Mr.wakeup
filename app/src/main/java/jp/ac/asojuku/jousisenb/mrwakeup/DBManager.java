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

public class DBManager extends SQLiteOpenHelper {

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

        db.execSQL("INSERT INTO tabira VALUES(1,'08044445555','0101100','1700')");

    }

    //ユーザーからセットされた時間を得る
    public String getSetTime(SQLiteDatabase db) {
        String result = "";
        String select = "SELECT time FROM tabira WHERE _id = '1'";

        SQLiteCursor cursor = (SQLiteCursor)db.rawQuery(select,null);
        if(cursor.getCount() != 0){
            cursor.moveToFirst();
            result = cursor.getString(1);//ここでエラー
        }
        cursor.close();
        return result;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE tabira");
        onCreate(db);
    }
}
