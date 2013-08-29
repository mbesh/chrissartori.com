package com.chrissartori.images.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.chrissartori.images.bean.SartoriImage;

@Service
public class SartoriImageServiceImpl implements SartoriImageService {

	@Value("${com.chrissartori.images.baseUrl:/images/resources/images/}")
	private String baseUrl;
	
	@Override
	public SartoriImage getRandomSartoriImage() {
		SartoriImage image = new SartoriImage();
		image.setUrl(baseUrl + "image1.jpg");
		image.setDescription("What an awesome dude!");
		return image;
	}

}
