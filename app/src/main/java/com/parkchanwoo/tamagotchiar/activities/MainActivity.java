package com.parkchanwoo.tamagotchiar.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;

import com.parkchanwoo.tamagotchiar.R;
import com.parkchanwoo.tamagotchiar.fragments.StatusBarsFragment;
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
			buildNewPetDialog();
		}
		else {
			Log.d("MainActivity", "yes live data");
		}
	}

	private void buildNewPetDialog() {
		AlertDialog.Builder dialogNewPet = new AlertDialog.Builder(this);
		dialogNewPet.setTitle("New Pet information");
		dialogNewPet.setCancelable(false);
		LayoutInflater inflater = this.getLayoutInflater();
		dialogNewPet.setView(inflater.inflate(R.layout.fragment_petinfo, null));
		dialogNewPet.setPositiveButton("Done", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {

			}
		});
		dialogNewPet.show();
	}
}