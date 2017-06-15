package com.example.administrator.bmob;


import android.Manifest;
import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;

import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UploadFileListener;


public class AddActivity extends AppCompatActivity{
    private EditText edtName;
    private EditText edtAge;
    private Button btnAdd;
    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtName = (EditText) findViewById(R.id.edt_name);
        edtAge = (EditText) findViewById(R.id.edt_age);
        btnAdd = (Button) findViewById(R.id.btn_add);

        String[] permissins = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};
        if(ContextCompat.checkSelfPermission(AddActivity.this,Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            requestPermissions(permissins,1001);
        }


    }
    public void onRequestPermissionsResult(int requestCode,String[] permissions, int[] grantResults){
        super.onRequestPermissionsResult(requestCode,permissions,grantResults);
    }

private  void add(){
    String path = "";
    File jpgFile = new File(path);
    if(jpgFile.exists()){
        Toast.makeText(this, "文件地址正确", Toast.LENGTH_SHORT).show();
    }else {
        Toast.makeText(this, "文件地址错误", Toast.LENGTH_SHORT).show();
    }
    final BmobFile bmobFile = new BmobFile(new File(path));
    bmobFile.uploadblock(new UploadFileListener() {
        @Override
        public void done(BmobException e) {
            if(e==null){
                Toast.makeText(AddActivity.this, "文件上传成功", Toast.LENGTH_SHORT).show();
                person p = new person();
                p.setHead(bmobFile);
                String name = edtName.getText().toString();
                p.setName(name);
                int age = Integer.parseInt(edtAge.getText().toString());
                p.setAge(age);
                p.setAddress("天源路789号");
                p.setScore(98);
                p.save(new SaveListener<String>() {
                    @Override
                    public void done(String s, BmobException e) {

                    }
                });
            }else{
                Toast.makeText(AddActivity.this, "文件上传", Toast.LENGTH_SHORT).show();
                Log.i("Bmob",e.toString());
            }
        }
        public  void onProgress(Integer value){
            Log.i("Bmob",""+value);
        }
    });
}
}
