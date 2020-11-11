package com.example.xiaozuoye2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {
    int paiming=41;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent intent=getIntent();
        int name=intent.getIntExtra("name", 0);
        int number=intent.getIntExtra("number", 0);
        Toast.makeText(this,"姓名："+name+" 学号："+number+" 排名："+paiming, Toast.LENGTH_LONG).show();

        Button b2=(Button)findViewById(R.id.act2);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity2.this,MainActivity.class);
                intent.putExtra("paiming",paiming);
                setResult(0,intent);
                finish();
            }
        });
    }
}
