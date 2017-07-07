package jp.ac.asojuku.jousisenb.mrwakeup;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


public class Shake extends AppCompatActivity {

        private SQLiteDatabase sqlDB;
    DBManager dbm;


    private ShakeListener mShaker;

    private MediaPlayer mp ;
    private String path;

    /** スレッドUI操作用ハンドラ */
    private Handler mHandler = new Handler();
    /** テキストオブジェクト */
    private Runnable updateText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shake);

        //リソースファイルから再生
        mp = MediaPlayer.create(this, R.raw.shake);
        mp.start();

        updateText = new Runnable() {
            public void run() {
                Intent intent =new Intent(Shake.this,G002.class);
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
            String phone2=dbm.getphone(sqlDB);

           Intent intent = new Intent(
                        Intent.ACTION_CALL,
                        Uri.parse("tel:"+phone2));

                startActivity(intent);

                mp.stop();
            }
        });
    }
}
