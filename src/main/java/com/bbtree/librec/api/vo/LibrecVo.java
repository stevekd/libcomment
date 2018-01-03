package com.bbtree.librec.api.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 实现了序列化，对外的model
 * 与core.model.Librec 对应
 * 字段，可以扩展
 *
 */
public class LibrecVo implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1030605262042050598L;
	

	private Integer id;

    private String code;

    private Integer questionId;

    private String content;

    private String imgs;

    private Date createTime;

    private Integer userId;

    private String userName;

    private String userExpertName;

    private String userBaby;

    private Integer agreeNum;

    private Integer uselessNum;

    private String userHeadimg;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImgs() {
        return imgs;
    }

    public void setImgs(String imgs) {
        this.imgs = imgs;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserExpertName() {
        return userExpertName;
    }

    public void setUserExpertName(String userExpertName) {
        this.userExpertName = userExpertName;
    }

    public String getUserBaby() {
        return userBaby;
    }

    public void setUserBaby(String userBaby) {
        this.userBaby = userBaby;
    }

    public Integer getAgreeNum() {
        return agreeNum;
    }

    public void setAgreeNum(Integer agreeNum) {
        this.agreeNum = agreeNum;
    }

    public Integer getUselessNum() {
        return uselessNum;
    }

    public void setUselessNum(Integer uselessNum) {
        this.uselessNum = uselessNum;
    }

    public String getUserHeadimg() {
        return userHeadimg;
    }

    public void setUserHeadimg(String userHeadimg) {
        this.userHeadimg = userHeadimg;
    }
}
