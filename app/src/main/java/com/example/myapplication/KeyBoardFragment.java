package com.example.myapplication;

import android.os.Build;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Objects;

public class KeyBoardFragment extends Fragment {

    MyViewModel myViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View Fragment_keyboard = inflater.inflate(R.layout.keyboard, container, false);
        Fragment_keyboard.findViewById(R.id.btn0).setOnClickListener(item -> myViewModel.addNumb("0"));
        Fragment_keyboard.findViewById(R.id.btn1).setOnClickListener(item -> myViewModel.addNumb("1"));
        Fragment_keyboard.findViewById(R.id.btn2).setOnClickListener(item -> myViewModel.addNumb("2"));
        Fragment_keyboard.findViewById(R.id.btn3).setOnClickListener(item -> myViewModel.addNumb("3"));
        Fragment_keyboard.findViewById(R.id.btn4).setOnClickListener(item -> myViewModel.addNumb("4"));
        Fragment_keyboard.findViewById(R.id.btn5).setOnClickListener(item -> myViewModel.addNumb("5"));
        Fragment_keyboard.findViewById(R.id.btn6).setOnClickListener(item -> myViewModel.addNumb("6"));
        Fragment_keyboard.findViewById(R.id.btn7).setOnClickListener(item -> myViewModel.addNumb("7"));
        Fragment_keyboard.findViewById(R.id.btn8).setOnClickListener(item -> myViewModel.addNumb("8"));
        Fragment_keyboard.findViewById(R.id.btn9).setOnClickListener(item -> myViewModel.addNumb("9"));
        Fragment_keyboard.findViewById(R.id.btn000).setOnClickListener(item -> myViewModel.addNumb("000"));
        Fragment_keyboard.findViewById(R.id.btndot).setOnClickListener(item -> myViewModel.addDot());
        Fragment_keyboard.findViewById(R.id.btnc).setOnClickListener(item -> myViewModel.clearField());
        Fragment_keyboard.findViewById(R.id.btndelete).setOnClickListener(item -> myViewModel.deleteSymbol());

        return Fragment_keyboard;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            myViewModel = ViewModelProviders.of(Objects.requireNonNull(getActivity())).get(MyViewModel.class);
        }
    }
}