package com.example.assignment1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;

import com.example.assignment1.Utils.LetterImageViews;

public class WeekActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ListView listView;
    public static SharedPreferences sharedPreferences;
    public static String SEL_DAY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week);

        setupUIViews();
        initToolbar();
        setupListView();
    }

    private void setupUIViews(){
        toolbar = findViewById(R.id.toolbar_week);
        listView = findViewById(R.id.ListView_week);
        sharedPreferences = getSharedPreferences("DAY", MODE_PRIVATE);
    }

    private void initToolbar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Week");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setupListView(){
        String[] week = getResources().getStringArray(R.array.Week);

        WeekAdapter adapter = new WeekAdapter(this, R.layout.activity_week_single_item, week);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch(position){
                    case 0: {
                        startActivity(new Intent(WeekActivity.this, day_detail.class));
                        sharedPreferences.edit().putString(SEL_DAY, "Monday").apply();
                        break;
                    }
                    case 1: {
                        startActivity(new Intent(WeekActivity.this, day_detail.class));
                        sharedPreferences.edit().putString(SEL_DAY, "Tuesday").apply();
                        break;
                    }
                    case 2: {
                        startActivity(new Intent(WeekActivity.this, day_detail.class));
                        sharedPreferences.edit().putString(SEL_DAY, "Wednesday").apply();
                        break;
                    }
                    case 3: {
                        startActivity(new Intent(WeekActivity.this, day_detail.class));
                        sharedPreferences.edit().putString(SEL_DAY, "Thursday").apply();
                        break;
                    }
                    case 4: {
                        startActivity(new Intent(WeekActivity.this, day_detail.class));
                        sharedPreferences.edit().putString(SEL_DAY, "Friday").apply();
                        break;
                    }
                    case 5: {
                        startActivity(new Intent(WeekActivity.this, day_detail.class));
                        sharedPreferences.edit().putString(SEL_DAY, "Saturday").apply();
                        break;
                    }
                    default:break;
                }
            }
        });
    }

    public class WeekAdapter extends ArrayAdapter{
        private int resource;
        private LayoutInflater layoutInflater;
        private String[] week;

        public WeekAdapter(Context context, int resource, String[] objects) {
            super(context, resource, objects);
            this.resource = resource;
            this.week = objects;
            layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            ViewHolder holder;
            if(convertView == null){
                holder = new ViewHolder();
                convertView = layoutInflater.inflate(resource, null);
                holder.imageViews_logo = convertView.findViewById(R.id.ImageViewLetter);
                holder.textView_week = convertView.findViewById(R.id.Timetable);
                convertView.setTag(holder);
            }
            else{
                holder = (ViewHolder)convertView.getTag();
            }

            holder.imageViews_logo.setOval(true);
            holder.imageViews_logo.setLetter(week[position].charAt(0));
            holder.textView_week.setText(week[position]);

            return convertView;
        }

        class ViewHolder{
            private LetterImageViews imageViews_logo;
            private TextView textView_week;
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
