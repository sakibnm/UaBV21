package com.example.mmiazi.uabv21;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

public class AdminAd2 extends AppCompatActivity {

    TextView adminAd2_title, admin_Name, admin_Review2;
    RatingBar ratingbarAdmin2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_ad2);

        adminAd2_title = findViewById(R.id.tv_recAd2);
        admin_Name = findViewById(R.id.tv_adminRev2_Name);
        admin_Review2 = findViewById(R.id.admin2_review);
        ratingbarAdmin2 = findViewById(R.id.ratingBarRec2);
        admin_Name.setText("Nazmus Sakib");
        admin_Review2.setText("Delicious vegan and vegetarian options for lunch!");
        ratingbarAdmin2.setRating((float) 5);
        ratingbarAdmin2.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        ratingbarAdmin2.setFocusable(false);
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, ShareReview.class);
        startActivity(intent);
        finish();
    }
}
