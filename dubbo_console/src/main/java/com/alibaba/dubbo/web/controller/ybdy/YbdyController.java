package com.alibaba.dubbo.web.controller.ybdy;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.bar.BarService;
import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.foo.FooService;
import com.alibaba.registry.TestRegistryService;

@Controller
public class YbdyController {

	@Autowired
	private TestRegistryService testRegistryService;
	@Autowired
	private FooService fooService;
	@Autowired
	private BarService barService;

	@RequestMapping("/ybdy")
	public ModelAndView index(Model model) throws Exception {
		Calendar ca = Calendar.getInstance();
		ca.setTime(new Date());
		Long start = ca.getTimeInMillis();
		System.out.println(start);
		fooService.findFoo(); // 此调用会立即返回null
		Future<String> fooFuture = RpcContext.getContext().getFuture();
		// 拿到调用的Future引用，当结果返回后，会被通知和设置到此Future。
		barService.findBar(); // 此调用会立即返回null
		Future<String> barFuture = RpcContext.getContext().getFuture();
		// 拿到调用的Future引用，当结果返回后，会被通知和设置到此Future。
		// 此时findFoo和findBar的请求同时在执行，客户端不需要启动多线程来支持并行，而是借助NIO的非阻塞完成。
		String foo = fooFuture.get();
		// 如果foo已返回，直接拿到返回值，否则线程wait住，等待foo返回后，线程会被notify唤醒。
		String bar = barFuture.get();
		ca.setTime(new Date());
		Long end = Calendar.getInstance().getTimeInMillis();
		System.out.println(end);

		// 同理等待bar返回。
		// 如果foo需要5秒返回，bar需要6秒返回，实际只需等6秒，即可获取到foo和bar，进行接下来的处理。
		String message = "总共执行了" + (end - start) / 1000 + "s";
		ModelAndView mv = new ModelAndView("ybdy");// 指定视图
		mv.addObject("message", message);
		mv.addObject("foo", foo);
		mv.addObject("bar", bar);
		return mv;
	}
}