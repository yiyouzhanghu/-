package com.entity.view;

import com.entity.MaomizhongleiEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

import java.io.Serializable;
import com.utils.EncryptUtil;
 

/**
 * 猫咪种类
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date 2030-12-19 19:43:29
 */
@TableName("maomizhonglei")
public class MaomizhongleiView  extends MaomizhongleiEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public MaomizhongleiView(){
	}
 
 	public MaomizhongleiView(MaomizhongleiEntity maomizhongleiEntity){
 	try {
			BeanUtils.copyProperties(this, maomizhongleiEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}


}
