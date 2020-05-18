package muskan.myappcompany.connect3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
//import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // 0 = yellow, 1 = red, 2 = empty
    int activePlayer;
    public void chooseCross(View view){
        activePlayer = 0;
        Button crossButton = (Button) findViewById(R.id.button2);
        Button circleButton = (Button) findViewById(R.id.button3);
        TextView choose = (TextView) findViewById(R.id.choose);
        crossButton.setVisibility(View.GONE);
        circleButton.setVisibility(View.GONE);
        choose.setVisibility(View.GONE);
    }
    public void chooseCircle(View view){
        activePlayer = 1;
        Button crossButton = (Button) findViewById(R.id.button2);
        Button circleButton = (Button) findViewById(R.id.button3);
        TextView choose = (TextView) findViewById(R.id.choose);
        crossButton.setVisibility(View.GONE);
        circleButton.setVisibility(View.GONE);
        choose.setVisibility(View.GONE);
    }


    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
    boolean gameActive = true;
    public void dropin (View view){

        ImageView counter = (ImageView) view;

        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        if(gameState[tappedCounter] == 2 && gameActive) {


            gameState[tappedCounter] = activePlayer;
            counter.setTranslationY(-100);

            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.whitecross);

                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.circlewhite);

                activePlayer = 0;
            }

            counter.animate().translationYBy(100).setDuration(300);
            for (int[] winningPosition : winningPositions) {

                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2) {
                    gameActive = false;

                    String winner = "";
                    if(activePlayer == 1) {
                        winner = "CROSS";
                    }else{
                        winner = "CIRCLE";
                    }
                    TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);
                    Button playAgainButton = (Button) findViewById(R.id.playAgainButton);
                    winnerTextView.setText(winner + " has won!");
                    playAgainButton.setVisibility(View.VISIBLE);
                    winnerTextView.setVisibility(View.VISIBLE);
                }

            }


        }



    }

    public void playAgain(View view){
        Button playAgainButton = (Button) findViewById(R.id.playAgainButton);

        TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);

        playAgainButton.setVisibility(View.GONE);

        winnerTextView.setVisibility(View.GONE);

        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);

        for(int i=0; i<gridLayout.getChildCount(); i++) {

            ImageView counter = (ImageView) gridLayout.getChildAt(i);

            counter.setImageDrawable(null);

        }

        for (int i=0; i<gameState.length; i++) {

            gameState[i] = 2;

        }

        activePlayer = 0;

        gameActive = true;
        Button crossButton = (Button) findViewById(R.id.button2);
        Button circleButton = (Button) findViewById(R.id.button3);
        TextView choose = (TextView) findViewById(R.id.choose);
        crossButton.setVisibility(View.VISIBLE);
        circleButton.setVisibility(View.VISIBLE);
        choose.setVisibility(View.VISIBLE);


    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
