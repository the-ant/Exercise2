package sdt.myapp.lab1.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import sdt.myapp.lab1.R;

public class ChangeTitleActivity extends AppCompatActivity {

    private static final String TAG = "settings_activity";
    public static final String TITLE_TEXT_RESULT = "text_title_result";
    public static final String COLOR_RESULT = "color_result";

    private int colorResult;

    @BindView(R.id.view_sample)
    View viewSample;
    @BindView(R.id.edt_title)
    EditText edtTitle;

    // <- Resource ->
    @BindColor(R.color.pink)
    int colorPink;
    @BindColor(R.color.blue)
    int colorBlue;
    @BindColor(R.color.green)
    int colorGreen;
    @BindColor(R.color.purple)
    int colorPurple;
    @BindColor(R.color.teal)
    int colorTeal;
    @BindColor(R.color.indigo)
    int colorIndigo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_title);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        getSupportActionBar().setTitle("ChangeTitleActivity");

        Intent intent = this.getIntent();
        if (intent != null) {
            String content = intent.getStringExtra(MainActivity.TITLE_CONTENT);
            int color = intent.getIntExtra(MainActivity.TITLE_COLOR, -1);
            colorResult = color;

            if (!content.isEmpty()) {
                edtTitle.setText(content);
            }
            if (color != -1) {
                viewSample.setBackgroundColor(color);
            }
        }
    }

    @OnClick({R.id.ivBlue, R.id.ivGreen, R.id.ivIndigo, R.id.ivPink, R.id.ivPurple, R.id.ivTeal})
    public void setColor(ImageView view) {
        switch (view.getId()) {
            case R.id.ivBlue:
                changeViewColor( colorBlue);
                break;
            case R.id.ivGreen:
                changeViewColor(colorGreen);
                break;
            case R.id.ivIndigo:
                changeViewColor(colorIndigo);
                break;
            case R.id.ivPurple:
                changeViewColor(colorPurple);
                break;
            case R.id.ivTeal:
                changeViewColor(colorTeal);
                break;
            case R.id.ivPink:
                changeViewColor(colorPink);
                break;
        }
    }

    private void changeViewColor(int color) {
        colorResult = color;
        viewSample.setBackgroundColor(color);
    }

    @OnClick(R.id.btnSave)
    public void saveChanges(){
        Intent resultIntent = new Intent(this, MainActivity.class);
        resultIntent.putExtra(TITLE_TEXT_RESULT, edtTitle.getText().toString());
        resultIntent.putExtra(COLOR_RESULT, colorResult);
        setResult(RESULT_OK, resultIntent);
        finish();
    }
}
