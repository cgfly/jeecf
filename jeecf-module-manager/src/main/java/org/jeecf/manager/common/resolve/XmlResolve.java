package org.jeecf.manager.common.resolve;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.jeecf.common.mapper.JaxbMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
/**
 * xml 文件解析
 * @author jianyiming
 *
 */
public class XmlResolve {
	
	/**
	 * XML文件转换为对象
	 * 
	 * @param fileName
	 * @param clazz
	 * @return
	 */
	public static <T> T toObject(String pathName, Class<T> clazz) {
		Resource resource = new ClassPathResource(pathName);
		InputStream is;
		try {
			is = resource.getInputStream();
			return XmlResolve.toObject(is, clazz);
		} catch (IOException e) {
		}
		return null;
	}

	public static <T> T toObject(InputStream is, Class<T> clazz) {
		try {

			BufferedReader br = null;
			if (is != null) {
				br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
				StringBuilder sb = new StringBuilder();
				while (true) {
					String line = br.readLine();
					if (line == null) {
						break;
					}
					sb.append(line).append("\r\n");
				}
				if (is != null) {
					is.close();
				}
				if (br != null) {
					br.close();
				}
				return JaxbMapper.fromXml(sb.toString(), clazz);
			}
		} catch (IOException e) {
		}
		return null;
	}

}
