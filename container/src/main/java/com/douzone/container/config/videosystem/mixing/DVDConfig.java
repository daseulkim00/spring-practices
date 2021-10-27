package com.douzone.container.config.videosystem.mixing;

import org.springframework.context.annotation.Bean;

import com.douzone.container.videosystem.Avengers;
import com.douzone.container.videosystem.BlankDisc;
import com.douzone.container.videosystem.DigitalVideoDisc;

public class DVDConfig {
	
	@Bean 
	public DigitalVideoDisc avengers() {
		return new Avengers();
	}
	
	@Bean(name ="avengersInfinityWar")  // dvd만 플리에하는 설정
	public DigitalVideoDisc blankDisc() {
		BlankDisc dvd = new BlankDisc();
		dvd.setTitle("Avengers Infinity War");
		dvd.setStudio("MARVEL");
		return dvd;
		
	}
}
