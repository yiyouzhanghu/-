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


import com.dao.MaomixinxiDao;
import com.entity.MaomixinxiEntity;
import com.service.MaomixinxiService;
import com.entity.vo.MaomixinxiVO;
import com.entity.view.MaomixinxiView;

@Service("maomixinxiService")
public class MaomixinxiServiceImpl extends ServiceImpl<MaomixinxiDao, MaomixinxiEntity> implements MaomixinxiService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<MaomixinxiEntity> page = this.selectPage(
                new Query<MaomixinxiEntity>(params).getPage(),
                new EntityWrapper<MaomixinxiEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<MaomixinxiEntity> wrapper) {
		  Page<MaomixinxiView> page =new Query<MaomixinxiView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<MaomixinxiVO> selectListVO(Wrapper<MaomixinxiEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public MaomixinxiVO selectVO(Wrapper<MaomixinxiEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<MaomixinxiView> selectListView(Wrapper<MaomixinxiEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public MaomixinxiView selectView(Wrapper<MaomixinxiEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}

    @Override
    public List<Map<String, Object>> selectValue(Map<String, Object> params, Wrapper<MaomixinxiEntity> wrapper) {
        return baseMapper.selectValue(params, wrapper);
    }

    @Override
    public List<Map<String, Object>> selectTimeStatValue(Map<String, Object> params, Wrapper<MaomixinxiEntity> wrapper) {
        return baseMapper.selectTimeStatValue(params, wrapper);
    }

    @Override
    public List<Map<String, Object>> selectGroup(Map<String, Object> params, Wrapper<MaomixinxiEntity> wrapper) {
        return baseMapper.selectGroup(params, wrapper);
    }




}
