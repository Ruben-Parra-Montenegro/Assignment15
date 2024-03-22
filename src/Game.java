import javax.swing.Action;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Game {

    @FXML
    private TextField a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y;

    private TextField[][] board;

    public void initialize() {
        board = new TextField[][] {
                { a, b, c, d, e },
                { f, g, h, i, j },
                { k, l, m, n, o },
                { p, q, r, s, t },
                { u, v, w, x, y }
        };

        ChangeListener<String> textListener = new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                checkForWin();
            }
        };

        for (TextField[] row : board) {
            for (TextField field : row) {
                field.textProperty().addListener(textListener);
            }
        }

    }

    public void checkForWin() {
        // Check rows
        for (int i = 0; i < 5; i++) {
            if (checkEqual(board[i][0], board[i][1], board[i][2], board[i][3], board[i][4])) {
                System.out.println("We have a winner: " + board[i][0].getText());
                return;
            }
        }

        // Check columns
        for (int i = 0; i < 5; i++) {
            if (checkEqual(board[0][i], board[1][i], board[2][i], board[3][i], board[4][i])) {
                System.out.println("We have a winner: " + board[0][i].getText());
                return;
            }
        }

        // Check diagonals
        if (checkEqual(board[0][0], board[1][1], board[2][2], board[3][3], board[4][4]) ||
                checkEqual(board[0][4], board[1][3], board[2][2], board[3][1], board[4][0])) {
            System.out.println("We have a winner: " + board[2][2].getText());
            return;
        }
    }

    public boolean checkEqual(TextField... fields) {
        String first = fields[0].getText();
        for (TextField field : fields) {
            if (field.getText().isEmpty() || !field.getText().equals(first)) {
                return false;
            }
        }
        return true;
    }

    @FXML
    void reset(ActionEvent event) {
        for (TextField[] row : board) {
            for (TextField field : row) {
                field.clear();
            }
        }
    }
}