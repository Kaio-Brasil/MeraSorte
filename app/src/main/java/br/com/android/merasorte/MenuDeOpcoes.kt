package br.com.android.merasorte

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_menu_de_opcoes.*
import java.util.*
import kotlin.random.Random

class MenuDeOpcoes : AppCompatActivity() {
    private var posicao: Int = 0
    private var numero: Int = 0
    private var validar: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_de_opcoes)

        var numeros: IntArray?

        btn_mega.setOnClickListener {
            numeros = sorteiaNumeros(6, 60)
            ordenacaoArray(numeros)
            mostrarResultado(numeros)
        }

        btn_quina.setOnClickListener {
            numeros = sorteiaNumeros(5, 60)
            ordenacaoArray(numeros)
            mostrarResultado(numeros)
        }

        btn_lotoFacil.setOnClickListener {
            numeros = sorteiaNumeros(15, 25)
            ordenacaoArray(numeros)
            mostrarResultado(numeros)
        }

        btn_lotoMania.setOnClickListener {
            numeros = sorteiaNumeros(50, 99)
            ordenacaoArray(numeros)
            mostrarResultado(numeros)
        }
    }

    protected fun sorteiaNumeros(tamanhoArray: Int, quantNumeroSort: Int): IntArray? {
        val numeros = IntArray(tamanhoArray)

        while(posicao < tamanhoArray) {
            numero = Random.nextInt(quantNumeroSort) + 1
            numeros[posicao] = numero

            for(i in numeros.indices) {
                if(i == posicao) {
                    continue
                }

                if(numeros[i] != 0 && numeros[i] != numero) {
                    validar++
                }
            }

            if(validar == posicao) {
                posicao++
            }

            validar = 0
        }

        if(tamanhoArray == 50) {
            numero = Random.nextInt(5)
            numeros[numeros.size-1] = if(numero == 0) 0 else numeros[numeros.size-1]
        }

        posicao = 0
        numero = 0
        validar = 0

        return numeros
    }

    protected fun ordenacaoArray(numeros: IntArray?) {
        var aux: Int

        if(numeros != null) {
            for(i in numeros.indices) {
                for(j in 0 until numeros.size - 1) {
                    if(numeros[j] > numeros[j + 1]) {
                        aux = numeros[j]
                        numeros[j] = numeros[j + 1]
                        numeros[j + 1] = aux
                    }
                }
            }
        }
    }

    fun mostrarResultado(numeros: IntArray?) {
        val intent = Intent(this, VerResultado::class.java)
        intent.putExtra("numeros", numeros)
        startActivity(intent)
    }

}
