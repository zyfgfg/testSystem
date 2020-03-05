package com.nowcoder.community.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CommunityUtil {
//    生成随机字符串
    public static String generateUUID(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }

//    MD5加密
    public static String md5(String key){
        if(StringUtils.isBlank(key)){
            return null;
        }
        return DigestUtils.md5DigestAsHex(key.getBytes());
    }

    public static String getJSONString(int code, String msg, Map<String,Object> map){
        JSONObject json=new JSONObject();
        json.put("code",code);
        json.put("msg",msg);
        if(map!=null){
            for(String key:map.keySet()){
                json.put(key,map.get(key));
            }
        }
        return json.toJSONString();
    }

public static String getJSONString(int code,String msg){
        return getJSONString(code,msg,null);

}public static String getJSONString(int code){
        return getJSONString(code,null,null);

}

    public static void main(String[] args) {
        Map<String ,Object> map=new HashMap<>();
        map.put("name","zhangsan");
        map.put("age",25);
        System.out.println(getJSONString(0,"ok",map));
    }
//    ajax示例
    @RequestMapping(path="/ajax",method = RequestMethod.POST)
    @ResponseBody
    public String testAJAX(String name,int age){
        System.out.println(name);
        System.out.println(age);
        return CommunityUtil.getJSONString(0,"操作成功！");
    }

}
