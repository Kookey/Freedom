package view.chenyu.com.freedom;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tencent.bugly.crashreport.BuglyLog;

import java.util.ArrayList;
import java.util.List;

import view.chenyu.com.freedom.bean.News;

/**
 * Created by wxl19 on 2017/6/22.
 */

public class MyAdapter extends BaseAdapter {

    private static final String TAG = MyAdapter.class.getName();

    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private List<News> news = new ArrayList<>();

    public MyAdapter(Context context) {
        this.mContext = context;
        this.mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getViewTypeCount() {
        return super.getViewTypeCount();
    }

    @Override
    public int getCount() {
        return news.size();
    }

    @Override
    public News getItem(int position) {
        return news.get(position);
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView != null) {
            holder = (ViewHolder) convertView.getTag();
        } else {
            convertView = mLayoutInflater.inflate(R.layout.item_listview, parent, false);
            ImageView imageView = (ImageView) convertView.findViewById(R.id.conver);
            TextView titleTv = (TextView) convertView.findViewById(R.id.title_tv);
            TextView contentTv = (TextView) convertView.findViewById(R.id.content_tv);
            holder = new ViewHolder(imageView, titleTv, contentTv);
            convertView.setTag(holder);
        }
        BuglyLog.w(TAG, "view=" + convertView.hashCode());
        News item = getItem(position);
        Glide.with(mContext).load(item.getConver()).into(holder.imageView);
        holder.titleTv.setText(item.getTitle());
        holder.contentTv.setText(item.getContent());
        return convertView;
    }

    private static class ViewHolder {
        public ImageView imageView;
        public TextView titleTv;
        public TextView contentTv;

        public ViewHolder(ImageView imageView, TextView titleTv, TextView contentTv) {
            this.imageView = imageView;
            this.titleTv = titleTv;
            this.contentTv = contentTv;
        }
    }

    public List<News> getNews() {
        return news;
    }

    public void setNews(List<News> news) {
        this.news = news;
    }

    public void addNews(News news) {
        this.news.add(news);
    }

}
