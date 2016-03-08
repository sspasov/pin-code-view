package com.mostcho.pincodeview.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.mostcho.pincodeview.R;
import com.mostcho.pincodeview.ui.views.PinCodeView;

public class MainActivity extends AppCompatActivity implements PinCodeView.IPinCodeViewListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private PinCodeView pinCodeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * find view and add the completion listener
         * */
        pinCodeView = (PinCodeView) findViewById(R.id.pinView);
        pinCodeView.setCompletionListener(this);

        /**
         * set PinCodeMode to SET_NEW_PINCODE in order to create the new pin code,
         * your responsibility is how you handle the new pin code after entering it
         * */
        pinCodeView.setPinCodeMode(PinCodeView.PinCodeMode.SET_NEW_PINCODE);
    }

    public void onButtonCLick(View view) {
        pinCodeView.resetPinCodeBoxes();
        pinCodeView.resetInfoMessage();
        pinCodeView.setPinCodeMode(PinCodeView.PinCodeMode.SET_NEW_PINCODE);
        Toast.makeText(MainActivity.this, "PinCodeView is now in SET_NEW_PINCODE mode.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNewPinCode(String pinCode) {
        /**
         * once the new pin code is entered you must handle it here (probably save it) after that
         * you can go into the other mode VERIFY_PINCODE. If you don't pass pin code to verify with
         * it will use the default "0000".
         * */
        pinCodeView.setPinCodeMode(PinCodeView.PinCodeMode.VERIFY_PINCODE);
        pinCodeView.setWrongEnteredPinCodesCount(10);
        pinCodeView.setDefaultPinCode("1234");
        pinCodeView.resetPinCodeBoxes();
        pinCodeView.resetInfoMessage();
        Toast.makeText(MainActivity.this, "New pin code is " + pinCode, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCorrectPinCode(boolean isPinCodeCorrect) {
        if (isPinCodeCorrect) {
            /**
             * entered pin code is correct. DO something here.
             * */
        } else {
            /**
             * entered pin code is INCORRECT. DO something here.
             * */
        }
    }
}
