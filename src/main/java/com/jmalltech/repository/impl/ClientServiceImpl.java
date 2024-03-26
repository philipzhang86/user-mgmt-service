package com.jmalltech.repository.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jmalltech.entity.Client;
import com.jmalltech.repository.ClientService;
import com.jmalltech.mapper.ClientMapper;
import org.springframework.stereotype.Service;

/**
* @author philipzhang
* @description 针对表【mwms_client】的数据库操作Service实现
* @createDate 2024-03-26 11:56:42
*/
@Service
public class ClientServiceImpl extends ServiceImpl<ClientMapper, Client> implements ClientService{

}




