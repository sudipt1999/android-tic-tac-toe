package com.example.tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    enum Player {
        ONE, TWO, NONE;
    }

    private Player currentPlayer = Player.ONE;
    private Player[] moves = new Player[9];
    private ImageView[] imgStore = new ImageView[9];
    private int chancesCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(getApplicationContext(),"Player One turns",Toast.LENGTH_LONG).show();
    }


    private Player isSomeoneWin () {
        int countOne = 0;
        int countTwo = 0;

        // check row 1
        for(int i = 0 ; i<3; i++){
            if(moves[i] == Player.ONE)
                countOne++;
            if(moves[i] == Player.TWO)
                countTwo++;
        }
        if(countOne == 3)
            return Player.ONE;
        if(countTwo == 3)
            return Player.TWO;



        // check row 2
        countOne = 0;
        countTwo = 0;
        for(int i = 3 ; i<6; i++){
            if(moves[i] == Player.ONE)
                countOne++;
            if(moves[i] == Player.TWO)
                countTwo++;
        }
        if(countOne == 3)
            return Player.ONE;
        if(countTwo == 3)
            return Player.TWO;

        // check row 3
        countOne = 0;
        countTwo = 0;
        for(int i = 6 ; i<9; i++){
            if(moves[i] == Player.ONE)
                countOne++;
            if(moves[i] == Player.TWO)
                countTwo++;
        }
        if(countOne == 3)
            return Player.ONE;
        if(countTwo == 3)
            return Player.TWO;

        // check col 1
        countOne = 0;
        countTwo = 0;
        for(int i = 0 ; i<7; i=i+3){
            if(moves[i] == Player.ONE)
                countOne++;
            if(moves[i] == Player.TWO)
                countTwo++;
        }
        if(countOne == 3)
            return Player.ONE;
        if(countTwo == 3)
            return Player.TWO;

        // check col 2
        countOne = 0;
        countTwo = 0;
        for(int i = 1 ; i<8; i=i+3){
            if(moves[i] == Player.ONE)
                countOne++;
            if(moves[i] == Player.TWO)
                countTwo++;
        }
        if(countOne == 3)
            return Player.ONE;
        if(countTwo == 3)
            return Player.TWO;

        // check col 3
        countOne = 0;
        countTwo = 0;
        for(int i = 2 ; i<9; i=i+3){
            if(moves[i] == Player.ONE)
                countOne++;
            if(moves[i] == Player.TWO)
                countTwo++;
        }
        if(countOne == 3)
            return Player.ONE;
        if(countTwo == 3)
            return Player.TWO;

        // check dig 1
        countOne = 0;
        countTwo = 0;
        for(int i = 0 ; i<9; i=i+4){
            if(moves[i] == Player.ONE)
                countOne++;
            if(moves[i] == Player.TWO)
                countTwo++;
        }
        if(countOne == 3)
            return Player.ONE;
        if(countTwo == 3)
            return Player.TWO;


        // check dig 2
        countOne = 0;
        countTwo = 0;
        for(int i = 2 ; i<7; i=i+2){
            if(moves[i] == Player.ONE)
                countOne++;
            if(moves[i] == Player.TWO)
                countTwo++;
        }
        if(countOne == 3)
            return Player.ONE;
        if(countTwo == 3)
            return Player.TWO;

        return Player.NONE;
    }


    public void movePlayer (View img) {
        Log.i("POS", "CLICKED AT : "+img.getTag().toString());
        int posClicked = Integer.parseInt(img.getTag().toString());
        if(moves[posClicked]== Player.ONE || moves[posClicked]== Player.TWO ){
            Toast.makeText(getApplicationContext(),"Already placed there! Move again !",Toast.LENGTH_SHORT).show();
            return;
        }

        // added the pos to array
        if(currentPlayer == Player.ONE){
            moves[posClicked] = Player.ONE;
            imgStore[posClicked] = (ImageView) img;
            currentPlayer = Player.TWO;
            ImageView im = (ImageView) img;
            im.setImageResource(R.drawable.one);
            im.animate().rotation((chancesCount+1)*3600f).alpha(1).setDuration(1000);
            Toast.makeText(getApplicationContext(),"Player Two turns",Toast.LENGTH_SHORT).show();
        }else {
            moves[posClicked] = Player.TWO;
            imgStore[posClicked] = (ImageView) img;
            currentPlayer = Player.ONE;
            ImageView im = (ImageView) img;
            im.setImageResource(R.drawable.two);
            im.animate().rotation( (chancesCount+1)*3600f).alpha(1).setDuration(1000);
            Toast.makeText(getApplicationContext(),"Player One turns",Toast.LENGTH_SHORT).show();
        }

        chancesCount++;
        // find if someone won and display winner !!!!
        Player winner = isSomeoneWin();
        if(winner == Player.NONE && chancesCount < 9){
            return;
        }

        if(winner == Player.ONE) {
            Toast toast= Toast.makeText(getApplicationContext(),
                    "Player ONE Won!!", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 50);
            toast.show();
        }

        if(winner == Player.TWO) {
            Toast toast= Toast.makeText(getApplicationContext(),
                    "Player TWO Won!!", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 50);
            toast.show();
        }

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        chancesCount = 0;
                        moves = new Player[9];
                        currentPlayer = Player.ONE;
                        Toast.makeText(getApplicationContext(),"New Game Begins !!",Toast.LENGTH_LONG).show();
                        for(int  i = 0 ;i < 9 ; i++){
                            ImageView imt = (ImageView)imgStore[i];
                            if(imt != null){
                                imt.animate().alpha(0);
                            }
                        }
                    }
                },
                3000);


    }
}
