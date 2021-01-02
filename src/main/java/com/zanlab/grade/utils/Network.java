package com.zanlab.grade.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.springframework.web.client.RestTemplate;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Network {
    public static String uploadWxQrCode(byte[] byteArray,String fileName){
        final String QINIU_UPLOAD_SITE ="";
        final String ACCESS_KEY="";
        final String SECRET_KEY="";
        final String BUCKET="";
        //构造一个带指定Zone对象的配置类
        InputStream inputStream=new ByteArrayInputStream(byteArray);
        Configuration cfg = new Configuration(Zone.zone0());
        //...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = null;
        Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
        String upToken = auth.uploadToken(BUCKET);
        try {
            Response response = null;

            response = uploadManager.put(inputStream, fileName, upToken, null, null);

            //解析上传成功的结果
            RestTemplate restTemplate = new RestTemplate();
            ObjectMapper mapper=new ObjectMapper();
            DefaultPutRet putRet=mapper.readValue(response.bodyString(),DefaultPutRet.class);
            System.out.println(putRet.key);
            System.out.println(putRet.hash);
            return QINIU_UPLOAD_SITE+putRet.key;
        } catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
