package com.company;

import java.util.ArrayList;
import java.util.List;

public class XoxCvC {
    int boardSize;
    String[][] board;
    boolean continuity = true;
    boolean isTie = false;
    String typeC2;
    String typeC1;
    int winC1 = 0;
    int winC2 = 0;

    public XoxCvC(int boardSize) {
        this.boardSize = boardSize - 1;
    }
    public void resetGame(){
        continuity = true;
        isTie = false;
    }

    public void createBoard() {
        String[][] board = new String[boardSize + 1][boardSize + 1];
        for (int i = 0; i <= boardSize; i++) {
            for (int l = 0; l <= boardSize; l++) {
                board[i][l] = "_";
            }
        }
        this.board = board;
    }

    public void board() {
        for (int i = 0; i <= boardSize; i++) {
            for (int n = 0; n <= boardSize; n++) {
                System.out.print(board[i][n] + "|");
            }
            System.out.println();
        }
    }

    public void game() {
        createBoard();
        Computer computer = new Computer("X");
        Computer2 computer2 = new Computer2("O");
        GameWinCheck gameWinCheck = new GameWinCheck();
        int random1 = (int) (Math.random() * (boardSize + 1));
        int random2 = (int) (Math.random() * (boardSize + 1));
        boolean firstMoveMadeComputer = false;
        while (!firstMoveMadeComputer) {
            if (board[random1][random2].equals("_")) {
                board[random1][random2] = typeC1;
                firstMoveMadeComputer = true;
            } else {
                random1 = (int) (Math.random() * (boardSize + 1));
                random2 = (int) (Math.random() * (boardSize + 1));
            }
        }
        int random3 = (int) (Math.random() * (boardSize + 1));
        int random4 = (int) (Math.random() * (boardSize + 1));
        boolean firstMoveMadeComputer2 = false;
        while (!firstMoveMadeComputer2) {
            if (board[random3][random4].equals("_")) {
                board[random3][random4] = typeC2;
                firstMoveMadeComputer2 = true;
            } else {
                random3 = (int) (Math.random() * (boardSize + 1));
                random4 = (int) (Math.random() * (boardSize + 1));
            }
        }
        try {
            while (true) {
                computer.allInOneMove();
                gameWinCheck.allInOneGameWinCheck();
                if (!continuity) {
                    board();
                    System.out.println("Player 1 Won!");
                    resetGame();
                    winC1 += 1;
                    break;
                }
                gameTieCheck();
                if (isTie) {
                    board();
                    System.out.println("Tie Game.No Winners");
                    resetGame();
                    break;
                }
                computer2.allInOneMove();
                gameWinCheck.allInOneGameWinCheck();
                if (!continuity) {
                    board();
                    System.out.println("Player 2 Won!");
                    resetGame();
                    winC2 += 1;
                    break;
                }
                gameTieCheck();
                if (isTie) {
                    board();
                    System.out.println("Tie Game.No Winners");
                    resetGame();
                    break;
                }
            }
        } catch (Exception exception) {
            System.out.println("Something went wrong.Shutting down");
        }
    }

    public class Computer2 {
        public Computer2(String typeComputer2) {
            typeC2 = typeComputer2;
        }

        boolean checkSingleMoveForWin2 = false;
        boolean isMoveMade2 = false;

        public void rowSingleMoveForWinCheck() {
            for (int i = 0; i <= boardSize && !isMoveMade2; i++) {
                boolean containsTypeC1 = false;
                for (int j = 0; j <= boardSize; j++) {
                    if (board[i][j].equals(typeC1)) {
                        containsTypeC1 = true;
                        break;
                    }
                }
                int counter = 0;
                for (int j = 0; j <= boardSize && !containsTypeC1; j++) {
                    for (int m = j; m <= boardSize; m++) {
                        if (board[i][j].equals(board[i][m]) && board[i][j].equals(typeC2)) {
                            counter += 1;
                        }
                    }
                }
                if (counter == (((boardSize + 1) * (boardSize + 2)) / 2) - (boardSize + 1)) {
                    for (int j = 0; j <= boardSize; j++) {
                        if (board[i][j].equals("_")) {
                            board[i][j] = typeC2;
                            isMoveMade2 = true;
                            checkSingleMoveForWin2 = true;
                            break;
                        }
                    }
                }
            }

        }

