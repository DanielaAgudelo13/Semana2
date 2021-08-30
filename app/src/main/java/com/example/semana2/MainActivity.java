package com.example.semana2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView preguntaText;
    private EditText respuestaUsuario;
    private TextView contadorText;
    private TextView puntajeText;
    private Button responder;
    private pregunta preguntaActual;
    private int tiempoRestante;
    private int puntaje;
    private int tiempoPulsado;
    private boolean isPressing;
    private Button intentDeNuevo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preguntaText = findViewById(R.id.preguntaText);
        respuestaUsuario = findViewById(R.id.respuestaUsuario);
        contadorText = findViewById(R.id.contadorText);
        puntajeText = findViewById(R.id.puntajeText);
        responder = findViewById(R.id.responder);
        intentDeNuevo = findViewById(R.id.intentDeNuevo);


        puntaje = 0;
        puntajeText.setText("puntaje: " + puntaje);

        tiempoRestante = 30;
        contadorText.setText(" " + tiempoRestante);
        new Thread(
                () -> {
                    while (tiempoRestante > 0) {
                        try {
                            tiempoRestante--;
                            runOnUiThread(
                                    () -> {
                                        contadorText.setText(" " + tiempoRestante);
                                    }
                            );

                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            Log.e("ERROR", e.toString());
                        }
                    }
                }
        ).start();

        generarNuevaPregunta();

        responder.setOnClickListener(
                (view) -> {
                    verificarRespuesta();
            }
        );


        //skip pregunta

        preguntaText.setOnTouchListener(
                (view, event) -> {
                    switch (event.getAction()){
                        case MotionEvent.ACTION_DOWN:
                            if(tiempoPulsado>1.5){
                                generarNuevaPregunta();
                            }
                    }
                    return true;
                }
        );

    }



    private void verificarRespuesta(){

            String respuestaTexto = respuestaUsuario.getText().toString();
            int respuestaInt = (int) Integer.parseInt(respuestaTexto);
            int correcta = preguntaActual.getRespuesta();

            if (respuestaInt == preguntaActual.getRespuesta()) {
                Toast.makeText(this, "Correcto", Toast.LENGTH_SHORT).show();
                puntaje = puntaje+5;
                puntajeText.setText("Puntaje: " + puntaje);
            } else {
                puntaje = puntaje-4;
                Toast.makeText(this, "Mal", Toast.LENGTH_SHORT).show();
            }

        puntajeText.setText(" "+puntaje);
        generarNuevaPregunta();

        }

    public void generarNuevaPregunta() {
        preguntaActual = new pregunta();
        preguntaText.setText(preguntaActual.getPregunta());
        tiempoRestante = 30;
        tiempoPulsado = 0;
        contadorText.setText(""+tiempoRestante);


    }
}