package sdt.myapp.lab1.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import sdt.myapp.lab1.R;
import sdt.myapp.lab1.adapter.ImageAdapter;

public class ChangeBackgroundActivity extends AppCompatActivity implements ImageAdapter.ItemClickListener {

    public static final String IMAGE_DETAIL = "img_detail";
    public static final String IMAGE_CHOSE = "img_chose";
    private static final int REQUEST_DETAIL_CODE = 12;

    @BindView(R.id.list_image)
    RecyclerView mRecyclerView;

    private List<Integer> listImages;
    private int chosedImage = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_background);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        getSupportActionBar().setTitle("ChangeBackgroundActivity");

        mRecyclerView.setHasFixedSize(true);
        GridLayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        listImages = new ArrayList<>();
        listImages.addAll(Arrays.asList(R.drawable.img_1, R.drawable.img_2, R.drawable.img_3,
                R.drawable.img_4, R.drawable.img_5, R.drawable.img_6));

        ImageAdapter adapter = new ImageAdapter(this, listImages, this);
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(int image) {
        chosedImage = image;
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(IMAGE_DETAIL, image);
        startActivityForResult(intent, REQUEST_DETAIL_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_DETAIL_CODE && resultCode == RESULT_OK) {
            String tag = data.getStringExtra(DetailActivity.RESULT_ACTION);
            if (!tag.isEmpty()) {
                if (tag.equals(DetailActivity.DETAIL_OK)) {
                    Intent intent = new Intent(this, MainActivity.class);
                    intent.putExtra(IMAGE_CHOSE, chosedImage);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        }
    }
}
