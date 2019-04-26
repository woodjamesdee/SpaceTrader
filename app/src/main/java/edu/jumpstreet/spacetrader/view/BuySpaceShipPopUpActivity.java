package edu.jumpstreet.spacetrader.view;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import edu.jumpstreet.spacetrader.R;

public class BuySpaceShipPopUpActivity extends Activity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DisplayMetrics dM = new DisplayMetrics();
        WindowManager windowManager = getWindowManager();
        Display defaultDisplay = windowManager.getDefaultDisplay();
        defaultDisplay.getMetrics(dM);
        Window window = getWindow();
        window.setLayout((int) (dM.widthPixels * GarageFuelActivity.WIDTH_MODIFIER),
                (int) (dM.heightPixels * GarageFuelActivity.HEIGHT_MODIFIER));
        WindowManager.LayoutParams params = window.getAttributes();
        params.gravity = Gravity.CENTER;
        params.x = 0;
        params.y = GarageFuelActivity.Y_LOCATION;
        setContentView(R.layout.popup_window_confirm_spaceship_buy);
        this.setFinishOnTouchOutside(true);
    }





    @Override
    public void onClick(View view) {

    }
}
