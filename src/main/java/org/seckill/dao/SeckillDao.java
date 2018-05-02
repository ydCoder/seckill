package org.seckill.dao;

import org.apache.ibatis.annotations.Param;
import org.seckill.entity.SecKill;

import java.util.Date;
import java.util.List;

public interface SeckillDao {
    /*
    * 减库存
    *
    * */

    /**
     * 注意parm注解冒号里不能有空格符
     * @param seckillId
     * @param killTime
     * @return
     */
    int reduceNumber(@Param("seckillId")long  seckillId,@Param("killTime") Date killTime);
 /* 根据id查询秒杀对象 */
   SecKill queryById(long  seckillId);

    /*
  * 根据偏移量查询秒杀商品列表*/
    /*

    Java没有保存形参的记录 必须加pram注解
     */
    List<SecKill>  queryAll(@Param("offset") int offset,@Param("limit")  int  limit);


}
