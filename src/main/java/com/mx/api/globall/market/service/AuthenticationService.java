package com.mx.api.globall.market.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;
import com.mx.api.globall.market.model.CatalogoPlataformasMarketplace;
import com.mx.api.globall.market.security.AuthenticationMapper;

import javax.servlet.http.HttpServletRequest;

@Component
public class AuthenticationService {
	
	@Autowired
	ICatalogoPlataformasMarketplaceService plataformaMarketService;
	
    private static final String AUTH_TOKEN_HEADER_NAME = "X-API-KEY";
    @SuppressWarnings("unused")
	private static final String AUTH_TOKEN = "b5dd6953-d35b-4ec6-a7b6-e46e41010ddd";
    
    public Authentication getAuthentication(HttpServletRequest request) {
    	String apiKey = request.getHeader(AUTH_TOKEN_HEADER_NAME);
    	
    	String servletPath = request.getServletPath();

    	/*if (apiKey == null || !apiKey.equals(AUTH_TOKEN)) {
            throw new BadCredentialsException("Invalid API Key");
        }*/
    	
    	/*if (apiKey == null || apiKey.trim().isEmpty()) {
            throw new BadCredentialsException("Invalid API Key");
        }*/
        
        /*if(!servletPath.contains("/swagger-") && !servletPath.contains("/api-docs")
        		&& !servletPath.contains("/error") && !servletPath.contains("/csrf")
        		&& !servletPath.contains("/webjars") ) {*/
    	
    	if(servletPath.contains("/ws/")) {
    	
	    	CatalogoPlataformasMarketplace dataMarket = plataformaMarketService.findAllByApiKeyAndEstatus(apiKey, 1);
	    	
	    	if(dataMarket == null || dataMarket.getApiKey()==null || dataMarket.getApiKey().isEmpty()) {
	    		throw new BadCredentialsException("Invalid API Key");
	    	}else if (apiKey == null || !apiKey.equals(dataMarket.getApiKey())) {
	            throw new BadCredentialsException("Invalid API Key");
	        }

        }
        
        return new AuthenticationMapper(apiKey, AuthorityUtils.NO_AUTHORITIES);
    }
}