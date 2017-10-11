package com.example.bhanu.homework08;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity implements currentCityFragment.OnFragmentInteractionListener,LocationApiAsync.sendLocation,DatabaseDatamanager.update{
    public  static final String shared_key="sharedKey";
    public static final String TEMP_FORMAT_KEY="TEMP_FORMAT";
    public static final String CURRENT_CITY_KEY="CURRENTCITY";
    public static final String CURRENT_COUNTRY_KEY="CURRENTCOUNTRY";
    public static final String TEMP_CELSIUS="Celsius";
    public static final String TEMP_F="Fahreheit";
    String currentCity="";
    String currrentCountry="";
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    ArrayList<City> savedCities=new ArrayList<City>();
    ProgressDialog pd;
    public static int REQ_CODE=100;
     public static String INTENT_FIVE_DAY="fiveDay";
    public static String INTENT_MAIN="mainac";
    public static String INTENT_SAVEDCITIES="saved";
    public DatabaseDatamanager getDatamanager() {
        return datamanager;
    }

    public void setDatamanager(DatabaseDatamanager datamanager) {
        this.datamanager = datamanager;
    }

    private DatabaseDatamanager datamanager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar= (Toolbar) findViewById(R.id.toolbar2);
        this.setActionBar(toolbar);
       getActionBar().setIcon(R.drawable.icon);
        getActionBar().setTitle("Weather App");
        getActionBar().setLogo(R.drawable.icon);
        sharedPreferences= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        editor=sharedPreferences.edit();
        if(!sharedPreferences.getString(CURRENT_CITY_KEY,"-1").equals("-1")){
           currentCity=sharedPreferences.getString(CURRENT_CITY_KEY,"=1");
            currrentCountry=sharedPreferences.getString(CURRENT_COUNTRY_KEY,"-1");
            new LocationApiAsync(MainActivity.this).execute(new String[]{currentCity,currrentCountry});
        }
        else{
            EmptyCityFragment fragment=new EmptyCityFragment();
            currentCityFragment fragment1=new currentCityFragment();
            getFragmentManager().beginTransaction().replace(R.id.topContainer,fragment).addToBackStack(null).commit();
        }
        editor.commit();
        datamanager=new DatabaseDatamanager(MainActivity.this,MainActivity.this);
        findViewById(R.id.searchCityMain).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText cityEdit= (EditText) findViewById(R.id.cityEditMain);
                EditText countryEdit=(EditText)findViewById(R.id.countryEditMain);
                if(cityEdit.getText().toString().isEmpty()||countryEdit.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this,"Please enter both city and country",Toast.LENGTH_LONG).show();

                }
                else{
                    pd=new ProgressDialog(MainActivity.this);
                    pd.setMessage("Loading Data");
                    pd.show();
                    pd.setCancelable(false);
                    new FiveDayForeCastAsync(MainActivity.this).execute(new String[]{cityEdit.getText().toString().trim(),countryEdit.getText().toString().trim()});
                    cityEdit.getText().clear();
                    countryEdit.getText().clear();
                }
            }
        });

    }
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        if(id==R.id.action_settings){
            Intent intent=new Intent(MainActivity.this,SettingsActivity.class);
            startActivity(intent);
           //write code here to call preference activity

        }




        return  true;

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void setCurrentCityButtonClicked() {
        LayoutInflater li = LayoutInflater.from(MainActivity.this);
        View child = li.inflate(R.layout.dialogchild, null);
        final EditText city= (EditText) child.findViewById(R.id.cityEditText);
        final EditText country= (EditText) child.findViewById(R.id.countryEditText);
        AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);

        builder.setView(child);
        builder.setCancelable(false)
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                   dialog.cancel();
                    }
                })
                .setPositiveButton("Set", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                   if(city.getText().toString().isEmpty()||country.getText().toString().isEmpty()){
                       Toast.makeText(MainActivity.this,"Please fill both city and country",Toast.LENGTH_LONG).show();
                   }
                   else{
                       currentCity=city.getText().toString().trim();
                       currrentCountry=country.getText().toString().trim();
                       new LocationApiAsync(MainActivity.this).execute(new String[]{city.getText().toString(),country.getText().toString()});
                   }
                    }
                });
        builder.create().show();

    }

    @Override
    public void sendCurrentCity(City city) {
        if(city!=null){
          Bundle bundle=new Bundle();
            editor.putString(CURRENT_CITY_KEY,currentCity);
            editor.putString(CURRENT_COUNTRY_KEY,currrentCountry);
            editor.commit();
            bundle.putSerializable(MainActivity.CURRENT_CITY_KEY,city);
            currentCityFragment fragment=new currentCityFragment();
            fragment.setArguments(bundle);
           // datamanager.addCity(city);
            getFragmentManager().beginTransaction().replace(R.id.topContainer,fragment).addToBackStack(null).commit();
        }
        else{
            Toast.makeText(MainActivity.this,"City Not Found",Toast.LENGTH_LONG);
        }
    }

    @Override
    public void sendFiveDayForeCast(FiveDayForecast fiveDayForecast) {
        pd.dismiss();
       if(fiveDayForecast!=null) {
           Log.d("demo", "FiveDayForecast is " + fiveDayForecast.toString());
           Intent intent = new Intent(MainActivity.this, Main2Activity.class);
           intent.putExtra(MainActivity.INTENT_FIVE_DAY, fiveDayForecast);
           intent.putExtra(MainActivity.INTENT_SAVEDCITIES, savedCities);
           // intent.putExtra(MainActivity.INTENT_MAIN, (Parcelable) MainActivity.this);
           startActivityForResult(intent,REQ_CODE);
       }
       else{
           Toast.makeText(MainActivity.this,"City not Found Please try again",Toast.LENGTH_LONG).show();
       }
    }

    @Override
    public void updateCitiesListFromFireBase(ArrayList<City> cities) {
        Log.d("demo","cities update method called");
        this.savedCities=cities;
        Collections.sort(cities,new Comparator<City>() {
            @Override
            public int compare(City o1, City o2) {
                if(o1.isFavorite()) return -1;
                if(o2.isFavorite()) return 1;
                return 0;
            }
        });
       if(!cities.isEmpty()){

           if(this.hasWindowFocus()) {
              replaceSavedCitiesFragment();
           }
       }
       else{
           getFragmentManager().beginTransaction().replace(R.id.bottomContainer,new EmptySavedListFragment()).addToBackStack(null).commit();
           Log.d("demo","no saved cities");
       }
    }
    public void replaceSavedCitiesFragment(){
        Log.d("demo","fragment update method called");
        SavedCities savedCitiesFragment = new SavedCities();
        Bundle bundle = new Bundle();
        bundle.putSerializable(SavedCities.ARG_CITIES_LIST, savedCities);
        //bundle.putSerializable(SavedCities.ARG_DATA_MANAGER,datamanager);
        savedCitiesFragment.setArguments(bundle);
        getFragmentManager().beginTransaction().replace(R.id.bottomContainer, savedCitiesFragment).addToBackStack(null).commit();
    }
    public static void updateCity(City city,ArrayList<City> savedCities){
        for(City temp:savedCities){
          if(temp.equals(city)){
              if(temp.getUpdatedTime()<city.getUpdatedTime()){
                  temp.setUpdatedTime(city.getUpdatedTime());
                  temp.setTemparatureC(city.getTemparatureC());
                  temp.setTemparatureF(city.getTemparatureF());
                  temp.setWeatherIconUrl(city.getWeatherIconUrl());
                  temp.setWeatherText(city.getWeatherText());
                  DatabaseDatamanager.addCity(temp);
                  Log.d("demo","updatedCities");
                  return;

              }
          }
        }
        city.setFavorite(false);
        DatabaseDatamanager.addCity(city);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("demo","resume called");
        replaceSavedCitiesFragment();
        currentCity=sharedPreferences.getString(CURRENT_CITY_KEY,"-1");
        currrentCountry=sharedPreferences.getString(CURRENT_COUNTRY_KEY,"-1");
        new LocationApiAsync(MainActivity.this).execute(new String[]{currentCity,currrentCountry});
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        Log.d("demo","restart called");
       // updateCitiesListFromFireBase(savedCities);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        updateCitiesListFromFireBase(savedCities);
        super.onActivityResult(requestCode, resultCode, data);
    }
}
