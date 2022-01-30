package com.example.calculator.ui.standard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.calculator.R;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;

import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

public class StandardFragment extends Fragment{
    private Boolean isFutureOperationButtonClicked = false;
    private Boolean isInstantOperationButtonClicked = false;
    private Boolean isEqualButtonClicked = false;

    private Double currentNumber = 0.0; // Value can be changed.
    private Double currentResult = 0.0;
    private Double memory = 0.0;

    private String historyText = ""; // Recognize type of variable without declaring it.
    private String historyInstantOperationText = "";
//    private ArrayList<String> historyActionList = ArrayList();
    private static ArrayList historyActionList = new ArrayList();
    private final String ZERO = "0";// Value cannot be changed.
    private final String ONE = "1";
    private final String TWO = "2";
    private final String THREE = "3";
    private final String FOUR = "4";
    private final String FIVE = "5";
    private final String SIX = "6";
    private final String SEVEN = "7";
    private final String EIGHT = "8";
    private final String NINE = "9";

    private final String INIT = "";

    private final String ADDITION = " + ";
    private final String SUBTRACTION = " − ";
    private final String MULTIPLICATION = " × ";
    private final String DIVISION = " ÷ ";

    private final String PERCENTAGE = "";
    private final String ROOT = "√";
    private final String SQUARE = "sqr";
    private final String FRACTION = "1/";

    private final String NEGATE = "negate";
    private final String COMMA = ".";
    private final String EQUAL = " = ";

    private String currentOperation = INIT;
    private TextView textViewCurrentNumber, textViewHistoryText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_standard,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button button0 = view.findViewById(R.id.number0);
        Button button1 = view.findViewById(R.id.number1);
        Button button2 = view.findViewById(R.id.number2);
        Button button3 = view.findViewById(R.id.number3);
        Button button4 = view.findViewById(R.id.number4);
        Button button5 = view.findViewById(R.id.number5);
        Button button6 = view.findViewById(R.id.number6);
        Button button7 = view.findViewById(R.id.number7);
        Button button8 = view.findViewById(R.id.number8);
        Button button9 = view.findViewById(R.id.number9);
        Button buttonComma = view.findViewById(R.id.DotBtn);
        textViewHistoryText = view .findViewById(R.id.prevExpress);
        textViewCurrentNumber = view.findViewById(R.id.resultNum);
        Button buttonPlusMinus = view.findViewById(R.id.inverseBtn);
        Button buttonAddition = view.findViewById(R.id.AddBtn);
        Button buttonSubtraction = view.findViewById(R.id.SubBtn);
        Button buttonMultiplication = view.findViewById(R.id.MulBtn);
        Button buttonDivision = view.findViewById(R.id.DivBtn);
        Button buttonFraction = view.findViewById(R.id.on1divxBtn);
        ImageButton buttonRoot = view.findViewById(R.id.SqrtBtn);
        Button buttonSquare = view.findViewById(R.id.SquareBtn);
        Button buttonC = view.findViewById(R.id.CEBtn);
        Button buttonCE = view.findViewById(R.id.CBtn);
        Button buttonEqual = view.findViewById(R.id.EqualBtn);
        Button buttonBackspace = view.findViewById(R.id.DeleteBtn);
        Button buttonPercentage = view.findViewById(R.id.PercentBtn);

