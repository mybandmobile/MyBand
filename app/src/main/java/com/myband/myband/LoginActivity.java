package com.myband.myband;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.myband.myband.asyncTask.LoginTask;
import com.myband.myband.dao.UserDAO;
import com.myband.myband.model.User;

import org.parceler.Parcels;

import java.util.Objects;
import java.util.concurrent.ExecutionException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.edtLogin)
    EditText mEdtLogin;

    @BindView(R.id.edtPassword)
    EditText mEdtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btnSignIn, R.id.btnSignUp, R.id.btnMaps})
    void onItemClicked(View view) {
        Intent it;
        User user;
        UserDAO dao;
        boolean error = false;

        switch (view.getId()) {
            case R.id.btnSignIn:
                if (Objects.equals(mEdtLogin.getText().toString(), "")) {
                    error = true;
                }
                if (Objects.equals(mEdtPassword.getText().toString(), "")) {
                    error = true;
                }
                if (!error) {
                    dao = new UserDAO(this);
                    user = new User();
                    user.setLogin(mEdtLogin.getText().toString());
                    user.setPassword(mEdtPassword.getText().toString());
                    try {
                        user = new LoginTask().execute(user).get();
                        if (user == null) {
                            Toast.makeText(this, getResources().getString(R.string.errorlogin), Toast.LENGTH_LONG).show();
                        } else {
                            if (user.getStatusCode() == 1) {
                                dao.update(user);
                                it = new Intent(this, MainActivity.class);
                                it.putExtra("user", Parcels.wrap(user));
                                startActivity(it);
                                finish();
                            } else {
                                Toast.makeText(this, getResources().getString(R.string.invalidpassword), Toast.LENGTH_LONG).show();
                            }
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        Toast.makeText(this, getResources().getString(R.string.errorlogin), Toast.LENGTH_LONG).show();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                        Toast.makeText(this, getResources().getString(R.string.errorlogin), Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(this, getResources().getString(R.string.invalidinputs), Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.btnSignUp:
                it = new Intent(this, CreateUserActivity.class);
                startActivity(it);
                break;
            case R.id.btnMaps:
                it = new Intent(this, MapsActivity.class);
                startActivity(it);
                break;
        }
    }
}
