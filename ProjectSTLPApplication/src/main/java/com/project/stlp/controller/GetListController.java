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

import com.project.stlp.entity.Address;
import com.project.stlp.entity.Assign;
import com.project.stlp.entity.Education;
import com.project.stlp.entity.Parent;
import com.project.stlp.entity.Staff;
import com.project.stlp.entity.StatelessPerson;
import com.project.stlp.entity.Witness;
import com.project.stlp.repository.AddressRepository;
import com.project.stlp.repository.AssignRepository;
import com.project.stlp.repository.EducationRepository;
import com.project.stlp.repository.ParentRepository;
import com.project.stlp.repository.WitnessRepository;
import com.project.stlp.util.ResponseObj;

@Controller
@RequestMapping(path = "/list")
public class GetListController {

	
	@Autowired
	EducationRepository mEducationRepository;

	@Autowired
	WitnessRepository mWitnessRepository;

	@Autowired
	AddressRepository mAddressRepository;

	@Autowired
	ParentRepository mParentRepository;
	
	@Autowired
	AssignRepository mAssignRepository;
	
	
	@PostMapping(path = "/education")
	public @ResponseBody ResponseObj getListEducationtByUsername(@RequestBody Map<String, Object> map)
			throws NoSuchAlgorithmException, UnsupportedEncodingException, ParseException {
		StatelessPerson statelessperson = new StatelessPerson();
		statelessperson.setUsername((String) map.get("username"));

		List<Education> queryListeducation = mEducationRepository.getEducationByUsername(statelessperson.getUsername());

		return new ResponseObj(200, queryListeducation);
	}

	@PostMapping(path = "/witness")
	public @ResponseBody ResponseObj getListWitnessByUsername(@RequestBody Map<String, Object> map)
			throws NoSuchAlgorithmException, UnsupportedEncodingException, ParseException {
		StatelessPerson statelessperson = new StatelessPerson();
		statelessperson.setUsername((String) map.get("username"));

		List<Witness> queryListwitness = mWitnessRepository.getWitnessByUsername(statelessperson.getUsername());

		return new ResponseObj(200, queryListwitness);
	}

	@PostMapping(path = "/address")
	public @ResponseBody ResponseObj getListAddressByUsername(@RequestBody Map<String, Object> map)
			throws NoSuchAlgorithmException, UnsupportedEncodingException, ParseException {
		StatelessPerson statelessperson = new StatelessPerson();
		statelessperson.setUsername((String) map.get("username"));

		List<Address> queryListaddress = mAddressRepository.getAddressByUsername(statelessperson.getUsername());

		return new ResponseObj(200, queryListaddress);
	}

	@PostMapping(path = "/parent")
	public @ResponseBody ResponseObj getListParentByUsername(@RequestBody Map<String, Object> map)
			throws NoSuchAlgorithmException, UnsupportedEncodingException, ParseException {
		StatelessPerson statelessperson = new StatelessPerson();
		statelessperson.setUsername((String) map.get("username"));

		List<Parent> queryListparent = mParentRepository.getParentByUsername(statelessperson.getUsername());

		return new ResponseObj(200, queryListparent);
	}
	
	@PostMapping(path = "/assign")
	public @ResponseBody ResponseObj getListAssignByUsername(@RequestBody Map<String, Object> map)
			throws NoSuchAlgorithmException, UnsupportedEncodingException, ParseException {

		Staff staff = new Staff();
		staff.setUsername((String) map.get("username"));

		List<Assign> queryListassign = mAssignRepository.getAssignByUsername(staff.getUsername());

		if (queryListassign == null)
			return new ResponseObj(500, "ไม่พบรายการคำร้อง?");

		return new ResponseObj(200, queryListassign);
	}

}
