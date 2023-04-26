package ru.mirea.smirnov.looper;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import java.util.concurrent.TimeUnit;

public class NotMyLooper extends Thread{
    public Handler mHandler;
    private Handler mainHandler;
    public NotMyLooper(Handler mainThreadHandler) {
        mainHandler = mainThreadHandler;
    }
    public void run() {
        Log.d("NotMyLooper", "run");
        Looper.prepare();
        mHandler = new Handler(Looper.myLooper()) {
            public void handleMessage(Message msg) {

                String school = msg.getData().getString("school");
                Integer witch = Integer.parseInt(msg.getData().getString("witch"));

                Message message = new Message();
                Bundle bundle = new Bundle();
                bundle.putString("result", String.format("ваша школа: %s; ваша ведьма: %s", school, witch));
                message.setData(bundle);

                try {
                    TimeUnit.SECONDS.sleep(witch);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                mainHandler.sendMessage(message);
            }
        };
        Looper.loop();
    }
}