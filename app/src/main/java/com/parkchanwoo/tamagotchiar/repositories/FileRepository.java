package com.parkchanwoo.tamagotchiar.repositories;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


public class FileRepository {
	private String TAG = this.getClass().getSimpleName();
	private Context appContext;
	/**
	 FileRepository fileHelper;
	 fileHelper = new FileRepository(Activity.this);
	 String data = fileHelper.resetInfo();
	 */
	public FileRepository(Context context) {
		appContext = context.getApplicationContext();
	}
	/**
	 * @param String data.
	 * @return Nothing.
	 * @exception IOException On input error.
	 * @see IOException
	 */
	public void writeToFile(String data) {
		try {
			OutputStreamWriter outputStreamWriter = new OutputStreamWriter(appContext.openFileOutput("data.txt", Context.MODE_PRIVATE));
			outputStreamWriter.write(data);
			outputStreamWriter.close();
		}
		catch (IOException e) {
			Log.e("Exception", "File write failed: " + e.toString());
		}
	}
	/**
	 * @param no input.
	 * @return string.
	 * @exception IOException On input error.
	 * @see IOException
	 */
	public String readFromFile() {
		String ret = "";
		try {
			InputStream inputStream = appContext.openFileInput("data.txt");
			if ( inputStream != null ) {
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
				StringBuilder stringBuilder = new StringBuilder();
				String currentLine;

				while ( (currentLine = bufferedReader.readLine()) != null ) {
					stringBuilder.append(currentLine).append("\n");
				}

				inputStream.close();
				ret = stringBuilder.toString();
			}
		}
		catch (FileNotFoundException e) {
			Log.e("login activity", "File not found: " + e.toString());
		} catch (IOException e) {
			Log.e("login activity", "Can not read file: " + e.toString());
		}
		return ret;
	}
	/**
	 * @param String key.
	 * @return string.
	 * @exception IOException On input error.
	 * @see IOException
	 */
	public String getInfo(String key) {
		String ret = "";
		key = key.toLowerCase();
		try {
			InputStream inputStream = appContext.openFileInput("data.txt");

			if ( inputStream != null ) {
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
				String currentLine;

				while ( (currentLine = bufferedReader.readLine()) != null ) {
					if(currentLine.startsWith(key))
						ret = currentLine.replace(key + ": ", "");
				}

				inputStream.close();
			}
		}
		catch (FileNotFoundException e) {
			Log.e("login activity", "File not found: " + e.toString());
		} catch (IOException e) {
			Log.e("login activity", "Can not read file: " + e.toString());
		}
		return ret;
	}


	public String updateInfo(String key, String newData) throws IOException {
		String ret = "";
		key = key.toLowerCase();

		try {
			InputStream inputStream = appContext.openFileInput("data.txt");

			if ( inputStream != null ) {
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
				String currentLine;
				StringBuilder stringBuilder = new StringBuilder();

				while ( (currentLine = bufferedReader.readLine()) != null ) {
					if(currentLine.startsWith(key)){
						currentLine = key + ": " + newData;
					}
					stringBuilder.append(currentLine).append("\n");
				}

				inputStream.close();
				ret = stringBuilder.toString();

				writeToFile(ret);
			}
		}
		catch (FileNotFoundException e) {
			Log.e("login activity", "File not found: " + e.toString());
		} catch (IOException e) {
			Log.e("login activity", "Can not read file: " + e.toString());
		}

		return ret;
	}
	public String updateInfo(String key, int newData) throws IOException {
		String ret = "";
		key = key.toLowerCase();

		try {
			InputStream inputStream = appContext.openFileInput("data.txt");

			if ( inputStream != null ) {
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
				String currentLine;
				StringBuilder stringBuilder = new StringBuilder();

				while ( (currentLine = bufferedReader.readLine()) != null ) {
					if(currentLine.startsWith(key)){
						currentLine = key + ": " + newData;
					}
					stringBuilder.append(currentLine).append("\n");
				}

				inputStream.close();
				ret = stringBuilder.toString();

				writeToFile(ret);
			}
		}
		catch (FileNotFoundException e) {
			Log.e("login activity", "File not found: " + e.toString());
		} catch (IOException e) {
			Log.e("login activity", "Can not read file: " + e.toString());
		}

		return ret;
	}
	public String updateInfo(String key, double newData) throws IOException {
		String ret = "";
		key = key.toLowerCase();

		try {
			InputStream inputStream = appContext.openFileInput("data.txt");

			if ( inputStream != null ) {
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
				String currentLine;
				StringBuilder stringBuilder = new StringBuilder();

				while ( (currentLine = bufferedReader.readLine()) != null ) {
					if(currentLine.startsWith(key)){
						currentLine = key + ": " + newData;
					}
					stringBuilder.append(currentLine).append("\n");
				}

				inputStream.close();
				ret = stringBuilder.toString();

				writeToFile(ret);
			}
		}
		catch (FileNotFoundException e) {
			Log.e("login activity", "File not found: " + e.toString());
		} catch (IOException e) {
			Log.e("login activity", "Can not read file: " + e.toString());
		}

		return ret;
	}

	public String resetInfo() {
		String fatoryData = "name: \nage: 0\nday: 0\nhappy: 100\ndob: 0\ngender: M";
		writeToFile(fatoryData);
		return fatoryData;
	}
}


