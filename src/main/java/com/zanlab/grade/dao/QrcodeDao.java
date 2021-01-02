package com.zanlab.grade.dao;

import com.zanlab.grade.domain.Qrcode;
import org.springframework.stereotype.Repository;

@Repository
public interface QrcodeDao {
    Qrcode findByActivityid(Integer activityid);
    int Save(Qrcode qrcode);
}
