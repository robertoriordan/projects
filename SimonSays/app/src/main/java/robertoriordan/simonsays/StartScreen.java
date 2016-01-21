package robertoriordan.simonsays;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

public class StartScreen extends AppCompatActivity {

    private int highscore = 5;
    private String difficulty = "EASY";
    private String level = "0";
    private String mode = "Normal";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);

        //Loading Beaufort Output display setting from previous app session
        SharedPreferences settings = getSharedPreferences("prefs_file", 0);
        highscore = settings.getInt("HIGHSCORE", 5);

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

        Intent intent = getIntent();
        highscore = intent.getIntExtra("HIGHSCORE", 5);

        //Set up Spinner & Adapter
        Spinner spinner = (Spinner) findViewById(R.id.spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.difficulties, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        //Set up seekBar & Text
        SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar);
        final TextView text = (TextView) findViewById(R.id.textView3);

        seekBar.setMax(highscore);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                text.setText(Integer.toString(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        //Set up RadioButtons
        final RadioButton radio1 = (RadioButton) findViewById(R.id.radioButton);
        final RadioButton radio2 = (RadioButton) findViewById(R.id.radioButton2);

        radio1.setChecked(true);

        radio1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radio2.setChecked(false);
            }
        });

        radio2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radio1.setChecked(false);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_start_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStop(){
        super.onStop();

        // Saving Beaufort display setting for future session
        SharedPreferences settings = getSharedPreferences("prefs_file", 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt("HIGHSCORE", highscore);

        // Commit the edits!
        editor.commit();
    }

    public void playGame(View view) {

        Intent intent = new Intent(this, GameScreen.class);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        difficulty = (String) spinner.getSelectedItem();

        TextView text = (TextView) findViewById(R.id.textView3);
        level = (String) text.getText();

        RadioButton radio1 = (RadioButton) findViewById(R.id.radioButton);
        RadioButton radio2 = (RadioButton) findViewById(R.id.radioButton2);

        if (radio1.isChecked()) {
            mode = (String) radio1.getText();
        }
        else {
            mode = (String) radio2.getText();
        }

        intent.putExtra("DIFFICULTY", difficulty);
        intent.putExtra("LEVEL", level);
        intent.putExtra("MODE", mode);
        intent.putExtra("HIGHSCORE", highscore);

        startActivity(intent);
    }
}
