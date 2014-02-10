package com.sunshine.fusys.util;

import java.security.Key;
import java.security.MessageDigest;
import java.security.Security;

import javax.crypto.Cipher;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 * 创建人：夏天松 <br>
 * 创建时间：2014-2-10 <br>
 * 功能描述： 提供各种加密功能<br>
 */
public class EncryptionUtil {

	private static Log LOG = LogFactory.getLog(EncryptionUtil.class);

	/** 定义 加密算法 */
	private static String ENCRYPT_MODE = "DES";
	/** 默认的密钥 */
	private static String DEFAULT_KEY = "e2s5";
	/** 加密密文 */
	private static Cipher encryptCipher = null;
	/** 解密密文 */
	private static Cipher decryptCipher = null;

	private static char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7',
			'8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

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
			LOG.error("产生加密的密钥失败", e);
		}
	}
	
	/**
	 * 
	 * 功能说明：返回Encryption的单实例，并且采用默认的加密密钥。 参数及返回值:
	 */
	public static EncryptionUtil getInstance() {
		if (encryp == null) {
			encryp = new EncryptionUtil();
		}
		return encryp;
	}

	/**
	 * 
	 * 功能说明：返回Encryption的单实例，并且传入自定义的加密密钥。 参数及返回值:
	 */
	public static EncryptionUtil getInstance(String key) {
		if (encryp == null) {
			encryp = new EncryptionUtil(key);
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
			LOG.error("产生加密的密钥失败", e);
		}
	}

	/**
	 * 
	 * 功能说明：产生加密的密钥 参数及返回值:
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
	 */
	protected static byte[] decode(byte[] input) {
		try {
			byte[] decrpyByte = decryptCipher.doFinal(input);
			return decrpyByte;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 功能说明：对输入的字符串进行解密 参数及返回值:
	 */
	public static String decode(String input) {
		try {
			if (input == null || "".equals(input)) {
				return input;
			} else {
				return new String(decode(hexStr2ByteArr(input)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return input;
	}

	/**
	 * 对输入的字节进行加密
	 * @param input
	 */
	protected static byte[] encode(byte[] input) {
		try {
			byte[] enrypByte = encryptCipher.doFinal(input);
			return enrypByte;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 
	 * 功能说明：对输入的字符串进行加密 参数及返回值:
	 */
	public static String encode(String input) {
		try {
			if (input == null || "".equals(input)) {
				return input;
			} else {
				return byteArr2HexStr(encode(input.getBytes()));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return input;
	}

	/**
	 * 
	 * 功能说明：将字节转换成字符串 参数及返回值:
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

	/**
	 * md5加密
	 * @param password
	 */
	public static String md5(String password) {
		if (password == null) {
			return null;
		}
		try {
			byte[] temp = password.getBytes();
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
		} catch (Exception e) {
			return null;
		}
	}
}