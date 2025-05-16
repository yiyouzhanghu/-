package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.MaomixinxiEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.MaomixinxiVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.MaomixinxiView;


/**
 * 猫咪信息
 *
 * @author 
 * @email 
 * @date 2030-12-19 19:43:29
 */
public interface MaomixinxiService extends IService<MaomixinxiEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<MaomixinxiVO> selectListVO(Wrapper<MaomixinxiEntity> wrapper);
   	
   	MaomixinxiVO selectVO(@Param("ew") Wrapper<MaomixinxiEntity> wrapper);
   	
   	List<MaomixinxiView> selectListView(Wrapper<MaomixinxiEntity> wrapper);
   	
   	MaomixinxiView selectView(@Param("ew") Wrapper<MaomixinxiEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<MaomixinxiEntity> wrapper);

   	

    List<Map<String, Object>> selectValue(Map<String, Object> params,Wrapper<MaomixinxiEntity> wrapper);

    List<Map<String, Object>> selectTimeStatValue(Map<String, Object> params,Wrapper<MaomixinxiEntity> wrapper);

    List<Map<String, Object>> selectGroup(Map<String, Object> params,Wrapper<MaomixinxiEntity> wrapper);



}

