package cn.chinaiptv.demo;

import java.io.Serializable;

public class EPGFileProperty implements Serializable {
	private String SourceUrl ;
	private String DestPath ;
	private String DestFile ;
	private String MD5 ;
	private String Result ;
	private String ErrorDescription ;
	public String getSourceUrl() {
		return SourceUrl;
	}
	public void setSourceUrl(String sourceUrl) {
		SourceUrl = sourceUrl;
	}
	public String getDestPath() {
		return DestPath;
	}
	public void setDestPath(String destPath) {
		DestPath = destPath;
	}
	public String getDestFile() {
		return DestFile;
	}
	public void setDestFile(String destFile) {
		DestFile = destFile;
	}
	public String getMD5() {
		return MD5;
	}
	public void setMD5(String mD5) {
		MD5 = mD5;
	}
	public String getResult() {
		return Result;
	}
	public void setResult(String result) {
		Result = result;
	}
	public String getErrorDescription() {
		return ErrorDescription;
	}
	public void setErrorDescription(String errorDescription) {
		ErrorDescription = errorDescription;
	}
	

}
