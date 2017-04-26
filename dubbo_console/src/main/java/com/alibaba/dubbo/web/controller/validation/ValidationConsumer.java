package com.alibaba.dubbo.web.controller.validation;

import java.util.Date;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.dubbo.rpc.RpcException;
import com.alibaba.validation.ValidationParameter;
import com.alibaba.validation.ValidationService;

/**
 * ValidationConsumer
 * 
 * @author william.liangf
 */
@RequestMapping("/validation")
@Controller
public class ValidationConsumer {
	@Autowired
	private ValidationService validationService;

	@RequestMapping("/save")
	public ModelAndView save(Model model) throws Exception {
		ValidationParameter parameter = new ValidationParameter();
		parameter.setName("liangfei");
		parameter.setEmail("liangfei@liang.fei");
		parameter.setAge(50);
		parameter.setLoginDate(new Date(System.currentTimeMillis() - 1000000));
		parameter.setExpiryDate(new Date(System.currentTimeMillis() + 1000000));
		validationService.save(parameter);
		System.out.println("Validation Save OK");
		// Save Error
		try {
			parameter = new ValidationParameter();
			validationService.save(parameter);
		} catch (RpcException e) {
			System.err.println("Validation Save ERROR");
			ConstraintViolationException ve = (ConstraintViolationException) e.getCause();
			Set<ConstraintViolation<?>> violations = ve.getConstraintViolations();
			System.out.println(violations);
		}

		ModelAndView mv = new ModelAndView("ybdy");// 指定视图
		return mv;
	}

	@RequestMapping("/delete")
	public ModelAndView delete(Model model) throws Exception {
		// Delete OK
		ValidationParameter parameter = new ValidationParameter();
		parameter.setName("abc");
		parameter.setAge(25);
		// Delete Error
		try {
			validationService.delete(parameter);
			System.out.println("Validation Delete OK");

			parameter = new ValidationParameter();
			validationService.delete(parameter);
		} catch (RpcException e) {
			System.err.println("Validation Delete ERROR");
			ConstraintViolationException ve = (ConstraintViolationException) e.getCause();
			Set<ConstraintViolation<?>> violations = ve.getConstraintViolations();
			System.out.println(violations);
		}

		ModelAndView mv = new ModelAndView("ybdy");// 指定视图
		return mv;
	}
}