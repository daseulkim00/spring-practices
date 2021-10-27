package com.douzone.container.config.soundsystem;


	import static org.junit.Assert.assertEquals;
	import static org.junit.Assert.assertNotNull;

	import org.junit.Rule;
	import org.junit.Test;
	import org.junit.contrib.java.lang.system.SystemOutRule;
	import org.junit.runner.RunWith;
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.test.context.ContextConfiguration;
	import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

	import com.douzone.container.soundsystem.CDPlayer;


	@RunWith(SpringJUnit4ClassRunner.class)  //기본 러너를 변경
	@ContextConfiguration(classes= {CDPlayerConfig.class})
	public class CDPlayerJavaConfigTest {
		@Rule
		public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();
		
		@Autowired  // 일단 로직을 세우고 cdplayer 만듬 nonull인지 확인  / 빨리실패하고 빨리성공시켜야함

		
		/* 주입 목적으로 한거라 그냥 안해도된다.
		 * @Autowired private CompactDisct cd;
		 * 
		 * 
		 * @Test public void testCompactDisctNotNull() { assertNotNull(cd); }
		 */
		
		private CDPlayer cdplayer;

		@Test
		public void testCDPlayerNotNull() {
			assertNotNull(cdplayer);
		}
		
		@Test
		public void testPlay() {
			cdplayer.play();                                             //맥지원             // 윈도우지원
			assertEquals("playing 붕붕 by 김하온" ,systemOutRule.getLog().replace("\r\n", "").replace("\n", "")); 
			
		}
	
}
