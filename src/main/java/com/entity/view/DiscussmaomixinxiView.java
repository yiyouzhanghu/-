package com.entity.view;

import com.entity.DiscussmaomixinxiEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

import java.io.Serializable;
import com.utils.EncryptUtil;
 

/**
 * 猫咪信息评论表
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date 2030-12-19 19:43:32
 */
@TableName("discussmaomixinxi")
public class DiscussmaomixinxiView  extends DiscussmaomixinxiEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public DiscussmaomixinxiView(){
	}
 
 	public DiscussmaomixinxiView(DiscussmaomixinxiEntity discussmaomixinxiEntity){
 	try {
			BeanUtils.copyProperties(this, discussmaomixinxiEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}


}
