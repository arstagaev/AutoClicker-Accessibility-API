package com.revolve44.serviceaccess1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.revolve44.serviceaccess1.floatingframe.FloatingView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button start;
    Button pause;
    //FrameLayout mLayout;
    private static final int SYSTEM_ALERT_WINDOW_PERMISSION = 2084;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        start = findViewById(R.id.button);
//        pause = findViewById(R.id.button2);
//
//        start.setOnClickListener(this);
//        pause.setOnClickListener(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(this)) {
            askPermission();
        }
        findViewById(R.id.startFloat).setOnClickListener(this);
    }

    private void askPermission() {
        Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                Uri.parse("package:" + getPackageName()));
        startActivityForResult(intent, SYSTEM_ALERT_WINDOW_PERMISSION);
    }


    @Override
    public void onClick(View view) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            startService(new Intent(MainActivity.this, FloatingView.class));
            finish();
        } else if (Settings.canDrawOverlays(this)) {
            startService(new Intent(MainActivity.this, FloatingView.class));
            finish();
        } else {
            askPermission();
            Toast.makeText(this, "You need System Alert Window Permission to do this", Toast.LENGTH_SHORT).show();
        }
//        if (view == start){
//            startService(new Intent(this, MyAndroidService.class));
//
//
//        }else if (view == pause) {
//            stopService(new Intent(this, MyAndroidService.class));
//        }

    }
}