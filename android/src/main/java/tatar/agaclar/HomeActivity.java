package tatar.agaclar;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

/**
 *
 * Home Screen Activity
 */
public class HomeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Displays Home Screen
        setContentView(R.layout.home);
    }

}