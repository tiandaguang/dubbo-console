package com.alibaba.dubbo.web.controller.notice;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.dubbo.enterprise.impl.NofifyImpl;
import com.alibaba.notice.IDemoService;
import com.alibaba.notice.Person;

@Controller
public class NoticeController {
	@Resource(name="demoService")
	private IDemoService demoService;
	@Resource(name = "demoCallback")
	private NofifyImpl demoCallback;

	@RequestMapping("/notice")
	public ModelAndView notice(Model model) throws Exception {
		int requestId = 2;
		Person person = demoService.get(requestId);
		System.out.println(person);
		// for Test：只是用来说明callback正常被调用，业务具体实现自行决定.
		// for Test：只是用来说明callback正常被调用，业务具体实现自行决定.
		for (int i = 0; i < 10; i++) {
			demoCallback.ret.put(i, new Person(i, "tianda" + i, String.valueOf(i)));
		}

		// System.out.println(demoCallback.ret);

		System.out.println(demoCallback.ret.get(requestId));

		return new ModelAndView("helloDubbo");// 指定视图
	}
}
