package com.example.aditya.gameconnect;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    int[] gamestate={2,2,2,2,2,2,2,2,2};//0:yellow,1:red,2:empty
    int[][] winningPositions={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    int activePlayer=0;
    boolean gameActive=true;
    public void dropIn(View view) {
        ImageView counter = (ImageView) view;
        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        Log.i("tag",counter.getTag().toString());
        if (gamestate[tappedCounter] == 2&& gameActive==true) {
            gamestate[tappedCounter] = activePlayer;
            counter.setTranslationY(-1500);
            if (activePlayer == 0) {
                activePlayer = 1;
                counter.setImageResource(R.drawable.yellow);
            } else {
                activePlayer = 0;
                counter.setImageResource(R.drawable.red);
            }
            counter.animate().translationYBy(1500).rotation(1500).setDuration(300);
            boolean won=false;
           for (int[] winningPosition : winningPositions) {
                if (gamestate[winningPosition[0]] == gamestate[winningPosition[1]] && gamestate[winningPosition[1]] == gamestate[winningPosition[2]] && gamestate[winningPosition[0]]!=2) {
                    String winner = "";
                    gameActive=false;
                    won=true;
                    if (activePlayer == 1) {
                        winner = "Yellow";
                    } else {
                        winner = "Red";
                    }
                    Button playAgainbutton=(Button)findViewById(R.id.playagain);
                    TextView winnertext=(TextView) findViewById(R.id.winnertext);
                    winnertext.setText(winner+" has won!!!");
                    playAgainbutton.setVisibility(View.VISIBLE);
                    winnertext.setVisibility(View.VISIBLE);
                }
            }
            int cnt=0;
            for(int i=0;i<gamestate.length;i++){
               if(gamestate[i]!=2)
                   cnt++;
            }
            if(cnt==gamestate.length&&won==false){
                gameActive=false;
                Button playAgainbutton=(Button)findViewById(R.id.playagain);
                TextView winnertext=(TextView) findViewById(R.id.winnertext);
                winnertext.setText("It is a tie!!!");
                playAgainbutton.setVisibility(View.VISIBLE);
                winnertext.setVisibility(View.VISIBLE);
            }
        }
    }
     public void playAgain(View view){
         Button playAgainbutton=(Button)findViewById(R.id.playagain);
         TextView winnertext=(TextView) findViewById(R.id.winnertext);
         playAgainbutton.setVisibility(View.INVISIBLE);
         winnertext.setVisibility(View.INVISIBLE);
         GridLayout gridLayout=(GridLayout) findViewById(R.id.gridLayout);
         for(int i=0;i<gridLayout.getChildCount();i++){
             ImageView counter=(ImageView) gridLayout.getChildAt(i);
             counter.setImageDrawable(null);

         }
         for(int i=0;i<gamestate.length;i++){
             gamestate[i]=2;
         }

         activePlayer=0;
         gameActive=true;
     }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
