package com.dao;

import com.entity.MaomixinxiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.MaomixinxiVO;
import com.entity.view.MaomixinxiView;


/**
 * 猫咪信息
 * 
 * @author 
 * @email 
 * @date 2030-12-19 19:43:29
 */
public interface MaomixinxiDao extends BaseMapper<MaomixinxiEntity> {
	
	List<MaomixinxiVO> selectListVO(@Param("ew") Wrapper<MaomixinxiEntity> wrapper);
	
	MaomixinxiVO selectVO(@Param("ew") Wrapper<MaomixinxiEntity> wrapper);
	
	List<MaomixinxiView> selectListView(@Param("ew") Wrapper<MaomixinxiEntity> wrapper);

	List<MaomixinxiView> selectListView(Pagination page,@Param("ew") Wrapper<MaomixinxiEntity> wrapper);

	
	MaomixinxiView selectView(@Param("ew") Wrapper<MaomixinxiEntity> wrapper);
	

    List<Map<String, Object>> selectValue(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<MaomixinxiEntity> wrapper);

    List<Map<String, Object>> selectTimeStatValue(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<MaomixinxiEntity> wrapper);

    List<Map<String, Object>> selectGroup(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<MaomixinxiEntity> wrapper);



}
