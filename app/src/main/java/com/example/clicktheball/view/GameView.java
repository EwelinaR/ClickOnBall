package com.example.clicktheball.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.clicktheball.R;
import com.example.clicktheball.databinding.ActivityGameBindingImpl;
import com.example.clicktheball.viewmodel.GameModelView;

public class GameView extends Fragment {

    private GameModelView gameModelView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        ActivityGameBindingImpl activityGameBinding =
                DataBindingUtil.inflate(
                        inflater, R.layout.activity_game, parent, false);
        gameModelView = new GameModelView();
        activityGameBinding.setGame(gameModelView);
        return activityGameBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        gameModelView.setView(view, getContext());
    }
}
