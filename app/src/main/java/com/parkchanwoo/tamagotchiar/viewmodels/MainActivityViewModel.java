package com.parkchanwoo.tamagotchiar.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.parkchanwoo.tamagotchiar.repositories.FileRepository;

public class MainActivityViewModel extends AndroidViewModel {
	private String TAG = this.getClass().getSimpleName();
	private FileRepository fileRepository;

	public MainActivityViewModel(@NonNull Application application) {
		super(application);
		fileRepository = new FileRepository(application);
	}
}
