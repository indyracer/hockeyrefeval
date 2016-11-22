package org.launchcode.controllers;

import javax.servlet.http.HttpSession;

import org.launchcode.models.Admin;
import org.launchcode.models.Evaluator;
import org.launchcode.models.Official;
import org.springframework.beans.factory.annotation.Autowired;

import or.launchcode.models.dao.AdminDoa;
import or.launchcode.models.dao.EvaluatorDao;
import or.launchcode.models.dao.OfficialDao;

public abstract class AbstractUserController {
	
	@Autowired
	protected OfficialDao officialDao;
	
	@Autowired
	protected EvaluatorDao evaluatorDao;//will correct itself when EvaluatorDao created
	
	@Autowired
	protected AdminDoa adminDao;//will correct itself when AdminDao created
	
	
	//for officials
	public static final String officialSessionKey = "official_id"; //DO I NEED TO DO SEPARATE CLASS FOR EVALUATORS AND ADMINS?
	
	protected Official getOfficialFromSession(HttpSession session) {
		Integer officialId = (Integer) session.getAttribute(officialSessionKey);
		return officialId == null ? null : officialDao.findByUid(officialId);
	}
	
	protected void setOfficialInSession(HttpSession session, Official official) {
		session.setAttribute(officialSessionKey, official.getUid());
	}
	
	//for evaluators
	public static final String evaluatorSessionKey = "evaluator_id";
	
	protected Evaluator getEvaluatorFromSession(HttpSession session) {
		Integer evaluatorId = (Integer) session.getAttribute(evaluatorSessionKey);
		return evaluatorId == null ? null : evaluatorDao.findByUid(evaluatorId);
	}
	
	protected void setEvaluatorInSession(HttpSession session, Evaluator evaluator){
		session.setAttribute(evaluatorSessionKey, evaluator.getUid());
	}
	
	//for admin
	public static final String adminSessionKey = "admin_id";
	
	protected Admin getAdminFromSession(HttpSession session) {
		Integer adminId = (Integer) session.getAttribute(adminSessionKey);
		return adminId == null ? null : adminDao.findByUid(adminId);
	}
	
	protected void setAdminInSession(HttpSession session, Admin admin){
		session.setAttribute(adminSessionKey, admin.getUid());
	}

}
