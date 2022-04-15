package com.main.auth;

import androidx.appcompat.app.AppCompatActivity;
import static android.content.ContentValues.TAG;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

public class ProfileActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    TextView username ;
    Button logoutBtn;
    ImageView profileImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        mAuth = FirebaseAuth.getInstance();
        getSupportActionBar().hide();

        FirebaseUser currentuser = mAuth.getCurrentUser();
        profileImage = findViewById(R.id.profilePic);
        username = findViewById(R.id.username);
        Log.i(TAG, "user = " + currentuser.getDisplayName());
        username.setText(currentuser.getDisplayName());

        final int radius = 500;
        final int margin = 5;
        final Transformation transformation = new RoundedTransformation(radius, margin);

        Picasso.get().load(currentuser.getPhotoUrl()).transform(transformation).into(profileImage);
        logoutBtn = findViewById(R.id.logOutBtn);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext() , MainActivity.class);
                startActivity(intent);
                finish();
                mAuth.signOut();
            }
        });


    }
}