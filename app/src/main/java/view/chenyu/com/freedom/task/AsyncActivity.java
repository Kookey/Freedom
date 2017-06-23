package view.chenyu.com.freedom.task;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import view.chenyu.com.freedom.R;

/**
 * Created by wxl19 on 2017/6/22.
 */

public class AsyncActivity extends AppCompatActivity {

    private TextView textView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async);
        textView = (TextView) findViewById(R.id.showTv);
        progressBar = (ProgressBar) findViewById(R.id.proBar);
    }

    public void doTask(View view) {
        new MyAsyncTask(textView, progressBar).execute();

    }
}
