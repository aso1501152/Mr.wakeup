package jp.ac.asojuku.jousisenb.mrwakeup;

import android.content.Intent;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume(){
        super.onResume();

        //Button
        Button button = (Button)findViewById(R.id.strartbutton);
        //Buttonにリスナーをセット
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //ボタンをタップされたら次の画面を表示
                Intent intent = new Intent(MainActivity.this,G002.class);
                startActivity(intent);
            }
        });
    }
}
