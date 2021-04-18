package com.example.clicktheball.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.clicktheball.R;

public class StartView extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.show_game_panel).setOnClickListener(view1 -> goToGameView());
    }

    public void goToGameView() {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.add(R.id.fragment_placeholder, new GameView());
        ft.commit();
    }
}
