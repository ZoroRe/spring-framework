package me.zoro.rtfsc.reading;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @author luguanquan
 * @date 2020/6/15 6:16 下午
 */
public class MyBeanPostProcessor implements BeanPostProcessor {
	public MyBeanPostProcessor() {
		System.out.println("自定义 BeanPostProcessor 构造器");
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		if("BeanA".equals(beanName)){
			System.out.println("BeanPostProcessor 实现类 postProcessBeforeInitialization 方法被调用中......");
		}
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		if("BeanA".equals(beanName)){
			System.out.println("BeanPostProcessor 实现类 postProcessAfterInitialization 方法被调用中......");
		}
		return bean;
	}
}
