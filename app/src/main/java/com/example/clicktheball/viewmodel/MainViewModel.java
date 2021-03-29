package com.example.clicktheball.viewmodel;

import androidx.databinding.BaseObservable;

public class MainViewModel extends BaseObservable {

    public void onStartGame() {
        System.out.println("Start the game");
    }
}
