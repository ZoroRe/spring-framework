package me.zoro.rtfsc.reading.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author luguanquan
 * @date 2020/6/15 9:37 下午
 */
@Component
public class BeanD {
	static {
		System.out.println("BeanD 静态代码被载入");
	}

	/***********************************************
	 * D 字段注入 C
	 **********************************************/
//	@Autowired
//	private BeanC beanC;
//
//	public BeanD() {
//		System.out.println("BeanD 构造器...");
//	}

	/***********************************************
	 * D 构造器注入 C
	 **********************************************/
	private BeanC beanC;

	@Autowired
	public BeanD(BeanC beanC) {
		this.beanC = beanC;
		System.out.println("BeanD 构造器...");
		System.out.println("BeanD 构造器中...通过构造器注入了 beanC: " + beanC);
		System.out.println("BeanD 构造器中...此时beanC.getBeanD()" + beanC.getBeanD());
	}
	public BeanC getBeanC() {
		return beanC;
	}
}
