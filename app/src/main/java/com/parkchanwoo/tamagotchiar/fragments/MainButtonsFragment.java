package com.parkchanwoo.tamagotchiar.fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.parkchanwoo.tamagotchiar.Pet;
import com.parkchanwoo.tamagotchiar.R;
import com.parkchanwoo.tamagotchiar.viewmodels.MainActivityViewModel;

public class MainButtonsFragment extends Fragment {
	private String TAG = this.getClass().getSimpleName();
	private MainActivityViewModel mainActivityViewModel;

	public MainButtonsFragment() {
		// Required empty public constructor
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_main_buttons, container, false);

		ImageView feedButton = view.findViewById(R.id.Feed_ImageView);
		ImageView playButton = view.findViewById(R.id.Play_ImageView);
		ImageView bathroomButton = view.findViewById(R.id.Bathroom_ImageView);

		feedButton.setOnClickListener(v -> {
			Pet pet = mainActivityViewModel.getPetLiveData().getValue();
			if (pet.getHunger() + 15 > 100)
				pet.setHunger(100);
			else
				pet.setHunger(pet.getHunger() + 15);
			mainActivityViewModel.setPetLiveData(pet);
		});

		playButton.setOnClickListener(v -> {
			Pet pet = mainActivityViewModel.getPetLiveData().getValue();
			if (pet.getHappiness() + 7 > 100)
				pet.setHappiness(100);
			else
				pet.setHappiness(pet.getHappiness() + 7);
			mainActivityViewModel.setPetLiveData(pet);
		});

		bathroomButton.setOnClickListener(v -> {
			Pet pet = mainActivityViewModel.getPetLiveData().getValue();
			if (pet.getBathroom() + 7 > 100)
				pet.setBathroom(100);
			else
				pet.setBathroom(pet.getBathroom() + 7);
			mainActivityViewModel.setPetLiveData(pet);
		});

		return view;
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		mainActivityViewModel = ViewModelProviders.of(getActivity()).get(MainActivityViewModel.class);


	}
}
