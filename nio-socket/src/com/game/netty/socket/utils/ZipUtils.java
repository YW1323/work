package com.game.netty.socket.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class ZipUtils {

	// 压缩
	public static String compress(String str,String charset) throws IOException {
		if (str == null || str.length() == 0) { return str; }
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		GZIPOutputStream gzip = new GZIPOutputStream(out);
		gzip.write(str.getBytes(charset));
		gzip.close();
		return out.toString(charset);
	}
	
	public static String compress(byte[] b,String charset) throws IOException {
		if ( b == null || b.length == 0) { return null; }
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		GZIPOutputStream gzip = new GZIPOutputStream(out);
		gzip.write(b);
		gzip.close();
		return out.toString(charset);
	}
	
	public static byte[] compressByte(byte[] b,String charset) throws IOException {
		if ( b == null || b.length == 0) { return null; }
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		GZIPOutputStream gzip = new GZIPOutputStream(out);
		gzip.write(b);
		gzip.close();
		return out.toByteArray();
	}

	// 解压缩
	public static String uncompress(String str,String charset) throws IOException {
		if (str == null || str.length() == 0) { return str; }
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ByteArrayInputStream in = new ByteArrayInputStream(str.getBytes(charset));
		GZIPInputStream gunzip = new GZIPInputStream(in);
		byte[] buffer = new byte[256];
		int n;
		while ((n = gunzip.read(buffer)) >= 0) {
			out.write(buffer, 0, n);
		}
		// toString()使用平台默认编码，也可以显式的指定如toString(&quot;GBK&quot;)
		return out.toString(charset);
	}
	
	// 解压缩
	public static String uncompress(byte[] b,String charset) throws IOException {
		if ( b == null || b.length == 0) { return null; }
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ByteArrayInputStream in = new ByteArrayInputStream(b);
		GZIPInputStream gunzip = new GZIPInputStream(in);
		byte[] buffer = new byte[256];
		int n;
		while ((n = gunzip.read(buffer)) >= 0) {
			out.write(buffer, 0, n);
		}
		return out.toString();
	}
	
	// 解压缩
	public static byte[] uncompressByByte(byte[] b,String charset) throws IOException {
		if ( b == null || b.length == 0) { return null; }
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ByteArrayInputStream in = new ByteArrayInputStream(b);
		GZIPInputStream gunzip = new GZIPInputStream(in);
		byte[] buffer = new byte[256];
		int n;
		while ((n = gunzip.read(buffer)) >= 0) {
			out.write(buffer, 0, n);
		}
		return out.toByteArray();
	}

}
