package com.example.norman_lee.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button buttonConvert;
    Button buttonSetExchangeRate;
    EditText editTextValue;
    TextView textViewResult;
    TextView textViewExchangeRate;
    double exchangeRate;
    public final String TAG = "Logcat";
    private SharedPreferences mPreferences;
    private String sharedPrefFile = "com.example.android.mainsharedprefs";
    public static final String RATE_KEY = "Rate_Key";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TODO 4.5 Get a reference to the sharedPreferences object
        mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
        //TODO 4.6 Retrieve the value using the key, and set a default when there is none
        exchangeRate = Double.parseDouble(mPreferences.getString(RATE_KEY, "2.95"));
        //TODO 3.13 Get the intent and retrieve the exchange rate passed to it
        Intent intent = getIntent();
        if (intent != null) {
            exchangeRate = intent.getDoubleExtra(SubActivity.INTENT_EXCH_RATE, exchangeRate);
        }
        //TODO 2.1 Use findViewById to get references to the widgets in the layout
        buttonConvert = findViewById(R.id.buttonConvert);
        buttonSetExchangeRate = findViewById(R.id.buttonSetExchangeRate);
        editTextValue = findViewById(R.id.editTextValue);
        textViewResult = findViewById(R.id.textViewResult);
        textViewExchangeRate = findViewById(R.id.textViewExchangeRate);
        //TODO 2.2 Assign a default exchange rate of 2.95 to the textView
        textViewExchangeRate.setText(String.valueOf(exchangeRate));
        //TODO 2.3 Set up setOnClickListener for the Convert Button
        buttonConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO 2.4 Display a Toast & Logcat message if the editTextValue widget contains an empty string
                if (editTextValue.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter a value", Toast.LENGTH_LONG).show(); // pop up text at bottom of screen
                    Log.d(TAG, "Please enter a value");
                } else {
                    //TODO 2.5 If not, calculate the units of B with the exchange rate and display it
                    double value = Double.parseDouble(editTextValue.getText().toString());
                    double result = value * exchangeRate;
                    textViewResult.setText(String.valueOf(result));
                }
            }
        });

        //TODO 3.1 Modify the Android Manifest to specify that the parent of SubActivity is MainActivity
        //TODO 3.2 Get a reference to the Set Exchange Rate Button
        //TODO 3.3 Set up setOnClickListener for this
        buttonSetExchangeRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO 3.4 Write an Explicit Intent to get to SubActivity
                Intent intent = new Intent(MainActivity.this, SubActivity.class);
                startActivity(intent);
            }
        });

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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    //TODO 4.1 Go to res/menu/menu_main.xml and add a menu item Set Exchange Rate
    //TODO 4.2 In onOptionsItemSelected, add a new if-statement and code accordingly
    //TODO 5.1 Go to res/menu/menu_main.xml and add a menu item Open Map App
    //TODO 5.2 In onOptionsItemSelected, add a new if-statement
    //TODO 5.3 code the Uri object and set up the intent
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        // to identify which specific button is pressed -> grabs ID

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.setExchangeRate) {
            Intent intent = new Intent(MainActivity.this, ExchangeRate.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.setOpenMapApp) { // 5.2 and 5.3
            String location = "SUTD";
            Uri.Builder builder = new Uri.Builder();
            builder.scheme("geo").opaquePart("0.0").appendQueryParameter("q", location);
            Uri geoLocation = builder.build();

            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(geoLocation);
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
        }

        return super.onOptionsItemSelected(item);
    }

    //TODO 4.3 override the methods in the Android Activity Lifecycle here
    //TODO 4.4 for each of them, write a suitable string to display in the Logcat
    @Override
    protected void onPause() {
        super.onPause();
        //TODO 4.7 In onPause, get a reference to the SharedPreferences.Editor object
        SharedPreferences.Editor preferencesEditor = mPreferences.edit();
        //TODO 4.8 store the exchange rate using the putString method with a key
        preferencesEditor.putString(RATE_KEY, String.valueOf(exchangeRate));
        preferencesEditor.apply();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart called");
    }
    // so and so forth
}
