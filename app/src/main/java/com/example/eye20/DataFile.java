package com.example.eye20;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 数据文件处置
 */
public class DataFile {
    private static final String TAG = "DataFile";
    private static Context ctx;


    /**
     * 往DIY文件datafile_1写数据
     * @param tem 温度
     * @param pre 气压
     * @param fre 时间
     * @param tim 频率
     * @return Boolean
     */
    public static boolean saveUserInfo(String tem,String pre,String fre,String tim){
        String path = "/data/user/0/com.example.eye20/files/datafile_1.txt";
        PrintStream stream=null;
        try {

            String string = tem + "##" + pre + "##" + fre + "##" + tim;
            stream=new PrintStream(path);//写入的文件path
            stream.print(string);//写入的字符串
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 读数据
     * @return map
     */
    public static Map<String,String> getUserInfo(){
        String path = "/data/user/0/com.example.eye20/files/datafile_1.txt";
        try {
            FileInputStream fis = new FileInputStream(path);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
            String text = reader.readLine();
            if(!TextUtils.isEmpty(text)){
                String[] infos = text.split("##");
                Map<String,String> map = new HashMap<String, String>();
                map.put("tem", infos[0]);
                map.put("pre", infos[1]);
                map.put("fre", infos[2]);
                map.put("tim", infos[3]);
                return map;
            }
            text.split("##");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 覆盖式写入文件
     * @param string 写入的数据
     * @param path 文件路径
     * @return
     */
    public boolean addFile(String string,String path) {

        PrintStream stream=null;
        try {

            stream=new PrintStream(path);//写入的文件path
            stream.print(string);//写入的字符串
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

}
