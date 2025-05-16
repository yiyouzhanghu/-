package com.dao;

import com.entity.ChongwufuwuEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.ChongwufuwuVO;
import com.entity.view.ChongwufuwuView;


/**
 * 宠物服务
 * 
 * @author 
 * @email 
 * @date 2030-12-19 19:43:30
 */
public interface ChongwufuwuDao extends BaseMapper<ChongwufuwuEntity> {
	
	List<ChongwufuwuVO> selectListVO(@Param("ew") Wrapper<ChongwufuwuEntity> wrapper);
	
	ChongwufuwuVO selectVO(@Param("ew") Wrapper<ChongwufuwuEntity> wrapper);
	
	List<ChongwufuwuView> selectListView(@Param("ew") Wrapper<ChongwufuwuEntity> wrapper);

	List<ChongwufuwuView> selectListView(Pagination page,@Param("ew") Wrapper<ChongwufuwuEntity> wrapper);

	
	ChongwufuwuView selectView(@Param("ew") Wrapper<ChongwufuwuEntity> wrapper);
	

}
