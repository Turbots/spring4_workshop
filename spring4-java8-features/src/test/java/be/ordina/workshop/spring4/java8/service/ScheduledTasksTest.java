package be.ordina.workshop.spring4.java8.service;

import be.ordina.workshop.spring4.java8.config.SpringConfiguration;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by stevedezitter on 25/02/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringConfiguration.class})
@WebAppConfiguration
public class ScheduledTasksTest {

    @Autowired
    private ScheduledTasks scheduledTasks;

    @Test
    public void messageHasBeenInjected() {
        assertNotNull(scheduledTasks.getMessage());

        assertEquals("Scheduled message!", scheduledTasks.getMessage());
    }

    @Test
    public void anotherMessageHasBeenInjected() {
        assertNotNull(scheduledTasks.getAnotherMessage());

        assertEquals("Another message!", scheduledTasks.getAnotherMessage());
    }
}