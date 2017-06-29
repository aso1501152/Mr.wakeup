package jp.ac.asojuku.jousisenb.mrwakeup;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by RIE on 2017/06/19.
 */

public class AlarmService  extends Service {
    private static final String TAG = AlarmService.class.getSimpleName();

    @Override
    public void onCreate() {
        Log.e("TAG","AlarmService_onCreate入った");
        Thread thr = new Thread(null, mTask, "AlarmServiceThread");
        thr.start();
        Log.e("TAG","AlarmService_onCreate終わり");
    }

        // アラーム用サービス
        Runnable mTask = new Runnable() {
            public void run() {
                // アラームを受け取るActivityを指定
                Intent alarmBroadcast = new Intent();
                // ここでActionをセットする(Manifestに書いたものと同じであれば何でもよい)
                alarmBroadcast.setAction("AlarmAction");
                // レシーバーへ渡す
                sendBroadcast(alarmBroadcast);
                Log.e("TAG", "レシーバに渡した");
                // 役目を終えたサービスを止める
                AlarmService.this.stopSelf();
                Log.e("TAG", "サービス停止");
            }
        };

    /*
     * 以下はBind時に必要なコード
     *
     * */

    //サービスに接続するためのBinder
    public class ServiceBinder extends Binder {
        //サービスの取得
        AlarmService getService() {
            return AlarmService.this;
        }
    }
    //Binderの生成
    private final IBinder mBinder = new ServiceBinder();

    @Override
    public IBinder onBind(Intent intent) {
        Log.e("TAG", "onBind" + ": " + intent);
        return mBinder;
        //接続時に呼び出される
    }

    @Override
    public void onRebind(Intent intent){
        Log.i(TAG, "onRebind" + ": " + intent);
        //再接続に呼び出される
    }

    @Override
    public boolean onUnbind(Intent intent){
        Log.i(TAG, "onUnbind" + ": " + intent);
        //切断時に呼び出される
        //onUnbindをreturn trueでoverrideすると次回バインド時にonRebildが呼ばれる
        return true;
    }
    @Override
    public void onDestroy() {
        Log.e("TAG", "onDestroy");
    }
}
