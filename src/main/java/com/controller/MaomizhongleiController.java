package com.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Date;
import java.util.List;
import java.util.Collections;

import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import com.utils.ValidatorUtils;
import com.utils.DeSensUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.annotation.IgnoreAuth;
import com.annotation.SysLog;

import com.entity.MaomizhongleiEntity;
import com.entity.view.MaomizhongleiView;

import com.service.MaomizhongleiService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.EncryptUtil;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 猫咪种类
 * 后端接口
 * @author 
 * @email 
 * @date 2030-12-19 19:43:29
 */
@RestController
@RequestMapping("/maomizhonglei")
public class MaomizhongleiController {
    @Autowired
    private MaomizhongleiService maomizhongleiService;




    



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,MaomizhongleiEntity maomizhonglei,
		HttpServletRequest request){
        EntityWrapper<MaomizhongleiEntity> ew = new EntityWrapper<MaomizhongleiEntity>();



		PageUtils page = maomizhongleiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, maomizhonglei), params), params));
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }
    
    /**
     * 前台列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,MaomizhongleiEntity maomizhonglei, 
		HttpServletRequest request){
        EntityWrapper<MaomizhongleiEntity> ew = new EntityWrapper<MaomizhongleiEntity>();

		PageUtils page = maomizhongleiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, maomizhonglei), params), params));
		
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( MaomizhongleiEntity maomizhonglei){
       	EntityWrapper<MaomizhongleiEntity> ew = new EntityWrapper<MaomizhongleiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( maomizhonglei, "maomizhonglei")); 
        return R.ok().put("data", maomizhongleiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(MaomizhongleiEntity maomizhonglei){
        EntityWrapper< MaomizhongleiEntity> ew = new EntityWrapper< MaomizhongleiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( maomizhonglei, "maomizhonglei")); 
		MaomizhongleiView maomizhongleiView =  maomizhongleiService.selectView(ew);
		return R.ok("查询猫咪种类成功").put("data", maomizhongleiView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        MaomizhongleiEntity maomizhonglei = maomizhongleiService.selectById(id);
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(maomizhonglei,deSens);
        return R.ok().put("data", maomizhonglei);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        MaomizhongleiEntity maomizhonglei = maomizhongleiService.selectById(id);
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(maomizhonglei,deSens);
        return R.ok().put("data", maomizhonglei);
    }
    



    /**
     * 后台保存
     */
    @RequestMapping("/save")
    @SysLog("新增猫咪种类") 
    public R save(@RequestBody MaomizhongleiEntity maomizhonglei, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(maomizhonglei);
        maomizhongleiService.insert(maomizhonglei);
        return R.ok();
    }
    
    /**
     * 前台保存
     */
    @SysLog("新增猫咪种类")
    @RequestMapping("/add")
    public R add(@RequestBody MaomizhongleiEntity maomizhonglei, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(maomizhonglei);
        maomizhongleiService.insert(maomizhonglei);
        return R.ok().put("data",maomizhonglei.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @SysLog("修改猫咪种类")
    public R update(@RequestBody MaomizhongleiEntity maomizhonglei, HttpServletRequest request){
        //ValidatorUtils.validateEntity(maomizhonglei);
        //全部更新
        maomizhongleiService.updateById(maomizhonglei);

        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @SysLog("删除猫咪种类")
    public R delete(@RequestBody Long[] ids){
        maomizhongleiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	











}
