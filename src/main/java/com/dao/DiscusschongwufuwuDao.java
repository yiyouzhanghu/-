package com.dao;

import com.entity.DiscusschongwufuwuEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.DiscusschongwufuwuVO;
import com.entity.view.DiscusschongwufuwuView;


/**
 * 宠物服务评论表
 * 
 * @author 
 * @email 
 * @date 2030-12-19 19:43:32
 */
public interface DiscusschongwufuwuDao extends BaseMapper<DiscusschongwufuwuEntity> {
	
	List<DiscusschongwufuwuVO> selectListVO(@Param("ew") Wrapper<DiscusschongwufuwuEntity> wrapper);
	
	DiscusschongwufuwuVO selectVO(@Param("ew") Wrapper<DiscusschongwufuwuEntity> wrapper);
	
	List<DiscusschongwufuwuView> selectListView(@Param("ew") Wrapper<DiscusschongwufuwuEntity> wrapper);

	List<DiscusschongwufuwuView> selectListView(Pagination page,@Param("ew") Wrapper<DiscusschongwufuwuEntity> wrapper);

	
	DiscusschongwufuwuView selectView(@Param("ew") Wrapper<DiscusschongwufuwuEntity> wrapper);
	

}
