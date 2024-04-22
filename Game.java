import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Random;

public class Game extends JFrame implements ActionListener {
    Container cp;
    JButton[] bt = new JButton[9];
    int[] check = new int[9];
    JLabel text;
    JPanel board;
    boolean xturn, win = false;
    int turnplay = 0;

    public Game() {
        setBoard();
        setting();
    }

    private void setBoard() {
        cp = this.getContentPane();
        cp.setLayout(new BorderLayout());
        board = new JPanel();
        board.setLayout(new GridLayout(3, 3));
        text = new JLabel(random1st());
        text.setFont(new Font("Times New Roman", Font.PLAIN, 50));
        text.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < 9; i++) {
            bt[i] = new JButton();
            bt[i].setFont(new Font("Times New Roman", Font.PLAIN, 30));
            bt[i].addActionListener(this);
            bt[i].setPreferredSize(new Dimension(150, 150));
            check[i] = 0;
            board.add(bt[i]);
        }
        cp.add(text, BorderLayout.NORTH);
        cp.add(board, BorderLayout.CENTER);

        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(e -> resetGame());
        resetButton.setFont(new Font("Times New Roman", Font.PLAIN, 40));
        resetButton.setPreferredSize(new Dimension(450,100));
        cp.add(resetButton, BorderLayout.SOUTH);
    }

    private void setting() {
        this.pack();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private String random1st() {
        Random rd = new Random();
        int a = rd.nextInt(2);
        if (a == 0) {
            xturn = true;
            return "X turn";
        } else {
            xturn = false;
            return "O turn";
        }
    }

    private void resetGame() {
        for (int i = 0; i < 9; i++) {
            bt[i].setText("");
            check[i] = 0;
        }
        win = false;
        turnplay = 0;
        text.setText(random1st());
    }

    private void checkwin(int t) {
        if ((check[0] == t && check[1] == t && check[2] == t) ||
            (check[3] == t && check[4] == t && check[5] == t) ||
            (check[6] == t && check[7] == t && check[8] == t) ||
            (check[0] == t && check[3] == t && check[6] == t) ||
            (check[1] == t && check[4] == t && check[7] == t) ||
            (check[2] == t && check[5] == t && check[8] == t) ||
            (check[0] == t && check[4] == t && check[8] == t) ||
            (check[2] == t && check[4] == t && check[6] == t)) {
            win = true;
            if(t==1)text.setText("X wins!"); else text.setText("O wins!");
        }
    }

    public void actionPerformed(ActionEvent e) {
        for (int j = 0; j < 9; j++) {
            if (e.getSource() == bt[j] && check[j] == 0 && !win) {
                if (xturn) {
                    bt[j].setText("X");
                    xturn = false;
                    check[j] = 1;
                    turnplay++;
                    checkwin(1);
                    if (turnplay == 9 && !win) {
                        text.setText("Draw!");
                    }
                    else if (!win) text.setText("O turn");
                } else {
                    bt[j].setText("O");
                    xturn = true;
                    check[j] = 2;
                    turnplay++;
                    checkwin(2);
                    if (turnplay == 9 && !win) {
                        text.setText("Draw!");
                    }
                    else if (!win) text.setText("X turn");
                }
            }
        }
    }

    public static void main(String[] args) {
        new Game();
    }
}
