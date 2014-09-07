package com.shivam.anthem;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayer.ErrorReason;
import com.google.android.youtube.player.YouTubePlayer.PlaybackEventListener;
import com.google.android.youtube.player.YouTubePlayer.PlayerStateChangeListener;
import com.google.android.youtube.player.YouTubePlayer.Provider;
import com.google.android.youtube.player.YouTubePlayerView;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class MainActivity extends YouTubeBaseActivity implements
	YouTubePlayer.OnInitializedListener{
	Firebase ref;
	public static final String API_KEY = "AIzaSyDBv4V6tLjf8TgS1qteLU-InOXdbE35mek";
	public static String VIDEO_ID = "dKLftgvYsVU";
	private double seek_delay;
	private static YouTubePlayer ytplayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ref = new Firebase("https://mhacks2014.firebaseio.com/mhacks2014/");
        ref.child("seconds").addListenerForSingleValueEvent(new ValueEventListener()
        {
        	@Override
        	public void onDataChange(DataSnapshot snapshot)
        	{
        		
        		seek_delay = (Double) snapshot.getValue();
        		ytplayer.seekToMillis((int) (seek_delay * 1000));
        	}

			@Override
			public void onCancelled(FirebaseError arg0) {
				// TODO Auto-generated method stub
			}
        });
        ref.child("videoid").addListenerForSingleValueEvent(new ValueEventListener()
        {
        	@Override
        	public void onDataChange(DataSnapshot snapshot)
        	{
        		VIDEO_ID = (String) snapshot.getValue();
        		ytplayer.loadVideo(VIDEO_ID);
        		//ytplayer.setFullscreen(true);
        	}
			@Override
			public void onCancelled(FirebaseError arg0) {
				// TODO Auto-generated method stub
			}
        });
        
        ref.child("seconds").addValueEventListener(new ValueEventListener()
        {
			@Override
			public void onCancelled(FirebaseError arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onDataChange(DataSnapshot arg0) {
				seek_delay = (Double) arg0.getValue();
        		ytplayer.seekToMillis((int)(seek_delay * 1000));
			}
        });
        ref.child("videoid").addValueEventListener(new ValueEventListener()
        {

			@Override
			public void onCancelled(FirebaseError arg0) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void onDataChange(DataSnapshot arg0) {
				VIDEO_ID = (String) arg0.getValue();
				ytplayer.loadVideo(VIDEO_ID);
        		
        		//ytplayer.setFullscreen(true);
			}
        });
        YouTubePlayerView youTubePlayerView = (YouTubePlayerView) findViewById(R.id.youtube_player);
		youTubePlayerView.initialize(API_KEY, this);
    }
    @Override
	public void onInitializationFailure(Provider provider, YouTubeInitializationResult result) {
		
    	Toast.makeText(this, result.toString(), Toast.LENGTH_LONG).show();
	}

	@Override
	public void onInitializationSuccess(Provider provider, final YouTubePlayer player, boolean wasRestored) {
		ytplayer = player;
		/** add listeners to YouTubePlayer instance **/
		player.setPlayerStateChangeListener(playerStateChangeListener);
		player.setPlaybackEventListener(playbackEventListener);

		/** Start buffering **/
		if (!wasRestored)
        {
			player.loadVideo(VIDEO_ID);
            player.setPlayerStateChangeListener(new PlayerStateChangeListener() {

	            @Override
	            public void onVideoStarted() {
	            }
	
	            @Override
	            public void onVideoEnded() {
	            }
	
	            @Override
	            public void onLoading() {
	            }
	
	            @Override
	            public void onLoaded(String videoId) {
	                player.play();
	            }
	
	            @Override
	            public void onError(ErrorReason reason) {
	            }
	
	            @Override
	            public void onAdStarted() {
	            }
            });
        }
	}

	private PlaybackEventListener playbackEventListener = new PlaybackEventListener() {

		@Override
		public void onBuffering(boolean arg0) {

		}

		@Override
		public void onPaused() {

		}

		@Override
		public void onPlaying() {

		}

		@Override
		public void onSeekTo(int arg0) {

		}

		@Override
		public void onStopped() {

		}

	};

	private PlayerStateChangeListener playerStateChangeListener = new PlayerStateChangeListener() {

		@Override
		public void onAdStarted() {

		}

		@Override
		public void onError(ErrorReason arg0) {

		}

		@Override
		public void onLoaded(String arg0) {
			//ytplayer.play();
		}

		@Override
		public void onLoading() {
			
		}

		@Override
		public void onVideoEnded() {

		}

		@Override
		public void onVideoStarted() {

		}
	};
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
