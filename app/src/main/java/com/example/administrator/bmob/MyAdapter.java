package com.example.administrator.bmob;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by Administrator on 2017/3/8.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{

    private List<person> personList;
    private onDelListener listener;

    public MyAdapter(List<person> personList, onDelListener listener){
        this.personList = personList;
        this.listener = listener;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
    private ImageView head;
        private TextView name,age;
        private Button del;
        public ViewHolder(View itemView) {
            super(itemView);
            head = (ImageView) itemView.findViewById(R.id.head);
            name = (TextView) itemView.findViewById(R.id.name);
            age = (TextView) itemView.findViewById(R.id.age);
            del = (Button) itemView.findViewById(R.id.del);

        }
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.person_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    public void changeData(List<person> personList){
        this.personList=personList;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final person p = personList.get(position);
        p.getHead().getFileUrl();

        holder.name.setText(p.getName());
        holder.age.setText(p.getAge());
        holder.del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                p.delete(new UpdateListener() {
                    @Override
                    public void done(BmobException e) {
                        listener.refresh();
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return personList.size();
    }
}
