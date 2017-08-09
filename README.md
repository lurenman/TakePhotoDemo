# TakePhotoDemo
这个是android关于图库TakePhoto框架使用的demo，自定义了配置文件，非常好的图库选择
# PhotoHelper

这个类主要就是设置一些配置的
   
   
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
