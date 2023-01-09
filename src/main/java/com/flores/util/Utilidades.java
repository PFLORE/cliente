package com.flores.util;

import java.security.MessageDigest;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import com.flores.dto.MensajeDto;

public class Utilidades {

	public static String Encriptar(String texto) {

        String secretKey =  Constantes.KEY_ENCRIPTAR;
        String base64EncryptedString = "";
        String finalString = "";
        try {
        	if(texto != null) {
        		MessageDigest md = MessageDigest.getInstance("MD5");
                byte[] digestOfPassword = md.digest(secretKey.getBytes("utf-8"));
                byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);

                SecretKey key = new SecretKeySpec(keyBytes, "DESede");
                Cipher cipher = Cipher.getInstance("DESede");
                cipher.init(Cipher.ENCRYPT_MODE, key);

                byte[] plainTextBytes = texto.getBytes("utf-8");
                byte[] buf = cipher.doFinal(plainTextBytes);
                byte[] base64Bytes = Base64.encodeBase64(buf);
                base64EncryptedString = new String(base64Bytes);
                
                finalString = base64EncryptedString.replaceAll("[_/]", "@");
        	} else {
        		finalString = null;
        	}
        	
        } catch (Exception ex) {
        	ex.printStackTrace();
        }
        return finalString;
    }
	
	public static String Desencriptar(String textoEncriptado) throws Exception {

        String secretKey = Constantes.KEY_ENCRIPTAR;
        String base64EncryptedString = "";
        
        try {
        	
        	if(textoEncriptado != null) {
        		String finalString = textoEncriptado.replaceAll("[_@]", "/");
                byte[] message = Base64.decodeBase64(finalString.getBytes("utf-8"));
                MessageDigest md = MessageDigest.getInstance("MD5");
                byte[] digestOfPassword = md.digest(secretKey.getBytes("utf-8"));
                byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
                SecretKey key = new SecretKeySpec(keyBytes, "DESede");

                Cipher decipher = Cipher.getInstance("DESede");
                decipher.init(Cipher.DECRYPT_MODE, key);

                byte[] plainText = decipher.doFinal(message);

                base64EncryptedString = new String(plainText, "UTF-8");
        	} else {
        		base64EncryptedString = null;
        	}

        } catch (Exception ex) {
        }
        return base64EncryptedString;
    }
	
	public static boolean esNumerico(String cadena) {
		try {
			Integer.parseInt(cadena);
			return true;
		} catch (NumberFormatException nfe){
			return false;
		}
	}
	
	@SuppressWarnings({ "rawtypes"})
	public static ResponseEntity<?> validarCampos(BindingResult result) {
		MensajeDto objeto = new MensajeDto();
		result.getFieldErrors().forEach((a) -> {
			objeto.setMensaje("El campo " + a.getField() + " " + a.getDefaultMessage());
		});
		return ResponseEntity.badRequest().body(objeto);
	}
}
