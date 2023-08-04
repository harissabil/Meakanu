# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
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

-keep class com.harissabil.meakanu.data.local.* { *; }
-keep class com.harissabil.meakanu.data.remote.response.* { *; }
-keep class com.harissabil.meakanu.glide.* { *; }
-keep class com.harissabil.meakanu.di.* { *; }
-keep class retrofit.** { *; }
-keep class retrofit2.** { *; }
-keep class okio.** { *; }
-keep class okhttp3.** { *; }
-keep class com.google.gson.** { *; }
-keep class * extends androidx.fragment.app.Fragment{}
-keep class androidx.navigation.fragment.NavHostFragment
-keepnames class * extends android.os.Parcelable
-keepnames class * extends java.io.Serializable