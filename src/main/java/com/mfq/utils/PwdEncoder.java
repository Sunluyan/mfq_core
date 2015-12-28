package com.mfq.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang.StringUtils;

public class PwdEncoder {

	private static String defaultSalt = "8Xukfsa2cX;qnb1d";

	public static String encodePassword(String rawPass) {
		return encodePassword(rawPass, defaultSalt);
	}

	public static String encodePassword(String rawPass, String salt) {
		String saltedPass = mergePasswordAndSalt(rawPass, salt, false);
		MessageDigest messageDigest = getMessageDigest();
		byte[] digest;
		try {
			digest = messageDigest.digest(saltedPass.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			throw new IllegalStateException("UTF-8 not supported!");
		}
		return new String(Hex.encodeHex(digest));
	}

	public static boolean isPasswordValid(String encPass, String rawPass) {
		return isPasswordValid(encPass, rawPass, defaultSalt);
	}

	public static boolean isPasswordValid(String encPass, String rawPass, String salt) {
		if (StringUtils.isBlank(encPass)) {
			return false;
		}
		String pass2 = encodePassword(rawPass, salt);
		return encPass.equals(pass2);
	}

	protected static final MessageDigest getMessageDigest() {
		String algorithm = "MD5";
		try {
			return MessageDigest.getInstance(algorithm);
		} catch (NoSuchAlgorithmException e) {
		}
		throw new IllegalArgumentException("No such algorithm [" + algorithm
				+ "]");
	}

	protected static String mergePasswordAndSalt(String password, Object salt,
			boolean strict) {
		if (password == null) {
			password = "";
		}
		if ((strict)
				&& (salt != null)
				&& ((salt.toString().lastIndexOf("{") != -1) || (salt
						.toString().lastIndexOf("}") != -1))) {
			throw new IllegalArgumentException(
					"Cannot use { or } in salt.toString()");
		}

		if ((salt == null) || ("".equals(salt))) {
			return password;
		}
		return password + "{" + salt.toString() + "}";
	}
}