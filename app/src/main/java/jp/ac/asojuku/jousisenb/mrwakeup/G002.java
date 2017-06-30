package jp.ac.asojuku.jousisenb.mrwakeup;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.icu.util.Calendar;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Timer;

public class G002 extends AppCompatActivity {


    private SQLiteDatabase sqlDB;
    DBManager dbm;
    Button button;

    private void setTimes(ListView List){
      //  SQLiteCursor cursor=null;

        //データベース空間オープン
        //dbm =new DBManager(this);
        //sqlDB =dbm.getWritableDatabase();

        //DBManager.javaで定義したメソッドを呼び出し
       // cursor= dbm.selectTime(sqlDB);

        //formの配列に入れる
        //String[]form={"phrase"};

       // String zikan;
       // String dayw;
        //String bangou;

        //時間を入れる
       // zikan = form[3];
        //曜日を入れる
       // dayw=form[2];
        //番号を入れる
       // bangou=form[1];
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_g002);

        //チェックボックスのデータを読み出す
        //final CheckBox checkBox =(CheckBox)findViewById(R.id.checkBox);
        //checkBox.setOnClickListener(new View.OnClickListener(){
        dbm =new DBManager(this);
        sqlDB =dbm.getWritableDatabase();

        button = (Button) findViewById(R.id.buttondayo);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                final EditText a = (EditText) findViewById(R.id.abc);
                final EditText b = (EditText) findViewById(R.id.bca);
                final EditText c = (EditText) findViewById(R.id.cab);


                String h =a.getText().toString();
                String m=b.getText().toString();
                String p=c.getText().toString();


                String hairetu[]= {h,m,p};
                dbm.sethenkou(sqlDB,hairetu);

                Toast.makeText(getApplicationContext(),"登録完了",Toast.LENGTH_LONG).show();

            }
        });


           // @Override
           // public void onClick(View v) {


                //if (checkBox.isChecked()==true){
                    //trueなら1を並べる
                   // youbi +="1";
               // }else {
               //    //falseなら０を入れる
                   // youbi +="0";
                }
           // }
      //  });






        //EditText editText =(EditText)findViewById(R.id.EditText);
       // String text=editText.getText().toString();

       // EditText editText2 =(EditText)findViewById(R.id.EditText2);
       // String text2 =editText.getText().toString();

    }

