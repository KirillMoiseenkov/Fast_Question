package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//@Component
public class TestServices {

    @Autowired
    public TestService testService;

    @Autowired
    public MessageService messageService;

}
