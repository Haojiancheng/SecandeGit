package com.bawei.secandegit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Button;

import com.bawei.secandegit.baseadapter.Mybaseadapter;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button but1;
    private RecyclerView rview;
    private List<Data.DataBean> list=new ArrayList<>();
    private LinearLayoutManager linearLayoutManager;
    private Mybaseadapter mybaseadapter;
    private GridLayoutManager gridLayoutManager;
    private StaggeredGridLayoutManager staggeredGridLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        but1= (Button) findViewById(R.id.but1);
        rview= (RecyclerView) findViewById(R.id.rview);
        linearLayoutManager = new LinearLayoutManager(this);
        gridLayoutManager = new GridLayoutManager(this, 3);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return 3 - position % 3 ;
            }
        });
        staggeredGridLayoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL );


        rview.setLayoutManager(linearLayoutManager);



        getHttp();

    }

    private void getHttp() {
        RequestParams requestParams=new RequestParams("http://www.93.gov.cn/93app/data.do?channelId=1&startNum=1");
        x.http().get(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Data data = new Gson().fromJson(result, Data.class);
                list = data.getData();
                mybaseadapter = new Mybaseadapter(list, MainActivity.this);
                rview.setAdapter(mybaseadapter);

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }
    public void gethuan(View view){
        RecyclerView.LayoutManager layoutManager = rview.getLayoutManager();
        if (layoutManager==null){
            return;
        }
        if (layoutManager instanceof GridLayoutManager){

                rview.setLayoutManager(linearLayoutManager);
        }else if (layoutManager instanceof LinearLayoutManager){

            rview.setLayoutManager(gridLayoutManager);
        }

    }
}
