package com.example.eye20;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.File;
import java.util.Map;

/**
 * DIY设置界面
 */
public class Activity_03 extends Activity {
    private static final String TAG = "Activity_03";
    private SeekBar seekbar0=null;
    private SeekBar seekbar1=null;
    private SeekBar seekbar2=null;
    private SeekBar seekbar3=null;
    private TextView textview2=null;
    private TextView textview6=null;
    private TextView textview11=null;
    private TextView textview16=null;
    String tem;     //温度
    String pre;        //气压
    String fre;       //频率
    String tim;            //时间


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_03);

        textview2=(TextView)findViewById(R.id.text2);
        textview6=(TextView)findViewById(R.id.text6);
        textview11=(TextView)findViewById(R.id.text11);
        textview16=(TextView)findViewById(R.id.text16);

        //读取数据文件中原有的数据
        Map map = DataFile.getUserInfo();
        tem = (String) map.get("tem");
        pre = (String) map.get("pre");
        fre = (String) map.get("fre");
        tim = (String) map.get("tim");
       // String tim = DisPlay.readFileData_1( "datafile4", Activity_03.this);

        textview2.setText(tem);
        textview6.setText(pre);
        textview11.setText(fre);
        textview16.setText(tim);

        /**
         * 动态框与实时数据关联
         */
        SeekBar seekbar = (SeekBar)findViewById(R.id.seekBar0);
        final TextView seekbarValue = (TextView) findViewById(R.id.text2);

        SeekBar seekbar_1 = (SeekBar)findViewById(R.id.seekBar1);
        final TextView seekbarValue_1 = (TextView) findViewById(R.id.text6);

        SeekBar seekbar_2 = (SeekBar)findViewById(R.id.seekBar2);
        final TextView seekbarValue_2 = (TextView) findViewById(R.id.text11);

        SeekBar seekbar_3 = (SeekBar)findViewById(R.id.seekBar3);
        final TextView seekbarValue_3 = (TextView) findViewById(R.id.text16);

        seekbar.setProgress(Integer.parseInt(tem)-25);
        seekbar_3.setProgress(Integer.parseInt(tim));

        /**
         * 根据状态转换对应标志值
         */
        if(pre.equals("低")) {
            seekbar_1.setProgress(0);
        }
        else if(pre.equals("中")) {
            seekbar_1.setProgress(1);
        }
        else {
            seekbar_1.setProgress(2);
        }

        if(fre.equals("弱")) {
            seekbar_2.setProgress(0);
        }
        else if(fre.equals("中")) {
            seekbar_2.setProgress(1);
        }
        else {
            seekbar_2.setProgress(2);
        }

        /**
         * 温度设置
         */
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

            @Override
            public void onProgressChanged(SeekBar seekbar, int progress,
                                          boolean fromUser) {

                progress=progress+25;
                tem=String.valueOf(progress);

                // TODO Auto-generated method stub
                //数据传送给动态框
                seekbarValue.setText(String.valueOf(progress));
                //更新datafile_1数据
                DataFile.saveUserInfo(tem,pre,fre,tim);
                Map map1 = DataFile.getUserInfo();
                Log.e(TAG,"更新后的datafile_1数据："+ map1);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
                seekbar.setProgress(seekBar.getProgress());
            }
        });

        /**
         * 气压设置
         */
        seekbar_1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

            @Override
            public void onProgressChanged(SeekBar seekbar_1, int progress,
                                          boolean fromUser) {
                if(progress==0) {
                    // TODO Auto-generated method stub
                    seekbarValue_1.setText("低");
                    pre="低";
                }
                else if(progress==1) {
                    seekbarValue_1.setText("中");
                    pre="中";
                }
                else {
                    seekbarValue_1.setText("高");
                    pre="高";
                }
                //更新datafile_1数据
                DataFile.saveUserInfo(tem,pre,fre,tim);
                Map map1 = DataFile.getUserInfo();
                Log.e(TAG,"更新后的datafile_1数据："+ map1);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }
        });

        /**
         * 震动频率设置
         */
        seekbar_2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

            @Override
            public void onProgressChanged(SeekBar seekbar_2, int progress,
                                          boolean fromUser) {
                if(progress==0) {
                    // TODO Auto-generated method stub
                    seekbarValue_2.setText("弱");
                    fre="弱";
                }
                else if(progress==1) {
                    seekbarValue_2.setText("中");
                    fre="中";
                }
                else {
                    seekbarValue_2.setText("强");
                    fre="强";
                }
                //更新datafile_1数据
                DataFile.saveUserInfo(tem,pre,fre,tim);
                Map map1 = DataFile.getUserInfo();
                Log.e(TAG,"更新后的datafile_1数据："+ map1);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }
        });

        /**
         * 定时时间设置
         */
        seekbar_3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

            @Override
            public void onProgressChanged(SeekBar seekbar_3, int progress,
                                          boolean fromUser) {
                // TODO Auto-generated method stub
                seekbarValue_3.setText(String.valueOf(progress));
                tim=String.valueOf(progress);
                //MainActivity.menu_1("tim",time);//设置显示版面时间
                //数据存入文件
                DataFile.saveUserInfo(tem,pre,fre,tim);
                Map map1 = DataFile.getUserInfo();
                Log.e(TAG,"更新后的datafile_1数据："+ map1);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }
        });

        Intent intent = new Intent();//只用来携带被传递的值，不跳转活动
        intent.putExtra("temperature",tem);
        setResult(RESULT_OK,intent);

    }




}
