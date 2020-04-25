package com.example.springboot.common.spi;

import sun.misc.Service;

import java.util.Iterator;
import java.util.ServiceLoader;

public class TestSpi {
    public static void main(String[] args) {    
        Iterator<SpiService> providers = Service.providers(SpiService.class);
        ServiceLoader<SpiService> load = ServiceLoader.load(SpiService.class);

        while(providers.hasNext()) {
            SpiService ser = providers.next();
            ser.print();
        }
        System.out.println("--------------------------------");
        Iterator<SpiService> iterator = load.iterator();
        while(iterator.hasNext()) {
            SpiService ser = iterator.next();
            ser.print();
        }
    }
}