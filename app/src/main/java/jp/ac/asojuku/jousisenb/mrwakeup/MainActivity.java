package jp.ac.asojuku.jousisenb.mrwakeup;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.app.AlarmManager;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;


import java.util.Calendar;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener{


    Context c;
    AlarmManager am;
    private static final String TAG = MainActivity.class.getSimpleName();
    private PendingIntent mAlarmSender;
    private SQLiteDatabase sqlDB;
    DBManager dbm;

    private static final int PREFERENCE_BOOTED = 1;

    @Override
    public void onClick(View v) {

    }
    /*
    public void onCreate(){
        Log.v("TAG","テスト");
    }
/**/
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /******
        // 初期化
        this.c = c;
        Log.v(TAG,"初期化前");
        am = (AlarmManager)c.getSystemService(Context.ALARM_SERVICE);
        Log.v(TAG,"初期化完了");

        //setContentView(R.layout.activity_g002);
***********/
        //Buttonを認識させる
        final Button setButton = (Button)findViewById(R.id.setbutton);
        // btn1のクリックイベント時の場所を指定(今回はメインページ指定なので「this」を指定している)
        setButton.setOnClickListener(this);

        // 初期化メソッド呼び出し
        iniSet();
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    //初期値設定　Preferenceを0に設定
    private void iniSet() {
        final Button setButton = (Button)findViewById(R.id.setbutton);
        // Preferenceの初期値設定
        SharedPreferences pref = getSharedPreferences("pref",MODE_WORLD_READABLE|MODE_WORLD_WRITEABLE);
        SharedPreferences.Editor e = pref.edit();
        e.putString("flg","on"); //初期値の設定。0はアラームオフ
        //setButton.setText(R.string.button_start);]
        setButton.setText("First");
        e.commit();
        //初回表示完了
        setState(PREFERENCE_BOOTED);
    }

    private void setState(int preferenceBooted) {
    }

    @Override
    protected void onResume() {
        super.onResume();

        // DBManager のインスタンス生成
        dbm = new DBManager(this);
        sqlDB = dbm.getWritableDatabase();
        String time = dbm.getSetTime(sqlDB);

        //時計をクリックしたら設定画面に飛ぶように設定
        TextView clock = (TextView) findViewById(R.id.setTime);
        clock.setText(time);  //DBから取得したやつをセットしたい
        //TextViewにリスナーをセット
        clock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //時計をタップされたら次の画面を表示
                Intent intent = new Intent(MainActivity.this, G002.class);
                startActivity(intent);
            }
        });

        //ボタンに関する処理
        Button setButton = (Button) findViewById(R.id.setbutton);
        //TextViewにリスナーをセット
        setButton.setOnClickListener(new Button.OnClickListener() {


            @Override
            public void onClick(View view) {
                Button setButton = (Button) findViewById(R.id.setbutton);
                SharedPreferences pref = getSharedPreferences("pref", MODE_WORLD_READABLE | MODE_WORLD_WRITEABLE);
                //String str = pref.getString("flg", "");
                SharedPreferences.Editor e = pref.edit();

                if(pref.getString("flg", "").equals("off")){ //int型で比較させる
                    Log.v("プリファレンスの値(if)", pref.getString("flg",""));

                    setButton.setText(R.string.button_stop);    //セットボタンを「ストップ」に書き換え
                    e.putString("flg","on"); //アラームオン
                    e.commit();

                    // DBManager のインスタンス生成
                    sqlDB = dbm.getWritableDatabase();
                    String setHour = dbm.getSetHour(sqlDB);
                    String setMinitue = dbm.getSetMinitue(sqlDB);
                    int set1 = Integer.parseInt(setHour);
                    int set2 = Integer.parseInt(setMinitue);

                    Log.v("a","テスト2");

                    /**************
                    // アラームを設定する
                    mAlarmSender = getPendingIntent();

                     ********/

                    // アラーム時間設定
                    Calendar cal = Calendar.getInstance();
                    cal.setTimeInMillis(System.currentTimeMillis());

                    // 設定した時刻をカレンダーに設定(今は手動で設定するためコメントアウト)

                    cal.set(Calendar.HOUR_OF_DAY, set1);
                    cal.set(Calendar.MINUTE, set2);
                    cal.set(Calendar.SECOND, 0);
                    cal.set(Calendar.MILLISECOND, 0);


/******************************************************************
                    cal.set(Calendar.HOUR_OF_DAY, 14);
                    cal.set(Calendar.MINUTE, 0);
                    cal.set(Calendar.SECOND, 0);
                    cal.set(Calendar.MILLISECOND, 0);


                    //設定時刻にアラームをセット
                    am.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), mAlarmSender);
                    Log.v(TAG, cal.getTimeInMillis()+"ms");


                    //トースト表示
                    Toast.makeText(getApplicationContext(), "アラームセット", Toast.LENGTH_SHORT).show();

 ****/

                }else{
                    Log.v("プリファレンスの値(else)", pref.getString("flg",""));
                    setButton.setText(R.string.button_start);   //セットボタンを「スタート」に書き換え
                    e.putString("flg","off"); //アラームオフ
                    e.commit();

                    // アラームのキャンセル
                    Log.d(TAG, "stopAlarm()");
                   /// am.cancel(mAlarmSender);
                }
            }
        });

    }
    @Override
    protected void onPause() {
        super.onPause();
        sqlDB.close();
    }
/****
    @Override
    public void onClick(View v) {

    }
    private PendingIntent getPendingIntent() {
        // アラーム時に起動するアプリケーションを登録
        Intent intent = new Intent(c, AlarmService.class);
        PendingIntent pendingIntent = PendingIntent.getService(c, PendingIntent.FLAG_ONE_SHOT, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        return pendingIntent;
    }
 **********************************************/
}