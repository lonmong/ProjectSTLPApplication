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

import com.project.stlp.entity.Assign;
import com.project.stlp.entity.RequestForHelp;
import com.project.stlp.repository.AssignRepository;
import com.project.stlp.util.ResponseObj;


@Controller
@RequestMapping(path = "/viewsuggestion")
public class ViewSuggestionController {

	@Autowired
	AssignRepository mAssignRepository;


	@PostMapping(path = "/viewsuggestionbyidrequest")
	public @ResponseBody ResponseObj viewSuggestionByUsername(@RequestBody Map<String, Object> map)
			throws NoSuchAlgorithmException, UnsupportedEncodingException, ParseException {

		Object objRequestForHelp = map.get("requestforhelp");
		@SuppressWarnings("unchecked")
		Map<String, Object> mapRequestForHelp = (Map<String, Object>) objRequestForHelp;

		RequestForHelp request = new RequestForHelp();
		request.setRequestid((int) mapRequestForHelp.get("requestid"));

		List<Assign> viewsuggestion = mAssignRepository.getAssignByRequestIdAS3(request.getRequestid());

		if (viewsuggestion == null) 
			return new ResponseObj(500, "คำร้องอยู่ระหว่างการพิจารณา?");

		return new ResponseObj(200, viewsuggestion);
	}


}
