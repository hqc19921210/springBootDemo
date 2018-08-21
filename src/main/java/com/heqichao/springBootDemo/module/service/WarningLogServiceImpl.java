package com.heqichao.springBootDemo.module.service;

import com.github.pagehelper.PageInfo;
import com.heqichao.springBootDemo.base.param.RequestContext;
import com.heqichao.springBootDemo.base.service.EquipmentService;
import com.heqichao.springBootDemo.base.service.UserService;
import com.heqichao.springBootDemo.base.util.PageUtil;
import com.heqichao.springBootDemo.base.util.ServletUtil;
import com.heqichao.springBootDemo.base.util.StringUtil;
import com.heqichao.springBootDemo.module.entity.LightningLog;
import com.heqichao.springBootDemo.module.entity.WarningLog;
import com.heqichao.springBootDemo.module.mapper.WarningLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by heqichao on 2018-8-13.
 */
@Service
@Transactional
public class WarningLogServiceImpl implements WarningLogService {
    @Autowired
    private WarningLogMapper warningLogMapper;
    @Autowired
    private EquipmentService equipmentService;
    @Override
    public WarningLog queryById(String id) {
        return warningLogMapper.queryWarningLog(id);
    }

    @Override
    public PageInfo queryAll() {
        List<String> list =new ArrayList<String>();
        Integer cmp = ServletUtil.getSessionUser().getCompetence();
        if(cmp !=null ){
            //管理员查询所有
            if(UserService.ROOT.equals(cmp)){
                list=equipmentService.getEquipmentIdListAll();
            } else  if(UserService.CUSTOMER.equals(cmp)){  //用户查自己设备
                list=equipmentService.getUserEquipmentIdList(ServletUtil.getSessionUser().getId());
            }
            //访客不允许
        }
        List<WarningLog> res=new ArrayList<WarningLog>();
        if(list!=null && list.size()>0){
            PageUtil.setPage();
            Map map = RequestContext.getContext().getParamMap();
            String status= (String) map.get("status");
            res= warningLogMapper.queryWarningLogByDevIds(list,status);
        }
        PageInfo pageInfo = new PageInfo(res);
        return pageInfo;
    }

    @Override
    public void save(WarningLog log) {
        warningLogMapper.saveWarningLog(log);
    }

    @Override
    public void updateFix(String devId) {
        warningLogMapper.updateStatus(FIXED,new Date(),devId);
    }

    @Override
    public int queryFaultCount() {
        int count =0;
        List<String> list =new ArrayList<String>();
        Integer cmp = ServletUtil.getSessionUser().getCompetence();
        if(cmp !=null ){
            //管理员查询所有
            if(UserService.ROOT.equals(cmp)){
                list=equipmentService.getEquipmentIdListAll();
            } else  if(UserService.CUSTOMER.equals(cmp)){  //用户查自己设备
                list=equipmentService.getUserEquipmentIdList(ServletUtil.getSessionUser().getId());
            }
            //访客不允许
        }
        if(list!=null && list.size()>0){
            //查询故障的数目
            count= warningLogMapper.queryFaultCount(list,FAULT);
        }
        return count;
    }
}
