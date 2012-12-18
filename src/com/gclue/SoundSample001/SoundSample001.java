package com.gclue.SoundSample001;

import java.io.IOException;

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
		// 描画クラスのインスタンスを生成
		MyView mView = new MyView( this );
		// 現在のViewに設定
		setContentView( mView );
	}
}

/**
 * 描画用のクラス
 */
class MyView extends View {
	/** サウンド再生データを保持する。 */
	private MediaPlayer mp;

	/**
	 * コンストラクタ。
	 * @param context コンテキスト
	 */
	public MyView( Context context ) {
		super( context );

		// イベントが取得できるようにFocusを有効にする
		setFocusable( true );

		// サウンドデータを読み込む（/res/raw/pon.mp3）
		mp = MediaPlayer.create( context, R.raw.pon );
	}

	/**
	 * 描画処理。
	 */
	@Override
	protected void onDraw( Canvas canvas ) {
		// 背景色を設定する
		canvas.drawColor( Color.BLUE );
	}

	/**
	 * タッチイベント。
	 */
	@Override
	public boolean onTouchEvent( MotionEvent event ) {
		if ( event.getAction() == MotionEvent.ACTION_DOWN ) {
			// ACTION_DOWNは指が触れた時
			// 音の再生開始位置を0ミリセカンドの位置に設定する
			mp.seekTo( 0 );
			// 音の再生を開始する
			mp.start();
		} else if ( event.getAction() == MotionEvent.ACTION_UP ) {
			// ACTION_UPは指が離れた時
			// 音を停止する
			mp.stop();

			// 一度再生をstop()してから再び音を再生する場合は、prepare()を呼び出す必要がある
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