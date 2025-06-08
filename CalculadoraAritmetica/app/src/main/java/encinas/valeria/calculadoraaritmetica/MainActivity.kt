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
        if (numTemporal.text.isEmpty() || primerNum.toString() != "NaN") {
            calcular()
            val boton: Button = b as Button
            if (boton.text.toString().trim() == "÷") {
                operacionActual = "/"
            } else if (boton.text.toString().trim() == "X") {
                operacionActual = "*"
            } else {
                operacionActual = boton.text.toString().trim()
            }
            resultado.text = formatoDecimal.format(primerNum) + operacionActual
            numTemporal.text = ""
        }
    }

    fun calcular(){
       try {
           if (primerNum.toString() != "NaN"){
               if (numTemporal.text.toString().isEmpty()){
                   numTemporal.text = resultado.text.toString()
               }
               segundoNum = numTemporal.text.toString().toDouble()
               numTemporal.text = ""

               when (operacionActual){
                   "+" -> primerNum = (primerNum + segundoNum)
                   "-" -> primerNum = (primerNum - segundoNum)
                   "*" -> primerNum = (primerNum * segundoNum)
                   "/" -> primerNum = (primerNum / segundoNum)
                   "%" -> primerNum = (primerNum % segundoNum)
               }
           } else {
               primerNum = numTemporal.text.toString().toDouble()
           }
       }catch (e:Exception){

       }
    }

    fun seleccionarNumero(b: View){
        val boton: Button = b as Button
        numTemporal.text = numTemporal.text.toString() + boton.text.toString()
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun igual(b: View){
        calcular()
        resultado.text = formatoDecimal.format((primerNum))
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
