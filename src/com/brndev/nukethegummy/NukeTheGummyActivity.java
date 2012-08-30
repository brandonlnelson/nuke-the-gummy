package com.brndev.nukethegummy;

import com.tapfortap.AdView;
import com.tapfortap.TapForTap;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;




public class NukeTheGummyActivity extends Activity {
    /** Called when the activity is first created. */
	
	private int counter=0;
	private Button button;
	private MediaPlayer mp;
	private Activity activity;
	
	private AdView adView;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TapForTap.setDefaultAppId("8e649ea0-c382-012f-fada-4040d804a637");
	    TapForTap.checkIn(this);
	    
	    
        setContentView(R.layout.main);
        
        final GameView gameView = (GameView) findViewById(R.id.game_view);
        
        gameView.setGummyColor(getIntent().getExtras().getInt("i"));
        
        adView = (AdView) findViewById(R.id.ad_view);
        
        adView.loadAds();

    }
    
    
    @Override
    public void onResume() {
    	super.onResume();
    	
    	adView.loadAds();
    }
    
}