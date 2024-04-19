package com.example.usb;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity3 extends AppCompatActivity {
    EditText editText;
    TextView resultdata;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        Button btnReturn2 = (Button) findViewById(R.id.btnReturn2);
        btnReturn2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),
                        MainActivity2.class);
                finish();
            }
        });

        editText = findViewById(R.id.editText);
        resultdata = findViewById(R.id.resultdata);

        Button tranport = findViewById(R.id.tranport);
        tranport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = editText.getText().toString();
                sendToService(input);
            }
        });

        Intent intent = getIntent();
        processIntent(intent);

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        processIntent(intent);
    }

    public void processIntent(Intent intent) {
        if (intent != null) {
            String command = intent.getStringExtra("command");
            String data = intent.getStringExtra("data");

            if (data != null) {
                resultdata.setText("받은 결과 : " + data);
            }
        }
    }


    public void sendToService(String input) {
        Intent serviceIntent = new Intent(getApplicationContext(), MyService.class);
        serviceIntent.putExtra("command", "show");
        serviceIntent.putExtra("data", input);

        startService(serviceIntent);
    }

}
