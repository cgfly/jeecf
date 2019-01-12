package org.jeecf.manager.common.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.jeecf.common.enums.SysErrorEnum;
import org.jeecf.common.exception.BusinessException;
/**
 * 文件下载工具类
 * @author jianyiming
 *
 */
public class DownloadUtils {

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
