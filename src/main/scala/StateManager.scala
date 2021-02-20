package org.gmakris.scalasweeper

import Entities.Board
import StateManager.GameStateSpace.GameState

object StateManager {

    // -------------------------------------------------------------------
    // End Condition
    // -------------------------------------------------------------------

    object GameStateSpace extends Enumeration {
        type GameState = Value

        val WON: GameState = Value("won")
        val LOST: GameState = Value("lost")
        val NOT_OVER: GameState = Value("not over")
    }

    def isGameOver(board: Board): GameState =
        if (isGameLost(board))
            GameStateSpace.LOST
        else if (isGameWon(board))
            GameStateSpace.WON
        else
            GameStateSpace.NOT_OVER

    def isGameWon(board: Board): Boolean =
        board
            .tiles
            .flatten
            .count(tile => !tile.isRevealed && tile.value >= 0) == 0

    def isGameLost(board: Board): Boolean =
        board
            .tiles
            .flatten
            .count(tile => tile.isRevealed && tile.value == -1) > 0

    // -------------------------------------------------------------------
    // Action
    // -------------------------------------------------------------------

    object GameActionSpace extends Enumeration {
        type GameAction = Value

        val RESTART: GameAction = Value(0)
        val REVEAL: GameAction = Value(1)
        val EXIT: GameAction = Value(2)
    }

}
