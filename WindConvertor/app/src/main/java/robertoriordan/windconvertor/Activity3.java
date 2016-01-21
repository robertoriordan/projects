package robertoriordan.windconvertor;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;

import java.io.FileOutputStream;

public class Activity3 extends AppCompatActivity {

    private boolean isBeauAsTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        isBeauAsTitle = intent.getBooleanExtra("BEAU_AS_TITLE", false);

        CheckBox checkBox = (CheckBox) findViewById(R.id.checkBox4);

        if (isBeauAsTitle) {
            checkBox.setChecked(true);
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void apply(View view) {

        Intent intent = new Intent(this, MainActivity.class);
        CheckBox checkBox = (CheckBox) findViewById(R.id.checkBox4);
        isBeauAsTitle = false;

        if (checkBox.isChecked()) {
            isBeauAsTitle = true;
        }

        intent.putExtra("BEAU_AS_TITLE", isBeauAsTitle);
        startActivity(intent);
    }

}
