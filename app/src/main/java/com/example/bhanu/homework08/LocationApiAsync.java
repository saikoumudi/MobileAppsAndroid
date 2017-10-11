package com.example.bhanu.homework08;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URL;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by bhanu on 4/5/2017.
 */

public class LocationApiAsync extends AsyncTask<String,Void,City>{

     public interface sendLocation{
         public void sendCurrentCity(City city);
         public void sendFiveDayForeCast(FiveDayForecast fiveDayForecast);
     }
     sendLocation location;
    public LocationApiAsync(sendLocation locationApiAsync) {
        this.location=locationApiAsync;
    }

    @Override
    protected City doInBackground(String... params) {
        City city = null;
        try {
            String url = "http://dataservice.accuweather.com/locations/v1/" + params[1] + "/search?apikey=FNyUcdUVJd8J3nappxus65CtYuCgxfRj&q=" + params[0];
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(url)
                    .build();
            Response response = client.newCall(request).execute();

            if (!response.isSuccessful()) {
                Log.d("demo", "response unsuccesful");

                return null;
            } else {
                String responseBody = response.body().string();
                Log.d("demo response body", responseBody);
                JSONArray jsonArray = new JSONArray(responseBody);
                if(jsonArray.length()==0){
                    return null;
                }
                else{
                    JSONObject cityObject=jsonArray.getJSONObject(0);
                    city=new City();
                    city.setKey(cityObject.getString("Key"));
                    city.setName(cityObject.getString("EnglishName"));
                    cityObject=cityObject.getJSONObject("Country");
                    city.setCountry(cityObject.getString("ID"));

                }
            }
            url="http://dataservice.accuweather.com/currentconditions/v1/"+city.getKey()+"?apikey=FNyUcdUVJd8J3nappxus65CtYuCgxfRj";
            request = new Request.Builder()
                    .url(url)
                    .build();
            response = client.newCall(request).execute();

            if (!response.isSuccessful()) {
                Log.d("demo", "response unsuccesful");

                return null;
            } else {
                String responseBody = response.body().string();
                JSONObject cityTemp=new JSONArray(responseBody).getJSONObject(0);
                city.setWeatherText(cityTemp.getString("WeatherText"));
                int imgid=cityTemp.getInt("WeatherIcon");
                String imgURL="";
                if(imgid<10){
                    imgURL="http://developer.accuweather.com/sites/default/files/0"+imgid+"-s.png";
                }
                else{
                    imgURL="http://developer.accuweather.com/sites/default/files/"+imgid+"-s.png";
                }
                city.setWeatherIconUrl(imgURL);
                city.setUpdatedTime(cityTemp.getLong("EpochTime"));
                city.setUpdatedTime(city.getUpdatedTime()*1000l);
                JSONObject temparatureObject=cityTemp.getJSONObject("Temperature");
                city.setTemparatureC(temparatureObject.getJSONObject("Metric").getDouble("Value"));
                city.setTemparatureF(temparatureObject.getJSONObject("Imperial").getDouble("Value"));
            }
        }
    catch(Exception e)
    {
        Log.d("demo","Exception in LocationApiAsync"+e.getLocalizedMessage());
        e.printStackTrace();
    }
    Log.d("demo",city.toString());
        return city;
    }
    public static City getCityObject(String... params) {
        City city = null;
        try {
            Log.d("demo","city and country are "+params[0]+params[1]);
            String url = "http://dataservice.accuweather.com/locations/v1/" + params[1] + "/search?apikey=FNyUcdUVJd8J3nappxus65CtYuCgxfRj&q=" + params[0];
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(url)
                    .build();
            Response response = client.newCall(request).execute();

            if (!response.isSuccessful()) {
                Log.d("demo", "response unsuccesful");

                return null;
            } else {
                String responseBody = response.body().string();
                Log.d("demo response body", responseBody);
                JSONArray jsonArray = new JSONArray(responseBody);
                if(jsonArray.length()==0){
                    return null;
                }
                else{
                    JSONObject cityObject=jsonArray.getJSONObject(0);
                    city=new City();
                    city.setKey(cityObject.getString("Key"));
                    city.setName(cityObject.getString("EnglishName"));
                    cityObject=cityObject.getJSONObject("Country");
                    city.setCountry(cityObject.getString("ID"));

                }
            }
            url="http://dataservice.accuweather.com/currentconditions/v1/"+city.getKey()+"?apikey=FNyUcdUVJd8J3nappxus65CtYuCgxfRj";
            request = new Request.Builder()
                    .url(url)
                    .build();
            response = client.newCall(request).execute();

            if (!response.isSuccessful()) {
                Log.d("demo", "response unsuccesful");

                return null;
            } else {
                String responseBody = response.body().string();
                JSONObject cityTemp=new JSONArray(responseBody).getJSONObject(0);
                city.setWeatherText(cityTemp.getString("WeatherText"));
                int imgid=cityTemp.getInt("WeatherIcon");
                String imgURL="";
                if(imgid<10){
                    imgURL="http://developer.accuweather.com/sites/default/files/0"+imgid+"-s.png";
                }
                else{
                    imgURL="http://developer.accuweather.com/sites/default/files/"+imgid+"-s.png";
                }
                city.setWeatherIconUrl(imgURL);
                city.setUpdatedTime(cityTemp.getLong("EpochTime"));
                city.setUpdatedTime(city.getUpdatedTime()*1000l);
                JSONObject temparatureObject=cityTemp.getJSONObject("Temperature");
                city.setTemparatureC(temparatureObject.getJSONObject("Metric").getDouble("Value"));
                city.setTemparatureF(temparatureObject.getJSONObject("Imperial").getDouble("Value"));
            }
        }
        catch(Exception e)
        {
            Log.d("demo","Exception in LocationApiAsync"+e.getLocalizedMessage());
            e.printStackTrace();
        }
        Log.d("demo",city.toString());
        return city;
    }

    @Override
    protected void onPostExecute(City city) {
        location.sendCurrentCity(city);
        super.onPostExecute(city);
    }
}
