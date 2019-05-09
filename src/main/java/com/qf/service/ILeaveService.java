package com.qf.service;

import java.util.Map;

import com.qf.entity.Leave;

public interface ILeaveService extends IBaseService<Leave> {

	void updateState(Map<String, Integer> map);

}
