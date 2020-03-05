package com.nowcoder.community;

import com.nowcoder.community.dao.DiscussPostMapper;
import com.nowcoder.community.dao.MessageMapper;
import com.nowcoder.community.dao.UserMapper;
import com.nowcoder.community.entity.DiscussPost;
import com.nowcoder.community.entity.Message;
import com.nowcoder.community.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes= TestApplication.class)
public class MapperTests {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private DiscussPostMapper discussPostMapper;
    @Autowired
    private MessageMapper messageMapper;
    @Test
    public void testSelectUser(){
        User user=userMapper.selectById(101);
        System.out.println(user);
        user=userMapper.selectByName("liubei");
        System.out.println(user);
        user=userMapper.selectByEmail("nowcoder101@sina.com");
        System.out.println(user);
    }
    @Test
    public void testInsertUser(){
        User user=new User();
        user.setUsername("test");
        user.setPassword("123456");
        user.setSalt("abc");
        user.setEmail("abc@qq.com");
        user.setHeaderUrl("http://wwww.nowcoder.com/101.png");
        user.setCreateTime(new Date());

    int rows=userMapper.insertUser(user);
        System.out.println(rows);
        System.out.println(user.getId());


    }
    @Test
    public void updateUser(){
        int rows=userMapper.updateStatus(150,1);
        System.out.println(rows);
        rows=userMapper.updateHeader(150,"http.baidu.com");
        System.out.println(rows);
        rows=userMapper.updatePassword(150,"banyuan");
        System.out.println(rows);
    }
@Test
    public void testSelectPosts(){
    List<DiscussPost> discussPosts = discussPostMapper.selectDiscussPosts(149, 0,10);
    for(DiscussPost d:discussPosts){
        System.out.println(d);
    }
    int rows=discussPostMapper.selectDiscussPostRows(149);
    System.out.println(rows);
}
@Test
    public void testSelectLetters(){
       List <Message>list= messageMapper.selectConversations(111,0,20);
        for(Message message:list){
            System.out.println(message);
        }
    int i = messageMapper.selectConversationCount(111);
    System.out.println(i);
    list=messageMapper.selectLetters("111_112",0,10);
    System.out.println(list);
    for(Message message:list){
        System.out.println(message);
    }
    int i1 = messageMapper.selectLetterCount("111_112");
    System.out.println(i1);
    int i2 = messageMapper.selectLetterUnreadCount(131, "111_131");
    System.out.println(i2);
}

}
