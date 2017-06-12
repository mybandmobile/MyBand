package com.myband.myband;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.myband.myband.asyncTask.UserTask;
import com.myband.myband.dao.UserDAO;
import com.myband.myband.model.User;

import org.parceler.Parcels;

import java.util.concurrent.ExecutionException;


public class MainActivity extends AppCompatActivity {

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText(getResources().getString(R.string.profile)));
        tabLayout.addTab(tabLayout.newTab().setText(getResources().getString(R.string.events)));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        user = Parcels.unwrap(getIntent().getParcelableExtra("user"));
        Bundle bundle = new Bundle();
        bundle.putParcelable("user", Parcels.wrap(user));

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount(), bundle);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent it;
        UserDAO dao;
        Bundle bundle;
        switch (item.getItemId()) {

            case R.id.action_update:
                bundle = new Bundle();
                bundle.putParcelable("user", Parcels.wrap(user));
                bundle.putInt("cod", User.updateAccount);
                try {
                    user = new UserTask().execute(bundle).get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                break;

            case R.id.action_delete:
                bundle = new Bundle();
                bundle.putParcelable("user", Parcels.wrap(user));
                bundle.putInt("cod", User.deleteAccount);
                try {
                    user = new UserTask().execute(bundle).get();
                    if (user != null && user.getStatusCode() == User.deleteOk) {
                        dao = new UserDAO(this);
                        dao.delete(user);
                        it = new Intent(this, LoginActivity.class);
                        startActivity(it);
                        finish();
                    } else {
                        Toast.makeText(this, getResources().getString(R.string.connectionError), Toast.LENGTH_LONG).show();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                break;

            case R.id.action_logout:
                dao = new UserDAO(this);
                dao.setAutoLoginFalse();
                it = new Intent(this, LoginActivity.class);
                startActivity(it);
                finish();
                break;
            case R.id.action_maps:
                it = new Intent(this, MapsActivity.class);
                it.putExtra("user", Parcels.wrap(user));
                startActivity(it);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}