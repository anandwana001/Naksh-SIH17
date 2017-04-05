package sih.com.naksh.join;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import sih.com.naksh.MainActivity;
import sih.com.naksh.R;
import sih.com.naksh.helper.SessionManager;

/**
 * Created by dell on 01-04-2017.
 */

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.uname)
    EditText uname;
    @BindView(R.id.pass)
    EditText pass;
    @BindView(R.id.login)
    Button login;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private SessionManager session;
    private String usrname, passwrd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        session = new SessionManager(getBaseContext());
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void login(View view) {
        usrname = uname.getText().toString();
        passwrd = pass.getText().toString();
        if (!usrname.isEmpty() && !passwrd.isEmpty()){
            if (usrname.contains("sih17") && passwrd.contains("sih17")) {
                session.setLogin(true);
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }else {
                Toast.makeText(getApplicationContext(), "Please enter the right credentials!", Toast.LENGTH_LONG).show();
            }
        }else {
            Toast.makeText(getApplicationContext(), "Please enter the credentials!", Toast.LENGTH_LONG).show();
        }
    }
}
