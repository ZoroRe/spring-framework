package me.zoro.rtfsc.reading.entity;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author luguanquan
 * @date 2020/6/15 6:21 下午
 */
public class BeanA implements InitializingBean, ApplicationContextAware {
	private BeanB beanB;

	public BeanA() {
		System.out.println("BeanA构造器");
	}

	public void setBeanB(BeanB beanB) {
		this.beanB = beanB;
		System.out.println("BeanA 注入 BeanB");
	}


	@Override
	public void afterPropertiesSet() throws Exception {

		System.out.println("BeanA afterPropertiesSet...");
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		System.out.println("BeanA setApplicationContext...");
	}
}
