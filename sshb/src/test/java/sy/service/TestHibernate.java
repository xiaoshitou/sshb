package sy.service;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.yyb.manager.entity.Tuser;
import com.yyb.manager.pageModel.DataGrid;
import com.yyb.manager.pageModel.Pmenu;
import com.yyb.manager.service.UserManagerI;



public class TestHibernate {

	@Test
	public void saveTest(){
		ApplicationContext ac = new ClassPathXmlApplicationContext(new String[]{"classpath:spring-hibernate.xml","classpath:spring.xml"});
		UserManagerI userManager =(UserManagerI) ac.getBean("userService");
		Tuser t= new Tuser();
		t.setName("1");
		t.setPwd("1");
		DataGrid d =userManager.datagrid(t);
		
	}
}
