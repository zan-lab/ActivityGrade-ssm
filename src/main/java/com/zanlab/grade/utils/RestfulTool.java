package com.zanlab.grade.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class RestfulTool {
    /**
     *  只有data的模式，默认成功,检查是否数据对了
     * @param data 单个模型或者list
     * @return 返回string修饰过的json数据
     */
    public static String JsonResult(Object data){
        if(data==null){
            return JsonResult(-3,"数据未找到");
        }
        if(data instanceof List){
            if(((List)data).size()==0){
                return JsonResult(-3,"数据未找到");
            }
        }
         return JsonResult(data,0,"");
    }

    /**
     * 成功的返回
     * @return
     */
    public static String JsonResult(){
        return JsonResult(null,0,"");
    }

    /**
     * 完整的数据返回模板
     * @param data 返回的数据
     * @param code  返回的状态码（预定义的）
     * @param msg 返回的错误信息
     * @return
     */
    public static String JsonResult(Object data,Integer code,String msg){
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

    /**
     * 返回错误信息的模板
     * @param code  错误代码
     * @param msg   错误信息
     * @return
     */
    public static String JsonResult(Integer code,String msg){
        return JsonResult(null,code,msg);
    }

    /**
     * 自己封装data，用于返回非domain的数据
     * @param key 键
     * @param value 值
     * @return Map对象，可以配合放在JsonResult
     */
    public static Map<String,Object>RetObject(String key,Object value){
        Map<String,Object> res=new HashMap<>();
        res.put(key,value);
        return res;
    }

}
