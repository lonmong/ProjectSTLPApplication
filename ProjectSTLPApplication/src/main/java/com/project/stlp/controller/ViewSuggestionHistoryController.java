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
import com.project.stlp.entity.Staff;
import com.project.stlp.repository.AssignRepository;
import com.project.stlp.util.ResponseObj;



@Controller
@RequestMapping(path = "/suggestion")
public class ViewSuggestionHistoryController {

	
	@Autowired
	AssignRepository mAssignRepository;
	
	
	@PostMapping(path = "/listhistory")
	public @ResponseBody ResponseObj listSuggestionHistoryByUsernameAndStatusAssign(@RequestBody Map<String, Object> map)
			throws NoSuchAlgorithmException, UnsupportedEncodingException, ParseException {

		Staff staff = new Staff();
		staff.setUsername((String) map.get("username"));

		List<Assign> queryListassign = mAssignRepository.listSuggestionHistory(staff.getUsername());

		if (queryListassign == null)
			return new ResponseObj(500, "ไม่พบรายการคำร้อง?");

		return new ResponseObj(200, queryListassign);
	}
	
	@PostMapping(path = "/detailsuggestionbyidassign")
	public @ResponseBody ResponseObj viewSuggestionHistory(@RequestBody Map<String, Object> map)
			throws NoSuchAlgorithmException, UnsupportedEncodingException, ParseException {
		Assign assign = new Assign();
		assign.setAssignid((int) map.get("assignid"));

		Assign queryAssign = mAssignRepository.getAssignByAssignid(assign.getAssignid());

		return new ResponseObj(200, queryAssign);
	}


}
