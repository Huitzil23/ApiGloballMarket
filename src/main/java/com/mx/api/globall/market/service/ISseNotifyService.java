package com.mx.api.globall.market.service;

import org.springframework.stereotype.Service;

@Service
public interface ISseNotifyService {

	boolean sendNotify(String id, String message);

}
