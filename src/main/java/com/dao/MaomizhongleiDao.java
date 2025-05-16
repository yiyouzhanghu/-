package com.dao;

import com.entity.MaomizhongleiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.MaomizhongleiVO;
import com.entity.view.MaomizhongleiView;


/**
 * 猫咪种类
 * 
 * @author 
 * @email 
 * @date 2030-12-19 19:43:29
 */
public interface MaomizhongleiDao extends BaseMapper<MaomizhongleiEntity> {
	
	List<MaomizhongleiVO> selectListVO(@Param("ew") Wrapper<MaomizhongleiEntity> wrapper);
	
	MaomizhongleiVO selectVO(@Param("ew") Wrapper<MaomizhongleiEntity> wrapper);
	
	List<MaomizhongleiView> selectListView(@Param("ew") Wrapper<MaomizhongleiEntity> wrapper);

	List<MaomizhongleiView> selectListView(Pagination page,@Param("ew") Wrapper<MaomizhongleiEntity> wrapper);

	
	MaomizhongleiView selectView(@Param("ew") Wrapper<MaomizhongleiEntity> wrapper);
	

}
