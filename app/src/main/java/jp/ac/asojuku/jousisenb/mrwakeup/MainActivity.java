package jp.ac.asojuku.jousisenb.mrwakeup;

import android.content.Intent;
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

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private SQLiteDatabase sqlDB;
    DBManager dbm;

    private static final int PREFERENCE_BOOTED = 1;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //setContentView(R.layout.activity_g002);

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



                }else{
                    Log.v("プリファレンスの値(else)", pref.getString("flg",""));
                    setButton.setText(R.string.button_start);   //セットボタンを「スタート」に書き換え
                    e.putString("flg","off"); //アラームオフ
                    e.commit();
                }
            }
        });
    }
    @Override
    protected void onPause() {
        super.onPause();
        sqlDB.close();
    }

    @Override
    public void onClick(View v) {

    }
}