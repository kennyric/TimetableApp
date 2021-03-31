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

public class FacultyActivity extends AppCompatActivity {

    private ListView listView;
    private Toolbar toolbar;
    public static SharedPreferences sharedPreferences;
    public static String SEL_FACULTY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty);

        setupUIViews();
        initToolbar();

        setupListView();
    }

    private void setupUIViews(){
        toolbar = findViewById(R.id.toolbar_faculty);
        listView = findViewById(R.id.ListView_faculty);
        sharedPreferences = getSharedPreferences("Faculty", MODE_PRIVATE);
    }

    private void initToolbar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Faculty");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setupListView(){
        String[] faculty_names = getResources().getStringArray(R.array.faculty_name);

        FacultyAdapter adapter = new FacultyAdapter(this, R.layout.faculty_single_item, faculty_names);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch(position){
                    case 0: {
                        startActivity(new Intent(FacultyActivity.this, FacultyDetails.class));
                        sharedPreferences.edit().putInt(SEL_FACULTY, 0).apply();
                        break;
                    }
                    case 1: {
                        startActivity(new Intent(FacultyActivity.this, FacultyDetails.class));
                        sharedPreferences.edit().putInt(SEL_FACULTY, 1).apply();
                        break;
                    }
                    default:break;
                }
            }
        });
    }

    public class FacultyAdapter extends ArrayAdapter {
        private int resource;
        private LayoutInflater layoutInflater;
        private String[] faculty = new String[]{};

        public FacultyAdapter(Context context, int resource, String[] objects) {
            super(context, resource, objects);
            this.resource = resource;
            this.faculty = objects;
            layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            ViewHolder holder;
            if(convertView == null){
                holder = new ViewHolder();
                convertView = layoutInflater.inflate(resource, null);
                holder.imageViews_logo = convertView.findViewById(R.id.ImageViewFaculty);
                holder.TextViewFaculty = convertView.findViewById(R.id.TextViewFaculty);
                convertView.setTag(holder);
            }
            else{
                holder = (ViewHolder)convertView.getTag();
            }

            holder.imageViews_logo.setOval(true);
            holder.imageViews_logo.setLetter(faculty[position].charAt(0));
            holder.TextViewFaculty.setText(faculty[position]);

            return convertView;
        }

        class ViewHolder{
            private LetterImageViews imageViews_logo;
            private TextView TextViewFaculty;
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
