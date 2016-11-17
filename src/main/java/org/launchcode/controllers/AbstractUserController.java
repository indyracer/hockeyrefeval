package org.launchcode.controllers;

import javax.servlet.http.HttpSession;

import org.launchcode.models.Official;
import org.springframework.beans.factory.annotation.Autowired;

import or.launchcode.models.dao.OfficialDao;

public abstract class AbstractUserController {
	
	@Autowired
	protected OfficialDao officialDao;
	
	@AutoWired
	protected EvaluatorDao evaluatorDao;//will correct itself when EvaluatorDao created
	
	@AutoWired
	protected AdminDao adminDao;//will correct itself when AdminDao created
	
	public static final String userSessionKey = "official_id"; //DO I NEED TO DO SEPARATE CLASS FOR EVALUATORS AND ADMINS?
	
	protected Official getOfficialFromSession(HttpSession session) {
		Integer officialId = (Integer) session.getAttribute(userSessionKey);
		return officialId == null ? null : officialDao.findByUid(officialId);
	}
	
	protected void setUserInSession(HttpSession session, Official official) {
		session.setAttribute(userSessionKey, official.getUid());
	}

}
