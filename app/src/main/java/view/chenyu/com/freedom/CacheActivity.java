package view.chenyu.com.freedom;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.Random;

/**
 * @author 王兴岭
 * @desc DiskLurCache演示
 * @email wxlhdm@qq.com
 * @create 2017/6/12 13:15
 **/
public class CacheActivity extends AppCompatActivity {

    public static final String TEXT = "text";
    private TextView show;
    private Cache cache;

    String[] values = new String[]{"1", "2", "3"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cache);

        cache = new Cache(this);

        show = (TextView) this.findViewById(R.id.show);

    }

    public void setValue(View view) {
        Random random = new Random();
        int nextInt = random.nextInt(values.length);
        String value = values[nextInt];
        cache.set(TEXT, value);
    }


    public void listDir(View view) {
        File cacheDir = Cache.getDiskCacheDir(this, Cache.CACHE_OBJECT);
        String path = cacheDir.getPath();
        String[] list = cacheDir.list();
        StringBuffer buffer = new StringBuffer(10);
        buffer.append(path);
        buffer.append("\n");
        for (String file : list) {
            buffer.append(file);
            buffer.append("\n");
        }
        show.setText(buffer.toString());
    }

    public void showValue(View view) {
        String s = cache.get(TEXT);
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
    }
}