        public void columnSingleMoveForWinCheck() {
            for (int i = 0; i <= boardSize && !isMoveMade2; i++) {
                boolean containsTypeC1 = false;
                for (int j = 0; j <= boardSize; j++) {
                    if (board[j][i].equals(typeC1)) {
                        containsTypeC1 = true;
                        break;
                    }
                }
                int counter = 0;
                for (int j = 0; j <= boardSize && !containsTypeC1; j++) {
                    for (int m = j; m <= boardSize; m++) {
                        if (board[j][i].equals(board[m][i]) && board[j][i].equals(typeC2)) {
                            counter += 1;
                        }
                    }
                }
                if (counter == (((boardSize + 1) * (boardSize + 2)) / 2) - (boardSize + 1)) {
                    for (int j = 0; j <= boardSize; j++) {
                        if (board[j][i].equals("_")) {
                            board[j][i] = typeC2;
                            isMoveMade2 = true;
                            checkSingleMoveForWin2 = true;
                            break;
                        }
                    }
                }
            }

        }

        public void leftDiagonalSingleMoveForWinCheck() {
            boolean containsTypeC1 = false;
            for (int i = 0; i <= boardSize; i++) {
                if (board[i][i].equals(typeC1)) {
                    containsTypeC1 = true;
                    break;
                }
            }
            int counter = 0;
            for (int i = 0; i <= boardSize && !containsTypeC1; i++) {
                for (int j = i; j <= boardSize; j++) {
                    if (board[i][i].equals(board[j][j]) && board[i][i].equals(typeC2)) {
                        counter += 1;
                    }
                }
            }
            if (counter == (((boardSize + 1) * (boardSize + 2)) / 2) - (boardSize + 1)) {
                for (int i = 0; i <= boardSize; i++) {
                    if (board[i][i].equals("_")) {
                        board[i][i] = typeC2;
                        isMoveMade2 = true;
                        checkSingleMoveForWin2 = true;
                        break;
                    }
                }
            }

        }

        public void rightDiagonalSingleMoveForWinCheck() {
            boolean containsTypeC2 = false;
            for (int i = 0; i <= boardSize; i++) {
                if (board[i][boardSize - i].equals(typeC1)) {
                    containsTypeC2 = true;
                    break;
                }
            }
            int counter = 0;
            for (int i = 0; i <= boardSize && !containsTypeC2; i++) {
                for (int j = i; j <= boardSize; j++) {
                    if (board[i][boardSize - i].equals(board[j][boardSize - j]) && board[i][boardSize - i].equals(typeC2)) {
                        counter += 1;
                    }
                }
            }
            if (counter == (((boardSize + 1) * (boardSize + 2)) / 2) - (boardSize + 1)) {
                for (int i = 0; i <= boardSize; i++) {
                    if (board[i][boardSize - i].equals("_")) {
                        board[i][boardSize - i] = typeC2;
                        isMoveMade2 = true;
                        break;
                    }
                }
            }
        }

        public void rowPreventionCheck() {
            if (!checkSingleMoveForWin2 && !isMoveMade2) {
                for (int i = 0; i <= boardSize && !isMoveMade2; i++) {
                    boolean containsTypeC2 = false;
                    for (int j = 0; j <= boardSize; j++) {
                        if (board[i][j].equals(typeC2)) {
                            containsTypeC2 = true;
                            break;
                        }
                    }
                    int counter = 0;
                    for (int j = 0; j <= boardSize && !containsTypeC2; j++) {
                        for (int m = j; m <= boardSize; m++) {
                            if (board[i][j].equals(board[i][m]) && board[i][j].equals(typeC1)) {
                                counter += 1;
                            }
                        }
                    }
                    if (counter == (((boardSize + 1) * (boardSize + 2)) / 2) - (boardSize + 1)) {
                        for (int j = 0; j <= boardSize; j++) {
                            if (board[i][j].equals("_")) {
                                board[i][j] = typeC2;
                                isMoveMade2 = true;
                                break;
                            }
                        }
                    }
                }
            }
        }

