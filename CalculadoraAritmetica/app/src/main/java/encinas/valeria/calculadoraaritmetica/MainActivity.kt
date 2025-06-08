package encinas.valeria.calculadoraaritmetica

import android.icu.text.DecimalFormat
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.time.temporal.Temporal


class MainActivity : AppCompatActivity() {
    val suma = "+"
    val resta = "-"
    val multiplicacion = "*"
    val dicision = "/"
    val porsentaje = "%"

    var operacionActual = ""
    var primerNum: Double = Double.NaN
    var segundoNum: Double = Double.NaN

    lateinit var numTemporal:TextView
    lateinit var resultado:TextView

    lateinit var formatoDecimal:DecimalFormat

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        formatoDecimal = DecimalFormat("#.##########")
        numTemporal = findViewById(R.id.numTemporal)
        resultado = findViewById(R.id.resultado)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun cambiarOperador(b: View) {
        val boton: Button = b as Button
        val operadorTexto = when (boton.text.toString().trim()) {
            "÷" -> "/"
            "X" -> "*"
            else -> boton.text.toString().trim()
        }

        if (!numTemporal.text.isNullOrEmpty()) {
            calcular()
        }
        operacionActual = operadorTexto
        resultado.text = formatoDecimal.format(primerNum) + operacionActual
        numTemporal.text = ""
    }

    fun calcular(){
        try {
            if (!primerNum.isNaN()) {
                if (numTemporal.text.toString().isNotEmpty()) {
                    segundoNum = numTemporal.text.toString().toDouble()
                    numTemporal.text = ""

                    when (operacionActual) {
                        "+" -> primerNum += segundoNum
                        "-" -> primerNum -= segundoNum
                        "*" -> primerNum *= segundoNum
                        "/" -> primerNum /= segundoNum
                        "%" -> primerNum %= segundoNum
                    }
                }
            } else if(numTemporal.text.toString().isNotEmpty()) {
                    primerNum = numTemporal.text.toString().toDouble()
                    numTemporal.text = ""
                }

        } catch (e: Exception) {
            resultado.text = "Error"
        }

    }

    fun seleccionarNumero(b: View){
        val boton: Button = b as Button
        numTemporal.text = numTemporal.text.toString() + boton.text.toString()
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun igual(b: View){
        calcular()
        if (!primerNum.isNaN()) {
            resultado.text = formatoDecimal.format(primerNum)
        } else {
            resultado.text = "Error"
        }
        operacionActual = ""
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun borrar(b: View) {
        val boton: Button = b as Button
        if (boton.text.toString().trim() == "C") {
            if (numTemporal.text.toString().isNotEmpty()) {
                var datosActuales: CharSequence = numTemporal.text as CharSequence
                numTemporal.text = datosActuales.subSequence(0, datosActuales.length - 1)
            }
            else {
                primerNum = Double.NaN
                segundoNum = Double.NaN
                numTemporal.text = ""
                resultado.text = ""
            }
        }else if (boton.text.toString().trim() == "CA") {
            primerNum = Double.NaN
            segundoNum = Double.NaN
            numTemporal.text = ""
            resultado.text = ""
            }
        }
    }
