package com.jei.springstudy;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jei.springstudy.SpringStudyApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SpringStudyApplication.class)
@WebAppConfiguration
public class SpringStudyApplicationTests {

	@Test
	public void contextLoads() {
	}

}
