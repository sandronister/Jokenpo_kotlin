package br.com.sandroni.jokenpo

import android.media.MediaPlayer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    val PEDRA = 0
    val PAPEL = 1
    val TESOURA = 2

    private var mediaPlayer:MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mediaPlayer = MediaPlayer.create(this,R.raw.jokenpo)

        ivPedra.setOnClickListener {
            realizarJogada(PEDRA)
        }

        ivPapel.setOnClickListener {
            realizarJogada(PAPEL)
        }

        ivTesoura.setOnClickListener {
            realizarJogada(TESOURA)
        }
    }

    private fun mudaImagem(ivPlayer: ImageView, jogada: Int) {
        when (jogada) {
            PEDRA -> {
                setImagem(R.drawable.pedra, ivPlayer)
            }
            TESOURA -> {
                setImagem(R.drawable.tesoura, ivPlayer)
            }
            PAPEL -> {
                setImagem(R.drawable.papel, ivPlayer)
            }
        }
    }

    private fun setImagem(icone: Int, ivPlayer: ImageView) {
        ivPlayer.setImageDrawable(
            ContextCompat.getDrawable(
                this, icone
            )
        )
    }

    fun realizarJogada(jogadaPlayer: Int) {

        mediaPlayer?.start();
        val jogadaAndroid = Random().nextInt(3)

        tvUltJogada.text="Última Jogada"
        mudaImagem(ivVoce, jogadaPlayer)
        mudaImagem(ivAndroid, jogadaAndroid)

        when (jogadaPlayer) {
            PEDRA -> {
                when (jogadaAndroid) {
                    PEDRA -> {
                        empatou()
                    }
                    PAPEL -> {
                        perdeu()
                    }
                    TESOURA -> {
                        ganhou()
                    }
                }
            }

            PAPEL -> {
                when (jogadaAndroid) {
                    PEDRA -> {
                        ganhou()
                    }
                    PAPEL -> {
                        empatou()
                    }
                    TESOURA -> {
                        perdeu()
                    }
                }
            }

            TESOURA -> {
                when (jogadaAndroid) {
                    PEDRA -> {
                        perdeu()
                    }
                    PAPEL -> {
                        ganhou()
                    }
                    TESOURA -> {
                        empatou()
                    }
                }
            }
        }
    }

    private fun perdeu() {
        tvResultado.text = "Perdeu"
    }

    private fun empatou() {
        tvResultado.text = "Empatou"
    }

    private fun ganhou() {
        tvResultado.text = "Ganhou"
    }
}
