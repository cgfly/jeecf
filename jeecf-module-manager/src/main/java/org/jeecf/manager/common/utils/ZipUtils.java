package org.jeecf.manager.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;
/**
 * zip 工具
 * @author jianyiming
 *
 */
public class ZipUtils {

	private static final int BUFFER_SIZE = 2 * 1024;

	/**
	 * 压缩成ZIP 方法1
	 * 
	 * @param srcDir           压缩文件夹路径
	 * @param out              压缩文件输出流
	 * @param keepDirStructure 是否保留原来的目录结构,true:保留目录结构;
	 *                         false:所有文件跑到压缩包根目录下(注意：不保留目录结构可能会出现同名文件,会压缩失败)
	 * @throws RuntimeException 压缩失败会抛出运行时异常
	 */

	public static void toZip(String srcDir, OutputStream out, boolean keepDirStructure) throws RuntimeException {
		ZipOutputStream zos = null;
		try {
			zos = new ZipOutputStream(out);
			File sourceFile = new File(srcDir);
			compress(sourceFile, zos, sourceFile.getName(), keepDirStructure);
		} catch (Exception e) {
			throw new RuntimeException("zip error from ZipUtils", e);
		} finally {
			if (zos != null) {
				try {
					zos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 压缩成ZIP 方法2
	 * 
	 * @param srcFiles 需要压缩的文件列表
	 * @param out      压缩文件输出流
	 * @throws RuntimeException 压缩失败会抛出运行时异常
	 */
	public static void toZip(List<File> srcFiles, OutputStream out) throws RuntimeException {
		ZipOutputStream zos = null;
		try {
			zos = new ZipOutputStream(out);
			for (File srcFile : srcFiles) {
				byte[] buf = new byte[BUFFER_SIZE];
				zos.putNextEntry(new ZipEntry(srcFile.getName()));
				int len;
				FileInputStream in = new FileInputStream(srcFile);
				while ((len = in.read(buf)) != -1) {
					zos.write(buf, 0, len);
				}
				zos.closeEntry();
				in.close();
			}
		} catch (Exception e) {
			throw new RuntimeException("zip error from ZipUtils", e);
		} finally {
			if (zos != null) {
				try {
					zos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 递归压缩方法
	 * 
	 * @param sourceFile       源文件
	 * @param zos              zip输出流
	 * @param name             压缩后的名称
	 * @param keepDirStructure 是否保留原来的目录结构,true:保留目录结构;
	 *                         false:所有文件跑到压缩包根目录下(注意：不保留目录结构可能会出现同名文件,会压缩失败)
	 * @throws Exception
	 */
	private static void compress(File sourceFile, ZipOutputStream zos, String name, boolean keepDirStructure)
			throws Exception {
		byte[] buf = new byte[BUFFER_SIZE];
		if (sourceFile.isFile()) {
			// 向zip输出流中添加一个zip实体，构造器中name为zip实体的文件的名字
			zos.putNextEntry(new ZipEntry(name));
			// copy文件到zip输出流中
			int len;
			FileInputStream in = new FileInputStream(sourceFile);
			while ((len = in.read(buf)) != -1) {
				zos.write(buf, 0, len);
			}
			// Complete the entry
			zos.closeEntry();
			in.close();
		} else {
			File[] listFiles = sourceFile.listFiles();
			if (listFiles == null || listFiles.length == 0) {
				// 需要保留原来的文件结构时,需要对空文件夹进行处理
				if (keepDirStructure) {
					// 空文件夹的处理
					zos.putNextEntry(new ZipEntry(name + "/"));
				}
			} else {
				for (File file : listFiles) {
					// 判断是否需要保留原来的文件结构
					if (keepDirStructure) {
						// 注意：file.getName()前面需要带上父文件夹的名字加一斜杠,
						// 不然最后压缩包中就不能保留原来的文件结构,即：所有文件都跑到压缩包根目录下了
						compress(file, zos, name + "/" + file.getName(), keepDirStructure);
					} else {
						compress(file, zos, file.getName(), keepDirStructure);
					}
				}
			}
		}
	}

	/**
	 *  解压缩方法
	 *  @param zipFileName 压缩文件名 
	 *  @param dstPath 解压目标路径 
	 *  @return
	 */
	public static boolean unzip(String zipFileName, String dstPath) {
		ZipInputStream zipInputStream =  null;
		boolean  flag = true;
		try {
		    zipInputStream = new ZipInputStream(new FileInputStream(zipFileName));
			ZipEntry zipEntry = null;
			// 缓冲器
			byte[] buffer = new byte[BUFFER_SIZE];
			// 每次读出来的长度
			int readLength = 0;
			while ((zipEntry = zipInputStream.getNextEntry()) != null) {
				// 若是zip条目目录，则需创建这个目录
				if (zipEntry.isDirectory()) {
					File dir = new File(dstPath + "/" + zipEntry.getName());
					if (!dir.exists()) {
						dir.mkdirs();
						continue;// 跳出
					}
				}
				// 若是文件，则需创建该文件
				File file = createFile(dstPath, zipEntry.getName());
				OutputStream outputStream = new FileOutputStream(file);
				while ((readLength = zipInputStream.read(buffer, 0, BUFFER_SIZE)) != -1) {
					outputStream.write(buffer, 0, readLength);
				}
				outputStream.close();
			}
		} catch (FileNotFoundException e) {
			flag = false;
		} catch (IOException e) {
			flag = false;
		}
		return flag;
	}

	/**
	 * 创建文件 
	 * 根据压缩包内文件名和解压缩目的路径，创建解压缩目标文件， 
	 * 生成中间目录 
	 * @param dstPath 解压缩目的路径
	 * @param fileName 压缩包内文件名 
	 * @return 解压缩目标文件 
	 * @throws IOException
	 */
	private static File createFile(String dstPath, String fileName) throws IOException {
		// 将文件名的各级目录分解
		String[] dirs = fileName.split("/");
		File file = new File(dstPath);
		if (dirs.length > 1) {
			// 文件有上级目录
			for (int i = 0; i < dirs.length - 1; i++) {
				// 依次创建文件对象知道文件的上一级目录
				file = new File(file, dirs[i]);
			}
			// 文件对应目录若不存在，则创建
			if (!file.exists()) {
				file.mkdirs();
			}
			// 创建文件
			file = new File(file, dirs[dirs.length - 1]);
			return file;
		} else {
			// 若目标路径的目录不存在，则创建
			if (!file.exists()) {
				file.mkdirs();
			}
			// 创建文件
			file = new File(file, dirs[0]);
			return file;
		}

	}

}
