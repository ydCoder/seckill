package org.seckill.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.entity.SecKill;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml",
        "classpath:spring/spring-service.xml"})

public class SeckillServiceTest {
    private final Logger logger= LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillService seckillService;

    @Test
    public void getSeckillList() throws Exception {
        List<SecKill> seckills=seckillService.getSeckillList();
        logger.info ("list{}",seckills);

    }

    @Test
    public void getById() throws Exception {

        long seckillId=1000;
        SecKill seckill=seckillService.getById(seckillId);
        System.out.println(seckill);
    }
    @Test
    public void exportSeckillUrl() throws Exception {

        long seckillId=1003;
        Exposer exposer=seckillService.exportSeckillUrl(seckillId);
        System.out.println(exposer);

    }
   // Exposer{exposed=true, md5='9fadf6710e07b1f9aae17d31e5773952', seckillId=1000, now=0, start=0, end=0}
   @Test//完整逻辑代码测试，注意可重复执行
   public void testSeckillLogic() throws Exception {
       long seckillId=1003;
       Exposer exposer=seckillService.exportSeckillUrl(seckillId);
       if (exposer.isExposed())
       {

           System.out.println(exposer);

           long userPhone=13476191876L;
           String md5=exposer.getMd5();

           try {
               SeckillExecution seckillExecution = seckillService.executeSeckill(seckillId, userPhone, md5);
               System.out.println(seckillExecution);
           }catch (RepeatKillException e)
           {
               e.printStackTrace();
           }catch (SeckillCloseException e1)
           {
               e1.printStackTrace();
           }
       }else {
           //秒杀未开启
           System.out.println(exposer);
       }
   }
}