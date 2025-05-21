package com.example.hotelmanagementsystem;

import android.os.Bundle;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SplashScreen extends AppCompatActivity {
    ImageView ivLogo;
    TextView tvName, tvSlogan;

    Animation logoAnim, nameAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash_screen);
        init();
        ivLogo.setAnimation(logoAnim);
        tvName.setAnimation(nameAnim);
        tvSlogan.setAnimation(nameAnim);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashScreen.this, MainActivity.class));
                finish();
            }
        }, 2000);
    }


    private void init() {
        ivLogo = findViewById(R.id.ivLogo);
        tvName = findViewById(R.id.tvName);
        tvSlogan = findViewById(R.id.tvDesc);
        logoAnim = AnimationUtils.loadAnimation(this, R.anim.logo_anim);
        nameAnim = AnimationUtils.loadAnimation(this, R.anim.name_anim);

    }
}