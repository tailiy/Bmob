package com.example.administrator.bmob;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

public class MainActivity extends AppCompatActivity implements onDelListener{
private RecyclerView mRecyclerView;
    private List<person> personList = new ArrayList<>();
    private MyAdapter myAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bmob.initialize(this, "224f14c76b17b572a6aa621f986e5ab4");

        mRecyclerView  = (RecyclerView) findViewById(R.id.recyclerView);
        myAdapter = new MyAdapter(personList,MainActivity.this);

        LinearLayoutManager lm = new LinearLayoutManager(MainActivity.this);
        mRecyclerView.setLayoutManager(lm);
        mRecyclerView.setAdapter(myAdapter);

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,AddActivity.class));
            }
        });
    }




public void add(){
        person p =new person();
        p.setName("123");
        p.setAge(12);
        p.setAddress("504");
        p.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if(e==null){

                    Toast.makeText(MainActivity.this, "添加数据成功，返回objectId为："+s, Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "创建数据失败：" + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

public void delete(){
        person p = new person();;
        p.delete("004363213f", new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if(e==null){

                    Toast.makeText(MainActivity.this, "删除数据成功，返回objectId为："+e, Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "删除数据失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

public void query(){
    BmobQuery<person> bmobQuery = new BmobQuery<person>();
    bmobQuery.getObject("4c007e0fa2", new QueryListener<person>() {
        @Override
        public void done(person person, BmobException e) {
            if(e==null){

                Toast.makeText(MainActivity.this, "查询成功", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(MainActivity.this, "查询失败", Toast.LENGTH_SHORT).show();
            }
        }
    });
}


public void update(){
    person p = new person();
    p.setAddress("qwe");
    p.update("4c007e0fa2", new UpdateListener() {
        @Override
        public void done(BmobException e) {
            if(e==null){

                Toast.makeText(MainActivity.this, "更改成功", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(MainActivity.this, "更改失败", Toast.LENGTH_SHORT).show();
            }
        }
    });
}

    public void queryAll(){
        BmobQuery<person> query = new BmobQuery<>();
        query.findObjects(new FindListener<person>() {
            @Override
            public void done(List<person> list, BmobException e) {
                if(e==null){
                    personList=list;
                    myAdapter.changeData(personList);
                }
            }
        });
    }
    @Override
    public void del(String name) {

    }

    @Override
    public void refresh() {

    }
}
