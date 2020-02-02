package com.parkchanwoo.tamagotchiar.viewmodels;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.parkchanwoo.tamagotchiar.Pet;
import com.parkchanwoo.tamagotchiar.repositories.FileRepository;

public class MainActivityViewModel extends AndroidViewModel {
	private String TAG = this.getClass().getSimpleName();
	private FileRepository fileRepository;

	public MainActivityViewModel(@NonNull Application application) {
		super(application);
		fileRepository = new FileRepository(application);
	}

	@Override
	protected void onCleared() {
		super.onCleared();
		Log.i(TAG, "MainActivityViewModel destroyed");
	}

	public LiveData<Pet> getPetLiveData() {
		return fileRepository.getPetLiveData();
	}
}
