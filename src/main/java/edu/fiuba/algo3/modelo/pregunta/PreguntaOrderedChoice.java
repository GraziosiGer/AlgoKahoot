package edu.fiuba.algo3.modelo.pregunta;

import java.util.List;

public class PreguntaOrderedChoice extends Pregunta {

    public PreguntaOrderedChoice(String pregunta, List<String> opciones, List<String> opcionesCorrectas) {
        super(pregunta, opciones, opcionesCorrectas);
    }
}