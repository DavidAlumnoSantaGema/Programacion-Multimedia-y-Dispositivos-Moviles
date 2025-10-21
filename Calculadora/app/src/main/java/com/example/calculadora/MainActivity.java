package com.example.calculadora;

import android.media.SyncParams;
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
    /**
     * El TextView en la zona de la calculadora que muestra la ecuación
     */
    TextView view;
    /**
     * Controla si se ha añadido un operador de operación
     */
    private boolean addedOp;
    /**
     * Controla si se ha añadido coma en el número de la izquierda
     */
    private boolean addedCommaLeft;
    /**
     * Controla si se ha añadido coma en el número de la derecha
     */
    private boolean addedCommaRight;
    /**
     * String donde se guarda la ecuación total
     */
    String equation = "";


    /**
     * Cambia el layout al que he creado para la calculadora
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.calculadora);
    }


    /**
     * Encontramos todos los botones y les asignamos sus eventos.
     */
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


    /**
     * Método cuando se pulsa AC.
     */
    private void BorrarTodo()
    {
        Reset();
        UpdateReadout();
    }


    /**
     * Reinicia la calculadora a 0, cambia la ecuación y todos los bools
     */
    private void Reset()
    {
        equation = "0";
        addedCommaLeft = false;
        addedCommaRight = false;
        addedOp = false;
    }


    /**
     * Borra el último carácter del string que guarda la ecuación<br>
     * Lo hace recreando el string entero parando en el penúltimo carácter
     */
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


    /**
     * Añade un número al string que guarda la ecucación entera.<br>
     * Sustituye el 0 default, y no te permite meter 0s a la izquierda.
     * @param number El número que se quiere añadir a la ecuación
     */
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


    /**
     * Añade un operador al string que guarda la ecuación.<br>
     * Solo permite insertar un operador.
     * @param operator
     */
    private void AddOperator(char operator)
    {
        char character = equation.charAt(equation.length() - 1);
        if (character == '.') return;
        else if (addedOp && !Character.isDigit(character))
        {
            BorrarUno();
        }
        else if (addedOp) return;

        equation += operator;
        addedOp = true;
        UpdateReadout();
    }


    /**
     * Añade la coma deicmal al número que se esté escribiendo.<br>
     * Solo se permite una coma por número. El número de la izquierda se deja de considerar cuando se escribe un operador y viceversa.
     */
    private void AddComma()
    {
        if (!addedOp && !addedCommaLeft)
        {
            if (!Character.isDigit(equation.charAt(equation.length() - 1)))
            {
                AddNumber(0);
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


    /**
     * Cambia el texto del TextView al valor de la ecuación.
     */
    private void UpdateReadout()
    {
        view.setText(equation);
    }


    /**
     * Calcula el resultado a partir del string guardado en ecuación.<br>
     * Internamente guarda los números de la izquierda y de la derecha, y el operador de la ecuación.<br>
     * Los dos números luego son operados según el carácter encontrado usando DoOperation
     */
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

        if (rightNumText.isEmpty())
        {
            if (leftNumText.endsWith("."))
            {
                leftNumText += '0';
            }

            Reset();
            equation = leftNumText;

            UpdateReadout();
        }
        else
        {
            float left = Float.parseFloat(leftNumText);
            float right = Float.parseFloat(rightNumText);
            String res = String.valueOf(DoOperation(left, right, op));
            if (res.equals("NaN"))
            {
                view.setText("NaN");
                Reset();
            }
            else
            {
                if (res.endsWith(".0"))
                {
                    res = res.substring(0, res.length() - 2);
                }
                Reset();
                equation = res;

                UpdateReadout();
            }
        }
    }


    /**
     * Hace la operación delimitada por el operador
     * @param A Primer numero
     * @param B Segundo numero
     * @param Op Operador +, -, *, /
     * @return El resultado de la operación, o NaN si es división entre 0 u operación no soportada.
     */
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