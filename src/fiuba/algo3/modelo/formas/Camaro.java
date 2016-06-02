package fiuba.algo3.modelo.formas;

public class Camaro extends FormaTerrestre {

    private Integer distanciaMovimiento ;

    public Camaro(){
        this.distanciaMovimiento = 5 ;
    }
    @Override
    public Forma getAlternativa() {
        return new HumanoideBumblebee();
    }

    @Override
    public int getDistanciaAtaque() {
        return 3;
    }

    @Override
    public int getPuntosAtaque() {
        return 20;
    }

    @Override
    public int getDistanciaMovimiento() {
        return this.distanciaMovimiento;
    }

    @Override
    public boolean esHumanoide() {
        return false;
    }

    @Override
    public boolean esVehiculo() {
        return true;
    }

    @Override
    public Integer disminuirEnUnMovimiento() {
        return (5/getDistanciaMovimiento()) ;
    }

    public void movimientoNormal(){
        this.distanciaMovimiento = 5;
    }

    @Override
    public void movimientoPenalizado() {
        this.distanciaMovimiento = (5/2) ;
    }


}
