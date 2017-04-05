package sih.com.naksh.join;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import sih.com.naksh.R;
import sih.com.naksh.config.AppController;

/**
 * Created by dell on 01-04-2017.
 */

public class RegisterActivity extends AppCompatActivity {
    @BindView(R.id.uname)
    EditText uname;
    @BindView(R.id.enumm)
    EditText enumm;
    @BindView(R.id.reg)
    Button reg;
    @BindView(R.id.email)
    EditText email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        registerUser();
    }

    private void registerUser() {
        final ProgressDialog loading = ProgressDialog.show(RegisterActivity.this,"Loading...","Please wait...",false,false);
        String tag_string_req = "req_register";

        StringRequest postReq = new StringRequest(Request.Method.GET, "http://192.168.1.105/taskmanager/v1/clglist", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                loading.dismiss();
                try {
                    JSONObject object = new JSONObject(response);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                loading.dismiss();
                Toast.makeText(RegisterActivity.this,"Network is too slow! Try Again", Toast.LENGTH_SHORT).show();
            }
        }){
        };
        AppController.getInstance().addToRequestQueue(postReq , tag_string_req);
    }
}
