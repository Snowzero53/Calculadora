package com.example.calculadora;

import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class MainActivity extends AppCompatActivity {

    private EditText entradaEditText;
    private TextView resultadoTextView;
    private Button calcularButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        entradaEditText = findViewById(R.id.editTextNumberDecimal);
        resultadoTextView = findViewById(R.id.textView2);
        calcularButton = findViewById(R.id.button16);


        calcularButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcularResultado();
            }
        });
    }

    // Método para calcular el resultado
    private void calcularResultado() {
        // Obtener la expresión ingresada
        String expresion = entradaEditText.getText().toString();

        try {
            // Evaluar la expresión matemática
            double resultado = evaluarExpresion(expresion);
            // Mostrar el resultado
            resultadoTextView.setText("Resultado: " + resultado);
        } catch (Exception e) {
            // Manejar errores
            resultadoTextView.setText("Error: Expresión inválida");
        }
    }

    // Método para evaluar la expresión matemática usando exp4j
    private double evaluarExpresion(String expresion) {
        // Crear una expresión con exp4j
        Expression expression = new ExpressionBuilder(expresion).build();
        return expression.evaluate(); // Evaluar y devolver el resultado
    }
}