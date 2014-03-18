package com.chrissartori.images.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chrissartori.images.bean.SartoriImage;
import com.chrissartori.images.service.SartoriImageService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger LOG = LoggerFactory
			.getLogger(HomeController.class);

	@Autowired
	private SartoriImageService sartoriImageService;

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = { "/", "/index.html"}, method = RequestMethod.GET)
	public String home(Model model) {		
		SartoriImage image = sartoriImageService.getRandomSartoriImage();

		model.addAttribute("url", image.getUrl());
		model.addAttribute("description", image.getDescription());
		return "home";
	}

	@RequestMapping(value = "/image", method = RequestMethod.GET)
	public @ResponseBody
	SartoriImage getRandomImage() {
		SartoriImage image = sartoriImageService.getRandomSartoriImage();
		return image;
	}
}
