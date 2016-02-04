package com.example.arunitgupta.myapplication;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.widget.TextView;

import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Activity_Weather extends AppCompatActivity {
    String API_URL = "https://api.fullcontact.com/v2/person.json?";
    String API_KEY = "b29103a702edd6a";
    String sourceText;
    TextView outputTextView;
    Context mContext;
    WebView weatherInfo;
    TextView City;
    TextView tempr;
    TextView Count;
    TextView Humidity;
    TextView MinTemp;
    TextView MaxTemp;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity__weather);
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
            public void logout(View v) {
                Intent redirect = new Intent(Activity_Weather.this, MainActivity.class);
                startActivity(redirect);
            }

            private void hideKeyboard(View editableView) {
                InputMethodManager imm = (InputMethodManager) mContext
                        .getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(editableView.getWindowToken(), 0);
            }

            public void temperatureDetail(View v) {
                TextView sourceTextView = (TextView) findViewById(R.id.Lbl_City);
                City = (TextView) findViewById(R.id.Txt_City);
                tempr = (TextView) findViewById(R.id.Lbl_Temp);
                Count = (TextView) findViewById(R.id.Lbl_Country);
                Humidity = (TextView) findViewById(R.id.Lbl_Humidity);
                MinTemp = (TextView) findViewById(R.id.Lbl_Min_Temp);
                MaxTemp = (TextView) findViewById(R.id.Lbl_Max_Tem);


                sourceText = sourceTextView.getText().toString();
                String getURL = "http://api.openweathermap.org/data/2.5/weather?q=" + sourceTextView.getText().toString() + "&appid=44db6a862fba0b067b1930da0d769e98";//The API service URL
                final String response1 = "";
                OkHttpClient client = new OkHttpClient();

                Request request = new Request.Builder()
                        .url(getURL)
                        .build();

                client.newCall(request).enqueue(new Callback() {

                    private Call call;
                    private Response response;

                    @Override
                    public void onFailure(Call call, IOException e) {
                        System.out.println(e.getMessage());
                    }


                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        this.call = call;
                        this.response = response;
                        final JSONObject jsonResult;
                        final String result = response.body().string();

                        try {
                            jsonResult = new JSONObject(result);
                            //parse to fetch city
                            final String cityname = jsonResult.getString("name");
                            //parsing climate test- IGNORE
                            // JSONArray weather = jsonResult.getJSONArray("weather");
                            // String climate = weather.getString(0).toString();
                            // Log.d("cool!!",climate);

                            //parsing for country
                            JSONObject nation = jsonResult.getJSONObject("sys");
                            final String country = nation.getString("country");

                            // parsing for getting generalized temperature
                            JSONObject main = jsonResult.getJSONObject("main");
                            final Double temp = main.getDouble("temp");


                            //parsing Humidity, Temperature Max, Temperature Min
                            JSONObject mai = jsonResult.getJSONObject("main");
                            final String humid = mai.getString("humidity");
                            final double min = mai.getDouble("temp_min");
                            final double max = mai.getDouble("temp_max");

                            Log.d("okHttp", jsonResult.toString());
                            Log.d("ok",cityname);
                            Log.d("ok",humid);
                            Log.d("ok",country);

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    //hideKeyboard(outputTextView);
                                    //outputTextView.setText(convertedText);}
                                    City.setText("City:" + cityname);
                                    City.setVisibility(View.VISIBLE);
                                    tempr.setText("Climate:" + temp.toString());
                                    tempr.setVisibility(View.VISIBLE);
                                    Count.setText("Country:" + country);
                                    Count.setVisibility(View.VISIBLE);
                                    Humidity.setText("Humidity" + humid);
                                    Humidity.setVisibility(View.VISIBLE);

                                    MinTemp.setText("Min Temp:" + min);
                                    MinTemp.setVisibility(View.VISIBLE);
                                    MaxTemp.setText("Max Temp:" + max);
                                    MaxTemp.setVisibility(View.VISIBLE);
                                }

                            });

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });


            }
}

