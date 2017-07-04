package witkowska.palmcounter;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private int counter;
    @BindView(R.id.textView) TextView text;
    Typeface font;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        font = Typeface.createFromAsset(getAssets(), "fonts/Andalus.ttf");
        text.setTypeface(font);
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
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick (R.id.button)
    public void buttonOnClick(View view) {
        text.setText(String.valueOf(++counter));
    }

    @Override
    protected void onPause() {
        super.onPause();

        SharedPreferences sharedPreferences = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("counter", counter);
        editor.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences savedData = this.getPreferences(Context.MODE_PRIVATE);
        counter = savedData.getInt("counter", 0);
        text.setText(String.valueOf(counter));

    }
}
