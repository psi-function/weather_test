package comitransition.weather.UI;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.*;
import comitransition.weather.Classes.Day;
import comitransition.weather.Classes.Weather;
import comitransition.weather.R;
import comitransition.weather.geolocation.GeoManager;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Hp
 * Date: 15.10.12
 * Time: 0:58
 * To change this template use File | Settings | File Templates.
 */
public class DayForecastActivity extends Activity {
    String text;

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


        ScrollView scrollView = new ScrollView(this); //root scroll
        ViewGroup.LayoutParams scrollViewParam = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        LinearLayout linearLayout = new LinearLayout(this);  //root linear
        linearLayout.setOrientation(LinearLayout.VERTICAL);


        GeoManager geoManager = new GeoManager(this);
        Weather weatherNow = new Weather();
        Day today = new Day(this);
        today.updateDayForecast(geoManager.checkWeather());

        LinearLayout.LayoutParams linearParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);


        for(Weather hourForecast:today.getWeatherList())
        {
            LinearLayout innerLinears = new LinearLayout(this);
            TextView timandate = new TextView(this);
            TextView temperatureC= new TextView(this);

            timandate.setGravity(1);
            temperatureC.setGravity(1);
            timandate.setTextSize(24);
            temperatureC.setTextSize(24);

            Date date= new Date(hourForecast.getTimestamp());
            timandate.setText(date.toLocaleString());
            timandate.setLayoutParams(linearParams);

            temperatureC.setText("  -    "+hourForecast.getAvgTempC()+" C");
            temperatureC.setLayoutParams(linearParams);


            innerLinears.addView(timandate);
            innerLinears.addView(temperatureC);
            innerLinears.setClickable(true);

            linearLayout.addView(innerLinears);

        }

        scrollView.addView(linearLayout, linearParams);



        setContentView(scrollView, scrollViewParam);


    }



    public void onResume() {
        super.onResume();

    }


}
