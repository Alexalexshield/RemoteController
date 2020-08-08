package ru.mineradio.remotecontroller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public Button butAlarmFA;
    public Button butAlarmFA2;
    public Button butIDFA;
    public Button butIDFA2;

    // Stream type.
    private static final int streamType = AudioManager.STREAM_MUSIC;
    private SoundPool soundPool;
    private AudioManager audioManager;
    private int soundAlarmFa;
    private int soundAlarmFa2;
    private int soundIdFa;
    private int soundIdFa2;
    private float volume;
    private boolean loaded;
    // Maximum sound stream.
    private static final int MAX_STREAMS = 5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        audioInit();

        butAlarmFA = findViewById(R.id.butAlarmFA);
        butAlarmFA2 = findViewById(R.id.butAlarmFA2);
        butIDFA = findViewById(R.id.butIDFA);
        butIDFA2 = findViewById(R.id.butIDFA2);

        butAlarmFA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = getApplicationContext();
                CharSequence text = getString(R.string.alarm_fa);
                int duration = Toast.LENGTH_SHORT;

                Toast.makeText(context, text, duration).show();
                playSoundAlarmFa1();
            }
        });
        butAlarmFA2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = getApplicationContext();
                CharSequence text = getString(R.string.alarm_fa2);
                int duration = Toast.LENGTH_SHORT;

                Toast.makeText(context, text, duration).show();
                playSoundAlarmFa2();
            }
        });
        butIDFA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = getApplicationContext();
                CharSequence text = getString(R.string.idFA);
                int duration = Toast.LENGTH_SHORT;

                Toast.makeText(context, text, duration).show();
                playSoundIdFa1();
            }
        });
        butIDFA2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = getApplicationContext();
                CharSequence text = getString(R.string.idFA2);
                int duration = Toast.LENGTH_SHORT;

                Toast.makeText(context, text, duration).show();
                playSoundIdFa2();
            }
        });

    }

    private void audioInit() {

        // AudioManager audio settings for adjusting the volume
        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);

        // Current volume Index of particular stream type.
        float currentVolumeIndex = (float) audioManager.getStreamVolume(streamType);

        // Get the maximum volume index for a particular stream type.
        float maxVolumeIndex  = (float) audioManager.getStreamMaxVolume(streamType);

        // Volumn (0 --> 1)
        this.volume = currentVolumeIndex / maxVolumeIndex;

        // Suggests an audio stream whose volume should be changed by
        // the hardware volume controls.
        this.setVolumeControlStream(streamType);

        // For Android SDK >= 21
        if (Build.VERSION.SDK_INT >= 21 ) {
            AudioAttributes audioAttrib = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_GAME)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();

            SoundPool.Builder builder= new SoundPool.Builder();
            builder.setAudioAttributes(audioAttrib).setMaxStreams(MAX_STREAMS);

            this.soundPool = builder.build();
        }
        // for Android SDK < 21
        else {
            // SoundPool(int maxStreams, int streamType, int srcQuality)
            this.soundPool = new SoundPool(MAX_STREAMS, AudioManager.STREAM_MUSIC, 0);
        }

        // When Sound Pool load complete.
        this.soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                loaded = true;
            }
        });

        // Load sound file (alarm.wav) into SoundPool.
        this.soundAlarmFa = this.soundPool.load(this, R.raw.alarm_fa1,1);
        this.soundAlarmFa2 = this.soundPool.load(this, R.raw.alarm_fa2,1);
        this.soundIdFa = this.soundPool.load(this, R.raw.id33333_fa,1);
        this.soundIdFa2 = this.soundPool.load(this, R.raw.id33333_fa2,1);
    }
    // When users click on the button "ALARM FA"
    public void playSoundAlarmFa1() {
        if(loaded)  {
            float leftVolumn = volume;
            float rightVolumn = volume;
            // Play sound of Alarm.
            int streamId = this.soundPool.play(this.soundAlarmFa,leftVolumn, rightVolumn, 1, 0, 1f);
            Log.d("StreamID","StreamID"+streamId);
        }
    }
    // When users click on the button "ALARM FA2"
    public void playSoundAlarmFa2()  {
        if(loaded)  {
            float leftVolumn = volume;
            float rightVolumn = volume;
            // Play sound of Alarm.
            int streamId = this.soundPool.play(this.soundAlarmFa2,leftVolumn, rightVolumn, 1, 0, 1f);
        }
    }
    // When users click on the button "id FA"
    public void playSoundIdFa1()  {
        if(loaded)  {
            float leftVolumn = volume;
            float rightVolumn = volume;
            // Play sound of Alarm.
            int streamId = this.soundPool.play(this.soundIdFa,leftVolumn, rightVolumn, 1, 0, 1f);
        }
    }
    // When users click on the button "id FA2"
    public void playSoundIdFa2()  {
        if(loaded)  {
            float leftVolumn = volume;
            float rightVolumn = volume;
            // Play sound of Alarm.
            int streamId = this.soundPool.play(this.soundIdFa2,leftVolumn, rightVolumn, 1, 0, 1f);
        }
    }
}
