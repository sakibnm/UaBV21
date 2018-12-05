package com.example.mmiazi.uabv21;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    private ImageView userPhoto;
    private EditText et_Name;
    private EditText et_Email;
    private Button createButton;
    private Button cancelButton;
    private Bitmap bitmap;
    private User user;
    FirebaseDatabase database;
    FirebaseStorage storage;
    private static int CAM_REQ = 0x1111;
    ProgressBar progressBar;
    ConstraintLayout backAlert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        database = FirebaseDatabase.getInstance();
        storage = FirebaseStorage.getInstance();
        setTitle("Be a Beacon!");

        userPhoto = findViewById(R.id.pick_camera);
        et_Name = findViewById(R.id.text_Name);
        et_Email = findViewById(R.id.text_Email);
        createButton = findViewById(R.id.button_register);
        cancelButton = findViewById(R.id.button_cancel);
        progressBar = findViewById(R.id.progressBar2);
        backAlert = findViewById(R.id.backAlert);

        userPhoto.setOnClickListener(new TakePhoto());
        cancelButton.setOnClickListener(this);
        createButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.button_cancel){
            Log.d("test","Cancel pressed");
            finish();
        } else if(view.getId()==R.id.button_register){
            final String userName = et_Name.getText().toString().trim();
            final String email = et_Email.getText().toString().trim();
            boolean inputValid = true;

            if (android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {

            }
            else{
                et_Email.setError("Put a valid Email address!");
//                Toast.makeText(this,"Please put a valid Email address!!!", Toast.LENGTH_SHORT).show();
                inputValid = false;
            }


            if(userName.equals("")||email.equals("")||bitmap==null) {
                inputValid = false;
                Toast.makeText(this,"Please take a photo, and fill up all the fields", Toast.LENGTH_SHORT).show();

            }
            if(inputValid){
                //ProgressBar..........
                backAlert.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.VISIBLE);

                //Upload image....
                Bitmap userPhotoBitmap = bitmap;
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                userPhotoBitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                final byte[] bytes = stream.toByteArray();
                StorageReference userPhotoReference = storage.getReference().child("photos/photo.jpg");
                UploadTask uploadTask = userPhotoReference.putBytes(bytes);
                uploadTask.addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle unsuccessful uploads
                        exception.printStackTrace();
                    }
                }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // taskSnapshot.getMetadata() contains file metadata such as size, content-type, etc.
                        // ...
                        Log.d("testy","Uploaded: "+taskSnapshot.getTotalByteCount());

                        user = new User(userName, email, "photos/user.png");
                        userSignUp(user);
                        progressBar.setVisibility(View.GONE);
                        backAlert.setVisibility(View.GONE);

                        Intent intent = new Intent(SignUp.this, ShareReview.class);
                        startActivity(intent);

                    }
                });


                //((ImageView)getView().findViewById(R.id.testView)).setImageBitmap(userPhoto);

            }

        }
    }
    private void userSignUp(User user) {
        Log.d("testy", user.getUserEmail() + " " + user.getUserName());

        DatabaseReference myRef = database.getReference("message");

        myRef.setValue(user);
    }
    class TakePhoto implements ImageButton.OnClickListener {

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, CAM_REQ);
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAM_REQ) {
            bitmap = (Bitmap) data.getExtras().get("data");
            userPhoto.setImageBitmap(bitmap);

        }
    }
    private boolean isConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo == null || !networkInfo.isConnected() ||
                (networkInfo.getType() != ConnectivityManager.TYPE_WIFI
                        && networkInfo.getType() != ConnectivityManager.TYPE_MOBILE)) {
            return false;
        }
        return true;
    }
}
