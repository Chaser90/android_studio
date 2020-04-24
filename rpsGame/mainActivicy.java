package com.example.rpsgame;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.Locale;
import java.util.Queue;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ImageView handAnimImageView;
    ImageView setHandImageView;

    ImageButton scissorButton;
    ImageButton paperButton;
    ImageButton rockButton;
    ImageButton replayButton;

    AnimationDrawable animationDrawable;

    TextToSpeech textToSpeech;

    TextToSpeech.OnInitListener onInitListener = new TextToSpeech.OnInitListener() {
        @Override
        public void onInit(int i) {
            if(i != TextToSpeech.ERROR){
                textToSpeech.setLanguage(Locale.KOREAN);
                textToSpeech.setPitch(1.0f);
                textToSpeech.setSpeechRate(1.0f);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        handAnimImageView = findViewById(R.id.hand_anim_image_view);
        setHandImageView = findViewById(R.id.set_hand_image_view);

        animationDrawable = (AnimationDrawable) handAnimImageView.getDrawable();

        textToSpeech = new TextToSpeech(getApplicationContext(), onInitListener);

        scissorButton = findViewById(R.id.Scissor_Button);
        rockButton = findViewById(R.id.Rock_Button);
        paperButton = findViewById(R.id.Paper_Button);
        replayButton = findViewById(R.id.Replay_Button);
    }

    public void button_click(View view) {
        switch (view.getId()) {
            case R.id.Replay_Button:
                setHandImageView.setVisibility(View.GONE);
                handAnimImageView.setVisibility(View.VISIBLE);
                animationDrawable.start();
                voicePlay("가위바위보");

                replayButton.setEnabled(false);

                scissorButton.setEnabled(true);
                rockButton.setEnabled(true);
                paperButton.setEnabled(true);

                break;

            case R.id.Scissor_Button:
            case R.id.Rock_Button:
            case R.id.Paper_Button:
                replayButton.setEnabled(true);

                scissorButton.setEnabled(false);
                rockButton.setEnabled(false);
                paperButton.setEnabled(false);

                animationDrawable.stop();
                handAnimImageView.setVisibility(View.GONE);
                setHandImageView.setVisibility(View.VISIBLE);

                int userHand = Integer.parseInt(view.getTag().toString());
                int comHand = setComHand();

                winCheck(userHand, comHand);
                break;

            default:
                break;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        textToSpeech.shutdown();
    }

    public void voicePlay(String voiceText) {
        textToSpeech.speak(voiceText, TextToSpeech.QUEUE_FLUSH, null, null);
    }

    public int setComHand() {
        int getComHand = new Random().nextInt(3) + 1;
        switch (getComHand) {
            case 1:
                setHandImageView.setImageResource(R.drawable.scissors);
                break;
            case 2:
                setHandImageView.setImageResource(R.drawable.rock);
                break;
            case 3:
                setHandImageView.setImageResource(R.drawable.paper);
                break;
        }
        return getComHand;

    }
        public void winCheck (int userHand, int comHand) {
           int result = ( 3 + userHand - comHand ) % 3;
           switch (result){
               case 0: // draw
                   voicePlay("비겼어요. 다시 시작하세요");
                   break;
               case 1: //user win
                   voicePlay("당신이 이겼네요 축하 축하");
                   break;
               case 2: // com win
                   voicePlay("제가 이겼어요. 헤헤헤");
                   break;
        }
    }
}
