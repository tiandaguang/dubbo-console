package com.alibaba.dubbo.web.controller.echo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.dubbo.rpc.service.EchoService;
import com.alibaba.registry.TestRegistryService;

/***
 * 回声测试用于检测服务是否可用，回声测试按照正常请求流程执行，能够测试整个调用是否通畅，可用于监控。
 * 所有服务自动实现EchoService接口，只需将任意服务引用强制转型为EchoService，即可使用。
 * 
 * @author Administrator
 * 
 */
@Controller
public class EchoController {

	@Autowired
	private TestRegistryService testRegistryService;

	@RequestMapping("/echo")
	public ModelAndView index(Model model) {

		EchoService echoService = (EchoService) testRegistryService;
		String status = (String) echoService.$echo("haha"); // 回声测试可用性
		// 本端是否为消费端，这里会返回true
		boolean isConsumerSide = RpcContext.getContext().isConsumerSide();
		// 获取最后一次调用的提供方IP地址
		String serverIP = RpcContext.getContext().getRemoteHost();
		// 获取当前服务配置信息，所有配置信息都将转换为URL的参数
		String application = RpcContext.getContext().getUrl().getParameter("application");
		// assert (status.equals("OK"));
		System.out.println(application);

		// setAttachment设置的KV，在完成下面一次远程调用会被清空。即多次远程调用要多次设置。
		// 隐式传参，后面的远程调用都会隐式将这些参数发送到服务器端，类似cookie，用于框架集成，不建议常规业务使用
		RpcContext.getContext().setAttachment("name", "tianda111111111111");
		System.out.println(testRegistryService.hello("haha"));
		ModelAndView mv = new ModelAndView("helloDubbo");// 指定视图
		String message = "testRegistryService is " + status + " ,isConsumerSide is " + isConsumerSide;
		mv.addObject("message", message);
		return mv;
	}
}
