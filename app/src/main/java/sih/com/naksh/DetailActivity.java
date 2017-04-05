package sih.com.naksh;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import sih.com.naksh.model.Post;

/**
 * Created by dell on 01-04-2017.
 */

public class DetailActivity extends AppCompatActivity {

    @BindView(R.id.thumbnail)
    ImageView thumbnail;
    @BindView(R.id.clg_name)
    TextView clgName;
    @BindView(R.id.clg_ven)
    TextView clgVen;
    @BindView(R.id.clg_time)
    TextView clgTime;
    @BindView(R.id.clg_cap)
    TextView clgCap;
    @BindView(R.id.clg_det)
    TextView clgDet;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private Post post;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        post = getIntent().getExtras().getParcelable("post");
        clgName.setText(post.getClg_name());
        clgCap.setText(post.getClg_cap());
        clgDet.setText(post.getClg_text());
        clgVen.setText(post.getVenue());
        clgTime.setText(post.getTiming());
        Picasso.with(DetailActivity.this).load(post.getThumbnail()).into(thumbnail);
    }
}
