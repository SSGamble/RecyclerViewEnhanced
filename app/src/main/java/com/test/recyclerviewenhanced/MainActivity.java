package com.test.recyclerviewenhanced;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;

import com.nikhilpanju.recyclerviewenhanced.OnActivityTouchListener;
import com.nikhilpanju.recyclerviewenhanced.RecyclerTouchListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RecyclerTouchListener.RecyclerTouchListenerHelper {

    RecyclerView mRecyclerView;
    Adapter mAdapter;
    List<Integer> mUnClickableRows, mUnSwipeableRows;
    private RecyclerTouchListener onTouchListener;
    private OnActivityTouchListener touchListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initRecyclerView();
    }

    /**
     * 初始化 RecyclerView
     */
    private void initRecyclerView() {
        mUnClickableRows = new ArrayList<>();
        mUnSwipeableRows = new ArrayList<>();

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mAdapter = new Adapter(this, getData());
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        onTouchListener = new RecyclerTouchListener(this, mRecyclerView);
        onTouchListener
                .setClickable(new RecyclerTouchListener.OnRowClickListener() {
                    @Override
                    public void onRowClicked(int position) {
                        Log.e("MainActivity", "点击了第" + (position + 1) + "行元素");
                    }

                    @Override
                    public void onIndependentViewClicked(int independentViewID, int position) {
                        Log.e("MainActivity", "Button in row " + (position + 1) + " clicked!");
                    }
                })
                .setSwipeOptionViews(R.id.edit, R.id.change)
                .setSwipeable(R.id.rowFG, R.id.rowBG, new RecyclerTouchListener.OnSwipeOptionsClickListener() {
                    @Override
                    public void onSwipeOptionClicked(int viewID, int position) {
                        if (viewID == R.id.edit) {
                            Log.e("MainActivity", "点击了edit按钮");
                        } else if (viewID == R.id.change) {
                            Log.e("MainActivity", "点击了change按钮");
                        }
                    }
                });
    }

    /**
     * 获取数据
     * @return
     */
    private List<RowModel> getData() {
        List<RowModel> list = new ArrayList<>(25);
        for (int i = 0; i < 10; i++) {
            list.add(new RowModel("Row " + (i + 1), "Some Text... "));
        }
        return list;
    }

    @Override
    protected void onResume() {
        super.onResume();
        mRecyclerView.addOnItemTouchListener(onTouchListener); }

    @Override
    protected void onPause() {
        super.onPause();
        mRecyclerView.removeOnItemTouchListener(onTouchListener);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (touchListener != null) touchListener.getTouchCoordinates(ev);
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public void setOnActivityTouchListener(OnActivityTouchListener listener) {
        this.touchListener = listener;
    }
}

