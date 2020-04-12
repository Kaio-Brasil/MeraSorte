package br.com.android.merasorte

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import kotlinx.android.synthetic.main.activity_ver_resultado.*

class VerResultado : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_resultado)

        val numeros: IntArray = intent.getIntArrayExtra("numeros")
        var tipoJogo: Int = 0

        if(numeros.size == 6) {
            tipo_jogo.setText(R.string.mega_sena)
            tipoJogo = 6
        } else if(numeros.size == 5) {
            tipo_jogo.setText(R.string.quina)
            tipoJogo = 5
        } else if(numeros.size == 15) {
            tipo_jogo.setText(R.string.loto_facil)
            tipoJogo = 5
        } else if(numeros.size == 50) {
            tipo_jogo.setText(R.string.loto_mania)
            tipoJogo = 10
        } else {
            Snackbar.make(tela_titulo, R.string.aviso_snackbar, Snackbar.LENGTH_SHORT).setAction("Ok", null).show()
        }

        var concat: String = ""

        for(i in 1..numeros.size) {
            if(i%tipoJogo == 0 && i != 0) {
                if(numeros[i-1] > 9) {
                    concat +=  "" + numeros[i-1] + "\n"
                } else {
                    concat += "0" + numeros[i-1] + "\n"
                }
            } else {
                if(numeros[i-1] > 9) {
                    concat += "" + numeros[i-1] + " "
                } else {
                    concat += "0" + numeros[i-1] + " "
                }
            }
        }

        numeros_sorteados.setText(concat)

    }
}
