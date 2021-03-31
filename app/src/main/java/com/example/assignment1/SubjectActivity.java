package com.example.assignment1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.assignment1.Utils.LetterImageViews;

public class SubjectActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ListView listView;
    private String[] subjects;
    public static SharedPreferences subjectPreferences;
    public static String SUB_PREF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);

        setupUIViews();
        initToolbar();
        setupListView();
    }


    private void setupUIViews(){
        toolbar = findViewById(R.id.toolbar_subject);
        listView = findViewById(R.id.ListView_subject);
        subjectPreferences = getSharedPreferences("Subjects", MODE_PRIVATE);
    }

    private void initToolbar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Subject");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setupListView(){
        subjects = getResources().getStringArray(R.array.Subjects);

        SubjectAdapter subjectAdapter = new SubjectAdapter(this, R.layout.activity_subject_single_item, subjects);

        listView.setAdapter(subjectAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:{
                        subjectPreferences.edit().putString(SUB_PREF, "JavaProgramming").apply();
                        Intent intent = new Intent(SubjectActivity.this, Subject_detail.class);
                        startActivity(intent);
                        break;
                    }
                    case 1:{
                        subjectPreferences.edit().putString(SUB_PREF, "ResearchMethods").apply();
                        Intent intent = new Intent(SubjectActivity.this, Subject_detail.class);
                        startActivity(intent);
                        break;
                    }
                    case 2:{
                        subjectPreferences.edit().putString(SUB_PREF, "BusinessModelling").apply();
                        Intent intent = new Intent(SubjectActivity.this, Subject_detail.class);
                        startActivity(intent);
                        break;
                    }
                }
            }
        });
    }

    public class SubjectAdapter extends ArrayAdapter {

        private int resource;
        private LayoutInflater layoutInflater;
        private String[] subjects;

        public SubjectAdapter(Context context, int resource, String[] objects) {
            super(context, resource, objects);
            this.resource = resource;
            this.subjects = objects;
            layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            ViewHolder holder;
            if(convertView == null){
                holder = new ViewHolder();
                convertView = layoutInflater.inflate(resource, null);
                holder.imageViews_logo = convertView.findViewById(R.id.ImageViewLetterSubject);
                holder.TextViewSubject = convertView.findViewById(R.id.TextViewSubject);
                convertView.setTag(holder);
            }
            else{
                holder = (ViewHolder)convertView.getTag();
            }

            holder.imageViews_logo.setOval(true);
            holder.imageViews_logo.setLetter(subjects[position].charAt(0));
            holder.TextViewSubject.setText(subjects[position]);

            return convertView;
        }

        class ViewHolder{
            private LetterImageViews imageViews_logo;
            private TextView TextViewSubject;
        }
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
