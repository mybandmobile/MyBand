package com.myband.myband;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.myband.myband.asyncTask.LoginTask;
import com.myband.myband.dao.UserDAO;
import com.myband.myband.model.Category;
import com.myband.myband.model.User;

import org.parceler.Parcels;

import java.util.Objects;
import java.util.concurrent.ExecutionException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CreateUserActivity extends AppCompatActivity {

    @BindView(R.id.edtName)
    EditText mEdtName;

    @BindView(R.id.edtLogin)
    EditText mEdtLogin;

    @BindView(R.id.edtPassword1)
    EditText mEdtPassword1;

    @BindView(R.id.edtPassword2)
    EditText mEdtPassword2;

    Category category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnSignUp)
    void onItemClicked(View view) {
        Intent it;
        User user;
        UserDAO dao;
        boolean error = false;
        if (view.getId() == R.id.btnSignUp) {
            if (Objects.equals(mEdtLogin.getText().toString(), "")) {
                error = true;
            }
            if (Objects.equals(mEdtName.getText().toString(), "")) {
                error = true;
            }
            if (Objects.equals(mEdtPassword1.getText().toString(), "")) {
                error = true;
            }
            if (!Objects.equals(mEdtPassword1.getText().toString(), mEdtPassword2.getText().toString())) {
                error = true;
            }
            if (!error) {
                dao = new UserDAO(this);
                user = new User();
                user.setLogin(mEdtLogin.getText().toString());
                user.setPassword(mEdtPassword1.getText().toString());
                user.setUserName(mEdtName.getText().toString());
                user.setCategory(category);
                user.setAutoLogin(true);
                try {
                    user = new LoginTask().execute(user).get();
                    if (user == null) {
                        Toast.makeText(this, getResources().getString(R.string.errorlogin), Toast.LENGTH_LONG).show();
                    } else {
                        if (user.getStatusCode() == 1) {
                            dao.insert(user);
                            it = new Intent(this, MainActivity.class);
                            it.putExtra("user", Parcels.wrap(user));
                            startActivity(it);
                            finish();
                        } else {
                            // tratar esse erro!
                            Toast.makeText(this, getResources().getString(R.string.invalidinputs), Toast.LENGTH_LONG).show();
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
        }
    }

    public void onRadioButtonClicked(View view) {
        category = new Category();
        switch (view.getId()) {
            case R.id.radioArtist:
                category.setId(1L);
                break;
            case R.id.radioBand:
                category.setId(2L);
                break;
            case R.id.radioPromoter:
                category.setId(3L);
                break;
        }
    }
}
