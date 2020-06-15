import me.zoro.rtfsc.reading.entity.BeanA;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author luguanquan
 * @date 2020/6/15 6:30 下午
 */
@DisplayName("IoC源码跟踪测试")
public class IoCTest {

	@Test
	@DisplayName("循环依赖跟踪")
	public void testCircleDependency() {
		// ApplicationContext是容器的高级接口，BeanFacotry（顶级容器/根容器，规范了/定义了容器的基础行为）
		// Spring应用上下文，官方称之为 IoC容器（错误的认识：容器就是map而已；准确来说，map是ioc容器的一个成员，
		// 叫做单例池, singletonObjects,容器是一组组件和过程的集合，包括BeanFactory、单例池、BeanPostProcessor等以及之间的协作流程）

		/**
		 * Ioc容器创建管理Bean对象的，Spring Bean是有生命周期的
		 * 构造器执行、初始化方法执行、Bean后置处理器的before/after方法、：AbstractApplicationContext#refresh#finishBeanFactoryInitialization
		 * Bean工厂后置处理器初始化、方法执行：AbstractApplicationContext#refresh#invokeBeanFactoryPostProcessors
		 * Bean后置处理器初始化：AbstractApplicationContext#refresh#registerBeanPostProcessors
		 */

		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		/**
		 * 关于循环依赖，可以在下面放大打断点看看主要调用方法,另外关注下下面几个方法
		 * AbstractBeanFactory.doGetBean()
		 * DefaultSingletonBeanRegistry.getSingleton()
		 * 哪些场景无法解决循环依赖？ 构造器循环依赖,原型模式循环依赖
		 */
		BeanA beanA = applicationContext.getBean(BeanA.class);
		System.out.println(beanA);
	}
}
