package view.chenyu.com.freedom;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

/**
 * @author 王兴岭
 * @desc disklurcache 缓存
 * @email wxlhdm@qq.com
 * @create 2017/6/12 14:51
 **/
public class ImageActivity extends AppCompatActivity {
    public static final String TAG = "ImageActivity";
    

    private ImageView imageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        imageView = (ImageView) findViewById(R.id.image);
    }

    public void loadImg(View view) {
        Toast.makeText(this, "加载图片", Toast.LENGTH_SHORT).show();
        Log.e(TAG, "loadImg: 加载图片");
        Glide.with(this).load("http://img1.gtimg.com/news/pics/hv1/135/102/2216/144121545.jpg").placeholder(R.mipmap.ic_launcher).into(imageView);
    }
}
