package com.nowcoder.community.dao;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
//测试
@Repository
@Primary
public class AlphaDaoMyBatisImpl implements AlphaDao{
    @Override
    public String select() {
        return "MyBatis";
    }
}
