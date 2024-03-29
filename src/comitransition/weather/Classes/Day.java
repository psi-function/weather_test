package comitransition.weather.Classes;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import comitransition.weather.Classes.Weather;

public class Day implements Parcelable {

    private Context context;
    private ArrayList<Weather> weatherList;
    private String city;
    private String country;
    JSONObject location;
    public Day updateDayForecast(JSONArray jsonArray)

    {

        try {

            weatherList = new ArrayList<>();
            Log.e("weatherlist creating = "," 4o");
            Geocoder gc = new Geocoder(context, Locale.getDefault());
            Log.e("geocoder creating = "," 4o");
            try {

                location = jsonArray.getJSONObject(0).getJSONObject("loc");

                List<Address> addresses;
                Log.e("trying to get list of addresses = ",location.toString());
                addresses = gc.getFromLocation(location.getLong("lat"), location.getLong("long"), 1);
                Log.e("list of addresses received and = ",addresses.toString());
                if (addresses.size() > 0) {

                    city = addresses.get(0).getLocality();
                    country = addresses.get(0).getCountryName();

                    Log.e("Country and city = ", city+" "+country);
                }
                else
                {

                    Log.e("returned empty addresses"," shit");
                }

            } catch (IOException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }


            JSONArray wholeDayForecast = jsonArray.getJSONObject(0).getJSONArray("periods");


            for (int i = 0; i < wholeDayForecast.length(); i++) {

                Weather weather = new Weather();
                JSONObject hourForecast = wholeDayForecast.getJSONObject(i);

                Log.e("starting setting weatherList from JSON: = ", hourForecast.toString());
                weather.setCountry(country);
                weather.setCity(city);
                weather.setTimestamp(hourForecast.getLong("timestamp") * 1000);
                weather.setAvgTempC(hourForecast.getInt("avgTempC"));
                weather.setAvgTempF(hourForecast.getInt("avgTempF"));
                weather.setMaxTempC(hourForecast.getInt("maxTempC"));
                weather.setMinTempC(hourForecast.getInt("minTempC"));
                weather.setMaxTempF(hourForecast.getInt("maxTempF"));
                weather.setMinTempF(hourForecast.getInt("minTempF"));
                weather.setPop(hourForecast.getInt("pop"));
                weather.setPrecipMM(hourForecast.getInt("precipMM"));
                weather.setPressure(hourForecast.getInt("pressureMB"));
                weather.setHumidity(hourForecast.getInt("humidity"));
                weather.setSkyCover(hourForecast.getInt("sky"));
                weather.setSnowCM(hourForecast.getInt("snowCM"));
                weather.setDewPointC(hourForecast.getInt("dewpointC"));
                weather.setDewPointF(hourForecast.getInt("dewpointF"));
                weather.setWindDir(hourForecast.getString("windDir"));
                weather.setWindGustKPH(hourForecast.getInt("windGustKPH"));
                weather.setWindGustMPH(hourForecast.getInt("windGustMPH"));
                weather.setWindSpeedKPH(hourForecast.getInt("windSpeedKPH"));
                weather.setWindSpeedMPH(hourForecast.getInt("windSpeedMPH"));
                weather.setWindSpeedMaxKPH(hourForecast.getInt("windSpeedMaxKPH"));
                weather.setWindSpeedMaxMPH(hourForecast.getInt("windSpeedMaxMPH"));
                weather.setWindSpeedMinKPH(hourForecast.getInt("windSpeedMinKPH"));
                weather.setWindSpeedMinMPH(hourForecast.getInt("windSpeedMinMPH"));
                weather.setDescription(hourForecast.getString("weather"));

                weatherList.add(weather);
            }


        } catch (JSONException jsone) {
            jsone.printStackTrace();
        }


        return this;


    }


    public Day(Context context) {
        this.context = context;
    }

    public ArrayList<Weather> getWeatherList() {
        return weatherList;
    }

    public void setWeatherList(ArrayList<Weather> weatherList) {
        this.weatherList = weatherList;
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
