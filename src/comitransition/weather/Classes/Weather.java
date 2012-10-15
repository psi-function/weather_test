package comitransition.weather.Classes;

import java.security.PrivateKey;
import java.sql.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Hp
 * Date: 09.10.12
 * Time: 1:15
 * To change this template use File | Settings | File Templates.
 */
public class Weather {

    private long timestamp;
    private int maxTempC;
    private int maxTempF;
    private int minTempC;
    private int minTempF;
    private int avgTempC;
    private int avgTempF;
    private int pop;
    private int precipMM;
    private int humidity;
    private int pressure;
    private int skyCover;
    private int snowCM;
    private int dewPointC;
    private int dewPointF;
    private String windDir;
    private int windGustKPH;
    private int windGustMPH;
    private int windSpeedKPH;
    private int windSpeedMPH;
    private int windSpeedMaxKPH;
    private int windSpeedMinKPH;
    private int windSpeedMaxMPH;
    private int windSpeedMinMPH;
    private String description;
    private long sunrise;
    private long sunset;

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public int getMaxTempC() {
        return maxTempC;
    }

    public void setMaxTempC(int maxTempC) {
        this.maxTempC = maxTempC;
    }

    public int getMaxTempF() {
        return maxTempF;
    }

    public void setMaxTempF(int maxTempF) {
        this.maxTempF = maxTempF;
    }

    public int getMinTempC() {
        return minTempC;
    }

    public void setMinTempC(int minTempC) {
        this.minTempC = minTempC;
    }

    public int getMinTempF() {
        return minTempF;
    }

    public void setMinTempF(int minTempF) {
        this.minTempF = minTempF;
    }

    public int getAvgTempC() {
        return avgTempC;
    }

    public void setAvgTempC(int avgTempC) {
        this.avgTempC = avgTempC;
    }

    public int getAvgTempF() {
        return avgTempF;
    }

    public void setAvgTempF(int avgTempF) {
        this.avgTempF = avgTempF;
    }

    public int getPop() {
        return pop;
    }

    public void setPop(int pop) {
        this.pop = pop;
    }

    public int getPrecipMM() {
        return precipMM;
    }

    public void setPrecipMM(int precipMM) {
        this.precipMM = precipMM;
    }

    public int getPressure() {
        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public int getSkyCover() {
        return skyCover;
    }

    public void setSkyCover(int skyCover) {
        this.skyCover = skyCover;
    }

    public int getSnowCM() {
        return snowCM;
    }

    public void setSnowCM(int snowCM) {
        this.snowCM = snowCM;
    }

    public int getDewPointC() {
        return dewPointC;
    }

    public void setDewPointC(int dewPointC) {
        this.dewPointC = dewPointC;
    }

    public int getDewPointF() {
        return dewPointF;
    }

    public void setDewPointF(int dewPointF) {
        this.dewPointF = dewPointF;
    }

    public String getWindDir() {
        return windDir;
    }

    public void setWindDir(String windDir) {
        this.windDir = windDir;
    }

    public int getWindGustKPH() {
        return windGustKPH;
    }

    public void setWindGustKPH(int windGustKPH) {
        this.windGustKPH = windGustKPH;
    }

    public int getWindGustMPH() {
        return windGustMPH;
    }

    public void setWindGustMPH(int windGustMPH) {
        this.windGustMPH = windGustMPH;
    }

    public int getWindSpeedKPH() {
        return windSpeedKPH;
    }

    public void setWindSpeedKPH(int windSpeedKPH) {
        this.windSpeedKPH = windSpeedKPH;
    }

    public int getWindSpeedMPH() {
        return windSpeedMPH;
    }

    public void setWindSpeedMPH(int windSpeedMPH) {
        this.windSpeedMPH = windSpeedMPH;
    }

    public int getWindSpeedMaxKPH() {
        return windSpeedMaxKPH;
    }

    public void setWindSpeedMaxKPH(int windSpeedMaxKPH) {
        this.windSpeedMaxKPH = windSpeedMaxKPH;
    }

    public int getWindSpeedMinKPH() {
        return windSpeedMinKPH;
    }

    public void setWindSpeedMinKPH(int windSpeedMinKPH) {
        this.windSpeedMinKPH = windSpeedMinKPH;
    }

    public int getWindSpeedMaxMPH() {
        return windSpeedMaxMPH;
    }

    public void setWindSpeedMaxMPH(int windSpeedMaxMPH) {
        this.windSpeedMaxMPH = windSpeedMaxMPH;
    }

    public int getWindSpeedMinMPH() {
        return windSpeedMinMPH;
    }

    public void setWindSpeedMinMPH(int windSpeedMinMPH) {
        this.windSpeedMinMPH = windSpeedMinMPH;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
