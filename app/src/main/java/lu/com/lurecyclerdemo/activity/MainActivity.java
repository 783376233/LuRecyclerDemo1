package lu.com.lurecyclerdemo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import lu.com.lurecyclerdemo.R;
import lu.com.lurecyclerdemo.adapter.GridAdapter;
import lu.com.lurecyclerdemo.adapter.LinerAdapter;
import lu.com.lurecyclerdemo.adapter.StagAdapter;

public class MainActivity extends AppCompatActivity {
    private LinerAdapter linerAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private ItemDividerDecoration mDecoration;
    private List<String> mList;
    private List<String> mStaData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        linerAdapter = new LinerAdapter(this, mList, LinearLayoutManager.VERTICAL);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(linerAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private void initData() {
        mList = new ArrayList<>();
        mStaData = new ArrayList<>();
        for (int i = 'A'; i < 'z'; i++) {
            String s = (char) i + "";
            mList.add(s);
        }
        Random random = new Random();
        for (int i=1;i<50;i++) {
            int n = random.nextInt(20) + 1;
            StringBuilder builder = new StringBuilder();
            for (int k = 1;k < n; k++) {
                builder.append(" Love ");
            }
            String s = builder.toString();
            mStaData.add(s);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mDecoration!=null){
            recyclerView.removeItemDecoration(mDecoration);
        }
        switch (item.getItemId()) {
            case R.id.liner_hor:
                linerAdapter = new LinerAdapter(this, mList, LinearLayoutManager.HORIZONTAL);
                layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(linerAdapter);
                mDecoration=new ItemDividerDecoration(this,LinearLayoutManager.HORIZONTAL);
                recyclerView.addItemDecoration(mDecoration);
                break;
            case R.id.liner_ver:
                linerAdapter = new LinerAdapter(this, mList, LinearLayoutManager.VERTICAL);
                layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(linerAdapter);
                mDecoration=new ItemDividerDecoration(this,LinearLayoutManager.VERTICAL);
                recyclerView.addItemDecoration(mDecoration);
                break;
            case R.id.menu_grid:
                GridAdapter gridAdapter=new GridAdapter(this,mList);
                GridLayoutManager gridLayoutManager=new GridLayoutManager(this,5,LinearLayoutManager.HORIZONTAL,false);
                recyclerView.setAdapter(gridAdapter);
                recyclerView.setLayoutManager(gridLayoutManager);
                break;
            case R.id.menu_stagger:
                StagAdapter stagAdapter = new StagAdapter(this, mStaData);
                StaggeredGridLayoutManager layoutManager=
                        new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(stagAdapter);
                break;
            case R.id.menu_add:
                linerAdapter.addData(1);
                break;
            case R.id.menu_delete:
                linerAdapter.deleteData(1);
                break;
            default:
                break;
        }
        return true;

    }
}

