package witkowska.palmcounter;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017-07-04.
 */

public class FontActivity extends AppCompatActivity {

    @BindView(R.id.myRadioGroup) RadioGroup radioGroup;
    @BindView(R.id.andalus) RadioButton text_andalus;
    @BindView(R.id.castellar) RadioButton text_castellar;
    @BindView(R.id.elephant) RadioButton text_elephant;
    @BindView(R.id.matura) RadioButton text_matura;
    @BindView(R.id.editFontSize) EditText edit_font_size;
    @BindView(R.id.currentFontSize) TextView current_font_size;

    String font_andalus;
    String font_castellar;
    String font_elephant;
    String font_matura;

    String font;
    int font_size;
    int counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_font);
        ButterKnife.bind(this);

        Bundle bundle = getIntent().getExtras();
        font = bundle.getString("font_path");
        font_size = bundle.getInt("font_size");
        counter = bundle.getInt("counter");

        setFonts();

        current_font_size.setText(String.valueOf(font_size));

        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = radioGroup.getCheckedRadioButtonId();

                if(selectedId == text_andalus.getId()) {
                    font = font_andalus;
                } else if(selectedId == text_castellar.getId()) {
                    font = font_castellar;
                } else if(selectedId == text_elephant.getId()) {
                    font = font_elephant;
                } else if(selectedId == text_matura.getId()) {
                    font = font_matura;
                }

                String edit_text = edit_font_size.getText().toString();

                if (edit_text.matches(""))
                    saveIntent();
                else if (Integer.parseInt(edit_text) < 80 || Integer.parseInt(edit_text) > 200)
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.font_size_toast), Toast.LENGTH_SHORT).show();
                else {
                    font_size = Integer.parseInt(edit_text);
                    saveIntent();
                }
            }
        });

    }

    public void saveIntent(){
        Intent intent = new Intent();
        intent.putExtra("font_path", font);
        intent.putExtra("font_size", font_size);
        intent.putExtra("counter", counter);
        setResult(RESULT_OK, intent);
        finish();
    }

    public void setFonts() {
        font_andalus = "fonts/Andalus.ttf";
        font_castellar = "fonts/Castellar.ttf";
        font_elephant = "fonts/Elephant.ttf";
        font_matura = "fonts/Matura.ttf";

        text_andalus.setTypeface(Typeface.createFromAsset(getAssets(), font_andalus));
        text_castellar.setTypeface(Typeface.createFromAsset(getAssets(), font_castellar));
        text_elephant.setTypeface(Typeface.createFromAsset(getAssets(), font_elephant));
        text_matura.setTypeface(Typeface.createFromAsset(getAssets(), font_matura));
    }

}

