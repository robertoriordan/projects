package robertoriordan.simonsays;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.Random;

public class GameScreen extends AppCompatActivity implements View.OnClickListener {

    private ArrayList<SimonButton> colours = new ArrayList<SimonButton>();
    private ArrayList<SimonButton> sequence = new ArrayList<SimonButton>();
    private ArrayList<SimonButton> userChoice = new ArrayList<SimonButton>();
    private SimonButton red;
    private SimonButton green;
    private SimonButton blue;
    private SimonButton yellow;
    private boolean correctGuess;
    private int highscore = 5;
    private int currentScore = 0;
    private int lives = 3;
    private boolean gameOver = false;
    private String difficulty = "EASY";
    private int level = 0;
    private String mode = "Normal";
    private Intent intent2;
    private GestureDetector gD;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gD.onTouchEvent(event);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        intent2 = new Intent(this, StartScreen.class);

        //Add Fling functionality (for flick right)
        gD = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

                if (e1.getX() + e2.getX() > 60) {
                    intent2.putExtra("HIGHSCORE", highscore);
                    startActivity(intent2);
                }
                else if (e1.getY() - e2.getY() > 60) {
                    if (difficulty.equals("EASY")) {
                        lives = 3;
                    }
                    else {
                        lives = 1;
                    }

                    sequence.clear();
                    gameOver = true;
                    animateSequence();
                }

                return false;
            }
        });

        //Pass data from intent
        Intent intent = getIntent();
        difficulty = intent.getStringExtra("DIFFICULTY");
        level = Integer.parseInt(intent.getStringExtra("LEVEL"));
        mode = intent.getStringExtra("MODE");
        highscore = intent.getIntExtra("HIGHSCORE", 5);

        //Change lives based on difficulty
        if (difficulty.equals("EASY")) {
            lives = 3;
        }
        else {
            lives = 1;
        }

        //Create Layout references
        LinearLayout layout = (LinearLayout) findViewById(R.id.LinLayout);
        LinearLayout layout2 = (LinearLayout) findViewById(R.id.LinLayout2);

        //Set TextViews
        TextView text1 = (TextView) findViewById(R.id.textView7);
        TextView text2 = (TextView) findViewById(R.id.textView8);
        TextView text3 = (TextView) findViewById(R.id.textView10);

        text1.setText(Integer.toString(currentScore));
        text2.setText(Integer.toString(highscore));
        text3.setText(Integer.toString(lives));

        //Set progressBar
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setMax(highscore);
        progressBar.setSecondaryProgress(1);

        //Create Simon Says buttons
        red = new SimonButton(this);
        red.setTag("redButton");
        green = new SimonButton(this);
        green.setColour("GREEN");
        green.setTag("greenButton");
        blue = new SimonButton(this);
        blue.setColour("BLUE");
        blue.setTag("blueButton");
        yellow = new SimonButton(this);
        yellow.setColour("YELLOW");
        yellow.setTag("yellowButton");

        //Add buttons to layouts
        layout.addView(red, 200, 200);
        layout.addView(green, 200, 200);
        layout2.addView(blue, 200, 200);
        layout2.addView(yellow, 200, 200);

        layout.setHorizontalGravity(200);

        //Add colours to list
        colours.add(red);
        colours.add(green);
        colours.add(blue);
        colours.add(yellow);

        //add listeners to colours
        red.setOnClickListener(this);
        green.setOnClickListener(this);
        blue.setOnClickListener(this);
        yellow.setOnClickListener(this);

        //Add additional colours to sequence based on level
        Random r = new Random();

        for (int i = 0; i < level; ++i) {
            int index = r.nextInt(4);
            sequence.add(colours.get(index));

            if (mode.equals("Double Trouble")) {
                index = r.nextInt(4);
                sequence.add(colours.get(index));
            }
        }

        //Sets condition to add to colour sequence
        correctGuess = true;

        //Starts Animation
        animateSequence();
    }

    public void animateSequence() {

        //Generate random colour
        Random r = new Random();
        int index = r.nextInt(4);

        //Adds to sequence
        if (correctGuess) {
            sequence.add(colours.get(index));

            if (mode.equals("Double Trouble")) {
                index = r.nextInt(4);
                sequence.add(colours.get(index));
            }
        }

        //Resets correctGuess
        correctGuess = false;

        //Sets Animation delay & starts animation
        long delayBetweenAnimations = 1000l;

        for (int i = 0; i < sequence.size(); ++i) {

            final SimonButton current = sequence.get(i);

            int delay = (int) (i * delayBetweenAnimations);

            current.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Animation animation = new AlphaAnimation(1, 0);
                    animation.setDuration(200);
                    animation.setInterpolator(new LinearInterpolator());
                    animation.setRepeatMode(Animation.REVERSE);
                    animation.setStartOffset(200);
                    current.startAnimation(animation);
                }
            }, delay);
        }

    }

    public void onClick(View v) {

        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        TextView text1 = (TextView) findViewById(R.id.textView7);
        TextView text2 = (TextView) findViewById(R.id.textView8);
        TextView text3 = (TextView) findViewById(R.id.textView10);
        TextView text4 = (TextView) findViewById(R.id.textView11);

        if (!gameOver) {
            userChoice.add((SimonButton) v);

            if (userChoice.size() == sequence.size()) {

                if (userChoice.equals(sequence)) {
                    correctGuess = true;
                    ++currentScore;
                    text1.setText(Integer.toString(currentScore));
                    progressBar.setProgress((progressBar.getProgress()) + 1);
                    progressBar.setSecondaryProgress(progressBar.getSecondaryProgress() + 1);

                    if (currentScore > highscore) {
                        highscore = currentScore;
                        text2.setText(Integer.toString(highscore));
                    }
                } else {
                    --lives;
                    text3.setText(Integer.toString(lives));
                }

                userChoice.clear();

                if (lives != 0) {
                    animateSequence();
                } else {
                    gameOver = true;
                    text4.setText("GAME OVER!");
                }
            }
        }
    }

}
