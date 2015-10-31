package com.example.asynchronoustask1;

import java.util.ArrayList;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends Activity {
	ListView lv;
	String [] satellites={"Sun","Moon","Stars","Mercury","Venus","Earth","Mars","Jupiter","Saturn","Neptune","Uranus","Pluto"};
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		lv=(ListView) findViewById(R.id.listView1);
		lv.setAdapter(new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1,new ArrayList<String>()));
		new MyTask().execute();
	}
	

	class MyTask extends AsyncTask<Void, String, String> 
	{
		ArrayAdapter<String> adapter;
		ProgressBar pb;
		int count;
		
		@Override
		protected void onPreExecute() {
			adapter=(ArrayAdapter<String>) lv.getAdapter();
			pb=(ProgressBar) findViewById(R.id.progressBar);
			pb.setMax(12);
			pb.setProgress(0);
			pb.setVisibility(View.VISIBLE);
			count=0;
		}
		
		@Override
		protected String doInBackground(Void... arg0) {
			
			for(String Sat: satellites){
				publishProgress(Sat);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return "All the names were added successfully";
		}
		@Override
		protected void onProgressUpdate(String... values) {
			
			adapter.add(values[0]);
			count++;
			pb.setProgress(count);
		}
		@Override
		protected void onPostExecute(String result) {
			pb.setVisibility(View.INVISIBLE);
			Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
			
		}	
	}
}
