package com.health.infrared.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import com.orhanobut.logger.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class FileUtils {
    private static final String TAG = "FileUtils";

    /**
     * 删除文件或者目录
     *
     * @param path 指定路径的文件或目录
     * @return 返回操作结果
     */
    public static boolean deleteFile(String path) {
        if (TextUtils.isEmpty(path))
            return true;

        File file = new File(path);
        if (!file.exists())
            return true;

        if (file.isDirectory()) {
            String[] subPaths = file.list();
            for (int i = 0; i < subPaths.length; i++) {
                if (!deleteFile(path)) {
                    return false;
                }
            }
        }
        Log.d(TAG, "deleteFile: " + path);
        return file.delete();
    }


    /**
     * 返回目录
     * 如果手机存在内存卡,则返回内存卡的位置,否则返回当前context的cache目录
     *
     * @param context
     * @param uniqueName
     * @return
     */
    public static File getDiskCacheDir(Context context, String uniqueName) {
        String cachePath;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()) || !Environment.isExternalStorageRemovable()) {
            cachePath = context.getExternalCacheDir().getPath();
        } else {
            cachePath = context.getCacheDir().getPath();
        }
        return new File(cachePath + File.separator + uniqueName);
    }

    /**
     * 创建文件，包括必要的父目录的创建，如果未创建
     *
     * @param file 待创建的文件
     * @return 返回操作结果
     * @throws IOException 创建失败，将抛出该异常
     */
    public static boolean create(File file) throws IOException {
        try {
            if (file.exists()) {
                return true;
            }

            File parent = file.getParentFile();
            parent.mkdirs();

            return file.createNewFile();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public static void saveBitmapToFile(String path, Bitmap bmp) {
        File f = new File(path);
        if (bmp == null) {
            return;
        }
        FileOutputStream fOut = null;
        try {
            File parent = f.getParentFile();
            parent.mkdirs();

            f.createNewFile();
            fOut = new FileOutputStream(f);

            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fOut);

            fOut.flush();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            try {
                fOut.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    //	public static String getOutputMediaFilePath(String name) {
//		return AppContext.getDirectoryManager().getDirPath(DirType.VEDIO) + File.separator + name + ".mp4";
//	}
    public static Uri getOutputPicUri(String name) {
        String fullPath;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            fullPath = Environment.getExternalStorageDirectory() + "/BabyImage" + File.separator + name + ".jpg";
        } else {
            fullPath = Environment.getDataDirectory() + "/BabyImage" + File.separator + name + ".jpg";
        }

        return Uri.fromFile(new File(fullPath));
    }

    public static String getPicPath(String title) {
        String fullPath;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            fullPath = Environment.getExternalStorageDirectory() + "/BabyImage" + File.separator + title + ".jpg";
        } else {
            fullPath = Environment.getDataDirectory() + "/BabyImage" + File.separator + title + ".jpg";
        }
        return fullPath;
    }

    // 判断SD是否存在
    public static boolean hasSDCard() {
        return Environment.MEDIA_MOUNTED.equals(Environment
                .getExternalStorageState());
    }

    public static String SDCachePath() {
        if (hasSDCard()) {
            return Environment.getExternalStorageDirectory().getPath();
        } else {
            return Environment.getDataDirectory().getPath();
        }
    }

    //获取bitmap缓存路径
    public static String GetBitmapCachePath() {
        String path = SDCachePath();
        String flie1 = path + "/qinqinbaby/cache/";
        File f = new File(flie1);
        if (!f.exists()) {
            f.mkdirs();
        }
        return flie1;
    }

    /**
     * @param path 文件夹路径
     */
    public static void isExist(String path) {
        File file = new File(path);
        //判断文件夹是否存在,如果不存在则创建文件夹
        if (!file.exists()) {
            file.mkdir();
        }
    }

    /**
     * @param path 文件夹路径
     */
    public static boolean isExistPath(String path) {
        File file = new File(path);
        //判断文件夹是否存在,如果不存在则创建文件夹
        if (!file.exists()) {
            file.mkdir();
            return false;
        }
        return true;
    }

    public static String loadAssetFile(Context context, String path) {
        try {
            InputStream inputStream = context.getAssets().open(path);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            StringBuilder builder = new StringBuilder();
            while ((line = bufferedReader.readLine()) != null) {
                builder.append(line);
            }
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
            return builder.toString();
        } catch (IOException e) {
            Logger.e("读取AssetFile失败：" + e.toString());
            e.printStackTrace();
        }
        return null;
    }

    public static String createRootPath(Context context) {
        String cacheRootPath;
        if (isSdCardAvailable()) {
            // /sdcard/Android/data/<application package>/cache
            cacheRootPath = context.getExternalCacheDir().getPath();
        } else {
            // /data/data/<application package>/cache
            cacheRootPath = context.getCacheDir().getPath();
        }
        return cacheRootPath;
    }

    public static boolean isSdCardAvailable() {
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
    }

    public static void writeFile(String filePathAndName, String fileContent) {
        try {
            OutputStream outputStream = new FileOutputStream(filePathAndName);
            OutputStreamWriter out = new OutputStreamWriter(outputStream);
            out.write(fileContent);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteFile(File file) {
        if (file.exists()) { // 判断文件是否存在
            if (file.isFile()) { // 判断是否是文件
                file.delete(); // delete()方法 你应该知道 是删除的意思;
            } else if (file.isDirectory()) { // 否则如果它是一个目录
                File files[] = file.listFiles(); // 声明目录下所有的文件 files[];
                for (int i = 0; i < files.length; i++) { // 遍历目录下所有的文件
                    this.deleteFile(files[i]); // 把每个文件 用这个方法进行迭代
                }
            }
            file.delete();
        } else {
        }
    }
}
