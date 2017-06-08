package jp.ac.asojuku.jousisenb.mrwakeup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume(){
        super.onResume();

        //時計をクリックしたら設定画面に飛ぶように設定
        TextView textView = (TextView)findViewById(R.id.setTime);
        //TextViewにリスナーをセット
        textView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //時計をタップされたら次の画面を表示
                Intent intent = new Intent(MainActivity.this,G002.class);
                startActivity(intent);
            }
        });

        //Buttonを認識させる
        final Button setButton = (Button)findViewById(R.id.setbutton);
        //Button buttonAction = (Button)findViewById(R.id.strartbutton);
        //ボタンにリスナーをセット
        setButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //タップされたらボタンの文字を変更
                setButton.setText(R.string.button_stop);
            }
        });
    }
}
