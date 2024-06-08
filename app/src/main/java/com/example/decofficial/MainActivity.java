package com.example.decofficial;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.decofficial.ebook.EbookActivity;
import com.example.decofficial.pyq.PYQActivity;
import com.example.decofficial.routine.RoutineActivity;
import com.example.decofficial.syllabus.SyllabusActivity;
import com.example.decofficial.ui.login.LoginActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.decofficial.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private ActivityMainBinding binding;

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;

    private NavigationView navigationView;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notices, R.id.navigation_gallery)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_drawer_view);

        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.start,R.string.close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        navigationView.setNavigationItemSelectedListener(this);

        firebaseAuth = FirebaseAuth.getInstance();

    }
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//
//        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
//        if (firebaseUser!=null){
//
//        }else {
//            startActivity(new Intent(this, LoginActivity.class));
//            finish();
//        }
//    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(toggle.onOptionsItemSelected(item)){
            return true;
        }
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        if (itemId == R.id.nav_drawer_video) {
            startActivity(new Intent(this, VideoLecturesActivity.class));
        } else if (itemId == R.id.nav_drawer_ebook) {
            startActivity(new Intent(this, EbookActivity.class));
        } else if (itemId == R.id.nav_drawer_pyq) {
            startActivity(new Intent(this, PYQActivity.class));
        } else if (itemId == R.id.nav_drawer_about) {
            startActivity(new Intent(this, AboutActivity.class));
        } else if (itemId == R.id.nav_drawer_syllabus) {
            startActivity(new Intent(this, SyllabusActivity.class));
        } else if (itemId == R.id.nav_drawer_routine) {
            startActivity(new Intent(this, RoutineActivity.class));
        } else if (itemId == R.id.nav_drawer_developer) {
            startActivity(new Intent(this, DevelopersActivity.class));
        } else if (itemId == R.id.nav_drawer_website) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://dec.ac.in/")));
        } else if (itemId == R.id.nav_drawer_logoutBtn) {
            startActivity(new Intent(this, firstActivity.class));
            firebaseAuth.signOut();
        }
        return true;
    }
}