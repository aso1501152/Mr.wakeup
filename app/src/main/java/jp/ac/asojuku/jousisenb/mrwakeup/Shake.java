package jp.ac.asojuku.jousisenb.mrwakeup;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


public class Shake extends AppCompatActivity {


    private ShakeListener mShaker;

    private MediaPlayer mp ;
    private String path;


    private SQLiteDatabase sqlDB;
    DBManager dbm;


    /** スレッドUI操作用ハンドラ */
    private Handler mHandler = new Handler();
    /** テキストオブジェクト */
    private Runnable updateText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shake);

        //リソースファイルから再生
        mp = MediaPlayer.create(this, R.raw.shake2);
        mp.setLooping(true);
        mp .seekTo(0);
        mp.start();

        updateText = new Runnable() {
            public void run() {
                // DBManager のインスタンス生成
                dbm = new DBManager(Shake.this);
                sqlDB = dbm.getWritableDatabase();

                String phone2=dbm.getphone(sqlDB);

                Intent intent = new Intent(
                        Intent.ACTION_CALL,
                        Uri.parse("tel:0"+phone2)
                );
                startActivity(intent);
                mp.stop();
            }
        };
        mHandler.postDelayed(updateText, 30000);
    }


    @Override
    protected void onResume() {
        super.onResume();

        mShaker = new ShakeListener(this);
        mShaker.setOnShakeListener(new ShakeListener.OnShakeListener() {
            @Override
            public void onShake() {

                Intent intent =new Intent(Shake.this,MainActivity.class);
                startActivity(intent);

                mp.release();
                mp = null;
            }
        });
    }
}