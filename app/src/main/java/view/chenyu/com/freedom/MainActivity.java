package view.chenyu.com.freedom;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";

    private RadioView imageView;

    private float mDensity;//密度
    private RelativeLayout.LayoutParams params;
    private RelativeLayout relativeLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        DisplayMetrics dm = getResources().getDisplayMetrics();
        mDensity = dm.density;
        params = new RelativeLayout.LayoutParams((int) (60*mDensity),(int)(60*mDensity));
        imageView = (RadioView) findViewById(R.id.iv);
        relativeLayout = (RelativeLayout) findViewById(R.id.rl_root);

        View decorView = getWindow().getDecorView();


    }

}
