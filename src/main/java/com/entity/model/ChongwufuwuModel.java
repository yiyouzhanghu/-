package com.entity.model;

import com.entity.ChongwufuwuEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 

/**
 * 宠物服务
 * 接收传参的实体类  
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了） 
 * 取自ModelAndView 的model名称
 * @author 
 * @email 
 * @date 2030-12-19 19:43:30
 */
public class ChongwufuwuModel  implements Serializable {
	private static final long serialVersionUID = 1L;

	 			
	/**
	 * 服务类型
	 */
	
	private String fuwuleixing;
		
	/**
	 * 封面
	 */
	
	private String fengmian;
		
	/**
	 * 人数
	 */
	
	private Integer columns;
		
	/**
	 * 次数
	 */
	
	private Integer rows;
		
	/**
	 * 总数
	 */
	
	private Integer totals;
		
	/**
	 * 可约时间
	 */
	
	private String opentime;
		
	/**
	 * 服务介绍
	 */
	
	private String fuwujieshao;
		
	/**
	 * 最近点击时间
	 */
		
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat 
	private Date clicktime;
		
	/**
	 * 评论数
	 */
	
	private Integer discussnum;
		
	/**
	 * 收藏数
	 */
	
	private Integer storeupnum;
				
	
	/**
	 * 设置：服务类型
	 */
	 
	public void setFuwuleixing(String fuwuleixing) {
		this.fuwuleixing = fuwuleixing;
	}
	
	/**
	 * 获取：服务类型
	 */
	public String getFuwuleixing() {
		return fuwuleixing;
	}
				
	
	/**
	 * 设置：封面
	 */
	 
	public void setFengmian(String fengmian) {
		this.fengmian = fengmian;
	}
	
	/**
	 * 获取：封面
	 */
	public String getFengmian() {
		return fengmian;
	}
				
	
	/**
	 * 设置：人数
	 */
	 
	public void setColumns(Integer columns) {
		this.columns = columns;
	}
	
	/**
	 * 获取：人数
	 */
	public Integer getColumns() {
		return columns;
	}
				
	
	/**
	 * 设置：次数
	 */
	 
	public void setRows(Integer rows) {
		this.rows = rows;
	}
	
	/**
	 * 获取：次数
	 */
	public Integer getRows() {
		return rows;
	}
				
	
	/**
	 * 设置：总数
	 */
	 
	public void setTotals(Integer totals) {
		this.totals = totals;
	}
	
	/**
	 * 获取：总数
	 */
	public Integer getTotals() {
		return totals;
	}
				
	
	/**
	 * 设置：可约时间
	 */
	 
	public void setOpentime(String opentime) {
		this.opentime = opentime;
	}
	
	/**
	 * 获取：可约时间
	 */
	public String getOpentime() {
		return opentime;
	}
				
	
	/**
	 * 设置：服务介绍
	 */
	 
	public void setFuwujieshao(String fuwujieshao) {
		this.fuwujieshao = fuwujieshao;
	}
	
	/**
	 * 获取：服务介绍
	 */
	public String getFuwujieshao() {
		return fuwujieshao;
	}
				
	
	/**
	 * 设置：最近点击时间
	 */
	 
	public void setClicktime(Date clicktime) {
		this.clicktime = clicktime;
	}
	
	/**
	 * 获取：最近点击时间
	 */
	public Date getClicktime() {
		return clicktime;
	}
				
	
	/**
	 * 设置：评论数
	 */
	 
	public void setDiscussnum(Integer discussnum) {
		this.discussnum = discussnum;
	}
	
	/**
	 * 获取：评论数
	 */
	public Integer getDiscussnum() {
		return discussnum;
	}
				
	
	/**
	 * 设置：收藏数
	 */
	 
	public void setStoreupnum(Integer storeupnum) {
		this.storeupnum = storeupnum;
	}
	
	/**
	 * 获取：收藏数
	 */
	public Integer getStoreupnum() {
		return storeupnum;
	}
			
}
