package com.parkchanwoo.tamagotchiar;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


public class PetManager {
	private Handler handler;
	private double currentTime = 0; //TODO Eventually have time/day pull from a file instead of 0
	private int day = 0;
	private int hunger_loop_tracker = 0;
	private int bathroom_loop_tracker = 0;
	private int happiness_loop_tracker = 0;
	private MutableLiveData<Pet> petLiveData;

	public PetManager(MutableLiveData<Pet> petLiveData){
		handler = new Handler();
		this.petLiveData = petLiveData;

	}

	public double getCurrentTime() { //TODO Retrieve current time
		return currentTime; //TODO Eventually toString() format to HH:MM
	}

	public int getDay() { //TODO Retrieve current day
		return day;
	}

	public void startTimer(){
		handler.postDelayed(updateTimer, 0);
	}

	public void stopTimer(){ //Currently not in use
		handler.removeCallbacks(updateTimer);
	}

	private Runnable updateTimer = new Runnable() {

		@Override
		public void run() {
			currentTime += .0155;
			Log.d("test", String.valueOf(currentTime));
			handler.postDelayed(this, 0);

			if(currentTime >= 12000){
				currentTime = 0.0;
				day += 1;
			}

			if(bathroom_loop_tracker == 390)
			{
				Pet pet = petLiveData.getValue();
				pet.setBathroom(pet.getBathroom()-1);
				bathroom_loop_tracker = 0;
				petLiveData.setValue(pet);
			}

			if(hunger_loop_tracker == 780)
			{
				Pet pet = petLiveData.getValue();
				pet.setHunger((pet.getHunger()-1));
				hunger_loop_tracker = 0;
				petLiveData.setValue(pet);
			}

			if(happiness_loop_tracker == 1560)
			{
				Pet pet = petLiveData.getValue();
				pet.setHappiness((pet.getHappiness()-1));
				happiness_loop_tracker = 0;
				petLiveData.setValue(pet);
			}

			//Loop trackers
			bathroom_loop_tracker += 1;
			hunger_loop_tracker += 1;
			happiness_loop_tracker += 1;
		}
	};
}
