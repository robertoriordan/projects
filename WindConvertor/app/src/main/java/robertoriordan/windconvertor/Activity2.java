package robertoriordan.windconvertor;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class Activity2 extends AppCompatActivity {

    private boolean setting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        String beau = intent.getStringExtra(MainActivity.EXTRA_BEAU);
        setting = intent.getBooleanExtra("BEAU_AS_TITLE", false);
        displayBeau(beau);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void displayBeau(String beau) {

        TextView beauText = (TextView) findViewById(R.id.textView7);
        TextView descText = (TextView) findViewById(R.id.textView8);
        String beau0 = getResources().getString(R.string.beau0);
        String beau1 = getResources().getString(R.string.beau1);
        String beau2 = getResources().getString(R.string.beau2);
        String beau3 = getResources().getString(R.string.beau3);
        String beau4 = getResources().getString(R.string.beau4);
        String beau5 = getResources().getString(R.string.beau5);
        String beau6 = getResources().getString(R.string.beau6);
        String beau7 = getResources().getString(R.string.beau7);
        String beau8 = getResources().getString(R.string.beau8);
        String beau9 = getResources().getString(R.string.beau9);
        String beau10 = getResources().getString(R.string.beau10);
        String beau11 = getResources().getString(R.string.beau11);
        String beau12 = getResources().getString(R.string.beau12);

        if (setting == false) {
            beauText.setText(beau);

            if (beau.equals("1")) {
                descText.setText(R.string.beau1);
            } else if (beau.equals("2")) {
                descText.setText(R.string.beau2);
            } else if (beau.equals("3")) {
                descText.setText(R.string.beau3);
            } else if (beau.equals("4")) {
                descText.setText(R.string.beau4);
            } else if (beau.equals("5")) {
                descText.setText(R.string.beau5);
            } else if (beau.equals("6")) {
                descText.setText(R.string.beau6);
            } else if (beau.equals("7")) {
                descText.setText(R.string.beau7);
            } else if (beau.equals("8")) {
                descText.setText(R.string.beau8);
            } else if (beau.equals("9")) {
                descText.setText(R.string.beau9);
            } else if (beau.equals("10")) {
                descText.setText(R.string.beau10);
            } else if (beau.equals("11")) {
                descText.setText(R.string.beau11);
            } else if (beau.equals("12")) {
                descText.setText(R.string.beau12);
            } else {
                descText.setText(R.string.beau0);
            }
        }
        else {
            descText.setText(beau);

            if (beau.equals(beau1)) {
                beauText.setText("1");
            } else if (beau.equals(beau2)) {
                beauText.setText("2");
            } else if (beau.equals(beau3)) {
                beauText.setText("3");
            } else if (beau.equals(beau4)) {
                beauText.setText("4");
            } else if (beau.equals(beau5)) {
                beauText.setText("5");
            } else if (beau.equals(beau6)) {
                beauText.setText("6");
            } else if (beau.equals(beau7)) {
                beauText.setText("7");
            } else if (beau.equals(beau8)) {
                beauText.setText("8");
            } else if (beau.equals(beau9)) {
                beauText.setText("9");
            } else if (beau.equals(beau10)) {
                beauText.setText("10");
            } else if (beau.equals(beau11)) {
                beauText.setText("11");
            } else if (beau.equals(beau12)) {
                beauText.setText("12");
            } else {
                beauText.setText("0");
            }
        }
    }

}
