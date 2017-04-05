package sih.com.naksh.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import sih.com.naksh.Adapter.PostAdapter;
import sih.com.naksh.DetailActivity;
import sih.com.naksh.R;
import sih.com.naksh.config.AppController;
import sih.com.naksh.helper.RecyclerTouchListener;
import sih.com.naksh.model.Post;

/**
 * Created by dell on 23-03-2017.
 */

public class FragmentPost extends Fragment {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private List<Post> postList = new ArrayList<>();
    private PostAdapter postAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragmentpost, container, false);
        ButterKnife.bind(this, rootView);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        postAdapter = new PostAdapter(postList,getContext());
        recyclerView.setAdapter(postAdapter);
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Post post = postList.get(position);
                Intent intent = new Intent(getContext(), DetailActivity.class);
                intent.putExtra("post",post);
                startActivity(intent);
            }
            @Override
            public void onLongClick(View view, int position) {
            }
        }));
        display();
        return rootView;
    }

    private void display() {
        final ProgressDialog loading = ProgressDialog.show(getContext(),"Loading...","Please wait...",false,false);
        String tag_string_req = "req_register";

        StringRequest postReq = new StringRequest(Request.Method.GET, "http://192.168.1.104/taskmanager/v1/postlist", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                loading.dismiss();
                try {
                    JSONObject object = new JSONObject(response);
                    JSONArray jsonArray = object.getJSONArray("feeds");
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
                            post.setThumbnail(obj.getString("pimage").replace("\\",""));
                            post.setProfilepic(obj.getString("clg_pic").replace("\\",""));
                            postList.add(post);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        postAdapter.notifyDataSetChanged();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                loading.dismiss();
                Toast.makeText(getContext(),"Network is too slow! Try Again", Toast.LENGTH_SHORT).show();
            }
        }){
        };
        AppController.getInstance().addToRequestQueue(postReq , tag_string_req);
    }
}
