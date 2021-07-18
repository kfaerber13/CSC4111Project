package com.happy.Reptile_Store.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ShopSuppliesViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    public ShopSuppliesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is the shop supplies page");
    }

    public LiveData<String> getText() {
        return mText;
    }
}

