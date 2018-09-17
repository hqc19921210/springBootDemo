package com.heqichao.springBootDemo.module.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.heqichao.springBootDemo.base.param.ResponeResult;
import com.heqichao.springBootDemo.module.service.LiteEquService;

@RestController
@RequestMapping(value = "/service")
public class LiteEquController {

	@Autowired
	private LiteEquService liteEquService;
	
	
	@RequestMapping(value = "/queryLiteEqus")
	ResponeResult queryLiteEqus() throws Exception {
		return new ResponeResult(liteEquService.queryLiteEqus());
	}
	
	@RequestMapping(value = "/addLiteEqu")
	ResponeResult addLiteEqu() throws Exception {
		return liteEquService.addLiteEqu();
	}
	
	@RequestMapping(value = "/getAppSeleList" )
    public ResponeResult getAppSeleList() throws Exception {
    	return liteEquService.getAppSelectList();
    }
	
	@RequestMapping(value = "/updLiteEqu")
	ResponeResult updLiteEqu() throws Exception {
		return liteEquService.updLiteEqu();
	}
	
	@RequestMapping(value = "/getEquForCmd")
	ResponeResult getEquForCmd() throws Exception {
		return liteEquService.getEquForCmd();
	}
	@RequestMapping(value = "/cmdLiteEqu")
	ResponeResult cmdLiteEqu() throws Exception {
		return liteEquService.cmdLiteEqu();
	}

	@RequestMapping(value = "/deleteLiteEqu")
	ResponeResult deleteLiteEqu() throws Exception {
		return liteEquService.deleteEquByID();
	}
}
