package com.example.clicktheball.viewmodel;

import androidx.databinding.BaseObservable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.clicktheball.R;
import com.example.clicktheball.view.GameView;

public class MainViewModel extends BaseObservable {

    private final FragmentManager fragmentManager;

    public MainViewModel(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    public void onStartGame() {
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.fragment_placeholder, new GameView());
        ft.commit();
    }
}
