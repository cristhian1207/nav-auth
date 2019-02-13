package com.cchacalcaje.navauth.bean;

import java.util.ArrayList;
import java.util.Date;

/**
 * Objeto de autorización.
 * @author Cristhian Chacalcaje
 *
 */
public class AuthorizationData {
	int no;
	String tableCaption;
	String recordPrimaryKey;
	String recordDescription;
	String fieldCaption;
	String oldValue;
	String newValue;
	Date postingDate;
	String requesterUserID;
	String requesterUserName;
	String approverUserID;
	String approverUserName;
	int status;
	String message;
	Date authorizedDate;
	int tableID;
	int fieldID;
	ArrayList<Additional> lAddtional = new ArrayList<>();
	public AuthorizationData() {
	}

	public AuthorizationData(int no, String tableCaption, String recordPrimaryKey, String recordDescription,
			String fieldCaption, String oldValue, String newValue, Date postingDate, String requesterUserID,
			String requesterUserName, String approverUserID, String approverUserName, int status, String message,
			Date authorizedDate, int tableID, int fieldID) {
		this.no = no;
		this.tableCaption = tableCaption;
		this.recordPrimaryKey = recordPrimaryKey;
		this.recordDescription = recordDescription;
		this.fieldCaption = fieldCaption;
		this.oldValue = oldValue;
		this.newValue = newValue;
		this.postingDate = postingDate;
		this.requesterUserID = requesterUserID;
		this.requesterUserName = requesterUserName;
		this.approverUserID = approverUserID;
		this.approverUserName = approverUserName;
		this.status = status;
		this.message = message;
		this.authorizedDate = authorizedDate;
		this.tableID = tableID;
		this.fieldID = fieldID;
	}
	public AuthorizationData(int no, String tableCaption, String recordPrimaryKey, String recordDescription,
			String fieldCaption, String oldValue, String newValue, Date postingDate, String requesterUserID,
			String requesterUserName, String approverUserID, String approverUserName, int status, String message,
			Date authorizedDate, int tableID, int fieldID, ArrayList<Additional> lAddtional) {
		this.no = no;
		this.tableCaption = tableCaption;
		this.recordPrimaryKey = recordPrimaryKey;
		this.recordDescription = recordDescription;
		this.fieldCaption = fieldCaption;
		this.oldValue = oldValue;
		this.newValue = newValue;
		this.postingDate = postingDate;
		this.requesterUserID = requesterUserID;
		this.requesterUserName = requesterUserName;
		this.approverUserID = approverUserID;
		this.approverUserName = approverUserName;
		this.status = status;
		this.message = message;
		this.authorizedDate = authorizedDate;
		this.tableID = tableID;
		this.fieldID = fieldID;
		this.lAddtional = lAddtional;
	}

	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getTableCaption() {
		return tableCaption;
	}
	public void setTableCaption(String tableCaption) {
		this.tableCaption = tableCaption;
	}
	public String getRecordPrimaryKey() {
		return recordPrimaryKey;
	}
	public void setRecordPrimaryKey(String recordPrimaryKey) {
		this.recordPrimaryKey = recordPrimaryKey;
	}
	public String getRecordDescription() {
		return recordDescription;
	}
	public void setRecordDescription(String recordDescription) {
		this.recordDescription = recordDescription;
	}
	public String getFieldCaption() {
		return fieldCaption;
	}
	public void setFieldCaption(String fieldCaption) {
		this.fieldCaption = fieldCaption;
	}
	public String getOldValue() {
		return oldValue;
	}
	public void setOldValue(String oldValue) {
		this.oldValue = oldValue;
	}
	public String getNewValue() {
		return newValue;
	}
	public void setNewValue(String newValue) {
		this.newValue = newValue;
	}
	public Date getPostingDate() {
		return postingDate;
	}
	public void setPostingDate(Date postingDate) {
		this.postingDate = postingDate;
	}
	public String getRequesterUserID() {
		return requesterUserID;
	}
	public void setRequesterUserID(String requesterUserID) {
		this.requesterUserID = requesterUserID;
	}
	public String getRequesterUserName() {
		return requesterUserName;
	}
	public void setRequesterUserName(String requesterUserName) {
		this.requesterUserName = requesterUserName;
	}
	public String getApproverUserID() {
		return approverUserID;
	}
	public void setApproverUserID(String approverUserID) {
		this.approverUserID = approverUserID;
	}
	public String getApproverUserName() {
		return approverUserName;
	}
	public void setApproverUserName(String approverUserName) {
		this.approverUserName = approverUserName;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getAuthorizedDate() {
		return authorizedDate;
	}
	public void setAuthorizedDate(Date authorizedDate) {
		this.authorizedDate = authorizedDate;
	}
	public int getTableID() {
		return tableID;
	}
	public void setTableID(int tableID) {
		this.tableID = tableID;
	}
	public int getFieldID() {
		return fieldID;
	}
	public void setFieldID(int fieldID) {
		this.fieldID = fieldID;
	}
	public ArrayList<Additional> getlAddtional() {
		return lAddtional;
	}
	public void setlAddtional(ArrayList<Additional> lAddtional) {
		this.lAddtional = lAddtional;
	}
}
