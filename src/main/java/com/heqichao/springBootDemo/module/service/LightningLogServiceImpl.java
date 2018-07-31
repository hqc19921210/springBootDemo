package com.heqichao.springBootDemo.module.service;

import com.github.pagehelper.PageInfo;
import com.heqichao.springBootDemo.base.param.RequestContext;
import com.heqichao.springBootDemo.base.service.EquipmentService;
import com.heqichao.springBootDemo.base.service.UserService;
import com.heqichao.springBootDemo.base.util.PageUtil;
import com.heqichao.springBootDemo.base.util.ServletUtil;
import com.heqichao.springBootDemo.base.util.StringUtil;
import com.heqichao.springBootDemo.module.entity.LightningLog;
import com.heqichao.springBootDemo.module.mapper.LightningLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by heqichao on 2018-7-15.
 */
@Service
@Transactional
public class LightningLogServiceImpl implements LightningLogService {

    @Autowired
    private LightningLogMapper lightningLogMapper;

    @Autowired
    private EquipmentService equipmentService;

    @Override
    public LightningLog queryById(String id) {
        return null;
    }

    @Override
    public PageInfo queryAll() {
        List<String> list =new ArrayList<String>();
        Integer cmp =ServletUtil.getSessionUser().getCompetence();
        if(cmp !=null ){
            //管理员查询所有
            if(UserService.ROOT.equals(cmp)){
                list=equipmentService.getEquipmentIdListAll();

            } else  if(UserService.CUSTOMER.equals(cmp)){  //用户查自己设备
                     list=equipmentService.getUserEquipmentIdList(ServletUtil.getSessionUser().getId());
            }
            //访客不允许

        }
        List<LightningLog> res=new ArrayList<LightningLog>();
        if(list!=null && list.size()>0){
            PageUtil.setPage();
            Map map = RequestContext.getContext().getParamMap();
            String functionCode= (String) map.get("functionCode");
            String devEUI= (String) map.get("devEUI");
            String start= (String) map.get("start");
            String end= (String) map.get("end");
            if(StringUtil.isNotEmpty(end)){
                end=end+" 23:59:59";
            }
            res= lightningLogMapper.queryLightningLogByDevIds(list,devEUI,end,start,functionCode);
        }

        PageInfo pageInfo = new PageInfo(res);
        return pageInfo;

    }

    @Override
    public void save(LightningLog log) {
        lightningLogMapper.saveLightningLog(log);
    }

    @Override
    public void setDevError(String devId, Date time) {

    }

    @Override
    public List<String> queryLogOnTime(int time) {
        return lightningLogMapper.queryLogOnTime(time);
    }
}
