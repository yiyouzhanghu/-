package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.MaomizhongleiEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.MaomizhongleiVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.MaomizhongleiView;


/**
 * 猫咪种类
 *
 * @author 
 * @email 
 * @date 2030-12-19 19:43:29
 */
public interface MaomizhongleiService extends IService<MaomizhongleiEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<MaomizhongleiVO> selectListVO(Wrapper<MaomizhongleiEntity> wrapper);
   	
   	MaomizhongleiVO selectVO(@Param("ew") Wrapper<MaomizhongleiEntity> wrapper);
   	
   	List<MaomizhongleiView> selectListView(Wrapper<MaomizhongleiEntity> wrapper);
   	
   	MaomizhongleiView selectView(@Param("ew") Wrapper<MaomizhongleiEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<MaomizhongleiEntity> wrapper);

   	

}

