package sih.com.naksh.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import sih.com.naksh.R;
import sih.com.naksh.model.College;

/**
 * Created by dell on 23-03-2017.
 */

public class CollegeAdapter extends RecyclerView.Adapter<CollegeAdapter.MyViewHolder> {
    private List<College> collegeList;
    private Context context;

    public CollegeAdapter(List<College> collegeList, Context context) {
        this.collegeList = collegeList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_clg, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        College college = collegeList.get(position);
        holder.collegeName.setText(college.getClg_name());
        Picasso.with(context).load(college.getImage()).into(holder.thumbnail);
    }

    @Override
    public int getItemCount() {
        return collegeList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.college_namme)
        TextView collegeName;
        @BindView(R.id.thumbnail)
        ImageView thumbnail;
        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
