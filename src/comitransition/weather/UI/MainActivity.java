package comitransition.weather.UI;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import comitransition.weather.R;
import comitransition.weather.geolocation.GeoManager;

public class MainActivity extends Activity {
    private Button locationShowActivityButton;

    /**
     * Called when the activity is first created.
     */
    public void switchActivity( View view){
        startService(new Intent(this, GeoManager.class));
        Intent intent=new Intent(view.getContext(),GeoActivity.class)  ;
                startActivity(intent);
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        locationShowActivityButton = (Button) findViewById(R.id.button) ;
    }
}

