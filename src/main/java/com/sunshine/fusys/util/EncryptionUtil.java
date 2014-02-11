package com.sunshine.fusys.util;

import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import com.sunshine.fusys.common.CommonConstant;
import com.sunshine.fusys.enums.CommonStateEnum;
import com.sunshine.fusys.exception.BusinessException;

/**
 * 
 * 创建人：fantasy <br>
 * 创建时间：2014-2-10 <br>
 * 功能描述： 提供各种加密功能<br>
 */
public class EncryptionUtil {

	private static Log log = LogFactory.getLog(EncryptionUtil.class);

	private static final Object LOCK = new Object();

	/** 定义 加密算法 */
	private static String ENCRYPT_MODE = "DES";

	/** 默认的密钥 */
	private static String DEFAULT_KEY = "e2s5";

	/** 加密密文 */
	private static Cipher encryptCipher = null;

	/** 解密密文 */
	private static Cipher decryptCipher = null;

	private static char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

	private static EncryptionUtil encryp = null;

	static {
		Security.addProvider(new com.sun.crypto.provider.SunJCE());
		try {
			Key key = getKey(DEFAULT_KEY.getBytes());
			encryptCipher = Cipher.getInstance(ENCRYPT_MODE);
			encryptCipher.init(Cipher.ENCRYPT_MODE, key);

			decryptCipher = Cipher.getInstance(ENCRYPT_MODE);
			decryptCipher.init(Cipher.DECRYPT_MODE, key);
		} catch (Exception e) {
			log.error("产生加密的密钥失败", e);
		}
	}

	/**
	 * 返回Encryption的单实例，并且采用默认的加密密钥。
	 */
	public static EncryptionUtil getInstance() {
		if (encryp == null) {
			synchronized (LOCK) {
				if (encryp == null) {
					encryp = new EncryptionUtil();
				}
			}
		}
		return encryp;
	}

	/**
	 * 
	 * 功能说明：返回Encryption的单实例，并且传入自定义的加密密钥。 参数及返回值:
	 */
	public static EncryptionUtil getInstance(String key) {
		if (encryp == null) {
			synchronized (LOCK) {
				if (encryp == null) {
					encryp = new EncryptionUtil(key);
				}
			}
		}
		return encryp;
	}

	private EncryptionUtil() {
		this(DEFAULT_KEY);
	}

	private EncryptionUtil(String keyStr) {
		Security.addProvider(new com.sun.crypto.provider.SunJCE());
		try {
			Key key = getKey(keyStr.getBytes());
			encryptCipher = Cipher.getInstance(ENCRYPT_MODE);
			encryptCipher.init(Cipher.ENCRYPT_MODE, key);

			decryptCipher = Cipher.getInstance(ENCRYPT_MODE);
			decryptCipher.init(Cipher.DECRYPT_MODE, key);
		} catch (Exception e) {
			log.error("产生加密的密钥失败", e);
		}
	}

	/**
	 * 
	 * 对输入的字符串进行加密
	 */
	public static String encode(String input) throws Exception {
		if (input == null || "".equals(input)) {
			return input;
		}
		return byteArr2HexStr(encode(input.getBytes()));
	}

	/**
	 * 
	 * 对输入的字符串进行解密
	 */
	public static String decode(String input) throws Exception {
		if (input == null || "".equals(input)) {
			return input;
		}
		return new String(decode(hexStr2ByteArr(input)));
	}

	/**
	 * md5加密
	 */
	public static String md5(String password) throws UnsupportedEncodingException, NoSuchAlgorithmException {
		if (password == null) {
			return null;
		}

		byte[] temp = password.getBytes(CommonConstant.UTF_8);
		MessageDigest digest = MessageDigest.getInstance("MD5");
		digest.update(temp);

		byte[] md = digest.digest();
		int length = md.length;
		char buffer[] = new char[length * 2];
		int k = 0;
		for (int i = 0; i < length; i++) {
			byte byte0 = md[i];
			buffer[k++] = hexDigits[byte0 >>> 4 & 0xf];
			buffer[k++] = hexDigits[byte0 & 0xf];
		}
		return new String(buffer);

	}

