package com.wmz.demo_w1.activity;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.wmz.demo_w1.R;
import com.wmz.demo_w1.base.BaseActivity;
import com.wmz.demo_w1.ormlite.bean.User;
import com.wmz.demo_w1.ormlite.dao.UserDao;
import com.wmz.mylibrary.adapter.MyCommonAdapter;
import com.wmz.mylibrary.adapter.ViewHolder;
import com.wmz.mylibrary.view.HorizontalListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import butterknife.BindView;

public class OrmLiteActivity extends BaseActivity {
    private Context mContex = OrmLiteActivity.this;
    private UserDao userDao = null;
    private List<User> mUsers = new ArrayList<>();
    @BindView(R.id.hlv_ormlite)
    HorizontalListView mHlv;
    @BindView(R.id.et_ormlite)
    EditText mEt;
    @BindView(R.id.lv_ormlite)
    ListView mLv;
    private String input = "wmz";

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_ormlite;
    }

    @Override
    protected void initData() {
        super.initData();
        ArrayList<String> operates = new ArrayList<>();
        operates.addAll(Arrays.asList(getResources().getStringArray(R.array.ormlite_list)));
        mHlv.setAdapter(new MyCommonAdapter<String>(mContext, operates) {
            @Override
            protected void builderData(ViewHolder viewHolder, Object obj, int position) {
                viewHolder.setText(R.id.btn, obj.toString());
            }

            @Override
            protected int builderView(LayoutInflater inflater) {
                return R.layout.item_btn;
            }
        });
        mEt.setText(input);
        mUserAdapter = new UserAdapter(mContext, mUsers);
        mLv.setAdapter(mUserAdapter);
        userDao = new UserDao(mContext);
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        mHlv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                input = mEt.getText().toString();
                operate(position);
            }
        });
    }

    private void operate(int position) {
        switch (position) {
            case 0:
                add();
                break;
            case 1:
                delete();
                break;
            case 2:
                query();
                show();
                break;
            case 3:
                update();
                break;
        }
    }

    private void add() {
        User user = new User(input, new Date());
        userDao.add(user);
        query();
        show();
    }

    private void delete() {
        query();
        for (User user : mUsers) {
            userDao.delete(user);
        }
        query();
        show();
    }

    private void query() {
        mUsers.clear();
        if (TextUtils.isEmpty(input) || input == "") {
            mUsers.addAll(userDao.queryForAll());
        } else {
            mUsers.addAll(userDao.queryForColumnName("name", input));
        }

    }

    private void update() {
        query();
        for (User user : mUsers) {
            user.setDate(new Date());
            userDao.update(user);
        }
        query();
        show();
    }

    private void show() {
        mUserAdapter.notifyDataSetChanged();
    }

    private UserAdapter mUserAdapter;

    class UserAdapter extends MyCommonAdapter<User> {

        protected UserAdapter(Context context, List<User> list) {
            super(context, list);
        }

        @Override
        protected void builderData(ViewHolder viewHolder, Object obj, int position) {
            viewHolder.setText(R.id.tv, obj.toString());
        }

        @Override
        protected int builderView(LayoutInflater inflater) {
            return R.layout.item_tv;
        }
    }
}
