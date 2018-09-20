package com.heqichao.springBootDemo.module.service;

import com.github.pagehelper.PageInfo;
import com.heqichao.springBootDemo.base.param.RequestContext;
import com.heqichao.springBootDemo.base.service.EquipmentService;
import com.heqichao.springBootDemo.base.service.UserService;
import com.heqichao.springBootDemo.base.util.*;
import com.heqichao.springBootDemo.module.entity.LightningLog;
import com.heqichao.springBootDemo.module.mapper.LightningLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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
        List<String> list =getMyDevList();
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
    public List<String> queryLogOnTime(int time) {
        return lightningLogMapper.queryLogOnTime(time);
    }

    @Override
    public void deleteAll() {
        List<String> list =getMyDevList();
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
            lightningLogMapper.deleteLightningLogByDevIds(list,devEUI,end,start,functionCode);
        }

    }

    @Override
    public List<Map> queryLightCountByYear() {
        String year = DateUtil.dateToString(new Date(),"yyyy");
        List<String> list =getMyDevList();
        if(list!=null && list.size()>0){
            return lightningLogMapper.queryLightCountByYear(year,getMyDevList());
        }
        return new ArrayList<Map>();

    }

    @Override
    public List<Map> queryLightChart() {
        List<String> list =getMyDevList();
        List<Map> res=new ArrayList<Map>();
        if(list!=null && list.size()>0){
            Map map = RequestContext.getContext().getParamMap();
            String devEUI= (String) map.get("devEUI");
            String logId= (String) map.get("logId");
            String start= (String) map.get("start");
            String end= (String) map.get("end");
            if(StringUtil.isNotEmpty(end)){
                end=end+" 23:59:59";
            }
            if(StringUtil.isNotEmpty(start) || StringUtil.isNotEmpty(end)){
                //有时间范围则不限制数量
                res= lightningLogMapper.queryLigChatAll(list,devEUI,end,start);
            }else{
                //限制15条
                Integer size= (Integer) map.get("size");
                List<Map> left =lightningLogMapper.queryLigChatLeft(list,devEUI,logId,size/2);
                if(left==null || left.size()<1){
                    left=new ArrayList<Map>();
                }
                List<Map> right=lightningLogMapper.queryLigChatRight(list,devEUI,logId,size-left.size());
                if(right!=null && right.size()>0){
                    res.addAll(right);
                }
                //重新排序 按顺序排序
                Collections.sort(res, new Comparator<Map>(){
                    @Override
                    public int compare(Map p1, Map p2) {
                        //按照Person的年龄进行升序排列
                        String t1 = (String) p1.get("ligntningTime");
                        String t2 = (String) p2.get("ligntningTime");
                        return t1.compareTo(t2);
                    }
                });
            }
        }

        return res;
    }

    private List<String > getMyDevList(){
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
        return list;
    }
}
