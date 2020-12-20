package com.zanlab.grade.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class RestfulTool {
    /**
     *
     * @param data
     * @param
     * @return 返回数据
     */
    public static String JsonResult(Object data){
        return JsonResult(data,200,"");
    }
    public static String JsonResult(){
        return JsonResult(null,200,"");
    }
    public static String JsonResult(Object data,Integer code,String msg){
        if(data==null){
            return JsonResult(-3,"数据未找到");
        }
        else if(data instanceof List){
            if(((List) data).size()==0){
                return JsonResult(-3,"数据未找到");
            }
        }
        Map<String, Object> map = new HashMap<>();
        map.put("code",code);
        map.put("msg",msg);
        map.put("data",data );
        ObjectMapper mapper = new ObjectMapper();
        String result = "";
        try {
            result = mapper.writeValueAsString(map);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return result;
    }
    public static String JsonResult(Integer code,String msg){
        return JsonResult(null,code,msg);
    }
    public static Map<String,Object>RetObject(String key,Object value){
        Map<String,Object> res=new HashMap<>();
        res.put(key,value);
        return res;
    }

}
