package com.parkchanwoo.tamagotchiar;
import java.io.Serializable;
import java.util.*;
/*
GOALS:
	[] Name, Birthday, Gender/Sex
	[] Status (Hunger, Happiness, Bathroom)
	[] Sickness & Discipline
 */



public class Pet implements Serializable
{

	private String name;
	private Date birthday;
	private String gender;
	private int happiness;
	private int hunger;
	private int bathroom;


	public Pet(String name, Date birthday, String gender){
		this.name = name;
		this.birthday = birthday;
		this.gender = gender;
		this.happiness = 100;
		this.hunger = 100;
		this.bathroom = 100;
	}

	public String getName() { //Retrieve name
		return name;
	}

	public Date getBirthday() { //Retrieve birthday
		return birthday;
	}

	public String getGender() { //Retrieve gender
		return gender;
	}

	public int getHappiness() { //Get happiness meter
		return happiness;
	}

	public int getHunger() { //Get hunger
		return hunger;
	}

	public int getBathroom() { //Get bathroom meter
		return bathroom;
	}

	public void setName(String name) { //Update name
		this.name = name;
	}

	public void setGender(String gender) { //Update gender
		this.gender = gender;
	}

	public void setHappiness(int happiness) { //Update happiness meter
		this.happiness = happiness;
	}

	public void setHunger(int hunger) { //Update hunger meter
		this.hunger = hunger;
	}

	public void setBathroom(int bathroom) { //Set bathroom meter
		this.bathroom = bathroom;
	}

	@Override
	public String toString() {
		return "Name: " + name + " birthday: " + birthday + " gender: " + gender + " happiness: " + happiness + " hunger: " + hunger + " bathroom: " + bathroom;
	}
}
