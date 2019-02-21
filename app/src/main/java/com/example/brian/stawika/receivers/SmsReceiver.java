package com.example.brian.stawika.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

public class SmsReceiver extends BroadcastReceiver {
    private static final String TAG = SmsReceiver.class.getSimpleName();
    private static SmsListener smsListener;

    @Override
    public void onReceive(Context context, Intent intent) {
        final Bundle bundle = intent.getExtras();
        try {

            if (bundle != null) {
                Object[] pdusObj = (Object[]) bundle.get("pdus");

                for (Object aPdusObj : pdusObj) {

                    SmsMessage currentMessage;
                    String senderAddress;
                    String message;

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                        String format = bundle.getString("format");
                        currentMessage = SmsMessage.createFromPdu((byte[]) aPdusObj, format);
                        senderAddress = currentMessage.getDisplayOriginatingAddress();
                        message = currentMessage.getDisplayMessageBody();

                    } else {

                        currentMessage = SmsMessage.createFromPdu((byte[]) aPdusObj);
                        senderAddress = currentMessage.getDisplayOriginatingAddress();
                        message = currentMessage.getDisplayMessageBody();
                    }

                    Log.e(TAG, message);

                    if (!message.contains(" STAWIKA ")) return;

                    // verification code from sms

                    String verificationCode = message.substring(0, 4);

                    Log.e(TAG, verificationCode);


                    Intent i = new Intent("broadCastName");
                    i.putExtra("code", verificationCode);
                    Log.e(TAG, "Sending broadcast.");
                    context.sendBroadcast(i);
                }

            }


        } catch (Exception e) {
            Log.e(TAG, "Exception: " + e.getMessage());
        }

    }


}
