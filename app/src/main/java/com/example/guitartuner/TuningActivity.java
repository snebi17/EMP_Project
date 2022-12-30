package com.example.guitartuner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import be.tarsos.dsp.io.android.AudioDispatcherFactory;
import be.tarsos.dsp.pitch.*;
import be.tarsos.dsp.*;


public class TuningActivity extends AppCompatActivity {
    TextView tuning;
    FloatingActionButton fab;

    TextView string;
    TextView pitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tuning);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            tuning = (TextView) findViewById(R.id.tuning_name);
            tuning.setText(extras.getString("tuning"));
        }

        string = (TextView) findViewById(R.id.string);
        pitch = (TextView) findViewById(R.id.pitch);

        if (checkPermissions() == PackageManager.PERMISSION_GRANTED) {
            AudioDispatcher dispatcher = AudioDispatcherFactory.fromDefaultMicrophone(22050, 1024, 0);
            PitchDetectionHandler pdh = (res, e) -> {
                final float pitchInHz = res.getPitch();
                runOnUiThread(() -> {
                    if (tuning.getText().toString().equals("Standard")) {
                        standardTuning(pitchInHz);
                    } else if (tuning.getText().toString().equals("Low E")) {
                        lowETuning(pitchInHz);
                    } else if (tuning.getText().toString().equals("Drop D")) {
                        dropDTuning(pitchInHz);
                    }
                });
            };

            AudioProcessor pitchProcessor = new PitchProcessor(PitchProcessor.PitchEstimationAlgorithm.FFT_YIN, 22050, 1024, pdh);
            dispatcher.addAudioProcessor(pitchProcessor);

            Thread audioThread = new Thread(dispatcher, "Audio Thread");
            audioThread.start();
        } else {
            ActivityCompat.requestPermissions(
                    TuningActivity.this,
                    new String[]{ Manifest.permission.RECORD_AUDIO
                    }, 1);
        }

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            Intent intent = new Intent(view.getContext(), MainActivity.class);
            startActivity(intent);
        });
    }

    private void standardTuning(float pitchInHz) {
        if (pitchInHz < 10) {
            string.setText("");
            pitch.setText("Start stroking!");
        }
        if (pitchInHz >= 72.4 && pitchInHz < 92.4) {
            string.setText("e");
            if (pitchInHz < 80.5) {
                string.setTextColor(Color.RED);
                pitch.setText("Tune it up!");
            } else if (pitchInHz > 83.5) {
                string.setTextColor(Color.RED);
                pitch.setText("Tune it down!");
            } else {
                string.setTextColor(Color.GREEN);
                pitch.setText("Sweet spot!");
            }
        }
        else if (pitchInHz >= 100 && pitchInHz < 120) {
            string.setText("A");
            if (pitchInHz < 108.5) {
                string.setTextColor(Color.RED);
                pitch.setText("Tune it up!");
            } else if (pitchInHz > 111.5) {
                string.setTextColor(Color.RED);
                pitch.setText("Tune it down!");
            } else {
                string.setTextColor(Color.GREEN);
                pitch.setText("Sweet spot!");
            }
        }
        else if (pitchInHz >= 137 && pitchInHz < 157) {
            string.setText("D");
            if (pitchInHz < 145.5) {
                string.setTextColor(Color.RED);
                pitch.setText("Tune it up!");
            } else if (pitchInHz > 148.5) {
                string.setTextColor(Color.RED);
                pitch.setText("Tune it down!");
            } else {
                string.setTextColor(Color.GREEN);
                pitch.setText("Sweet spot!");
            }
        }
        else if (pitchInHz >= 186 && pitchInHz < 206) {
            string.setText("G");
            if (pitchInHz < 194.5) {
                string.setTextColor(Color.RED);
                pitch.setText("Tune it up!");
            } else if (pitchInHz > 197.5) {
                string.setTextColor(Color.RED);
                pitch.setText("Tune it down!");
            } else {
                string.setTextColor(Color.GREEN);
                pitch.setText("Sweet spot!");
            }
        }
        else if (pitchInHz >= 237 && pitchInHz <= 257) {
            string.setText("B");
            if (pitchInHz < 245.5) {
                string.setTextColor(Color.RED);
                pitch.setText("Tune it up!");
            } else if (pitchInHz > 248.5) {
                string.setTextColor(Color.RED);
                pitch.setText("Tune it down!");
            } else {
                string.setTextColor(Color.GREEN);
                pitch.setText("Sweet spot!");
            }
        }
        else if (pitchInHz >= 320 && pitchInHz < 340) {
            string.setText("E");
            if (pitchInHz < 328.5) {
                string.setTextColor(Color.RED);
                pitch.setText("Tune it up!");
            } else if (pitchInHz > 331.5) {
                string.setTextColor(Color.RED);
                pitch.setText("Tune it down!");
            } else {
                string.setTextColor(Color.GREEN);
                pitch.setText("Sweet spot!");
            }
        }
    }

    private void dropDTuning(float pitchInHz) {
        if (pitchInHz < 10) {
            string.setText("");
            pitch.setText("Start stroking!");
        }

        if (pitchInHz >= 63.4 && pitchInHz < 83.4) {
            string.setText("e");
            if (pitchInHz < 72.5) {
                string.setTextColor(Color.RED);
                pitch.setText("Tune it up!");
            } else if (pitchInHz > 73.5) {
                string.setTextColor(Color.RED);
                pitch.setText("Tune it down!");
            } else {
                string.setTextColor(Color.GREEN);
                pitch.setText("Sweet spot!");
            }
        }
        else if (pitchInHz >= 100 && pitchInHz < 120) {
            string.setText("A");
            if (pitchInHz < 108.5) {
                string.setTextColor(Color.RED);
                pitch.setText("Tune it up!");
            } else if (pitchInHz > 111.5) {
                string.setTextColor(Color.RED);
                pitch.setText("Tune it down!");
            } else {
                string.setTextColor(Color.GREEN);
                pitch.setText("Sweet spot!");
            }
        }
        else if (pitchInHz >= 137 && pitchInHz < 157) {
            string.setText("D");
            if (pitchInHz < 145.5) {
                string.setTextColor(Color.RED);
                pitch.setText("Tune it up!");
            } else if (pitchInHz > 148.5) {
                string.setTextColor(Color.RED);
                pitch.setText("Tune it down!");
            } else {
                string.setTextColor(Color.GREEN);
                pitch.setText("Sweet spot!");
            }
        }
        else if (pitchInHz >= 186 && pitchInHz < 206) {
            string.setText("G");
            if (pitchInHz < 194.5) {
                string.setTextColor(Color.RED);
                pitch.setText("Tune it up!");
            } else if (pitchInHz > 197.5) {
                string.setTextColor(Color.RED);
                pitch.setText("Tune it down!");
            } else {
                string.setTextColor(Color.GREEN);
                pitch.setText("Sweet spot!");
            }
        }
        else if (pitchInHz >= 237 && pitchInHz <= 257) {
            string.setText("B");
            if (pitchInHz < 245.5) {
                string.setTextColor(Color.RED);
                pitch.setText("Tune it up!");
            } else if (pitchInHz > 248.5) {
                string.setTextColor(Color.RED);
                pitch.setText("Tune it down!");
            } else {
                string.setTextColor(Color.GREEN);
                pitch.setText("Sweet spot!");
            }
        }
        else if (pitchInHz >= 320 && pitchInHz < 340) {
            string.setText("E");
            if (pitchInHz < 328.5) {
                string.setTextColor(Color.RED);
                pitch.setText("Tune it up!");
            } else if (pitchInHz > 331.5) {
                string.setTextColor(Color.RED);
                pitch.setText("Tune it down!");
            } else {
                string.setTextColor(Color.GREEN);
                pitch.setText("Sweet spot!");
            }
        }
    }

    private void lowETuning(float pitchInHz) {
        if (pitchInHz < 10) {
            string.setText("");
            pitch.setText("Start stroking!");
        }

        if(pitchInHz >= 70 && pitchInHz < 86) {
            if (pitchInHz < 76.5) {
                string.setTextColor(Color.RED);
                pitch.setText("Tune it up!");
            } else if (pitchInHz > 79.5) {
                string.setTextColor(Color.RED);
                pitch.setText("Tune it down!");
            } else {
                string.setTextColor(Color.GREEN);
                pitch.setText("Sweet spot!");
            }
        }
        else if(pitchInHz >= 97 && pitchInHz < 113) {
            string.setText("A");
            if (pitchInHz < 103.5) {
                string.setTextColor(Color.RED);
                pitch.setText("Tune it up!");
            } else if (pitchInHz > 106.5) {
                string.setTextColor(Color.RED);
                pitch.setText("Tune it down!");
            } else {
                string.setTextColor(Color.GREEN);
                pitch.setText("Sweet spot!");
            }
        }
        else if(pitchInHz >= 129 && pitchInHz < 148) {
            string.setText("D");
            if (pitchInHz < 137.5) {
                string.setTextColor(Color.RED);
                pitch.setText("Tune it up!");
            } else if (pitchInHz > 140.5) {
                string.setTextColor(Color.RED);
                pitch.setText("Tune it down!");
            } else {
                string.setTextColor(Color.GREEN);
                pitch.setText("Sweet spot!");
            }
        }
        else if(pitchInHz >= 175 && pitchInHz < 195) {
            string.setText("G");
            if (pitchInHz < 183.5) {
                string.setTextColor(Color.RED);
                pitch.setText("Tune it up!");
            } else if (pitchInHz > 186.5) {
                string.setTextColor(Color.RED);
                pitch.setText("Tune it down!");
            } else {
                string.setTextColor(Color.GREEN);
                pitch.setText("Sweet spot!");
            }
        }
        else if(pitchInHz >= 223 && pitchInHz <= 243) {
            string.setText("B");
            if (pitchInHz < 231.5) {
                string.setTextColor(Color.RED);
                pitch.setText("Tune it up!");
            } else if (pitchInHz > 234.5) {
                string.setTextColor(Color.RED);
                pitch.setText("Tune it down!");
            } else {
                string.setTextColor(Color.GREEN);
                pitch.setText("Sweet spot!");
            }
        }
        else if(pitchInHz >= 302 && pitchInHz < 322) {
            string.setText("E");
            if (pitchInHz < 310.5) {
                string.setTextColor(Color.RED);
                pitch.setText("Tune it up!");
            } else if (pitchInHz > 313.5) {
                string.setTextColor(Color.RED);
                pitch.setText("Tune it down!");
            } else {
                string.setTextColor(Color.GREEN);
                pitch.setText("Sweet spot!");
            }
        }
    }

    private int checkPermissions() {
        return ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.RECORD_AUDIO);
    }
}
