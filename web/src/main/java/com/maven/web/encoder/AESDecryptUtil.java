/**
 * 
 */
/**
 * @author zhangyalin
 *
 */
package com.maven.web.encoder;

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


/*
BASE的加密解密是双向的，可以求反解.
BASEEncoder和BASEDecoder是非官方JDK实现类。虽然可以在JDK里能找到并使用，但是在API里查不到。
JRE 中 sun 和 com.sun 开头包的类都是未被文档化的，他们属于 java, javax 类库的基础，其中的实现大多数与底层平台有关，
一般来说是不推荐使用的。
BASE 严格地说，属于编码格式，而非加密算法
主要就是BASEEncoder、BASEDecoder两个类，我们只需要知道使用对应的方法即可。
另，BASE加密后产生的字节位数是的倍数，如果不够位数以=符号填充。
BASE
按照RFC的定义，Base被定义为：Base内容传送编码被设计用来把任意序列的位字节描述为一种不易被人直接识别的形式。
（The Base Content-Transfer-Encoding is designed to represent arbitrary sequences of octets in a form that need not be humanly readable.）
常见于邮件、http加密，截取http信息，你就会发现登录操作的用户名、密码字段通过BASE加密的。
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
	        String str = "FVvyqxukO+P86cKWvYhJlGwh6TVF00gwYfmswNmxDh8EbbnTuLMAXtrWxVFAuCGmUjPRroMUKGcj+O3iHjJNWe3I+4TwrAYfjIlqKLJSMKKBd6bpowJsZdc9jb3Mkr3w8qVsC9fkRNNvapBjGwVqGw==";
	        System.out.println("原始串："+str);
//	        // 加密
//	        byte[] e=AESDecryptUtil.encryptByKey(str,"C2F9BEBCE8DD3CEDFF8016A4B8248FF4");
//	        System.out.println("密串："+parseByte2HexStr(e));
	        // 解密
	        byte[] ddd = decryptByKey(parseHexStr2Byte(str),"!QA2Z@w1sxO*(-8L");
	        System.out.println("解密后："+new String(ddd));

	    }
}