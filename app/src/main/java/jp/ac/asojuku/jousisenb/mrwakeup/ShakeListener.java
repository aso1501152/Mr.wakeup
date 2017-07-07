
package jp.ac.asojuku.jousisenb.mrwakeup;

import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;

// SensorListenerインターフェイスをimplementsする。
/*
onSensorChanged(int sensor, float values[])メソッドとonAccuracyChanged(int sensor, int accuracy)メソッドを実装。
onSensorChangedはセンサーの値が変更される毎に呼び出される。
sensorはセンサーを識別する整数。valuesはデータ自体を表現する浮動小数点数で、センサーによって提供される値の数は異なる。
onAccuracyChangedはセンサーの精度が変わると呼ばれる。今回は使わない。
*/
public class ShakeListener extends Activity implements SensorListener {

    private static final int FORCE_THRESHOLD = 350;
    private static final int TIME_THRESHOLD = 100;
    private static final int SHAKE_TIMEOUT = 500;
    private static final int SHAKE_DURATION = 100;
    private static final int SHAKE_COUNT = 80;

    private SensorManager mSensorManager;
    private float mLastX = -1.0f, mLastY = -1.0f, mLastZ = -1.0f;
    private long mLastTime;
    private OnShakeListener mShakeListener;
    private Context mContext;
    private int mShakeCount = 0;
    private long mLastShake;
    private long mLastForce;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shake);
    }


    @Override
    public void onAccuracyChanged(int sensor, int accuracy) {

    }

    @Override
    public void onSensorChanged(int sensor, float[] values) {
//SensorManager.SENSOR_ACCELEROMETER＝加速度センサーでなければreturnする。
        if ( sensor != SensorManager.SENSOR_ACCELEROMETER ) return;
//System.currentTimeMillisを使い現在時刻をミリ秒で取得。
        long now = System.currentTimeMillis();
//最後に動かしてから500ミリ秒経過していたら、連続していないのでカウントを0に戻す。
        if ( ( now - mLastForce ) > SHAKE_TIMEOUT ) {
            mShakeCount = 0;
        }
//最後に振ってから100ミリ秒経っていたら以下の処理。
        if ( ( now - mLastTime ) > TIME_THRESHOLD ) {
            long diff = now - mLastTime;
//端末の加速度から前回の加速度＝if ( speed > 350 )を引いたものをMath.absで絶対値にする。それを経過時間で割ったものに10000を掛けて10秒間でどれだけ加速したかを求めているよう。
/*
XYZ軸の概念は
http://seesaawiki.jp/w/moonlight_aska/d/%B2%C3%C2%AE%C5%D9%A5%BB%A5%F3%A5%B5%A1%BC%A4%CE%C3%CD%A4%F2%BC%E8%C6%C0%A4%B9%A4%EB
が参考になりました。
*/
            float speed = Math.abs(values[SensorManager.DATA_X] +
                    values[SensorManager.DATA_Y] +
                    values[SensorManager.DATA_Z] -
                    mLastX - mLastY - mLastZ ) / diff * 10000;
/*350より大きい速度で、振られたのが3回目（以上）でかつ、最後にシェイクを検知してから100ミリ秒以上経っていたら
今の時間を残して、シェイク回数を0に戻す。onShakeメソッドを呼ぶ。
*/
            if ( speed > FORCE_THRESHOLD ) {
                if ( ( ++mShakeCount >= SHAKE_COUNT ) && now - mLastShake > SHAKE_DURATION ) {
                    mLastShake = now;
                    mShakeCount = 0;
                    if ( mShakeListener != null ) {
                        mShakeListener.onShake();
                    }
                }
                mLastForce = now;
            }
            mLastTime = now;
            mLastX = values[SensorManager.DATA_X];
            mLastY = values[SensorManager.DATA_Y];
            mLastZ = values[SensorManager.DATA_Z];
        }
    }

    public interface OnShakeListener {
        public void onShake();
    }

    public ShakeListener ( Context context ) {
        mContext = context;
        resume();
    }

    public void setOnShakeListener ( OnShakeListener listener ) {
        mShakeListener = listener;
    }

    public void resume () {
        mSensorManager = (SensorManager) mContext.getSystemService(SENSOR_SERVICE);
        if ( mSensorManager == null ) {
            throw new UnsupportedOperationException("Sensor not suported");
        }
        boolean supported = mSensorManager.registerListener(this,
                SensorManager.SENSOR_ACCELEROMETER,
                SensorManager.SENSOR_DELAY_GAME);
        if ( !supported ) {
            mSensorManager.unregisterListener(this, SensorManager.SENSOR_ACCELEROMETER);
            throw new UnsupportedOperationException("Acceleroneter not supported");
        }
    }

    public void pause() {
        if ( mSensorManager != null ) {
            mSensorManager.unregisterListener(this, SensorManager.SENSOR_ACCELEROMETER);
            mSensorManager = null;
        }
    }
}