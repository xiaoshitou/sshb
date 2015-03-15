package com.yb.local;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * IO常用工具包
 * 
 * @author amosryan
 * @since 2010-06-03
 */
public class IOUtils {
	
	/**
	 * 按行读取文件到输出文件  
	 * @param inFileName
	 * @param outFileName
	 * @throws Exception
	 */
	public static void readFileByLine(File inFileName, String outFileName) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(inFileName), "utf-8"));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFileName,true), "utf-8"));

		try {
			String tempString = null;
			while ((tempString = br.readLine()) != null) {
				bw.write(tempString);
				bw.newLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				// 关闭流
				bw.close();
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}


	/**
	 * 将输入流转换成字节流
	 * 
	 * @param input
	 * @return
	 * @throws Exception
	 */
	public static byte[] toBytes(InputStream input) throws Exception {
		byte[] data = null;
		try {
			ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
			byte[] b = new byte[1024];
			int read = 0;
			while ((read = input.read(b)) > 0) {
				byteOut.write(b, 0, read);
			}
			data = byteOut.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			input.close();
		}
		return data;
	}

	/**
	 * 将文件读取为一个字符串
	 * 
	 * @param input
	 * @return
	 * @throws Exception
	 */
	public static String toString(File file) throws Exception {
		return toString(new FileInputStream(file));
	}

	/**
	 * 将输入流转换为一个串
	 * 
	 * @param input
	 * @return
	 * @throws Exception
	 */
	public static String toString(InputStream input) throws Exception {
		return toStringWithLineBreak(input, null);
	}

	/**
	 * 以指定编码格式将输入流按行置入一个List<String>
	 * 
	 * @param input
	 * @return
	 * @throws Exception
	 */
	public static List<String> toLines(InputStream input, String encoding)
			throws Exception {
		InputStreamReader insreader = new InputStreamReader(input, encoding);
		BufferedReader bin = new BufferedReader(insreader);
		List<String> lines = new ArrayList<String>();
		String line;
		while ((line = bin.readLine()) != null) {
			lines.add(line);
		}
		bin.close();
		insreader.close();
		return lines;
	}

	/**
	 * 以GBK格式将输入流按行置入一个List<String>
	 * 
	 * @param input
	 * @return
	 * @throws Exception
	 */
	public static List<String> toLines(InputStream input) throws Exception {
		return toLines(input, "GBK");
	}

	/**
	 * 转换为每行补充指定换行符(例如："/n"，"</br>")
	 * 
	 * @param input
	 * @param lineBreak
	 * @return
	 * @throws Exception
	 */
	public static String toStringWithLineBreak(InputStream input,
			String lineBreak) throws Exception {
		List<String> lines = toLines(input);
		StringBuilder sb = new StringBuilder(20480);
		for (String line : lines) {
			sb.append(line);
			if (lineBreak != null) {
				sb.append(lineBreak);
			}
		}
		return sb.toString();
	}

	/**
	 * 将字符串转出到指定文件
	 * 
	 * @param saveFile
	 * @param content
	 */
	public static void toFile(File saveFile, String content) {
		File parent = saveFile.getParentFile();
		if (!parent.exists()) {
			parent.mkdirs();
		}
		PrintWriter out = null;
		try {
			out = new PrintWriter(new FileWriter(saveFile));
			out.print(content);
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}

	/**
	 * 将一组文件打zip包
	 * 
	 * @param srcFiles
	 * @param targetFileName
	 * @throws IOException
	 */
	public static void filesToZip(List<File> srcFiles, String targetFileName)
			throws IOException {
		String fileOutName = targetFileName + ".zip";
		byte[] buf = new byte[1024];
		FileInputStream in = null;
		FileOutputStream fos = null;
		ZipOutputStream out = null;
		try {
			fos = new FileOutputStream(fileOutName);
			out = new ZipOutputStream(fos);
			for (File file : srcFiles) {
				in = new FileInputStream(file);
				out.putNextEntry(new ZipEntry(file.getName()));
				int len;
				while ((len = in.read(buf)) != -1) {
					out.write(buf, 0, len);
				}
				if (in != null) {
					in.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				in.close();
			}
			if (fos != null) {
				out.closeEntry();
				out.close();
				fos.close();
			}
		}
	}

//	public static void main(String[] args) {
//		try {
//			File doc1 = new File(
//					"E://workspace//test//doc//1272531757100_1.doc");
//			IOUtils.toString(new FileInputStream(doc1));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
}
