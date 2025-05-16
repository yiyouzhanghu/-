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


import com.dao.DiscusschongwufuwuDao;
import com.entity.DiscusschongwufuwuEntity;
import com.service.DiscusschongwufuwuService;
import com.entity.vo.DiscusschongwufuwuVO;
import com.entity.view.DiscusschongwufuwuView;

@Service("discusschongwufuwuService")
public class DiscusschongwufuwuServiceImpl extends ServiceImpl<DiscusschongwufuwuDao, DiscusschongwufuwuEntity> implements DiscusschongwufuwuService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<DiscusschongwufuwuEntity> page = this.selectPage(
                new Query<DiscusschongwufuwuEntity>(params).getPage(),
                new EntityWrapper<DiscusschongwufuwuEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<DiscusschongwufuwuEntity> wrapper) {
		  Page<DiscusschongwufuwuView> page =new Query<DiscusschongwufuwuView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<DiscusschongwufuwuVO> selectListVO(Wrapper<DiscusschongwufuwuEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public DiscusschongwufuwuVO selectVO(Wrapper<DiscusschongwufuwuEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<DiscusschongwufuwuView> selectListView(Wrapper<DiscusschongwufuwuEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public DiscusschongwufuwuView selectView(Wrapper<DiscusschongwufuwuEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
