package ru.kirea.startercalculator.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;

import com.google.android.material.textfield.TextInputEditText;

import androidx.appcompat.app.AppCompatActivity;
import ru.kirea.startercalculator.R;
import ru.kirea.startercalculator.uimodel.MainPresenter;
import ru.kirea.startercalculator.uimodel.MainView;

public class MainActivity extends AppCompatActivity implements MainView {

    private MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainPresenter = new MainPresenter(this, this);
        initButton();
    }

    //обработчик кнопок
    private void initButton() {
        findViewById(R.id.button_run_calculator_id).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextInputEditText editFirstValue = findViewById(R.id.edit_first_value_id);
                TextInputEditText editSecondValue = findViewById(R.id.edit_second_value_id);
                Spinner operation = findViewById(R.id.operation_id);

                //получаем введенные числа
                String firstValue = editFirstValue.getText() == null ? null : editFirstValue.getText().toString();
                String secondValue = editSecondValue.getText() == null ? null : editSecondValue.getText().toString();

                mainPresenter.runCalculator(firstValue, secondValue, operation.getSelectedItemPosition());
            }
        });
    }

    @Override
    public void runActivity(Intent intent) {
        startActivity(intent);
    }
}
