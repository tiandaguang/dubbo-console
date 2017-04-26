package com.alibaba.dubbo.enterprise.impl;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.dubbo.enterprise.inter.Nofify;
import com.alibaba.notice.Person;

public class NofifyImpl implements Nofify {
	public Map<Integer, Person> ret = new HashMap<Integer, Person>();
	public Map<Integer, Throwable> errors = new HashMap<Integer, Throwable>();

	public void onreturn(Person msg, Integer id) {
		System.out.println("onreturn:" + msg +"id ="+id);
		ret.put(id, msg);
	}

	public void onthrow(Throwable ex, Integer id) {
		errors.put(id, ex);
	}

	public void oninvoke(Integer id) {
		System.out.println("oninvoke");
	}
}