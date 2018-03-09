package sdt.myapp.lab1.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import sdt.myapp.lab1.R;

public class DetailActivity extends AppCompatActivity {

    public static final String DETAIL_CANCEL = "";
    public static final String DETAIL_OK = "ok";
    public static final String RESULT_ACTION = "result_detail";

    @BindView(R.id.image)
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        getSupportActionBar().setTitle("DetailActivity");

        Intent intent = getIntent();
        if (intent != null) {
            int image = intent.getIntExtra(ChangeBackgroundActivity.IMAGE_DETAIL, -1);
            if (image != -1) {
                Picasso.with(this)
                        .load(image)
                        .fit()
                        .centerCrop()
                        .into(imageView);
            } else {
                Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @OnClick({R.id.button_cancel, R.id.button_ok})
    public void onClick(Button button) {
        switch (button.getId()) {
            case R.id.button_cancel:
                result(DETAIL_CANCEL);
                break;
            case R.id.button_ok:
                result(DETAIL_OK);
                break;

        }
    }

    private void result(String tag) {
        Intent intent = new Intent(this, ChangeBackgroundActivity.class);
        intent.putExtra(RESULT_ACTION, tag);
        setResult(RESULT_OK, intent);
        this.finish();
    }
}
