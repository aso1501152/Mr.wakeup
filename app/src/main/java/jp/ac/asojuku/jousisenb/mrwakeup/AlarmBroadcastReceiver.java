package jp.ac.asojuku.jousisenb.mrwakeup;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class AlarmBroadcastReceiver extends BroadcastReceiver {

    private static final String TAG = AlarmBroadcastReceiver.class.getSimpleName();
    private Context application;


            @Override
            public void onReceive(Context context, Intent intent) {
                // toast で受け取りを確認
                Toast.makeText(context, "Received ", Toast.LENGTH_LONG).show();

                // アラームを受け取って起動するActivityを指定、起動
                Intent notification = new Intent(context, AlarmNortificationActivity.class);
                // 画面起動に必要
                notification.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(notification);
        }
    }