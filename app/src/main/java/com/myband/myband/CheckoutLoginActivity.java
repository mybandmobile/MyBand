package com.myband.myband;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.myband.myband.asyncTask.LoginTask;
import com.myband.myband.dao.UserDAO;
import com.myband.myband.model.User;

import org.parceler.Parcels;

import java.util.concurrent.ExecutionException;

public class CheckoutLoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        UserDAO dao = new UserDAO(this);
        User user = dao.selectAutoLogin();
        Intent it = null;

        if (user == null) {
            it = new Intent(this, LoginActivity.class);
            startActivity(it);
            finish();
        } else {
            //verifica internet
            try {
                user = new LoginTask().execute(user).get();
                if (user == null || user.getStatusCode() != 1) {
                    it = new Intent(this, LoginActivity.class);
                } else {
                    dao.update(user, true);
                    it = new Intent(this, MainActivity.class);
                    it.putExtra("user", Parcels.wrap(user));
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                it = new Intent(this, MainActivity.class);
                it.putExtra("user", Parcels.wrap(user));
            } catch (ExecutionException e) {
                e.printStackTrace();
                it = new Intent(this, MainActivity.class);
                it.putExtra("user", Parcels.wrap(user));
            } finally {
                startActivity(it);
                finish();
            }
        }
    }
}
