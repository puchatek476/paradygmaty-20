import scala.util.Random

class Matrix(val rowsNum: Int, val colNum: Int, rand: Boolean = false){
  val size = rowsNum * colNum
  var matrixArray = new Array[Int](size) // array memory reservation
  if(rand){
    matrixArray = Array.fill(rowsNum * colNum)(Random.between(0,2))
  }

  def this(newMatrixArr: Array[Int], rowsNum: Int, colNum: Int){
    this(rowsNum, colNum)
    matrixArray = newMatrixArr
  }

  def this(upperMtx: Matrix, lowerMtx: Matrix){ // composing matrix from two matrices, first is upper half
    this(upperMtx.rowsNum + lowerMtx.rowsNum, upperMtx.colNum)
    matrixArray = upperMtx.matrixArray ++ lowerMtx.matrixArray
  }

  def this(rowsNum: Int, rand: Boolean){ // creating square matrix of size rowsNum
    this(rowsNum, rowsNum, rand)
  }
  def getCol(colIndex: Int): Array[Int]={
    val colArray = Array.fill(rowsNum)(0)
    val colIndexes = Array.tabulate(rowsNum)((i: Int) => {
      var startIndex: Int = 0
      if(i == 0)
        startIndex = colIndex
      else
        startIndex = colIndex + i * colNum
      startIndex
    })
    var j = 0
    for(i <- colIndexes){
      colArray(j) = matrixArray(i)
      j += 1
    }
    colArray
  }

  def getRow(rowIndex: Int): Array[Int] ={
    matrixArray.slice(rowIndex * colNum, rowIndex * colNum + colNum)
  }

  def setAtIndex(i: Int, j: Int, elem: Int) = {
    matrixArray(i * colNum + j) = elem
  }

  def printMatrix()={
    for(i <- 0 to size - 1){
      print(matrixArray(i))
      if((i + 1) % colNum == 0)
        println()
      else
        print(", ")
    }
    println()
  }
}