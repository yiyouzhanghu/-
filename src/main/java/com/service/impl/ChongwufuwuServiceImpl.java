package com.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.utils.PageUtils;
import com.utils.Query;


import com.dao.ChongwufuwuDao;
import com.entity.ChongwufuwuEntity;
import com.service.ChongwufuwuService;
import com.entity.vo.ChongwufuwuVO;
import com.entity.view.ChongwufuwuView;

@Service("chongwufuwuService")
public class ChongwufuwuServiceImpl extends ServiceImpl<ChongwufuwuDao, ChongwufuwuEntity> implements ChongwufuwuService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ChongwufuwuEntity> page = this.selectPage(
                new Query<ChongwufuwuEntity>(params).getPage(),
                new EntityWrapper<ChongwufuwuEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<ChongwufuwuEntity> wrapper) {
		  Page<ChongwufuwuView> page =new Query<ChongwufuwuView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<ChongwufuwuVO> selectListVO(Wrapper<ChongwufuwuEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public ChongwufuwuVO selectVO(Wrapper<ChongwufuwuEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<ChongwufuwuView> selectListView(Wrapper<ChongwufuwuEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public ChongwufuwuView selectView(Wrapper<ChongwufuwuEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
