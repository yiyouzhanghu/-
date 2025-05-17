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

import com.entity.ShangpinfenleiEntity;
import com.entity.view.ShangpinfenleiView;

import com.service.ShangpinfenleiService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.EncryptUtil;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 商品分类
 * 后端接口
 * @author 
 * @email 
 * @date 2030-12-19 19:43:30
 */
@RestController
@RequestMapping("/shangpinfenlei")
public class ShangpinfenleiController {
    @Autowired
    private ShangpinfenleiService shangpinfenleiService;




    



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,ShangpinfenleiEntity shangpinfenlei,
		HttpServletRequest request){
        EntityWrapper<ShangpinfenleiEntity> ew = new EntityWrapper<ShangpinfenleiEntity>();



		PageUtils page = shangpinfenleiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, shangpinfenlei), params), params));
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }
    
    /**
     * 前台列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,ShangpinfenleiEntity shangpinfenlei, 
		HttpServletRequest request){
        EntityWrapper<ShangpinfenleiEntity> ew = new EntityWrapper<ShangpinfenleiEntity>();

		PageUtils page = shangpinfenleiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, shangpinfenlei), params), params));
		
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( ShangpinfenleiEntity shangpinfenlei){
       	EntityWrapper<ShangpinfenleiEntity> ew = new EntityWrapper<ShangpinfenleiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( shangpinfenlei, "shangpinfenlei")); 
        return R.ok().put("data", shangpinfenleiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(ShangpinfenleiEntity shangpinfenlei){
        EntityWrapper< ShangpinfenleiEntity> ew = new EntityWrapper< ShangpinfenleiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( shangpinfenlei, "shangpinfenlei")); 
		ShangpinfenleiView shangpinfenleiView =  shangpinfenleiService.selectView(ew);
		return R.ok("查询商品分类成功").put("data", shangpinfenleiView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        ShangpinfenleiEntity shangpinfenlei = shangpinfenleiService.selectById(id);
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(shangpinfenlei,deSens);
        return R.ok().put("data", shangpinfenlei);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        ShangpinfenleiEntity shangpinfenlei = shangpinfenleiService.selectById(id);
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(shangpinfenlei,deSens);
        return R.ok().put("data", shangpinfenlei);
    }
    



    /**
     * 后台保存
     */
    @RequestMapping("/save")
    @SysLog("新增商品分类") 
    public R save(@RequestBody ShangpinfenleiEntity shangpinfenlei, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(shangpinfenlei);
        shangpinfenleiService.insert(shangpinfenlei);
        return R.ok();
    }
    
    /**
     * 前台保存
     */
    @SysLog("新增商品分类")
    @RequestMapping("/add")
    public R add(@RequestBody ShangpinfenleiEntity shangpinfenlei, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(shangpinfenlei);
        shangpinfenleiService.insert(shangpinfenlei);
        return R.ok().put("data",shangpinfenlei.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @SysLog("修改商品分类")
    public R update(@RequestBody ShangpinfenleiEntity shangpinfenlei, HttpServletRequest request){
        //ValidatorUtils.validateEntity(shangpinfenlei);
        //全部更新
        shangpinfenleiService.updateById(shangpinfenlei);

        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @SysLog("删除商品分类")
    public R delete(@RequestBody Long[] ids){
        shangpinfenleiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	











}
