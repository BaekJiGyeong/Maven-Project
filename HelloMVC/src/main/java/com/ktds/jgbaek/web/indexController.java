package com.ktds.jgbaek.web;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.jgbaek.vo.LoginVO;

import kr.co.hucloud.utilities.excel.option.ReadOption;
import kr.co.hucloud.utilities.excel.read.ExcelRead;

@Controller
public class indexController {
	
	private Logger logger = LoggerFactory.getLogger(indexController.class);

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String index() {
		
		//Integer.parseInt("aaa");
		return "mainPage";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(HttpSession session) {
		
		if ( session.getAttribute("_MEMBER_") !=null ){
			// 로그인을 했을때의 처리
//			return "redirect:http://www.daum.net"; 절대 URL
//			return "redirect:home"; 상대 URL
			
			// http://localhost:8080/home
			// Redirect 는 response.sendRedirect와 같이 대량의 데이터를 보낼 수 없다.
			return "redirect:/home"; // 절대 URL
		}
		
		// WEB-INF/view/login/login.jsp
		return "login/login";
	}

	/*
	 @RequestMapping(value="/doLogin", method=RequestMethod.POST)
	 public String doLogin(@RequestParam String id, @RequestParam String
	 password, HttpServletRequest request) {
	
	// String id = request.getParameter("id");
	// String password = request.getParameter("password");
	
	 System.out.println("ID"+ id);
	 System.out.println("Password"+ password);
	 System.out.println("memberNumber" +);
	
	 return "";
	 }
	 */
	/*
	@RequestMapping(value = "/doLogin", method = RequestMethod.POST)
	public String doLogin(LoginVO loginVO) {

		// String id = request.getParameter("id");
		// String password = request.getParameter("password");

		System.out.println("ID : " + loginVO.getId());
		System.out.println("Password : " + loginVO.getPassword());
		System.out.println("memberNumber : " + loginVO.getMemberNumber());
		System.out.println("enableAutoLogin : " + loginVO.isEnableAutoLogin());
		
		for(int i=0; i<loginVO.getHobby().size(); i++){
			System.out.println(loginVO.getHobby().get(i));
		}

		return "";
	}
	*/
	@RequestMapping(value = "/doLogin", method = RequestMethod.POST)
	public ModelAndView doLogin(@Valid LoginVO loginVO, Errors errors, HttpSession session, HttpServletResponse response) {

		ModelAndView view = new ModelAndView();
		
		if ( errors.hasErrors() ) {
			view.setViewName("login/login");
			return view;
		}
		view.setViewName("redirect:/home");
		
		MultipartFile uploadFile = loginVO.getUploadFile();
		if ( !uploadFile.isEmpty() ) {
			//String randomFileName = UUID.randomUUID().toString();
			//File tempFile = new File("D:\\" + randomFileName);
			
			File tempFile = new File("D:\\" + uploadFile.getOriginalFilename());
			
			try {
				uploadFile.transferTo(tempFile);
				
				// TODO excel파일 읽어와서 출력하기..
				
				String filePath = tempFile.getAbsolutePath();
				if ( filePath.toUpperCase().endsWith(".XLS") || filePath.toUpperCase().endsWith(".XLSX") ) {
					ReadOption option = new ReadOption();
					option.setFilePath(filePath);
					option.setOutputColumns("A", "B", "C");
					option.setStartRow(1);
					
					List<Map<String, String>> excel = ExcelRead.read(option);
					
					String content = "";
					for (Map<String, String> map : excel) {
						content="";
						content += map.get("A");
						content += "\t";
						content += map.get("B");
						content += "\t";
						content += map.get("C");
						logger.info(content);
						
					}
					
				}
				
				
			} catch (IllegalStateException | IOException e) {
				throw new RuntimeException(e.getMessage(), e);
			}
		}
		
		// String id = request.getParameter("id");
		// String password = request.getParameter("password");

		session.setAttribute("_MEMBER_", loginVO.getId());
		
		System.out.println("ID : " + loginVO.getId());
		System.out.println("Password : " + loginVO.getPassword());
		System.out.println("memberNumber : " + loginVO.getMemberNumber());
		System.out.println("enableAutoLogin : " + loginVO.isEnableAutoLogin());
		
		for(int i=0; i<loginVO.getHobby().size(); i++){
			System.out.println(loginVO.getHobby().get(i));
		}

		return view;
	}

}
