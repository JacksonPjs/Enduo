package com.pvj.xlibrary.log;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class LogDEMO extends AppCompatActivity {

    private String JSON_CONTENT = "{\"weatherinfo\":{\"city\":\"北京\",\"cityid\":\"101010100\"," +
            "\"temp\":\"18\",\"WD\":\"东南风\",\"WS\":\"1级\",\"SD\":\"17%\",\"WSE\":\"1\"," +
            "\"time\":\"17:05\",\"isRadar\":\"1\",\"Radar\":\"JC_RADAR_AZ9010_JB\"," +
            "\"njd\":\"暂无实况\",\"qy\":\"1011\",\"rain\":\"0\"}}";

    private String XML_CONTENT = "<china dn=\"nay\"><city quName=\"黑龙江\" pyName=\"heilongjiang\" " +
            "cityname=\"哈尔滨\" state1=\"1\" state2=\"1\" stateDetailed=\"多云\"/><city quName=\"吉林\"" +
            " pyName=\"jilin\" " +
            "cityname=\"长春\" state1=\"0\" state2=\"0\" stateDetailed=\"晴\"/><city quName=\"辽宁\" " +
            "pyName=\"liaoning\" " +
            "cityname=\"沈阳\" state1=\"1\" state2=\"0\" stateDetailed=\"多云转晴\"/><city " +
            "quName=\"海南\" pyName=\"hainan\" " +
            "cityname=\"海口\" state1=\"22\" state2=\"21\" stateDetailed=\"中到大雨转小到中雨\"/></china>";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     //   setContentView(R.layout.activity_main);

        Logger.d("执行了 onCreate");

        // ======================
        // JSON 类型数据
       Logger.json(JSON_CONTENT);
//         ======================
//         XML 类型数据
       Logger.xml(XML_CONTENT);
//         ======================
//         List 类型数据
       List<String> list = new ArrayList<>();
       list.add("hello");
        list.add("world");
       Logger.d(list);
//         ======================
//         Map 类型数据
        Map<String, String> map = new HashMap<>();
        map.put("key_hello", "hello");
        map.put("key_world", "world");
        Logger.d(map);
//         ======================
//         Set 类型数据
        Set<String> set = new HashSet<>();
        set.add(new String("hello"));
        set.add(new String("world"));
        Logger.d(set);
//         ======================
//         字符串格式化
       Logger.d("hello %s %d", "world", 5);
//         ======================
//         自定义 TAG
      Logger.d("hello world");
//         ======================
//         临时 TAG
        Logger.t("MyTag").d("hello world");
//         ======================
//         数组越界异常
       int[] a = new int[3];
       try {
           a[4] = 3;
       } catch (Exception e) {
            Logger.e(e, "message");
       }
    }
}
