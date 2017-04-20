package com.sys.tianda.dubbo_console;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sys.cache.TestRegistryService;

@Controller
public class IndexController {

	@Autowired
	private TestRegistryService testRegistryService;

	@RequestMapping("/hello")
	public ModelAndView index(Model model) {
		String message = testRegistryService.hello("dubbo");
		ModelAndView mv = new ModelAndView("helloDubbo");// 指定视图
		mv.addObject("message", message);
		return mv;
	}

}