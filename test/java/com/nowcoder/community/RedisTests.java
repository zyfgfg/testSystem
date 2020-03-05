package com.nowcoder.community;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes= TestApplication.class)
public class RedisTests {
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testStrings(){
        String redisKey="test:count";

        redisTemplate.opsForValue().set(redisKey,1);
        System.out.println(redisTemplate.opsForValue().get(redisKey));
        System.out.println(redisTemplate.opsForValue().increment(redisKey));
        System.out.println(redisTemplate.opsForValue().decrement(redisKey));

    }
    @Test
    public void testHashs(){
        String redisKey ="test:user";
        redisTemplate.opsForHash().put(redisKey,"id",1);
        redisTemplate.opsForHash().put(redisKey,"username","zhangsan");
        System.out.println( redisTemplate.opsForHash().get(redisKey,"id"));
        System.out.println( redisTemplate.opsForHash().get(redisKey,"username"));

    }
    @Test
    public void testLists(){
        String redisKey ="test:ids";
        redisTemplate.opsForList().leftPush(redisKey,101);
        redisTemplate.opsForList().leftPush(redisKey,102);
        redisTemplate.opsForList().leftPush(redisKey,103);
        System.out.println( redisTemplate.opsForList().size(redisKey));
        System.out.println( redisTemplate.opsForList().index(redisKey,0));
        System.out.println( redisTemplate.opsForList().range(redisKey,0,2));
        System.out.println( redisTemplate.opsForList().leftPop(redisKey));
        System.out.println( redisTemplate.opsForList().leftPop(redisKey));
        System.out.println( redisTemplate.opsForList().leftPop(redisKey));

    }
    @Test
    public  void testSets(){
        String redisKey ="test:teachers";
        redisTemplate.opsForSet().add(redisKey,"sda","saddfasdf","sdaffsd","dfsdfgsffds","sadfgsfsfd");
        System.out.println(redisTemplate.opsForSet().size(redisKey));
        System.out.println(redisTemplate.opsForSet().pop(redisKey));
        System.out.println(redisTemplate.opsForSet().members(redisKey));
    }
    @Test
    public void testSortedSets(){
        String redisKey ="test:teachers";
        redisTemplate.opsForZSet().add(redisKey,"里面",32424);
        redisTemplate.opsForZSet().add(redisKey,"里面=",3224);
        redisTemplate.opsForZSet().add(redisKey,"里面7",3244);
        redisTemplate.opsForZSet().add(redisKey,"里面57",324);
        redisTemplate.opsForZSet().add(redisKey,"里面57657",32423424);
        System.out.println(redisTemplate.opsForZSet().zCard(redisKey));
        System.out.println(redisTemplate.opsForZSet().score(redisKey,"里面"));
        System.out.println(redisTemplate.opsForZSet().reverseRank(redisKey,"里面"));
        System.out.println(redisTemplate.opsForZSet().reverseRange(redisKey,0,2));
    }
    @Test
    public void testKeys(){
        redisTemplate.delete("test:teachers");
        System.out.println(redisTemplate.hasKey("test:teachers"));
        redisTemplate.expire("test:user",10, TimeUnit.SECONDS);
    }
//    多次访问同一个key
    @Test
    public  void testBoundOperations(){
        String redisKey ="test:count";
        BoundValueOperations operations=redisTemplate.boundValueOps(redisKey);
        operations.increment();
        operations.increment();
        operations.increment();
        operations.increment();
        operations.increment();
        System.out.println(operations.get());
    }
//    编程式事务
    @Test
    public void testTransactional(){
        Object obj=redisTemplate.execute(new SessionCallback() {
            @Override
            public Object execute(RedisOperations operations) throws DataAccessException {
                String redisKey ="test:tx";
                operations.multi();
                operations.opsForSet().add(redisKey,"zhangsan");
                operations.opsForSet().add(redisKey,"zhangsan3");
                operations.opsForSet().add(redisKey,"zhangsan2");
                System.out.println(operations.opsForSet().members(redisKey));
                return operations.exec();
            }
        });
        System.out.println(obj);
    }
}
