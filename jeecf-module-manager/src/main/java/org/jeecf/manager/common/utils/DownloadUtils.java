package org.jeecf.manager.common.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.jeecf.common.enums.SplitCharEnum;
import org.jeecf.common.enums.SysErrorEnum;
import org.jeecf.common.exception.BusinessException;
import org.jeecf.common.utils.FileUtils;
import org.jeecf.manager.common.properties.TemplateProperties;
/**
 * 文件下载工具类
 * @author jianyiming
 *
 */
public class DownloadUtils {

	public static String genBuild(String key) {
		if (StringUtils.isNotEmpty(key)) {
			FileOutputStream out = null;
			try {
				TemplateProperties properties = SpringContextUtils.getBean(TemplateProperties.class);
				String fileDir = properties.getDownloadPerfixPath() + File.separator + key;
				String outPath = fileDir + SplitCharEnum.DOT.getName()+properties.getDownloadSuffixName();
				FileUtils.createDirectory(fileDir);
				out = new FileOutputStream(new File(outPath));
				ZipUtils.toZip(fileDir, out, true);
				return outPath;
			} catch (FileNotFoundException e) {
				throw new BusinessException(SysErrorEnum.FILE_NO);
			} finally {
				if (out != null) {
					try {
						out.close();
					} catch (IOException e) {
						throw new BusinessException(SysErrorEnum.IO_ERROR);
					}
				}
			}
		} else {
			throw new BusinessException(SysErrorEnum.FILE_NO);
		}
	}
	
	public static void downloadFile(HttpServletResponse response,String fileName) {
        if (fileName != null) {
            File file = new File(fileName);
            if (file.exists()) {
            	// 设置强制下载不打开
                response.setContentType("application/force-download");
             // 设置文件名
                response.addHeader("Content-Disposition",
                        "attachment;fileName=" + fileName);
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                } catch (Exception e) {
    				throw new BusinessException(SysErrorEnum.FILE_NO);
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
    						throw new BusinessException(SysErrorEnum.IO_ERROR);
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
    						throw new BusinessException(SysErrorEnum.IO_ERROR);
                        }
                    }
                }
            }
        }
    }

}
