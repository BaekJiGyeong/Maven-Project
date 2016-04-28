package kr.co.hucloud.security.code.example.index.web;

import kr.co.hucloud.security.code.example.valid.table.service.TableValidService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	private TableValidService tableValidService;
	
	public void setTableValidService(TableValidService tableValidService) {
		this.tableValidService = tableValidService;
	}

	@RequestMapping("/member")
	public String memberIndex() {
		return "memberIndex";
	}
	
	@RequestMapping("/")
	public String index() {
		boolean isInitiatedTable = tableValidService.isInitiatedTable();
		if(!isInitiatedTable) {
			tableValidService.initiateTable();
		}
		return "index";
	}



	
}
