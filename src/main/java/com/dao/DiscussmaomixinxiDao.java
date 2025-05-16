package com.dao;

import com.entity.DiscussmaomixinxiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.DiscussmaomixinxiVO;
import com.entity.view.DiscussmaomixinxiView;


/**
 * 猫咪信息评论表
 * 
 * @author 
 * @email 
 * @date 2030-12-19 19:43:32
 */
public interface DiscussmaomixinxiDao extends BaseMapper<DiscussmaomixinxiEntity> {
	
	List<DiscussmaomixinxiVO> selectListVO(@Param("ew") Wrapper<DiscussmaomixinxiEntity> wrapper);
	
	DiscussmaomixinxiVO selectVO(@Param("ew") Wrapper<DiscussmaomixinxiEntity> wrapper);
	
	List<DiscussmaomixinxiView> selectListView(@Param("ew") Wrapper<DiscussmaomixinxiEntity> wrapper);

	List<DiscussmaomixinxiView> selectListView(Pagination page,@Param("ew") Wrapper<DiscussmaomixinxiEntity> wrapper);

	
	DiscussmaomixinxiView selectView(@Param("ew") Wrapper<DiscussmaomixinxiEntity> wrapper);
	

}
