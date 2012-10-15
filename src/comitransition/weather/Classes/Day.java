package comitransition.weather.Classes;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.Parcel;
import android.os.Parcelable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.Locale;


public class Day  implements Parcelable{
    private Date date;
    private Context context;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    private String city;
    private String country;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public ArrayList<Weather> getWeatherList() {
        return weatherList;
    }

    public void setWeatherList(ArrayList<Weather> weatherList) {
        this.weatherList = weatherList;
    }

    private ArrayList<Weather> weatherList;


    public Day(Context context) {
        this.context = context;
    }


    public Day updateDayForecast(JSONArray jsonArray)

    {


        try {
             weatherList=new ArrayList<Weather>()  ;
            detectCity( jsonArray.getJSONObject(0).getJSONObject("loc").getLong("lat"), jsonArray.getJSONObject(0).getJSONObject("loc").getLong("long")  );

            JSONArray wholeDayForecast =       jsonArray.getJSONObject(0).getJSONArray("periods");
            int capacity = wholeDayForecast.length();

            for ( int i = 0; i < capacity; i++ )
            {

                  Weather weather=new Weather();


                JSONObject hourForecast = wholeDayForecast.getJSONObject(i);

                weather.setTimestamp(hourForecast.getLong("timestamp")*1000);
                weather.setAvgTempC( hourForecast.getInt( "avgTempC" ) );
                weather.setAvgTempF( hourForecast.getInt( "avgTempF" ) );
                weather.setMaxTempC( hourForecast.getInt( "maxTempC" ) );
                weather.setMinTempC(hourForecast.getInt("minTempC"));
                weather.setMaxTempF(hourForecast.getInt("maxTempF"));
                weather.setMinTempF(hourForecast.getInt("minTempF"));
                weather.setPop( hourForecast.getInt( "pop" ) );
                weather.setPrecipMM( hourForecast.getInt( "precipMM" ) );
                weather.setPressure( hourForecast.getInt( "pressureMB" ) );
                weather.setHumidity( hourForecast.getInt( "humidity" ) );
                weather.setSkyCover( hourForecast.getInt( "sky" ) );
                weather.setSnowCM( hourForecast.getInt( "snowCM" ) );
                weather.setDewPointC( hourForecast.getInt( "dewpointC" ) );
                weather.setDewPointF(hourForecast.getInt("dewpointF"));
                weather.setWindDir( hourForecast.getString( "windDir" ) );
                weather.setWindGustKPH(hourForecast.getInt("windGustKPH"));
                weather.setWindGustMPH(hourForecast.getInt("windGustMPH"));
                weather.setWindSpeedKPH( hourForecast.getInt( "windSpeedKPH" ) );
                weather.setWindSpeedMPH(hourForecast.getInt("windSpeedMPH"));
                weather.setWindSpeedMaxKPH(hourForecast.getInt("windSpeedMaxKPH"));
                weather.setWindSpeedMaxMPH(hourForecast.getInt("windSpeedMaxMPH"));
                weather.setWindSpeedMinKPH(hourForecast.getInt("windSpeedMinKPH"));
                weather.setWindSpeedMinMPH(hourForecast.getInt("windSpeedMinMPH"));
                weather.setDescription( hourForecast.getString( "weather" ) );

                weatherList.add( weather );
            }


        } catch (JSONException jsone) {
            jsone.printStackTrace();
        }


        return this;


    }


    public void detectCity(long lat,long lon) {
        Geocoder gc = new Geocoder(context, Locale.getDefault());
        try {
            List<Address> addresses;
            addresses = gc.getFromLocation(lat, lon, 1);

            if (addresses.size() > 0) {
               this.setCity(addresses.get(0).getLocality());
                this.setCountry(addresses.get(0).getCountryName());
            }

        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }


    @Override
    public int describeContents() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
