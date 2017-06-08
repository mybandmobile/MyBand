package com.myband.myband;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.myband.myband.asyncTask.UserTask;
import com.myband.myband.dao.UserDAO;
import com.myband.myband.model.User;

import org.parceler.Parcels;

import java.util.concurrent.ExecutionException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.txtUserName)
    TextView mTxtUserName;

    @BindView(R.id.txtLogin)
    TextView mTxtLogin;

    @BindView(R.id.txtCategory)
    TextView mTxtCategory;

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        TabHost host = (TabHost) findViewById(R.id.tabHost);
        host.setup();

        //Tab 1
        TabHost.TabSpec spec = host.newTabSpec("Tab1");
        spec.setContent(R.id.tab1);
        spec.setIndicator(getResources().getString(R.string.profile));
        host.addTab(spec);

        //Tab 2
        spec = host.newTabSpec("Tab2");
        spec.setContent(R.id.tab2);
        spec.setIndicator(getResources().getString(R.string.events));
        host.addTab(spec);

        user = Parcels.unwrap(getIntent().getParcelableExtra("user"));

        mTxtUserName.setText(user.getUserName());
        mTxtLogin.setText(user.getLogin());
        switch (user.getCategory().getId().intValue()) {
            case 1:
                mTxtCategory.setText(getResources().getString(R.string.typeArtist));
                break;
            case 2:
                mTxtCategory.setText(getResources().getString(R.string.typeBand));
                break;
            case 3:
                mTxtCategory.setText(getResources().getString(R.string.typePromoter));
                break;
            default:
                mTxtCategory.setText(getResources().getString(R.string.typeArtist));
                break;
        }
    }

    @OnClick(R.id.btnMaps)
    void onItemClicked(View view) {
        Intent it;

        switch (view.getId()) {
            case R.id.btnMaps:
                it = new Intent(this, MapsActivity.class);
                it.putExtra("user", Parcels.wrap(user));
                startActivity(it);
                break;
        }
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
        }
        return super.onOptionsItemSelected(item);
    }
}
