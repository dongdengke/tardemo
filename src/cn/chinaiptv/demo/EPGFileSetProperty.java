package cn.chinaiptv.demo;
 
import java.io.Serializable;

public class EPGFileSetProperty implements Serializable {
	private String EPGGroup ;
	private String SystemFile ;
	private String NeedUnTar ;
	private String BeginTime ;
	private String Result ;
	private String ErrorDescription ;
	
	public String getEPGGroup() {
		return EPGGroup;
	}
	public void setEPGGroup(String ePGGroup) {
		EPGGroup = ePGGroup;
	}
	public String getSystemFile() {
		return SystemFile;
	}
	public void setSystemFile(String systemFile) {
		SystemFile = systemFile;
	}
	public String getNeedUnTar() {
		return NeedUnTar;
	}
	public void setNeedUnTar(String needUnTar) {
		NeedUnTar = needUnTar;
	}
	public String getBeginTime() {
		return BeginTime;
	}
	public void setBeginTime(String beginTime) {
		BeginTime = beginTime;
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
