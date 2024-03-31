package com.jmalltech;

import com.jmalltech.entity.Client;
import com.jmalltech.service.ClientDomainService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ClientDBTest {
    @Autowired
    private ClientDomainService clientService;

    @Test
    public void test1() {
        Client c = new Client();
        c.setUsername("client1");
        c.setPassword("123456");
        c.setCreatedById(1L);
        c.setCompanyName("ammall");
        clientService.insert(c);
        c = clientService.getById(1L);
        c.setEmail("client1@jmalltech.com");
        clientService.update(c);
        System.out.println(clientService.getById(1L));
    }

    @Test
    public void test2(){

        System.out.println(clientService.remove(3L, null));
    }

    @Test
    public void test3(){
        Client c = clientService.getByUsernameAndPassword("client1", "123456");
        System.out.println(c);
    }
}
