package com.parkchanwoo.tamagotchiar.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
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
	private EditText etPetInfoName;
	private DatePicker dpPetInfoDOB;
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
		dpPetInfoDOB = v.findViewById(R.id.dpPetInfoDOB);
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
		String dob = dpPetInfoDOB.getDayOfMonth() + "/" + (dpPetInfoDOB.getMonth() + 1) + "/" + dpPetInfoDOB.getYear();
		String gender;
		if (rbPetInfoMale.isChecked())
			gender = "male";
		else
			gender = "female";
		Log.d("petdob", "" + dob);
		Pet pet = new Pet(name, new Date(dob), gender);
		mainActivityViewModel.setPetLiveData(pet);
	}
}
