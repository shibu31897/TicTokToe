package com.example.android.connect3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //yellow = 0 ; red =1; empty = 2

    int[] gameState = {2,2,2,2,2,2,2,2,2};
    int[][] winningPostions = {{0,1,2} , {3,4,5 }, {6,7,8} , {0,3,6}  , {1,4,7} , {2,5,8}, {0,4,8} , {2,4,6}};
    int activePlayer = 0;
    boolean gameActive =true ;

    public void dropIn(View view)
    {
        ImageView counter = (ImageView) view;

        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        if(gameState[tappedCounter]==2 && gameActive)
        {
        gameState[tappedCounter] = activePlayer;
        counter.setTranslationY(-2500);
        if(activePlayer == 0) {
            counter.setImageResource(R.drawable.yellow);
            activePlayer =1;
        }
        else
        {
            counter.setImageResource(R.drawable.red);
            activePlayer = 0;
        }
        counter.animate().translationYBy(2500).setDuration(300);
        for(int[] winningPostion : winningPostions )
        {
            if(gameState[winningPostion[0]] ==gameState[winningPostion[1]] && gameState[winningPostion[1]] == gameState[winningPostion[2]] && gameState[winningPostion[0]]!=2 )
            {   String won;
                gameActive = false;
                if(activePlayer == 0)
                {
                    won = "red";
                }else {
                    won = "yellow";
                }
                Toast.makeText(this, won+" has won !", Toast.LENGTH_SHORT).show();
                Button playAgainButton = (Button) findViewById(R.id.PlayAgainButton);
                TextView winner = (TextView) findViewById(R.id.WinnerTextView);
                winner.setText("Winner : "+won);
                playAgainButton.setVisibility(View.VISIBLE);
                winner.setVisibility(View.VISIBLE);

            }
        }
        }

    }
    public void playAgain(View view)
    {
        Button playAgainButton = (Button) findViewById(R.id.PlayAgainButton);
        TextView winner = (TextView) findViewById(R.id.WinnerTextView);
        playAgainButton.setVisibility(View.INVISIBLE);
        winner.setVisibility(View.INVISIBLE);
        GridLayout gridLayout = findViewById(R.id.gridLayout);
        for(int i= 0; i<gridLayout.getChildCount() ; i++)
        {
            ImageView counter = (ImageView) gridLayout.getChildAt(i);
            counter.setImageDrawable(null);
        }
        for (int i=0; i<gameState.length ; i++ )
        {
            gameState[i] = 2;

        }
       activePlayer = 0;
        gameActive =true ;

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
