package com.zanlab.grade.dao;

import com.zanlab.grade.domain.Qrcode;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface QrcodeDao {
    @Select("select * from qrcode where activityid=#{activityid}")
    Qrcode findByActivityid(Integer activityid);
    @Insert("insert into qrcode (activityid,url)values(#{activityid},#{url})")
    int Save(Qrcode qrcode);
}
