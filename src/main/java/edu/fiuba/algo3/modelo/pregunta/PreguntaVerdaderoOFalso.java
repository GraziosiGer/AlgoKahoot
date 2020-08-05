package edu.fiuba.algo3.modelo.pregunta;

import edu.fiuba.algo3.modelo.Opcion;
import edu.fiuba.algo3.modelo.Respuesta;
import edu.fiuba.algo3.modelo.RespuestaVerdaderoOFalso;

import java.util.List;

public class PreguntaVerdaderoOFalso implements Pregunta {

    public final String pregunta;
    public final List<Opcion> opcionesIncorrectas;
    public final List<Opcion> opcionesCorrectas;
    public RespuestaVerdaderoOFalso respuesta;

    public PreguntaVerdaderoOFalso (String pregunta, List<Opcion> opcionesCorrectas, List<Opcion> opcionesIncorrectas) {
        this.pregunta = pregunta;
        this.opcionesIncorrectas = opcionesIncorrectas;
        this.opcionesCorrectas = opcionesCorrectas;
    }

    public String obtenerPregunta() {
        return pregunta;
    }

    public List<Opcion> obtenerOpcionesCorrectas() {
        return opcionesCorrectas;
    }

    public List<Opcion> obtenerOpcionesIncorrectas() {
        return opcionesIncorrectas;
    }

    public int obtenerPuntaje(Respuesta respuesta) {
        return respuesta.obtenerPuntaje(this.opcionesCorrectas);
    }

    public RespuestaVerdaderoOFalso armarRespuesta (Opcion respuesta) {
        return new RespuestaVerdaderoOFalso(respuesta);
    }
}