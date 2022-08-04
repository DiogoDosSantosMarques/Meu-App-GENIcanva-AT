package com.example.appgenicanva;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.text.TextPaint;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.appgenicanva.fragmentos.Cadastro;
import com.example.appgenicanva.fragmentos.Login;

public class MainActivity extends AppCompatActivity {
    private Button button;



    TextView genicanva;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

                //botão pra ir cadastro
        button = findViewById(R.id.ircadastro);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCadastro();
            }
        });




                 //Botão pra ir Login
        button = findViewById(R.id.irlogin);
        button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               openLogin();
           }
       });




        genicanva = findViewById(R.id.genicanva);
        setTextGradientColor();


                        // Animação do main_activity
        ConstraintLayout constraintLayout = findViewById(R.id.layoutPrincipal);

        AnimationDrawable animationDrawable = (AnimationDrawable) constraintLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2500);
        animationDrawable.setExitFadeDuration(5000);
        animationDrawable.start();

                            // Fim da Animação
    }



    private void openCadastro() {
        Intent intent = new Intent(this, Cadastro.class);
        startActivity(intent);

    }

    private void openLogin() {

        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }


    // Gradiente no Text View
    private void setTextGradientColor() {
        TextPaint paint = genicanva.getPaint();
        float width = paint.measureText("GENICANVA!");

        Shader shader = new LinearGradient(0,0,width,genicanva.getTextSize(),
                new int[] {
                        Color.parseColor("#FF6200EE"),
                        Color.parseColor("#FFFFFFFF"),
                        Color.parseColor("#FF6200EE"),
                        Color.parseColor("FFFFFFFF"),
                        Color.parseColor("#FF6200EE"),


                }, null, Shader.TileMode.CLAMP);
        genicanva.getPaint().setShader(shader);
    }

                        // Fim do Gradient no Text View
}