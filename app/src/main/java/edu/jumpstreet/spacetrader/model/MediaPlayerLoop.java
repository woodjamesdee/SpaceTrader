package edu.jumpstreet.spacetrader.model;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;

import edu.jumpstreet.spacetrader.R;

public class MediaPlayerLoop extends Service {
    private static final String TAG = null;
    MediaPlayer player;


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public void onCreate(){
        super.onCreate();
        player = MediaPlayer.create(this, R.raw.game_maintheme);
        player.setLooping(true);
        player.setVolume(75,75);
    }
    public int onStartCommand(Intent intent, int flags, int startId) {
        player.start();
        return 1;
    }
    public void onStop() {

    }
    public void onPause() {

    }
    @Override
    public void onDestroy() {
        player.stop();
        player.release();
    }

    @Override
    public void onLowMemory() {

    }
}
