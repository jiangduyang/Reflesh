package com.zx.refleshdemo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.header.StoreHouseHeader;
public class MainActivity extends AppCompatActivity {
 private ArrayList<String> data;
    private ListView listView;
    private PtrClassicFrameLayout ptrframelayout;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //开始添加数据
        initData();
        //初始化布局
        initView();
    }
    private  void initData(){
        data=new ArrayList<>();
        for(int i = 0; i < 20; i++){
            data.add("新的数据"+i);
        }
    }

    private void initView(){
         listView= (ListView) findViewById(R.id.listView);
        ptrframelayout= (PtrClassicFrameLayout) findViewById(R.id.ptrframelayout);
        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,data);
        listView.setAdapter(adapter);
        initRefresh();
        ptrframelayout.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
            Refresh();
            }
        });
    }
    //用来对刷新效果做一些改变，设置一些属性。
    private  void initRefresh(){
        ptrframelayout.setLastUpdateTimeRelateObject(this);
        //设置关闭的时长
        ptrframelayout.setDurationToCloseHeader(3000);
        //改变刷新的样式
        StoreHouseHeader header=new StoreHouseHeader(this);
        //设置刷新的视图以文字显示
        header.initWithString("I LOVE YOU");
        header.setTextColor(R.color.colorAccent);
        //把刷新的样式设置到ptrFrameLayout上
        ptrframelayout.setHeaderView(header);
        ptrframelayout.addPtrUIHandler(header);
    }
    private  void  Refresh(){
        adapter.clear();
        for (int i = 0; i < 20; i++) {
            adapter.add("我是刷新到的数据"+i);
        }
        adapter.notifyDataSetChanged();
        ptrframelayout.refreshComplete();

    }

}
