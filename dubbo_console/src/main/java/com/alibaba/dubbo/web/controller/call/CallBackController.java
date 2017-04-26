package com.alibaba.dubbo.web.controller.call;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.call.CallbackListener;
import com.alibaba.call.CallbackService;

/***
 * 回声测试用于检测服务是否可用，回声测试按照正常请求流程执行，能够测试整个调用是否通畅，可用于监控。
 * 所有服务自动实现EchoService接口，只需将任意服务引用强制转型为EchoService，即可使用。
 * 
 * @author Administrator
 * 
 */
@Controller
public class CallBackController {

	@Autowired
	private CallbackService callbackService;

	@RequestMapping("/call")
	public ModelAndView index(Model model) {
		callbackService.addListener("callback1", new CallbackListener() {
			@Override
			public void changed(String msg) {
				System.out.println("callback1:"+msg);
			}
		});
		
		callbackService.addListener("callback2", new CallbackListener() {
			@Override
			public void changed(String msg) {
				System.out.println("callback2---->:"+msg);
			}
		});
		
		return new ModelAndView("ybdy");
	}
}
