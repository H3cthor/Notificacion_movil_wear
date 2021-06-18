package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button boton;
    NotificationCompat.Builder notificacion;
    private static final int idUnico = 454542;
    String CHANNEL_ID;

    private NotificationManager notificationManager;
    private Intent intent;
    private PendingIntent pendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boton = (Button) findViewById(R.id.boton);

        CHANNEL_ID = getApplicationContext().getString(R.string.app_name);

        notificacion = new NotificationCompat.Builder(this, CHANNEL_ID);
        notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        intent = new Intent(this, MainActivity.class);

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    int importance = NotificationManager.IMPORTANCE_HIGH;
                    String name = "Notificación";

                    NotificationChannel notificationChannel =
                            new NotificationChannel(CHANNEL_ID, name, importance);

                    notificationChannel.enableVibration(true);

                    notificationManager.createNotificationChannel(notificationChannel);

                    pendingIntent =
                            PendingIntent.getActivity(MainActivity.this,
                                    0,
                                    intent,
                                    PendingIntent.FLAG_UPDATE_CURRENT);

                    notificacion.setSmallIcon(R.mipmap.ic_launcher);
                    notificacion.setTicker("Nueva notificación");
                    notificacion.setWhen(System.currentTimeMillis());
                    notificacion.setContentTitle("Título");
                    notificacion.setContentText("Notificación de prueba");
                    notificacion.setPriority(NotificationCompat.PRIORITY_DEFAULT);
                    notificacion.setAutoCancel(true);
                    notificacion.setVibrate(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
                    notificacion.setContentIntent(pendingIntent);

                    notificationManager.notify(idUnico, notificacion.build());
                }
            }
        });
    }
}