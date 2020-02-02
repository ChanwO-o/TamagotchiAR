package com.parkchanwoo.tamagotchiar.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.parkchanwoo.tamagotchiar.Pet;
import com.parkchanwoo.tamagotchiar.R;
import com.parkchanwoo.tamagotchiar.viewmodels.MainActivityViewModel;

import java.util.Date;

public class PetInfoFragment extends Fragment {
	private String TAG = this.getClass().getSimpleName();
	private MainActivityViewModel mainActivityViewModel;
	private EditText etPetInfoName, etPetInfoDOB;
	private RadioButton rbPetInfoMale, rbPetInfoFemale;
	private Button bPetInfoCreatePet;

	public PetInfoFragment() {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View v = inflater.inflate(R.layout.fragment_petinfo, container, false);
		etPetInfoName = v.findViewById(R.id.etPetInfoName);
		etPetInfoDOB = v.findViewById(R.id.etPetInfoDOB);
		rbPetInfoMale = v.findViewById(R.id.rbPetInfoMale);
		rbPetInfoFemale = v.findViewById(R.id.rbPetInfoFemale);
		bPetInfoCreatePet = v.findViewById(R.id.bPetInfoCreatePet);
		bPetInfoCreatePet.setOnClickListener(view -> {
			createPet();
		});
		return v;
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		mainActivityViewModel = ViewModelProviders.of(getActivity()).get(MainActivityViewModel.class);
	}

	private void createPet() {
		String name = etPetInfoName.getText().toString();
		String dob = etPetInfoDOB.getText().toString();
		String gender;
		if (rbPetInfoMale.isChecked())
			gender = "male";
		else
			gender = "female";
		Pet pet = new Pet(name, new Date(dob), gender);
		mainActivityViewModel.setPetLiveData(pet);
	}
}
