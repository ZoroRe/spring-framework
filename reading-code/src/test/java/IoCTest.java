import me.zoro.rtfsc.reading.config.ReadingConfig;
import me.zoro.rtfsc.reading.entity.BeanA;
import me.zoro.rtfsc.reading.entity.BeanC;
import me.zoro.rtfsc.reading.entity.BeanD;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
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
		 * RTFSC
		 * Ioc容器创建管理Bean对象的，Spring Bean是有生命周期的
		 * 构造器执行、初始化方法执行、Bean后置处理器的before/after方法、：AbstractApplicationContext#refresh#finishBeanFactoryInitialization
		 * Bean工厂后置处理器初始化、方法执行：AbstractApplicationContext#refresh#invokeBeanFactoryPostProcessors
		 * Bean后置处理器初始化：AbstractApplicationContext#refresh#registerBeanPostProcessors
		 */

		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		/**
		 * RTFSC
		 * 关于循环依赖，可以在下面放大打断点看看主要调用方法,另外关注下下面几个方法
		 * AbstractBeanFactory.doGetBean()
		 * DefaultSingletonBeanRegistry.getSingleton()
		 * 哪些场景无法解决循环依赖？ 构造器循环依赖,原型模式循环依赖
		 */
		BeanA beanA = applicationContext.getBean(BeanA.class);
		System.out.println(beanA);
	}

	@DisplayName("纯注解模式:循环依赖问题-构造器分析1")
	@Test
	public void testCircleDependency2() {
		System.out.println("分别针对 BeanC 和 BeanD 中的构造器谁依赖谁不同场景测试");
		ApplicationContext context = new AnnotationConfigApplicationContext(ReadingConfig.class);
		/**
		 * RTFSC
		 * 1.如果D构造器依赖C，C字段注入D，可以运行成功,不会触发 UnsatisfiedDependencyException
		 * 此时看日志，首先构造C，然后在注入D的时候，去创建D，此时的C已经可以使用并注入
		 * 2.如果是C构造器依赖D,D字段注入C,运行触发 UnsatisfiedDependencyException
		 * 此时看日志，首先构造D,然后就发生了错误。。。所以我猜测这时候的构造D是在解析C的时候，发现构造器需要传入D，然后才去创建的D，
		 * 因此这是C还完全不可用
		 *
		 * 总结出现这种原因是 bean 创建过程扫描需要创建的 bean 总有个先后过程，然后这里C总是在D之前，这时候如果C构造器依赖D,就无法创建出C,
		 * 而如果这时候C构造器没有依赖，已经可以创建出来，然后注入D的时候就可以使用这个C
		 */
		System.out.println("有意思的是=====划重点=====\n\n" +
				"\n=====划重点结束======");
//		BeanD beanD = context.getBean(BeanD.class);
//		System.out.println("beanD构造器已执行完，此时 beanD=" + beanD);
//		System.out.println("beanD构造器已执行完，此时beanD.getBeanC().getBeanD():" + beanD.getBeanC().getBeanD());
	}

	@DisplayName("纯注解模式:循环依赖问题-构造器注入分析2")
	@Test
	public void testCircleDependency3() {

		ApplicationContext context = new AnnotationConfigApplicationContext(ReadingConfig.class);
		BeanC beanC = context.getBean(BeanC.class);
		System.out.println("通过  构造器注入分析 1和2 说明单项构造器注入时的依赖注入还是可以的，但在构造内注入部分");
	}
}