        public void columnPreventionCheck() {
            if (!checkSingleMoveForWin2 && !isMoveMade2) {
                for (int i = 0; i <= boardSize && !isMoveMade2; i++) {
                    boolean containsTypeC2 = false;
                    for (int j = 0; j <= boardSize; j++) {
                        if (board[j][i].equals(typeC2)) {
                            containsTypeC2 = true;
                            break;
                        }
                    }
                    int counter = 0;
                    for (int j = 0; j <= boardSize && !containsTypeC2; j++) {
                        for (int m = j; m <= boardSize; m++) {
                            if (board[j][i].equals(board[m][i]) && board[j][i].equals(typeC1)) {
                                counter += 1;
                            }
                        }
                    }
                    if (counter == (((boardSize + 1) * (boardSize + 2)) / 2) - (boardSize + 1)) {
                        for (int j = 0; j <= boardSize; j++) {
                            if (board[j][i].equals("_")) {
                                board[j][i] = typeC2;
                                isMoveMade2 = true;
                                break;
                            }
                        }
                    }
                }
            }
        }

        public void leftDiagonalPreventionCheck() {
            if (!checkSingleMoveForWin2 && !isMoveMade2) {
                boolean containsTypeC2 = false;
                for (int i = 0; i <= boardSize; i++) {
                    if (board[i][i].equals(typeC2)) {
                        containsTypeC2 = true;
                        break;
                    }
                }
                int counter = 0;
                for (int i = 0; i <= boardSize && !containsTypeC2; i++) {
                    for (int j = i; j <= boardSize; j++) {
                        if (board[i][i].equals(board[j][j]) && board[i][i].equals(typeC1)) {
                            counter += 1;
                        }
                    }
                }
                if (counter == (((boardSize + 1) * (boardSize + 2)) / 2) - (boardSize + 1)) {
                    for (int i = 0; i <= boardSize; i++) {
                        if (board[i][i].equals("_")) {
                            board[i][i] = typeC2;
                            isMoveMade2 = true;
                            break;
                        }
                    }
                }
            }
        }

        public void rightDiagonalPreventionCheck() {
            if (!checkSingleMoveForWin2 && !isMoveMade2) {
                boolean containsTypeC2 = false;
                for (int i = 0; i <= boardSize; i++) {
                    if (board[i][boardSize - i].equals(typeC2)) {
                        containsTypeC2 = true;
                        break;
                    }
                }
                int counter = 0;
                for (int i = 0; i <= boardSize && !containsTypeC2; i++) {
                    for (int j = i; j <= boardSize; j++) {
                        if (board[i][boardSize - i].equals(board[j][boardSize - j]) && board[i][boardSize - i].equals(typeC1)) {
                            counter += 1;
                        }
                    }
                }
                if (counter == (((boardSize + 1) * (boardSize + 2)) / 2) - (boardSize + 1)) {
                    for (int i = 0; i <= boardSize; i++) {
                        if (board[i][boardSize - i].equals("_")) {
                            board[i][boardSize - i] = typeC2;
                            isMoveMade2 = true;
                            break;
                        }
                    }
                }
            }
        }

        public class TryingToWin2 {
            List<Integer> highestCounter = new ArrayList<>();
            List<String> highestIndexInfo = new ArrayList<>();
            List<Integer> highestLocation = new ArrayList<>();

            public void findHighestOfRows() {
                highestCounter.add(0);
                highestLocation.add(0);
                highestIndexInfo.add("Empty");
                for (int i = 0; i <= boardSize && !isMoveMade2; i++) {
                    boolean containsTypeC1 = false;
                    for (int j = 0; j <= boardSize; j++) {
                        if (board[i][j].equals(typeC1)) {
                            containsTypeC1 = true;
                            break;
                        }
                    }
                    int counter = 0;
                    for (int j = 0; j <= boardSize && !containsTypeC1; j++) {
                        for (int m = j; m <= boardSize; m++) {
                            if (board[i][j].equals(board[i][m]) && board[i][j].equals(typeC2)) {
                                counter += 1;
                            }
                        }
                    }
                    if (counter >= highestCounter.get(0) && !containsTypeC1) {
                        highestCounter.set(0, counter);
                        highestIndexInfo.set(0, "Row");
                        highestLocation.set(0, i);
                    }
                }
            }

