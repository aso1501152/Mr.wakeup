package jp.ac.asojuku.jousisenb.mrwakeup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


public class Shake extends AppCompatActivity {


    private ShakeListener mShaker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shake);
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
