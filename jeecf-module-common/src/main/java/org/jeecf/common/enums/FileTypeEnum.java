package org.jeecf.common.enums;

/**
 * 文件类型枚举类
 * 
 * @author jianyiming
 *
 */
public enum FileTypeEnum {
	/**
	 * 后缀名 html
	 */
	HTML(0, "html"),
	/**
	 * 后缀名 htm
	 */
	HTM(1, "htm"),
	/**
	 * 后缀名 shtml
	 */
	SHTML(2, "shtml"), 
	/**
	 * 后缀名 apk
	 */
	APK(3, "apk"), 
	/**
	 * 后缀名 sis
	 */
	SIS(5, "sis"), 
	/**
	 * 后缀名 sisx
	 */
	SISX(6, "sisx"), 
	/**
	 * 后缀名 exe
	 */
	EXE(7, "exe"),
	/**
	 * 后缀名 msi
	 */
	MSI(8, "msi"), 
	/**
	 * 后缀名 css
	 */
	CSS(9, "css"),
	/**
	 * 后缀名 xml
	 */
	XML(10, "xml"), 
	/**
	 * 后缀名 gif
	 */
	GIF(11, "gif"), 
	/**
	 * 后缀名 jpeg
	 */
	JPEG(12, "jpeg"),
	/**
	 * 后缀名 jpg
	 */
	JPG(13, "jpg"), 
	/**
	 * 后缀名 js
	 */
	JS(14, "js"), 
	/**
	 * 后缀名 txt
	 */
	TXT(15, "txt"), 
	/**
	 * 后缀名 png
	 */
	PNG(16, "png"),
	/**
	 * 后缀名 ico
	 */
	ICO(17, "ico"), 
	/**
	 * 后缀名 bmp
	 */
	BMP(18, "bmp"), 
	/**
	 * 后缀名 svg
	 */
	SVG(19, "svg"), 
	/**
	 * 后缀名 jar
	 */
	JAR(20, "jar"), 
	/**
	 * 后缀名 var
	 */
	VAR(21, "var"), 
	/**
	 * 后缀名 ear
	 */
	EAR(22, "ear"), 
	/**
	 * 后缀名 doc
	 */
	DOC(23, "doc"),
	/**
	 * 后缀名 pdf
	 */
	PDF(24, "pdf"), 
	/**
	 * 后缀名 xls
	 */
	XLS(25, "xls"), 
	/**
	 * 后缀名 ppt
	 */
	PPT(26, "ppt"), 
	/**
	 * 后缀名 rar
	 */
	RAR(27, "rar"),
	/**
	 * 后缀名 xhtml
	 */
	XHTML(28, "xhtml"), 
	/**
	 * 后缀名 zip
	 */
	ZIP(29, "zip"), 
	/**
	 * 后缀名 mp3
	 */
	MP3(30, "mp3"),
	/**
	 * 后缀名 ogg
	 */
	OGG(31, "ogg"), 
	/**
	 * 后缀名 mp4
	 */
	MP4(32, "mp4"),
	/**
	 * 后缀名 mov
	 */
	MOV(33, "mov"), 
	/**
	 * 后缀名 flv
	 */
	FLV(34, "flv"), 
	/**
	 * 后缀名 wmv
	 */
	WMV(35, "wmv"),
	/**
	 * 后缀名 avi
	 */
	AVI(36, "avi"),
	;

	public final int code;
	public final String name;

	private FileTypeEnum(int code, String name) {
		this.code = code;
		this.name = name;
	}

	public int getCode() {
		return this.code;
	}

	public String getName() {
		return this.name;
	}
}
