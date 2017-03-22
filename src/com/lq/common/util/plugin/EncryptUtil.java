package com.lq.common.util.plugin;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class EncryptUtil {

	private static final String Algorithm = "DES/CBC/PKCS5Padding";

	private static final byte[] bytes = { 'e', '+', 's', 'u', '%', '*', '4',
			'O' };

	private static final byte[] key = { 'd', 'e', '#', '$', '%', '^', '7', 'l' };

	// new String("de$%^@#-").getBytes();
	private static SecretKey deskey = new SecretKeySpec(key, "DES");

	private static IvParameterSpec ivSpec = new IvParameterSpec(bytes);

	private EncryptUtil() {
	}

	public static String encrypt(String str) {
		try {
			Cipher c1 = Cipher.getInstance(Algorithm);
			c1.init(Cipher.ENCRYPT_MODE, deskey, ivSpec);
			byte[] cipherByte = c1.doFinal(str.getBytes());
			BASE64Encoder base64encoder = new BASE64Encoder();
			return base64encoder.encode(cipherByte);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String decrypt(String str) {
		try {
			BASE64Decoder base64decoder = new BASE64Decoder();
			byte cipherByte[] = base64decoder.decodeBuffer(str);
			Cipher c1 = Cipher.getInstance(Algorithm);
			c1.init(Cipher.DECRYPT_MODE, deskey, ivSpec);
			byte[] clearByte = c1.doFinal(cipherByte);
			return new String(clearByte);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) throws Exception {
		// try {
		String pwd = EncryptUtil.encrypt("123456");
		System.out.println(pwd);
		String rs = EncryptUtil.decrypt(pwd);
		System.out.println(rs);
		// } catch (Exception e) {
		// e.printStackTrace(); // To change body of catch statement use
		// File | Settings | File Templates.
		// }
	}
}
