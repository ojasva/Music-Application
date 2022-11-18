package com.example.musicplayer;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import org.w3c.dom.Text;
import java.io.IOException;
public class MusicPlayer<LOG_TAG> extends AppCompatActivity {
    ImageView im;
    SeekBar mSeekBar;
    TextView tv,currentTime,totalTime;
    long song;
    int photo;
    String name;
    Button startPlaying,stopPlaying;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player);
        im=findViewById(R.id.imageView4);
        mSeekBar=findViewById(R.id.seekBar2);
        tv=findViewById(R.id.tvv);
        startPlaying=findViewById(R.id.button7);
        stopPlaying=findViewById(R.id.button8);
        currentTime=findViewById(R.id.textView5);
        totalTime=findViewById(R.id.textView6);
        Intent in=getIntent();
        song=in.getLongExtra("cs",0);
        photo=in.getIntExtra("im",0);
        name=in.getStringExtra("sn");
        im.setImageResource(photo);
        tv.setText(name);
        MediaPlayer mp;
        mp=MediaPlayer.create(getApplicationContext(), (int)song);
        int mFileDuration=mp.getDuration();
        String st=createTimerlabel(mFileDuration);
        totalTime.setText(st);
        mSeekBar.setMax(mFileDuration/1000);
        mp.start();
        Handler mHandler = new Handler();
        MusicPlayer.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(mp != null){
                    int mCurrentPosition = mp.getCurrentPosition() / 1000;
                    mSeekBar.setProgress(mCurrentPosition);
                    String curr=createTimerlabel(mp.getCurrentPosition());
                    currentTime.setText(curr);
                }
                mHandler.postDelayed(this, 1000);
            }
        });
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(mp != null && fromUser){
                    mp.seekTo(progress * 1000);
                }
            }
        });
        startPlaying.setOnClickListener(new View.OnClickListener() {
            boolean mStartPlaying = true;
            @Override
            public void onClick(View view) {
                startplaying(mp);
                if (mStartPlaying) {
                    startPlaying.setText("RESUME");
                } else {
                    startPlaying.setText("PAUSE");
                }
                mStartPlaying = !mStartPlaying;
            }

        });
        stopPlaying.setOnClickListener(new View.OnClickListener() {
            boolean mStartPlaying = true;
            @Override
            public void onClick(View view) {
                stopplaying(mp);
            }

        });
    }
    private void stopplaying(MediaPlayer mp) {
        mp.release();
        mp= null;
        startPlaying.setText("Start playing");
    }
    private void startplaying(MediaPlayer mp) {
        if(mp != null && mp.isPlaying()){
            mp.pause();
        } else if(mp != null){
            mp.start();
        }else{
            mp = new MediaPlayer();
            try {
                mp.prepare();
                mp.start();
            } catch (IOException e) {
            }
        }
    }
    public String createTimerlabel(int duration){
        String timerlabel="";
        int min=duration/1000/60;
        int sec=duration/1000%60;
        timerlabel+=min+":";
        if(sec<10)timerlabel+="0";
        timerlabel+=sec;
        return timerlabel;
    }
}