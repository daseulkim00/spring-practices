package com.douzone.container.soundsystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CDPlayer {

	@Autowired
	private CompactDisct cd;  //인터페이스로만듬
	
	public void play() {
		cd.play();
	}

}