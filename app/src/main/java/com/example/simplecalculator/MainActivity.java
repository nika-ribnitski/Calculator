package com.example.simplecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.Stack;

public class MainActivity extends AppCompatActivity {
    Button button0, button1, button2, button3, button4, button5, button6, button7, button8, button9, buttonDecimal, buttonAdd, buttonSub, buttonMul, buttonDiv, buttonOpenPara, buttonClosePara, buttonClear, buttonEnter;
    TextView edttxt;
    CalculatorUtil calculator = new CalculatorUtil();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Buttons
        button0 = (Button) findViewById(R.id.btn0);
        button1 = (Button) findViewById(R.id.btn1);
        button2 = (Button) findViewById(R.id.btn2);
        button3 = (Button) findViewById(R.id.btn3);
        button4 = (Button) findViewById(R.id.btn4);
        button5 = (Button) findViewById(R.id.btn5);
        button6 = (Button) findViewById(R.id.btn6);
        button7 = (Button) findViewById(R.id.btn7);
        button8 = (Button) findViewById(R.id.btn8);
        button9 = (Button) findViewById(R.id.btn9);
        buttonDecimal = (Button) findViewById(R.id.btnDecimal);
        buttonAdd = (Button) findViewById(R.id.btnAdd);
        buttonSub = (Button) findViewById(R.id.btnSub);
        buttonMul = (Button) findViewById(R.id.btnMul);
        buttonDiv = (Button) findViewById(R.id.btnDiv);
        buttonOpenPara = (Button) findViewById(R.id.btnOpenPara);
        buttonClosePara = (Button) findViewById(R.id.btnClosePara);
        buttonClear = (Button) findViewById(R.id.btnClear);
        buttonEnter = (Button) findViewById(R.id.btnEnter);
        //TextView
        edttxt = (TextView) findViewById(R.id.screen);

        //Numbers/Decimals--------------------------------------------------------------------------
        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edttxt.setText(edttxt.getText() + "0");
                calculator.appendValue("0");
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edttxt.setText(edttxt.getText() + "1");
                calculator.appendValue("1");
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edttxt.setText(edttxt.getText() + "2");
                calculator.appendValue("2");
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edttxt.setText(edttxt.getText() + "3");
                calculator.appendValue("3");
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edttxt.setText(edttxt.getText() + "4");
                calculator.appendValue("4");
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edttxt.setText(edttxt.getText() + "5");
                calculator.appendValue("5");
            }
        });
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edttxt.setText(edttxt.getText() + "6");
                calculator.appendValue("6");
            }
        });
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edttxt.setText(edttxt.getText() + "7");
                calculator.appendValue("7");
            }
        });
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edttxt.setText(edttxt.getText() + "8");
                calculator.appendValue("8");
            }
        });
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edttxt.setText(edttxt.getText() + "9");
                calculator.appendValue("9");
            }
        });
        buttonDecimal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!calculator.getDecimalUsed()) {
                    edttxt.setText(edttxt.getText() + ".");
                    calculator.appendValue(".");
                    calculator.setDecimalUsed(true);
                }
            }
        });

        //Calculator Operations---------------------------------------------------------------------
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculator.operation("+");
                edttxt.setText(edttxt.getText() + "+");
            }
        });
        buttonSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculator.operation("-");
                edttxt.setText(edttxt.getText() + "-");
            }
        });
        buttonDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculator.operation("/");
                edttxt.setText(edttxt.getText() + "/");
            }
        });
        buttonMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculator.operation("*");
                edttxt.setText(edttxt.getText() + "*");
            }
        });
        buttonOpenPara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculator.addToArray("(");
                edttxt.setText(edttxt.getText() + "(");
            }
        });
        buttonClosePara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculator.operation(")");
                edttxt.setText(edttxt.getText() + ")");
            }
        });
        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculator.setValue("");
                calculator.spill();
                calculator.setDecimalUsed(false);
                edttxt.setText("");
            }
        });
        buttonEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(calculator.peek() != ")"){
                    calculator.addToArray(calculator.getValue());
                }
                float answer = calculator.compute();
                edttxt.setText(edttxt.getText() + " = " + answer);
            }
        });
    }
}