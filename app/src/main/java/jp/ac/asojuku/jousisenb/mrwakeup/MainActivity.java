package jp.ac.asojuku.jousisenb.mrwakeup;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.app.AlarmManager;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
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


 AlarmManager am;
 private static final String TAG = MainActivity.class.getSimpleName();
 private PendingIntent mAlarmSender;
 private SQLiteDatabase sqlDB;
 DBManager dbm;

 private static final int PREFERENCE_BOOTED = 1;

 @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)

 @Override
 public void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.activity_main);

  //初期化しなくてもいいならエラーになるのでアウト
  Log.e("TAG","初期化開始");
  am = (AlarmManager)MainActivity.this.getSystemService(Context.ALARM_SERVICE);
  Log.e("TAG","初期化終了");


  //Buttonを認識させる
  final Button alarmSetButton = (Button)findViewById(R.id.alarmSetButton);
  // btn1のクリックイベント時の場所を指定(今回はメインページ指定なので「this」を指定している)
  alarmSetButton.setOnClickListener(this);

  // プリファレンスの初期値メソッド呼び出し
  iniSet();
  Log.e("TAG","初期化OK");
 }

 @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
 //初期値設定
 private void iniSet() {
  Log.e("TAG","iniSet入った");
  final Button alarmSetButton = (Button)findViewById(R.id.alarmSetButton);
  // Preferenceの初期値設定
  Log.e("TAG","プリファレンス設定開始");
  SharedPreferences pref = getSharedPreferences("pref",MODE_WORLD_READABLE|MODE_WORLD_WRITEABLE);
  SharedPreferences.Editor e = pref.edit();
  e.putString("flg","off"); //初期値の設定
  //alarmSetButton.setText(R.string.button_start);
  alarmSetButton.setText("START");
  e.commit();
  //初回表示完了
  setState(PREFERENCE_BOOTED);
  Log.e("TAG","プリファレンス初期化OK");
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
  Button alarmSetButton = (Button) findViewById(R.id.alarmSetButton);
  //TextViewにリスナーをセット
  alarmSetButton.setOnClickListener(new Button.OnClickListener() {


   @Override
   public void onClick(View view) {
    Button alarmSetButton = (Button) findViewById(R.id.alarmSetButton);
    SharedPreferences pref = getSharedPreferences("pref", MODE_WORLD_READABLE | MODE_WORLD_WRITEABLE);
    SharedPreferences.Editor e = pref.edit();

    // DBManager のインスタンス生成
    sqlDB = dbm.getWritableDatabase();
    String setHour = dbm.getSetHour(sqlDB);
    String setMinitue = dbm.getSetMinitue(sqlDB);
    int set1 = Integer.parseInt(setHour);
    int set2 = Integer.parseInt(setMinitue);

    // アラーム時間設定
    Calendar cal = Calendar.getInstance();
    cal.setTimeInMillis(System.currentTimeMillis());

    // 設定した時刻をカレンダーに設定
    cal.set(Calendar.HOUR_OF_DAY, set1);
    cal.set(Calendar.MINUTE, set2);
    cal.set(Calendar.SECOND, 0);
    cal.set(Calendar.MILLISECOND, 0);
    // 過去だったら明日にする
    if(cal.getTimeInMillis() < System.currentTimeMillis()){
     cal.add(Calendar.DAY_OF_YEAR, 1);
    }


    // 過去だったら明日にする
    if(cal.getTimeInMillis() < System.currentTimeMillis()){
     cal.add(Calendar.DAY_OF_YEAR, 1);
    }


    Log.e("設定時間",cal.getTimeInMillis()+"ms");

    if(pref.getString("flg", "").equals("off")){
     Log.e("プリファレンスの値(if)", pref.getString("flg",""));

     alarmSetButton.setText(R.string.button_stop);    //セットボタンを「ストップ」に書き換え
     e.putString("flg","on"); //アラームオン
     e.commit();

     // アラームを設定する

     //メモ：第二引数を被らないものにする
     mAlarmSender = PendingIntent.getService(MainActivity.this, 777, new Intent(MainActivity.this, AlarmService.class), PendingIntent.FLAG_UPDATE_CURRENT);

     am.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), mAlarmSender);
     Log.e("TAG","アラームセット完了");


     //doBindService();
     //Log.e("TAG","Bind接続開始");

     //トースト表示
     Toast.makeText(MainActivity.this, String.format("%02d時%02d分にアラームをセットしました", set1, set2), Toast.LENGTH_LONG).show();

    }else{
     Log.e("プリファレンスの値(else)", pref.getString("flg",""));
     /*
     alarmSetButton.setText(R.string.button_start);   //セットボタンを「スタート」に書き換え
     e.putString("flg","off"); //アラームオフ
     e.commit();
     */

     // アラームのキャンセル
     Log.e("TAG", "stopAlarm()");
     MainActivity.this.doUnbindService();

     //もし翌日もアラーム設定されてたらアラームを再セット

     int week = cal.get(Calendar.DAY_OF_WEEK);
     if(week == 1){
      Log.e("TAG","今日は日曜日");
     }else if(week == 2){
      Log.e("TAG","今日は月曜日");
     }else if(week == 3){
      Log.e("TAG","今日は火曜日");
     }else if(week == 4){
      Log.e("TAG","今日は水曜日");
     }else  if(week == 5){
      Log.e("TAG","今日は木曜日");
     }else  if(week == 6){
      Log.e("TAG","今日は金曜日");
     }else  if(week == 7) {
      Log.e("TAG","今日は土曜日");
     }

     int nextday = week+1;

     // DBManager のインスタンス生成
     dbm = new DBManager(MainActivity.this);
     sqlDB = dbm.getWritableDatabase();

     String setWeek = dbm.getSetWeek(sqlDB);
     Log.e("TAG", setWeek);
     String setWeek2 = setWeek.substring(week, nextday);
     Log.e("TAG", setWeek2);


     if(setWeek2.equals("2")) {
      alarmSetButton.setText("STOP");
      e.putString("flg","on"); //初期値の設定
      Toast.makeText(MainActivity.this, "翌日もアラームが設定されています", Toast.LENGTH_SHORT).show();
     }/*else if(setWeek2.equals("1")){
      e.putString("flg","off"); //初期値の設定
      alarmSetButton.setText("START");
     }*/
     e.commit();
    }

   }
  });

 }

 //取得したServiceの保存
 private AlarmService mBoundService;
 private boolean mIsBound;

 private ServiceConnection mConnection = new ServiceConnection() {
  public void onServiceConnected(ComponentName className, IBinder service) {

   // サービスとの接続確立時に呼び出される
   //Toast.makeText(MainActivity.this, "Activity:onServiceConnected",Toast.LENGTH_SHORT).show();

   // サービスにはIBinder経由で#getService()してダイレクトにアクセス可能
   mBoundService = ((AlarmService.ServiceBinder)service).getService();

   //必要であればmBoundServiceを使ってバインドしたサービスへの制御を行う
  }

  public void onServiceDisconnected(ComponentName className) {
   // サービスとの切断(異常系処理)
   // プロセスのクラッシュなど意図しないサービスの切断が発生した場合に呼ばれる。
   mBoundService = null;
   Toast.makeText(MainActivity.this, "Activity:onServiceDisconnected",
           Toast.LENGTH_SHORT).show();
  }
 };

 void doBindService() {
  //サービスとの接続を確立する。明示的にServiceを指定
  //(特定のサービスを指定する必要がある。他のアプリケーションから知ることができない = ローカルサービス)
  bindService(new Intent(MainActivity.this, AlarmService.class), mConnection, Context.BIND_AUTO_CREATE);
  mIsBound = true;
 }

 void doUnbindService() {
  if (mIsBound) {
   // コネクションの解除
   unbindService(mConnection);
   mIsBound = false;
   Log.e("TAG", "アンバインドしました");
  }
 }

 @Override
 public void onClick(View v) {

 }

 public void OnUnbindService(){

 }
}
