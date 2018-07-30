package com.heqichao.springBootDemo.base.control;

import com.heqichao.springBootDemo.base.param.ResponeResult;
import com.heqichao.springBootDemo.base.service.EquipmentService;
import com.heqichao.springBootDemo.base.service.UserService;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Muzzy Xu.
 * 
 */


@RestController
@RequestMapping(value = "/service")
public class EquipmentController extends BaseController{

    @Autowired
    private EquipmentService eService;

    
    @RequestMapping(value = "/getEquipments")
    public ResponeResult getUsers() {
    	return new ResponeResult(eService.queryEquipmentList());
    }
    
    @RequestMapping(value = "/getUserEqu")
    public List<String> getUserEquipmentIdList(@RequestParam("id") Integer uid) {
    	return eService.getUserEquipmentIdList(uid);
    }
    
    @RequestMapping(value = "/getEquAll")
    public List<String> getEquipmentIdListAll() {
    	return eService.getEquipmentIdListAll();
    }
    
    @RequestMapping(value = "/addEqu" )
    @ResponseBody
    public ResponeResult addUser(@RequestBody Map map) throws Exception {
        return eService.insertEqu(map);
    }
    
    @RequestMapping(value = "/delEqu" )
    @ResponseBody
    public ResponeResult delEqu(@RequestBody Map map) throws Exception {
    	return eService.deleteEquByID(map);
    }
    

}
