package ru.mirea.smirnov.thread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import ru.mirea.smirnov.thread.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Thread thread = new Thread(new Runnable() {
                    public void run() {

                        Log.d("thread_calculate", String.format("Запущен поток студентом группы %s номер по списку No %s ", "БСБО-02-20", "24"));
                        int dni = Integer.parseInt(String.valueOf(binding.editTextTextPersonName.getText()));
                        int para = Integer.parseInt(String.valueOf(binding.editTextTextPersonName2.getText()));

                                Log.d("Smirnov", String.format("среднее кол-во пар = %s", dni/para));

                        Log.d("thread_calculate", "Выполнен поток расчёта");
                    }
                });
                thread.start();
            }
        });
    }
}