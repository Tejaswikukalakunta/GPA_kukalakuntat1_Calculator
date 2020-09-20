package com.example.gpa_kukalakuntat1_calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<String> grades = new ArrayList<>();
    //    EditText grade1, grade2, grade3, grade4, grade5;
    TextView credit1, credit2, credit3, credit4, credit5, result;
    Button compute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initGrades();
        credit1 = findViewById(R.id.credit_1);
        credit2 = findViewById(R.id.credit_2);
        credit3 = findViewById(R.id.credit_3);
        credit4 = findViewById(R.id.credit_4);
        credit5 = findViewById(R.id.credit_5);
        result = findViewById(R.id.result);

        setFilter((EditText) findViewById(R.id.grade_1), credit1);
        setFilter((EditText) findViewById(R.id.grade_2), credit2);
        setFilter((EditText) findViewById(R.id.grade_3), credit3);
        setFilter((EditText) findViewById(R.id.grade_4), credit4);
        setFilter((EditText) findViewById(R.id.grade_5), credit5);


        compute = findViewById(R.id.compute);
        compute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (compute.getText().toString().equalsIgnoreCase(getString(R.string.clear_form))) {
                    ((EditText) findViewById(R.id.grade_1)).setText("");
                    ((EditText) findViewById(R.id.grade_2)).setText("");
                    ((EditText) findViewById(R.id.grade_3)).setText("");
                    ((EditText) findViewById(R.id.grade_4)).setText("");
                    ((EditText) findViewById(R.id.grade_5)).setText("");
                    return;
                }
                try {
                    int value1 = Integer.parseInt(credit1.getText().toString());
                    int value2 = Integer.parseInt(credit2.getText().toString());
                    int value3 = Integer.parseInt(credit3.getText().toString());
                    int value4 = Integer.parseInt(credit4.getText().toString());
                    int value5 = Integer.parseInt(credit5.getText().toString());

                    double res = (value1 + value2 + value3 + value4 + value5) / 5.0;

                    result.setText("" + res);
                    if (res >= 3.5) {
                        result.setBackgroundColor(Color.GREEN);
                    } else if (res >= 3) {
                        result.setBackgroundColor(Color.YELLOW);
                    } else {
                        result.setBackgroundColor(Color.RED);
                    }
                    compute.setText(R.string.clear_form);
                } catch (NumberFormatException e) {

                }

            }
        });

    }

    private void initGrades() {
        grades.clear();
        grades.add("A");
        grades.add("B");
        grades.add("C");
        grades.add("F");
    }

    public void setFilter(EditText editText, TextView textView) {
        editText.setFilters(new InputFilter[]{new InputFilter.AllCaps(), new InputFilter.LengthFilter(1)});
        addChangeListener(editText, textView);
    }

    public void addChangeListener(final EditText editText, final TextView textView) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.e("Chars=" + s, ">>" + count);
                if (count == 1) {
                    if (grades.contains("" + s)) {
                        Log.e("Grade ", "found");
                        editText.setBackgroundColor(getResources().getColor(R.color.background_label));
                        switch ("" + s) {
                            case "A":
                                textView.setText("" + 4);
                                break;
                            case "B":
                                textView.setText("" + 3);
                                break;
                            case "C":
                                textView.setText("" + 2);
                                break;
                            case "F":
                                textView.setText("" + 0);
                                break;
                            default:
                                textView.setText("");
                                break;
                        }
                        result.setBackgroundColor(Color.WHITE);
                        result.setText("");

                    } else {
                        Log.e("Grade ", "not found");
                        editText.setBackgroundColor(getResources().getColor(R.color.color_red));
                        textView.setText("");
                        result.setBackgroundColor(Color.WHITE);
                        result.setText("");
                    }
                } else {
                    editText.setBackgroundColor(getResources().getColor(R.color.background_label));
                    textView.setText("");

                }
                result.setBackgroundColor(Color.WHITE);
                result.setText("");
                compute.setText(R.string.compute_gpa);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

}