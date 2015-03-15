package com.yyb.weixin.util;

import java.util.Timer;
import java.util.TimerTask;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
/**
 * 自启动类，构造一线程任务，定时获取token
 * @author Alex
 *
 */
public class InitBean implements InitializingBean {
	Log log =LogFactory.getLog(InitBean.class);
	@Override
	public void afterPropertiesSet() throws Exception {
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
		timer.schedule(timerTask, 0, interval*1000);
		//timer.cancel();
	}

}
