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
import android.widget.RadioButton;
import android.widget.Toast;

import com.parkchanwoo.tamagotchiar.Pet;
import com.parkchanwoo.tamagotchiar.PetManager;
import com.parkchanwoo.tamagotchiar.R;
import com.parkchanwoo.tamagotchiar.viewmodels.MainActivityViewModel;
import com.pranavpandey.android.dynamic.toasts.DynamicToast;

import java.util.Date;

public class MainActivity extends AppCompatActivity {
	private String TAG = this.getClass().getSimpleName();
	private MainActivityViewModel mainActivityViewModel;

	private PetManager petManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
		petManager = new PetManager(mainActivityViewModel.getPetMutableLiveData());

		if (mainActivityViewModel.getPetLiveData().getValue() == null) { // no saved pet
			buildNewPetDialog();
		}
		else {
			DynamicToast.makeSuccess(this, "Scan and Tap the grid to see your pet!", Toast.LENGTH_LONG).show();
			petManager.startTimer();
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
		petManager.stopTimer();
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
			DynamicToast.makeSuccess(this, "Scan and Tap the grid to see your pet!", Toast.LENGTH_LONG).show();
			petManager.startTimer();
		});
		dialogNewPet.show();
	}
}