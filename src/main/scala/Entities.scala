package org.gmakris.scalasweeper

object Entities {
    case class Point(x: Int, y: Int)
    case class Tile(point: Point, isRevealed : Boolean, value: Int)
    case class Config(width: Int, height: Int, mines: Int)
    case class Board(config: Config, tiles: Array[Array[Tile]])
}