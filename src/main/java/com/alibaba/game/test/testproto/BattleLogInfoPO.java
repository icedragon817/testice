/**
 * 
 */
package com.alibaba.game.test.testproto;

import java.io.UnsupportedEncodingException;

/**
* @ClassName: BattleLogInfoPO 
* @Description: 对战信息持久化对象
* @author piqiu.wp
* @date 2018年6月1日 下午4:35:10 
*  
*/
public class BattleLogInfoPO {
	/**
	 * 标记log类型（需要根据传入int数值反签名算法得出字符串）
	 */
	private String typeCode;
	/**
	 * 上报手机操作系统
	 */
	private String platform;
	/**
	 * 角色账号id 
	 */
	private String playerId;
	/**
	 * 角色名
	 */
	private String accountName;
	/**
	 * 区Id
	 */
	private String areaId;
	/**
	 * 包版本号
	 */
	private String appVersion;
	/**
	 * 资源版本号
	 */
	private String resourceVersion;
	/**
	 * 项目编码
	 */
	private String projectId;
	/**
	 * 每次运行的唯一id
	 */
	private Integer runningId;
	/**
	 * 数据格式版本号
	 */
	private String formatVersion;
	/**
	 * 时间戳
	 */
	private Long timeStamp;
	/**
	 * 战斗id
	 */
	private String dungeonId;
	
	/**
	 * 上报人的英雄id
	 */
	private Integer logHeroId;
	/**
	 * 上报人的阵营
	 */
	private Boolean logHeroRace;
	/**
	 * 战斗日志类型；0：None（错误数据）1：GetItem（局内战斗获得经验、装备）2：AttrChange（局内战斗属性面板切片）
	 */
	private Integer battleLogType;
	/**
	 * 战斗数据自增ID，从1开始，每生成一条ID+1
	 */
	private Integer logId;
	/**
	 * 帧id
	 */
	private Integer frameId;
	/**
	 * 获益者英雄id
	 */
	private Integer ownerId;
	/**
	 * 获益者阵营
	 */
	private Boolean ownerRace;
	/**
	 * 额外的不固定的数据json封装
	 */
	private String extJson;
	
	/**
	 * @return the platform
	 */
	public String getPlatform() {
		return platform;
	}
	/**
	 * @param platform the platform to set
	 */
	public void setPlatform(String platform) {
		this.platform = platform;
	}
	/**
	 * @return the accountName
	 */
	public String getAccountName() {
		return accountName;
	}
	/**
	 * @param accountName the accountName to set
	 */
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	/**
	 * @return the areaId
	 */
	public String getAreaId() {
		return areaId;
	}
	/**
	 * @param areaId the areaId to set
	 */
	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	/**
	 * @return the appVersion
	 */
	public String getAppVersion() {
		return appVersion;
	}
	/**
	 * @param appVersion the appVersion to set
	 */
	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}
	/**
	 * @return the resourceVersion
	 */
	public String getResourceVersion() {
		return resourceVersion;
	}
	/**
	 * @param resourceVersion the resourceVersion to set
	 */
	public void setResourceVersion(String resourceVersion) {
		this.resourceVersion = resourceVersion;
	}
	/**
	 * @return the projectId
	 */
	public String getProjectId() {
		return projectId;
	}
	/**
	 * @param projectId the projectId to set
	 */
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	/**
	 * @return the formatVersion
	 */
	public String getFormatVersion() {
		return formatVersion;
	}
	/**
	 * @param formatVersion the formatVersion to set
	 */
	public void setFormatVersion(String formatVersion) {
		this.formatVersion = formatVersion;
	}
	/**
	 * @return the timeStamp
	 */
	public Long getTimeStamp() {
		return timeStamp;
	}
	/**
	 * @param timeStamp the timeStamp to set
	 */
	public void setTimeStamp(Long timeStamp) {
		this.timeStamp = timeStamp;
	}
	/**
	 * @return the playerId
	 */
	public String getPlayerId() {
		return playerId;
	}
	/**
	 * @param playerId the playerId to set
	 */
	public void setPlayerId(String playerId) {
		this.playerId = playerId;
	}
	/**
	 * @return the dungeonId
	 */
	public String getDungeonId() {
		return dungeonId;
	}
	/**
	 * @param dungeonId the dungeonId to set
	 */
	public void setDungeonId(String dungeonId) {
		this.dungeonId = dungeonId;
	}
	/**
	 * @return the runningId
	 */
	public Integer getRunningId() {
		return runningId;
	}
	/**
	 * @param runningId the runningId to set
	 */
	public void setRunningId(Integer runningId) {
		this.runningId = runningId;
	}
	/**
	 * @return the logHeroId
	 */
	public Integer getLogHeroId() {
		return logHeroId;
	}
	/**
	 * @param logHeroId the logHeroId to set
	 */
	public void setLogHeroId(Integer logHeroId) {
		this.logHeroId = logHeroId;
	}
	/**
	 * @return the logHeroRace
	 */
	public Boolean getLogHeroRace() {
		return logHeroRace;
	}
	/**
	 * @param logHeroRace the logHeroRace to set
	 */
	public void setLogHeroRace(Boolean logHeroRace) {
		this.logHeroRace = logHeroRace;
	}
	/**
	 * @return the battleLogType
	 */
	public Integer getBattleLogType() {
		return battleLogType;
	}
	/**
	 * @param battleLogType the battleLogType to set
	 */
	public void setBattleLogType(Integer battleLogType) {
		this.battleLogType = battleLogType;
	}
	/**
	 * @return the logId
	 */
	public Integer getLogId() {
		return logId;
	}
	/**
	 * @param logId the logId to set
	 */
	public void setLogId(Integer logId) {
		this.logId = logId;
	}
	/**
	 * @return the typeCode
	 */
	public String getTypeCode() {
		return typeCode;
	}
	/**
	 * @param typeCode the typeCode to set
	 */
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
	/**
	 * @return the frameId
	 */
	public Integer getFrameId() {
		return frameId;
	}
	/**
	 * @param frameId the frameId to set
	 */
	public void setFrameId(Integer frameId) {
		this.frameId = frameId;
	}
	/**
	 * @return the ownerId
	 */
	public Integer getOwnerId() {
		return ownerId;
	}
	/**
	 * @param ownerId the ownerId to set
	 */
	public void setOwnerId(Integer ownerId) {
		this.ownerId = ownerId;
	}
	/**
	 * @return the ownerRace
	 */
	public Boolean getOwnerRace() {
		return ownerRace;
	}
	/**
	 * @param ownerRace the ownerRace to set
	 */
	public void setOwnerRace(Boolean ownerRace) {
		this.ownerRace = ownerRace;
	}
	/**
	 * @return the extJson
	 */
	public String getExtJson() {
		return extJson;
	}
	/**
	 * @param extJson the extJson to set
	 */
	public void setExtJson(String extJson) {
		this.extJson = extJson;
	}

}
