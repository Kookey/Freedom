package view.chenyu.com.freedom.fragment;


import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.bgabanner.BGABanner;
import view.chenyu.com.freedom.R;

/**
 * Created by wxl19 on 2017/6/21.
 */

public class ThreeFragment extends Fragment {

    private RecyclerView recyclerView;
    private MyAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_three, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = (RecyclerView) view.findViewById(R.id.content);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new MyAdapter(getActivity());
        recyclerView.setAdapter(adapter);
        new MyAsyncTask(adapter).execute();
    }

    private class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> implements BGABanner.Adapter<ImageView, String> {

        private LayoutInflater mLayoutInflater;

        private List<String> mBanners = new ArrayList<>();

        public MyAdapter(Context context) {
            mLayoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        }


        @Override
        public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = mLayoutInflater.inflate(R.layout.banner_item, parent, false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(MyAdapter.MyViewHolder holder, int position) {
            BGABanner bgaBanner = holder.mBGABanner;
            bgaBanner.setAdapter(this);
            if (mBanners.size() > 0) {
                bgaBanner.setData(mBanners, null);
            }
        }

        @Override
        public int getItemCount() {
            return 1;
        }

        @Override
        public void fillBannerItem(BGABanner banner, ImageView itemView, String model, int position) {
            Glide.with(getActivity())
                    .load(model)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.mipmap.holder)
                    .error(R.mipmap.holder)
                    .dontAnimate()
                    .centerCrop()
                    .into(itemView);
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {

            public BGABanner mBGABanner;

            public MyViewHolder(View itemView) {
                super(itemView);
                mBGABanner = (BGABanner) itemView.findViewById(R.id.banner_guide_content);
            }
        }

        public List<String> getBanners() {
            return mBanners;
        }

    }

    private class MyAsyncTask extends AsyncTask {

        private MyAdapter myAdapter;

        public MyAsyncTask(MyAdapter myAdapter) {
            this.myAdapter = myAdapter;
        }

        @Override
        protected Object doInBackground(Object[] params) {
            try {
                Thread.sleep(1L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            List<String> banners = myAdapter.getBanners();
            banners.clear();
            banners.add("http://desk.fd.zol-img.com.cn/t_s960x600c5/g5/M00/0C/0E/ChMkJ1j1gQ6ILskaADPWcgfgSeYAAbvmQAADeEAM9aK508.jpg");
            banners.add("http://desk.fd.zol-img.com.cn/t_s960x600c5/g5/M00/0C/0E/ChMkJlj1gTSIRKHkAHpV06Be1gMAAbvmQELLy0AelXr975.jpg");
            banners.add("http://desk.fd.zol-img.com.cn/t_s960x600c5/g5/M00/0C/0E/ChMkJlj1gTSIXpitAH0nuDdR6pUAAbvmQGHSYcAfSfQ954.jpg");
            banners.add("http://desk.fd.zol-img.com.cn/t_s960x600c5/g5/M00/0C/0E/ChMkJ1j1gUCIIVeyAHPQ2-cq6C0AAbvmQJXaNMAc9Dz224.jpg");
            banners.add("http://desk.fd.zol-img.com.cn/t_s960x600c5/g5/M00/0D/00/ChMkJ1kv3VGIePNAAEdBY8S3odQAAcvrgN1H90AR0F7861.jpg");
            banners.add("http://desk.fd.zol-img.com.cn/t_s960x600c5/g5/M00/0D/00/ChMkJlkv3c-IE0KFAGlheqczfJsAAcvsAEvI6cAaWGS528.jpg");
            banners.add("http://desk.fd.zol-img.com.cn/t_s960x600c5/g5/M00/0D/00/ChMkJ1kv3cCIN1uRAHcueXM0es8AAcvsAAAAAAAdy6R419.jpg");
            myAdapter.notifyItemChanged(0);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.custom_menu2, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }
}
