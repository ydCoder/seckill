package org.seckill.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.entity.SecKill;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SeckillDaoTest {
    @Resource
   private   SeckillDao seckillDao;


    @Test
    public void queryById() throws Exception {
        long id=1000;
        SecKill seckill;
        seckill = seckillDao.queryById(id);
        System.out.println(seckill.getName());
        System.out.println(seckill);
    }

    @Test
    public void queryAll() throws Exception {
        List<SecKill> seckills=seckillDao.queryAll(0,100);
        for(SecKill seckill:seckills){
            System.out.println(seckill);
        }
    }
    @Test
    public void reduceNumber() throws Exception {
        Date  killTime=new Date();
     int updateCount=   seckillDao.reduceNumber(1000L,killTime);
     System.out.print(updateCount);
    }

}