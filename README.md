# MediaCenter [![](https://jitpack.io/v/wong1988/MediaCenter.svg)](https://jitpack.io/#wong1988/MediaCenter)

对系统MediaCenter进行扩展

Step 1. Add it in your root build.gradle at the end of repositories:

```
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

Step 2. Add the dependency

```
dependencies {
    implementation 'com.github.wong1988:MediaCenter:1.0.0'
}
```

## Method

```
// 提供了部分分类的扩展名集合，如：图片、视频、媒体（图片+视频）、文档等
MediaCenter.XXX_EXTENSIONS
// 获取 媒体类型
getMimeType()
// 获取 媒体类型等相关信息
getMediaFileType()
// 是否是某种类型，如：图片类型、视频类型等
isXXXFileType()
```

## Change Log

1.0.1:

* 首个版本发布
 
