package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.DiscusschongwufuwuEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.DiscusschongwufuwuVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.DiscusschongwufuwuView;


/**
 * 宠物服务评论表
 *
 * @author 
 * @email 
 * @date 2030-12-19 19:43:32
 */
public interface DiscusschongwufuwuService extends IService<DiscusschongwufuwuEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<DiscusschongwufuwuVO> selectListVO(Wrapper<DiscusschongwufuwuEntity> wrapper);
   	
   	DiscusschongwufuwuVO selectVO(@Param("ew") Wrapper<DiscusschongwufuwuEntity> wrapper);
   	
   	List<DiscusschongwufuwuView> selectListView(Wrapper<DiscusschongwufuwuEntity> wrapper);
   	
   	DiscusschongwufuwuView selectView(@Param("ew") Wrapper<DiscusschongwufuwuEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<DiscusschongwufuwuEntity> wrapper);

   	

}

