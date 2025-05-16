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
import com.entity.OrdersEntity;
import com.service.OrdersService;
import com.utils.UserBasedCollaborativeFiltering;

import com.entity.ChongwushangpinEntity;
import com.entity.view.ChongwushangpinView;

import com.service.ChongwushangpinService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.EncryptUtil;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;
import com.service.StoreupService;
import com.entity.StoreupEntity;

/**
 * 宠物商品
 * 后端接口
 * @author 
 * @email 
 * @date 2030-12-19 19:43:30
 */
@RestController
@RequestMapping("/chongwushangpin")
public class ChongwushangpinController {
    @Autowired
    private ChongwushangpinService chongwushangpinService;

    @Autowired
    private StoreupService storeupService;

    @Autowired
    private OrdersService ordersService;


    



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,ChongwushangpinEntity chongwushangpin,
                @RequestParam(required = false) Double pricestart,
                @RequestParam(required = false) Double priceend,
		HttpServletRequest request){
        EntityWrapper<ChongwushangpinEntity> ew = new EntityWrapper<ChongwushangpinEntity>();
                if(pricestart!=null) ew.ge("price", pricestart);
                if(priceend!=null) ew.le("price", priceend);



		PageUtils page = chongwushangpinService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, chongwushangpin), params), params));
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }
    
    /**
     * 前台列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,ChongwushangpinEntity chongwushangpin, 
                @RequestParam(required = false) Double pricestart,
                @RequestParam(required = false) Double priceend,
		HttpServletRequest request){
        EntityWrapper<ChongwushangpinEntity> ew = new EntityWrapper<ChongwushangpinEntity>();
                if(pricestart!=null) ew.ge("price", pricestart);
                if(priceend!=null) ew.le("price", priceend);

		PageUtils page = chongwushangpinService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, chongwushangpin), params), params));
		
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( ChongwushangpinEntity chongwushangpin){
       	EntityWrapper<ChongwushangpinEntity> ew = new EntityWrapper<ChongwushangpinEntity>();
      	ew.allEq(MPUtil.allEQMapPre( chongwushangpin, "chongwushangpin")); 
        return R.ok().put("data", chongwushangpinService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(ChongwushangpinEntity chongwushangpin){
        EntityWrapper< ChongwushangpinEntity> ew = new EntityWrapper< ChongwushangpinEntity>();
 		ew.allEq(MPUtil.allEQMapPre( chongwushangpin, "chongwushangpin")); 
		ChongwushangpinView chongwushangpinView =  chongwushangpinService.selectView(ew);
		return R.ok("查询宠物商品成功").put("data", chongwushangpinView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        ChongwushangpinEntity chongwushangpin = chongwushangpinService.selectById(id);
		chongwushangpin.setClicknum(chongwushangpin.getClicknum()+1);
		chongwushangpinService.updateById(chongwushangpin);
        chongwushangpin = chongwushangpinService.selectView(new EntityWrapper<ChongwushangpinEntity>().eq("id", id));
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(chongwushangpin,deSens);
        return R.ok().put("data", chongwushangpin);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        ChongwushangpinEntity chongwushangpin = chongwushangpinService.selectById(id);
		chongwushangpin.setClicknum(chongwushangpin.getClicknum()+1);
		chongwushangpinService.updateById(chongwushangpin);
        chongwushangpin = chongwushangpinService.selectView(new EntityWrapper<ChongwushangpinEntity>().eq("id", id));
				Map<String, String> deSens = new HashMap<>();
				DeSensUtil.desensitize(chongwushangpin,deSens);
        return R.ok().put("data", chongwushangpin);
    }
    



    /**
     * 后台保存
     */
    @RequestMapping("/save")
    @SysLog("新增宠物商品") 
    public R save(@RequestBody ChongwushangpinEntity chongwushangpin, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(chongwushangpin);
        chongwushangpinService.insert(chongwushangpin);
        return R.ok();
    }
    
    /**
     * 前台保存
     */
    @SysLog("新增宠物商品")
    @RequestMapping("/add")
    public R add(@RequestBody ChongwushangpinEntity chongwushangpin, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(chongwushangpin);
        chongwushangpinService.insert(chongwushangpin);
        return R.ok().put("data",chongwushangpin.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @SysLog("修改宠物商品")
    public R update(@RequestBody ChongwushangpinEntity chongwushangpin, HttpServletRequest request){
        //ValidatorUtils.validateEntity(chongwushangpin);
        //全部更新
        chongwushangpinService.updateById(chongwushangpin);

        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @SysLog("删除宠物商品")
    public R delete(@RequestBody Long[] ids){
        chongwushangpinService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	
	/**
     * 前台智能排序
     */
	@IgnoreAuth
    @RequestMapping("/autoSort")
    public R autoSort(@RequestParam Map<String, Object> params,ChongwushangpinEntity chongwushangpin, HttpServletRequest request,String pre){
        EntityWrapper<ChongwushangpinEntity> ew = new EntityWrapper<ChongwushangpinEntity>();
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
		params.put("sort", "clicknum");
        params.put("order", "desc");
		PageUtils page = chongwushangpinService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, chongwushangpin), params), params));
        return R.ok().put("data", page);
    }


    /**
     * 协同算法（基于用户的协同算法）
     */
    @RequestMapping("/autoSort2")
    public R autoSort2(@RequestParam Map<String, Object> params,ChongwushangpinEntity chongwushangpin, HttpServletRequest request){
        String userId = request.getSession().getAttribute("userId").toString();
        Integer limit = params.get("limit")==null?10:Integer.parseInt(params.get("limit").toString());
        List<OrdersEntity> orders = ordersService.selectList(new EntityWrapper<OrdersEntity>());
        Map<String, Map<String, Double>> ratings = new HashMap<>();
        if(orders!=null && orders.size()>0) {
            for(OrdersEntity o : orders) {
                Map<String, Double> userRatings = null;
                if(ratings.containsKey(o.getUserid().toString())) {
                    userRatings = ratings.get(o.getUserid().toString());
                } else {
                    userRatings = new HashMap<>();
                    ratings.put(o.getUserid().toString(), userRatings);
                }
                if(userRatings.containsKey(o.getGoodid().toString())) {
                    userRatings.put(o.getGoodid().toString(), userRatings.get(o.getGoodid().toString())+1.0);
                } else {
                    userRatings.put(o.getGoodid().toString(), 1.0);
                }

            }
        }
        // 创建协同过滤对象
        UserBasedCollaborativeFiltering filter = new UserBasedCollaborativeFiltering(ratings);

        // 为指定用户推荐物品
        String targetUser = userId;
        int numRecommendations = limit;
        List<String> recommendations = filter.recommendItems(targetUser, numRecommendations);

        // 输出推荐结果
        System.out.println("Recommendations for " + targetUser + ":");
        for (String item : recommendations) {
            System.out.println(item);
        }

        EntityWrapper<ChongwushangpinEntity> ew = new EntityWrapper<ChongwushangpinEntity>();
        ew.in("id", recommendations);
        if(recommendations!=null && recommendations.size()>0) {
            ew.last("order by FIELD(id, "+String.join(",", recommendations)+")");
        }

        PageUtils page = chongwushangpinService.queryPage(params, ew);
        List<ChongwushangpinEntity> pageList = (List<ChongwushangpinEntity>)page.getList();
        if(pageList.size()<limit) {
            int toAddNum = limit-pageList.size();
            ew = new EntityWrapper<ChongwushangpinEntity>();
            ew.notIn("id", recommendations);
            ew.orderBy("id", false);
            ew.last("limit "+toAddNum);
            pageList.addAll(chongwushangpinService.selectList(ew));
        } else if(pageList.size()>limit) {
            pageList = pageList.subList(0, limit);
        }
        page.setList(pageList);

        return R.ok().put("data", page);
    }









}
