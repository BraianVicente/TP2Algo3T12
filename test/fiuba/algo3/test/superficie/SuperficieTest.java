package fiuba.algo3.test.superficie;

import fiuba.algo3.modelo.unidades.Transformer;
import fiuba.algo3.modelo.unidades.Bumblebee;
import fiuba.algo3.modelo.unidades.Ratchet;
import org.junit.Assert;
import org.junit.Test;

import fiuba.algo3.modelo.tablero.Posicion;
import fiuba.algo3.modelo.tablero.Tablero;
import fiuba.algo3.modelo.tablero.superficies.*;
import fiuba.algo3.modelo.tablero.superficies.aerea.NebulosaAndromeda;
import fiuba.algo3.modelo.tablero.superficies.aerea.Nubes;
import fiuba.algo3.modelo.tablero.superficies.aerea.TormentaPsionica;
import fiuba.algo3.modelo.tablero.superficies.terrestre.*;

public class SuperficieTest {

	@Test
    public void test01HumanoideEnPantano(){
        Bumblebee bee=new Bumblebee();
        bee.transformar();
        Superficie sup=new Pantano();
        sup.afectarA(bee);
        Assert.assertTrue(bee.getCoeficienteMovimientoActual()==0f);
    }
	
	@Test
    public void test02VehiculoEnPantano(){
        Bumblebee bee=new Bumblebee();
        Superficie sup=new Pantano();
        sup.afectarA(bee);
        Assert.assertTrue(bee.getCoeficienteMovimientoActual()==0.5f);
    }
	
	@Test
    public void test03EspinasSaca5Porciento(){
        Bumblebee bee=new Bumblebee();
        Superficie sup=new Espinas();
        sup.afectarA(bee);
        Assert.assertTrue(bee.getVidaMaxima()-bee.getVidaMaxima()*5/100==bee.getVida());
    }
	
	@Test
    public void test04TormentaPsionciaSacaDanioNave(){
        Ratchet bee=new Ratchet();
        Superficie sup=new TormentaPsionica();
        float puntosAtaqueOriginal=bee.getPuntosAtaque();
        sup.afectarA(bee);
        Assert.assertTrue(bee.getPuntosAtaque()==0.4f*puntosAtaqueOriginal);
    }
	
	@Test
    public void test05TormentaPsionciaNoSacaDanioHumanoide(){
        Ratchet bee=new Ratchet();
        Superficie sup=new TormentaPsionica();
        bee.transformar();
        float puntosAtaqueOriginal=bee.getPuntosAtaque();
        bee.avanzarTurno();
        bee.transformar();
        sup.afectarA(bee);
        bee.avanzarTurno();
        bee.transformar();
        Assert.assertEquals(puntosAtaqueOriginal, (float) bee.getPuntosAtaque(), 0.5f);

    }
	
	@Test
    public void test06NubeNoHaceNada(){
        Ratchet bee=new Ratchet();
        Superficie sup=new Nubes();
        float puntosAtaqueOriginal=bee.getPuntosAtaque();
        sup.afectarA(bee);
        Assert.assertTrue(bee.getPuntosAtaque()==puntosAtaqueOriginal&&bee.getCoeficienteMovimientoActual()==1);
    }
	
	@Test
    public void test07RocosaNoHaceNada(){
        Transformer bee=new Bumblebee();
        Superficie sup=new Rocosa();
        float puntosAtaqueOriginal=bee.getPuntosAtaque();
        sup.afectarA(bee);
        Assert.assertTrue(bee.getPuntosAtaque()==puntosAtaqueOriginal&&bee.getCoeficienteMovimientoActual()==1);
    }
	
	@Test
    public void test08AndromedaNoTeDejaMoverPorTresTurnos(){
        Transformer bee=new Ratchet();
        Superficie sup=new NebulosaAndromeda();
        sup.afectarA(bee);
        Assert.assertTrue(bee.getCoeficienteMovimientoActual()==0);
        bee.avanzarTurno();
        Assert.assertTrue(bee.getCoeficienteMovimientoActual()==0);
        bee.avanzarTurno();
        Assert.assertTrue(bee.getCoeficienteMovimientoActual()==0);
        bee.avanzarTurno();
        Assert.assertFalse(bee.getCoeficienteMovimientoActual()==0);
    }
}
