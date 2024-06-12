import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        TotalResult totalResult = new TotalResult();

        System.out.println("How many soldiers are attacking");
        int attackNum = Integer.parseInt(keyboard.nextLine());

        System.out.println("How many soldiers are defending");
        int defendNum = Integer.parseInt(keyboard.nextLine());

        attackNum = verifySoldierAmount(attackNum);
        defendNum = verifySoldierAmount(defendNum);

        firstAttackDice(totalResult, attackNum, defendNum);

        System.out.println("\t\t\tWins\tWin %\tAverage Soldiers left");
        System.out.println("Attacker |\t" + totalResult.getTotalAttackerWins() + " |\t" + totalResult.getAttackerWinsPercentage() + " |\t" + totalResult.getAverageAttackersLeft());
        System.out.println("Defender |\t" + totalResult.getTotalDefenderWins() + " |\t" + totalResult.getDefenderWinsPercentage() + " |\t" + totalResult.getAverageDefendersLeft());
        System.out.println("Total    |\t" + totalResult.getTotalPossibilities() + " |\t" + "100\t\t\t\t  " + " |\t" + totalResult.getAverageTotalLeft());
    }


    public static int verifySoldierAmount(int amount) {
        if (amount < 1) {
            amount = 1;
        }
        return amount;
    }


    public static void firstAttackDice(TotalResult totalResult, int attackNum, int defendNum) {
        if (attackNum >= 1) {
            for (int i = 1; i <= 6; i++) {
                secondAttackDice(totalResult, attackNum, defendNum, i);
            }
        }
    }


    public static void secondAttackDice(TotalResult totalResult, int attackNum, int defendNum, int firstAttackDieNum) {
        if (attackNum >= 2) {
            for (int i = 1; i <= 6; i++) {
                thirdAttackDice(totalResult, attackNum, defendNum, firstAttackDieNum, i);
            }
        } else {
            firstDefendDice(totalResult, attackNum, defendNum, firstAttackDieNum, 0, 0);
        }
    }


    public static void thirdAttackDice(TotalResult totalResult, int attackNum, int defendNum, int firstAttackDieNum, int secondAttackDieNum) {
        if (attackNum >= 3) {
            for (int i = 1; i <= 6; i++) {
                firstDefendDice(totalResult, attackNum, defendNum, firstAttackDieNum, secondAttackDieNum, i);
            }
        } else {
            firstDefendDice(totalResult, attackNum, defendNum, firstAttackDieNum, secondAttackDieNum, 0);
        }
    }


    public static void firstDefendDice(TotalResult totalResult, int attackNum, int defendNum, int firstAttackDieNum, int secondAttackDieNum, int thirdAttackDieNum) {
        if (defendNum >= 1) {
            for (int i = 1; i <= 6; i++) {
                secondDefendDice(totalResult, attackNum, defendNum, firstAttackDieNum, secondAttackDieNum, thirdAttackDieNum, i);
            }
        }
    }


    public static void secondDefendDice(TotalResult totalResult, int attackNum, int defendNum, int firstAttackDieNum, int secondAttackDieNum, int thirdAttackDieNum, int firstDefendDieNum) {
        if (attackNum >= 2) {
            for (int i = 1; i <= 6; i++) {
                compareDice(totalResult, attackNum, defendNum, firstAttackDieNum, secondAttackDieNum, thirdAttackDieNum, firstDefendDieNum, i);
            }
        } else {
            compareDice(totalResult, attackNum, defendNum, firstAttackDieNum, secondAttackDieNum, thirdAttackDieNum, firstDefendDieNum, 0);
        }
    }


    public static void compareDice(TotalResult totalResult, int attackNum, int defendNum, int firstAttackDieNum, int secondAttackDieNum, int thirdAttackDieNum, int firstDefendDieNum, int secondDefendDieNum) {
        int firstHighestAttackNum = compareHighestDice(firstAttackDieNum, secondAttackDieNum, thirdAttackDieNum);
        int secondHighestAttackNum = compareSecondHighestDice(firstAttackDieNum, secondAttackDieNum, thirdAttackDieNum);
        int firstHighestDefendNum = compareHighestDice(firstDefendDieNum, secondDefendDieNum, 0);
        int secondHighestDefendNum = compareSecondHighestDice(firstDefendDieNum, secondDefendDieNum, 0);

        if (firstHighestAttackNum <= firstHighestDefendNum) {
            attackNum--;
        } else {
            defendNum--;
        }

        if (secondHighestAttackNum != 0 && secondHighestDefendNum != 0) {
            if (secondHighestAttackNum <= secondHighestDefendNum) {
                attackNum--;
            } else {
                defendNum--;
            }
        }

        boolean isBattleOver = testIfWinner(totalResult, attackNum, defendNum);
        if (isBattleOver != true && (attackNum != 0 || defendNum != 0)) {
            firstAttackDice(totalResult, attackNum, defendNum);
        }
    }


    public static int compareHighestDice(int firstDieNum, int secondDieNum, int thirdDieNum) {
        int highestNum = firstDieNum;
        if (secondDieNum > highestNum) {
            highestNum = secondDieNum;
        }
        if (thirdDieNum > highestNum) {
            highestNum = thirdDieNum;
        }
        return highestNum;
    }


    public static int compareSecondHighestDice(int firstDieNum, int secondDieNum, int thirdDieNum) {
        int highestNum = firstDieNum;
        int highestDieNum = 1;
        if (secondDieNum > highestNum) {
            highestDieNum = 2;
        }
        if (thirdDieNum > highestNum) {
            highestDieNum = 3;
        }

        int secondHighestNum = 0;
        if (highestDieNum == 1) {
            secondHighestNum = secondDieNum;
            if (thirdDieNum > secondHighestNum) {
                secondHighestNum = thirdDieNum;
            }
        } else if (highestDieNum == 2) {
            secondHighestNum = firstDieNum;
            if (thirdDieNum > secondHighestNum) {
                secondHighestNum = thirdDieNum;
            }
        } else if (highestDieNum == 3) {
            secondHighestNum = firstDieNum;
            if (secondDieNum > secondHighestNum) {
                secondHighestNum = secondDieNum;
            }
        }
        return secondHighestNum;
    }


    public static boolean testIfWinner(TotalResult totalResult, int attackNum, int defendNum) {
        boolean over = false;
        if (attackNum == 0) {
            over = true;
            totalResult.addDefenderWin();
            totalResult.addTotalDefendersLeft(defendNum);
            totalResult.addPossibility();
        } else if (defendNum == 0) {
            over = true;
            totalResult.addAttackerWin();
            totalResult.addTotalAttackersLeft(attackNum);
            totalResult.addPossibility();
        }
        return over;
    }
}

