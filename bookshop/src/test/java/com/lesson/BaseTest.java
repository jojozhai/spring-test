/**
 * 
 */
package com.lesson;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.lesson.BookShopApplication;

/**
 * @author zhailiang
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BookShopApplication.class)
@Transactional
public class BaseTest {
	
}
