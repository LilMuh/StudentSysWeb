package com.tomh.studentspringboot.mapper;

import com.tomh.studentspringboot.entity.Essay;
import com.tomh.studentspringboot.entity.Params;
import com.tomh.studentspringboot.entity.Player;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import javax.servlet.annotation.HandlesTypes;
import java.util.List;

import java.util.List;

@Repository
public interface EssayDao extends Mapper<Essay> {

    List<Essay> search(@Param("params") Params params);
}
