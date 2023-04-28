package com.bukvityarmin.tipcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText AmountText,GuestText;
    private RadioGroup RadioGroup;
    private Button CalculateButton;
    private TextView FullAmountText,TipAmountText,AmountPerGuest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        AmountText = findViewById(R.id.AmountText);
        GuestText = findViewById(R.id.GuestText);
        RadioGroup = findViewById(R.id.RadioGroup);
        CalculateButton = findViewById(R.id.CalculateButton);
        FullAmountText = findViewById(R.id.FullAmountText);
        TipAmountText = findViewById(R.id.TipAmountText);
        AmountPerGuest = findViewById(R.id.AmountPerGuest);



        CalculateButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String AmountTextCheck = AmountText.getText().toString().trim();
                String GuestTextCheck = GuestText.getText().toString().trim();

                if (AmountTextCheck.isEmpty() || GuestTextCheck.isEmpty()){
                    Toast.makeText(MainActivity.this, "Adjon meg számot", Toast.LENGTH_SHORT).show();

                } else {

                    double amount = Double.parseDouble(AmountText.getText().toString());
                    double tipPercentage = getTipPercentage();
                    int GuestNo = Integer.parseInt(GuestText.getText().toString());

                    double tip = (amount * tipPercentage) / 100;
                    double fullAmount = amount + tip;
                    double GuestAmount = fullAmount / GuestNo;


                    FullAmountText.setText("A teljes összeg: " + Math.round(fullAmount));
                    TipAmountText.setText("A borravaló mértéke: " + Math.round(tip));
                    AmountPerGuest.setText("Fejenkénti összeg: " + Math.round(GuestAmount));

                }
            }
        });
    }

    private double getTipPercentage() {
        int selectedId = RadioGroup.getCheckedRadioButtonId();
        RadioButton TipAmount = findViewById(selectedId);

        switch (TipAmount.getId()) {
            case R.id.radioButton10:
                return 10.0;
            case R.id.radioButton15:
                return 15.0;
            case R.id.radioButton25:
                return 25.0;
            case R.id.radioButton50:
                return 50.0;
            default:
                return 0.0;
        }
    }
}