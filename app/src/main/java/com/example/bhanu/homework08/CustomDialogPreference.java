package com.example.bhanu.homework08;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.DialogPreference;
import android.preference.PreferenceManager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by praka on 4/7/2017.
 */

public class CustomDialogPreference extends DialogPreference {
    EditText editTextCity,editCountry;
    public CustomDialogPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        setPersistent(false);
        setDialogLayoutResource(R.layout.dialoglayoutsettings);
        final SharedPreferences sharedPreferences=PreferenceManager.getDefaultSharedPreferences(getContext());
        String city=sharedPreferences.getString(MainActivity.CURRENT_CITY_KEY,"-1");
        String country=sharedPreferences.getString(MainActivity.CURRENT_COUNTRY_KEY,"-1");
        if(!city.equals("-1")) {
            setDialogTitle("Update City Details");
            setPositiveButtonText("Update");
        }
        else {
            setDialogTitle("Enter City Details");
            setPositiveButtonText("Set");
        }

    }

    @Override
    protected void onBindDialogView(View view) {
        super.onBindDialogView(view);
        
        final SharedPreferences sharedPreferences=PreferenceManager.getDefaultSharedPreferences(getContext());
        String city=sharedPreferences.getString(MainActivity.CURRENT_CITY_KEY,"-1");
        String country=sharedPreferences.getString(MainActivity.CURRENT_COUNTRY_KEY,"-1");
        setDialogTitle("");
        editTextCity= (EditText) view.findViewById(R.id.edit_city);
        editCountry= (EditText) view.findViewById(R.id.edit_country);
        if(!city.equals("-1")){
            editTextCity.setText(city);

        }
        if(!country.equals("-1")){
            editCountry.setText(country);
        }
    }

    @Override
    protected void onDialogClosed(boolean positiveResult) {
        super.onDialogClosed(positiveResult);
        if (positiveResult){
            SharedPreferences sharedPreferences=PreferenceManager.getDefaultSharedPreferences(getContext());
            String city=sharedPreferences.getString(MainActivity.CURRENT_CITY_KEY,"-1");
            String country=sharedPreferences.getString(MainActivity.CURRENT_COUNTRY_KEY,"-1");

            if(editTextCity.getText().toString().isEmpty()||editCountry.getText().toString().isEmpty()){
                Toast.makeText(getContext(),"Please fill both city and country.Try again",Toast.LENGTH_LONG).show();
            }
            else{
                if(city.equals("-1")){
                    Toast.makeText(getContext(),"Current City Saved",Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(getContext(),"Current City Updated",Toast.LENGTH_LONG).show();
                }
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString(MainActivity.CURRENT_CITY_KEY,editTextCity.getText().toString().trim());
                editor.putString(MainActivity.CURRENT_COUNTRY_KEY,editCountry.getText().toString().trim());
                editor.commit();
            }

        }
    }
}
