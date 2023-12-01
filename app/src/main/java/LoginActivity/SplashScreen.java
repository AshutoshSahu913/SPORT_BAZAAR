package LoginActivity;

import static android.view.animation.AnimationUtils.loadAnimation;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sportbazaar.R;

@SuppressLint("CustomSplashScreen")
public class SplashScreen extends AppCompatActivity {

    ImageView imageView1;
    Animation upperanimation;

    private static final int timer = 6000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        imageView1 = findViewById(R.id.LogoCar);
        upperanimation = loadAnimation(this, R.anim.upper_animation);
        imageView1.setAnimation(upperanimation);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this, EnterMobileNumber.class);
                startActivity(intent);
            }
        }, timer);

    }
}
