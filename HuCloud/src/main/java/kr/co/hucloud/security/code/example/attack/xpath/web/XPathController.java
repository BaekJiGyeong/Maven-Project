package kr.co.hucloud.security.code.example.attack.xpath.web;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

@Controller
public class XPathController {

	@RequestMapping("/attack/xpath")
	public String xpath(HttpServletResponse response) {
		return "attack/xpath/xpath";
	}
	
	@RequestMapping("/attack/xpath/test")
	public ModelAndView xpathTest(HttpServletRequest request) {
		
		ModelAndView view = new ModelAndView();
		view.setViewName("attack/xpath/xpath");
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		//FIXME XPath Injection 코드 수정
		
		XPathFactory xPathFactory = XPathFactory.newInstance();
		XPath xPath = xPathFactory.newXPath();
		String exprString = 
				"//addressBook/address[email/text()='" + email + 
				"' and password/text()='" + password + "']/*/text()";
		
		XPathExpression expr = null;
		try {
			expr = xPath.compile(exprString);
		} catch (XPathExpressionException e) {
			throw new RuntimeException(e);
		}
		
		if(expr != null) {
			
			Document doc = null;
			try {
				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			    DocumentBuilder builder = factory.newDocumentBuilder();
			    InputStream is = XPathController.class.getClassLoader().getResource("users.xml").openStream();
			    doc = builder.parse(is);
			} catch (SAXException | IOException | ParserConfigurationException e) {
				throw new RuntimeException(e);
			}
				
			if(doc != null) {
				try {
					Object result = expr.evaluate(doc, XPathConstants.NODESET);
					NodeList nodes = (NodeList)result;
					for ( int i = 0; i < nodes.getLength(); i++ ) {
						Node node = nodes.item(i);
						view.addObject(node.getParentNode().getNodeName(), node.getNodeValue());
					}
				} catch (XPathExpressionException e) {
					throw new RuntimeException(e);
				}
			}
			
		}
		
		return view;
	}
	
}