            public void findHighestOfColumns() {
                for (int i = 0; i <= boardSize && !isMoveMade2; i++) {
                    boolean containsTypeC1 = false;
                    for (int j = 0; j <= boardSize; j++) {
                        if (board[j][i].equals(typeC1)) {
                            containsTypeC1 = true;
                            break;
                        }
                    }
                    int counter = 0;
                    for (int j = 0; j <= boardSize && !containsTypeC1; j++) {
                        for (int m = j; m <= boardSize; m++) {
                            if (board[j][i].equals(board[m][i]) && board[j][i].equals(typeC2)) {
                                counter += 1;
                            }
                        }
                    }
                    if (counter >= highestCounter.get(0) && !containsTypeC1) {
                        highestCounter.set(0, counter);
                        highestIndexInfo.set(0, "Col");
                        highestLocation.set(0, i);
                    }
                }
            }

            public void findLeftDiagonal() {
                boolean containsTypeC1 = false;
                for (int i = 0; i <= boardSize; i++) {
                    if (board[i][i].equals(typeC2)) {
                        containsTypeC1 = true;
                        break;
                    }
                }
                int counter = 0;
                for (int i = 0; i <= boardSize && !containsTypeC1; i++) {
                    for (int j = i; j <= boardSize; j++) {
                        if (board[i][i].equals(board[j][j]) && board[i][i].equals(typeC2)) {
                            counter += 1;
                        }
                    }
                }
                if (counter >= highestCounter.get(0) && !containsTypeC1) {
                    highestCounter.set(0, counter);
                    highestIndexInfo.set(0, "LeftDiagonal");
                }
            }

            public void findRightDiagonal() {
                boolean containsTypeC1 = false;
                for (int i = 0; i <= boardSize; i++) {
                    if (board[i][boardSize - i].equals(typeC1)) {
                        containsTypeC1 = true;
                        break;
                    }
                }
                int counter = 0;
                for (int i = 0; i <= boardSize && !containsTypeC1; i++) {
                    for (int j = i; j <= boardSize; j++) {
                        if (board[i][boardSize - i].equals(board[j][boardSize - j]) && board[i][boardSize - i].equals(typeC2)) {
                            counter += 1;
                        }
                    }
                }
                if (counter >= highestCounter.get(0) && !containsTypeC1) {
                    highestCounter.set(0, counter);
                    highestIndexInfo.set(0, "RightDiagonal");
                }
            }

            public void makeMove() {
                if (!highestIndexInfo.get(0).equals("Empty")) {
                    if (highestIndexInfo.get(0).equals("Row") && !isMoveMade2) {
                        for (int i = 0; i <= boardSize && !isMoveMade2; i++) {
                            if (board[highestLocation.get(0)][i].equals("_")) {
                                board[highestLocation.get(0)][i] = typeC2;
                                isMoveMade2 = true;
                            }
                        }
                    }
                    if (highestIndexInfo.get(0).equals("Col") && !isMoveMade2) {
                        for (int i = 0; i <= boardSize && !isMoveMade2; i++) {
                            if (board[i][highestLocation.get(0)].equals("_")) {
                                board[i][highestLocation.get(0)] = typeC2;
                                isMoveMade2 = true;
                            }
                        }
                    }
                    if (highestIndexInfo.get(0).equals("LeftDiagonal") && !isMoveMade2) {
                        for (int i = 0; i <= boardSize && !isMoveMade2; i++) {
                            if (board[i][i].equals("_")) {
                                board[i][i] = typeC2;
                                isMoveMade2 = true;
                            }
                        }
                    }
                    if (highestIndexInfo.get(0).equals("RightDiagonal") && !isMoveMade2) {
                        for (int i = 0; i <= boardSize && !isMoveMade2; i++) {
                            if (board[i][boardSize].equals("_")) {
                                board[i][boardSize] = typeC2;
                                isMoveMade2 = true;
                            }
                        }
                    }
                }
            }

            public void allInOneTryingToWin() {
                findHighestOfRows();
                findHighestOfColumns();
                findLeftDiagonal();
                findRightDiagonal();
                makeMove();
            }
        }

        public void fillSpaces() {
            if (!isMoveMade2) {
                for (int i = 0; i <= boardSize && !isMoveMade2; i++) {
                    for (int j = 0; j <= boardSize && !isMoveMade2; j++) {
                        if (board[i][j].equals("_")) {
                            board[i][j] = typeC2;
                            isMoveMade2 = true;
                        }
                    }
                }
            }
        }

