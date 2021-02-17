package org.gmakris.scalasweeper

import Entities.{Config, Point}

import scala.util.{Random, Try}

object BoardCreation {

    // ===================================================================
    // Core
    // ===================================================================

    // TODO: make it work
    def create(config: Config): Array[Int] = {
        val minePoints = createMinePoints(config)
        /*
        (0 until (width * height))
            .toArray
            .map(index =>)
                         */
    }

    // ===================================================================
    // Randomness
    // ===================================================================

    val rnd = new Random(System.currentTimeMillis())

    def randomValue(max: Int): Try[Int] =
        Try(rnd.between(0, max))

    def randomPoint(maxX: Int, maxY: Int): Try[Point] =
        for {
            x <- randomValue(maxX)
            y <- randomValue(maxY)
        } yield Point(x, y)

    // ===================================================================
    // Mines
    // ===================================================================

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


}
