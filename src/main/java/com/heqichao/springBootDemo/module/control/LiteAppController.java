package com.heqichao.springBootDemo.module.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.heqichao.springBootDemo.base.param.ResponeResult;
import com.heqichao.springBootDemo.module.service.LiteAppService;

@RestController
@RequestMapping(value = "/service")
public class LiteAppController {

	@Autowired
	private LiteAppService liteAppService;
	
	
	@RequestMapping(value = "/queryLiteApps")
	ResponeResult queryLiteApps() throws Exception {
		return new ResponeResult(liteAppService.queryLiteApps());
	}
	
	@RequestMapping(value = "/addLiteApp")
	ResponeResult addLiteApp() throws Exception {
		return liteAppService.addLiteApp();
	}
	
	@RequestMapping(value = "/updLiteApp")
	ResponeResult updLiteApp() throws Exception {
		return liteAppService.updLiteApp();
	}
	
	@RequestMapping(value = "/resetSecret")
	ResponeResult resetSecret() throws Exception {
		return liteAppService.resetSecret();
	}

	@RequestMapping(value = "/deleteLiteApp")
	ResponeResult deleteLiteApp() throws Exception {
		return liteAppService.deleteAppByID();
	}
	
	@RequestMapping(value = "/subLiteDataChg")
	ResponeResult subLiteDataChg() throws Exception {
		return liteAppService.subLiteDataChg();
	}
	
	@RequestMapping(value = "/deleteLiteDataAll")
	ResponeResult deleteLiteAll() throws Exception {
		return liteAppService.deleteAppByID();
	}
}
