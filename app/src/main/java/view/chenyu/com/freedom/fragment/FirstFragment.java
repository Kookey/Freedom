package view.chenyu.com.freedom.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.tencent.bugly.crashreport.BuglyLog;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.bgabanner.BGABanner;
import view.chenyu.com.freedom.GlideExceptionActivity;
import view.chenyu.com.freedom.R;

/**
 * Created by wxl19 on 2017/6/21.
 */

public class FirstFragment extends Fragment {

    private static final String TAG = FirstFragment.class.getName();
    private RecyclerView recyclerView;
    private MyAdapter adapter;

    public static final int MENU_TYPE = 1;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = (RecyclerView) view.findViewById(R.id.content);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(manager);
        DividerItemDecoration mDividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                manager.getOrientation());
        recyclerView.addItemDecoration(mDividerItemDecoration);
        adapter = new MyAdapter(getActivity());
        recyclerView.setAdapter(adapter);

        MyAsyncTask task = new MyAsyncTask(adapter);
        task.execute();

    }

    private class MyAsyncTask extends AsyncTask {

        private MyAdapter myAdapter;

        public MyAsyncTask(MyAdapter myAdapter) {
            this.myAdapter = myAdapter;
        }

        @Override
        protected Object doInBackground(Object[] params) {
            try {
                Thread.sleep(10000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            List<Integer[]> bannerIds = myAdapter.getBannerIds();
            Integer[] arr = bannerIds.get(0);
            arr[0] = R.drawable.car01;
            arr[1] = R.drawable.car02;
            arr[2] = R.drawable.car03;
            myAdapter.notifyItemChanged(0);
        }
    }

    private class MyAdapter extends RecyclerView.Adapter {

        private LayoutInflater mLayoutInflater;
        private List<Integer[]> bannerIds;


        public MyAdapter(Context context) {
            mLayoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            bannerIds = new ArrayList<>();
            for (int i = 0; i < 100; i++) {
                Integer[] bannerIdArr = new Integer[]{R.mipmap.danche, R.mipmap.danche_001, R.mipmap.danche_002};
                bannerIds.add(bannerIdArr);
            }
        }

        @Override
        public int getItemViewType(int position) {
            BuglyLog.e(TAG, "postion=" + position);
            if (position == 1) {
                return MENU_TYPE;
            }
            return super.getItemViewType(position);
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if (viewType == MENU_TYPE) {
                View view = mLayoutInflater.inflate(R.layout.menu, parent, false);
                return new MenuViewHolder(view);
            }
            View view = mLayoutInflater.inflate(R.layout.banner_item, parent, false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
            int viewType = getItemViewType(position);
            if (viewType == MENU_TYPE) {
                MenuViewHolder viewHolder = (MenuViewHolder) holder;
                viewHolder.menu01.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getActivity(), "菜单1", Toast.LENGTH_SHORT).show();
//                        Integer[] arr = bannerIds.get(2);
//                        arr[0] = R.drawable.car01;
//                        arr[1] = R.drawable.car02;
//                        arr[2] = R.drawable.car03;
//                        MyAdapter.this.notifyItemChanged(2);
                        MyAsyncTask task = new MyAsyncTask(adapter);
                        task.execute();
                    }
                });
                viewHolder.menu02.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getActivity(), "菜单2", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getActivity(), GlideExceptionActivity.class);
                        getActivity().startActivity(intent);
                    }
                });
                viewHolder.menu03.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getActivity(), "菜单3", Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                MyViewHolder viewHolder = (MyViewHolder) holder;
                BGABanner bgaBanner = viewHolder.mBGABanner;
                Integer[] arr = bannerIds.get(position);
                bgaBanner.setData(arr[0], arr[1], arr[2]);
            }
        }

        @Override
        public int getItemCount() {
            return bannerIds.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {

            public BGABanner mBGABanner;

            public MyViewHolder(View itemView) {
                super(itemView);
                mBGABanner = (BGABanner) itemView.findViewById(R.id.banner_guide_content);
            }
        }

        public class MenuViewHolder extends RecyclerView.ViewHolder {
            public TextView menu01;
            public TextView menu02;
            public TextView menu03;

            public MenuViewHolder(View itemView) {
                super(itemView);
                menu01 = (TextView) itemView.findViewById(R.id.menu_01);
                menu02 = (TextView) itemView.findViewById(R.id.menu_02);
                menu03 = (TextView) itemView.findViewById(R.id.menu_03);
            }
        }

        public List<Integer[]> getBannerIds() {
            return bannerIds;
        }
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.custom_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.change) {
            List<Integer[]> bannerIds = adapter.getBannerIds();
            Integer[] arr = bannerIds.get(0);
            arr[0] = R.drawable.car01;
            arr[1] = R.drawable.car02;
            arr[2] = R.drawable.car03;
            adapter.notifyItemChanged(0);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}
