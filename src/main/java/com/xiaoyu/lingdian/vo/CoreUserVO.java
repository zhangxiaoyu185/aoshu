package com.xiaoyu.lingdian.vo;

import com.xiaoyu.lingdian.entity.CoreUser;
import com.xiaoyu.lingdian.vo.BaseVO;
import com.xiaoyu.lingdian.tool.DateUtil;

/**
* 用户表
*/
public class CoreUserVO implements BaseVO {

	/**
	*标识UUID
	*/
	private String crusrUuid;

	/**
	*帐户名称
	*/
	private String crusrName;

	/**
	*真实姓名
	*/
	private String crusrCode;

	/**
	*登录密码
	*/
	private String crusrPassword;

	/**
	*电子邮件
	*/
	private String crusrEmail;

	/**
	*手机号码
	*/
	private String crusrMobile;

	/**
	*会员等级
	*/
	private Integer crusrType;

	/**
	*状态:1启用,0禁用
	*/
	private Integer crusrStatus;

	/**
	*创建日期
	*/
	private String crusrCdate;

	/**
	*修改日期
	*/
	private String crusrUdate;

	/**
	*生日
	*/
	private String crusrBirthday;

	/**
	*性别:1男,0女,2其它
	*/
	private Integer crusrGender;

	/**
	*QQ
	*/
	private String crusrQq;

	/**
	*地址
	*/
	private String crusrAddress;

	/**
	*备注
	*/
	private String crusrRemarks;

	/**
	*授权OPENID
	*/
	private String crusrOpenid;

	/**
	*微信用户的昵称
	*/
	private String crusrWxNickname;

	/**
	*微信用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
	*/
	private String crusrWxSex;

	/**
	*微信用户所在城市
	*/
	private String crusrWxCity;

	/**
	*微信用户所在国家
	*/
	private String crusrWxCountry;

	/**
	*微信用户所在省份
	*/
	private String crusrWxProvince;

	/**
	*微信用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空
	*/
	private String crusrWxHeadimgurl;

	/**
	*微信用户关注时间，为时间戳。如果用户曾多次关注，则取最后关注时间
	*/
	private String crusrWxSubscribe;

	/**
	*所属班级(1普通班)
	*/
	private String crusrGrade;
	
	/**
	*所属班级名称
	*/
	private String crusrGradeName;
	
	/**
	*就读学校
	*/
	private String crusrSchool;

	/**
	*在读年级(1一年级2二年级3三年级4四年级5五年级6六年级7其他)
	*/
	private Integer crusrReadClass;

	/**
	*头像附件UUID
	*/
	private String headUuid;
	
	public void setCrusrUuid(String crusrUuid) {
		this.crusrUuid = crusrUuid;
	}

	public String getCrusrUuid( ) {
		return crusrUuid;
	}

	public void setCrusrName(String crusrName) {
		this.crusrName = crusrName;
	}

	public String getCrusrName( ) {
		return crusrName;
	}

	public void setCrusrCode(String crusrCode) {
		this.crusrCode = crusrCode;
	}

	public String getCrusrCode( ) {
		return crusrCode;
	}

	public void setCrusrPassword(String crusrPassword) {
		this.crusrPassword = crusrPassword;
	}

	public String getCrusrPassword( ) {
		return crusrPassword;
	}

	public void setCrusrEmail(String crusrEmail) {
		this.crusrEmail = crusrEmail;
	}

	public String getCrusrEmail( ) {
		return crusrEmail;
	}

	public void setCrusrMobile(String crusrMobile) {
		this.crusrMobile = crusrMobile;
	}

	public String getCrusrMobile( ) {
		return crusrMobile;
	}

	public void setCrusrType(Integer crusrType) {
		this.crusrType = crusrType;
	}

	public Integer getCrusrType( ) {
		return crusrType;
	}

	public void setCrusrStatus(Integer crusrStatus) {
		this.crusrStatus = crusrStatus;
	}

	public Integer getCrusrStatus( ) {
		return crusrStatus;
	}

	public void setCrusrCdate(String crusrCdate) {
		this.crusrCdate = crusrCdate;
	}

	public String getCrusrCdate( ) {
		return crusrCdate;
	}

	public void setCrusrUdate(String crusrUdate) {
		this.crusrUdate = crusrUdate;
	}

	public String getCrusrUdate( ) {
		return crusrUdate;
	}

	public void setCrusrBirthday(String crusrBirthday) {
		this.crusrBirthday = crusrBirthday;
	}

	public String getCrusrBirthday( ) {
		return crusrBirthday;
	}

	public void setCrusrGender(Integer crusrGender) {
		this.crusrGender = crusrGender;
	}

	public Integer getCrusrGender( ) {
		return crusrGender;
	}

	public void setCrusrQq(String crusrQq) {
		this.crusrQq = crusrQq;
	}

	public String getCrusrQq( ) {
		return crusrQq;
	}

	public void setCrusrAddress(String crusrAddress) {
		this.crusrAddress = crusrAddress;
	}

