package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.ChongwufuwuEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.ChongwufuwuVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.ChongwufuwuView;


/**
 * 宠物服务
 *
 * @author 
 * @email 
 * @date 2030-12-19 19:43:30
 */
public interface ChongwufuwuService extends IService<ChongwufuwuEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<ChongwufuwuVO> selectListVO(Wrapper<ChongwufuwuEntity> wrapper);
   	
   	ChongwufuwuVO selectVO(@Param("ew") Wrapper<ChongwufuwuEntity> wrapper);
   	
   	List<ChongwufuwuView> selectListView(Wrapper<ChongwufuwuEntity> wrapper);
   	
   	ChongwufuwuView selectView(@Param("ew") Wrapper<ChongwufuwuEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<ChongwufuwuEntity> wrapper);

   	

}

