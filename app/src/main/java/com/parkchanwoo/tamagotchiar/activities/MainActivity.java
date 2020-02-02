package com.parkchanwoo.tamagotchiar.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.util.Log;

import com.parkchanwoo.tamagotchiar.R;
import com.parkchanwoo.tamagotchiar.viewmodels.MainActivityViewModel;

public class MainActivity extends AppCompatActivity {
	private String TAG = this.getClass().getSimpleName();
	private MainActivityViewModel mainActivityViewModel;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
		if (mainActivityViewModel.getPetLiveData().getValue() == null) { // no saved pet
			Log.d("MainActivity", "no live data");
		}
		else {
			Log.d("MainActivity", "yes live data");
		}
	}
}
