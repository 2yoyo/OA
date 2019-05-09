package com.qf.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qf.dao.IBaseDao;
import com.qf.dao.LeaveMapper;
import com.qf.entity.Leave;
import com.qf.service.ILeaveService;

@Service
public class LeaveServiceImpl extends BaseServiceImpl<Leave> implements ILeaveService {
	
	@Autowired
	private LeaveMapper leaveMapper;

	@Override
	protected IBaseDao<Leave> getBaseDao() {
		return leaveMapper;
	}

	@Override
	public void updateState(Map<String, Integer> map) {
		leaveMapper.updateState(map);
	}

}
