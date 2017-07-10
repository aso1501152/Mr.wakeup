package jp.ac.asojuku.jousisenb.mrwakeup;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
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
    TimePicker timePicker;


    private SQLiteDatabase sqlDB;
    DBManager dbm;
    Button button;
    private CheckBox checkBox1;
    private CheckBox checkBox2;
    private CheckBox checkBox3;
    private CheckBox checkBox4;
    private CheckBox checkBox5;
    private CheckBox checkBox6;
    private CheckBox checkBox7;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_g002);


        //データベースから初期値を持ってくる
        dbm = new DBManager(this);
        sqlDB = dbm.getWritableDatabase();
        String day2 = dbm.getday(sqlDB);
        String phone2=dbm.getphone(sqlDB);
        String hour=dbm.getSetHour(sqlDB);
        String minute=dbm.getSetMinitue(sqlDB);

        TimePicker timePicker2 = (TimePicker) findViewById(R.id.timePicker);

        int hour3=Integer.parseInt(hour);
        int minute3=Integer.parseInt(minute);

        timePicker2.setCurrentHour(hour3);
        timePicker2.setCurrentMinute(minute3);

        int youbi3 = Integer.parseInt(day2);
        int len = String.valueOf(youbi3).length();
        int d = (int)Math.pow(10, len - 1);

        int[] youbi333;

        youbi333 =new int[7];

        int youbi44=0;


      //桁ごとに配列に代入
        for (int i = 1; i <= len; i++) {

            youbi333[youbi44]=youbi3/d;

            youbi3 %= d;
            d /= 10;

            youbi44 +=1;
        }


        EditText editText2 = (EditText) findViewById(R.id.cab);
        editText2.setText("0"+phone2);


        final CheckBox checkBox1 = (CheckBox) findViewById(R.id.checkBox1);

        if (youbi333[0]==2){
            checkBox1.setChecked(true);
        }else {
            checkBox1.setChecked(false);
        }


        final CheckBox checkBox2 = (CheckBox) findViewById(R.id.checkBox2);


        if (youbi333[1]==2){
            checkBox2.setChecked(true);
        }else {
            checkBox2.setChecked(false);
        }

        final CheckBox checkBox3 = (CheckBox) findViewById(R.id.checkBox3);

        if (youbi333[2]==2){
            checkBox3.setChecked(true);
        }else {
            checkBox3.setChecked(false);
        }

        final CheckBox checkBox4 = (CheckBox) findViewById(R.id.checkBox4);

        if (youbi333[3]==2){
            checkBox4.setChecked(true);
        }else {
            checkBox4.setChecked(false);
        }


        final CheckBox checkBox5 = (CheckBox) findViewById(R.id.checkBox5);

        if (youbi333[4]==2){
            checkBox5.setChecked(true);
        }else {
            checkBox5.setChecked(false);
        }

        final CheckBox checkBox6 = (CheckBox) findViewById(R.id.checkBox6);
        if (youbi333[5]==2){
            checkBox6.setChecked(true);
        }else {
            checkBox6.setChecked(false);
        }

        final CheckBox checkBox7 = (CheckBox) findViewById(R.id.checkBox7);

        if (youbi333[6]==2){
            checkBox7.setChecked(true);
        }else {
            checkBox7.setChecked(false);
        }


        timePicker = (TimePicker) findViewById(R.id.timePicker);

        button = (Button) findViewById(R.id.buttondayo);


        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String[] youbi1;
                youbi1 = new String[7];
                final CheckBox checkBox1 = (CheckBox) findViewById(R.id.checkBox1);
                boolean niti = checkBox1.isChecked();

                //チェックボックスの内容を確認し配列に挿入
                if (niti == true) {

                    String niti1 = "2";
                    youbi1[0] = niti1;
                } else {
                    String niti1 = "1";
                    youbi1[0] = niti1;
                }

                final CheckBox checkBox2 = (CheckBox) findViewById(R.id.checkBox2);
                boolean getu = checkBox2.isChecked();
                if (getu == true) {

                    String getu1 = "2";
                    youbi1[1] = getu1;
                } else {
                    String getu1 = "1";
                    youbi1[1] = getu1;
                }


                final CheckBox checkBox3 = (CheckBox) findViewById(R.id.checkBox3);
                boolean ka = checkBox3.isChecked();
                if (ka == true) {

                    String ka1 = "2";
                    youbi1[2] = ka1;
                } else {
                    String ka1 = "1";
                    youbi1[2] = ka1;
                }


                final CheckBox checkBox4 = (CheckBox) findViewById(R.id.checkBox4);
                boolean sui = checkBox4.isChecked();
                if (sui == true) {

                    String sui1 = "2";
                    youbi1[3] = sui1;
                } else {
                    String sui1 = "1";
                    youbi1[3] = sui1;
                }


                final CheckBox checkBox5 = (CheckBox) findViewById(R.id.checkBox5);
                boolean moku = checkBox5.isChecked();
                if (moku == true) {

                    String moku1 = "2";
                    youbi1[4] = moku1;
                } else {
                    String moku1 = "1";
                    youbi1[4] = moku1;
                }


                final CheckBox checkBox6 = (CheckBox) findViewById(R.id.checkBox6);
                boolean kin = checkBox6.isChecked();
                if (kin == true) {

                    String kin1 = "2";
                    youbi1[5] = kin1;
                } else {
                    String kin1 = "1";
                    youbi1[5] = kin1;
                }


                final CheckBox checkBox7 = (CheckBox) findViewById(R.id.checkBox7);
                boolean dou = checkBox7.isChecked();
                if (dou == true) {

                    String dou1 = "2";
                    youbi1[6] = dou1;
                } else {
                    String dou1 = "1";
                    youbi1[6] = dou1;
                }


                final EditText c = (EditText) findViewById(R.id.cab);


                //登録用時間取得
                TimePicker timePicker1 = (TimePicker) findViewById(R.id.timePicker);
                int hour = timePicker1.getCurrentHour();
                int minutu = timePicker1.getCurrentMinute();

                //int型をString型に変更
                String h1 = Integer.toString(hour);
                String m1 = Integer.toString(minutu);

                String p = c.getText().toString();


                String youbi2 = youbi1[0] + youbi1[1] + youbi1[2] + youbi1[3] + youbi1[4] + youbi1[5] + youbi1[6];
                String hairetu[] = {h1, m1, p, youbi2};
                dbm.sethenkou(sqlDB, hairetu);

                Intent intent =new Intent(G002.this,MainActivity.class);
                startActivity(intent);

            }
        });
    }
}

