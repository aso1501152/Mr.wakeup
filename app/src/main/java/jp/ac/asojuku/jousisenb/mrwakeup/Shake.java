package jp.ac.asojuku.jousisenb.mrwakeup;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


public class Shake extends AppCompatActivity {


    private ShakeListener mShaker;

    private MediaPlayer mp ;
    private String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shake);

        //リソースファイルから再生
        mp = MediaPlayer.create(this, R.raw.SHAKE);
        mp.start();
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
            }
        });
    }
}
