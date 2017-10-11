package com.example.bhanu.homework08;

import java.io.Serializable;

/**
 * Created by praka on 4/6/2017.
 */

public class DayForecast implements Serializable {
    private long date;
    private double Minc,MinF,Maxc,MaxF;
    private String dayWeatherText,nightWeatherText,dayCloudUrl,nightCloudUrl,mobileAccuWeatherLink;

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public double getMinc() {
        return Minc;
    }

    public void setMinc(double minc) {
        Minc = minc;
    }

    public double getMinF() {
        return MinF;
    }


    public void setMinF(double minF) {
        MinF = minF;
    }

    public double getMaxc() {
        return Maxc;
    }

    public void setMaxc(double maxc) {
        Maxc = maxc;
    }

    public double getMaxF() {
        return MaxF;
    }

    public void setMaxF(double maxF) {
        MaxF = maxF;
    }

    public String getDayWeatherText() {
        return dayWeatherText;
    }

    public void setDayWeatherText(String dayWeatherText) {
        this.dayWeatherText = dayWeatherText;
    }

    public String getNightWeatherText() {
        return nightWeatherText;
    }

    public void setNightWeatherText(String nightWeatherText) {
        this.nightWeatherText = nightWeatherText;
    }

    public String getDayCloudUrl() {
        return dayCloudUrl;
    }

    public void setDayCloudUrl(String dayCloudUrl) {
        this.dayCloudUrl = dayCloudUrl;
    }

    public String getNightCloudUrl() {
        return nightCloudUrl;
    }

    public void setNightCloudUrl(String nightCloudUrl) {
        this.nightCloudUrl = nightCloudUrl;
    }

    public String getMobileAccuWeatherLink() {
        return mobileAccuWeatherLink;
    }

    public void setMobileAccuWeatherLink(String mobileAccuWeatherLink) {
        this.mobileAccuWeatherLink = mobileAccuWeatherLink;
    }

    @Override
    public String toString() {
        return "DayForecast{" +
                "date=" + date +
                ", Minc=" + Minc +
                ", MinF=" + MinF +
                ", Maxc=" + Maxc +
                ", MaxF=" + MaxF +
                ", dayWeatherText='" + dayWeatherText + '\'' +
                ", nightWeatherText='" + nightWeatherText + '\'' +
                ", dayCloudUrl='" + dayCloudUrl + '\'' +
                ", nightCloudUrl='" + nightCloudUrl + '\'' +
                ", mobileAccuWeatherLink='" + mobileAccuWeatherLink + '\'' +
                '}';
    }
}
