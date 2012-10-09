package comitransition.weather;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
    private Button locationShowActivityButton;

    /**
     * Called when the activity is first created.
     */
    public void switchActivity( View view){

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

