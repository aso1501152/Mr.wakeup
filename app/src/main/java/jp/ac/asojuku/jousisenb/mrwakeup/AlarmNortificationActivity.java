package jp.ac.asojuku.jousisenb.mrwakeup;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class AlarmNortificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_nortification);

        Handler hdl = new Handler();
        // 第２引数で切り替わる秒数(ミリ秒)を指定、今回は1秒
        hdl.postDelayed(new splashHandler(), 1000);
    }
    // splashHandlerクラス
    class splashHandler implements Runnable {
        public void run() {
            Intent intent = new Intent(AlarmNortificationActivity.this, G002.class);
            startActivity(intent);
            AlarmNortificationActivity.this.finish();
        }
    }
}

