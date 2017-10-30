package com.blueocean.common.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.blueocean.common.util.AESDecryptUtil;


/**
 * AES算法--对称加解密
 * @author evelyn
 *
 */
public class AESDecryptUtil {
	
	private static Logger logger = LoggerFactory.getLogger(AESDecryptUtil.class);
	
	private static String encoding ="utf-8";
	
	private static String AES ="AES";
	
     /**将二进制转换成16进制 
      * @param buf 
       * @return 
       */   
      public static String parseByte2HexStr(byte buf[]) {   
              StringBuffer sb = new StringBuffer();   
              for (int i = 0; i < buf.length; i++) {   
                      String hex = Integer.toHexString(buf[i] & 0xFF);   
                      if (hex.length() == 1) {   
                              hex = '0' + hex;   
                      }   
                      sb.append(hex.toUpperCase());   
              }   
              return sb.toString();   
     }   
        
      /**将16进制转换为二进制 
       * @param hexStr 
        * @return 
        */   
       public static byte[] parseHexStr2Byte(String hexStr) {   
               if (hexStr.length() < 1)   
                       return null;   
               byte[] result = new byte[hexStr.length()/2];   
               for (int i = 0;i< hexStr.length()/2; i++) {   
                       int high = Integer.parseInt(hexStr.substring(i*2, i*2+1), 16);   
                       int low = Integer.parseInt(hexStr.substring(i*2+1, i*2+2), 16);   
                       result[i] = (byte) (high * 16 + low);   
               }   
               return result;   
       } 
       
       
       
       /** 
        * 加密 
       *  
        * @param content 需要加密的内容 
       * @param password  加密密码 
       * @return 
        */   
       public static byte[] encryptByKey(String content, String password) {   
               try {              
                       KeyGenerator kgen = KeyGenerator.getInstance(AES);   
                       SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
                       secureRandom.setSeed(password.getBytes());   
                       kgen.init(128, secureRandom);   
                       SecretKey secretKey = kgen.generateKey();   
                       byte[] enCodeFormat = secretKey.getEncoded();   
                       SecretKeySpec key = new SecretKeySpec(enCodeFormat, AES);   
                       Cipher cipher = Cipher.getInstance(AES);// 创建密码器   
                       byte[] byteContent = content.getBytes(encoding);   
                       cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化   
                       byte[] result = cipher.doFinal(byteContent);   
                       return result; // 加密   
               } catch (NoSuchAlgorithmException e) {   
                       logger.error("NoSuchAlgorithmException",e);   
               } catch (NoSuchPaddingException e) {   
                       logger.error("NoSuchPaddingException",e);   
               } catch (InvalidKeyException e) {   
                       logger.error("InvalidKeyException",e);   
               } catch (UnsupportedEncodingException e) {   
                       logger.error("UnsupportedEncodingException",e);   
               } catch (IllegalBlockSizeException e) {   
                       logger.error("IllegalBlockSizeException",e);   
               } catch (BadPaddingException e) {   
                       logger.error("BadPaddingException",e);   
               }   
               return null;   
       }   
         
       /**解密 
        * @param content  待解密内容 
        * @param password 解密密钥 
        * @return 
         */   
        public static byte[] decryptByKey(byte[] content, String password) {   
                try {   
                         KeyGenerator kgen = KeyGenerator.getInstance(AES);
                         SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
                         secureRandom.setSeed(password.getBytes());
                         kgen.init(128, secureRandom);   
                         SecretKey secretKey = kgen.generateKey();   
                         byte[] enCodeFormat = secretKey.getEncoded();   
                         SecretKeySpec key = new SecretKeySpec(enCodeFormat, AES);               
                         Cipher cipher = Cipher.getInstance(AES);// 创建密码器   
                        cipher.init(Cipher.DECRYPT_MODE, key);// 初始化   
                        byte[] result = cipher.doFinal(content);   
                        return result; // 加密   
                } catch (NoSuchAlgorithmException e) {   
                        logger.error("NoSuchAlgorithmException",e);   
                } catch (NoSuchPaddingException e) {   
                        logger.error("NoSuchPaddingException",e);   
                } catch (InvalidKeyException e) {   
                        logger.error("InvalidKeyException",e);   
                } catch (IllegalBlockSizeException e) {   
                        logger.error("IllegalBlockSizeException",e);   
                } catch (BadPaddingException e) {   
                        logger.error("BadPaddingException",e);   
                }   
                return null;   
        }  
    
	    public static void main(String args[]) {
	        String str = "111";
	        System.out.println("原始串："+str);
	        // 加密
	        byte[] e=AESDecryptUtil.encryptByKey(str,"C2F9BEBCE8DD3CEDFF8016A4B8248FF4");
	        System.out.println("密串："+parseByte2HexStr(e));
	        // 解密
	        byte[] ddd = AESDecryptUtil.decryptByKey(parseHexStr2Byte(parseByte2HexStr(e)),"C2F9BEBCE8DD3CEDFF8016A4B8248FF4");
	        System.out.println("解密后："+new String(ddd));

	    }
}