        public void reset() {
            isMoveMade2 = false;
        }

        public void allInOneMove() {
            rowSingleMoveForWinCheck();
            columnSingleMoveForWinCheck();
            leftDiagonalSingleMoveForWinCheck();
            rightDiagonalSingleMoveForWinCheck();
            rowPreventionCheck();
            columnPreventionCheck();
            leftDiagonalPreventionCheck();
            rightDiagonalPreventionCheck();
            Computer2.TryingToWin2 tryingToWin = new Computer2.TryingToWin2();
            tryingToWin.allInOneTryingToWin();
            fillSpaces();
            reset();

        }
    }

    public class Computer {

        public Computer(String typeComputer) {
            typeC1 = typeComputer;
        }

        boolean checkSingleMoveForWin = false;
        boolean isMoveMade = false;

        public void rowSingleMoveForWinCheck() {
            for (int i = 0; i <= boardSize && !isMoveMade; i++) {
                boolean containsTypeP = false;
                for (int j = 0; j <= boardSize; j++) {
                    if (board[i][j].equals(typeC2)) {
                        containsTypeP = true;
                        break;
                    }
                }
                int counter = 0;
                for (int j = 0; j <= boardSize && !containsTypeP; j++) {
                    for (int m = j; m <= boardSize; m++) {
                        if (board[i][j].equals(board[i][m]) && board[i][j].equals(typeC1)) {
                            counter += 1;
                        }
                    }
                }
                if (counter == (((boardSize + 1) * (boardSize + 2)) / 2) - (boardSize + 1)) {
                    for (int j = 0; j <= boardSize; j++) {
                        if (board[i][j].equals("_")) {
                            board[i][j] = typeC1;
                            isMoveMade = true;
                            checkSingleMoveForWin = true;
                            break;
                        }
                    }
                }
            }

        }

        public void columnSingleMoveForWinCheck() {
            for (int i = 0; i <= boardSize && !isMoveMade; i++) {
                boolean containsTypeP = false;
                for (int j = 0; j <= boardSize; j++) {
                    if (board[j][i].equals(typeC2)) {
                        containsTypeP = true;
                        break;
                    }
                }
                int counter = 0;
                for (int j = 0; j <= boardSize && !containsTypeP; j++) {
                    for (int m = j; m <= boardSize; m++) {
                        if (board[j][i].equals(board[m][i]) && board[j][i].equals(typeC1)) {
                            counter += 1;
                        }
                    }
                }
                if (counter == (((boardSize + 1) * (boardSize + 2)) / 2) - (boardSize + 1)) {
                    for (int j = 0; j <= boardSize; j++) {
                        if (board[j][i].equals("_")) {
                            board[j][i] = typeC1;
                            isMoveMade = true;
                            checkSingleMoveForWin = true;
                            break;
                        }
                    }
                }
            }

        }

        public void leftDiagonalSingleMoveForWinCheck() {
            boolean containsTypeP = false;
            for (int i = 0; i <= boardSize; i++) {
                if (board[i][i].equals(typeC2)) {
                    containsTypeP = true;
                    break;
                }
            }
            int counter = 0;
            for (int i = 0; i <= boardSize && !containsTypeP; i++) {
                for (int j = i; j <= boardSize; j++) {
                    if (board[i][i].equals(board[j][j]) && board[i][i].equals(typeC1)) {
                        counter += 1;
                    }
                }
            }
            if (counter == (((boardSize + 1) * (boardSize + 2)) / 2) - (boardSize + 1)) {
                for (int i = 0; i <= boardSize; i++) {
                    if (board[i][i].equals("_")) {
                        board[i][i] = typeC1;
                        isMoveMade = true;
                        checkSingleMoveForWin = true;
                        break;
                    }
                }
            }

        }

