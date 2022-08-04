package com.example.appgenicanva.fragmentos;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.appgenicanva.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Cadastro extends AppCompatActivity {

    String usuarioID;

    private EditText edit_nome_apelido,edit_email,edit_senha;
    String[] mensagens = {"Não  Se Esqueça de Preencher todos os campos", "Cadastro Feito com Sucesso!"};



    private Button button_cadastrar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro);

        button_cadastrar = findViewById(R.id.docadastroprologin);
        button_cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opendocadastropraologin();
            }
        });


        iniciarComponentes();

        button_cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               String nome_apelido = edit_nome_apelido.getText().toString();
                String email = edit_email.getText().toString();
                String senha = edit_senha.getText().toString();

                if (nome_apelido.isEmpty() || email.isEmpty() || senha.isEmpty()) {
                    Snackbar snackbar = Snackbar.make(view, mensagens[0],Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.YELLOW);
                    snackbar.show();
                } else {
                    CadastrarUsuario(view);
                }
            }
        });
    }

    private void CadastrarUsuario(View view) {

        String email = edit_email.getText().toString();
        String senha = edit_senha.getText().toString();

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()){

                    SalvarDadosUsuario();


                    Snackbar snackbar = Snackbar.make(view, mensagens[1],Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.GREEN);
                    snackbar.setTextColor(Color.WHITE);
                    snackbar.show();

                }else {
                    String erro;
                    try {

                        throw task.getException();

                    }catch(FirebaseAuthWeakPasswordException e) {

                        erro = "Ops, a senha precisa ter no mínimo 6 caracteres";

                    } catch (FirebaseAuthUserCollisionException e) {

                        erro = "Essa conta ja foi cadastrada, Tente outro Nome!";

                    }catch(FirebaseAuthInvalidCredentialsException e) {

                        erro = "E-mail inválido";


                    } catch (Exception e ) {
                        erro = "Erro ao cadastrar usuário";
                    }

                    Snackbar snackbar = Snackbar.make(view, erro,Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.RED);
                    snackbar.show();
                }

            }
        });




    }

    private void opendocadastropraologin() {
        Intent intent = new Intent(this, Login.class );
        startActivity(intent);
    }

    private void SalvarDadosUsuario(){
        String nome = edit_nome_apelido.getText().toString();

        FirebaseFirestore database = FirebaseFirestore.getInstance();

        Map<String, Object> usuarios = new HashMap<>();
        usuarios.put("nome_Apelido", nome);

        usuarioID = FirebaseAuth.getInstance().getCurrentUser().getUid();

        DocumentReference documentReference = database.collection("Usuarios").document(usuarioID);
        documentReference.set(usuarios).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Log.d("database", "Sucesso ao Salvar os Dados");

            }
        })
        .addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Log.d("database_error", "Erro ao Salvar os dados" + e.toString());

            }
        });
    }


    private void iniciarComponentes() {
        edit_nome_apelido = findViewById(R.id.nome_apelido);
        edit_email = findViewById(R.id.email_cadastro);
        edit_senha = findViewById(R.id.senha_cadastro);
        button_cadastrar = findViewById(R.id.docadastroprologin);
    }

}