package vcmsa.projects.calculatorapp

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var tvResult: TextView
    private var currentInput = ""
    private var lastOperator = ""
    private var lastNumber = 0.0
    private var newOperation = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvResult = findViewById(R.id.tvResult)
    }

    fun onDigitClick(view: android.view.View) {
        val button = view as Button
        if (newOperation) {
            currentInput = ""
            newOperation = false
        }
        currentInput += button.text
        tvResult.text = currentInput
    }

    fun onOperatorClick(view: android.view.View) {
        val button = view as Button
        if (currentInput.isNotEmpty()) {
            lastNumber = currentInput.toDouble()
            lastOperator = button.text.toString()
            newOperation = true
        }
    }

    fun onEqualClick(view: android.view.View) {
        if (currentInput.isNotEmpty() && lastOperator.isNotEmpty()) {
            val secondNumber = currentInput.toDouble()
            val result = when (lastOperator) {
                "+" -> lastNumber + secondNumber
                "-" -> lastNumber - secondNumber
                "*" -> lastNumber * secondNumber
                "/" -> if (secondNumber != 0.0) lastNumber / secondNumber else "Error"
                else -> ""
            }
            tvResult.text = result.toString()
            currentInput = result.toString()
            lastOperator = ""
            newOperation = true
        }
    }

    fun onClearClick(view: android.view.View) {
        currentInput = ""
        lastOperator = ""
        lastNumber = 0.0
        tvResult.text = "0"
        newOperation = true
    }
}
