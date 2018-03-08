package sdt.myapp.lab1.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import butterknife.BindColor;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import sdt.myapp.lab1.R;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "sdt";
    private static final int REQUEST_CHANGE_TITLE_CODE = 1;
    private static final int REQUEST_CHANGE_BACKGROUND_CODE = REQUEST_CHANGE_TITLE_CODE + 1;
    public static final String TITLE_COLOR = "color";
    public static final String TITLE_CONTENT = "content";

    private int currentTitleColor = -1;

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.image_background)
    ImageView imgBackground;

    //<- Resource ->
    @BindColor(R.color.pink)
    int colorDefault;
    @BindString(R.string.text_title_default)
    String titleDefault;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        getSupportActionBar().setTitle("MainActivity");

        tvTitle.setText(titleDefault);
        tvTitle.setTextColor(colorDefault);
        currentTitleColor = colorDefault;
    }

    @OnClick(R.id.button_change_title)
    public void startChangeTitleActivity() {
        Intent intent = new Intent(this, ChangeTitleActivity.class);
        intent.putExtra(TITLE_COLOR, currentTitleColor);
        intent.putExtra(TITLE_CONTENT, tvTitle.getText().toString());
        startActivityForResult(intent, REQUEST_CHANGE_TITLE_CODE);
    }

    @OnClick(R.id.button_change_background)
    public void startChangeBackgroundActivity() {
        Intent intent = new Intent(this, ChangeBackgroundActivity.class);
        startActivityForResult(intent, REQUEST_CHANGE_BACKGROUND_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CHANGE_TITLE_CODE && resultCode == RESULT_OK) {
            String text = data.getStringExtra(ChangeTitleActivity.TITLE_TEXT_RESULT);
            int color = data.getIntExtra(ChangeTitleActivity.COLOR_RESULT, currentTitleColor);

            if (!text.isEmpty()) tvTitle.setText(text);
            currentTitleColor = color;
            tvTitle.setTextColor(color);

        } else if (requestCode == REQUEST_CHANGE_BACKGROUND_CODE && resultCode == RESULT_OK) {
            int image = data.getIntExtra(ChangeBackgroundActivity.IMAGE_CHOSE, -1);
            if (image != -1) {
                Picasso.with(this).load(image).into(imgBackground);
            } else {
                Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
