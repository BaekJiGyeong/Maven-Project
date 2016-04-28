package kr.co.hucloud.security.code.example.valid.table.web;

import javax.servlet.http.HttpServletResponse;

import kr.co.hucloud.security.code.example.common.util.SendMessage;
import kr.co.hucloud.security.code.example.valid.table.service.TableValidService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TableController {

	private TableValidService tableValidService;
	
	public void setTableValidService(TableValidService tableValidService) {
		this.tableValidService = tableValidService;
	}

	@RequestMapping("/table/deleteAllTable")
	public void deleteAllTable(HttpServletResponse response) {
		tableValidService.dropAllTable();
		SendMessage.send(response, "OK");
	}

	
}
