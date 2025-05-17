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

import com.entity.ChargerecordEntity;
import com.entity.view.ChargerecordView;

import com.service.ChargerecordService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.EncryptUtil;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 充值记录表
 * 后端接口
 * @author 
 * @email 
 * @date 2030-12-19 19:43:31
 */
@RestController
@RequestMapping("/chargerecord")
public class ChargerecordController {
    @Autowired
    private ChargerecordService chargerecordService;




    



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,ChargerecordEntity chargerecord,
		HttpServletRequest request){
        if(!request.getSession().getAttribute("role").toString().equals("管理员")) {
            chargerecord.setUserid((Long)request.getSession().getAttribute("userId"));
        }
        EntityWrapper<ChargerecordEntity> ew = new EntityWrapper<ChargerecordEntity>();



		PageUtils page = chargerecordService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, chargerecord), params), params));
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }
    
    /**
     * 前台列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,ChargerecordEntity chargerecord, 
		HttpServletRequest request){
        EntityWrapper<ChargerecordEntity> ew = new EntityWrapper<ChargerecordEntity>();

		PageUtils page = chargerecordService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, chargerecord), params), params));
		
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( ChargerecordEntity chargerecord){
       	EntityWrapper<ChargerecordEntity> ew = new EntityWrapper<ChargerecordEntity>();
      	ew.allEq(MPUtil.allEQMapPre( chargerecord, "chargerecord")); 
        return R.ok().put("data", chargerecordService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(ChargerecordEntity chargerecord){
        EntityWrapper< ChargerecordEntity> ew = new EntityWrapper< ChargerecordEntity>();
 		ew.allEq(MPUtil.allEQMapPre( chargerecord, "chargerecord")); 
		ChargerecordView chargerecordView =  chargerecordService.selectView(ew);
		return R.ok("查询充值记录表成功").put("data", chargerecordView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        ChargerecordEntity chargerecord = chargerecordService.selectById(id);
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(chargerecord,deSens);
        return R.ok().put("data", chargerecord);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        ChargerecordEntity chargerecord = chargerecordService.selectById(id);
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(chargerecord,deSens);
        return R.ok().put("data", chargerecord);
    }
    



    /**
     * 后台保存
     */
    @RequestMapping("/save")
    @SysLog("新增充值记录表") 
    public R save(@RequestBody ChargerecordEntity chargerecord, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(chargerecord);
    	chargerecord.setUserid((Long)request.getSession().getAttribute("userId"));
        chargerecordService.insert(chargerecord);
        return R.ok();
    }
    
    /**
     * 前台保存
     */
    @SysLog("新增充值记录表")
    @RequestMapping("/add")
    public R add(@RequestBody ChargerecordEntity chargerecord, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(chargerecord);
        chargerecordService.insert(chargerecord);
        return R.ok().put("data",chargerecord.getId());
    }



     /**
     * 获取用户密保
     */
    @RequestMapping("/security")
    @IgnoreAuth
    public R security(@RequestParam String username){
        ChargerecordEntity chargerecord = chargerecordService.selectOne(new EntityWrapper<ChargerecordEntity>().eq("", username));
        return R.ok().put("data", chargerecord);
    }


    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @IgnoreAuth
    public R update(@RequestBody ChargerecordEntity chargerecord, HttpServletRequest request){
        //ValidatorUtils.validateEntity(chargerecord);
        //全部更新
        chargerecordService.updateById(chargerecord);

        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @SysLog("删除充值记录表")
    public R delete(@RequestBody Long[] ids){
        chargerecordService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	
	/**
     * 前台智能排序
     */
	@IgnoreAuth
    @RequestMapping("/autoSort")
    public R autoSort(@RequestParam Map<String, Object> params,ChargerecordEntity chargerecord, HttpServletRequest request,String pre){
        EntityWrapper<ChargerecordEntity> ew = new EntityWrapper<ChargerecordEntity>();
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
		PageUtils page = chargerecordService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, chargerecord), params), params));
        return R.ok().put("data", page);
    }











}
