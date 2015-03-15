package com.yyb.weixin.entity.req;

public class ImageMessage extends BaseMessage{
	 /**
	 * 
	 */
	private static final long serialVersionUID = 3892891918350865881L;
	// 图片链接  
    private String PicUrl;  
  
    public String getPicUrl() {  
        return PicUrl;  
    }  
  
    public void setPicUrl(String picUrl) {  
        PicUrl = picUrl;  
    }  
}
