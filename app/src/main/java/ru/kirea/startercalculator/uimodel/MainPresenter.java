package ru.kirea.startercalculator.uimodel;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.widget.Toast;

import ru.kirea.startercalculator.R;

public class MainPresenter {
    private final String KEY_FIRST_VALE = "firstValue";
    private final String KEY_SECOND_VALE = "secondValue";
    private final String KEY_OPERATION = "operation";
    private final String CALCULATOR_URI = "calculator://run";

    private Context context;
    private MainView mainView;

    public MainPresenter(Context context, MainView mainView) {
        this.context = context;
        this.mainView = mainView;
    }

    //запустить приложение калькулятора
    public void runCalculator(String firstValue, String secondValue, int selectedOperationPosition) {
        String[] operations = context.getResources().getStringArray(R.array.operations);

        Uri uri = Uri.parse(CALCULATOR_URI);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        //передаем введенные параметры
        if (firstValue != null && !firstValue.isEmpty()) {
            intent.putExtra(KEY_FIRST_VALE, Double.valueOf(firstValue));
        }
        if (secondValue != null && !secondValue.isEmpty()) {
            intent.putExtra(KEY_SECOND_VALE, Double.valueOf(secondValue));
        }
        intent.putExtra(KEY_OPERATION, operations[selectedOperationPosition].charAt(0));

        //проверяем, есть ли нужный калькулятор на устройстве
        ActivityInfo activityInfo = intent.resolveActivityInfo(context.getPackageManager(), intent.getFlags());
        if (activityInfo != null ) {
            mainView.runActivity(intent);
        } else {
            Toast.makeText(context, context.getString(R.string.calculator_no_found), Toast.LENGTH_LONG).show();
        }
    }
}
