package com.example.clicktheball.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.clicktheball.R;
import com.example.clicktheball.databinding.ActivityMainBinding;
import com.example.clicktheball.viewmodel.MainViewModel;

public class StartView extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ActivityMainBinding activityMainBinding =
                DataBindingUtil.inflate(
                        inflater, R.layout.activity_main, container, false);
        activityMainBinding.setMainViewModel(new MainViewModel(getFragmentManager()));
        return activityMainBinding.getRoot();
    }

    // This event is triggered soon after onCreateView().
    // Any view setup should occur here.  E.g., view lookups and attaching view listeners.
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        // Setup any handles to view objects here
        // EditText etFoo = (EditText) view.findViewById(R.id.etFoo);
    }
}
