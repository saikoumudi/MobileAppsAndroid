package com.example.bhanu.homework08;

import android.content.Context;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by bhanu on 4/5/2017.
 */

public class DatabaseDatamanager implements Serializable {


    int key=1;
    update interface1;

    public static interface update{
        public  void updateCitiesListFromFireBase(ArrayList<City> cities);


    }
    public DatabaseDatamanager(Context mContext, final update update1){
        DatabaseReference db= FirebaseDatabase.getInstance().getReference();
        this.interface1=update1;
        db=db.child("cities");
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                try{
                    ArrayList<City> citiesList=new ArrayList<City>();
                for(DataSnapshot ds: dataSnapshot.getChildren()) {
                 citiesList.add(ds.getValue(City.class));

                }
                Log.d("demo","cities list"+citiesList);
                update1.updateCitiesListFromFireBase(citiesList);


                }catch (Exception e){
                    Log.d("demo","error"+e.getLocalizedMessage());
                    e.printStackTrace();
                }


            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    public static boolean addCity(City city){
        DatabaseReference db= FirebaseDatabase.getInstance().getReference();
        db=db.child("cities");
        db.child(city.getKey()).setValue(city);
        return true;
    }
    public static boolean deleteCity(City city){
        DatabaseReference db= FirebaseDatabase.getInstance().getReference();
        db=db.child("cities");
        db.child(city.getKey()).removeValue();

        return true;
    }

}
