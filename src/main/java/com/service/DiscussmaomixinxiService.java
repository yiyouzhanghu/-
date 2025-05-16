package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.DiscussmaomixinxiEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.DiscussmaomixinxiVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.DiscussmaomixinxiView;


/**
 * 猫咪信息评论表
 *
 * @author 
 * @email 
 * @date 2030-12-19 19:43:32
 */
public interface DiscussmaomixinxiService extends IService<DiscussmaomixinxiEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<DiscussmaomixinxiVO> selectListVO(Wrapper<DiscussmaomixinxiEntity> wrapper);
   	
   	DiscussmaomixinxiVO selectVO(@Param("ew") Wrapper<DiscussmaomixinxiEntity> wrapper);
   	
   	List<DiscussmaomixinxiView> selectListView(Wrapper<DiscussmaomixinxiEntity> wrapper);
   	
   	DiscussmaomixinxiView selectView(@Param("ew") Wrapper<DiscussmaomixinxiEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<DiscussmaomixinxiEntity> wrapper);

   	

}

