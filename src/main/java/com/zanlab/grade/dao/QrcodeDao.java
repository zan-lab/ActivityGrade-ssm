package com.zanlab.grade.dao;

import com.zanlab.grade.domain.Qrcode;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface QrcodeDao {
    @Select("select * from qrcode where activityid=#{activityid} and type=#{type}")
    Qrcode findByActivityidType(@Param("activityid") Integer activityid, @Param("type")Integer type);
    @Insert("insert into qrcode (activityid,url,type)values(#{activityid},#{url},#{type})")
    int Save(Qrcode qrcode);
}
