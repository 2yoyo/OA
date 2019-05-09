package com.qf.dao;

import java.util.Map;

import com.qf.entity.Leave;

public interface LeaveMapper extends IBaseDao<Leave> {

	void updateState(Map<String, Integer> map);
}