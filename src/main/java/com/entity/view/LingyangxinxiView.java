package com.entity.view;

import com.entity.LingyangxinxiEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

import java.io.Serializable;
import com.utils.EncryptUtil;
 

/**
 * 领养信息
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date 2030-12-19 19:43:29
 */
@TableName("lingyangxinxi")
public class LingyangxinxiView  extends LingyangxinxiEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public LingyangxinxiView(){
	}
 
 	public LingyangxinxiView(LingyangxinxiEntity lingyangxinxiEntity){
 	try {
			BeanUtils.copyProperties(this, lingyangxinxiEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}


}
