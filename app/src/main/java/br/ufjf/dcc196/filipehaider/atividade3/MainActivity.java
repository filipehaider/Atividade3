package br.ufjf.dcc196.filipehaider.atividade3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    public enum Jogada{
        PEDRA(0), PAPEL(1), TESOURA(2), LAGARTO(3), SPOCK(4);

        private final int valor;
        Jogada(int valor){
           this.valor = valor;
       }
    }
    public enum Resultado{
        DERROTA(-1), EMPATE(0), VITORIA(1);

        private final int valor;
        Resultado(int valor){
            this.valor = valor;
        }
    }

    public static final Resultado TABELA[][] = {
        {Resultado.EMPATE, Resultado.DERROTA, Resultado.VITORIA, Resultado.VITORIA, Resultado.DERROTA},
        {Resultado.VITORIA, Resultado.EMPATE, Resultado.DERROTA, Resultado.DERROTA, Resultado.VITORIA},
        {Resultado.DERROTA, Resultado.VITORIA, Resultado.EMPATE, Resultado.VITORIA, Resultado.DERROTA},
        {Resultado.DERROTA, Resultado.VITORIA, Resultado.DERROTA, Resultado.EMPATE, Resultado.VITORIA},
        {Resultado.VITORIA, Resultado.DERROTA, Resultado.VITORIA, Resultado.DERROTA, Resultado.EMPATE},

    };



    private Integer pontosCPU = 0;
    private Integer pontosHumano = 0;

    private Button buttonPedra;
    private Button buttonPapel;
    private Button buttonTesoura;
    private Button buttonLagarto;
    private Button buttonSpock;

    private ProgressBar progressBarCPU;
    private ProgressBar progressBarHumano;
    private TextView textViewStatus;

    private Random dado = new Random();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Componentes de entrada
        buttonPedra = findViewById(R.id.buttonPedra);
        buttonPapel = findViewById(R.id.buttonPapel);
        buttonTesoura = findViewById(R.id.buttonTesoura);
        buttonLagarto = findViewById(R.id.buttonLagarto);
        buttonSpock = findViewById(R.id.buttonSpock);

        //Componentes de saída
        progressBarCPU = findViewById(R.id.progressBarCPU);
        progressBarHumano = findViewById(R.id.progressBarHumano);
        textViewStatus = findViewById(R.id.textViewStatus);
    }

    public void buttonPedraClick(View view){
        rodada(Jogada.PEDRA);
    }
    public void buttonPapelClick(View view){
        rodada(Jogada.PAPEL);
    }
    public void buttonTesouraClick(View view){
        rodada(Jogada.TESOURA);
    }
    public void buttonLagartoClick(View view){
        rodada(Jogada.LAGARTO);
    }
    public void buttonSpockClick(View view){
        rodada(Jogada.SPOCK);
    }

    public void rodada(Jogada jogada){
        Jogada jogadaCPU = jogada.values()[dado.nextInt(5)];
        switch (TABELA[jogada.valor][jogadaCPU.valor]){
            case VITORIA:
                pontosHumano += 3;
                Toast.makeText(this, "Você ganhou", Toast.LENGTH_SHORT).show();
                break;
            case EMPATE:
                pontosCPU += 1;
                pontosHumano += 1;
                Toast.makeText(this, "Empate", Toast.LENGTH_SHORT).show();
                break;
            case DERROTA:
                pontosCPU += 3;
                Toast.makeText(this, "Você perdeu", Toast.LENGTH_SHORT).show();
                break;
        }
        atualizaStatus();
    }

    private void atualizaStatus(){
     progressBarCPU.setProgress(pontosCPU);
     progressBarHumano.setProgress(pontosHumano);
     if (pontosHumano <15 && pontosCPU <15){
                textViewStatus.setText("Escolha uma opção acima. . .");
     } else
         if (pontosHumano >=15 && pontosCPU <15){
             textViewStatus.setText("Parabéns, você venceu o torneio!");
             iniciaTorneio();
         }else
         if (pontosHumano <15 && pontosCPU >=15){
             textViewStatus.setText("Perdeu !!!!!!!!!!!!!!!!!!");
             iniciaTorneio();
         }else{
             textViewStatus.setText("O torneio terminou empatado.");
             iniciaTorneio();
         }

    }

    private void iniciaTorneio() {
        pontosCPU = 0;
        pontosHumano = 0;
    }

}


