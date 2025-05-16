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

import com.entity.DiscusschongwufuwuEntity;
import com.entity.view.DiscusschongwufuwuView;

import com.service.DiscusschongwufuwuService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.EncryptUtil;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 宠物服务评论表
 * 后端接口
 * @author 
 * @email 
 * @date 2030-12-19 19:43:32
 */
@RestController
@RequestMapping("/discusschongwufuwu")
public class DiscusschongwufuwuController {
    @Autowired
    private DiscusschongwufuwuService discusschongwufuwuService;




    



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,DiscusschongwufuwuEntity discusschongwufuwu,
		HttpServletRequest request){
        EntityWrapper<DiscusschongwufuwuEntity> ew = new EntityWrapper<DiscusschongwufuwuEntity>();



		PageUtils page = discusschongwufuwuService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discusschongwufuwu), params), params));
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }
    
    /**
     * 前台列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,DiscusschongwufuwuEntity discusschongwufuwu, 
		HttpServletRequest request){
        EntityWrapper<DiscusschongwufuwuEntity> ew = new EntityWrapper<DiscusschongwufuwuEntity>();

		PageUtils page = discusschongwufuwuService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discusschongwufuwu), params), params));
		
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( DiscusschongwufuwuEntity discusschongwufuwu){
       	EntityWrapper<DiscusschongwufuwuEntity> ew = new EntityWrapper<DiscusschongwufuwuEntity>();
      	ew.allEq(MPUtil.allEQMapPre( discusschongwufuwu, "discusschongwufuwu")); 
        return R.ok().put("data", discusschongwufuwuService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(DiscusschongwufuwuEntity discusschongwufuwu){
        EntityWrapper< DiscusschongwufuwuEntity> ew = new EntityWrapper< DiscusschongwufuwuEntity>();
 		ew.allEq(MPUtil.allEQMapPre( discusschongwufuwu, "discusschongwufuwu")); 
		DiscusschongwufuwuView discusschongwufuwuView =  discusschongwufuwuService.selectView(ew);
		return R.ok("查询宠物服务评论表成功").put("data", discusschongwufuwuView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        DiscusschongwufuwuEntity discusschongwufuwu = discusschongwufuwuService.selectById(id);
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(discusschongwufuwu,deSens);
        return R.ok().put("data", discusschongwufuwu);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        DiscusschongwufuwuEntity discusschongwufuwu = discusschongwufuwuService.selectById(id);
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(discusschongwufuwu,deSens);
        return R.ok().put("data", discusschongwufuwu);
    }
    



    /**
     * 后台保存
     */
    @RequestMapping("/save")
    @SysLog("新增宠物服务评论表") 
    public R save(@RequestBody DiscusschongwufuwuEntity discusschongwufuwu, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(discusschongwufuwu);
        discusschongwufuwuService.insert(discusschongwufuwu);
        return R.ok();
    }
    
    /**
     * 前台保存
     */
    @SysLog("新增宠物服务评论表")
    @RequestMapping("/add")
    public R add(@RequestBody DiscusschongwufuwuEntity discusschongwufuwu, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(discusschongwufuwu);
        discusschongwufuwuService.insert(discusschongwufuwu);
        return R.ok().put("data",discusschongwufuwu.getId());
    }



     /**
     * 获取用户密保
     */
    @RequestMapping("/security")
    @IgnoreAuth
    public R security(@RequestParam String username){
        DiscusschongwufuwuEntity discusschongwufuwu = discusschongwufuwuService.selectOne(new EntityWrapper<DiscusschongwufuwuEntity>().eq("", username));
        return R.ok().put("data", discusschongwufuwu);
    }


    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @IgnoreAuth
    public R update(@RequestBody DiscusschongwufuwuEntity discusschongwufuwu, HttpServletRequest request){
        //ValidatorUtils.validateEntity(discusschongwufuwu);
        //全部更新
        discusschongwufuwuService.updateById(discusschongwufuwu);

        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @SysLog("删除宠物服务评论表")
    public R delete(@RequestBody Long[] ids){
        discusschongwufuwuService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	
	/**
     * 前台智能排序
     */
	@IgnoreAuth
    @RequestMapping("/autoSort")
    public R autoSort(@RequestParam Map<String, Object> params,DiscusschongwufuwuEntity discusschongwufuwu, HttpServletRequest request,String pre){
        EntityWrapper<DiscusschongwufuwuEntity> ew = new EntityWrapper<DiscusschongwufuwuEntity>();
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
		PageUtils page = discusschongwufuwuService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discusschongwufuwu), params), params));
        return R.ok().put("data", page);
    }











}
