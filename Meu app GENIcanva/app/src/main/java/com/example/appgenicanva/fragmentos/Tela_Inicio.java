package com.example.appgenicanva.fragmentos;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.example.appgenicanva.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class Tela_Inicio extends AppCompatActivity {




    BottomNavigationView bottomNavigationView;

    HomeFragment homeFragment = new HomeFragment();
    CriatividadeFragment criatividadeFragment = new CriatividadeFragment();
    DesignFragment designFragment = new DesignFragment();
    PersuasaoFragment persuasaoFragment = new PersuasaoFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_inicio_navegacao);


        bottomNavigationView = findViewById(R.id.bottom_nagivation);

        getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, homeFragment).commit();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.pagina_1:
                        getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, homeFragment).commit();
                return true;



                        case R.id.pagina_2:
                            getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, criatividadeFragment).commit();
                            return true;

                            case R.id.pagina_3:
                                getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, designFragment).commit();
                                return true;



                                case R.id.pagina_4:
                                    getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, persuasaoFragment).commit();
                                    return true;

                }
                return false;
            }
        });
    }




}



