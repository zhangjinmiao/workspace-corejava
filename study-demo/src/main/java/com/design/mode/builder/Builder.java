package com.design.mode.builder;

import java.util.ArrayList;
import java.util.List;

import com.design.mode.factory.common.ISender;
import com.design.mode.factory.common.MailSender;
import com.design.mode.factory.common.SmsSender;

public class Builder {
	private List<ISender> list = new ArrayList<ISender>();  
    
    public void produceMailSender(int count){  
        for(int i=0; i<count; i++){  
            list.add(new MailSender());  
        }  
    }  
      
    public void produceSmsSender(int count){  
        for(int i=0; i<count; i++){  
            list.add(new SmsSender());  
        }  
    }  
}
