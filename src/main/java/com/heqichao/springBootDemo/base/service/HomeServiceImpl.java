package com.heqichao.springBootDemo.base.service;

import com.heqichao.springBootDemo.base.mapper.HomeMapper;
import com.heqichao.springBootDemo.base.entity.HomeEntity;
import com.heqichao.springBootDemo.base.entity.User;
import com.heqichao.springBootDemo.base.util.ServletUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

/**
 * @author Muzzy Xu.
 * 
 */


@Service
@Transactional
public class HomeServiceImpl implements HomeService {
    @Autowired
    private HomeMapper hMapper ;
    

    @Override
    public HomeEntity queryPieData() {
    	User user = ServletUtil.getSessionUser();
    	Integer competence = user.getCompetence();
    	Integer id = user.getId();
    	Integer parentId = user.getParentId();
    	HomeEntity hentity = new HomeEntity();
    	List<Integer> pieMap = hMapper.queryPieData(competence, id, parentId);
    	Integer equOnline = hMapper.queryOnlineEqu(competence, id, parentId);
    	Integer equAll = hMapper.queryAllEqu(competence, id, parentId);
    	if(competence == HomeService.ADMINCMP) {
    		List<Integer> users =hMapper.queryUserData();
    		hentity.setCustAll(users.get(0));
    		hentity.setUserAll(users.get(1));
    	}else {
    		List<String> users =hMapper.queryUserMax(competence, id, parentId);
    		hentity.setLastTime(users.get(0));
    		hentity.setPeak(users.get(1));
    		
    	}
    	hentity.setEquNom(pieMap.get(1));//LOA设备总数
    	hentity.setEquBrD(equOnline);//在线设备
    	hentity.setEquAll(equAll);//总设备
    	hentity.setPieMap(pieMap);
    	return hentity;
    }


}
