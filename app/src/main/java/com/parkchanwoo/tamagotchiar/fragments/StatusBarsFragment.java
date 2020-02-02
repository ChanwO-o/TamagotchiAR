package com.parkchanwoo.tamagotchiar.fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.parkchanwoo.tamagotchiar.Pet;
import com.parkchanwoo.tamagotchiar.R;
import com.parkchanwoo.tamagotchiar.viewmodels.MainActivityViewModel;

public class StatusBarsFragment extends Fragment {
	private String TAG = this.getClass().getSimpleName();
	private MainActivityViewModel mainActivityViewModel;
	private ProgressBar hungerBar, happinessBar, bathroomBar;

	public StatusBarsFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_status_bars, container, false);
		hungerBar = v.findViewById(R.id.HungerBar_ProgBar);
		happinessBar = v.findViewById(R.id.HappinessBar_ProgBar);
		bathroomBar = v.findViewById(R.id.BathroomBar_ProgBar);
		return v;
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		mainActivityViewModel = ViewModelProviders.of(getActivity()).get(MainActivityViewModel.class);
//		LiveData<Pet> petLiveData = mainActivityViewModel.getPetLiveData();
		MutableLiveData<Pet> petLiveData = mainActivityViewModel.getPetMutableLiveData();
		petLiveData.observe(getViewLifecycleOwner(), pet -> {
			hungerBar.setProgress(pet.getHunger());
			happinessBar.setProgress(pet.getHappiness());
			bathroomBar.setProgress(pet.getBathroom());
		});
	}
}
