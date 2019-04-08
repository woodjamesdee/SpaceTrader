package edu.jumpstreet.spacetrader.view;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.WindowManager;

import edu.jumpstreet.spacetrader.R;

/**
 * manages garage purchasing activity
 */
public class GarageSpaceshipBuyActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DisplayMetrics dM = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dM);
        getWindow().setLayout((int) (dM.widthPixels * GarageFuelActivity.WIDTH_MODIFIER), (int) (dM.heightPixels * GarageFuelActivity.HEIGHT_MODIFIER));
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x = 0;
        params.y = GarageFuelActivity.Y_LOCATION;
        setContentView(R.layout.popup_window_garage_spaceship_buy);
    }
}
