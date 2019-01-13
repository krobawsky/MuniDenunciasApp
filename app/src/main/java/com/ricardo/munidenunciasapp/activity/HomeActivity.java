package com.ricardo.munidenunciasapp.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.ricardo.munidenunciasapp.R;
import com.ricardo.munidenunciasapp.adapter.DenunciasAdapter;
import com.ricardo.munidenunciasapp.models.Denuncia;
import com.ricardo.munidenunciasapp.service.ApiService;
import com.ricardo.munidenunciasapp.service.ApiServiceGenerator;

import java.util.List;

import layout.List2Fragment;
import layout.RegisterFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private TextView txtName;
    private TextView txtEmail;

    private String id;

    // SharedPreferences
    private SharedPreferences sharedPreferences;

    private static final String TAG = HomeActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //con esto agregamos datos en el header del menu-------------------------------
        View hView = navigationView.getHeaderView(0);
        txtName = (TextView) hView.findViewById(R.id.name);

        // init SharedPreferences
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        // get id from SharedPreferences
        id = sharedPreferences.getString("id", null);
        Log.d(TAG, "usuario_id: " + id);
        String user_nombre = sharedPreferences.getString("user_nombre", null);
        Log.d(TAG, "user_nombre: " + user_nombre);

        txtName.setText(user_nombre);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        List2Fragment fragment = new List2Fragment();
        transaction.replace(R.id.content, fragment);
        transaction.commit();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if (id == R.id.nav_list) {
            List2Fragment fragment = new List2Fragment();
            transaction.replace(R.id.content, fragment);
            transaction.commit();

        } else if (id == R.id.nav_new) {
            RegisterFragment fragment = new RegisterFragment();
            transaction.replace(R.id.content, fragment);
            transaction.commit();

        } else if (id == R.id.nav_send) {
            logoutUser();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void logoutUser() {

        // remove from SharedPreferences
        SharedPreferences.Editor editor = sharedPreferences.edit();
        boolean success = editor.putBoolean("islogged", false).commit();
        boolean success2 = editor
                .putString("user_nombre", "")
                .commit();

        // Launching the login activity
        Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();

    }
}
