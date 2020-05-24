package com.zuul.gateway;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.jwt.crypto.sign.RsaVerifier;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import io.micrometer.core.instrument.util.IOUtils;

@SpringBootApplication
@EnableDiscoveryClient
@EnableResourceServer
public class SpringZuulRouteApi1Application extends ResourceServerConfigurerAdapter {
	
	public static void main(String[] args) {
		SpringApplication.run(SpringZuulRouteApi1Application.class, args);
	}
	
	@Override
	public void configure(ResourceServerSecurityConfigurer resources) { 
		//resources.tokenServices(tokenServices());
		resources.tokenServices(tokenServices());
	}
		
	@Bean
	@Primary
	public DefaultTokenServices tokenServices() {
		
		DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
		defaultTokenServices.setTokenStore(tokenStore());
		return defaultTokenServices;
	}
	
	@Bean
	public TokenStore tokenStore() {
		return new JwtTokenStore(accessTokenConverter());	
	}
	
	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		
		JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
		Resource resource = new ClassPathResource("/private_key/publicKey.txt");
		String publicKey = null;
		try {
			publicKey = IOUtils.toString(resource.getInputStream());
		} catch(IOException e) {
			throw new RuntimeException(e);
		}
		converter.setVerifierKey(publicKey);
		return converter;
		
	}

}
