package jp.ac.asojuku.jousisenb.mrwakeup;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by yabumoto on 2017/06/02.
 */

public class DBManager extends SQLiteOpenHelper{

    public DBManager(Context context){
        super(context,"Horiguthi",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS Tabira(_id INTEGER PRIMARY KEY AUTOINCREMENT, phone_namber INT,youbi VARCHARA(8),time TIME)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE tabira");
        onCreate(db);
    }
}
