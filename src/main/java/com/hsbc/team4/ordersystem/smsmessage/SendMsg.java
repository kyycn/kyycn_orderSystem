package com.hsbc.team4.ordersystem.smsmessage;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * SendMsg
 * @author Kevin
 */
@ApiModel("发消息")
@SuppressWarnings("serial")
@Entity
@Data
public class SendMsg {
	@Id
	private String id;
    @ApiModelProperty(value = "message：1 SMS,2 voice", required = true)
    private String msgType;
	@ApiModelProperty(value = "bizType:1.register verifyCode 2.login verifyCode3.update password verifyCode 4.update msg verifyCode", required = true)
	private String bizType;
	@ApiModelProperty(value = "phone", required = true)
	private String phone;
	@ApiModelProperty(value = "SMS params")
	private String params;
	@ApiModelProperty(value = "expirationTime")
	private String expirationTime;
	@ApiModelProperty(value = "sender")
	private String sender;
	@ApiModelProperty(value = "code")
	@JsonIgnore
	private String code;
	@ApiModelProperty(value = "createTime")
	private long createTime;
	@ApiModelProperty(value = "status")
	private String status;
}
