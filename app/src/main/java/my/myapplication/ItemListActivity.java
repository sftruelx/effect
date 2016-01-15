package my.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


import my.myapplication.dummy.DummyContent;

import java.util.ArrayList;
import java.util.List;

/**
 * An activity representing a list of Items. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link ItemDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class ItemListActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener, SwipeRefreshLayout.OnLoadListener {
    protected ListView mListView;
    private ArrayAdapter<String> mListAdapter;
    SwipeRefreshLayout mSwipeLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_list);

        mListView = (ListView) findViewById(R.id.list);
        mListAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, values);
        mListView.setAdapter(mListAdapter);


        mSwipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
        mSwipeLayout.setRefreshing(false);
        mSwipeLayout.setOnRefreshListener(this);
        mSwipeLayout.setOnLoadListener(this);
        mSwipeLayout.setColor(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        mSwipeLayout.setMode(SwipeRefreshLayout.Mode.PULL_FROM_END);
        mSwipeLayout.setLoadNoFull(true);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    ArrayList<String> values = new ArrayList<String>() {{
        add("value 0");
        add("value 1");
        add("value 2");
        add("value 3");
        add("value 4");
        add("value 5");
        add("value 6");
    }};

    @Override
    public void onRefresh() {
        values.add(0, "Add " + values.size());
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mSwipeLayout.setRefreshing(false);
                mListAdapter.notifyDataSetChanged();
            }
        }, 2000);
    }

    @Override
    public void onLoad() {
        values.add("Add " + values.size());
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mSwipeLayout.setLoading(false);
                mListAdapter.notifyDataSetChanged();
            }
        }, 1000);
    }
}