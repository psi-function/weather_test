package comitransition.weather;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.app.Activity;
import android.content.Context;
import android.widget.TextView;
import comitransition.weather.Classes.Weather;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.PrivateKey;
import java.security.Provider;

import static android.content.Context.LOCATION_SERVICE;

/**
 * Created with IntelliJ IDEA.
 * User: Hp
 * Date: 07.10.12
 * Time: 13:26
 * To change this template use File | Settings | File Templates.
 */
public class GeoActivity extends Activity implements LocationListener {
    private LocationManager locationManager;
    private String bestProvider;
    private Location currentLoc;
    private TextView wind;
    private TextView T;
    double lng;
    double lat;
    private Weather weatherNow;
    private HttpClient client;
    private HttpGet httpGet;
    private HttpResponse response;
    private Criteria criteria;
    private JSONObject weatherJSON;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.geoshow);
        T = (TextView) findViewById(R.id.T);
        wind = (TextView) findViewById(R.id.wind);

        weatherNow = new Weather();

        updateWeather();


     weatherJSON = obtainJSONByGeo(lng, lat);

   try {
            weatherNow = updateWeather(weatherJSON);
        } catch (JSONException jsone) {
            jsone.printStackTrace();
        }


        T.setText(weatherNow.getTemperature());
        wind.setText(String.valueOf(weatherNow.getWindSpeed()));
    }

    public Weather updateWeather(JSONObject jsonObject) throws JSONException {
       JSONObject data = jsonObject.getJSONObject("data");
        JSONArray current_condition = data.getJSONArray("current_condition");

        JSONObject cur = (JSONObject)current_condition.get(0);
        String temperature_C = cur.get("temp_C").toString();
        String wind_speed = cur.get("windspeedKmph").toString();

        Weather weather = new Weather();
        weather.setTemperature(temperature_C);
        weather.setWindSpeed(Integer.parseInt(wind_speed));

        return weather;

    }


    //{ "data": { "current_condition": [ {"cloudcover": "61", "humidity": "71", "observation_time": "12:52 PM", "precipMM": "0.2", "pressure": "1012", "temp_C": "29", "temp_F": "85", "visibility": "10", "weatherCode": "353",  "weatherDesc": [ {"value": "Light rain shower" } ],  "weatherIconUrl": [ {"value": "http:\/\/www.worldweatheronline.com\/images\/wsymbols01_png_64\/wsymbol_0009_light_rain_showers.png" } ], "winddir16Point": "SW", "winddirDegree": "218", "windspeedKmph": "16", "windspeedMiles": "10" } ],  "request": [ {"query": "Lat 0.00 and Lon 0.00", "type": "LatLon" } ] }}




    protected void onResume() {
        super.onResume();
        locationManager.requestLocationUpdates(bestProvider, 400, 1, this);
    }
    public void updateWeather()
    {

        criteria = new Criteria();
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        bestProvider = locationManager.getBestProvider(criteria, false);
        currentLoc = locationManager.getLastKnownLocation(bestProvider);
        bestProvider = locationManager.getBestProvider(criteria, false);
        currentLoc = locationManager.getLastKnownLocation(bestProvider);

        if (currentLoc != null) {
            onLocationChanged(currentLoc);
        }

    }
    public JSONObject obtainJSONByGeo(double longitude, double latitude) {
        try {

            client = new DefaultHttpClient();
            httpGet = new HttpGet("http://free.worldweatheronline.com/feed/weather.ashx?q="
                    + latitude
                    + ","
                    + longitude
                    + "&format=json&num_of_days=2&key=6fa7baabc0230029120810");
            response = client.execute(httpGet);
            JSONObject jsonObject = new JSONObject(new BufferedReader(new InputStreamReader(response.getEntity().getContent())).readLine());


            return jsonObject;
        } catch (IOException ioe) {
            ioe.printStackTrace();
            return null;
        } catch (JSONException jsonException) {
            jsonException.printStackTrace();
            return null;
        }

    }

    public JSONObject obtainJSONByCity(String city) {
        try {

            client = new DefaultHttpClient();
            httpGet = new HttpGet("http://free.worldweatheronline.com/feed/weather.ashx?q="
                    + city +
                    "&format=json&num_of_days=2&key=6fa7baabc0230029120810");
            response = client.execute(httpGet);
            BufferedReader streamReader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            JSONObject jsonObject = new JSONObject(streamReader.readLine());
            return jsonObject;
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (JSONException jsonException) {
            jsonException.printStackTrace();
        } finally {
            return null;
        }

    }


    public void callAPI(View view) {

    }


    @Override
    public void onLocationChanged(Location location) {
        lat = location.getLatitude();
        lng = location.getLongitude();


    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void onProviderEnabled(String s) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void onProviderDisabled(String s) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
