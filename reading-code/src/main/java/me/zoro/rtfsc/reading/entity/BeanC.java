package me.zoro.rtfsc.reading.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author luguanquan
 * @date 2020/6/15 9:36 下午
 */
@Component
public class BeanC {

	static {
		System.out.println("BeanC 静态代码被载入");
	}

	/***********************************************
	 * C 字段注入 D
	 **********************************************/
	@Autowired
	private BeanD beanD;
	public BeanC() {
		System.out.println("BeanC 构造器...");
	}


	/***********************************************
	 * C 构造器注入 D
	 **********************************************/
//	private BeanD beanD;
//
//	public BeanC(BeanD beanD) {
//		this.beanD = beanD;
//		System.out.println("BeanC 构造器...");
//		System.out.println("BeanC 构造器中...通过构造器注入了 beanD: " + beanD);
//		System.out.println("BeanC 构造器中...此时beanD.etBeanC" + beanD.getBeanC());
//	}

	public BeanD getBeanD() {
		return beanD;
	}
}
