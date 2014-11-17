package com.example.servicioaudio;

import java.io.IOException;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;


public class MainActivity extends Activity {

	TextView Tv1,Tv2;
	EditText Edt1,Edt2;
	ImageButton Ibtn1,Ibtn2,Ibtn3;
	
	
	private static final String LOG_TAG = "Grabadora";          
	private MediaRecorder mediaRecorder;
	private MediaPlayer mediaPlayer;
	private static String fichero = Environment.getExternalStorageDirectory().getAbsolutePath()+"/audio.3gp";
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Tv1= (TextView) findViewById(R.id.txvtitulo);
        Tv2= (TextView) findViewById(R.id.txvcategoria);
        Edt1=(EditText) findViewById(R.id.edxtitulo);
        Edt2=(EditText) findViewById(R.id.edxcategoria);
        Ibtn1=(ImageButton) findViewById(R.id.Grabar);
        Ibtn2=(ImageButton) findViewById(R.id.Play);
        Ibtn3=(ImageButton) findViewById(R.id.Pausar);
        
        
        	
        //GRABAR
        
        Ibtn1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				mediaRecorder= new MediaRecorder();
				mediaRecorder.setOutputFile(fichero);
				mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
				mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
				mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
				
				try{
					mediaRecorder.prepare();
					
				}catch(IOException e){
					Log.e(LOG_TAG,"Fallo en grabación");
					
				}
				mediaRecorder.start();
				
			}
		});
        
  //REPRODUCIR
        Ibtn2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				mediaPlayer = new MediaPlayer();
				try{
					mediaPlayer.setDataSource(fichero);
					mediaPlayer.prepare();
					mediaPlayer.start();
				}catch(IOException e){
					Log.e(LOG_TAG, "Fallo en la reproducion");
					
				}
				
			}
		});
        
   //DETENER
        Ibtn3.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mediaRecorder.stop();
				mediaRecorder.release();
				
				
			}
		});
        
        
        
        
    }


    
  
}
