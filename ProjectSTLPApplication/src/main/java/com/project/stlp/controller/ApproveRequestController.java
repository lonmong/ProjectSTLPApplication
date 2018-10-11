package com.project.stlp.controller;


import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.stlp.entity.Center;
import com.project.stlp.entity.RequestForHelp;
import com.project.stlp.entity.StatelessPerson;
import com.project.stlp.repository.RequestForHelpRepository;
import com.project.stlp.util.ResponseObj;


@Controller
@RequestMapping(path = "/approverequest")
public class ApproveRequestController {

	
	@Autowired
	RequestForHelpRepository mRequestnRepository;
	
	@PostMapping(path = "/listrequestforhelp")
	public @ResponseBody ResponseObj getListRequestForHelpByTelCenter(@RequestBody Map<String, Object> map)
			throws NoSuchAlgorithmException, UnsupportedEncodingException, ParseException {

		Center center = new Center();
		center.setTelcenter((String) map.get("telcenter"));

		List<RequestForHelp> queryListrequest = mRequestnRepository.getRequestByTelCenter(center.getTelcenter());

		if (queryListrequest == null)
			return new ResponseObj(500, "ไม่พบรายการคำร้อง?");

		return new ResponseObj(200, queryListrequest);
	}
	
	@PostMapping(path = "/getdetailsrequestbyusername")
	public @ResponseBody ResponseObj detailRequestByUsername(@RequestBody Map<String, Object> map)
			throws NoSuchAlgorithmException, UnsupportedEncodingException, ParseException {
		
		Object objStattelessperson = map.get("statelessperon");
		@SuppressWarnings("unchecked")
		Map<String, Object> mapStatelessperson = (Map<String, Object>) objStattelessperson;
		
		StatelessPerson stateless = new StatelessPerson();
		stateless.setUsername((String)mapStatelessperson.get("username"));

		RequestForHelp queryrequestForhelp = mRequestnRepository.getDetailrequestByUsername(stateless.getUsername());

		return new ResponseObj(200, queryrequestForhelp);
	}
	
	@PostMapping(path = "/setapproverequeststatus")
	public @ResponseBody ResponseObj requestNotapprove(@RequestBody Map<String, Object> map)
			throws NoSuchAlgorithmException, UnsupportedEncodingException, ParseException {
		
		Object objStattelessperson = map.get("statelessperon");
		@SuppressWarnings("unchecked")
		Map<String, Object> mapStatelessperson = (Map<String, Object>) objStattelessperson;
		
		StatelessPerson stateless = new StatelessPerson();
		stateless.setUsername((String)mapStatelessperson.get("username"));
		
		RequestForHelp request = new RequestForHelp();
		request.setStatusrequest((int)map.get("statusrequest"));

		RequestForHelp updateStatusRequest = mRequestnRepository.doApproveRequestForHelp(stateless.getUsername());

		updateStatusRequest.setStatusrequest(request.getStatusrequest());

		mRequestnRepository.save(updateStatusRequest);

		return new ResponseObj(200, updateStatusRequest);
	}

}
