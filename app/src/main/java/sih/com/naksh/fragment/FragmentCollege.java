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
import butterknife.Unbinder;
import sih.com.naksh.Adapter.CollegeAdapter;
import sih.com.naksh.R;
import sih.com.naksh.ThirdActivity;
import sih.com.naksh.config.AppController;
import sih.com.naksh.helper.RecyclerTouchListener;
import sih.com.naksh.model.College;

/**
 * Created by dell on 23-03-2017.
 */

public class FragmentCollege extends Fragment {

    @BindView(R.id.recycler_view_college)
    RecyclerView recyclerVieww;
    Unbinder unbinder;
    private List<College> collegeList = new ArrayList<>();
    private CollegeAdapter collegeAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragmentcollege, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerVieww.setLayoutManager(mLayoutManager);
        collegeAdapter = new CollegeAdapter(collegeList,getContext());
        recyclerVieww.setAdapter(collegeAdapter);
        recyclerVieww.addOnItemTouchListener(new RecyclerTouchListener(getContext(), recyclerVieww, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                College college = collegeList.get(position);
                Intent intent = new Intent(getContext(), ThirdActivity.class);
                intent.putExtra("college",college);
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
        final ProgressDialog loading = ProgressDialog.show(getContext(), "Loading...", "Please wait...", false, false);
        String tag_string_req = "req_register";

        StringRequest postReq = new StringRequest(Request.Method.GET, "http://192.168.1.104/taskmanager/v1/clglist", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                loading.dismiss();
                try {
                    JSONObject object = new JSONObject(response);
                    JSONArray jsonArray = object.getJSONArray("feeds");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        try {
                            JSONObject obj = jsonArray.getJSONObject(i);
                            College college = new College();
                            college.setId(obj.getInt("id"));
                            college.setClg_name(obj.getString("clg_name"));
                            college.setImage(obj.getString("clg_pic").replace("\\", ""));
                            college.setId(obj.getInt("id"));
                            collegeList.add(college);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        collegeAdapter.notifyDataSetChanged();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                loading.dismiss();
                Toast.makeText(getContext(), "Network is too slow! Try Again", Toast.LENGTH_SHORT).show();
            }
        }) {
        };
        AppController.getInstance().addToRequestQueue(postReq, tag_string_req);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
