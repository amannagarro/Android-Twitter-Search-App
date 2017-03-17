package com.firstapp.amy.notify;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.GestureDetector;
import android.support.v4.view.GestureDetectorCompat;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements GestureDetector.OnDoubleTapListener,
  GestureDetector.OnGestureListener{

    TextToSpeech tts;
    protected static final int SPEECH=100;
    private static final String DEBUG_TAG = "Gestures";
    private GestureDetectorCompat mDetector;

   private static Button b;

    //speach recognizer for callbacks
    private SpeechRecognizer mSpeechRecognizer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.mDetector = new GestureDetectorCompat(this,this);
        mDetector.setOnDoubleTapListener(this);
        tts = new TextToSpeech(MainActivity.this, new TextToSpeech.OnInitListener() {

            @Override
            public void onInit(int status) {
                tts.setLanguage(Locale.US);
               tts.speak("Welcome to Notify",TextToSpeech.QUEUE_ADD,null);
                tts.speak("Tap to get started",TextToSpeech.QUEUE_ADD,null);
            }
        });
        b = (Button)findViewById(R.id.buttonTap);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d(DEBUG_TAG, "speechtest " + "speechtest");
                //tts.speak("Set your Destination",TextToSpeech.QUEUE_ADD,null);

                Intent in = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                in.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,"en-us");
                in.putExtra(RecognizerIntent.EXTRA_PROMPT , "Speak");
                try{
                    startActivityForResult(in,200);
                }
                catch (ActivityNotFoundException e)
                {
                    Log.d(DEBUG_TAG, "test speech" + "speechtest");
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode)
        {
            case 200: if( data!=null && resultCode == RESULT_OK)
            {
                Log.d(DEBUG_TAG, "test speech" + "xxx");
                ArrayList<String> op = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                Log.d(DEBUG_TAG, "op1" + op.get(0));
                //Log.d(DEBUG_TAG, "op2" + op.get(1));
            }
                break;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        this.mDetector.onTouchEvent(event);
        // Be sure to call the superclass implementation
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent event) {
        Log.d(DEBUG_TAG,"onDown: " + event.toString());
        return true;
    }

    @Override
    public boolean onFling(MotionEvent event1, MotionEvent event2,
                           float velocityX, float velocityY) {
        Log.d(DEBUG_TAG, "onFling: " + event1.toString()+event2.toString());
        return true;
    }

    @Override
    public void onLongPress(MotionEvent event) {
        Log.d(DEBUG_TAG, "onLongPress: " + event.toString());
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
                            float distanceY) {
        Log.d(DEBUG_TAG, "onScroll: " + e1.toString()+e2.toString());
        return true;
    }

    @Override
    public void onShowPress(MotionEvent event) {
        Log.d(DEBUG_TAG, "onShowPress: " + event.toString());
    }

    @Override
    public boolean onSingleTapUp(MotionEvent event) {
        Log.d(DEBUG_TAG, "onDoubleTap: " + event.toString());
        return true;
    }



    @Override
    public boolean onDoubleTap(MotionEvent event) {
        Log.d(DEBUG_TAG, "onDoubleTap: " + event.toString());
        return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent event) {
        Log.d(DEBUG_TAG, "onDoubleTapEvent: " + event.toString());
        return true;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent event) {
        Log.d(DEBUG_TAG, "onSingleTapConfirmed: " + event.toString());
        return true;
    }

}