        public void rightDiagonalSingleMoveForWinCheck() {
            boolean containsTypeP = false;
            for (int i = 0; i <= boardSize; i++) {
                if (board[i][boardSize - i].equals(typeC2)) {
                    containsTypeP = true;
                    break;
                }
            }
            int counter = 0;
            for (int i = 0; i <= boardSize && !containsTypeP; i++) {
                for (int j = i; j <= boardSize; j++) {
                    if (board[i][boardSize - i].equals(board[j][boardSize - j]) && board[i][boardSize - i].equals(typeC1)) {
                        counter += 1;
                    }
                }
            }
            if (counter == (((boardSize + 1) * (boardSize + 2)) / 2) - (boardSize + 1)) {
                for (int i = 0; i <= boardSize; i++) {
                    if (board[i][boardSize - i].equals("_")) {
                        board[i][boardSize - i] = typeC1;
                        isMoveMade = true;
                        break;
                    }
                }
            }
        }

        public void rowPreventionCheck() {
            if (!checkSingleMoveForWin && !isMoveMade) {
                for (int i = 0; i <= boardSize && !isMoveMade; i++) {
                    boolean containsTypeC = false;
                    for (int j = 0; j <= boardSize; j++) {
                        if (board[i][j].equals(typeC1)) {
                            containsTypeC = true;
                            break;
                        }
                    }
                    int counter = 0;
                    for (int j = 0; j <= boardSize && !containsTypeC; j++) {
                        for (int m = j; m <= boardSize; m++) {
                            if (board[i][j].equals(board[i][m]) && board[i][j].equals(typeC2)) {
                                counter += 1;
                            }
                        }
                    }
                    if (counter == (((boardSize + 1) * (boardSize + 2)) / 2) - (boardSize + 1)) {
                        for (int j = 0; j <= boardSize; j++) {
                            if (board[i][j].equals("_")) {
                                board[i][j] = typeC1;
                                isMoveMade = true;
                                break;
                            }
                        }
                    }
                }
            }
        }

        public void columnPreventionCheck() {
            if (!checkSingleMoveForWin && !isMoveMade) {
                for (int i = 0; i <= boardSize && !isMoveMade; i++) {
                    boolean containsTypeC = false;
                    for (int j = 0; j <= boardSize; j++) {
                        if (board[j][i].equals(typeC1)) {
                            containsTypeC = true;
                            break;
                        }
                    }
                    int counter = 0;
                    for (int j = 0; j <= boardSize && !containsTypeC; j++) {
                        for (int m = j; m <= boardSize; m++) {
                            if (board[j][i].equals(board[m][i]) && board[j][i].equals(typeC2)) {
                                counter += 1;
                            }
                        }
                    }
                    if (counter == (((boardSize + 1) * (boardSize + 2)) / 2) - (boardSize + 1)) {
                        for (int j = 0; j <= boardSize; j++) {
                            if (board[j][i].equals("_")) {
                                board[j][i] = typeC1;
                                isMoveMade = true;
                                break;
                            }
                        }
                    }
                }
            }
        }

        public void leftDiagonalPreventionCheck() {
            if (!checkSingleMoveForWin && !isMoveMade) {
                boolean containsTypeC = false;
                for (int i = 0; i <= boardSize; i++) {
                    if (board[i][i].equals(typeC1)) {
                        containsTypeC = true;
                        break;
                    }
                }
                int counter = 0;
                for (int i = 0; i <= boardSize && !containsTypeC; i++) {
                    for (int j = i; j <= boardSize; j++) {
                        if (board[i][i].equals(board[j][j]) && board[i][i].equals(typeC2)) {
                            counter += 1;
                        }
                    }
                }
                if (counter == (((boardSize + 1) * (boardSize + 2)) / 2) - (boardSize + 1)) {
                    for (int i = 0; i <= boardSize; i++) {
                        if (board[i][i].equals("_")) {
                            board[i][i] = typeC1;
                            isMoveMade = true;
                            break;
                        }
                    }
                }
            }
        }

        public void rightDiagonalPreventionCheck() {
            if (!checkSingleMoveForWin && !isMoveMade) {
                boolean containsTypeC = false;
                for (int i = 0; i <= boardSize; i++) {
                    if (board[i][boardSize - i].equals(typeC1)) {
                        containsTypeC = true;
                        break;
                    }
                }
                int counter = 0;
                for (int i = 0; i <= boardSize && !containsTypeC; i++) {
                    for (int j = i; j <= boardSize; j++) {
                        if (board[i][boardSize - i].equals(board[j][boardSize - j]) && board[i][boardSize - i].equals(typeC2)) {
                            counter += 1;
                        }
                    }
                }
                if (counter == (((boardSize + 1) * (boardSize + 2)) / 2) - (boardSize + 1)) {
                    for (int i = 0; i <= boardSize; i++) {
                        if (board[i][boardSize - i].equals("_")) {
                            board[i][boardSize - i] = typeC1;
                            isMoveMade = true;
                            break;
                        }
                    }
                }
            }
        }

