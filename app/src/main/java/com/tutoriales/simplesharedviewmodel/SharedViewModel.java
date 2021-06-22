package com.tutoriales.simplesharedviewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SharedViewModel extends ViewModel {
    private MutableLiveData<String> countryMLD;

    public SharedViewModel() {
        this.countryMLD = new MutableLiveData<>();
    }

    public void setCountry(String country){
        countryMLD.setValue(country);
    }

    public LiveData<String> getCountry() {
        return countryMLD;
    }
}
