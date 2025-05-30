package com.dao;

import com.entity.FuwuyuyueEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.FuwuyuyueVO;
import com.entity.view.FuwuyuyueView;


/**
 * 服务预约
 * 
 * @author 
 * @email 
 * @date 2030-12-19 19:43:30
 */
public interface FuwuyuyueDao extends BaseMapper<FuwuyuyueEntity> {
	
	List<FuwuyuyueVO> selectListVO(@Param("ew") Wrapper<FuwuyuyueEntity> wrapper);
	
	FuwuyuyueVO selectVO(@Param("ew") Wrapper<FuwuyuyueEntity> wrapper);
	
	List<FuwuyuyueView> selectListView(@Param("ew") Wrapper<FuwuyuyueEntity> wrapper);

	List<FuwuyuyueView> selectListView(Pagination page,@Param("ew") Wrapper<FuwuyuyueEntity> wrapper);

	
	FuwuyuyueView selectView(@Param("ew") Wrapper<FuwuyuyueEntity> wrapper);
	

    List<Map<String, Object>> selectValue(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<FuwuyuyueEntity> wrapper);

    List<Map<String, Object>> selectTimeStatValue(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<FuwuyuyueEntity> wrapper);

    List<Map<String, Object>> selectGroup(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<FuwuyuyueEntity> wrapper);



}
