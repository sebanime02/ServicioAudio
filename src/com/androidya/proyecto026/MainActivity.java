package com.androidya.proyecto026;

import java.io.File;
import java.io.IOException;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.View;


import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends Activity implements OnCompletionListener {
	TextView tv1,tv2,tv3;
	MediaRecorder recorder;
	MediaPlayer player;
	File archivo;
	File archivo2;
	ImageButton b1, b2, b3;
	Button btnguardar;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		tv1 = (TextView) this.findViewById(R.id.textView1);
		tv2= (TextView) findViewById(R.id.edtitulo);
		tv3 = (TextView) findViewById(R.id.edtcategoria);
		b1 = (ImageButton) findViewById(R.id.button1);
		b2 = (ImageButton) findViewById(R.id.button2);
		b3 = (ImageButton) findViewById(R.id.button3);
		btnguardar = (Button) findViewById(R.id.Guardar);
	}

	
	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	

	public void grabar(View v) {
		
		String ruta = (String) getText(R.id.tvtitulo);
		
		
		recorder = new MediaRecorder();
		recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
		recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
		recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
		File path = new File(Environment.getExternalStorageDirectory()
				.getPath());
		try {
			archivo = File.createTempFile(ruta, ".3gp", path);
		} catch (IOException e) {
		}
		recorder.setOutputFile(archivo.getAbsolutePath());
		try {
			recorder.prepare();
		} catch (IOException e) {
		}
		recorder.start();
		tv1.setText("Grabando");
		b1.setEnabled(false);
		b2.setEnabled(true);
	}

	public void detener(View v) {
		recorder.stop();
		recorder.release();
		player = new MediaPlayer();
		player.setOnCompletionListener(this);
		try {
			player.setDataSource(archivo.getAbsolutePath());
		} catch (IOException e) {
		}
		try {
			player.prepare();
		} catch (IOException e) {
		}
		b1.setEnabled(true);
		b2.setEnabled(false);
		b3.setEnabled(true);
		tv1.setText("Listo para reproducir");
	}

	public void reproducir(View v) {
		player.start();
		b1.setEnabled(false);
		b2.setEnabled(false);
		b3.setEnabled(false);
		tv1.setText("Reproduciendo");
	}

	public void onCompletion(MediaPlayer mp) {
		b1.setEnabled(true);
		b2.setEnabled(true);
		b3.setEnabled(true);
		tv1.setText("Listo");
	}
	
	
	public void Grabar(View v){
		
		
		
	}
	
	
}
