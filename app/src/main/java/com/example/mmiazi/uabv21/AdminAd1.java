package com.example.mmiazi.uabv21;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

public class AdminAd1 extends AppCompatActivity {

    TextView adminAd1_title, admin_Name, admin_Review;
    RatingBar ratingbarAdmin1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_ad1);

        adminAd1_title = findViewById(R.id.tv_recAd1);
        admin_Name = findViewById(R.id.tv_adminRev1_Name);
        admin_Review = findViewById(R.id.admin1_review);
        ratingbarAdmin1 = findViewById(R.id.ratingBarRec1);

        admin_Name.setText("Nazmus Sakib");
        admin_Review.setText("Called these guys: the receptionist was super courteous, she responded fast!");
        ratingbarAdmin1.setRating((float) 5);
        ratingbarAdmin1.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        ratingbarAdmin1.setFocusable(false);
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, ShareReview.class);
        startActivity(intent);
        finish();
    }
}
