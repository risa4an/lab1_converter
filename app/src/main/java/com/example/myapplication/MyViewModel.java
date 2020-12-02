package com.example.myapplication;

import android.app.Application;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Build;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.Objects;

public class MyViewModel extends AndroidViewModel {
    private final MutableLiveData<String> ValueFrom = new MutableLiveData<>("");
    private final MutableLiveData<String> ValueTo = new MutableLiveData<>("");
    private double percent = 1.0;

    public MyViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<String> getValueFrom() {
        return ValueFrom;
    }

    public LiveData<String> getValueTo() {
        return ValueTo;
    }

    public void changeData(Float percent) {
        this.percent = percent;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (Objects.requireNonNull(ValueFrom.getValue()).length() != 0)
                convertField();
        }
    }

    public void clearField() {
        ValueFrom.setValue("");
        ValueTo.postValue("");
    }

    public void deleteSymbol() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (Objects.requireNonNull(ValueFrom.getValue()).length() <= 1) {
                clearField();
            }
            else
            {
                ValueFrom.setValue(ValueFrom.getValue().substring(0, ValueFrom.getValue().length() - 1));
                convertField();
            }
        }
    }

    public void addNumb(String numb) {
        ValueFrom.setValue(ValueFrom.getValue() + numb);
        convertField();
    }

    public void addDot() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (!Objects.requireNonNull(ValueFrom.getValue()).contains("."))
            {
                ValueFrom.setValue(ValueFrom.getValue() + ".");
                convertField();
            }
        }
    }

    public void convertField() {
        ValueTo.postValue(Float.toString((float) (Float.parseFloat(Objects.requireNonNull(ValueFrom.getValue())) * percent)));
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void changeFields() {
        if (!Objects.equals(ValueTo.getValue(), ""))
        {
            ValueFrom.setValue(ValueTo.getValue());
            convertField();
        }

    }


    public void copyInBuffer(int field, ClipboardManager clipboardManager) {
        switch (field) {
            case 1:
                ClipData clipData = ClipData.newPlainText("text", ValueFrom.getValue());
                clipboardManager.setPrimaryClip(clipData);
                break;
            case 2:
                clipData = ClipData.newPlainText("text", ValueTo.getValue());
                clipboardManager.setPrimaryClip(clipData);
                break;
        }
        Toast toast = Toast.makeText(getApplication(), "The copy was successful", Toast.LENGTH_LONG);
        toast.show();
    }

}