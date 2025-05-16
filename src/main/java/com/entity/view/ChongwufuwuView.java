package com.entity.view;

import com.entity.ChongwufuwuEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

import java.io.Serializable;
import com.utils.EncryptUtil;
 

/**
 * 宠物服务
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date 2030-12-19 19:43:30
 */
@TableName("chongwufuwu")
public class ChongwufuwuView  extends ChongwufuwuEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public ChongwufuwuView(){
	}
 
 	public ChongwufuwuView(ChongwufuwuEntity chongwufuwuEntity){
 	try {
			BeanUtils.copyProperties(this, chongwufuwuEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}


}
