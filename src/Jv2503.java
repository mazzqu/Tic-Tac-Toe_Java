 /*
  * 名前：ラウレンシユス　アラン
  * クラス：KE2A06
   */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Jv2503 extends JFrame implements ActionListener {
//	変数の定義
		int[ ] position = new int[9];
		JButton [] buttons  = new JButton [9];
		JButton btnReset = new JButton("Reset");
		JPanel panel =new JPanel();
		int count = 0,
			  playerIndex = -1;

    public Jv2503 () {
        super ("Tic-Tac-Toe");

//  	ボードゲームの見た目設定

        Container cont = getContentPane();
        cont.setLayout(new BorderLayout());
        cont.add(BorderLayout.NORTH,btnReset);
        cont.add(BorderLayout.CENTER,panel);
        panel.setLayout(new GridLayout(3,3));
        setVisible (true);
        setSize (300, 350);
        setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);

        btnReset.addActionListener(this);
        //      ループカウンタでボードゲーム使い方法を制作する
        for (int i = 0; i < 9; ++i) {
            buttons [i] = new JButton (" ");
            buttons [i].addActionListener (this);
            buttons[i].setFont(new Font("Arial",Font.BOLD,40));
            panel.add (buttons [i]);
        }

//    ルールの定め
    for (int i=0; i<9; i++) {
        JButton finalButton = buttons[i];
        buttons[i].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                finalButton.setEnabled(false);
                if (playerIndex < 0){
                    finalButton.setText("O");
                }
                else{
                    finalButton.setText("X");
                }
                for (int i=0; i<9; i++){
                    if (e.getSource() == buttons[i])
                    	position[i] = playerIndex;
                }
                if (checkWinner() == -1){
                    btnReset.setText("You gotta win!");

                }
                else if (checkWinner() == 1){
                	btnReset.setText("You gotta Lose!");

                }
                else drawChecker();
                playerIndex *= -1;
            }
        });
       }
    }

//  btnResetの有効にするため処理
    void resetGame(){ //Resets the game.
    	btnReset.setText("Reset");
        for (JButton button:buttons){
            button.setEnabled(true);
            button.setText(" ");
        }
        for (int i=0; i<9; i++)
        	position[i] = 0;
    }

//    Drawと決めた
    void drawChecker(){
    	//Checks if the game results in a draw or not.
        for (JButton button:buttons){
            if (button.isEnabled())
            	return;
        }
        if(playerIndex == -1)
        	btnReset.setText("Draw game !");
        else
        resetGame();
    }

//勝負のつけるため優勝者
    private int checkWinner() {
    	if (position[0] == position[1] && position[0] == position[2] && position[0] != 0)
    		return position[0];
        else if(position[3] == position[4] && position[3] == position[5] && position[3] != 0)
        	return position[3];
        else if(position[6] == position[7] && position[6] == position[8] && position[6] != 0)
        	return position[6];
        else if(position[0] == position[3] && position[0] == position[6] && position[0] != 0)
        	return position[0];
        else if(position[1] == position[4] && position[1] == position[7] && position[1] != 0)
        	return position[1];
        else if(position[2] == position[5] && position[2] == position[8] && position[2] != 0)
        	return position[2];
        else if(position[4] == position[0] && position[4] == position[8] && position[4] != 0)
        	return position[4];
        else if(position[4] == position[2] && position[4] == position[6] && position[4] != 0)
        	return position[4];
        else return 0;
    }

    public static void main (String [] args) {
        new Jv2503 ();
    }
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnReset)
			resetGame();
	}
}