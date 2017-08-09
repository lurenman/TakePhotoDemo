package com.example.administrator.takephotodemo;

import android.net.Uri;
import android.os.Environment;
import android.view.View;

import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.compress.CompressConfig;
import com.jph.takephoto.model.CropOptions;
import com.jph.takephoto.model.LubanOptions;
import com.jph.takephoto.model.TakePhotoOptions;

import java.io.File;

/**
 * Created by Administrator on 2017/8/9.
 */
//这个类主要就是设置一些配置的
public class PhotoHelper {

    //裁切配置
    private boolean isCrop=true;//是否裁切
    private int CropHeight=800;
    private int CropWidth=800;
    private boolean withWonCrop=false;//true:TakePhoto自带、false：第三方
    private boolean isAspect=false;//true:宽/高/false:宽x高

    //关于压缩配置的一些
    private boolean ys_rbCompressYes=true;//是否压缩
    private int ys_maxSize=102400;//
    private int ys_width=800;//
    private int ys_height=800;//
    private boolean ys_showProgressBar=true;//是否显示压缩进度条
    private boolean ys_rgCompressTool=true;//压缩工具:自带、Luban
    //选择图片配置
    private boolean xz_rbPickWithOwn=true;//是否使用TakePhoto自带相册
    private int photoLimitNumber=1;//最多选择几张
    private String fromSelect="从相册";//从相册、从文件

    //其他配置
    private boolean enableRawFile=true;//拍照压缩后是否保存原图
    private boolean rbCorrect=false; //纠正拍照的照片旋转角度

    public boolean isCrop() {
        return isCrop;
    }

    public void setCrop(boolean crop) {
        isCrop = crop;
    }

    public int getPhotoLimitNumber() {
        return photoLimitNumber;
    }

    public void setPhotoLimitNumber(int photoLimitNumber) {
        this.photoLimitNumber = photoLimitNumber;
    }

    public PhotoHelper()
    {

    }

    public void onClick(View view, TakePhoto takePhoto)
    {
        File file=new File(Environment.getExternalStorageDirectory(), "/temp/"+System.currentTimeMillis() + ".jpg");
        if (!file.getParentFile().exists())file.getParentFile().mkdirs();
        Uri imageUri = Uri.fromFile(file);
        //压缩配置
        configCompress(takePhoto);
        //选择图片配置
        configTakePhotoOption(takePhoto);

        switch (view.getId()){
            //选择照片
            case R.id.btnPickBySelect:
                //最多选择：
                if(photoLimitNumber>1){
                    //是否裁切
                    if(isCrop){
                        //裁切配置
                        takePhoto.onPickMultipleWithCrop(photoLimitNumber,getCropOptions());
                    }else {
                        //不裁切
                        takePhoto.onPickMultiple(photoLimitNumber);
                    }
                    return;
                }
                //rgFrom从哪选择  //  rbFile 从文件
                if("从文件".equals(fromSelect)){
                    //判断是否裁切
                    if(isCrop){
                        takePhoto.onPickFromDocumentsWithCrop(imageUri,getCropOptions());
                    }else {
                        takePhoto.onPickFromDocuments();
                    }
                    return;
                }else {
                    //从相册//判断是否裁切
                    if(isCrop){
                        takePhoto.onPickFromGalleryWithCrop(imageUri,getCropOptions());
                    }else {
                        takePhoto.onPickFromGallery();
                    }
                }
                break;
            //拍照按钮
            case R.id.btnPickByTake:
                //判断是否裁切
                if(isCrop){
                    takePhoto.onPickFromCaptureWithCrop(imageUri,getCropOptions());
                }else {
                    takePhoto.onPickFromCapture(imageUri);
                }
                break;
            default:
                break;
        }

    }
    //压缩配置
    private void configCompress(TakePhoto takePhoto){

          if (ys_rbCompressYes==false)
          {
              takePhoto.onEnableCompress(null,false);
              return ;
          }
        CompressConfig config;
        //压缩工具
        if(ys_rgCompressTool){
            config=new CompressConfig.Builder()
                    .setMaxSize(ys_maxSize)
                    .setMaxPixel(ys_width>=ys_height? ys_width:ys_height)
                    .enableReserveRaw(enableRawFile)
                    .create();
        }else {
            LubanOptions option=new LubanOptions.Builder()
                    .setMaxHeight(ys_height)
                    .setMaxWidth(ys_width)
                    .setMaxSize(ys_maxSize)
                    .create();
            config=CompressConfig.ofLuban(option);
            config.enableReserveRaw(enableRawFile);
        }
        takePhoto.onEnableCompress(config,ys_showProgressBar);
    }
    //选择图片配置
    private void configTakePhotoOption(TakePhoto takePhoto){
        TakePhotoOptions.Builder builder=new TakePhotoOptions.Builder();
        //使用TakePhoto自带相册
        if(xz_rbPickWithOwn){
            builder.setWithOwnGallery(true);
        }
        //纠正拍照的照片旋转角度
        if(rbCorrect){
            builder.setCorrectImage(true);
        }
        takePhoto.setTakePhotoOptions(builder.create());
    }
    //是否裁切
    private CropOptions getCropOptions(){
        //是否裁切
        if(isCrop==false)return null;
        CropOptions.Builder builder=new CropOptions.Builder();
        // 尺寸/比例 //rbAspect宽/高
        if(isAspect){
            builder.setAspectX(CropWidth).setAspectY(CropHeight);
        }else {
            //rbOutPut宽x高
            builder.setOutputX(CropWidth).setOutputY(CropHeight);
        }
        builder.setWithOwnCrop(withWonCrop);
        return builder.create();
    }
}
