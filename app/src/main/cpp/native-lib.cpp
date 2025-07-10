#include <jni.h>
#include <string>

extern "C"
JNIEXPORT jstring JNICALL
Java_com_example_ajaynewsapp_utils_NativeLibraryData_getApiKey(JNIEnv *env, jobject thiz) {
    std::string str = "521ff6f1861041e2a6739540bd70ce56";
    return env->NewStringUTF(str.c_str());
}