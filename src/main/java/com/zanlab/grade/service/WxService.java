package com.zanlab.grade.service;

import com.zanlab.grade.domain.UserLoginRes;

import java.io.IOException;

public interface WxService {

    String getAccessToken() throws IOException;
    UserLoginRes code2Session(String code) throws IOException;
}
