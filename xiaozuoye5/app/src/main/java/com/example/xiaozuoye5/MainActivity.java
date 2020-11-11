package com.example.xiaozuoye5;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView textView = (TextView) findViewById(R.id.n);
        final Handlertext handler = new Handlertext(textView);

        final Runnable myWorker =new Runnable() {
            @Override
            public void run() {
                int progress = 0;
                while(progress <= 100){
                    Message msg = new Message();
                    msg.arg1 = progress;
                    handler.sendMessage(msg);
                    progress += 10;
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Message msg = handler.obtainMessage();//同 new Message();
                msg.arg1 = -1;
                handler.sendMessage(msg);
            }
        };

        Button b=findViewById(R.id.start);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Thread workThread = new Thread(null, myWorker, "WorkThread");
                workThread.start();
            }
        });
    }
}
class Handlertext extends Handler {
    TextView tv;
    Handlertext(TextView tv){
        this.tv=tv;
    }
    public void handleMessage(@NonNull Message msg) {
        super.handleMessage(msg);
        tv.setText("数字："+msg.arg1);
    }
}