package com.example.handlerhw2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final String MSG_KEY = "msgKey";
    private final CustomHandler customHandler = new CustomHandler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sendMessage(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Message msg = customHandler.obtainMessage();
                Bundle bundle = new Bundle();
                bundle.putString(MSG_KEY, "Above button has been clicked");
                msg.setData(bundle);
                customHandler.sendMessage(msg);
            }
        }).start();
    }
    class CustomHandler extends Handler{
        @Override
        public void handleMessage(@NonNull Message msg) {
            Bundle bundle = msg.getData();
            String string = bundle.getString(MSG_KEY);
            Toast.makeText(getApplicationContext() ,string, Toast.LENGTH_SHORT).show();
        }
    }
}
