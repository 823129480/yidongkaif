package com.example.jisuanshushu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    static final String UPPER_NUM="upper";
    EditText etNum;
    CalThread calThread;
    class CalThread extends Thread {
        android.os.Handler mHandler;

        @Override
        public void run() {
            Looper.prepare();
            mHandler = new android.os.Handler() {
                @Override
                public void handleMessage(Message msg) {
                    if (msg.what == 0x123) {
                        int i = 2;
                        String jieguo="";
                        int upper = msg.getData().getInt(UPPER_NUM);
                        outer:
                        for (i = 2; i < upper; i++) {
                            if (upper % i == 0) {
                                jieguo = "不是素数";
                                break;
                            }
                        }
                        if(i >= upper)
                            jieguo="是素数";
                        Toast.makeText(MainActivity.this,jieguo, Toast.LENGTH_LONG).show();
                    }
                }
            };
            Looper.loop();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etNum=(EditText)findViewById(R.id.etNum);
        Button bn=(Button)findViewById(R.id.button);
        calThread = new CalThread();
        calThread.start();
        bn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Message msg = new Message();
                msg.what = 0x123;
                Bundle bundle = new Bundle();
                bundle.putInt(UPPER_NUM, Integer.parseInt(etNum.getText().toString()));
                msg.setData(bundle);
                calThread.mHandler.sendMessage(msg);

            }
        });

    }
}