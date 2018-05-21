package com.design.mode.factory.staticmethod;

import com.design.mode.factory.common.ISender;
import com.design.mode.factory.common.MailSender;
import com.design.mode.factory.common.SmsSender;

public class SendFactory {
	
	public static ISender produceSMS(){
		return new SmsSender();
	}
	
	public static ISender produceEmail(){
		return new MailSender();
	}

}
