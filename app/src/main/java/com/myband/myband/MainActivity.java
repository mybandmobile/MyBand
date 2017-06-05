package com.myband.myband;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TabHost;
import android.widget.TextView;

import com.myband.myband.dao.UserDAO;
import com.myband.myband.model.User;

import org.parceler.Parcels;

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
        mTxtCategory.setText(user.getCategory().getName());
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
        switch (item.getItemId()) {
            case R.id.action_logout:
                UserDAO dao = new UserDAO(this);
                dao.setAutoLoginFalse();
                it = new Intent(this, LoginActivity.class);
                startActivity(it);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