        public class TryingToWin {
            List<Integer> highestCounter = new ArrayList<>();
            List<String> highestIndexInfo = new ArrayList<>();
            List<Integer> highestLocation = new ArrayList<>();

            public void findHighestOfRows() {
                highestCounter.add(0);
                highestLocation.add(0);
                highestIndexInfo.add("Empty");
                for (int i = 0; i <= boardSize && !isMoveMade; i++) {
                    boolean containsTypeP = false;
                    for (int j = 0; j <= boardSize; j++) {
                        if (board[i][j].equals(typeC2)) {
                            containsTypeP = true;
                            break;
                        }
                    }
                    int counter = 0;
                    for (int j = 0; j <= boardSize && !containsTypeP; j++) {
                        for (int m = j; m <= boardSize; m++) {
                            if (board[i][j].equals(board[i][m]) && board[i][j].equals(typeC1)) {
                                counter += 1;
                            }
                        }
                    }
                    if (counter >= highestCounter.get(0) && !containsTypeP) {
                        highestCounter.set(0, counter);
                        highestIndexInfo.set(0, "Row");
                        highestLocation.set(0, i);
                    }
                }
            }

            public void findHighestOfColumns() {
                for (int i = 0; i <= boardSize && !isMoveMade; i++) {
                    boolean containsTypeP = false;
                    for (int j = 0; j <= boardSize; j++) {
                        if (board[j][i].equals(typeC2)) {
                            containsTypeP = true;
                            break;
                        }
                    }
                    int counter = 0;
                    for (int j = 0; j <= boardSize && !containsTypeP; j++) {
                        for (int m = j; m <= boardSize; m++) {
                            if (board[j][i].equals(board[m][i]) && board[j][i].equals(typeC1)) {
                                counter += 1;
                            }
                        }
                    }
                    if (counter >= highestCounter.get(0) && !containsTypeP) {
                        highestCounter.set(0, counter);
                        highestIndexInfo.set(0, "Col");
                        highestLocation.set(0, i);
                    }
                }
            }

            public void findLeftDiagonal() {
                boolean containsTypeP = false;
                for (int i = 0; i <= boardSize; i++) {
                    if (board[i][i].equals(typeC2)) {
                        containsTypeP = true;
                        break;
                    }
                }
                int counter = 0;
                for (int i = 0; i <= boardSize && !containsTypeP; i++) {
                    for (int j = i; j <= boardSize; j++) {
                        if (board[i][i].equals(board[j][j]) && board[i][i].equals(typeC1)) {
                            counter += 1;
                        }
                    }
                }
                if (counter >= highestCounter.get(0) && !containsTypeP) {
                    highestCounter.set(0, counter);
                    highestIndexInfo.set(0, "LeftDiagonal");
                }
            }

            public void findRightDiagonal() {
                boolean containsTypeP = false;
                for (int i = 0; i <= boardSize; i++) {
                    if (board[i][boardSize - i].equals(typeC2)) {
                        containsTypeP = true;
                        break;
                    }
                }
                int counter = 0;
                for (int i = 0; i <= boardSize && !containsTypeP; i++) {
                    for (int j = i; j <= boardSize; j++) {
                        if (board[i][boardSize - i].equals(board[j][boardSize - j]) && board[i][boardSize - i].equals(typeC1)) {
                            counter += 1;
                        }
                    }
                }
                if (counter >= highestCounter.get(0) && !containsTypeP) {
                    highestCounter.set(0, counter);
                    highestIndexInfo.set(0, "RightDiagonal");
                }
            }

