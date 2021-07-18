package com.happy.Reptile_Store.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ShopAnimalsViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    public ShopAnimalsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is the shop animals page");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
