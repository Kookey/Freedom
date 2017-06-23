package view.chenyu.com.freedom;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.TextView;

import view.chenyu.com.freedom.bean.News;


/**
 * @author 王兴岭
 * @desc ListView 演示
 * @email wxlhdm@qq.com
 * @create 2017/6/22 19:26
 **/
public class ListViewActivity extends AppCompatActivity {

    private ListView mListView;
    private TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_activity);


        initView();
    }

    private void initView() {
        mListView = (ListView) findViewById(R.id.custom_lv);
        textView = (TextView) findViewById(R.id.format_tv);
        textView.setText(getResources().getString(R.string.format_string, "稳定","342432"));
        MyAdapter adapter = new MyAdapter(this);
        for (int i = 0; i < 100; i++) {
            //(int id, String title, String content, String conver)
            int id = i + 1;
            News news = new News(id, "Title" + id, "sadfasdf啊但是发射点发射点似的发射点发射点反对" + id, "http://desk.fd.zol-img.com.cn/t_s960x600c5/g5/M00/08/09/ChMkJ1kehd6ICFj5AARijXYDEWgAAcd5QNgvkEABGKl824.jpg");
            adapter.addNews(news);
        }
        mListView.setAdapter(adapter);
        mListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        mListView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {

            @Override
            public void onItemCheckedStateChanged(ActionMode mode, int position,
                                                  long id, boolean checked) {
                // Here you can do something when items are selected/de-selected,
                // such as update the title in the CAB
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                // Respond to clicks on the actions in the CAB
                switch (item.getItemId()) {
                    case R.id.menu_content_delete:
                        mode.finish(); // Action picked, so close the CAB
                        return true;
                    default:
                        return false;
                }
            }

            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                // Inflate the menu for the CAB
                MenuInflater inflater = mode.getMenuInflater();
                inflater.inflate(R.menu.context_menu, menu);
                return true;
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {
                // Here you can make any necessary updates to the activity when
                // the CAB is removed. By default, selected items are deselected/unchecked.
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                // Here you can perform updates to the CAB due to
                // an invalidate() request
                return false;
            }
        });

    }

}
