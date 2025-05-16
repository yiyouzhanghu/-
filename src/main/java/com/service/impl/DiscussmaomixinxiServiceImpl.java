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


import com.dao.DiscussmaomixinxiDao;
import com.entity.DiscussmaomixinxiEntity;
import com.service.DiscussmaomixinxiService;
import com.entity.vo.DiscussmaomixinxiVO;
import com.entity.view.DiscussmaomixinxiView;

@Service("discussmaomixinxiService")
public class DiscussmaomixinxiServiceImpl extends ServiceImpl<DiscussmaomixinxiDao, DiscussmaomixinxiEntity> implements DiscussmaomixinxiService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<DiscussmaomixinxiEntity> page = this.selectPage(
                new Query<DiscussmaomixinxiEntity>(params).getPage(),
                new EntityWrapper<DiscussmaomixinxiEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<DiscussmaomixinxiEntity> wrapper) {
		  Page<DiscussmaomixinxiView> page =new Query<DiscussmaomixinxiView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<DiscussmaomixinxiVO> selectListVO(Wrapper<DiscussmaomixinxiEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public DiscussmaomixinxiVO selectVO(Wrapper<DiscussmaomixinxiEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<DiscussmaomixinxiView> selectListView(Wrapper<DiscussmaomixinxiEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public DiscussmaomixinxiView selectView(Wrapper<DiscussmaomixinxiEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
