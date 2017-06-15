package jp.ac.asojuku.jousisenb.mrwakeup;

import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ListView;

public class G002 extends AppCompatActivity {


    private SQLiteDatabase sqlDB;
    DBManager dbm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_g002);
    }

  private void setTimes(ListView List){
      SQLiteCursor cursor=null;

      //データベース空間オープン
      dbm =new DBManager(this);
      sqlDB =dbm.getWritableDatabase();

      //DBManager.javaで定義したメソッドを呼び出し
      cursor= dbm.selectTime(sqlDB);

      //formの配列に入れる
      String[]form={"phrase"};

      String zikan;
      String dayw;
      String bangou;

      //時間を入れる
      zikan = form[3];
      //曜日を入れる
      dayw=form[2];
      //番号を入れる
      bangou=form[1];

      final EditText editText=(EditText)findViewById(R.id.editText);
      editText.setText(bangou);
  }


}
