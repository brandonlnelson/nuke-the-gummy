package com.brndev.nukethegummy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.appbrain.AppBrain;
import com.tapfortap.AdView;
import com.tapfortap.TapForTap;

public class StartActivity extends Activity {
	
	private AdView adView;
	
	@Override
	public void onCreate(Bundle b) {
		super.onCreate(b);

		AppBrain.init(this);
		
		TapForTap.setDefaultAppId("8e649ea0-c382-012f-fada-4040d804a637");
	    TapForTap.checkIn(this);
	

		setContentView(R.layout.start_main);
		
		
		final Button start_green = (Button) findViewById(R.id.start_green);
		
		start_green.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startGame(0);
			}
		});
		
		
		final Button start_orange = (Button) findViewById(R.id.start_orange);
		
		start_orange.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startGame(1);
			}
		});
		
		
		adView = (AdView) findViewById(R.id.ad_view);
		adView.loadAds();
		
	}
	
	
	public void startGame(int color) {
		Intent i = new Intent(this, NukeTheGummyActivity.class);
		Bundle bundle = new Bundle();
		bundle.putInt("i", color);
		i.putExtras(bundle);
		
		startActivity(i);
		
	}
	
	
	@Override
	public void onResume() {
		super.onResume();
		
		adView.loadAds();
	}
	
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		
		AppBrain.getAds().showInterstitial(this);
	}

}
