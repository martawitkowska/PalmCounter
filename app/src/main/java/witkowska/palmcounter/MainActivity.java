package witkowska.palmcounter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private int counter;
    @BindView(R.id.textView) TextView text;
    String font_path;
    int font_size;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        SharedPreferences savedData = this.getPreferences(Context.MODE_PRIVATE);
        counter = savedData.getInt("counter", 0);
        font_path = savedData.getString("font_path","fonts/Andalus.ttf");
        font_size = savedData.getInt("font_size", 140);

        text.setText(String.valueOf(counter));
        text.setTypeface(Typeface.createFromAsset(getAssets(), font_path));
        text.setTextSize(font_size);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.change_font:
                Bundle bundle = new Bundle();
                Intent intent = new Intent(getApplicationContext(), FontActivity.class);
                bundle.putString("font_path", font_path);
                bundle.putInt("font_size", font_size);
                bundle.putInt("counter", counter);
                intent.putExtras(bundle);
                startActivityForResult(intent, 1);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick (R.id.button)
    public void buttonOnClick(View view) {
        text.setText(String.valueOf(++counter));
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK) {
                font_path = data.getStringExtra("font_path");
                font_size = data.getIntExtra("font_size", 140); //default font size = 140  sp
                text.setTypeface(Typeface.createFromAsset(getAssets(), font_path));
                text.setTextSize(font_size);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        SharedPreferences sharedPreferences = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("counter", counter);
        editor.putString("font_path", font_path);
        editor.putInt("font_size", font_size);
        editor.commit();
    }


}
