package com.example.corona.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.corona.model.Tips;
import com.example.corona.repository.TipsRepository;

import java.util.List;

public class TipsViewModel  extends AndroidViewModel {
    private TipsRepository repository;
    private LiveData<List<Tips>> mListLiveData;

    public TipsViewModel(@NonNull Application application) {
        super(application);
        repository = TipsRepository.getInstance(application);
        mListLiveData = repository.getAllTips();
    }

    public LiveData<List<Tips>> getTips() {
        return repository.getAllTips();}

    public void insert(final Tips tips) {
        repository.insert(tips);
    }

    public void delete(final Tips tips) {
        repository.delete(tips);
    }
}
