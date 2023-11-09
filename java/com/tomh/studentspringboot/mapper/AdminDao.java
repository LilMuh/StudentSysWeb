package com.tomh.studentspringboot.mapper;

import com.tomh.studentspringboot.entity.Admin;
import com.tomh.studentspringboot.entity.Params;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface AdminDao extends Mapper<Admin> {

    List<Admin> search(@Param("params") Params params);

    @Select("select * from admin where username = #{username} limit 1")
    Admin findByName(@Param("username") String username);

    @Select("select * from admin where workerid = #{workerID} limit 1")
    Admin findByWorkerID(@Param("workerID") String workerID);

    @Select("select * from admin where phonenumber = #{phone} limit 1")
    Admin findByPhone(@Param("phone") String phoneNumber);

    @Select("select * from admin where username = #{username} and password = #{password} limit 1")
    Admin findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    @Select("select * from admin where workerid = #{workerID} and phonenumber = #{phoneNumber} limit 1")
    Admin findByWorkerIDAndPhone(String workerID, String phoneNumber);
}
