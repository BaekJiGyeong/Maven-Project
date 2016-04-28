package kr.co.hucloud.security.code.example.attack.encoding.web;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import sun.misc.BASE64Decoder;

@Controller
public class EncodingController {

	@RequestMapping("/attack/encoding")
	public String encoding(HttpServletResponse response) {
		return "attack/encoding/encoding";
	}
	
	@RequestMapping("/attack/encoding/method1")
	public String encodingMethod1(HttpServletRequest request, HttpServletResponse response) {
		
		String textData = request.getParameter("text");
		System.out.println("Encoding 전 : " + textData);
		
		//FIXME URLDecoder 를 이용한 Encoding
		
		System.out.println("Encoding 후 : " + textData);
		
		return "attack/encoding/encoding";
	}
	
	@RequestMapping("/attack/encoding/method2")
	public ModelAndView encodingMethod2(HttpServletRequest request, HttpServletResponse response) {
		
		String textData = request.getParameter("text");
		System.out.println("Decoding 전 : " + textData);
		
		//FIXME Base64Decoder를 이용한 Decoding
		
		System.out.println("Decoding 후 : " + textData);
		
		ModelAndView view = new ModelAndView();
		view.setViewName("attack/encoding/encoding");
		view.addObject("inputValue", request.getParameter("text"));
		view.addObject("result", textData);
		
		return view;
	}
	
	@RequestMapping("/attack/encoding/method3")
	public ModelAndView encodingMethod3(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView view = new ModelAndView();
		view.setViewName("attack/encoding/encoding");
		
		view.addObject("inputValue2", request.getParameter("text"));
		
		String textData = request.getParameter("text");
		System.out.println("Encoding 전 : " + textData);
		
		//FIXME URLEncoder를 이용한 Encoding과 HTML Encoding
		// URLEncoding
		System.out.println("URL Encoding 후 : " + textData);
		view.addObject("result2_1", textData);
		
		textData = request.getParameter("text");
		// HTML Encoding
		System.out.println("HTML Encoding 후 : " + textData);
		
		view.addObject("result2_2", textData);
		
		return view;
	}
	
}
