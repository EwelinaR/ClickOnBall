package com.example.clicktheball.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.example.clicktheball.R;
import com.example.clicktheball.databinding.ActivityMainBinding;
import com.example.clicktheball.viewmodel.MainViewModel;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding activityMainBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_main);
        activityMainBinding.setMainViewModel(new MainViewModel());
        activityMainBinding.executePendingBindings();
    }
}
