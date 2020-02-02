package com.parkchanwoo.tamagotchiar.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;

import com.parkchanwoo.tamagotchiar.Pet;
import com.parkchanwoo.tamagotchiar.PetManager;
import com.parkchanwoo.tamagotchiar.R;
import com.parkchanwoo.tamagotchiar.fragments.StatusBarsFragment;
import com.parkchanwoo.tamagotchiar.viewmodels.MainActivityViewModel;

import java.util.Date;

public class MainActivity extends AppCompatActivity {
	private String TAG = this.getClass().getSimpleName();
	private MainActivityViewModel mainActivityViewModel;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);


		ImageView feedButton = findViewById(R.id.Feed_ImageView);
		ImageView playButton = findViewById(R.id.Play_ImageView);
		ImageView bathroomButton = findViewById(R.id.Bathroom_ImageView);
		ProgressBar hungerBar = findViewById(R.id.HungerBar_ProgBar);
		ProgressBar happinessBar = findViewById(R.id.HappinessBar_ProgBar);
		ProgressBar bathroomBar = findViewById(R.id.BathroomBar_ProgBar);


		feedButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				hungerBar.setProgress(hungerBar.getProgress() + 15);
			}
		});

		playButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				happinessBar.setProgress(happinessBar.getProgress() + 15);
			}
		});

		bathroomButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				bathroomBar.setProgress(bathroomBar.getProgress() + 15);
			}
		});

		mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
		if (mainActivityViewModel.getPetLiveData().getValue() == null) { // no saved pet
			buildNewPetDialog();
		}
		else {
			Pet pet = mainActivityViewModel.getPetLiveData().getValue();
			Log.d("MainActivity", pet.toString());
			new PetManager(mainActivityViewModel.getPetMutableLiveData()).startTimer();
		}
	}

	private void buildNewPetDialog() {
		AlertDialog.Builder dialogNewPet = new AlertDialog.Builder(this);
		dialogNewPet.setTitle("New Pet information");
		dialogNewPet.setCancelable(false);
		LayoutInflater inflater = this.getLayoutInflater();
		View dialogLayout = inflater.inflate(R.layout.fragment_petinfo, null);
		dialogNewPet.setView(dialogLayout);
		EditText etName = dialogLayout.findViewById(R.id.etPetInfoName);
		DatePicker dpDOB = dialogLayout.findViewById(R.id.dpPetInfoDOB);
		RadioButton rbPetInfoMale = dialogLayout.findViewById(R.id.rbPetInfoMale);
		dialogNewPet.setPositiveButton("Done", (dialog, which) -> {
			String name = etName.getText().toString();
			Date dob = new Date(dpDOB.getYear(), dpDOB.getMonth(), dpDOB.getDayOfMonth());
			String gender;
			if (rbPetInfoMale.isChecked())
				gender = "Male";
			else
				gender = "Female";
			Pet pet = new Pet(name, dob, gender);
			mainActivityViewModel.setPetLiveData(pet);
		});
		dialogNewPet.show();
	}
}