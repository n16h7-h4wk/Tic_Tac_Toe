package com.developer.ankit.tictactoe;

import android.app.Activity;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private int player = 1;
    private int gameState[] = {2,2,2,2,2,2,2,2,2};
    private int[][] winPos = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    boolean gameIsActive = true ;

    public void dropIn(View view){
        ImageView counter = (ImageView) view ;
        int checkTap = Integer.parseInt(counter.getTag().toString());

        if(player ==1 && gameState[checkTap]==2&&gameIsActive) {
            counter.setTranslationY(-1000f);
            counter.setImageResource(R.drawable.cross);
            counter.animate().translationYBy(1000f).rotation(720).setDuration(500) ;
            gameState[checkTap] = 1 ;
            boolean ans = checkResult();
            if(ans){
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        recreate();
                    }
                },2000l);
            }else{
                checkMoves();
            }
            player = 0;
        }else if(player ==0 && gameState[checkTap]==2&&gameIsActive){
            counter.setTranslationY(-1000f);
            counter.setImageResource(R.drawable.zero);
            counter.animate().translationYBy(1000f).rotation(720).setDuration(500) ;
            gameState[checkTap] = 0 ;
            boolean ans = checkResult();
            if(ans){
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        recreate();
                    }
                },2000l);
            }else{
                checkMoves();
            }
            player = 1;
        }

    }
    private void checkMoves(){
        for(int i : gameState){
            if(i==2)
                return;
        }
        Toast.makeText(this, "Match DRAW!", Toast.LENGTH_SHORT).show();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                recreate();
            }
        },2000l);

    }
    private boolean checkResult(){
        for(int[] winPos : winPos){
            if(gameState[winPos[0]]==gameState[winPos[1]]&&gameState[winPos[1]]==gameState[winPos[2]]&&gameState[winPos[0]]!=2){
                gameIsActive = false ;
                if(gameState[winPos[0]]==1){
                    Toast.makeText(this, "Player CROSS WINS!", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this, "Player ZERO WINS!", Toast.LENGTH_SHORT).show();
                }
                return true ;
            }
        }
        return false ;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
