package com.project.stlp.controller;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.stlp.entity.Assign;
import com.project.stlp.entity.RequestForHelp;
import com.project.stlp.repository.AssignRepository;
import com.project.stlp.repository.RequestForHelpRepository;
import com.project.stlp.util.ResponseObj;

@Controller
@RequestMapping(path = "/exporttopdf")
public class ExportToPDFFileController {

	
	@Autowired
	RequestForHelpRepository mRequestnRepository;
	
	@Autowired
	AssignRepository mAssignRepository;
	
	@PostMapping(path = "/getdetailsrequestbyidrequest", consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseObj detailRequestByUsername(@RequestBody Map<String, Object> map)
			throws NoSuchAlgorithmException, UnsupportedEncodingException, ParseException {
			
		RequestForHelp request = new RequestForHelp();
		request.setRequestid((int)map.get("requestid"));
		
		RequestForHelp requestbyid = mRequestnRepository.getRequestByIdRequest(request.getRequestid());
		
		if (requestbyid == null)
			return new ResponseObj(500, "ไม่พบคำร้อง?");

		return new ResponseObj(200, requestbyid);
	}

	
	@PostMapping(path = "/detailbestsuggestion")
	public @ResponseBody ResponseObj detailBestSuggestionByIdrequestAndStatusAssign(@RequestBody Map<String, Object> map)
			throws NoSuchAlgorithmException, UnsupportedEncodingException, ParseException {

		Object objRequestForHelp = map.get("requestforhelp");
		@SuppressWarnings("unchecked")
		Map<String, Object> mapRequestForHelp = (Map<String, Object>) objRequestForHelp;

		RequestForHelp request = new RequestForHelp();
		request.setRequestid((int) mapRequestForHelp.get("requestid"));

		Assign queryBestSuggestion = mAssignRepository.detailBestSuggestion(request.getRequestid());

		if (queryBestSuggestion == null)
			return new ResponseObj(500, "ไม่พบรายการคำร้อง?");

		return new ResponseObj(200, queryBestSuggestion);
	}


}
