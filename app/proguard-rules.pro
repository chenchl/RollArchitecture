# Add project specific ProGuard rules here.
# You can control the setInternal of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in G:\newsdk\sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}
#-----------------混淆配置设定------------------------------------------------------------------------
-optimizationpasses 5                                          #指定代码压缩级别
-dontusemixedcaseclassnames                                   #混合时不使用大小写混合，混合后的类名为小写
-dontskipnonpubliclibraryclasses                             #指定不忽略非公共类库
-dontskipnonpubliclibraryclassmembers                       # 指定不去忽略非公共库的类成员
-dontpreverify                                                  #不预校验，如果需要预校验，是-dontoptimize
-ignorewarnings                                                 #屏蔽警告
-verbose                                                         #使我们的项目混淆后产生映射文件
-printmapping proguardMapping.txt                                #混淆前后的映射
-optimizations !code/simplification/cast,!field/*,!class/merging/*     #混淆时所采用的算法
-keepattributes *Annotation*,InnerClasses                         #保留Annotation注解不混淆
-keepattributes Signature                                         # 避免混淆泛型
-keepattributes SourceFile,LineNumberTable                        # 抛出异常时保留代码行号
#-keepattributes *JavascriptInterface*                              #解决android sdk api >= 17 时安卓与H5交互方法需要加@JavascriptInterface”所出现的问题

-keep public class * extends android.app.Activity
-keep public class * extends android.app.Fragment
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class * extends android.view.View
-keep public class com.android.vending.licensing.ILicensingService
-keep class android.support.** {*;}
#有引用v4包
-keep public class * extends android.support.v4.app.Fragment

#本地方法不混淆
-keepclasseswithmembernames class * {
    native <methods>;
}
#保持在layout中写的onclick方法android:onclick="onClick",不进行混淆
-keepclassmembers class * extends android.app.Activity{
    public void *(android.view.View);
}
#保持枚举enum类不被混淆
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}
#自定义组件不被混淆
-keep public class * extends android.view.View{
    *** get*();
    void set*(***);
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
}
#保持自定义控件类不被混淆
-keepclasseswithmembers class * {
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
}
-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}
#保持Serializable不被混淆并且enum 类也不被混淆
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}
#保持Serializable实现类不被混淆
-keepnames class * implements java.io.Serializable
-keep public class * implements java.io.Serializable {*;}
#不混淆资源类
-keepclassmembers class **.R$* {
    public static <fields>;
}
-keep class **.R$* {
 *;
}
-keepclassmembers class * {
    void *(**On*Event);
}

-keep public class com.ReadApp.client.R$*{
public static final int *;
}

# 对于带有回调函数的onXXEvent、**On*Listener的，不能被混淆
-keepclassmembers class * {
    void *(**On*Event);
    void *(**On*Listener);
}

#如果引用了v4或者v7包(忽略警告)
-dontwarn android.support.**

#support.v4/v7包不混淆
-keep public class * extends android.support.v4.**
-keep public class * extends android.support.v7.**
-keep interface android.support.v7.app.** { *; }

# support-v4
#https://stackoverflow.com/questions/18978706/obfuscate-android-support-v7-widget-gridlayout-issue
-dontwarn android.support.v4.**
-keep class android.support.v4.**{*;}
-keep class android.support.v4.app.** { *; }
-keep interface android.support.v4.app.** { *; }
-keep class android.support.v4.** { *; }

# support-v7
-keep class android.support.** { *; }
-dontwarn android.support.v7.**
-keep class android.support.v7.internal.** { *; }
-keep interface android.support.v7.internal.** { *; }
-keep class android.support.v7.** { *; }

# support-design
-dontwarn android.support.design.**
-keep class android.support.design.** { *; }
-keep interface android.support.design.** { *; }
-keep public class android.support.design.R$* { *; }

-dontwarn org.json.**
-keep class org.json.**{*;}

#保持注解继承类不混淆
-keep class * extends java.lang.annotation.Annotation {*;}

#---------------------------------1.实体类---------------------------------
#bean类不能混淆
-keep class com.tope365.ReadApp.net.response.** { *; }
-keep class com.tope365.ReadApp.model.** { *; }
-keep class com.readapp.basecommon.model.** { *; }
-keep class com.readapp.basecommon.database.** { *; }
-keep class com.tope365.readapp.logincomponent.model.bean.** { *; }
-keep class com.tope365.readapp.logincomponent.model.response.** { *; }
-keep class com.tope365.readapp.maincomponent.model.bean.** { *; }
-keep class com.tope365.readapp.maincomponent.model.response.** { *; }
-keep class com.ieng.common.model.** { *; }
-keep class com.ieng.common.http.HttpResponse
-keep class com.ieng.its.model.** { *; }
-keep class com.tope365.ReadApp.ui.activity.WebView.GameWebViewActivity { *; }

#---------------------------------2.第三方包-------------------------------
#引擎加密相关
-keep class com.speechTool.** {*;}
-keep class com.ccl.jnitest.** {*;}
-keep class com.readapp.tomp3.** {*;}
-keep class com.ccl.ffmpeg.** {*;}
-keep class com.ccl.appcontant.** {*;}
-dontwarn com.speechTool.**
-keep class com.tt.** {*;}
-keep class com.xs.** {*;}
-keep class com.constraint.** {*;}
-dontwarn com.tt.**
-dontwarn com.xs.**
-dontwarn com.constraint.**

#progressbar
-keep class com.dinuscxj.progressbar.** {*;}
-dontwarn com.dinuscxj.progressbar.**

#basehttpnet
-dontwarn com.readapp.basecommonnet.**
-keep class com.readapp.basecommonnet.**{*;}

#okhttp
-dontwarn okhttp3.**
-keep class okhttp3.**{*;}

#CacheWebview
-dontwarn ren.yale.android.cachewebviewlib.**
-keep class ren.yale.android.cachewebviewlib.**{*;}

# Retrofit
-dontnote retrofit2.Platform
-dontnote retrofit2.Platform$IOS$MainThreadExecutor
-dontwarn retrofit2.Platform$Java8
-keepattributes Exceptions

# Retrofit does reflection on generic parameters. InnerClasses is required to use Signature and
# EnclosingMethod is required to use InnerClasses.
-keepattributes Signature, InnerClasses, EnclosingMethod

# Retrofit does reflection on method and parameter annotations.
-keepattributes RuntimeVisibleAnnotations, RuntimeVisibleParameterAnnotations

# Retain service method parameters when optimizing.
-keepclassmembers,allowshrinking,allowobfuscation interface * {
    @retrofit2.http.* <methods>;
}

# Ignore annotation used for build tooling.
-dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement

# Ignore JSR 305 annotations for embedding nullability information.
-dontwarn javax.annotation.**

#retrofit-converters-gson
-keep class retrofit2.converter.gson.** { *; }
-keep interface retrofit2.converter.gson.** { *; }

#retrofit2.adapter.rxjava
-keep class retrofit2.adapter.rxjava.** { *; }
-keep interface retrofit2.adapter.rxjava.** { *; }

-keep class rx.android.** { *; }
-keep interface rx.android.** { *; }

-keep class rx.android.plugins.** { *; }
-keep interface rx.android.plugins.** { *; }

-keep class rx.android.schedulers.** { *; }
-keep interface rx.android.schedulers.** { *; }

# RxJava RxAndroid
-dontwarn sun.misc.**
-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
    long producerIndex;
    long consumerIndex;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode producerNode;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode consumerNode;
}

-dontnote rx.internal.util.PlatformDependent

#butterknife 8.4.0
-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewBinder { *; }

-keepclasseswithmembernames class * {
    @butterknife.* <fields>;
}

-keepclasseswithmembernames class * {
    @butterknife.* <methods>;
}

#eventbus:3.0.0
## EventBus3  specific rules ##
# http://greenrobot.org/eventbus/documentation/proguard/

-keepattributes *Annotation*
-keepclassmembers class ** {
    @org.greenrobot.eventbus.Subscribe <methods>;
}
-keep enum org.greenrobot.eventbus.ThreadMode { *; }

# Only required if you use AsyncExecutor
-keepclassmembers class * extends org.greenrobot.eventbus.util.ThrowableFailureEvent {
    <init>(java.lang.Throwable);
}

-dontwarn android.support.design.**
-keep class android.support.design.** { *; }
-keep interface android.support.design.** { *; }


-dontwarn android.support.design.internal.**
-keep class android.support.design.internal.** { *; }
-keep interface android.support.design.internal.** { *; }


-dontwarn android.support.design.widget.**
-keep class android.support.design.widget.** { *; }
-keep interface android.support.design.widget.** { *; }

#okio
-dontwarn okio.**
-keep class okio.**{*;}

#glide
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public class * extends com.bumptech.glide.module.AppGlideModule
-keep public enum com.bumptech.glide.load.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}

#umeng
-keepclassmembers class * {
   public <init> (org.json.JSONObject);
}

#com.google.android.gms
-dontwarn com.google.android.gms.**
-keep class com.google.android.gms.**{*;}

#zxing(二维码)
#-libraryjars libs/zxing.jar
#-libraryjars libs/zxing_apply.jar
-keep class com.google.zxing.** {*;}
-dontwarn com.google.zxing.**

#gson
#如果用用到Gson解析包的，直接添加下面这几行就能成功混淆，不然会报错。
# Application classes that will be serialized/deserialized over Gson
-keep class com.google.gson.** { *; }
-keep class com.google.**{*;}
-keep class com.google.gson.stream.** { *; }
-keep class com.google.gson.examples.android.model.** { *; }

#ali点播
-keep class com.alivc.player.**{*;}
-keep class com.aliyun.clientinforeport.**{*;}
-keep class com.aliyun.vodplayer.**{*;}
-keep class com.aliyun.vodplayerview.**{*;}
-dontwarn com.alivc.player.**

#ali
-keep class com.alibaba.sdk.android.**{*;}
-keep class com.ut.**{*;}
-keep class com.ta.**{*;}

#MP4parser
-keep class com.coremedia.iso.**{*;}
-keep class com.googlecode.mp4parser.**{*;}
-keep class com.mp4parser.**{*;}
-keep class org.mp4parser.aspectj.**{*;}

#ali推送
-keepattributes Signature
-keep class sun.misc.Unsafe { *; }
-keep class com.taobao.** {*;}
-keep class com.alibaba.** {*;}
-keep class com.alipay.** {*;}
-keep class com.ut.** {*;}
-keep class com.ta.** {*;}
-keep class anet.**{*;}
-keep class anetwork.**{*;}
-keep class org.android.spdy.**{*;}
-keep class org.android.agoo.**{*;}
-keep class android.os.**{*;}
-dontwarn com.taobao.**
-dontwarn com.alibaba.**
-dontwarn com.alipay.**
-dontwarn anet.**
-dontwarn org.android.spdy.**
-dontwarn org.android.agoo.**
-dontwarn anetwork.**
-dontwarn com.ut.**
-dontwarn com.ta.**
-dontwarn com.tope365.**

-dontwarn com.tencent.bugly.**
-keep public class com.tencent.bugly.**{*;}


-dontwarn android.content.pm.**
-dontwarn android.util.**

-keepattributes SourceFile,LineNumberTable

-keepclassmembers class * {
   public <init>(org.json.JSONObject);
}
-keepattributes Signature
-keepattributes EnclosingMethod

-keep class com.youku.**{*;}
-keep class com.alibaba.**{*;}
-keep class com.taobao.**{*;}
-keep class com.ut.** { *; }
-keep class cn.com.mma.** { *; }
-keep class cn.mmachina.** { *; }
-keep class com.nostra13.**{*;}

#oss
-keep class com.alibaba.sdk.android.oss.** { *; }
-dontwarn okio.**
-dontwarn org.apache.commons.codec.binary.**

#ARouter
-keep public class com.alibaba.android.arouter.routes.**{*;}
-keep public class com.alibaba.android.arouter.facade.**{*;}
-keep class * implements com.alibaba.android.arouter.facade.template.ISyringe{*;}

# 如果使用了 byType 的方式获取 Service，需添加下面规则，保护接口
-keep interface * implements com.alibaba.android.arouter.facade.template.IProvider

# 如果使用了 单类注入，即不定义接口实现 IProvider，需添加下面规则，保护实现
# -keep class * implements com.alibaba.android.arouter.facade.template.IProvider

#jimu
-keep interface * {
  <methods>;
}
-keep class com.luojilab.component.componentlib.** {*;}
-keep class com.luojilab.gen.router.** {*;}
-keep class * implements com.luojilab.component.componentlib.router.ISyringe {*;}
-keep class * implements com.luojilab.component.componentlib.applicationlike.IApplicationLike {*;}


#---------------------------------3.与js互相调用的类------------------------

#---------------------------------4.反射相关的类和方法-----------------------

