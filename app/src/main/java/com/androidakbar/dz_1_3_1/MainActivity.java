package com.androidakbar.dz_1_3_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private final String TAG = "MainActivity";
    private ArrayList <Person> personList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText etName = findViewById(R.id.et_name);
        final EditText etAge = findViewById(R.id.et_age);
        Button btnSave = findViewById(R.id.btn_save);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = etName.getText().toString();
                String ageStr = etAge.getText().toString();

                // log нажатия кнопки пользователем
                Log.i(TAG, "нажатие кнопки SAVE");

                if (name.isEmpty() || ageStr.isEmpty()) {
                    Toast.makeText(MainActivity.this, getText(R.string.msg_empty_fill), Toast.LENGTH_LONG).show();
                    return;
                }

                int age;
                try {
                    age = Integer.parseInt(ageStr);
                } catch (NumberFormatException e) {
                    etAge.getText().clear();
                    Toast.makeText(MainActivity.this, getText(R.string.msg_non_number), Toast.LENGTH_LONG).show();
                    return;
                }

                Person person = new Person(name, age);
                personList.add(person);

                Toast.makeText(MainActivity.this, person.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        Button btnHealth = findViewById(R.id.btn_health);
        btnHealth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, HealthActivity.class);
                startActivity(intent);

                // log нажатия кнопки пользователем
                Log.i(TAG, "нажатие кнопки перехода на страницу индивидуальных показателей");
            }
        });

        Button btnLife = findViewById(R.id.btn_life);
        btnLife.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LifeActivity.class);
                startActivity(intent);
                // log нажатия кнопки пользователем
                Log.i(TAG, "нажатие кнопки перехода на страницу жизненных показателей");
            }
        });


    }
}