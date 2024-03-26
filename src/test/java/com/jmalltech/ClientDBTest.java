package com.jmalltech;

import com.jmalltech.entity.Client;
import com.jmalltech.repository.impl.ClientServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ClientDBTest {
    @Autowired
    private ClientServiceImpl clientService;

    @Test
    public void test1() {
        Client c = new Client();
        c.setUsername("client1");
        c.setPassword("123456");
        c.setCreatedById(1L);
        c.setCompanyName("ammall");
        clientService.save(c);
        c = clientService.getById(1L);
        c.setEmail("client1@jmalltech.com");
        clientService.updateById(c);
        System.out.println(clientService.getById(1L));
    }

    @Test
    public void test2(){

        System.out.println(clientService.removeById(3L));
    }
}
