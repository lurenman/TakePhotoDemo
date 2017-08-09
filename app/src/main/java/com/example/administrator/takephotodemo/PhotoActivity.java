package com.example.administrator.takephotodemo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;

import com.jph.takephoto.app.TakePhotoActivity;
import com.jph.takephoto.model.TImage;
import com.jph.takephoto.model.TResult;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/8/9.
 */

public class PhotoActivity extends TakePhotoActivity
{
    private Button btnPickByTake;//拍照
    private Button btnPickBySelect;//选择照片
    private GridView gv_gridView;
    private GridAdapter mGridAdapter;
    private PhotoHelper mPhotoHelper;
    private  ArrayList<TImage> images=new ArrayList<TImage>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        initViews();
        mPhotoHelper=new PhotoHelper();
        mPhotoHelper.setCrop(true);//设置是否裁剪
        mPhotoHelper.setPhotoLimitNumber(2);//设置选择图片的数量
    }

    private void initViews()
    {
        btnPickByTake= (Button) findViewById(R.id.btnPickByTake);
        btnPickBySelect=(Button)findViewById(R.id.btnPickBySelect);
        gv_gridView= (GridView) findViewById(R.id.gv_gridView);
        mGridAdapter=new GridAdapter(PhotoActivity.this,images);
        gv_gridView.setAdapter(mGridAdapter);
    }
    //点击事件
    public void onClick(View view) {
       // 把事件交给photohelper
        if (null!=mPhotoHelper)
        mPhotoHelper.onClick(view,getTakePhoto());

    }

    @Override
    public void takeCancel() {
        super.takeCancel();
    }

    @Override
    public void takeFail(TResult result, String msg) {
        super.takeFail(result, msg);
    }

    @Override
    public void takeSuccess(TResult result) {
        super.takeSuccess(result);
       //成功之后我们可以拿到图片集合，之后gridView实现加载图片
        ArrayList<TImage> datas = result.getImages();
        images.addAll(datas);
        mGridAdapter.notifyDataSetChanged();
    }
}
