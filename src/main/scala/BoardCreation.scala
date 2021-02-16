package org.gmakris.scalasweeper

import Entities.Point

import scala.util.{Random, Try}

object BoardCreation {

    // ===================================================================
    // Core
    // ===================================================================

    // TODO: make it work
    def create(width: Int, height: Int, mines: Int): Array[Int] = {
        val minePoints = createMinePoints(width, height, mines)
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

    // TODO: make it work
    def createMinePoints(width: Int, height: Int, mines: Int): Set[Point] =
        Iterator.unfold(Set.empty[Try[Point]]) {
            points => randomPoint(width, height) + points
        }
            .takeWhile(points => points.size < mines)



}
