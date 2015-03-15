package com.yyb.course.util;

import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 * 自启动类，构造一线程任务，定时获取token
 * @author Alex
 *
 */
public class InitServlet extends HttpServlet  {
	
	private static final long serialVersionUID = -4286182486130516459L;
	Log log =LogFactory.getLog(InitServlet.class);
	@Override
	public void init() throws ServletException {
		log.info("InitializingBean");
		
		TimerTask timerTask =new TimerTask() {
			@Override
			public void run() {
				try {
					String token = GetTokenThread.getInstance().getToken();
					if(token==null){
						log.info("observerToken the first");
					}
					//每隔近两个小时 重新获取一次token
					GetTokenThread.getInstance().observerToken();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		};
		Long interval = new Long(GetTokenThread.getInstance().getExpires());
		Timer timer = new Timer();
		timer.schedule(timerTask, 0, (interval-200)*1000);
		//timer.cancel();
	}
}
