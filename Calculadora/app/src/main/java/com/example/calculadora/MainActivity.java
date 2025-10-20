package com.example.calculadora;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity
{
    TextView view;
    private boolean addedOp;
    private boolean addedCommaLeft;
    private boolean addedCommaRight;
    String equation = "";


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.calculadora);
    }


    @Override
    protected void onStart()
    {
        super.onStart();
        view = findViewById(R.id.view);
        Button boton1 = findViewById(R.id.boton1);
        boton1.setOnClickListener(v -> AddNumber(1));
        Button boton2 = findViewById(R.id.boton2);
        boton2.setOnClickListener(v -> AddNumber(2));
        Button boton3 = findViewById(R.id.boton3);
        boton3.setOnClickListener(v -> AddNumber(3));
        Button boton4 = findViewById(R.id.boton4);
        boton4.setOnClickListener(v -> AddNumber(4));
        Button boton5 = findViewById(R.id.boton5);
        boton5.setOnClickListener(v -> AddNumber(5));
        Button boton6 = findViewById(R.id.boton6);
        boton6.setOnClickListener(v -> AddNumber(6));
        Button boton7 = findViewById(R.id.boton7);
        boton7.setOnClickListener(v -> AddNumber(7));
        Button boton8 = findViewById(R.id.boton8);
        boton8.setOnClickListener(v -> AddNumber(8));
        Button boton9 = findViewById(R.id.boton9);
        boton9.setOnClickListener(v -> AddNumber(9));
        Button boton0 = findViewById(R.id.boton0);
        boton0.setOnClickListener(v -> AddNumber(0));
        Button botonDiv = findViewById(R.id.botonDiv);
        botonDiv.setOnClickListener(v -> AddOperator('/'));
        Button botonMult = findViewById(R.id.botonMult);
        botonMult.setOnClickListener(v -> AddOperator('*'));
        Button botonResta = findViewById(R.id.botonMenos);
        botonResta.setOnClickListener(v -> AddOperator('-'));
        Button botonSuma = findViewById(R.id.botonSuma);
        botonSuma.setOnClickListener(v -> AddOperator('+'));
        Button botonComa = findViewById(R.id.botonComa);
        botonComa.setOnClickListener(v -> AddComma());
        Button botonC = findViewById(R.id.botonC);
        botonC.setOnClickListener(v -> BorrarUno());
        Button botonAC = findViewById(R.id.botonAC);
        botonAC.setOnClickListener(v -> BorrarTodo());
        Button botonIgual = findViewById(R.id.botonIgual);
        botonIgual.setOnClickListener(v -> CalcularResultado());
    }


    private void BorrarTodo()
    {
        Reset();
        UpdateReadout();
    }


    private void Reset()
    {
        equation = "0";
        addedCommaLeft = false;
        addedCommaRight = false;
        addedOp = false;
    }


    private void BorrarUno()
    {
        if (equation.isEmpty()) return;

        String newString = "";

        char lastChar = equation.charAt(equation.length() - 1);
        if (!Character.isDigit(lastChar))
        {
            if (lastChar == '.')
            {
                if (addedOp)
                {
                    addedCommaRight = false;
                }
                else
                {
                    addedCommaLeft = false;
                }
            }
            else
            {
                addedOp = false;
            }
        }
        for (int i = 0; i < equation.length() - 1; i++)
        {
            char character = equation.charAt(i);

            newString += character;
        }

        equation = newString.length() > 0 ? newString : "0";
        UpdateReadout();
    }


    private void AddNumber(int number)
    {
        if (equation.startsWith("0"))
        {
            if (number != 0)
            {
                equation = String.valueOf(number);
            }
        }
        else
        {
            equation += String.valueOf(number);
        }
        UpdateReadout();
    }


    private void AddNumber(int number, boolean force)
    {
        if (!force && number == 0 && equation.isEmpty()) return;
        equation += String.valueOf(number);
        UpdateReadout();
    }


    private void AddOperator(char operator)
    {
        if (equation.isEmpty()
                || addedOp
                || equation.charAt(equation.length() - 1) == '.' ) return;

        equation += operator;
        addedOp = true;
        UpdateReadout();
    }


    private void AddComma()
    {
        if (!addedOp && !addedCommaLeft)
        {
            if (equation.isEmpty() || !Character.isDigit(equation.charAt(equation.length() - 1)))
            {
                AddNumber(0, true);
            }
            equation += '.';
            addedCommaLeft = true;
        }
        else if (addedOp && !addedCommaRight)
        {
            if (equation.isEmpty() || !Character.isDigit(equation.charAt(equation.length() - 1)))
            {
                AddNumber(0);
            }
            equation += '.';
            addedCommaRight = true;
        }
        UpdateReadout();
    }


    private void UpdateReadout()
    {
        view.setText(equation);
    }


    private void CalcularResultado()
    {
        if (equation.isEmpty()) return;

        String leftNumText = "";
        String rightNumText = "";
        char op = '\u0000';
        for (int i = 0; i < equation.length(); i++)
        {
            char character = equation.charAt(i);
            if (Character.isDigit(character) || character == '.')
            {
                if (op == '\u0000')
                {
                    leftNumText += character;
                }
                else
                {
                    rightNumText += character;
                }
            }
            else
            {
                op = character;
            }


        }
        float left = Float.parseFloat(leftNumText);
        float right = Float.parseFloat(rightNumText);

        view.setText(String.valueOf(DoOperation(left, right, op)));
        Reset();
    }


    private float DoOperation(float A, float B, char Op)
    {
        switch (Op)
        {
            case '+':
                return A + B;
            case '-':
                return A - B;
            case '*':
                return A * B;
            case '/':
                if (B <= 0)
                {
                    return Float.NaN;
                }
                else
                {
                    return  A / B;
                }
        }
        return Float.NaN;
    }
}