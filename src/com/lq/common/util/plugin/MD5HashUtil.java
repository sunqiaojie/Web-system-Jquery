package com.lq.common.util.plugin;

import java.io.FileInputStream;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5HashUtil {

	private MessageDigest md = null;

	private static MD5HashUtil md5 = null;

	private static final char[] hexChars = { '0', '1', '2', '3', '4', '5', '6',
			'7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

	/**
	 * Constructor is private so you must use the getInstance method
	 */
	private MD5HashUtil() throws NoSuchAlgorithmException {
		md = MessageDigest.getInstance("MD5");
	}

	/**
	 * This returns the singleton instance
	 */
	public static MD5HashUtil getInstance() throws NoSuchAlgorithmException {
		if (md5 == null) {
			md5 = new MD5HashUtil();
		}
		return (md5);
	}

	public static String hashCode(String dataToHash)
			throws NoSuchAlgorithmException {
		return getInstance().hashData(dataToHash.getBytes());
	}

	public static String hashCode(byte[] dataToHash)
			throws NoSuchAlgorithmException {
		return getInstance().hashData(dataToHash);
	}

	public String hashData(byte[] dataToHash) {
		return bytesToHexString((calculateHash(dataToHash))).toLowerCase();
	}

	private byte[] calculateHash(byte[] dataToHash) {
		md.update(dataToHash, 0, dataToHash.length);
		return (md.digest());
	}

	public String bytesToHexString(byte[] b) {
		String hex = "";
		int msb;
		int lsb = 0;
		int i;
		// MSB maps to idx 0
		for (i = 0; i < b.length; i++) {
			msb = ((int) b[i] & 0x000000FF) / 16; // 转成十进�?
			lsb = ((int) b[i] & 0x000000FF) % 16;
			hex = hex + hexChars[msb] + hexChars[lsb]; // 转成十六进制
		}
		return (hex);
	}

	public static String getFileHash(String fileName, String hashType)
			throws Exception {
		InputStream fis;
		fis = new FileInputStream(fileName);
		byte[] buffer = new byte[1024];
		MessageDigest md5 = MessageDigest.getInstance(hashType);
		int numRead = 0;
		while ((numRead = fis.read(buffer)) > 0) {
			md5.update(buffer, 0, numRead);
		}
		fis.close();
		return getInstance().bytesToHexString(md5.digest());
	}

	public static void main(String args[]) throws Exception {
		String string = "123456";
		System.out.println(string);
		System.out.println(hashCode(string));

		String fileName = "123456";
		String hashType = "MD5";
		System.out.println(hashType + " == " + getFileHash(fileName, hashType));
		hashType = "SHA1";
		System.out.println(hashType + " == " + getFileHash(fileName, hashType));
		hashType = "SHA-256";
		System.out.println(hashType + " == " + getFileHash(fileName, hashType));
		hashType = "SHA-384";
		System.out.println(hashType + " == " + getFileHash(fileName, hashType));
		hashType = "SHA-512";
		System.out.println(hashType + " == " + getFileHash(fileName, hashType));
	}

}
