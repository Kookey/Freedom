package view.chenyu.com.freedom.task;

import android.os.AsyncTask;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Created by wxl19 on 2017/6/22.
 */

public class MyAsyncTask extends AsyncTask<Void, Integer, Void> {

    private TextView mTextView;
    private ProgressBar progressBar;

    public MyAsyncTask(TextView mTextView, ProgressBar progressBar) {
        super();
        this.mTextView = mTextView;
        this.progressBar = progressBar;

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }


    @Override
    protected Void doInBackground(Void... params) {
        for (int i = 1; i <= 100; i++) {
            publishProgress(i);
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        progressBar.setProgress(values[0]);
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        mTextView.setText("调度结束");
    }
}
