package com.example.soundsample001;

import java.io.IOException;

import com.gclue.SoundSample001.R;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

public class SoundSample001 extends Activity {

	@Override
	public void onCreate( Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState );
		MyView mView = new MyView( this );
		setContentView( mView );
	}
}

class MyView extends View {
	private MediaPlayer mp;

	public MyView( Context context ) {
		super( context );
		setFocusable( true );
		mp = MediaPlayer.create( context, R.raw.pon );
	}

	@Override
	protected void onDraw( Canvas canvas ) {
		canvas.drawColor( Color.BLUE );
	}

	@Override
	public boolean onTouchEvent( MotionEvent event ) {
		if ( event.getAction() == MotionEvent.ACTION_DOWN ) {
			mp.seekTo( 0 );
			mp.start();
		} else if ( event.getAction() == MotionEvent.ACTION_UP ) {
			mp.stop();
			try {
				mp.prepare();
			} catch ( IllegalStateException e ) {
				e.printStackTrace();
			} catch ( IOException e ) {
				e.printStackTrace();
			}
		}
		return true;
	}
}