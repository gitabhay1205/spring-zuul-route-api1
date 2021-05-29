package com.zuul.gateway;

import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import org.springframework.stereotype.Component;

//@Component
//public class PublicKeyProcessing {
//
//	public String SECRET_KEY = "secretKeyToSign";
//
//	public RSAPublicKey publicKeyDecoder() throws NoSuchAlgorithmException, InvalidKeySpecException {
//
//		byte[] publicKeybyteArray = Base64.getDecoder().decode(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
//
//		X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(publicKeybyteArray);
//		KeyFactory kf = KeyFactory.getInstance("RSA");
//		RSAPublicKey rsaPublicKey = (RSAPublicKey) kf.generatePublic(publicKeySpec);
//
//		return rsaPublicKey;
//	}
//
//}
