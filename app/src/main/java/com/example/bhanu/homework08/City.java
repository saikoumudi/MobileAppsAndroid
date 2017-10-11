package com.example.bhanu.homework08;

import java.io.Serializable;

/**
 * Created by bhanu on 4/5/2017.
 */

public class City implements Serializable{
 private String key;
    private String name;
    private String country;
    private long updatedTime;
    private double temparatureC;
    private double temparatureF;

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    private boolean favorite;
    public double getTemparatureF() {
        return temparatureF;
    }

    public void setTemparatureF(double temparatureF) {
        this.temparatureF = temparatureF;
    }

    public long getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(long updatedTime) {
        this.updatedTime = updatedTime;
    }

    public double getTemparatureC() {
        return temparatureC;
    }

    public void setTemparatureC(double temparatureC) {
        this.temparatureC = temparatureC;
    }

   
    public String getWeatherText() {
        return weatherText;
    }

    public void setWeatherText(String weatherText) {
        this.weatherText = weatherText;
    }

    public String getWeatherIconUrl() {
        return weatherIconUrl;
    }

    public void setWeatherIconUrl(String weatherIconUrl) {
        this.weatherIconUrl = weatherIconUrl;
    }

    private String weatherText;
    private String weatherIconUrl;
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "City{" +
                "key='" + key + '\'' +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", updatedTime=" + updatedTime +
                ", temparatureC=" + temparatureC +
                ", temparatureF=" + temparatureF +
                ", weatherText='" + weatherText + '\'' +
                ", weatherIconUrl='" + weatherIconUrl + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        City city = (City) o;

        if (!key.equals(city.key)) return false;
        if (!name.equals(city.name)) return false;
        return country.equals(city.country);

    }

    @Override
    public int hashCode() {
        int result = key.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + country.hashCode();
        return result;
    }
}
