package com.example.myapplication;

import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.myapplication.databinding.ActivityMainBinding;

public class MainActivity extends Activity {

    private Button wBoton;
    private ActivityMainBinding binding;
    private Intent intent;
    private PendingIntent pendingIntent;

    private NotificationCompat.Builder notificacion;
    private NotificationManagerCompat nm;
    private NotificationCompat.WearableExtender wearableExtender;

    String idChannel = "Mi_Canal";
    int idNotificacion = 001;

    private NotificationCompat.BigTextStyle bigTextStyle;

    String longText = "Without BigTextStyle, only a single line of text would be visible. " +
            "Any additional text would not appear directly on the notification. " +
            "The entire first line would not even be on the notification if it were too long! " +
            "Text that doesn't fit in a standard notification becomes ellipsized. " +
            "That is, the characters that don't fit are removed and replaced by ellipsis.";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        wBoton = (Button) findViewById(R.id.wBoton);

        intent = new Intent(MainActivity.this, MainActivity.class);

        nm = NotificationManagerCompat.from(MainActivity.this);

        wearableExtender = new NotificationCompat.WearableExtender();

        bigTextStyle = new NotificationCompat.BigTextStyle().bigText(longText);

        wBoton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int importance = NotificationManager.IMPORTANCE_HIGH;
                String name = "Notificación";

                NotificationChannel notificationChannel =
                        new NotificationChannel(idChannel, name, importance);

                nm.createNotificationChannel(notificationChannel);

                pendingIntent = PendingIntent.getActivity(MainActivity.this, 0, intent, 0);

                notificacion = new NotificationCompat.Builder(MainActivity.this, idChannel)
                                        .setSmallIcon(R.mipmap.ic_launcher)
                                        .setContentTitle("Notificacion wear")
                                        .setContentText(longText)
                                        .setContentIntent(pendingIntent)
                                        .extend(wearableExtender)
                                        .setVibrate(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400})
                                        .setStyle(bigTextStyle);

                nm.notify(idNotificacion, notificacion.build());
            }
        });
    }
}