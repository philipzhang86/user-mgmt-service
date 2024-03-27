package com.jmalltech.mapper;

import com.jmalltech.entity.Client;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
* @author philipzhang
* @description 针对表【mwms_client】的数据库操作Mapper
* @createDate 2024-03-26 11:56:42
* @Entity com.jmalltech.entity.Client
*/
public interface ClientMapper extends BaseMapper<Client> {
    Client selectByUsername(@Param("username") String username);

}




