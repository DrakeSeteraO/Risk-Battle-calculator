public class TotalResult {
    private int totalAttackerWins = 0;
    private int totalDefenderWins = 0;
    private int totalAttackersLeft = 0;
    private int totalDefendersLeft = 0;
    private int totalPossibilities = 0;

    public TotalResult() {
    }

    public void addAttackerWin() {
        totalAttackerWins++;
    }

    public void addDefenderWin() {
        totalDefenderWins++;
    }

    public int getTotalAttackerWins() {
        return totalAttackerWins;
    }

    public int getTotalDefenderWins() {
        return totalDefenderWins;
    }

    public double getAttackerWinsPercentage(){
        return (double) totalAttackerWins / (totalAttackerWins + totalDefenderWins);
    }

    public double getDefenderWinsPercentage(){
        return (double) totalDefenderWins / (totalAttackerWins + totalDefenderWins);
    }

    public double getAverageAttackersLeft() {
        return (double) totalAttackersLeft / totalPossibilities;
    }

    public void addTotalAttackersLeft(int totalAttackersLeft) {
        this.totalAttackersLeft += totalAttackersLeft;
    }

    public double getAverageDefendersLeft() {
        return (double) totalDefendersLeft / totalPossibilities;
    }

    public void addTotalDefendersLeft(int totalDefendersLeft) {
        this.totalDefendersLeft += totalDefendersLeft;
    }

    public void addPossibility(){
        totalPossibilities ++;
    }

    public int getTotalPossibilities() {
        return totalPossibilities;
    }

    public double getAverageTotalLeft() {
        return (double) (totalDefendersLeft + totalAttackersLeft) / totalPossibilities;
    }
}
