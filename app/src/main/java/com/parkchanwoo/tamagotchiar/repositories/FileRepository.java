package com.parkchanwoo.tamagotchiar.repositories;

import android.content.Context;

public class FileRepository {
	private String TAG = this.getClass().getSimpleName();
	private Context appContext;

	public FileRepository(Context context) {
		appContext = context.getApplicationContext();
	}
}
