package com.example.anote.AccountPage;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.anote.R;

public class AccountFragment extends Fragment {

    public AccountFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account, container, false);

        initToolbar(view);

        return view;
    }

    private void initToolbar(View view){
        AppCompatActivity parent = (AppCompatActivity) getActivity();
        Toolbar toolbar = view.findViewById(R.id.account_toolbar);
        parent.setSupportActionBar(toolbar);
        parent.getSupportActionBar().setTitle("ANOTE");
    }
}
