package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void lataaRahaaOnnistuu() {
        kortti.lataaRahaa(10);
        assertEquals("saldo: 0.20", kortti.toString());
        
    }
    @Test
    public void lataaRahaaOnnistuuNegatiivisesti() {
        kortti.lataaRahaa(-5);
        assertEquals("saldo: 0.5", kortti.toString());
        
    }
    
    @Test
    public void toStringToimii() {
        assertEquals("saldo: 0.10", kortti.toString());
        
    }
    
    @Test
    public void saldoToimii() {
        assertEquals(10, kortti.saldo());
        
    }
    
    @Test
    public void OtaRahaaToimii() {
        kortti.otaRahaa(5);
        assertEquals("saldo: 0.5", kortti.toString());
        
    }
    
    @Test
    public void OtarahaaEiToimiYliSaldon() {
        kortti.otaRahaa(15);
        assertEquals("saldo: 0.10", kortti.toString());
        
    }
    
}
