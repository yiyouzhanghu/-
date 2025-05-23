package com.entity.view;

import com.entity.FuwuyuyueEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

import java.io.Serializable;
import com.utils.EncryptUtil;
 

/**
 * 服务预约
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date 2030-12-19 19:43:30
 */
@TableName("fuwuyuyue")
public class FuwuyuyueView  extends FuwuyuyueEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public FuwuyuyueView(){
	}
 
 	public FuwuyuyueView(FuwuyuyueEntity fuwuyuyueEntity){
 	try {
			BeanUtils.copyProperties(this, fuwuyuyueEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}


}
