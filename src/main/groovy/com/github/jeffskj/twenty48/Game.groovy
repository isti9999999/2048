package com.github.jeffskj.twenty48

class Game {
    Board board
    boolean over = false
    boolean won = false
    int score = 0
    
    private Random rand = new Random()
    
    Game(int startTiles=2, int size=4) {
        board = new Board(size)
        startTiles.times { addRandomTile() }
    }
    
    MoveResult move(Direction dir) {
        if (this.over || this.won) return
        
        def result = board.move(dir)
        
        if (board.maxValue == 2048) { won = true }
        
        if (result.moved) {
            addRandomTile()
            if (!board.moveAvailable) { over = true }
            score += result.scores                        
        }
        
        return result
    }
    
    void addRandomTile() {
        def avail = board.availableTiles
        board.set(avail[rand.nextInt(avail.size())], rand.nextInt(10) == 0 ? 4 : 2) 
    }
    
    int getMaxTile() {
        return board.maxValue
    }
}
