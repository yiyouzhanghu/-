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

import com.entity.DiscussmaomixinxiEntity;
import com.entity.view.DiscussmaomixinxiView;

import com.service.DiscussmaomixinxiService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.EncryptUtil;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 猫咪信息评论表
 * 后端接口
 * @author 
 * @email 
 * @date 2030-12-19 19:43:32
 */
@RestController
@RequestMapping("/discussmaomixinxi")
public class DiscussmaomixinxiController {
    @Autowired
    private DiscussmaomixinxiService discussmaomixinxiService;




    



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,DiscussmaomixinxiEntity discussmaomixinxi,
		HttpServletRequest request){
        EntityWrapper<DiscussmaomixinxiEntity> ew = new EntityWrapper<DiscussmaomixinxiEntity>();



		PageUtils page = discussmaomixinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discussmaomixinxi), params), params));
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }
    
    /**
     * 前台列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,DiscussmaomixinxiEntity discussmaomixinxi, 
		HttpServletRequest request){
        EntityWrapper<DiscussmaomixinxiEntity> ew = new EntityWrapper<DiscussmaomixinxiEntity>();

		PageUtils page = discussmaomixinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discussmaomixinxi), params), params));
		
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( DiscussmaomixinxiEntity discussmaomixinxi){
       	EntityWrapper<DiscussmaomixinxiEntity> ew = new EntityWrapper<DiscussmaomixinxiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( discussmaomixinxi, "discussmaomixinxi")); 
        return R.ok().put("data", discussmaomixinxiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(DiscussmaomixinxiEntity discussmaomixinxi){
        EntityWrapper< DiscussmaomixinxiEntity> ew = new EntityWrapper< DiscussmaomixinxiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( discussmaomixinxi, "discussmaomixinxi")); 
		DiscussmaomixinxiView discussmaomixinxiView =  discussmaomixinxiService.selectView(ew);
		return R.ok("查询猫咪信息评论表成功").put("data", discussmaomixinxiView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        DiscussmaomixinxiEntity discussmaomixinxi = discussmaomixinxiService.selectById(id);
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(discussmaomixinxi,deSens);
        return R.ok().put("data", discussmaomixinxi);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        DiscussmaomixinxiEntity discussmaomixinxi = discussmaomixinxiService.selectById(id);
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(discussmaomixinxi,deSens);
        return R.ok().put("data", discussmaomixinxi);
    }
    



    /**
     * 后台保存
     */
    @RequestMapping("/save")
    @SysLog("新增猫咪信息评论表") 
    public R save(@RequestBody DiscussmaomixinxiEntity discussmaomixinxi, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(discussmaomixinxi);
        discussmaomixinxiService.insert(discussmaomixinxi);
        return R.ok();
    }
    
    /**
     * 前台保存
     */
    @SysLog("新增猫咪信息评论表")
    @RequestMapping("/add")
    public R add(@RequestBody DiscussmaomixinxiEntity discussmaomixinxi, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(discussmaomixinxi);
        discussmaomixinxiService.insert(discussmaomixinxi);
        return R.ok().put("data",discussmaomixinxi.getId());
    }



     /**
     * 获取用户密保
     */
    @RequestMapping("/security")
    @IgnoreAuth
    public R security(@RequestParam String username){
        DiscussmaomixinxiEntity discussmaomixinxi = discussmaomixinxiService.selectOne(new EntityWrapper<DiscussmaomixinxiEntity>().eq("", username));
        return R.ok().put("data", discussmaomixinxi);
    }


    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @IgnoreAuth
    public R update(@RequestBody DiscussmaomixinxiEntity discussmaomixinxi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(discussmaomixinxi);
        //全部更新
        discussmaomixinxiService.updateById(discussmaomixinxi);

        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @SysLog("删除猫咪信息评论表")
    public R delete(@RequestBody Long[] ids){
        discussmaomixinxiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	
	/**
     * 前台智能排序
     */
	@IgnoreAuth
    @RequestMapping("/autoSort")
    public R autoSort(@RequestParam Map<String, Object> params,DiscussmaomixinxiEntity discussmaomixinxi, HttpServletRequest request,String pre){
        EntityWrapper<DiscussmaomixinxiEntity> ew = new EntityWrapper<DiscussmaomixinxiEntity>();
        Map<String, Object> newMap = new HashMap<String, Object>();
        Map<String, Object> param = new HashMap<String, Object>();
		Iterator<Map.Entry<String, Object>> it = param.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, Object> entry = it.next();
			String key = entry.getKey();
			String newKey = entry.getKey();
			if (pre.endsWith(".")) {
				newMap.put(pre + newKey, entry.getValue());
			} else if (StringUtils.isEmpty(pre)) {
				newMap.put(newKey, entry.getValue());
			} else {
				newMap.put(pre + "." + newKey, entry.getValue());
			}
		}
		params.put("sort", "clicktime");
        params.put("order", "desc");
		PageUtils page = discussmaomixinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discussmaomixinxi), params), params));
        return R.ok().put("data", page);
    }











}
