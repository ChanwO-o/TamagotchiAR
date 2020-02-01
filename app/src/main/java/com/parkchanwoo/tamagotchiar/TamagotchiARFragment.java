package com.parkchanwoo.tamagotchiar;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import com.google.ar.sceneform.ux.ArFragment;
import com.parkchanwoo.tamagotchiar.viewmodels.MainActivityViewModel;

public class TamagotchiARFragment extends ArFragment {
	private String TAG = this.getClass().getSimpleName();
	private MainActivityViewModel mainActivityViewModel;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		mainActivityViewModel = ViewModelProviders.of(getActivity()).get(MainActivityViewModel.class);
		Log.i(TAG, "onActivityCreated()");
	}
}
