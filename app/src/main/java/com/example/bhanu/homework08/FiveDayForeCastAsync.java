package com.example.bhanu.homework08;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by praka on 4/7/2017.
 */

public class FiveDayForeCastAsync extends AsyncTask<String,Void,FiveDayForecast> {
    LocationApiAsync.sendLocation update1;

    public FiveDayForeCastAsync(LocationApiAsync.sendLocation sendLocation) {
        update1=sendLocation;
    }

    @Override
    protected FiveDayForecast doInBackground(String... params) {
        City city=LocationApiAsync.getCityObject(params);
        DecimalFormat oneDigit = new DecimalFormat("#,##0.0");
        FiveDayForecast fiveDayForecast=null;
        if(city==null){
            return null;
        }
        try {
            String url = "http://dataservice.accuweather.com/forecasts/v1/daily/5day/"+city.getKey()+"?apikey=FNyUcdUVJd8J3nappxus65CtYuCgxfRj";
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(url)
                    .build();
            Response response = client.newCall(request).execute();

            if (!response.isSuccessful()) {
                Log.d("demo", "response unsuccesful");

                return null;
            } else {
                JSONObject responseObject=new JSONObject(response.body().string());
                fiveDayForecast=new FiveDayForecast();
                fiveDayForecast.setCity(city);
                fiveDayForecast.setHeadline(responseObject.getJSONObject("Headline").getString("Text"));
                fiveDayForecast.setExtendedForeCastUrl(responseObject.getJSONObject("Headline").getString("MobileLink"));
                JSONArray dayArrays=responseObject.getJSONArray("DailyForecasts");
                ArrayList<DayForecast> dayForecasts=new ArrayList<DayForecast>();
                for(int i=0;i<dayArrays.length();i++){
                    JSONObject dayObject=dayArrays.getJSONObject(i);
                    DayForecast dayForecast=new DayForecast();
                    dayForecast.setDate(dayObject.getLong("EpochDate")*1000);
                    dayForecast.setDayWeatherText(dayObject.getJSONObject("Day").getString("IconPhrase"));

                    int dayCloud=dayObject.getJSONObject("Day").getInt("Icon");
                    if(dayCloud<10){
                        dayForecast.setDayCloudUrl("http://developer.accuweather.com/sites/default/files/0"+dayCloud+"-s.png");
                    }
                    else{
                                dayForecast.setDayCloudUrl("http://developer.accuweather.com/sites/default/files/"+dayCloud+"-s.png");
                    }
                    dayForecast.setNightWeatherText(dayObject.getJSONObject("Night").getString("IconPhrase"));
                    dayCloud=dayObject.getJSONObject("Night").getInt("Icon");
                    if(dayCloud<10){
                        dayForecast.setNightCloudUrl("http://developer.accuweather.com/sites/default/files/0"+dayCloud+"-s.png");
                    }
                    else{
                        dayForecast.setNightCloudUrl("http://developer.accuweather.com/sites/default/files/"+dayCloud+"-s.png");
                    }
                    dayForecast.setMaxF(dayObject.getJSONObject("Temperature").getJSONObject("Maximum").getInt("Value")+0.0);
                    dayForecast.setMinF(dayObject.getJSONObject("Temperature").getJSONObject("Minimum").getInt("Value")+0.0);
                    double maxC=((dayForecast.getMaxF()-32)*5)/9;
                    dayForecast.setMaxc(Double.valueOf(oneDigit.format(maxC)));
                    double minC=((dayForecast.getMinF()-32)*5)/9;
                    dayForecast.setMinc(Double.valueOf(oneDigit.format(minC)));
                    dayForecast.setMobileAccuWeatherLink(dayObject.getString("MobileLink"));
                    dayForecasts.add(dayForecast);
                    Log.d("demo","fivedayCast size"+dayForecasts.size());

                }
                fiveDayForecast.setDayForecastArrayList(dayForecasts);
                return fiveDayForecast;
            }
        }
            catch(Exception e){
                Log.d("demo",e.getLocalizedMessage());
                e.printStackTrace();
            }
        return null;
    }

    @Override
    protected void onPostExecute(FiveDayForecast fiveDayForecast) {
        update1.sendFiveDayForeCast(fiveDayForecast);

        super.onPostExecute(fiveDayForecast);
    }
}