	public String getCrusrAddress( ) {
		return crusrAddress;
	}

	public void setCrusrRemarks(String crusrRemarks) {
		this.crusrRemarks = crusrRemarks;
	}

	public String getCrusrRemarks( ) {
		return crusrRemarks;
	}

	public void setCrusrOpenid(String crusrOpenid) {
		this.crusrOpenid = crusrOpenid;
	}

	public String getCrusrOpenid( ) {
		return crusrOpenid;
	}

	public void setCrusrWxNickname(String crusrWxNickname) {
		this.crusrWxNickname = crusrWxNickname;
	}

	public String getCrusrWxNickname( ) {
		return crusrWxNickname;
	}

	public void setCrusrWxSex(String crusrWxSex) {
		this.crusrWxSex = crusrWxSex;
	}

	public String getCrusrWxSex( ) {
		return crusrWxSex;
	}

	public void setCrusrWxCity(String crusrWxCity) {
		this.crusrWxCity = crusrWxCity;
	}

	public String getCrusrWxCity( ) {
		return crusrWxCity;
	}

	public void setCrusrWxCountry(String crusrWxCountry) {
		this.crusrWxCountry = crusrWxCountry;
	}

	public String getCrusrWxCountry( ) {
		return crusrWxCountry;
	}

	public void setCrusrWxProvince(String crusrWxProvince) {
		this.crusrWxProvince = crusrWxProvince;
	}

	public String getCrusrWxProvince( ) {
		return crusrWxProvince;
	}

	public void setCrusrWxHeadimgurl(String crusrWxHeadimgurl) {
		this.crusrWxHeadimgurl = crusrWxHeadimgurl;
	}

	public String getCrusrWxHeadimgurl( ) {
		return crusrWxHeadimgurl;
	}

	public void setCrusrWxSubscribe(String crusrWxSubscribe) {
		this.crusrWxSubscribe = crusrWxSubscribe;
	}

	public String getCrusrWxSubscribe( ) {
		return crusrWxSubscribe;
	}

	public String getCrusrGrade() {
		return crusrGrade;
	}

	public void setCrusrGrade(String crusrGrade) {
		this.crusrGrade = crusrGrade;
	}

	public String getCrusrGradeName() {
		return crusrGradeName;
	}

	public void setCrusrGradeName(String crusrGradeName) {
		this.crusrGradeName = crusrGradeName;
	}

	public void setCrusrSchool(String crusrSchool) {
		this.crusrSchool = crusrSchool;
	}

	public String getCrusrSchool( ) {
		return crusrSchool;
	}

	public void setCrusrReadClass(Integer crusrReadClass) {
		this.crusrReadClass = crusrReadClass;
	}

	public Integer getCrusrReadClass( ) {
		return crusrReadClass;
	}

	public String getHeadUuid() {
		return headUuid;
	}

	public void setHeadUuid(String headUuid) {
		this.headUuid = headUuid;
	}

	public CoreUserVO( ) { 
	}

	@Override
	public void convertPOToVO(Object poObj) {
		if (null == poObj) {
			return;
		}

		CoreUser po = (CoreUser) poObj;
		this.crusrUuid = po.getCrusrUuid();
		this.crusrName = po.getCrusrName();
		this.crusrCode = po.getCrusrCode();
		this.crusrPassword = po.getCrusrPassword();
		this.crusrEmail = po.getCrusrEmail();
		this.crusrMobile = po.getCrusrMobile();
		this.crusrType = po.getCrusrType();
		this.crusrStatus = po.getCrusrStatus();
		this.crusrCdate = po.getCrusrCdate()!=null?DateUtil.formatTimesTampDate(po.getCrusrCdate()):"";
		this.crusrUdate = po.getCrusrUdate()!=null?DateUtil.formatTimesTampDate(po.getCrusrUdate()):"";
		this.crusrBirthday = po.getCrusrBirthday();
		this.crusrGender = po.getCrusrGender();
		this.crusrQq = po.getCrusrQq();
		this.crusrAddress = po.getCrusrAddress();
		this.crusrRemarks = po.getCrusrRemarks();
		this.crusrOpenid = po.getCrusrOpenid();
		this.crusrWxNickname = po.getCrusrWxNickname();
		this.crusrWxSex = po.getCrusrWxSex();
		this.crusrWxCity = po.getCrusrWxCity();
		this.crusrWxCountry = po.getCrusrWxCountry();
		this.crusrWxProvince = po.getCrusrWxProvince();
		this.crusrWxHeadimgurl = po.getCrusrWxHeadimgurl();
		this.crusrWxSubscribe = po.getCrusrWxSubscribe();
		this.crusrGrade = po.getCrusrGrade();
		this.crusrGradeName = po.getCrusrGradeName();
		this.crusrSchool = po.getCrusrSchool();
		this.crusrReadClass = po.getCrusrReadClass();
	}
//<=================定制内容开始==============
//==================定制内容结束==============>

}