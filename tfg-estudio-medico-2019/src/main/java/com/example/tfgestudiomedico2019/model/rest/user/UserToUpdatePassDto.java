package com.example.tfgestudiomedico2019.model.rest.user;

import io.swagger.annotations.ApiModelProperty;

/**
 * User dto with information to update the password of a user.
 *
 */
public class UserToUpdatePassDto {
	
	@ApiModelProperty(value = "The id of the user", example = "23", dataType = "java.lang.String")
	private String id;
	@ApiModelProperty(value = "The old password of the user", example = "123456", dataType = "java.lang.String")
	private String oldPassword;
	@ApiModelProperty(value = "The new password of the user", example = "123456", dataType = "java.lang.String")
	private String newPassword;

	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	@Override
	public String toString() {
		return "UserToUpdateDto [id=" + id + ", oldPssword=" + oldPassword+ ", newPassword=" + newPassword + "]";
	}
	
}
