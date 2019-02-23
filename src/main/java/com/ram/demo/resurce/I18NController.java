package com.ram.demo.resurce;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RestController
public class I18NController {

	@Autowired
	MessageSource messageSource;
	
	@GetMapping("/welcome")
	@ApiOperation(value=" This resource is to get welcome message for user of any locale")
	public String getWelcomeMessage(/* @RequestHeader(name="Accept-Language", required=false) Locale locale */) {
		
		return messageSource.getMessage("welcome.message", null, LocaleContextHolder.getLocale());
		//return "Welcome";
	}
}
