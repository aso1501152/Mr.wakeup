package jp.ac.asojuku.jousisenb.mrwakeup;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by yabumoto on 2017/06/02.
 */

public class DBManager extends SQLiteOpenHelper{

    public DBManager(Context context){
        super(context,"Horiguthi",null,4);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS tabira" +
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " phone_number TEXT," +
                " day TEXT," +
                " time TEXT)");

        db.execSQL("INSERT INTO tabira VALUES(1,'08044445555','0101100','1700'");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE tabira");
        onCreate(db);
    }
}
