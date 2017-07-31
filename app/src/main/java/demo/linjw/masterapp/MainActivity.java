package demo.linjw.masterapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private final static String SKIN_APK_PACKAGE = "demo.linjw.skin";
    
    private SkinHelper mSkinHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSkinHelper = new SkinHelper(this, SKIN_APK_PACKAGE);

        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText(mSkinHelper.getString("label"));

        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setImageDrawable(mSkinHelper.getDrawable("img"));
    }
}