        Button buttonMemoryClear =view.findViewById(R.id.button25);
        Button buttonMemoryRecall =view.findViewById(R.id.button26);
        Button buttonMemoryAdd =view.findViewById(R.id.button27);
        Button buttonMemorySubtract =view.findViewById(R.id.button28);
        Button buttonMemoryStore =view.findViewById(R.id.button50);


        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onNumberButtonClick(ZERO);
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onNumberButtonClick(ONE);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onNumberButtonClick(TWO);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onNumberButtonClick(THREE);
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onNumberButtonClick(FOUR);
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onNumberButtonClick(FIVE);
            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onNumberButtonClick(SIX);
            }
        });

        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onNumberButtonClick(SEVEN);
            }
        });

        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onNumberButtonClick(EIGHT);
            }
        });

        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onNumberButtonClick(NINE);
            }
        });

        buttonAddition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onFutureOperationButtonClick(ADDITION);
            }
        });

        buttonSubtraction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onFutureOperationButtonClick(SUBTRACTION);
            }
        });

        buttonMultiplication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onFutureOperationButtonClick(MULTIPLICATION);
            }
        });

        buttonDivision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onFutureOperationButtonClick(DIVISION);
            }
        });

        buttonCE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearEntry(0);
            }
        });

        buttonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentNumber = 0.0;
                currentResult = 0.0;
                currentOperation = INIT;

                historyText = "";
                historyInstantOperationText = "";

                textViewCurrentNumber.setText(String.valueOf(currentNumber));
                textViewHistoryText.setText(historyText);

                isFutureOperationButtonClicked = false;
                isEqualButtonClicked = false;
                isInstantOperationButtonClicked = false;
            }
        });

        buttonBackspace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isFutureOperationButtonClicked && !isInstantOperationButtonClicked && !isEqualButtonClicked) {
                    String currentValue = textViewCurrentNumber.getText().toString();
                    char var4 = StringsKt.first((CharSequence)currentValue);
                    int charsLimit = Character.isDigit(var4) ? 1 : 2;
                    if (currentValue.length() > charsLimit) {
                        byte var5 = 0;
                        int var6 = currentValue.length() - 1;
                        if (currentValue == null) {
                            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                        }

                        String var10000 = currentValue.substring(var5, var6);
                        Intrinsics.checkExpressionValueIsNotNull(var10000, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                        currentValue = var10000;
                    } else {
                        currentValue = ZERO;
                    }

                    textViewCurrentNumber.setText((CharSequence)currentValue);
                    currentNumber = formatStringToDouble(currentValue);
                }
            }
        });

        buttonPlusMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String currentValue = textViewCurrentNumber.getText().toString();
                currentNumber = formatStringToDouble(currentValue);
                if (currentNumber != 0.0D) {
                    currentNumber = currentNumber * (double)-1;
                    textViewCurrentNumber.setText((CharSequence)formatDoubleToString(currentNumber));
                    if (isInstantOperationButtonClicked) {
                        historyInstantOperationText = '(' + historyInstantOperationText + ')';
                        String var10001 = NEGATE + historyInstantOperationText;
                        Intrinsics.checkNotNullExpressionValue(var10001, "StringBuilder().append(N…OperationText).toString()");
                        historyInstantOperationText = var10001;
                        textViewHistoryText.setText((CharSequence)(historyText + currentOperation + historyInstantOperationText));
                    }

                    if (isEqualButtonClicked) {
                        currentOperation = INIT;
                    }

                    isFutureOperationButtonClicked = false;
                    isEqualButtonClicked = false;
                }
            }
        });

        buttonComma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String currentValue = textViewCurrentNumber.getText().toString();
                String var10000;
                if (!isFutureOperationButtonClicked && !isInstantOperationButtonClicked && !isEqualButtonClicked) {
                    if (currentValue.contains(COMMA)) {
                        return;
                    }

                    var10000 = currentValue + COMMA;
                    Intrinsics.checkNotNullExpressionValue(var10000, "StringBuilder().append(c….append(COMMA).toString()");
                    currentValue = var10000;
                } else {
                    var10000 = ZERO + COMMA;
                    Intrinsics.checkNotNullExpressionValue(var10000, "StringBuilder().append(Z….append(COMMA).toString()");
                    currentValue = var10000;
                    if (isInstantOperationButtonClicked) {
                        historyInstantOperationText = "";
                        textViewHistoryText.setText((CharSequence)(historyText + currentOperation));
                    }

                    if (isEqualButtonClicked) {
                        currentOperation = INIT;
                    }

                    currentNumber = 0.0D;
                }

                textViewCurrentNumber.setText((CharSequence)currentValue);
                isFutureOperationButtonClicked = false;
                isInstantOperationButtonClicked = false;
                isEqualButtonClicked = false;
            }
        });

        buttonEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isFutureOperationButtonClicked) {
                    currentNumber = currentResult;
                }

                String historyAllText = calculateResult();
                Toast.makeText(getContext().getApplicationContext(), (CharSequence)historyAllText, Toast.LENGTH_LONG).show();
                historyActionList.add(historyAllText);
                String var10001 = formatDoubleToString(currentResult);
                Intrinsics.checkNotNullExpressionValue(var10001, "StringBuilder().append(f…urrentResult)).toString()");
                historyText = var10001;
                textViewHistoryText.setText((CharSequence)"");
                isFutureOperationButtonClicked = false;
                isEqualButtonClicked = true;

            }
        });

        buttonPercentage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onInstantOperationButtonClick(PERCENTAGE);
            }
        });

        buttonRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onInstantOperationButtonClick(ROOT);
            }
        });

        buttonSquare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onInstantOperationButtonClick(SQUARE);
            }
        });

        buttonFraction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onInstantOperationButtonClick(FRACTION);
            }
        });

        buttonMemoryClear.setEnabled(false);
        buttonMemoryClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonMemoryClear.setEnabled(false);
                buttonMemoryRecall.setEnabled(false);
                memory = 0.0D;
                Toast.makeText(getContext().getApplicationContext(), (CharSequence)getString(R.string.memory_cleared_toast), Toast.LENGTH_SHORT).show();
            }
        });

        buttonMemoryRecall.setEnabled(false);
        buttonMemoryRecall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearEntry(memory);
                Toast.makeText(getContext().getApplicationContext(), (CharSequence)getString(R.string.memory_recalled_toast), Toast.LENGTH_SHORT).show();
            }
        });

        buttonMemoryAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonMemoryClear.setEnabled(true);
                buttonMemoryRecall.setEnabled(true);
                String currentValue = textViewCurrentNumber.getText().toString();
                double thisOperationNumber = formatStringToDouble(currentValue);
                double newMemory = memory + thisOperationNumber;
                Toast.makeText(getContext().getApplicationContext(), (CharSequence)(getString(R.string.memory_added_toast) + formatDoubleToString(memory)
                        + " + " + formatDoubleToString(thisOperationNumber) + " = " + formatDoubleToString(newMemory)), Toast.LENGTH_LONG).show();
                memory = newMemory;
            }
        });

        buttonMemorySubtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonMemoryClear.setEnabled(true);
                buttonMemoryRecall.setEnabled(true);
                String currentValue = textViewCurrentNumber.getText().toString();
                double thisOperationNumber = formatStringToDouble(currentValue);
                double newMemory = memory - thisOperationNumber;
                Toast.makeText(getContext().getApplicationContext(), (CharSequence)(getString(R.string.memory_subtracted_toast)
                        + formatDoubleToString(memory) + " - " + formatDoubleToString(thisOperationNumber) + " = "
                        + formatDoubleToString(newMemory)), Toast.LENGTH_LONG).show();
                memory = newMemory;
            }
        });

        buttonMemoryStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String currentValue = textViewCurrentNumber.getText().toString();
                memory = formatStringToDouble(currentValue);
                buttonMemoryClear.setEnabled(true);
                buttonMemoryRecall.setEnabled(true);
                Toast.makeText(getContext().getApplicationContext(), (CharSequence)(getString(R.string.memory_stored_toast)
                        + formatDoubleToString(memory)), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private final void onNumberButtonClick(String number) throws IllegalArgumentException {
        String currentValue = this.textViewCurrentNumber.getText().toString();
        String var10000;
        if (!Intrinsics.areEqual(currentValue, this.ZERO) && !this.isFutureOperationButtonClicked && !this.isInstantOperationButtonClicked && !this.isEqualButtonClicked) {
            var10000 = currentValue + number;
            Intrinsics.checkNotNullExpressionValue(var10000, "StringBuilder().append(c…append(number).toString()");
        } else {
            var10000 = number;
        }

        currentValue = var10000;

        this.currentNumber = this.formatStringToDouble(currentValue);

//        try {
//            this.currentNumber = this.formatStringToDouble(currentValue);
//        } catch (ParseException var5) {
//            throw (Throwable)(new IllegalArgumentException("String must be number."));
//        }

        this.textViewCurrentNumber.setText((CharSequence)currentValue);
        if (this.isEqualButtonClicked) {
            this.currentOperation = this.INIT;
            this.historyText = "";
        }

        if (this.isInstantOperationButtonClicked) {
            this.historyInstantOperationText = "";
            this.textViewHistoryText.setText((CharSequence)(this.historyText + this.currentOperation));
            this.isInstantOperationButtonClicked = false;
        }

        this.isFutureOperationButtonClicked = false;
        this.isEqualButtonClicked = false;
    }

    // $FF: synthetic method
//    static void onNumberButtonClick$default(MainActivity var0, String var1, boolean var2, int var3, Object var4) throws IllegalArgumentException {
//        if ((var3 & 2) != 0) {
//            var2 = false;
//        }
//
//        var0.onNumberButtonClick(var1, var2);
//    }

    private final void onFutureOperationButtonClick(String operation) {
        if (!isFutureOperationButtonClicked && !isEqualButtonClicked) {
            calculateResult();
        }

        currentOperation = operation;
        if (isInstantOperationButtonClicked) {
            isInstantOperationButtonClicked = false;
            historyText = textViewHistoryText.getText().toString();
        }

        textViewHistoryText.setText((CharSequence)(this.historyText + operation));
        isFutureOperationButtonClicked = true;
        isEqualButtonClicked = false;
    }

    private final void onInstantOperationButtonClick(String operation) {
        String currentValue = this.textViewCurrentNumber.getText().toString();
        double thisOperationNumber = this.formatStringToDouble(currentValue);
        currentValue = '(' + this.formatDoubleToString(thisOperationNumber) + ')';
        if (Intrinsics.areEqual(operation, this.PERCENTAGE)) {
            thisOperationNumber = thisOperationNumber / (double)100;
            currentValue = this.formatDoubleToString(thisOperationNumber);
        } else if (Intrinsics.areEqual(operation, this.ROOT)) {
            thisOperationNumber = this.getSqrt(thisOperationNumber);
        } else if (Intrinsics.areEqual(operation, this.SQUARE)) {
            thisOperationNumber *= thisOperationNumber;
        } else if (Intrinsics.areEqual(operation, this.FRACTION)) {
            thisOperationNumber = (double)1 / thisOperationNumber;
        }

        String var10001;
        if (this.isInstantOperationButtonClicked) {
            this.historyInstantOperationText = '(' + this.historyInstantOperationText + ')';
            var10001 = operation + this.historyInstantOperationText;
            Intrinsics.checkNotNullExpressionValue(var10001, "StringBuilder().append(o…OperationText).toString()");
            this.historyInstantOperationText = var10001;
            this.textViewHistoryText.setText(this.isEqualButtonClicked ? (CharSequence)this.historyInstantOperationText : (CharSequence)(this.historyText + this.currentOperation + this.historyInstantOperationText));
        } else if (this.isEqualButtonClicked) {
            var10001 = operation + currentValue;
            Intrinsics.checkNotNullExpressionValue(var10001, "StringBuilder().append(o…(currentValue).toString()");
            this.historyInstantOperationText = var10001;
            this.textViewHistoryText.setText((CharSequence)this.historyInstantOperationText);
        } else {
            var10001 = operation + currentValue;
            Intrinsics.checkNotNullExpressionValue(var10001, "StringBuilder().append(o…(currentValue).toString()");
            this.historyInstantOperationText = var10001;
            this.textViewHistoryText.setText((CharSequence)(this.historyText + this.currentOperation + this.historyInstantOperationText));
        }

        this.textViewCurrentNumber.setText((CharSequence)this.formatDoubleToString(thisOperationNumber));
        if (this.isEqualButtonClicked) {
            this.currentResult = thisOperationNumber;
        } else {
            this.currentNumber = thisOperationNumber;
        }

        this.isInstantOperationButtonClicked = true;
        this.isFutureOperationButtonClicked = false;
    }

    private final String calculateResult() {
        String var1 = this.currentOperation;
        String var10001;
        if (Intrinsics.areEqual(var1, this.INIT)) {
            this.currentResult = this.currentNumber;
            var10001 = this.textViewHistoryText.getText().toString();
            Intrinsics.checkNotNullExpressionValue(var10001, "StringBuilder().append(t…xt.toString()).toString()");
            this.historyText = var10001;
        } else if (Intrinsics.areEqual(var1, this.ADDITION)) {
            this.currentResult += this.currentNumber;
        } else if (Intrinsics.areEqual(var1, this.SUBTRACTION)) {
            this.currentResult -= this.currentNumber;
        } else if (Intrinsics.areEqual(var1, this.MULTIPLICATION)) {
            this.currentResult *= this.currentNumber;
        } else if (Intrinsics.areEqual(var1, this.DIVISION)) {
            this.currentResult /= this.currentNumber;
        }

        this.textViewCurrentNumber.setText((CharSequence)this.formatDoubleToString(this.currentResult));
        if (this.isInstantOperationButtonClicked) {
            this.isInstantOperationButtonClicked = false;
            this.historyText = this.textViewHistoryText.getText().toString();
            if (this.isEqualButtonClicked) {
                var10001 = this.historyText + this.currentOperation + this.formatDoubleToString(this.currentNumber);
                Intrinsics.checkNotNullExpressionValue(var10001, "StringBuilder().append(h…urrentNumber)).toString()");
                this.historyText = var10001;
            }
        } else {
            var10001 = this.historyText + this.currentOperation + this.formatDoubleToString(this.currentNumber);
            Intrinsics.checkNotNullExpressionValue(var10001, "StringBuilder().append(h…urrentNumber)).toString()");
            this.historyText = var10001;
        }

        String var10000 = this.historyText + this.EQUAL + this.formatDoubleToString(this.currentResult);
        Intrinsics.checkNotNullExpressionValue(var10000, "StringBuilder().append(h…urrentResult)).toString()");
        return var10000;
    }

    private final DecimalFormat useNumberFormat() {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator(',');
        DecimalFormat format = new DecimalFormat("#.##############");
        format.setDecimalFormatSymbols(symbols);
        return format;
    }

    private final String formatDoubleToString(double number) {
        String var10000 = this.useNumberFormat().format(number);
        Intrinsics.checkNotNullExpressionValue(var10000, "useNumberFormat().format(number)");
        return var10000;
    }

    private final double formatStringToDouble(String number) {
        return Double.parseDouble(number);
    }

    private final double getSqrt(double $this$sqrt) {
        return Math.sqrt($this$sqrt);
    }

    private final void clearEntry(double newNumber) {
        this.historyInstantOperationText = "";
        if (this.isEqualButtonClicked) {
            this.currentOperation = this.INIT;
            this.historyText = "";
        }

        if (this.isInstantOperationButtonClicked) {
            this.textViewHistoryText.setText((CharSequence)(this.historyText + this.currentOperation));
        }

        this.isInstantOperationButtonClicked = false;
        this.isFutureOperationButtonClicked = false;
        this.isEqualButtonClicked = false;
        this.currentNumber = newNumber;
        this.textViewCurrentNumber.setText((CharSequence)this.formatDoubleToString(newNumber));
    }

    // $FF: synthetic method
//    static void clearEntry$default(MainActivity var0, double var1, int var3, Object var4) {
//        if ((var3 & 1) != 0) {
//            var1 = 0.0D;
//        }
//
//        var0.clearEntry(var1);
//    }

//    Tao History
//    public boolean onCreateOptionsMenu(@org.jetbrains.annotations.Nullable Menu menu) {
//        this.getMenuInflater().inflate(1400000, menu);
//        return super.onCreateOptionsMenu(menu);
//    }
//
//    public boolean onOptionsItemSelected(@org.jetbrains.annotations.Nullable MenuItem item) {
//        Integer var2 = item != null ? item.getItemId() : null;
//        int var3 = 1000171;
//        if (var2 != null) {
//            if (var2 == var3) {
//                HistoryActionListDialogFragment.Companion.newInstance(this.historyActionList).show(this.getSupportFragmentManager(), "dialog");
//                return true;
//            }
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
//
//    public void onHistoryItemClicked(@NotNull String resultText) {
//        Intrinsics.checkNotNullParameter(resultText, "resultText");
//
//        try {
//            this.onNumberButtonClick(resultText, true);
//        } catch (IllegalArgumentException var3) {
//            return;
//        }
//
//        Toast.makeText(this.getApplicationContext(), (CharSequence)(this.getString(1900066) + resultText), 0).show();
//    }

//    @NotNull
//    public final Lazy bind(@NotNull final Activity $this$bind, @IdRes final int idRes) {
//        Intrinsics.checkNotNullParameter($this$bind, "$this$bind");
//        return LazyKt.lazy(LazyThreadSafetyMode.NONE, (Function0)(new Function0() {
//            // $FF: synthetic method
//            // $FF: bridge method
//            public Object invoke() {
//                return this.invoke();
//            }
//
//            public final View invoke() {
//                return $this$bind.findViewById(idRes);
//            }
//        }));
//    }

//    public MainActivity() {
//        this.currentOperation = this.INIT;
//    }

    // $FF: synthetic method
    public static final void access$setHistoryActionList$p(ArrayList var1) {
        historyActionList = var1;
    }
}