	/**
	 * 
	 * 功能说明：产生加密的密钥 参数及返回值:
	 * 
	 * @return
	 * @throws BmException
	 */
	private static Key getKey(byte[] arrBTmp) throws Exception {
		// 创建一个空的8位字节数组（默认值为0）
		byte[] arrB = new byte[8];
		// 将原始字节数组转换为8位
		for (int i = 0; i < arrBTmp.length && i < arrB.length; i++) {
			arrB[i] = arrBTmp[i];
		}

		// 生成密钥
		Key key = new javax.crypto.spec.SecretKeySpec(arrB, ENCRYPT_MODE);
		return key;
	}

	@SuppressWarnings("unused")
	private static byte toByte(char c) {
		byte b = (byte) "0123456789ABCDEF".indexOf(c);
		return b;
	}

	/**
	 * 对输入的字节进行解密
	 * 
	 * @param input
	 * @param key
	 * @return
	 * @throws Exception
	 */
	private static byte[] decode(byte[] input) throws Exception {
		return decryptCipher.doFinal(input);
	}

	/**
	 * 对输入的字节进行加密
	 * 
	 * @param input
	 * @param key
	 * @return
	 * @throws Exception
	 */
	private static byte[] encode(byte[] input) throws Exception {
		return encryptCipher.doFinal(input);
	}

	/**
	 * 
	 * 功能说明：将字节转换成字符串 参数及返回值:
	 * 
	 * @return
	 * @throws BmException
	 */
	private static String byteArr2HexStr(byte[] arrB) {
		int iLen = arrB.length;
		// 每个byte用两个字符才能表示，所以字符串的长度是数组长度的两倍
		StringBuffer sb = new StringBuffer(iLen * 2);
		for (int i = 0; i < iLen; i++) {
			int intTmp = arrB[i];
			// 把负数转换为正数
			while (intTmp < 0) {
				intTmp = intTmp + 256;
			} // 小于0F的数需要在前面补0
			if (intTmp < 16) {
				sb.append("0");
			}
			sb.append(Integer.toString(intTmp, 16));
		}
		return sb.toString();
	}

	/**
	 * 
	 * 功能说明：将字符串转换成字节 参数及返回值:
	 * 
	 * @return
	 * @throws BmException
	 */
	private static byte[] hexStr2ByteArr(String strIn) {
		byte[] arrB = strIn.getBytes();
		int iLen = arrB.length;
		// 两个字符表示一个字节，所以字节数组长度是字符串长度除以2
		byte[] arrOut = new byte[iLen / 2];
		for (int i = 0; i < iLen; i = i + 2) {
			String strTmp = new String(arrB, i, 2);
			arrOut[i / 2] = (byte) Integer.parseInt(strTmp, 16);
		}
		return arrOut;
	}

	// ===========================================AES对称加密解密算法==============================================

	/**
	 * AES解密
	 */
	public static String aesEncrypt(String sSrc, String sKey, String sIv) throws Exception {
		if (sKey == null) {
			throw new BusinessException(CommonStateEnum.BADREQ_PARA);
		}
		// 判断Key是否为16位
		if (sKey.length() != 16) {
			throw new BusinessException(CommonStateEnum.BADREQ_PARA);
		}
		byte[] raw = sKey.getBytes();
		SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");// "算法/模式/补码方式"
		IvParameterSpec iv = new IvParameterSpec(sIv.getBytes());// 使用CBC模式，需要一个向量iv，可增加加密算法的强度
		cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
		byte[] encrypted = cipher.doFinal(sSrc.getBytes());

		return new BASE64Encoder().encode(encrypted);// 此处使用BASE64做转码功能，同时能起到2次加密的作用。
	}

	/**
	 * AES解密
	 */
	public static String aesDecrypt(String sSrc, String sKey, String sIv) throws Exception {
		// 判断Key是否正确
		if (sKey == null) {
			throw new BusinessException(CommonStateEnum.BADREQ_PARA);
		}
		// 判断Key是否为16位
		if (sKey.length() != 16) {
			throw new BusinessException(CommonStateEnum.BADREQ_PARA);
		}
		byte[] raw = sKey.getBytes("ASCII");
		SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		IvParameterSpec iv = new IvParameterSpec(sIv.getBytes());
		System.out.println();
		cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
		byte[] encrypted1 = new BASE64Decoder().decodeBuffer(sSrc);// 先用base64解密
		byte[] original = cipher.doFinal(encrypted1);
		String originalString = new String(original);
		return originalString;
	}

	public static void main(String[] args) throws Exception {
		System.out.println(encode("123465"));
	}
}