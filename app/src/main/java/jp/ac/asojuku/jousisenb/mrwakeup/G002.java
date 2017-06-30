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


        dbm =new DBManager(this);
        sqlDB =dbm.getWritableDatabase();
        timePicker = (TimePicker) findViewById(R.id.timePicker);

        button = (Button) findViewById(R.id.buttondayo);



        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String[] youbi1;
                youbi1 = new String[7];
                final CheckBox checkBox1 =(CheckBox)findViewById(R.id.checkBox1);
                boolean niti = checkBox1.isChecked();


                if (niti == true){

                    String niti1 ="1";
                    youbi1[0]=niti1;
                }else {
                    String niti1 ="0";
                    youbi1[0]=niti1;
                }

                final CheckBox checkBox2 =(CheckBox)findViewById(R.id.checkBox2);
                boolean getu =checkBox2.isChecked();
                if (getu == true){

                    String getu1 ="1";
                    youbi1[1]=getu1;
                }else {
                    String getu1 ="0";
                    youbi1[1]=getu1;
                }


                final CheckBox checkBox3 =(CheckBox)findViewById(R.id.checkBox3);
                boolean ka =checkBox2.isChecked();
                if (ka == true){

                    String ka1 ="1";
                    youbi1[2]=ka1;
                }else {
                    String ka1 ="0";
                    youbi1[2]=ka1;
                }


                final CheckBox checkBox4 =(CheckBox)findViewById(R.id.checkBox4);
                boolean sui =checkBox2.isChecked();
                if (sui == true){

                    String sui1 ="1";
                    youbi1[3]=sui1;
                }else {
                    String sui1 ="0";
                    youbi1[3]=sui1;
                }


                final CheckBox checkBox5 =(CheckBox)findViewById(R.id.checkBox5);
                boolean moku =checkBox2.isChecked();
                if (moku == true){

                    String moku1 ="1";
                    youbi1[4]=moku1;
                }else {
                    String moku1 ="0";
                    youbi1[4]=moku1;
                }


                final CheckBox checkBox6 =(CheckBox)findViewById(R.id.checkBox6);
                boolean kin =checkBox2.isChecked();
                if (kin == true){

                    String kin1 ="1";
                    youbi1[5]=kin1;
                }else {
                    String kin1 ="0";
                    youbi1[5]=kin1;
                }


                final CheckBox checkBox7 =(CheckBox)findViewById(R.id.checkBox7);
                boolean dou =checkBox2.isChecked();
                if (dou == true){

                    String dou1 ="1";
                    youbi1[6]=dou1;
                }else {
                    String dou1 ="0";
                    youbi1[6]=dou1;
                }



                final EditText c = (EditText) findViewById(R.id.cab);

                TimePicker timePicker1 =(TimePicker)findViewById(R.id.timePicker);
                int hour =timePicker1.getCurrentHour();
                int minutu =timePicker1.getCurrentMinute();


                String h1 = Integer.toString(hour);
                String m1 =Integer.toString(minutu);

                String p=c.getText().toString();


                String hairetu[]= {h1,m1,p};
                dbm.sethenkou(sqlDB,hairetu);

                Toast.makeText(getApplicationContext(),youbi1[0]+youbi1[1]+youbi1[2]+youbi1[3]+youbi1[4]+youbi1[5]+youbi1[6]+"登録完了",Toast.LENGTH_LONG).show();

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

