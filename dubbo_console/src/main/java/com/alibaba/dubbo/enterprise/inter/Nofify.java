package com.alibaba.dubbo.enterprise.inter;

import com.alibaba.notice.Person;

public interface Nofify {
	public void onreturn(Person msg, Integer id);

	public void onthrow(Throwable ex, Integer id);
	
	public void oninvoke(Integer id);
	
}