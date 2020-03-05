package com.nowcoder.community.dao;

import org.springframework.stereotype.Repository;
//测试
@Repository("alphaHibernate")
public class AlphaDaoHibernateImpl implements AlphaDao {
    @Override
    public String select() {
        return "Hibernate";
    }
}
