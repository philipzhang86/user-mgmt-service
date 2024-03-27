package com.jmalltech.repository.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jmalltech.entity.Staff;
import com.jmalltech.mapper.StaffMapper;
import com.jmalltech.repository.StaffService;
import org.springframework.stereotype.Service;

/**
* @author philipzhang
* @description 针对表【mwms_staff】的数据库操作Service实现
* @createDate 2024-03-26 14:02:05
*/
@Service
public class StaffServiceImpl extends ServiceImpl<StaffMapper, Staff> implements StaffService{
}




