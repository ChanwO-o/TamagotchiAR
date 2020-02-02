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
import android.widget.RadioButton;

import com.parkchanwoo.tamagotchiar.Pet;
import com.parkchanwoo.tamagotchiar.PetManager;
import com.parkchanwoo.tamagotchiar.R;
import com.parkchanwoo.tamagotchiar.viewmodels.MainActivityViewModel;

import java.util.Date;

public class MainActivity extends AppCompatActivity {
	private String TAG = this.getClass().getSimpleName();
	private MainActivityViewModel mainActivityViewModel;

	private PetManager petManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ImageView feedButton = findViewById(R.id.Feed_ImageView);
		ImageView playButton = findViewById(R.id.Play_ImageView);
		ImageView bathroomButton = findViewById(R.id.Bathroom_ImageView);

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
		View dialogLayout = inflater.inflate(R.layout.layout_petinfo, null);
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