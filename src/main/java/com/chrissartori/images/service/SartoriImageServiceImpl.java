package com.chrissartori.images.service;

import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.chrissartori.images.bean.SartoriImage;
import com.chrissartori.images.dao.SartoriImageDao;

@Service
public class SartoriImageServiceImpl implements SartoriImageService {

	private static final Logger LOG = LoggerFactory
			.getLogger(SartoriImageServiceImpl.class);
	
	private List<SartoriImage> sartoriImages;
	
	@Autowired
	private SartoriImageDao sartoriImageDao;
	
	private Date lastInitialized;
	
	private final static long ONE_DAY = 86400000;
	
	@PostConstruct
	public void init() {
		sartoriImages = sartoriImageDao.getAllImages();
		lastInitialized = new Date();
		
	}
	
	@Override
	public SartoriImage getRandomSartoriImage() {
		// Reload images every day
		if(System.currentTimeMillis() - ONE_DAY >= lastInitialized.getTime()) {
			sartoriImages = sartoriImageDao.getAllImages();
		}
		return sartoriImages.get(new Random().nextInt(sartoriImages.size()));
	}

}
