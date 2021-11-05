package com.habuma.spitter.alerts;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import com.habuma.spitter.domain.Spittle;

@Component("alertService")
public class AlertServiceImpl implements AlertService {
	
	@Autowired
	private JmsTemplate jmsTamplate;

	@Override
	public void sendSpittleAlert(Spittle spittle) {
		
		//Specifies destination - spittle.alert.queue
		
		jmsTamplate.send("spittle.alert.queue", new MessageCreator(){
			
			public Message createMessage  (Session session) throws JMSException {
				
				return session.createObjectMessage(spittle);
				
			}
			
		});

	}

}
