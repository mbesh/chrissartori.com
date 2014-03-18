package com.chrissartori.images.dao;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.chrissartori.images.bean.SartoriImage;
import com.chrissartori.xml.Images;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

@Repository
public class SartoriImageDaoImpl implements SartoriImageDao {

	private Unmarshaller imageUnmarshaller;

	@Value("${com.chrissartori.images.imageLookupUrl:http://www.mikebeshai.com/img/images.php}")
	private String imageResourceUrl;
	
	@Value("${com.chrissartori.images.baseUrl:resources/images/}")
	private String baseUrl;

	private static final Logger LOG = LoggerFactory.getLogger(SartoriImageDaoImpl.class);
	
	private static final String[] descriptions = new String[] {
		"What a beaut!", 
		"Can you imagine the mane on this beast?",
		"What an awesome dude!",
		"Yea, I'd tap that.",
		"Stare into his eyes and feel the love.",
		"I would love to have a drink siphoned through his manly beard hairs.",
		"My heart is melting...",
		"In the year 3000, Chris Sartori rules the world.",
		"Chris Sartori built the planet using a variety of woodworking tools.",
		"POWERRRRRRRR!!!!!!!!!!",
		"This thing has a turbo.",
		"Make love to me, Chris Sartori!",
	};
	
	@PostConstruct
	public void init() {
		try {
		 imageUnmarshaller = JAXBContext.newInstance(com.chrissartori.xml.Images.class).createUnmarshaller();
		} catch(Exception e) {
			throw new RuntimeException("Failed to initialize JAXBContext for com.chrissartori.xml.Images", e);
		}
	}
	
	@Override
	public List<SartoriImage> getAllImages() {
		Client c = Client.create();
		WebResource r = c.resource(imageResourceUrl);
		try {
			Images images = (Images) imageUnmarshaller.unmarshal(r.get(InputStream.class));
			ArrayList<SartoriImage> imageList = new ArrayList<SartoriImage>();
			Random rand = new Random();
			for(String i : images.getImage()) {
				SartoriImage newImg = new SartoriImage();
				newImg.setUrl(baseUrl + i);
				newImg.setDescription(descriptions[rand.nextInt(descriptions.length)]);
				imageList.add(newImg);
			}
			return imageList;
		} catch(Exception e) {
			LOG.error("Exception unmarshalling images", e);
			return new ArrayList<SartoriImage>();
		}
	}

}
