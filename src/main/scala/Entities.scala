package org.gmakris.scalasweeper

object Entities {
    case class Point(x: Int, y: Int)
    case class Tile(point: Point, isRevealed : Boolean, value: Int)
    case class Board(width: Int, height: Int, mines: Int, tiles: Array[Tile])
}