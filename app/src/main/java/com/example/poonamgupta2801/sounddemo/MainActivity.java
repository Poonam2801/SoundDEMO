package com.example.poonamgupta2801.sounddemo;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer;
    AudioManager audioManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );

        mediaPlayer=MediaPlayer.create (this, R.raw.marbles );

        audioManager=(AudioManager) getSystemService ( Context.AUDIO_SERVICE );
        int maxVol=audioManager.getStreamMaxVolume ( AudioManager.STREAM_MUSIC );
        int currVol=audioManager.getStreamVolume ( AudioManager.STREAM_MUSIC );


        SeekBar volumeControl=(SeekBar)findViewById ( R.id.volumeControl );
        final SeekBar lengthControl=(SeekBar)findViewById ( R.id.lengthControl );

        volumeControl.setMax ( maxVol );
        volumeControl.setProgress ( currVol );
        lengthControl.setMax(mediaPlayer.getDuration ());

        new Timer ().scheduleAtFixedRate ( new TimerTask () {
            @Override
            public void run() {
                lengthControl.setProgress (mediaPlayer.getCurrentPosition ());
            }
        },0,100 );


        volumeControl.setOnSeekBarChangeListener ( new SeekBar.OnSeekBarChangeListener () {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.i ("seekbar value",Integer.toString ( progress ) );
                audioManager.setStreamVolume ( AudioManager.STREAM_MUSIC, progress,0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        } );


        lengthControl.setOnSeekBarChangeListener ( new SeekBar.OnSeekBarChangeListener () {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.i ("seekbar value",Integer.toString ( progress ) );
                mediaPlayer.seekTo ( progress );
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        } );


    }

    public void play(View view) {
        //mediaPlayer=MediaPlayer.create (this, R.raw.marbles );

        mediaPlayer.start ();
    }

    public void pause(View view) {

        //mediaPlayer=MediaPlayer.create (this, R.raw.marbles );
        mediaPlayer.pause();
    }
}
