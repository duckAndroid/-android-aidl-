### -android-aidl-最简单的实现
###### 介绍
* 简单说明一下，最简单的`aidl`实现，不需要多个`app`，也不需要多个`module`。只要一个`service`，一个`activity`，一个`.aidl`文件。
* aidl 本身是对bindService的扩展。
* 更多介绍：[android aidl 从懵逼开始](http://www.jianshu.com/p/16fec59acda1)
* 关于单`module`中如何实现`parcelable`对象的传递，可以看一下：[android aidl 继懵逼之后](http://www.jianshu.com/p/61e021515dbc)

------------

##### 实现
1. `aidl`的最简单实现(通过单`module`中`aidl`获取一个`int`值) ==> `tag v1.0`
2. `aidl`的最简单实现的扩展：单`module`中通过`aidl`实现跨进程传递`pojo`对象 ==> `tagv1.1`

