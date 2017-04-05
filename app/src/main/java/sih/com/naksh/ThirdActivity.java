package sih.com.naksh;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import sih.com.naksh.Adapter.CollegePostAdapter;
import sih.com.naksh.config.AppController;
import sih.com.naksh.helper.RecyclerTouchListener;
import sih.com.naksh.model.College;
import sih.com.naksh.model.Post;

/**
 * Created by dell on 02-04-2017.
 */

public class ThirdActivity extends AppCompatActivity {

    @BindView(R.id.recycler_view_postlist)
    RecyclerView recyclerView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private List<Post> postList = new ArrayList<>();
    private CollegePostAdapter postAdapter;
    private College college;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.collegelist);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        college = getIntent().getExtras().getParcelable("college");
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(ThirdActivity.this);
        recyclerView.setLayoutManager(mLayoutManager);
        postAdapter = new CollegePostAdapter(postList, ThirdActivity.this);
        recyclerView.setAdapter(postAdapter);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(ThirdActivity.this, recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Post post = postList.get(position);
                Intent intent = new Intent(ThirdActivity.this, DetailActivity.class);
                intent.putExtra("post", post);
                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {
            }
        }));
        display();
    }

    private void display() {
        final ProgressDialog loading = ProgressDialog.show(ThirdActivity.this, "Loading...", "Please wait...", false, false);
        String tag_string_req = "req_register";

        StringRequest postReq = new StringRequest(Request.Method.POST, "http://192.168.1.104/taskmanager/v1/getclgpost", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                loading.dismiss();
                try {
                    JSONObject object = new JSONObject(response);
                    JSONArray jsonArray = object.getJSONArray("feeds");
                    if(jsonArray.length()<=0){
                        Toast.makeText(getBaseContext(),"No Post from this College",Toast.LENGTH_LONG).show();
                    }else{
                        for (int i = 0; i < jsonArray.length(); i++) {
                            try {
                                JSONObject obj = jsonArray.getJSONObject(i);
                                Post post = new Post();
                                post.setClg_name(obj.getString("clg_name"));
                                post.setClg_cap(obj.getString("caption"));
                                post.setClg_text(obj.getString("details"));
                                post.setTime_stamp(obj.getString("timeStamp"));
                                post.setVenue(obj.getString("venue"));
                                post.setTiming(obj.getString("timing"));
                                post.setThumbnail(obj.getString("pimage").replace("\\", ""));
                                post.setProfilepic(obj.getString("clg_pic").replace("\\", ""));
                                postList.add(post);
                                postAdapter.notifyDataSetChanged();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                loading.dismiss();
                Toast.makeText(ThirdActivity.this, "Network is too slow! Try Again", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("id", "" + college.getId());
                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(postReq, tag_string_req);
    }
}
