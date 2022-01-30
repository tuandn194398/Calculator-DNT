package com.example.calculator.ui.bill;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.calculator.R;

import java.text.NumberFormat;

public class BillFragment extends Fragment implements TextView.OnEditorActionListener {
    // define variables for the widgets
    EditText billAmountEditText;
    TextView percentTextView;
    Button percentUpButton;
    Button percentDownButton;
    TextView tipTextView;
    TextView totalTextView;

    // define the SharedPreferences object
    SharedPreferences savedValues;

    // define instance variables that should be saved
    String billAmountString = "";
    float tipPercent = .15f;

    final String TAG = "TipCalculatorActivity";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_bill, container, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        billAmountEditText = view.findViewById(R.id.billAmountEditText);
        percentTextView = view.findViewById(R.id.percentTextView);
        percentUpButton = view.findViewById(R.id.btnPercentUp);
        percentDownButton = view.findViewById(R.id.btnPercentDown);
        tipTextView = view.findViewById(R.id.textviewTotal);
        totalTextView = view.findViewById(R.id.textviewTip);

        // set the listeners
        billAmountEditText.setOnEditorActionListener(this);

        // get SharedPreferences object
        savedValues = getContext().getSharedPreferences("SavedValues", Context.MODE_PRIVATE);

        percentDownButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tipPercent = tipPercent - .01f;
                calculateAndDisplay();
            }
        });

        percentUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tipPercent = tipPercent + .01f;
                calculateAndDisplay();
            }
        });
    }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.fragment_bill);
//
//        // get references to the widgets
//
//    }

    @Override
    public void onResume() {
        super.onResume();

        // get the instance variables
        billAmountString = savedValues.getString("billAmountString", "");
        tipPercent = savedValues.getFloat("tipPercent", 0.15f);

        // set the bill amount on its widget
        billAmountEditText.setText(billAmountString);

        // calculate and display
        calculateAndDisplay();
    }

    public void calculateAndDisplay() {

        // get the bill amount
        billAmountString = billAmountEditText.getText().toString();
        float billAmount;
        try {
            billAmount = Float.parseFloat(billAmountString);
        }
        catch (NumberFormatException e) {
            billAmount = 0;
        }

        // calculate tip and total
        float tipAmount = billAmount * tipPercent;
        float totalAmount = billAmount + tipAmount;

        // display the other results with formatting
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        tipTextView.setText(currency.format(tipAmount));
        totalTextView.setText(currency.format(totalAmount));

        NumberFormat percent = NumberFormat.getPercentInstance();
        percentTextView.setText(percent.format(tipPercent));
    }

    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_DONE ||
                actionId == EditorInfo.IME_ACTION_UNSPECIFIED) {
            calculateAndDisplay();
        }
        return false;
    }
}
