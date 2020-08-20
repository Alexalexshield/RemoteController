package ru.mineradio.remotecontroller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

public class ToneGenerator extends AppCompatActivity {

    private static final int DEFAULT_FREQ = 1015;
    private static final int DEFAULT_MAX_FREQ = 8000;
    private static final int DEFAULT_MIN_FREQ = 300;


    private int currentFrequency = DEFAULT_FREQ;
    private int memo1Frequency = DEFAULT_FREQ;
    private int memo2Frequency = DEFAULT_FREQ;
    private int memo3Frequency = DEFAULT_FREQ;

    private SeekBar seekBarToneFreq;
    private TextView editCurrentFreq;

    private Button Memo1, Memo2, Memo3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tone_generator);

        Memo1 = findViewById(R.id.butToneM1);
        Memo2 = findViewById(R.id.butToneM2);
        Memo3 = findViewById(R.id.butToneM3);

        editCurrentFreq = findViewById(R.id.editCurrentFreq);
        editCurrentFreq.setText(String.valueOf(currentFrequency));

        seekBarToneFreq = findViewById(R.id.seekBarFrequency);
        seekBarToneFreq.setMax(DEFAULT_MAX_FREQ-DEFAULT_MIN_FREQ);
        //seekBarToneFreq.setMin(DEFAULT_MIN_FREQ);
        seekBarToneFreq.setProgress(DEFAULT_FREQ);

        seekBarToneFreq.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                editCurrentFreq.setText(String.valueOf(seekBarToneFreq.getProgress()+DEFAULT_MIN_FREQ));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                currentFrequency = seekBarToneFreq.getProgress()+DEFAULT_MIN_FREQ;
                editCurrentFreq.setText(String.valueOf(currentFrequency));
            }
        });

        Memo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                todo correct algoritm fot memory button
//                if (currentFrequency!=DEFAULT_FREQ){
//                    memo1Frequency = currentFrequency;
//                }
//                else{
//                    currentFrequency = memo1Frequency;
//                    seekBarToneFreq.setProgress(currentFrequency);
//                }
            }
        });

    }
}