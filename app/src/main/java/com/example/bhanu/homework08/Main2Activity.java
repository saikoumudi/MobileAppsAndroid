package com.example.bhanu.homework08;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Main2Activity extends AppCompatActivity implements CityWeatherAdapter.ChangeCurrentDay {

    FiveDayForecast fiveDayForecast;
    MainActivity main;
    SimpleDateFormat dateFormat;
    SharedPreferences sharedPreferences;
    int currentIndex=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Weather App");
        setSupportActionBar(toolbar);
       getSupportActionBar().setIcon(R.drawable.icon);
        getSupportActionBar().setTitle("Weather App");
        getSupportActionBar().setLogo(R.drawable.icon);
        fiveDayForecast= (FiveDayForecast) getIntent().getExtras().getSerializable(MainActivity.INTENT_FIVE_DAY);
        //main= (MainActivity) getIntent().getExtras().get(MainActivity.INTENT_MAIN);
        TextView titleText= (TextView) findViewById(R.id.titleTextView);
        City cityTemp=fiveDayForecast.getCity();
        titleText.setText("Daily forecast for "+cityTemp.getName()+","+cityTemp.getCountry());
        TextView headLineText= (TextView) findViewById(R.id.headlinetext);
        headLineText.setText(fiveDayForecast.getHeadline());
        dateFormat=new SimpleDateFormat("MMMMMM d, yyyy");
        sharedPreferences= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        updateCurrentIndexViews();
        TextView moreDetails= (TextView) findViewById(R.id.moreDetails);
        moreDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent=new Intent(Intent.ACTION_VIEW);
                browserIntent.setData(Uri.parse(fiveDayForecast.getDayForecastArrayList().get(currentIndex).getMobileAccuWeatherLink()));
                startActivity(browserIntent);
            }
        });
        TextView extendedForeCast= (TextView) findViewById(R.id.textView12);
        extendedForeCast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent=new Intent(Intent.ACTION_VIEW);
                browserIntent.setData(Uri.parse(fiveDayForecast.getExtendedForeCastUrl()));
                startActivity(browserIntent);
            }
        });
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(Main2Activity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        RecyclerView recyclerView= (RecyclerView) findViewById(R.id.recyclercity);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(new CityWeatherAdapter(fiveDayForecast.getDayForecastArrayList(),Main2Activity.this,Main2Activity.this));
    }
    void updateCurrentIndexViews(){
        char degree = '\u00B0';
        DayForecast currentDay=fiveDayForecast.getDayForecastArrayList().get(currentIndex);
        TextView dateText= (TextView) findViewById(R.id.forecastDate);
        dateText.setText("Forecast on "+dateFormat.format(new Date(currentDay.getDate())));
        TextView dateTemparature= (TextView) findViewById(R.id.temparatureRange);
        Log.d("demo","shared pref in city weather"+sharedPreferences.getAll());
        if(sharedPreferences.getString(MainActivity.TEMP_FORMAT_KEY,MainActivity.TEMP_CELSIUS).equals(MainActivity.TEMP_CELSIUS)){

            dateTemparature.setText("Temparature : " + currentDay.getMaxc() + degree + "/" + currentDay.getMinc() + degree);
        }
        else {
            dateTemparature.setText("Temparature : "+currentDay.getMaxF()+degree+"/"+currentDay.getMinF()+degree);
        }
        ImageView dayCloud= (ImageView) findViewById(R.id.imageView2);
        ImageView nightCloud= (ImageView) findViewById(R.id.imageView);
        Picasso.with(Main2Activity.this).load(currentDay.getDayCloudUrl()).into(dayCloud);
        Picasso.with(Main2Activity.this).load(currentDay.getNightCloudUrl()).into(nightCloud);
        TextView dayWeatherText= (TextView) findViewById(R.id.dayWeatherText);
        TextView nightWeatherText= (TextView) findViewById(R.id.nightWeatherText);
        dayWeatherText.setText(currentDay.getDayWeatherText());
        nightWeatherText.setText(currentDay.getNightWeatherText());
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        if(id==R.id.action_settings){
            //write code here to call preference activity
            Intent intent=new Intent(Main2Activity.this,SettingsActivity.class);
            startActivity(intent);
        }
        else if(id==R.id.action_savecity){
            //write code save city;
            ArrayList<City> cityArrayList= (ArrayList<City>) getIntent().getExtras().getSerializable(MainActivity.INTENT_SAVEDCITIES);
            MainActivity.updateCity(fiveDayForecast.getCity(),cityArrayList);
            if(cityArrayList.contains(fiveDayForecast.getCity())){
                Toast.makeText(Main2Activity.this,"City Updated",Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(Main2Activity.this,"City Saved",Toast.LENGTH_LONG).show();
            }

        }
        else{
            SharedPreferences sharedPreferences=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            SharedPreferences.Editor edit = sharedPreferences.edit();

            if(sharedPreferences.getString(MainActivity.CURRENT_CITY_KEY,"-1").equals("-1")){
               Toast.makeText(Main2Activity.this,"Current City Saved",Toast.LENGTH_LONG).show();
            }
            else {
                Toast.makeText(Main2Activity.this,"Current City Updated",Toast.LENGTH_LONG).show();
            }
            edit.putString(MainActivity.CURRENT_CITY_KEY,fiveDayForecast.getCity().getName());
            edit.putString(MainActivity.CURRENT_COUNTRY_KEY,fiveDayForecast.getCity().getCountry());
            edit.commit();
        }




        return  true;

    }


    @Override
    public void changecurrentDay(int day) {
        currentIndex=day;
        updateCurrentIndexViews();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        updateCurrentIndexViews();
    }
}
