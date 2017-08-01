package demo.linjw.masterapp;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    private final static String SKIN_APK_PACKAGE = "demo.linjw.skin";
    private final static String SKIN_APK = "skin-debug.apk";

    private SkinHelper mSkinHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //需要将皮肤apk安装到系统
        mSkinHelper = new SkinHelper(this, SKIN_APK_PACKAGE);

        //需要将皮肤apk放到存储卡根目录
        //File skinApk = new File(Environment.getExternalStorageDirectory().getPath(), SKIN_APK);
        //mSkinHelper = new SkinHelper(this, SKIN_APK_PACKAGE, skinApk);

        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText(mSkinHelper.getString("label"));

        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setImageDrawable(mSkinHelper.getDrawable("img"));
    }
}