            public void makeMove() {
                if (!highestIndexInfo.get(0).equals("Empty")) {
                    if (highestIndexInfo.get(0).equals("Row") && !isMoveMade) {
                        for (int i = 0; i <= boardSize && !isMoveMade; i++) {
                            if (board[highestLocation.get(0)][i].equals("_")) {
                                board[highestLocation.get(0)][i] = typeC1;
                                isMoveMade = true;
                            }
                        }
                    }
                    if (highestIndexInfo.get(0).equals("Col") && !isMoveMade) {
                        for (int i = 0; i <= boardSize && !isMoveMade; i++) {
                            if (board[i][highestLocation.get(0)].equals("_")) {
                                board[i][highestLocation.get(0)] = typeC1;
                                isMoveMade = true;
                            }
                        }
                    }
                    if (highestIndexInfo.get(0).equals("LeftDiagonal") && !isMoveMade) {
                        for (int i = 0; i <= boardSize && !isMoveMade; i++) {
                            if (board[i][i].equals("_")) {
                                board[i][i] = typeC1;
                                isMoveMade = true;
                            }
                        }
                    }
                    if (highestIndexInfo.get(0).equals("RightDiagonal") && !isMoveMade) {
                        for (int i = 0; i <= boardSize && !isMoveMade; i++) {
                            if (board[i][boardSize].equals("_")) {
                                board[i][boardSize] = typeC1;
                                isMoveMade = true;
                            }
                        }
                    }
                }
            }

            public void allInOneTryingToWin() {
                findHighestOfRows();
                findHighestOfColumns();
                findLeftDiagonal();
                findRightDiagonal();
                makeMove();
            }
        }

        public void fillSpaces() {
            if (!isMoveMade) {
                for (int i = 0; i <= boardSize && !isMoveMade; i++) {
                    for (int j = 0; j <= boardSize && !isMoveMade; j++) {
                        if (board[i][j].equals("_")) {
                            board[i][j] = typeC1;
                            isMoveMade = true;
                        }
                    }
                }
            }
        }

        public void reset() {
            isMoveMade = false;
        }

        public void allInOneMove() {
            rowSingleMoveForWinCheck();
            columnSingleMoveForWinCheck();
            leftDiagonalSingleMoveForWinCheck();
            rightDiagonalSingleMoveForWinCheck();
            rowPreventionCheck();
            columnPreventionCheck();
            leftDiagonalPreventionCheck();
            rightDiagonalPreventionCheck();
            TryingToWin tryingToWin = new TryingToWin();
            tryingToWin.allInOneTryingToWin();
            fillSpaces();
            reset();

        }
    }

    public class GameWinCheck {
        public void rowCheck() {
            int counter = -1;
            for (int i = 0; i <= boardSize; i++) {
                for (int j = 0; j <= boardSize; j++) {
                    if (board[i][i].equals(board[i][j]) && !board[i][i].equals("_")) {
                        counter += 1;
                    }
                }
                if (counter == boardSize) {
                    continuity = false;
                    break;
                }
                counter = -1;
            }
        }

        public void columnCheck() {
            int counter = -1;
            for (int i = 0; i <= boardSize; i++) {
                for (int j = 0; j <= boardSize; j++) {
                    if (board[i][i].equals(board[j][i]) && !board[i][i].equals("_")) {
                        counter += 1;
                    }
                }
                if (counter == boardSize) {
                    continuity = false;
                }
                counter = -1;
            }
        }

        public void leftDiagonalCheck() {
            int counter = -1;
            for (int i = 0; i <= boardSize; i++) {
                if (board[0][0].equals(board[i][i]) && !board[0][0].equals("_")) {
                    counter += 1;
                }
            }
            if (counter == boardSize) {
                continuity = false;
            }
        }

        public void rightDiagonalCheck() {
            int counter = -1;

            if (!board[0][boardSize].equals("_")) {
                for (int i = 0; i <= boardSize; i++) {
                    if (board[0][boardSize].equals(board[i][boardSize - i])) {
                        counter += 1;
                    }
                }
                if (counter == boardSize) {
                    continuity = false;
                }
            }
        }

        public void allInOneGameWinCheck() {
            rowCheck();
            columnCheck();
            leftDiagonalCheck();
            rightDiagonalCheck();
        }
    }

    public void gameTieCheck() {
        int counter = 0;
        for (int i = 0; i <= boardSize; i++) {
            for (int j = 0; j <= boardSize; j++) {
                if (!board[i][j].equals("_")) {
                    counter += 1;
                }
            }
        }
        if (counter == (boardSize + 1) * (boardSize + 1)) {
            isTie = true;
        }
    }
}
