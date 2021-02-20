package org.gmakris.scalasweeper

import Entities.{Config, Point, Tile}

import scala.util.{Random, Try}

object BoardCreation {

    // -------------------------------------------------------------------
    // Core
    // -------------------------------------------------------------------

    def create(config: Config): Try[Array[Tile]] = {
        for {
            mines <- minePoints(config)
            points = boardPoints(config.width, config.height)
        } yield points
            .map(point =>
                if (mines.contains(point))
                    Tile(point, isRevealed = false, -1)
                else
                    Tile(
                        point,
                        isRevealed = false,
                        mines.count(isAdjacent(point))))
            .toArray
    }

    def boardPoints(width: Int, height: Int): Set[Point] =
        (0 until (width * height))
            .map(index => (
                index % width,
                index / width))
            .map(rowAndColumn => Point(rowAndColumn._1, rowAndColumn._2))
            .toSet

    def isAdjacent(a: Point): Point => Boolean =
        b => Math.abs(a.x - b.x) <= 1 && Math.abs(a.y - b.y) <= 1

    // -------------------------------------------------------------------
    // Randomness
    // -------------------------------------------------------------------

    val rnd = new Random(System.currentTimeMillis())

    def randomValue(max: Int): Try[Int] =
        Try(rnd.between(0, max))

    def randomPoint(maxX: Int, maxY: Int): Try[Point] =
        for {
            x <- randomValue(maxX)
            y <- randomValue(maxY)
        } yield Point(x, y)

    // -------------------------------------------------------------------
    // Mines
    // -------------------------------------------------------------------

    def createMinePoints(config: Config, mines: Set[Point]): Try[Set[Point]] =
        if (mines.size == config.mines)
            Try(mines)
        else (for {
            mine <- randomPoint(config.width, config.height)
            newState = mines + mine
        } yield createMinePoints(config, newState))
            .map(_.get)

    def minePoints(config: Config): Try[Set[Point]] =
        createMinePoints(config, Set.empty)

    // -------------------------------------------------------------------
    // Tiles
    // -------------------------------------------------------------------


}
