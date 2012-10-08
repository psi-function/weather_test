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
    private TextView latitudeField ;
    private TextView longitudeField ;
     private Button showCityButton;
    private TextView jsonField;
    double lng;
    double lat;
   private  Weather weatherNow;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.geoshow);
        Criteria criteria=new Criteria();
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        bestProvider=  locationManager.getBestProvider(criteria,false)    ;
        currentLoc=locationManager.getLastKnownLocation(bestProvider)       ;



        jsonField=(TextView) findViewById( R.id.jsonField)    ;
        longitudeField=(TextView)findViewById(R.id.longitudeField)   ;
        latitudeField=(TextView) findViewById(R.id.latitudeField)     ;
        showCityButton=(Button) findViewById(R.id.buttonShowCity) ;
        weatherNow =new Weather();

        if (currentLoc != null) {
            System.out.println("Provider " + bestProvider + " has been selected.");
            onLocationChanged(currentLoc);
        } else {
            latitudeField.setText("Location not available");
            longitudeField.setText("Location not available");
        }
    }


    protected void onResume() {
        super.onResume();
        locationManager.requestLocationUpdates(bestProvider, 400, 1, this);
    }

    public JSONObject obtainJSON(double lon,double lat)
    {
         try{
        HttpClient client = new DefaultHttpClient();
        HttpGet httpGet=new HttpGet("http://www.myweather2.com/developer/forecast.ashx?uac=Bk4dtgXOB0&output=json&query="+lat+","+lon);
        HttpResponse response = client.execute(httpGet) ;
             BufferedReader streamReader=new BufferedReader(new InputStreamReader(response.getEntity().getContent()))  ;
             JSONObject jsonObject=new JSONObject(streamReader.readLine());
             JSONObject weather=jsonObject.getJSONObject("weather");
             jsonField.setText(weather.toString());
             return weather;
         }
         catch  (IOException ioe){ioe.printStackTrace();}
         catch  (JSONException jsonException){ jsonException.printStackTrace();}
           finally{return null;}

    }
       public void getCity(View view)
       {

           obtainJSON(lng,lat);

       }



    @Override
    public void onLocationChanged(Location location) {
        lat = (double) (location.getLatitude());
        lng = (double) (location.getLongitude());
        latitudeField.setText(String.valueOf(lat));
        longitudeField.setText(String.valueOf(lng));
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
