package com.example.assignment1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;

public class FacultyDetails extends AppCompatActivity {

    private ImageView facultyImage;
    private Toolbar toolbar;
    private TextView facultyName;
    private TextView phoneNumber;
    private TextView email;
    private TextView location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_details);

        setupUIViews();
        initToolbar();
        setupDetails();
    }

    private void setupUIViews(){
        toolbar = findViewById(R.id.toolbar_faculty_details);
        facultyImage = findViewById(R.id.ImageViewFaculty2);
        facultyName = findViewById(R.id.textViewFacultyName);
        phoneNumber = findViewById(R.id.textViewPhoneNo);
        email = findViewById(R.id.textViewEmail);
        location = findViewById(R.id.textViewLocationDetail);
    }

    private void initToolbar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Faculty Details");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setupDetails(){
        int faculty_position = FacultyActivity.sharedPreferences.getInt(FacultyActivity.SEL_FACULTY, 0);
        String[] facultyNames = getResources().getStringArray(R.array.faculty_name);
        int[] facultyImages = new int[]{R.drawable.faculty, R.drawable.faculty};
        int[] facultyArray = new int[]{R.array.faculty1, R.array.faculty2};
        String[] facultyDetails = getResources().getStringArray(facultyArray[faculty_position]);
        phoneNumber.setText(facultyDetails[0]);
        email.setText(facultyDetails[1]);
        location.setText(facultyDetails[2]);
        facultyImage.setImageResource(R.drawable.faculty);
        facultyName.setText(facultyNames[faculty_position]);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home : {
                onBackPressed();
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
