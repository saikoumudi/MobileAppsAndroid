package com.example.bhanu.homework08;


import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.ocpsoft.prettytime.PrettyTime;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by praka on 4/6/2017.
 */

public class RecycleAdapter extends RecyclerView.Adapter<ViewHolder>{

    ArrayList<City> cities;
     DatabaseDatamanager datamanager;
    Activity activity;
    PrettyTime prettyTime;
    public RecycleAdapter(ArrayList<City> cities,DatabaseDatamanager datamanager,Activity activity) {
        this.cities=cities;
        this.datamanager=datamanager;
        this.activity=activity;
        prettyTime=new PrettyTime();
    }

    @Override

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.recyclerchild,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.cityText.setText(cities.get(position).getName()+","+cities.get(position).getCountry());
        char degree = '\u00B0';
        String temparature="";
        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(activity);
        Log.d("demo","shared in recycle adapter"+sharedPreferences);
        if(sharedPreferences.getString(MainActivity.TEMP_FORMAT_KEY,MainActivity.TEMP_CELSIUS).equals(MainActivity.TEMP_CELSIUS)){
            temparature="Temparature: "+cities.get(position).getTemparatureC()+degree+"C";
        }
        else{
            temparature="Temparature: "+cities.get(position).getTemparatureF()+degree+"F";
        }
        temparature+="\nLastUpdated: "+prettyTime.format(new Date(cities.get(position).getUpdatedTime()));

        holder.temparatureText.setText(temparature);
        if(cities.get(position).isFavorite()){
            holder.star.setImageResource(R.drawable.star_gold);
        }
        else{
            holder.star.setImageResource(R.drawable.star_gray);
        }
        holder.star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                City city=cities.get(position);
                city.setFavorite(!city.isFavorite());
                datamanager.addCity(city);
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                datamanager.deleteCity(cities.get(position));
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return cities.size();
    }
}
