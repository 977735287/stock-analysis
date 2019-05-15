package per.san.common.utils;

import com.google.common.collect.Sets;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.apache.poi.ss.usermodel.FillPatternType.SOLID_FOREGROUND;

/**
 * description: excel工具类
 *
 * @author shencai.huang@hand-china.com
 * @date 12/10/2018 17:10
 * lastUpdateBy: shencai.huang@hand-china.com
 * lastUpdateDate: 12/10/2018
 */
public class ExcelUtils {

    private static final String EXCEL_XLS = ".xls";
    private static final String EXCEL_XLSX = ".xlsx";
    public static final String YEAR_MONTH_DAY_HH_MM_SS_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * description: 读取excel文件,默认文件格式为 .xlsx
     *
     * @param inputStream excel文件输入流
     * @return 以sheet页签名为键，页签的数据为值，数据以行为list存入map，以列为list存入行list
     */
    public static Map<String, List<List<Object>>> readExcel(InputStream inputStream) {
        return readExcel(EXCEL_XLSX, inputStream);
    }

    /**
     * description: 读取excel文件
     *
     * @param fileType    文件格式
     * @param inputStream excel文件输入流
     * @return 以sheet页签名为键，页签的数据为值，数据以行为list存入map，以列为list存入行list
     */
    public static Map<String, List<List<Object>>> readExcel(String fileType, InputStream inputStream) {
        return readExcel(fileType, inputStream, 1, (short) 1);
    }

