package com.example.guitartuner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.widget.Button;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    Button btn1, btn2, btn3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);

        Button[] buttons = { btn1, btn2, btn3 };

        for (Button btn : buttons) {
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String tuning = btn.getText().toString();
                    Intent intent = new Intent(view.getContext(), TuningActivity.class);
                    intent.putExtra("tuning", tuning);
                    startActivity(intent);
                }
            });
        }
    }
}