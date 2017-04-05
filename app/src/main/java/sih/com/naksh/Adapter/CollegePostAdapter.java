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
import sih.com.naksh.model.Post;

/**
 * Created by dell on 02-04-2017.
 */

public class CollegePostAdapter extends RecyclerView.Adapter<CollegePostAdapter.MyViewHolder> {
    private List<Post> collegeList;
    private Context context;

    public CollegePostAdapter(List<Post> collegeList, Context context) {
        this.collegeList = collegeList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_post, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Post post = collegeList.get(position);
        holder.name.setText(post.getClg_name());
        holder.txtStatusMsg.setText(post.getClg_cap());
        holder.timestamp.setText(post.getTime_stamp());
        Picasso.with(context).load(post.getProfilepic()).into(holder.profilePic);
        Picasso.with(context).load(post.getThumbnail()).into(holder.thumbnail);
    }

    @Override
    public int getItemCount() {
        return collegeList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.profilePic)
        ImageView profilePic;
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.timestamp)
        TextView timestamp;
        @BindView(R.id.txtStatusMsg)
        TextView txtStatusMsg;
        @BindView(R.id.thumbnail)
        ImageView thumbnail;
        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}