    /**
     * description: 读取excel文件
     *
     * @param fileType    文件格式
     * @param inputStream excel文件输入流
     * @param startRow    开始读取的第几行
     * @param startColumn 开始读取的第几列
     * @return 以sheet页签名为键，页签的数据为值，数据以行为list存入map，以列为list存入行list
     */
    public static Map<String, List<List<Object>>> readExcel(String fileType, InputStream inputStream,
                                                            int startRow, short startColumn) {
        Map<String, List<List<Object>>> dataMap = new LinkedHashMap<>();
        int rowSize = 0;
        Workbook wb;
        Sheet st;
        Row row;
        Cell cell;
        try {
            if (EXCEL_XLS.equalsIgnoreCase(fileType)) {
                wb = new HSSFWorkbook(inputStream);
            } else if (EXCEL_XLSX.equalsIgnoreCase(fileType)) {
                wb = new XSSFWorkbook(inputStream);
            } else {
                return dataMap;
            }
            for (int sheetIndex = 0; sheetIndex < wb.getNumberOfSheets(); sheetIndex++) {
                List<List<Object>> outerList = new ArrayList<>();
                st = wb.getSheetAt(sheetIndex);
                String sheetName = st.getSheetName();
                for (int rowIndex = startRow - 1; rowIndex <= st.getLastRowNum(); rowIndex++) {
                    List<Object> innerList = new ArrayList();
                    row = st.getRow(rowIndex);
                    if (row == null) {
                        continue;
                    }
                    int tempRowSize = row.getLastCellNum() + 1;
                    if (tempRowSize > rowSize) {
                        rowSize = tempRowSize;
                    }
                    boolean hasValue = false;
                    for (short columnIndex = (short) (startColumn - 1); columnIndex < row.getLastCellNum(); columnIndex++) {
                        cell = row.getCell(columnIndex);
                        Object value = getFormatValue(cell);
                        if (columnIndex == startColumn && value == null) {
                            break;
                        }
                        innerList.add(value);
                        hasValue = true;
                    }
                    if (hasValue) {
                        outerList.add(innerList);
                    }
                }
                dataMap.put(sheetName, outerList);
            }
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataMap;
    }

    /**
     * description: 写excel文件
     *
     * @param dataMap 写入的数据
     * @return 文件的输出流
     */
    public static Workbook writeExcel(Map<String, List<List<Object>>> dataMap,
                                      Map<Integer, List<Short>> redIndexMap,
                                      Map<Integer, List<Short>> blueIndexMap) {
        Workbook wb = writeExcel(dataMap, null, EXCEL_XLSX);
        String sheetName = dataMap.keySet().iterator().next();
        CellStyle redStyle = wb.createCellStyle();
        CellStyle blueStyle = wb.createCellStyle();
        Sheet st = wb.getSheet(sheetName);
        Font redFont = wb.createFont();
        Font blueFont = wb.createFont();
        redFont.setColor(Font.COLOR_RED);
        redStyle.setFont(redFont);
        blueFont.setColor(IndexedColors.BLUE.index);
        blueStyle.setFont(blueFont);
        setFontStyle(redIndexMap, redStyle, st);
        setFontStyle(blueIndexMap, blueStyle, st);
        return wb;
    }

    private static void setFontStyle(Map<Integer, List<Short>> redIndexMap, CellStyle redStyle, Sheet st) {
        for (Integer redIndex : redIndexMap.keySet()) {
            Row row = st.getRow(redIndex);
            for (Short columnIndex : redIndexMap.get(redIndex)) {
                Cell cell = row.getCell(columnIndex);
                if (cell == null) {
                    cell = row.createCell(columnIndex);
                }
                cell.setCellStyle(redStyle);
            }
        }
    }

    /**
     * description: 写excel文件
     *
     * @param dataMap 写入的数据
     * @return 文件的输出流
     */
    public static Workbook writeExcel(Map<String, List<List<Object>>> dataMap, List<Integer> columnWidths) {
        return writeExcel(dataMap, columnWidths, EXCEL_XLSX);
    }

    /**
     * description: 写excel文件
     *
     * @param dataMap     写入的数据
     * @param startSheet  从第几个sheet开始写入
     * @param startRow    从第几行开始写入
     * @param startColumn 从第几列开始写入
     * @return 文件的输出流
     */
    public static Workbook writeExcel(Map<String, List<List<Object>>> dataMap, List<Integer> columnWidths, int startSheet, int startRow, short startColumn) {
        return writeExcel(dataMap, columnWidths, EXCEL_XLSX, startSheet, startRow, startColumn);
    }

    /**
     * description: 写excel文件
     *
     * @param dataMap  写入的数据
     * @param fileType 文件类型
     * @return 文件的输出流
     */
    public static Workbook writeExcel(Map<String, List<List<Object>>> dataMap, List<Integer> columnWidths, String fileType) {
        return writeExcel(dataMap, columnWidths, fileType, 1, 1, (short) 1);
    }

    /**
     * description: 写excel文件
     *
     * @param dataMap     写入的数据
     * @param fileType    文件类型
     * @param startSheet  从第几个sheet开始写入
     * @param startRow    从第几行开始写入
     * @param startColumn 从第几列开始写入
     * @return 文件的输出流
     */
    public static Workbook writeExcel(Map<String, List<List<Object>>> dataMap, List<Integer> columnWidths,
                                      String fileType, int startSheet, int startRow, short startColumn) {
        Workbook wb = null;
        Sheet st;
        Set<Integer> fixedColumnSet = Sets.newHashSet();
        Set<Integer> columnSet = Sets.newHashSet();
        try {
            if (EXCEL_XLS.endsWith(fileType)) {
                wb = new HSSFWorkbook();
            } else if (EXCEL_XLSX.endsWith(fileType)) {
                wb = new XSSFWorkbook();
            } else {
                wb = new XSSFWorkbook();
            }
            int emptySheet = startSheet;
            while (startSheet > 1) {
                wb.createSheet("Sheet" + (emptySheet - startSheet + 1));
                startSheet--;
            }
            for (String name : dataMap.keySet()) {
                st = wb.createSheet(name);
                List<List<Object>> dataList = dataMap.get(name);
                for (int i = startRow - 1; i < dataList.size(); i++) {
                    Row row = st.createRow(i + startRow - 1);
                    List<Object> valueList = dataList.get(i);
                    for (int j = startColumn - 1; j < valueList.size(); j++) {
                        columnSet.add(j);
                        Object value = valueList.get(j);
                        if (value instanceof String && ((String) value).length() > 15) {
                            fixedColumnSet.add(j);
                        }
                        Cell cell = row.createCell(j + startColumn - 1);
                        if (value instanceof Integer) {
                            cell.setCellValue((Integer) value);
                        } else if (value instanceof String) {
                            cell.setCellValue((String) value);
                        } else if (value instanceof Double) {
                            cell.setCellValue((Double) value);
                        } else if (value instanceof Float) {
                            cell.setCellValue((Float) value);
                        } else if (value instanceof Long) {
                            cell.setCellValue((Long) value);
                        } else if (value instanceof Boolean) {
                            cell.setCellValue((Boolean) value);
                        } else if (value instanceof Date) {
                            SimpleDateFormat sdf = new SimpleDateFormat(YEAR_MONTH_DAY_HH_MM_SS_FORMAT);
                            cell.setCellValue(sdf.format((Date) value));
                        }
                    }
                }
                if (columnWidths != null && !columnWidths.isEmpty()) {
                    for (int i = 0; i < columnWidths.size(); i++) {
                        st.setColumnWidth(i, columnWidths.get(i) * 256);
                    }
                }else {
                    if (!columnSet.isEmpty()) {
                        for (Integer i : columnSet) {
                            if (fixedColumnSet.contains(i)) {
                                st.setColumnWidth(i, 30 * 256);
                            }else {
                                // 自适应宽度
                                st.autoSizeColumn(i, true);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return wb;
    }

    /**
     * description: 获取数据
     *
     * @param cell 单元格
     * @return 单元格的数据
     */
    private static Object getFormatValue(Cell cell) {
        Object value = null;
        if (cell != null) {
            switch (cell.getCellType()) {
                case STRING:
                    value = rightTrim(cell.getStringCellValue());
                    break;
                case NUMERIC:
                    if (HSSFDateUtil.isCellDateFormatted(cell)) {
                        value = cell.getDateCellValue();
                    } else {
                        value = cell.getNumericCellValue();
                    }
                    break;
                case FORMULA:
                    // 导入时如果为公式生成的数据则无值
                    if (!cell.getStringCellValue().equals("")) {
                        value = cell.getStringCellValue();
                    } else {
                        value = cell.getNumericCellValue() + "";
                    }
                    break;
                case BLANK:
                    break;
                case ERROR:
                    value = null;
                    break;
                case BOOLEAN:
                    value = cell.getBooleanCellValue();
                    break;
                default:
                    value = null;
            }
        }
        return value;
    }

    /**
     * 去掉字符串右边的空格
     *
     * @param str 要处理的字符串
     * @return 处理后的字符串
     */

    public static String rightTrim(String str) {
        if (str == null) {
            return "";
        }
        int length = str.length();
        for (int i = length - 1; i >= 0; i--) {
            if (str.charAt(i) != 0x20) {
                break;
            }
            length--;
        }
        return str.substring(0, length);
    }

//    public static void main(String[] args) throws IOException {
////        File file1 = new File("C:\\Users\\97773\\Desktop\\test.xlsx");
////        Map<String, List<List<Object>>> dataMap = readExcel(file1.getName().substring(file1.getName().lastIndexOf(".")),
////                new FileInputStream(file1.getAbsolutePath()), 2, (short) 2);
////        File file = new File("C:\\Users\\97773\\Desktop\\test2.xlsx");
////        if (!file.exists()) {
////            file.createNewFile();
////        }
////        OutputStream outputStream = new FileOutputStream(file);
////        writeExcel(dataMap, null).write(outputStream);
////        outputStream.close();
////        System.out.println(dataMap);
//        String a = "-0.20";
//        Double b = Double.parseDouble(a);
//        DecimalFormat df = new DecimalFormat("0.00");
//        System.out.println(df.format(b));
//        if (b < 0) {
//            System.out.println(df.format(b) + "  < 0");
//        }
//    }
}
