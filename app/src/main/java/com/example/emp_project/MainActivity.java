package com.example.emp_project;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.media.MediaRecorder;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import androidx.core.app.ActivityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.emp_project.databinding.ActivityMainBinding;

import android.widget.TextView;

import be.tarsos.dsp.io.android.AudioDispatcherFactory;
import be.tarsos.dsp.pitch.*;
import be.tarsos.dsp.*;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    private TextView noteText;
    private TextView pitchText;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        pitchText = findViewById(R.id.freq);
        noteText = findViewById(R.id.freq_Number2);

        if (checkPermissions() == PackageManager.PERMISSION_GRANTED) {
            AudioDispatcher dispatcher = AudioDispatcherFactory.fromDefaultMicrophone(22050, 1024, 0);

            PitchDetectionHandler pdh = new PitchDetectionHandler() {
                @Override
                public void handlePitch(PitchDetectionResult res, AudioEvent e) {
                    final float pitchInHz = res.getPitch();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            standart_Tuning(pitchInHz);
                        }
                    });
                }
            };

            AudioProcessor pitchProcessor = new PitchProcessor(PitchProcessor.PitchEstimationAlgorithm.FFT_YIN, 22050, 1024, pdh);
            dispatcher.addAudioProcessor(pitchProcessor);

            Thread audioThread = new Thread(dispatcher, "Audio Thread");
            audioThread.start();
        }
        else {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{
                    Manifest.permission.RECORD_AUDIO}, 1);
        }
    }

    @SuppressLint("SetTextI18n")
    public void standart_Tuning(float pitchInHz) {
        pitchText.setText("" + pitchInHz);
        // za sweet spot je green

        if(pitchInHz >= 72.4 && pitchInHz < 92.4) {
            //e - 82,4 je sweet spot
            noteText.setText("e");
        }
        else if(pitchInHz >= 100 && pitchInHz < 120) {
            //A - 110 je sweet spot
            noteText.setText("A");
        }
        else if(pitchInHz >= 137 && pitchInHz < 157) {
            //D - 147 je sweet spot
            noteText.setText("D");
        }
        else if(pitchInHz >= 186 && pitchInHz < 206) {
            //G - 196 je sweet spot
            noteText.setText("G");
        }
        else if(pitchInHz >= 237 && pitchInHz <= 257) {
            //B - 247 je sweet spot
            noteText.setText("B");
        }
        else if(pitchInHz >= 320 && pitchInHz < 340) {
            //E - 330 je sweet spot
            noteText.setText("E");
        }
        else {
            noteText.setText("undefined");
        }
    }

    @SuppressLint("SetTextI18n")
    public void dropD_Tuning(float pitchInHz) {
        pitchText.setText("" + pitchInHz);
        // za sweet spot je green

        if(pitchInHz >= 63.4 && pitchInHz < 83.4) {
            //e - 73,4 je sweet spot
            noteText.setText("e");
        }
        else if(pitchInHz >= 100 && pitchInHz < 120) {
            //A - 110 je sweet spot
            noteText.setText("A");
        }
        else if(pitchInHz >= 137 && pitchInHz < 157) {
            //D - 147 je sweet spot
            noteText.setText("D");
        }
        else if(pitchInHz >= 186 && pitchInHz < 206) {
            //G - 196 je sweet spot
            noteText.setText("G");
        }
        else if(pitchInHz >= 237 && pitchInHz <= 257) {
            //B - 247 je sweet spot
            noteText.setText("B");
        }
        else if(pitchInHz >= 320 && pitchInHz < 340) {
            //E - 330 je sweet spot
            noteText.setText("E");
        }
        else {
            noteText.setText("undefined");
        }
    }

    @SuppressLint("SetTextI18n")
    public void lowE_Tuning(float pitchInHz) { // slash tuning
        pitchText.setText("" + pitchInHz);
        // za sweet spot je green

        if(pitchInHz >= 70 && pitchInHz < 86) {
            //e - 78 je sweet spot
            noteText.setText("e");
        }
        else if(pitchInHz >= 97 && pitchInHz < 113) {
            //A - 105 je sweet spot
            noteText.setText("A");
        }
        else if(pitchInHz >= 129 && pitchInHz < 148) {
            //D - 139 je sweet spot
            noteText.setText("D");
        }
        else if(pitchInHz >= 175 && pitchInHz < 195) {
            //G - 185 je sweet spot
            noteText.setText("G");
        }
        else if(pitchInHz >= 223 && pitchInHz <= 243) {
            //B - 233 je sweet spot
            noteText.setText("B");
        }
        else if(pitchInHz >= 302 && pitchInHz < 322) {
            //E - 312 je sweet spot
            noteText.setText("E");
        }
        else {
            noteText.setText("undefined");
        }
    }

    private int checkPermissions() {
        return ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.RECORD_AUDIO);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}