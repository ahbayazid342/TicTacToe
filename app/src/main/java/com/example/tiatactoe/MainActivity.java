package com.example.tiatactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvPlayerOne, tvPlayerTwo;
    private Button reset;
    private Button[][] button = new Button[3][3];

    private boolean playerTurn = false;

    private int playerOne;
    private int playerTwo;

    private int roundCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvPlayerOne = findViewById(R.id.tvPlayer1);
        tvPlayerTwo = findViewById(R.id.tvPlayer2);
        reset = findViewById(R.id.reset);

        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                String buttonId = "btn_" + i + j;
                int resId = getResources().getIdentifier(buttonId, "id", getPackageName());
                button[i][j] = findViewById(resId);
                button[i][j].setOnClickListener(this);
            }
        }

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetBoard();
            }
        });

    }

    private void resetBoard() {
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                button[i][j].setText("");
            }
        }

        roundCount = 0;
        tvPlayerOne.setText("Player 1 : " + 0);
        tvPlayerTwo.setText("Player 2 : " + 0);
        playerTurn = true;
    }

    @Override
    public void onClick(View v) {
        if (!((Button) v).getText().toString().equals("")){
            return;
        }

        if (playerTurn){
            ((Button) v).setText("x");
        } else {
            ((Button) v).setText("o");
        }

        roundCount++;
        if (checkForWin()){
            if (playerTurn){
                playerOneWins();
            } else {
                playerTwoWins();
            }
        } else if (roundCount == 9){
            draw();
        } else {
            playerTurn = !playerTurn;
        }
    }

    private void draw() {
        Toast.makeText(MainActivity.this, "Match Draw !!!", Toast.LENGTH_SHORT).show();
        resetBoard();
    }


    private void playerOneWins() {
        Toast.makeText(MainActivity.this, "Player 1 Win !!!", Toast.LENGTH_SHORT).show();
        playerOne++;
        tvPlayerOne.setText("Player 1 : " + playerOne);
    }

    private void playerTwoWins() {
        Toast.makeText(MainActivity.this, "Player 2 Win !!!", Toast.LENGTH_SHORT).show();
        playerTwo++;
        tvPlayerTwo.setText("Player 1 : " + playerTwo);
    }

    private boolean checkForWin() {
        String[][] field = new String[3][3];
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                field[i][j] = button[i][j].getText().toString();
            }
        }

        for (int i = 0; i < 3; i++){
            if (field[0][i].equals(field[1][i]) && field[0][i].equals(field[2][i]) && !field[0][i].equals("")){
                return true;
            }
        }

        for (int i = 0; i < 3; i++){
            if (field[i][0].equals(field[i][1]) && field[i][0].equals(field[i][2]) && !field[i][0].equals("")){
                return true;
            }
        }

        if (field[0][0].equals(field[1][1]) && field[0][0].equals(field[2][2]) && !field[0][0].equals("")){
            return true;
        }

        if (field[0][2].equals(field[1][1]) && field[0][2].equals(field[2][0]) && !field[0][2].equals("")){
            return true;
        }
        return false;
    }
}