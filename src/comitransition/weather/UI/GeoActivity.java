package comitransition.weather.UI;


import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.app.Activity;
import android.widget.TextView;
import comitransition.weather.Classes.Day;
import comitransition.weather.Classes.Weather;
import comitransition.weather.R;
import comitransition.weather.geolocation.GeoManager;
import org.json.JSONException;

import java.sql.Date;

public class GeoActivity extends Activity {

    private TextView wind;
    private TextView T;
    private TextView city;
    private TextView date;
    private TextView day;
    private Button buttonMore;
    private Button buttonAdvice;
    private Weather weatherNow;
    private GeoManager geoManager;
    private Day today;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.geoshow);
        T = (TextView) findViewById(R.id.T);
        wind = (TextView) findViewById(R.id.humidity);
        date = (TextView) findViewById(R.id.date);
        city = (TextView) findViewById(R.id.city);
        day = (TextView) findViewById(R.id.day);
        buttonAdvice = (Button) findViewById(R.id.buttonAdvice);
        buttonMore = (Button) findViewById(R.id.buttonMore);

        geoManager = new GeoManager(this);
        weatherNow = new Weather();
        today = new Day(this);
        today.updateDayForecast(geoManager.checkWeather());

        Date currentTime = new Date(System.currentTimeMillis());


              //   buttonAdvice.setText(today.getWeatherList().size());
      buttonAdvice.setText(today.getWeatherList().get(0).getMaxTempC()+" C");

       buttonMore.setText(new Date(today.getWeatherList().get(0).getTimestamp()).toString());
        try{
        wind.setText(" " + geoManager.checkWeather().getJSONObject(0).getJSONArray("periods").toString());
        }catch (JSONException jsone) {jsone.printStackTrace(System.err);}

    }


    public void switchActivity( View view){

        Intent intent=new Intent(view.getContext(),DayForecastActivity.class)  ;
        startActivity(intent);
    }

    //{ "data": { "current_condition": [ {"cloudcover": "61", "humidity": "71", "observation_time": "12:52 PM", "precipMM": "0.2", "pressure": "1012", "temp_C": "29", "temp_F": "85", "visibility": "10", "weatherCode": "353",  "weatherDesc": [ {"value": "Light rain shower" } ],  "weatherIconUrl": [ {"value": "http:\/\/www.worldweatheronline.com\/images\/wsymbols01_png_64\/wsymbol_0009_light_rain_showers.png" } ], "winddir16Point": "SW", "winddirDegree": "218", "windspeedKmph": "16", "windspeedMiles": "10" } ],  "request": [ {"query": "Lat 0.00 and Lon 0.00", "type": "LatLon" } ] }}



    protected void onResume() {
        super.onResume();
        //geoManager.requestLocationUpdates(bestProvider, 400, 1, this);
    }


}
