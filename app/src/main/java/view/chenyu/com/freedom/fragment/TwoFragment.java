package view.chenyu.com.freedom.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.bingoogolapple.bgabanner.BGABanner;
import view.chenyu.com.freedom.R;

/**
 * Created by wxl19 on 2017/6/21.
 */

public class TwoFragment extends Fragment {

    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_two, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = (RecyclerView) view.findViewById(R.id.content);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new MyAdapter(getActivity()));
    }

    private class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

        private LayoutInflater mLayoutInflater;

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
            bgaBanner.setData(R.mipmap.holder,R.mipmap.holder,R.mipmap.holder);
        }

        @Override
        public int getItemCount() {
            return 1;
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {

            public BGABanner mBGABanner;

            public MyViewHolder(View itemView) {
                super(itemView);
                mBGABanner = (BGABanner) itemView.findViewById(R.id.banner_guide_content);
            }
        }
    }
}
