package com.example.bhanu.homework08;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by praka on 4/6/2017.
 */

public class FiveDayForecast implements Serializable{
    private String headline;

    public String getExtendedForeCastUrl() {
        return extendedForeCastUrl;
    }

    public void setExtendedForeCastUrl(String extendedForeCastUrl) {
        this.extendedForeCastUrl = extendedForeCastUrl;
    }

    private String extendedForeCastUrl;
    private City city;
    private ArrayList<DayForecast> dayForecastArrayList;

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public ArrayList<DayForecast> getDayForecastArrayList() {
        return dayForecastArrayList;
    }

    public void setDayForecastArrayList(ArrayList<DayForecast> dayForecastArrayList) {
        this.dayForecastArrayList = dayForecastArrayList;
    }

    public String getHeadline() {

        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    @Override
    public String toString() {
        return "FiveDayForecast{" +
                "headline='" + headline + '\'' +
                ", extendedForeCastUrl='" + extendedForeCastUrl + '\'' +
                ", city=" + city +
                ", dayForecastArrayList=" + dayForecastArrayList +
                '}';
    }
}
