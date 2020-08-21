package edu.fiuba.algo3.vista.preguntas;

import edu.fiuba.algo3.controlador.SonidoHandler;
import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Opcion;
import edu.fiuba.algo3.modelo.preguntas.Pregunta;
import edu.fiuba.algo3.vista.DiseñadorDeVistas;
import edu.fiuba.algo3.vista.LabelTiempo;
import edu.fiuba.algo3.vista.Observador;
import edu.fiuba.algo3.vista.VistaAbstracta;
import edu.fiuba.algo3.vista.botones.BotonEnviarRespuesta;
import edu.fiuba.algo3.vista.botones.BotonExclusividad;
import edu.fiuba.algo3.vista.opciones.VistaOpcionesOrderedChoice;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class VistaOrderedChoice extends VistaAbstracta implements Observador {

    List<Opcion> respuesta = new LinkedList<>();
    private final List<Opcion> opciones = new ArrayList<>();
    private final Pregunta pregunta;
    private VistaOpcionesOrderedChoice vistaOpciones;
    private final int tiempoDisponible = 30;
    private final BotonExclusividad botonExclusividad;
    private final ImageView imagenVista;

    public VistaOrderedChoice(Pregunta pregunta, Stage stage, ImageView imagenVista, SonidoHandler sonido, Juego juego) {
        super(stage, imagenVista, sonido, juego);
        this.juego = juego;
        this.pregunta = pregunta;
        this.stage = stage;
        this.jugadores = new LinkedList<>(juego.obtenerJugadores());
        this.botonExclusividad = new BotonExclusividad(juego);
        this.labelTiempo = new LabelTiempo(tiempoDisponible, juego);
        this.imagenVista = imagenVista;
    }

    public void armarVistaPropia() {
        juego.guardarObservador(this);
        Label tipoPregunta =  new Label("Pregunta Ordered Choice");
        Label labelPregunta = new Label(this.pregunta.getPregunta());
        this.opciones.addAll(this.pregunta.obtenerOpcionesCorrectas());
        Collections.shuffle(this.opciones);
        BotonEnviarRespuesta botonEnviar = new BotonEnviarRespuesta(this.respuesta, this.juego);
        botonEnviar.setTranslateY(20);
        botonEnviar.setTranslateX(150);
        vistaOpciones = new VistaOpcionesOrderedChoice(this.opciones, this.respuesta, botonEnviar);
        Image imagen = new Image("file:src/resources/imagen5.jpg", 512, 250, true, false);
        imagenVista.setImage(imagen);
        VBox vBox = new VBox(vistaOpciones, botonExclusividad, botonEnviar);
        vBox.setSpacing(100);
        vBox.setTranslateX(-150);
        vBox.setTranslateY(270);
        this.getChildren().addAll(imagenVista, labelTiempo, infoJugador, tipoPregunta, labelPregunta, vBox);
        DiseñadorDeVistas diseñadorDeVistas = new DiseñadorDeVistas();
        diseñadorDeVistas.diseñarVistaGroupChoice(tipoPregunta, labelPregunta, labelTiempo, infoJugador, botonEnviar);
        update();
    }

    @Override
    protected void updatePropio(Jugador jugadorActual){
        vistaOpciones.update();
        botonExclusividad.actualizar(jugadorActual);
    }
}