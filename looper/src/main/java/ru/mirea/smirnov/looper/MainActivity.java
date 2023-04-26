package ru.mirea.smirnov.looper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;

import ru.mirea.smirnov.looper.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Handler mainThreadHandler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                Log.d("result", msg.getData().getString("result"));
            }
        };

        NotMyLooper notMyLooper = new NotMyLooper(mainThreadHandler);
        notMyLooper.start();

        binding.number.setText("Мой номер по списку No25");
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Message msg = Message.obtain();
                Bundle bundle = new Bundle();

                bundle.putString("school", binding.editTextTextPersonName.getText().toString());
                bundle.putString("witch", binding.editTextTextPersonName2.getText().toString());

                msg.setData(bundle);
                notMyLooper.mHandler.sendMessage(msg);
            }
        });
    }
}