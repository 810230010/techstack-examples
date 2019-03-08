package com.usher.sharding.mapper;

import com.usher.sharding.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface UserMapper {
    User findByUserId(Integer userId);
}
