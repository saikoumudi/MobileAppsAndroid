package com.example.bhanu.homework08;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



public class EmptyCityFragment extends Fragment {


    private currentCityFragment.OnFragmentInteractionListener mListener;

    public EmptyCityFragment() {
        // Required empty public constructor
    }


    public static EmptyCityFragment newInstance(String param1, String param2) {
        EmptyCityFragment fragment = new EmptyCityFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mListener = (currentCityFragment.OnFragmentInteractionListener)getActivity();
        View view= inflater.inflate(R.layout.fragment_empty_city, container, false);
        view.findViewById(R.id.buttonSetCurrentCity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.setCurrentCityButtonClicked();
            }
        });
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof currentCityFragment.OnFragmentInteractionListener) {
            mListener = (currentCityFragment.OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }



}
