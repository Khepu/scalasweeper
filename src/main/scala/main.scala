package org.gmakris.scalasweeper

import BoardCreation.createBoard
import Entities.{Config, Tile}

object main {
    def boardToString(tiles: Array[Array[Tile]]): String =
        tiles
            .map(row => row
                .map(tile =>
                    if (!tile.isRevealed)
                        " "
                    else if (tile.value == -1)
                        "*"
                    else
                        tile.value.toString))
            .map(_.mkString("|"))
            .mkString("\n")

    def main(args: Array[String]): Unit = {
        val config = Config(10, 15, 10)

        for {
            board <- createBoard(config)
            tiles = board.tiles
        } yield print(boardToString(tiles))
    }
}
