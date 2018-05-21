package com.design.mode.factory.mulitmethod;

import com.design.mode.factory.common.ISender;
import com.design.mode.factory.common.MailSender;
import com.design.mode.factory.common.SmsSender;

public class SendFactory {
	
	public ISender produceSMS(){
		return new SmsSender();
	}
	
	public ISender produceEmail(){
		return new MailSender();
	}

}
