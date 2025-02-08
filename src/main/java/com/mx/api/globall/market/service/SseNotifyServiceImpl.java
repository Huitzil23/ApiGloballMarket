package com.mx.api.globall.market.service;

import java.util.Collections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SseNotifyServiceImpl implements ISseNotifyService{
	
	private static final Logger log = LoggerFactory.getLogger(SseNotifyServiceImpl.class);
	
	@Autowired
	private RestTemplate apiRestTemplate;
	
	@Value("${app.general.sse.notifica.url}")
    private String urlNotificaciones;
	
	@Override
	public boolean sendNotify(String id, String message) {
		boolean response = false; 
		String url = urlNotificaciones + "sse/message/" + id + "?message=" + message;
		log.info(">>> Notificando["+url+"]");
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		HttpEntity<Object> request = new HttpEntity<Object>(null, headers);
		try {
			ResponseEntity<String> responseGet = apiRestTemplate.exchange(url,HttpMethod.GET,request,String.class);
			if (responseGet.getStatusCodeValue() == 200) {
				log.info("Response mensaje. data ["+responseGet.getBody()+"]");
				response = true;
				
			}else {
				log.info("Request Notificacion Fallo. Code: " + responseGet.getStatusCodeValue());
				response = false;
			}
		}catch(Exception e) {
			response = false;
			log.info(e.getMessage());
		}
		
		return response;
	}

}
