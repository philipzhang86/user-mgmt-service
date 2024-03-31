package com.jmalltech;

import com.jmalltech.entity.Client;
import com.jmalltech.service.ClientDomainService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ClientCRUDCacheTest {
    @Autowired
    private ClientDomainService service;

    @Test
    public void test1(){
        System.out.println(service.getClientListWithPassword());
    }

    @Test
    public void test2(){
        System.out.println(service.remove(null, "client2"));
    }

    @Test
    public void test3(){
        System.out.println(service.getClientListWithoutPassword());
    }

    @Test
    public void test4(){
        Client client = new Client();
        client.setUsername("client8");
        client.setPassword("test8");
        client.setEmail("client8@test.com");
        service.insert(client);
        System.out.println(service.getByUsername("client8"));
    }

    @Test
    public void test5(){
        Client c = service.getByUsername("client3");
        c.setCompanyName("newCompany");
        service.update(c);
        System.out.println(service.getByUsername("client3"));
    }


    @Test
    public void test6(){
        System.out.println(service.remove(null,"client8"));
    }

}
