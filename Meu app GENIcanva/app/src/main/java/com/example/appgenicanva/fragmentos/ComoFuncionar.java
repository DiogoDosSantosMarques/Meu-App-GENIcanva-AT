package com.example.appgenicanva.fragmentos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.appgenicanva.R;

public class ComoFuncionar extends AppCompatActivity implements View.OnClickListener {



    public CardView  genicanva, pratiquetela, gamequiz, estrategias, apresentacao, prosseguir;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.como_vai_funcionar);

        genicanva = (CardView) findViewById(R.id.geni_canva);
        pratiquetela = (CardView) findViewById(R.id.pratique_tela);
        gamequiz = (CardView) findViewById(R.id.game_quiz);
        estrategias = (CardView) findViewById(R.id.estratégias);
        apresentacao = (CardView)  findViewById(R.id.apresentacoes);
        prosseguir = (CardView)  findViewById(R.id.prosseguir);



        genicanva.setOnClickListener(this);
        pratiquetela.setOnClickListener(this);
        gamequiz.setOnClickListener(this);
        estrategias.setOnClickListener(this);
        apresentacao.setOnClickListener(this);
        prosseguir.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        Intent i;

        switch (v.getId()){
            case R.id.genicanva:
                i = new Intent(this, Geni_mais_Canva.class);
                startActivity(i);
                break;

            case R.id.pratique_tela :
                i = new Intent(this, Pratique_Tela.class);
                startActivity(i);
                break;

            case R.id.game_quiz:
                i = new Intent(this, GameQuiz.class);
                startActivity(i);
                break;

            case R.id.estratégias:
                i = new Intent(this, Estrategias.class);
                startActivity(i);
                break;

            case R.id.apresentacoes:
                i = new Intent(this, Apresentacao.class);
                startActivity(i);
                break;


            case R.id.prosseguir:
                i = new Intent(this, Tela_Inicio.class);
                startActivity(i);
                break;
        }

    }
}
