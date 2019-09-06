package com.example.livesmart.BroadCastReceivers;

import android.app.AlarmManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.util.Log;

public class AlarmBroadCast extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        AudioManager am = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        if( AudioManager.RINGER_MODE_SILENT == am.getRingerMode() ){
            am.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
            Log.d("RINGMODE", String.valueOf(am.getRingerMode()));
        }else if( AudioManager.RINGER_MODE_NORMAL == am.getRingerMode() ){
            am.setRingerMode(AudioManager.RINGER_MODE_SILENT);
            Log.d("RINGMODE", String.valueOf(am.getRingerMode()));

        }
    }

}
