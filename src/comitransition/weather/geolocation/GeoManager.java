package comitransition.weather.geolocation;

import android.content.Context;
import android.location.*;
import android.os.Bundle;
import comitransition.weather.Classes.Weather;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Locale;

public class GeoManager implements LocationListener {


    public GeoManager(Context context) {

        this.context = context;

    }






    private Context context;
    private LocationManager locationManager;

    public JSONArray checkWeather() {
        Weather weather = new Weather();
        Location location = updateLocation();
        JSONArray jsonArray=new JSONArray();
        try {

            JSONObject jsonObject;
            jsonObject = obtainJSONByGeo(location);

            if (!Boolean.parseBoolean(jsonObject.getString("success"))) {
                String errorMessage = jsonObject.getString("error");
            } else {

                jsonArray=jsonObject.getJSONArray("response");
            }
        } catch (JSONException jsone) {
            jsone.printStackTrace(System.err);
        } catch (NullPointerException npe) {
            npe.printStackTrace(System.err);
        }
        finally {
            return jsonArray;
        }

    }


    public JSONObject obtainJSONByGeo(Location location) {
        try {

            String responseJSON;
            HttpClient httpClient;
            HttpGet httpGet;
            InputStreamReader inputStreamReader;
            HttpResponse httpResponse;

            httpClient = new DefaultHttpClient();

            httpGet = new HttpGet("http://api.aerisapi.com/forecasts/closest?client_id=MPDFulbfdKgcvw5NCdYjN&client_secret=b6cPTm7bgMnP8Q9YO7lIjYkt5zdLiX3x1VydrzFm" +
                    "&filter=1hr" +
                    "&limit=24" +
                    "&p="
                    + location.getLatitude()
                    + ","
                    + location.getLongitude()
            );

            httpResponse = httpClient.execute(httpGet);
            inputStreamReader = new InputStreamReader(httpResponse.getEntity().getContent());
            responseJSON = new BufferedReader(inputStreamReader).readLine();

            return new JSONObject(responseJSON);

        } catch (IOException ioe) {
            ioe.printStackTrace();
            return null;
        } catch (JSONException jsonException) {
            jsonException.printStackTrace();
            return null;
        }
    }


    public Location updateLocation()            //TODO: разобраться с контекстом
    {
        Criteria criteria = new Criteria();
        locationManager = (LocationManager) this.context.getSystemService(Context.LOCATION_SERVICE);
        String bestProvider = locationManager.getBestProvider(criteria, false);
        locationManager.requestLocationUpdates(bestProvider, 400, 1, this);
        Location location = locationManager.getLastKnownLocation(bestProvider);

        if (location != null) {
            return location;
        } else {
            return null;
        }

    }

    public void detectCity() {


    }


    @Override
    public void onLocationChanged(Location location) {

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
