package com.example.mazesolvergame.data.maze

data class Maze(
    val rows: Int,
    val cols: Int,
    val grid: List<List<MazeCell>>
) {
    fun neighbors(cell: MazeCell): List<MazeCell> {
        val list = mutableListOf<MazeCell>()
        val r = cell.row
        val c = cell.col

        if (!cell.topWall && r > 0) list.add(grid[r - 1][c])
        if (!cell.bottomWall && r < rows - 1) list.add(grid[r + 1][c])
        if (!cell.leftWall && c > 0) list.add(grid[r][c - 1])
        if (!cell.rightWall && c < cols - 1) list.add(grid[r][c + 1])

        return list
    }
}