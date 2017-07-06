package jp.ac.asojuku.jousisenb.mrwakeup;

import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.PowerManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

public class AlarmNortificationActivity extends AppCompatActivity {

    private PowerManager.WakeLock wakelock;
    private KeyguardManager.KeyguardLock keylock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarm_notification);


        // スクリーンロックを解除する
        // 権限が必要
        //スリープ状態から復帰する
        wakelock = ((PowerManager) getSystemService(Context.POWER_SERVICE))
                .newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK
                        | PowerManager.ACQUIRE_CAUSES_WAKEUP
                        | PowerManager.ON_AFTER_RELEASE, "disableLock");
        wakelock.acquire();

        //スクリーンロックを解除する
        KeyguardManager keyguard = (KeyguardManager) getSystemService(Context.KEYGUARD_SERVICE);
        keylock = keyguard.newKeyguardLock("disableLock");
        keylock.disableKeyguard();


        Log.e("TAG","画面起動");
        Intent intent = new Intent(AlarmNortificationActivity.this, Shake.class);
        startActivity(intent);
        AlarmNortificationActivity.this.finish();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }
    @Override
    public void onPause() {
        super.onPause();

        wakelock.release();
        keylock.reenableKeyguard();
    }
}

