package com.mycompany.unicafe;


import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author olgaviho
 */
public class KassapaateTest {
    
    Kassapaate kassa;
    Maksukortti kortti;

    @Before
    public void setUp() {
        kassa = new Kassapaate();
        kortti = new Maksukortti(500);
    }
    
    
    @Test
    public void kassassaOnRahaaOikeaMaara() {
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void edullisiaLounaitaOnMyytyNolla() {
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void maukkaitaLounaitaOnMyytyNolla() {
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void edullisenLounaanOstoKerryttaaMyytyjaLounaita() {
        kassa.syoEdullisesti(300);
        assertEquals(1, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void maukkaanLounaanOstoKerryttaaMyytyjaLounaita() {
        kassa.syoMaukkaasti(400);
        assertEquals(1, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void edullisenLounaanOstoLisaaKassanRahoja() {
        kassa.syoEdullisesti(300);
        assertEquals(100240, kassa.kassassaRahaa());
    }
    
    @Test
    public void maukkaanLounaanOstoLisaaKassanRahoja() {
        kassa.syoMaukkaasti(400);
        assertEquals(100400, kassa.kassassaRahaa());
    }
    
    @Test
    public void maukkaanLounaanOstoAntaaVaihtorahaa() {
        
        assertEquals(50, kassa.syoMaukkaasti(450));
    }
    @Test
    public void edullisenLounaanOstoAntaaVaihtorahaa() {
        
        assertEquals(10, kassa.syoEdullisesti(250));
    }
    
    @Test
    public void maukkaanLounaanEpaonnistunutOstoEiKerryttaMyytyjaLounaita() {
        kassa.syoMaukkaasti(100);
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void edullisenLounaanEpaonnistunutOstoEiKerryttaMyytyjaLounaita() {
        kassa.syoEdullisesti(100);
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void edullisenLounaanEpaonnistunutOstoEiLisaaKassanRahoja() {
        kassa.syoEdullisesti(100);
        assertEquals(100000, kassa.kassassaRahaa());
    }
    @Test
    public void maukkaanLounaanEpaonnistunutOstoEiLisaaKassanRahoja() {
        kassa.syoMaukkaasti(100);
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void edullisenLounaanEpaonnistunutOstoAntaaVaihtorahaa() {
        
        assertEquals(100, kassa.syoEdullisesti(100));
    }
    
    @Test
    public void maukkaanLounaanEpaonnistunutOstoAntaaVaihtorahaa() {
        
        assertEquals(100, kassa.syoMaukkaasti(100));
    }
    
    @Test
    public void edullisenLounaanOstoKortillaKerryttaaMyytyjaLounaita() {
        kassa.syoEdullisesti(kortti);
        assertEquals(1, kassa.edullisiaLounaitaMyyty());
        
        
    }
    @Test
    public void maukkaanLounaanOstoKortillaKerryttaaMyytyjaLounaita() {
        kassa.syoMaukkaasti(kortti);
        assertEquals(1, kassa.maukkaitaLounaitaMyyty());       
        
    }
    
    @Test
    public void edullisenLounaanOstoKortillaVahentaaKortinRahaa() {
        kassa.syoEdullisesti(kortti);
        assertEquals("saldo: 2.60", kortti.toString());
    }
    
    @Test
    public void maukkaanLounaanOstoKortillaVahentaaKortinRahaa() {
        kassa.syoMaukkaasti(kortti);
        assertEquals("saldo: 1.0", kortti.toString());
    }
    
    @Test
    public void MaukkaanLounaanOstoKortillaPalauttaaTrue() {
        assertTrue(kassa.syoMaukkaasti(kortti)==true);
    }
    
    @Test
    public void EdullisenLounaanOstoKortillaPalauttaaTrue() {
        assertTrue(kassa.syoEdullisesti(kortti)==true);
    }
    
    @Test
    public void edullisenLounaanEpaonnistunutOstoKortillaEiVahennaKortinRahaa() {
        kassa.syoEdullisesti(kortti);
        kassa.syoEdullisesti(kortti);
        kassa.syoEdullisesti(kortti);
        assertEquals("saldo: 0.20", kortti.toString());
    }
    @Test
    public void maukkaanLounaanEpaonnistunutOstoKortillaEiVahennaKortinRahaa() {
        kassa.syoMaukkaasti(kortti);
        kassa.syoMaukkaasti(kortti);
        assertEquals("saldo: 1.0", kortti.toString());
    }
    @Test
    public void maukkaanLounaanEpaonnistunutOstoKortillaEiKerrytaMyytyjaLounaita() {
        kassa.syoMaukkaasti(kortti);
        kassa.syoMaukkaasti(kortti);
        assertEquals(1, kassa.maukkaitaLounaitaMyyty());
    }
    @Test
    public void edullisenLounaanEpaonnistunutOstoKortillaEiKerrytaMyytyjaLounaita() {
        kassa.syoEdullisesti(kortti);
        kassa.syoEdullisesti(kortti);
        kassa.syoEdullisesti(kortti);
        assertEquals(2, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void epaonnistuneenEdullisenLounaanOstoKortillaPalauttaaFalse() {
        kassa.syoEdullisesti(kortti);
        kassa.syoEdullisesti(kortti);
        assertTrue(kassa.syoEdullisesti(kortti)==false);
    }
    
    @Test
    public void epaonnistuneenMaukkaanLounaanOstoKortillaPalauttaaFalse() {
        kassa.syoMaukkaasti(kortti);
        assertTrue(kassa.syoMaukkaasti(kortti)==false);
    }
    
    @Test
    public void edullisenLounaanEpaonnistunutOstoKortillaEiLisaaKassanRahoja() {
        kassa.syoEdullisesti(kortti);
        assertEquals(100000, kassa.kassassaRahaa());
    }
    @Test
    public void maukkaanLounaanEpaonnistunutOstoKortillaEiLisaaKassanRahoja() {
        kassa.syoMaukkaasti(kortti);
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void lataaRahaaKortillaKasvattaaKassanRahamaaraa() {
        kassa.lataaRahaaKortille(kortti, 500);
        assertEquals(100500,kassa.kassassaRahaa());
    }
    
    @Test
    public void lataaRahaaKortillaKasvattaaKortinRahamaaraa() {
        kassa.lataaRahaaKortille(kortti, 500);
        assertEquals("saldo: 10.0",kortti.toString());
    }
    
    @Test
    public void epaonnistunutlataaRahaaKortilleEiTeeMitaan() {
        kassa.lataaRahaaKortille(kortti, -500);
        assertEquals("saldo: 5.0",kortti.toString());
    }
            
    
    
}
