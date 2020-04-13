package others

import org.apache.poi.ss.usermodel.CellType
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.FileInputStream

data class dateData(var coAvg: Double = 0.0, var bc: Double = 0.0)

fun main() {
    val dataList = ArrayList<dateData>()
    val excelFile = FileInputStream("C:/Users/dodri/Documents/카카오톡 받은 파일/dataSheet.xlsx")
    val workBook = XSSFWorkbook(excelFile)
    val sheet = workBook.getSheetAt(0)

    val rows = sheet.physicalNumberOfRows
    for(rowIndex in 1..rows) {
        val row = sheet.getRow(rowIndex)
        val tmp = dateData()
        if(row != null) {
            val cells = row.physicalNumberOfCells
            for(columnIndex in 0..cells) {
                val cell = row.getCell(columnIndex)
                if(cell != null) {
                    when(columnIndex) {
                        0 -> {
                            val date = cell.numericCellValue.toInt()

                        }
                        1 -> tmp.coAvg = cell.numericCellValue
                        2 -> tmp.bc = cell.numericCellValue
                    }
                }
            }
            dataList.add(tmp)
            println(tmp.toString())
        }
    }
}