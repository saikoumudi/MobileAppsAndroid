package com.example.bhanu.homework08;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link EmptySavedListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link EmptySavedListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EmptySavedListFragment extends Fragment {




    public EmptySavedListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EmptySavedListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EmptySavedListFragment newInstance(String param1, String param2) {
        EmptySavedListFragment fragment = new EmptySavedListFragment();

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
        return inflater.inflate(R.layout.fragment_empty_saved_list, container, false);
    }




    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }


}
