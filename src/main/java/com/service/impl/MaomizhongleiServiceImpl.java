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


import com.dao.MaomizhongleiDao;
import com.entity.MaomizhongleiEntity;
import com.service.MaomizhongleiService;
import com.entity.vo.MaomizhongleiVO;
import com.entity.view.MaomizhongleiView;

@Service("maomizhongleiService")
public class MaomizhongleiServiceImpl extends ServiceImpl<MaomizhongleiDao, MaomizhongleiEntity> implements MaomizhongleiService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<MaomizhongleiEntity> page = this.selectPage(
                new Query<MaomizhongleiEntity>(params).getPage(),
                new EntityWrapper<MaomizhongleiEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<MaomizhongleiEntity> wrapper) {
		  Page<MaomizhongleiView> page =new Query<MaomizhongleiView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<MaomizhongleiVO> selectListVO(Wrapper<MaomizhongleiEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public MaomizhongleiVO selectVO(Wrapper<MaomizhongleiEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<MaomizhongleiView> selectListView(Wrapper<MaomizhongleiEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public MaomizhongleiView selectView(Wrapper<MaomizhongleiEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
