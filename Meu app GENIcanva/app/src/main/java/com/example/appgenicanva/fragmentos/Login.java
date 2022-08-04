package com.example.appgenicanva.fragmentos;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.appgenicanva.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    private EditText edit_email, edit_senha;
    ImageView facebook;
    private Button botao_entrar;
    private ProgressBar progressBar;
    String[] mensagens = {"Não  Se Esqueça de Preencher todos os campos", "Login Efetuado com Sucesso!"};
    private Button button;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);


        //Logar com Facebook
        facebook = findViewById(R.id.facebook_logo);
        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //login to facebook
            }
        });


        //Ir Para a tela Inicial
        button = findViewById(R.id.irtelacomovaifuncionar);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openTelacomovaifuncionar();
            }
        });

        botao_entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edit_email.getText().toString();
                String senha = edit_senha.getText().toString();

                if (email.isEmpty() || senha.isEmpty()) {

                    Snackbar snackbar = Snackbar.make(view, mensagens[0],Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.YELLOW);
                    snackbar.show();

                } else {
                    AutenticarUsuario(view);
                }
            }
        });
    }

    private void AutenticarUsuario (View view) {
        String email = edit_email.getText().toString();
        String senha = edit_senha.getText().toString();

        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {
                    progressBar.setVisibility(View.VISIBLE);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            TelaPrincipal();

                        }
                    }, 3000);
                } else {
                    String erro;
                    try {
                        throw task.getException();

                    } catch (Exception e) {
                        erro = "Ops! Erro ao Logar";
                    }
                    Snackbar snackbar = Snackbar.make(view, erro,Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.GREEN);
                    snackbar.setTextColor(Color.WHITE);
                    snackbar.show();
                }

            }
        });

    }


    // Serve para quando o Usuario sair do aplicativo voltar na tela principal e não da de login denovo

    //Lógica: Se o Usuário for diferente de nulo, vai retornar para a tela principal
    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser usuarioAtual = FirebaseAuth.getInstance().getCurrentUser();
        if (usuarioAtual != null) {   //!= Significa "Diferente"
            TelaPrincipal();
        }

    }

    private void TelaPrincipal() {
        Intent intent = new Intent (Login.this, ComoFuncionar.class);
        startActivity(intent);
        finish();
    }




    private void openTelacomovaifuncionar() {

        Intent intent = new Intent(this, ComoFuncionar.class );
        startActivity(intent);

        edit_email = findViewById(R.id.email_cadastro);
        edit_senha = findViewById(R.id.senha_cadastro);
        botao_entrar = findViewById(R.id.irtelacomovaifuncionar);
    }
}