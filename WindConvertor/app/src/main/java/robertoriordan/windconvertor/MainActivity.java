package robertoriordan.windconvertor;

import android.app.DialogFragment;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements WarningAlert.NoticeDialogListener{

    public static final String EXTRA_BEAU = "com.robertoriordan.windconvertor.beau";
    static final String OUTPUT = "";
    //Setting for whether beaufort outputs are displayed as number/title
    private boolean setting = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView5 = (TextView) findViewById(R.id.textView5);
        if (savedInstanceState != null) {
            // Restore value of members from saved state
            textView5.setText(savedInstanceState.getString(OUTPUT));
            setting = savedInstanceState.getBoolean("BEAU_AS_TITLE");
        }

        //Loading Beaufort Output display setting from previous app session
        SharedPreferences settings = getSharedPreferences("prefs_file", 0);
        setting = settings.getBoolean("BEAU_AS_TITLE", false);

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

        //Setting Beaufort Display setting from Settings Activity intent
        Intent intent = getIntent();
        setting = intent.getBooleanExtra("BEAU_AS_TITLE", setting);

        //Setting up Spinner adapters to hold the arrays specified in string file
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.inputTypes, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.outputTypes, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner2.setAdapter(adapter2);

        //Pending Notification code
        String notify_title = getResources().getString(R.string.notify_title);
        String notify_message = getResources().getString(R.string.notify_message);

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.call_notify)
                        .setContentTitle(notify_title)
                        .setContentText(notify_message);

        Intent intent2 = new Intent(Intent.ACTION_CALL);

        intent2.setData(Uri.parse("tel:0211234567"));

        PendingIntent resultPendingIntent =
                PendingIntent.getActivity(
                        this,
                        0,
                        intent2,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );

        mBuilder.setContentIntent(resultPendingIntent);

        // Sets an ID for the notification
        int mNotificationId = 001;
        // Gets an instance of the NotificationManager service
        NotificationManager mNotifyMgr =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        // Builds the notification and issues it.
        mNotifyMgr.notify(mNotificationId, mBuilder.build());
    }

    @Override
    protected void onStop(){
        super.onStop();

        // Saving Beaufort display setting for future session
        SharedPreferences settings = getSharedPreferences("prefs_file", 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean("BEAU_AS_TITLE", setting);

        // Commit the edits!
        editor.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //checks if settings button is selected and runs method
        if (id == R.id.action_settings) {
            ioSettings();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        TextView textView5 = (TextView) findViewById(R.id.textView5);

        // Saves current output/beaufort setting for orientation changes
        savedInstanceState.putString(OUTPUT, (String) textView5.getText());
        savedInstanceState.putBoolean("BEAU_AS_TITLE", setting);

        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }

    //Method triggered when convert button selected
    public void convert(View view) {

        EditText editText = (EditText) findViewById(R.id.editText);
        String input = editText.getText().toString();

        //Adds 0 if user hasn't entered anything
        if (input.isEmpty()) {
            editText.setText("0");
            input = editText.getText().toString();
        }

        double windspeed = Double.parseDouble(input);

        Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);
        String outputType = spinner2.getSelectedItem().toString();

        TextView textView5 = (TextView) findViewById(R.id.textView5);
        String item1 = spinner2.getItemAtPosition(0).toString();
        String item2 = spinner2.getItemAtPosition(1).toString();
        String item3 = spinner2.getItemAtPosition(2).toString();

        //Picks a method of conversion based on selection in spinners
        if (outputType.equals(item1)) {
            textView5.setText(convertToKm(windspeed));
        }
        else if (outputType.equals(item2)) {
            textView5.setText(convertToKnots(windspeed));
        }
        else if (outputType.equals(item3)) {
            textView5.setText(convertToBeaufort(windspeed));
        }

    }

    //Method for converting to Kilometer/Hour
    public String convertToKm(double windspeed) {

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        String inputType = spinner.getSelectedItem().toString();
        String item1 = spinner.getItemAtPosition(0).toString();
        String item2 = spinner.getItemAtPosition(1).toString();
        String item3 = spinner.getItemAtPosition(2).toString();

        if (inputType.equals(item2)) {
            return Double.toString(1.85 * windspeed);
        }
        else if (inputType.equals(item3)) {
            return calcBeaufort(windspeed, item1, true);
        }

        return Double.toString(windspeed);
    }

    //Method for converting to Knots
    public String convertToKnots(double windspeed) {

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        String inputType = spinner.getSelectedItem().toString();
        String item1 = spinner.getItemAtPosition(0).toString();
        String item2 = spinner.getItemAtPosition(1).toString();
        String item3 = spinner.getItemAtPosition(2).toString();

        if (inputType.equals(item1)) {
            return Double.toString(0.539956803 * windspeed);
        }
        else if (inputType.equals(item3)) {
            return calcBeaufort(windspeed, item2, true);
        }

        return Double.toString(windspeed);
    }

    //Method for converting to beaufort
    public String convertToBeaufort(double windspeed) {

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        String inputType = spinner.getSelectedItem().toString();
        String item1 = spinner.getItemAtPosition(0).toString();
        String item2 = spinner.getItemAtPosition(1).toString();

        if (inputType.equals(item1)) {
            return calcBeaufort(windspeed, item1, false);
        }
        else if (inputType.equals(item2)) {
            return calcBeaufort(windspeed, item2, false);
        }

        return calcBeaufort(windspeed, "", true);
    }

    public String calcBeaufort(double value, String type, boolean convertFromBeau) {

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        String item1 = spinner.getItemAtPosition(0).toString();
        String item2 = spinner.getItemAtPosition(1).toString();
        String warning = getResources().getString(R.string.warning);
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


        if (convertFromBeau) {
            if (type.equals(item1)) {

                if (value == 0) {
                    return "< 1.1";
                }
                else if (value == 1) {
                    return "1.1 - 5.5";
                }
                else if (value == 2) {
                    return "5.5 - 11.9";
                }
                else if (value == 3) {
                    return "11.9 - 19.7";
                }
                else if (value == 4) {
                    return "19.7 - 28.7";
                }
                else if (value == 5) {
                    return "28.7 - 38.8";
                }
                else if (value == 6) {
                    return "38.8 - 49.9";
                }
                else if (value == 7) {
                    return "49.9 - 61.8";
                }
                else if (value == 8) {
                    return "61.8 - 74.6";
                }
                else if (value == 9) {
                    return "74.6 - 88.1";
                }
                else if (value == 10) {
                    return "88.1 - 102.4";
                }
                else if (value == 11) {
                    return "102.4 - 117.4";
                }
                else if (value == 12) {
                    return ">= 117.4";
                }
                else {
                    return warning;
                }
            }
            else if (type.equals(item2)) {

                if (value == 0) {
                    return "< 0.6";
                }
                else if (value == 1) {
                    return "0.6 - 3";
                }
                else if (value == 2) {
                    return "3 - 6.4";
                }
                else if (value == 3) {
                    return "6.4 - 10.6";
                }
                else if (value == 4) {
                    return "10.6 - 15.5";
                }
                else if (value == 5) {
                    return "15.5 - 21";
                }
                else if (value == 6) {
                    return "21 - 26.9";
                }
                else if (value == 7) {
                    return "26.9 - 3.4";
                }
                else if (value == 8) {
                    return "33.4 - 40.3";
                }
                else if (value == 9) {
                    return "40.3 - 47.6";
                }
                else if (value == 10) {
                    return "47.6 - 55.3";
                }
                else if (value == 11) {
                    return "55.3 - 63.4";
                }
                else if (value == 12) {
                    return ">= 63.4";
                }
                else {
                    return warning;
                }
            }
                double valid = value % 1;
                int newValue = (int) value;

                if (valid == 0 && value < 13) {

                    if (setting == false) {
                        return Integer.toString(newValue);
                    }

                    if (value == 0) {
                        return beau0;
                    } else if (value == 1) {
                        return beau1;
                    } else if (value == 2) {
                        return beau2;
                    } else if (value == 3) {
                        return beau3;
                    } else if (value == 4) {
                        return beau4;
                    } else if (value == 5) {
                        return beau5;
                    } else if (value == 6) {
                        return beau6;
                    } else if (value == 7) {
                        return beau7;
                    } else if (value == 8) {
                        return beau8;
                    } else if (value == 9) {
                        return beau9;
                    } else if (value == 10) {
                        return beau10;
                    } else if (value == 11) {
                        return beau11;
                    } else if (value == 12) {
                        return beau12;
                    }
                }

                return warning;
        }
        else {

            //Condition for if beaufort output is displayed as a number
            if (setting == false) {
                if (type.equals(item1)) {

                    if (value < 1.1) {
                        return "0";
                    } else if (value >= 1.1 && value < 5.5) {
                        return "1";
                    } else if (value >= 5.5 && value < 11.9) {
                        return "2";
                    } else if (value >= 11.9 && value < 19.7) {
                        return "3";
                    } else if (value >= 19.7 && value < 28.7) {
                        return "4";
                    } else if (value >= 28.7 && value < 38.8) {
                        return "5";
                    } else if (value >= 38.8 && value < 49.9) {
                        return "6";
                    } else if (value >= 49.9 && value < 61.8) {
                        return "7";
                    } else if (value >= 61.8 && value < 74.6) {
                        return "8";
                    } else if (value >= 74.6 && value < 88.1) {
                        return "9";
                    } else if (value >= 88.1 && value < 102.4) {
                        return "10";
                    } else if (value >= 102.4 && value < 117.4) {
                        return "11";
                    } else if (value >= 117.4) {
                        return "12";
                    }
                } else if (type.equals(item2)) {

                    if (value < 0.6) {
                        return "0";
                    } else if (value >= 0.6 && value < 3) {
                        return "1";
                    } else if (value >= 3 && value < 6.4) {
                        return "2";
                    } else if (value >= 6.4 && value < 10.6) {
                        return "3";
                    } else if (value >= 10.6 && value < 15.5) {
                        return "4";
                    } else if (value >= 15.5 && value < 21) {
                        return "5";
                    } else if (value >= 21 && value < 26.9) {
                        return "6";
                    } else if (value >= 26.9 && value < 33.4) {
                        return "7";
                    } else if (value >= 33.4 && value < 40.3) {
                        return "8";
                    } else if (value >= 40.3 && value < 47.6) {
                        return "9";
                    } else if (value >= 47.6 && value < 55.3) {
                        return "10";
                    } else if (value >= 55.3 && value < 63.4) {
                        return "11";
                    } else if (value >= 63.4) {
                        return "12";
                    }
                }
            }

            //Condition for if beaufort output is displayed as a title
            if (type.equals(item1)) {

                if (value < 1.1) {
                    return beau0;
                } else if (value >= 1.1 && value < 5.5) {
                    return beau1;
                } else if (value >= 5.5 && value < 11.9) {
                    return beau2;
                } else if (value >= 11.9 && value < 19.7) {
                    return beau3;
                } else if (value >= 19.7 && value < 28.7) {
                    return beau4;
                } else if (value >= 28.7 && value < 38.8) {
                    return beau5;
                } else if (value >= 38.8 && value < 49.9) {
                    return beau6;
                } else if (value >= 49.9 && value < 61.8) {
                    return beau7;
                } else if (value >= 61.8 && value < 74.6) {
                    return beau8;
                } else if (value >= 74.6 && value < 88.1) {
                    return beau9;
                } else if (value >= 88.1 && value < 102.4) {
                    return beau10;
                } else if (value >= 102.4 && value < 117.4) {
                    return beau11;
                } else if (value >= 117.4) {
                    return beau12;
                }
            } else if (type.equals(item2)) {

                if (value < 0.6) {
                    return beau0;
                } else if (value >= 0.6 && value < 3) {
                    return beau1;
                } else if (value >= 3 && value < 6.4) {
                    return beau2;
                } else if (value >= 6.4 && value < 10.6) {
                    return beau3;
                } else if (value >= 10.6 && value < 15.5) {
                    return beau4;
                } else if (value >= 15.5 && value < 21) {
                    return beau5;
                } else if (value >= 21 && value < 26.9) {
                    return beau6;
                } else if (value >= 26.9 && value < 33.4) {
                    return beau7;
                } else if (value >= 33.4 && value < 40.3) {
                    return beau8;
                } else if (value >= 40.3 && value < 47.6) {
                    return beau9;
                } else if (value >= 47.6 && value < 55.3) {
                    return beau10;
                } else if (value >= 55.3 && value < 63.4) {
                    return beau11;
                } else if (value >= 63.4) {
                    return beau12;
                }
            }
        }

        return Double.toString(value);
    }

    //Method for triggering additional Beaufort details activity
    public void showBeauInfo(View view) {
        Intent intent = new Intent(this, Activity2.class);
        EditText editText = (EditText) findViewById(R.id.editText);
        TextView textView = (TextView) findViewById(R.id.textView5);
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        String item1 = spinner.getItemAtPosition(0).toString();
        String item2 = spinner.getItemAtPosition(1).toString();
        String warning = getResources().getString(R.string.warning);

        String input = editText.getText().toString();
        String output = textView.getText().toString();

        if (output.equals(warning)) {
            DialogFragment newFragment = new WarningAlert();
            newFragment.show(getFragmentManager(), "warning");
        }
        else {
            if (input.isEmpty()) {
                input = "0";
            }

            double windSpeed = Double.parseDouble(input);
            String beau = "";

            if (spinner.getSelectedItem() == spinner.getItemAtPosition(0)) {
                beau = calcBeaufort(windSpeed, item1, false);
            } else if (spinner.getSelectedItem() == spinner.getItemAtPosition(1)) {
                beau = calcBeaufort(windSpeed, item2, false);
            } else {
                beau = calcBeaufort(windSpeed, "", true);
            }

            //Carries over beaufort output data from the current input via intent to new activity
            intent.putExtra(EXTRA_BEAU, beau);
            intent.putExtra("BEAU_AS_TITLE", setting);

            startActivity(intent);
        }
    }

    //Method for triggering settings activity
    public void ioSettings() {
        Intent intent = new Intent(this, Activity3.class);
        intent.putExtra("BEAU_AS_TITLE", setting);
        startActivity(intent);
    }

    //AlertDialog onClick listeners
    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        Intent intent = new Intent(this, Activity2.class);
        intent.putExtra(EXTRA_BEAU, "0");
        intent.putExtra("BEAU_AS_TITLE", setting);

        startActivity(intent);
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        //does nothing
    }

    @Override
    public void onDialogCustomClick(DialogFragment dialog) {
        EditText editText = (EditText) findViewById(R.id.editText);
        editText.setText("");
        editText.selectAll();
    }
}
