package com.tutoriales.simplesharedviewmodel;

import android.os.SystemClock;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SharedViewModel extends ViewModel {
    private MutableLiveData<String> countryMLD;
    private MutableLiveData<Boolean> taskStatusMLD;

    public SharedViewModel() {
        countryMLD = new MutableLiveData<>("Perú");
        taskStatusMLD = new MutableLiveData<>(false);
    }

    public void setCountry(String country){
        taskStatusMLD.setValue(true);

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 5; i++) {
                    SystemClock.sleep(500);
                }
                taskStatusMLD.postValue(false);
                countryMLD.postValue(country);
            }
        });
    }

    public LiveData<String> getCountry() {
        return countryMLD;
    }

    public LiveData<Boolean> getTaskStatus() {
        return taskStatusMLD;
    }
}
