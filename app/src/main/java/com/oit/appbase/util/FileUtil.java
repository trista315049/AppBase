package com.oit.appbase.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * @author trista
 * @date 2018/9/26
 * @function
 */
public class FileUtil {
    public static Map<String, RequestBody> getBodyMap(Map<String, File> fileList, String fileName){
        Map<String, RequestBody> bodyMap = new HashMap<>();
        Iterator<Map.Entry<String, File>> entries = fileList.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<String, File> file = entries.next();
            if (file != null && file.getValue() != null && file.getValue().exists()) {
                RequestBody requestFile = RequestBody.create(MediaType.parse("image/png"), file.getValue());
                bodyMap.put(fileName+"\"; filename=\""+file.getKey(),requestFile);
            }
        }
        return  bodyMap;
    }
    public static void deleteFile(Context context, Map<String, File> files) {
        Iterator<Map.Entry<String, File>> entries = files.entrySet().iterator();

        while (entries.hasNext()) {
            Map.Entry<String, File> file = entries.next();
            if (file != null && file.getValue() != null && file.getValue().exists()) {
                file.getValue().delete();
                context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(file.getValue())));
            }
        }


    }
    public static void deleteFile(Context context,File file) {
        if (file != null && file.exists()) {
            file.delete();
            context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(file)));
        }


    }
}
