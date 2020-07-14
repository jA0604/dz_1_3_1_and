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

public class LifeActivity extends AppCompatActivity {
    private final String TAG = "LifeActivity";
    private ArrayList<PersonLife> personLifeList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life);

        final EditText etWeight = findViewById(R.id.et_weight);
        final EditText etStep = findViewById(R.id.et_step);
        Button btnSave = findViewById(R.id.btn_save);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String weightStr = etWeight.getText().toString();
                String stepStr = etStep.getText().toString();

                // log нажатия кнопки пользователем
                Log.i(TAG, "нажатие кнопки SAVE");

                if (weightStr.isEmpty() || stepStr.isEmpty()) {
                    Toast.makeText(LifeActivity.this, getText(R.string.msg_empty_fill), Toast.LENGTH_LONG).show();
                    return;
                }

                double weight;
                int step;
                try {
                    weight = Double.parseDouble(weightStr);
                    step = Integer.parseInt(stepStr);
                } catch (NumberFormatException e) {
                    Toast.makeText(LifeActivity.this, getText(R.string.msg_non_number), Toast.LENGTH_LONG).show();
                    return;
                }

                PersonLife personLife = new PersonLife(weight, step);
                personLifeList.add(personLife);

                Toast.makeText(LifeActivity.this, personLife.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        Button btnHealth = findViewById(R.id.btn_health);
        btnHealth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LifeActivity.this, HealthActivity.class);
                startActivity(intent);

                // log нажатия кнопки пользователем
                Log.i(TAG, "нажатие кнопки перехода на страницу индивидуальных показателей");
            }
        });

        Button btnPerson = findViewById(R.id.btn_person);
        btnPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LifeActivity.this, MainActivity.class);
                startActivity(intent);

                // log нажатия кнопки пользователем
                Log.i(TAG, "нажатие кнопки перехода на страницу ввода персональных данных");
            }
        });

    }
}