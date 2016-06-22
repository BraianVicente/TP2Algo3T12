/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fiuba.algo3.vista.statsPane;

import fiuba.algo3.modelo.unidades.muerte.Death;
import fiuba.algo3.modelo.juego.Juego;
import fiuba.algo3.modelo.posicion.Posicion;
import fiuba.algo3.modelo.tablero.Tablero;
import fiuba.algo3.modelo.tablero.superficies.Superficie;
import fiuba.algo3.modelo.tablero.superficies.aerea.TormentaPsionica;
import fiuba.algo3.modelo.tablero.superficies.terrestre.Pantano;
import fiuba.algo3.modelo.unidades.Bumblebee;
import fiuba.algo3.modelo.unidades.Optimusprime;
import fiuba.algo3.modelo.unidades.Ratchet;
import fiuba.algo3.modelo.unidades.Superion;
import fiuba.algo3.modelo.unidades.Unidad;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author brahvic
 */
public class StatsPane {


    @FXML
    private TextField unidadNombre ;

    @FXML
    private ImageView unidadImagen ;

    @FXML
    private ProgressBar vidaUnidad ;

    @FXML
    private TextField ataqueUnidad ;

    @FXML
    
    private TextField tieneChispa ;
    
    @FXML
    private ImageView terrestreView ;

    @FXML
    private TextArea terrestreEfectos ;

    @FXML
    private ImageView aereoView ;

    @FXML
    private TextArea aereoEfectos ;

    private Tablero tablero;
    private Juego juego;
    
    @FXML
    void initialize() {
        assert unidadNombre != null : "fx:id=\"unidadNombre\" was not injected: check your FXML file 'statsPane.fxml'.";
        assert unidadImagen != null : "fx:id=\"unidadImagen\" was not injected: check your FXML file 'statsPane.fxml'.";
        assert vidaUnidad != null : "fx:id=\"vidaUnidad\" was not injected: check your FXML file 'statsPane.fxml'.";
        assert ataqueUnidad != null : "fx:id=\"ataqueUnidad\" was not injected: check your FXML file 'statsPane.fxml'.";
        assert terrestreView != null : "fx:id=\"terrestreView\" was not injected: check your FXML file 'statsPane.fxml'.";
        assert terrestreEfectos != null : "fx:id=\"terrestreEfectos\" was not injected: check your FXML file 'statsPane.fxml'.";
        assert aereoView != null : "fx:id=\"aereoView\" was not injected: check your FXML file 'statsPane.fxml'.";
		assert aereoEfectos != null : "fx:id=\"aereoEfectos\" was not injected: check your FXML file 'statsPane.fxml'.";

    }

    public void setUnidadSeleccionada(Unidad u){
        this.unidadImagen(u);
        this.vidaUnidad(u);
        this.unidadNombre(u);
        this.ataqueUnidad(u);
        this.tieneChispa(u);
    }
    
    public void setUnidadSeleccionada(Posicion p){
        Unidad u = this.tablero.obtenerUnidad(p);
        this.setUnidadSeleccionada(u);
    }
    
    public void setSuperficieTerrestre(Superficie s){
        this.terrestreView(s);
        this.terrestreEfectos(s);
        
    }
    
    public void setSuperficieAerea(Superficie s){
        this.aereoView(s);
        this.aereoEfectos(s);
    }
    
    public void setUpPreview(){

        Unidad u = new Superion(new Optimusprime(),new Ratchet(),new Bumblebee()) ;
        this.setUnidadSeleccionada(u);
        Superficie t = new Pantano();
        this.setSuperficieTerrestre(t);
        Superficie a = new TormentaPsionica();
        this.setSuperficieAerea(a);
        
	}

    private void unidadNombre(Unidad unidad) {
        this.unidadNombre.setText(unidad.nombre());
    }

    private void unidadImagen(Unidad u) {
        this.unidadImagen.setImage(new Image(u.nombreImagen()));
    }

    private void vidaUnidad(Unidad u) {
    	double vida=u.getVida();
    	double vidaMaxima=u.getVidaMaxima();
        this.vidaUnidad.setProgress(vida/vidaMaxima);

    }

    private void ataqueUnidad(Unidad u) {
        Integer ataq = u.getPuntosAtaque();
        this.ataqueUnidad.setText(ataq.toString());
            
    }

    private void terrestreView(Superficie s) {
        this.terrestreView.setImage(new Image(s.nombreImagen()));
    }

    private void terrestreEfectos(Superficie s) {
        this.terrestreEfectos.setText(s.efecto());
    }

    private void aereoView(Superficie s) {
        this.aereoView.setImage(new Image(s.nombreImagen()));
    }

    private void aereoEfectos(Superficie s) {
        this.aereoEfectos.setText(s.efecto());
    }

    
    private void tieneChispa(Unidad u) {
       
        this.tieneChispa.setText("No");
        if(u.tieneChispa()){
            this.tieneChispa.setText("Si");
        }
        
    }
    
}
