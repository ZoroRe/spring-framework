package me.zoro.rtfsc.reading.entity;

/**
 * @author luguanquan
 * @date 2020/6/15 6:21 下午
 */
public class BeanB {

	private BeanA beanA;

	public BeanB() {
		System.out.println("BeanB 构造器");
	}

	public void setBeanA(BeanA beanA) {
		this.beanA = beanA;
		System.out.println("BeanB 注入 BeanA");
	}
}
