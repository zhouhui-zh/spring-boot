package com.hui.springboot.poi;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 树形结构数据导出excel工具
 * <p>
 * Created by lzy on 2021/2/24 14:09
 */
@SuppressWarnings("ALL")
public class Tree2ExcelUtil {

    /**
     * 标识最大列
     */
    private static int maxCol = 0;
    private static String lableName = "lable";
    private static String childrenName = "children";
    private static final String COL = "col";
    private static final String ROW = "row";
    private static final String ROW_OFT = "rowOft";
    private static final String ROW_SIZE = "rowSize";


    public static void main(String[] args) {

        String jsonStr = "{\"name\":\"aaa\",\"children\":[{\"name\":\"bbb\",\"children\":[{\"name\":\"eee\"},{\"name\":\"fff\",\"children\":[{\"name\":\"iii\"},{\"name\":\"jjj\",\"children\":[{\"name\":\"qqq\"},{\"name\":\"ttt\"}]}]},{\"name\":\"www\"}]},{\"name\":\"ccc\",\"children\":[{\"name\":\"ggg\"},{\"name\":\"hhh\",\"children\":[{\"name\":\"kkk\",\"children\":[{\"name\":\"ttt\"},{\"name\":\"mmm\"}]},{\"name\":\"uuu\"}]},{\"name\":\"ooo\"}]},{\"name\":\"ddd\",\"children\":[{\"name\":\"ggg\"},{\"name\":\"hhh\",\"children\":[{\"name\":\"kkk\"},{\"name\":\"uuu\"}]}]}]}";

        System.out.println(jsonStr);
        jsonStr = JSON.toJSONString(DtoTest.intDtos().get(0));
        System.out.println(jsonStr);
        Map tree = JSONObject.parseObject(jsonStr, Map.class);
        String filePath = "F:\\Users\\zh\\Desktop\\tree.xls";
        if (StringUtils.isBlank(filePath)) {
            System.err.println("文件名称不能为空");
            return;
        }
        tree2Excel(tree, filePath, "name", "children");
    }

    /**
     * 树形结构数据生成excel文件
     *
     * @param tree     树形数据
     * @param filePath 文件路径
     * @return
     */
    public static boolean tree2Excel(Map tree, String filePath) {
        return tree2Excel(tree, filePath, null, null);
    }

    /**
     * 树形结构数据生成excel文件
     *
     * @param tree         树形数据
     * @param filePath     文件路径
     * @param lableName    标签Key名称
     * @param childrenName 子节点Key名称
     * @return
     */
    public static boolean tree2Excel(Map tree, String filePath, String lableName, String childrenName) {
        if (isBlank(filePath)) {
            System.err.println("文件名称不能为空");
            return false;
        }
        try {
            doSame(tree, lableName, childrenName);
            createExcel(filePath, tree);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 树形结构数据生成Workbook对象
     *
     * @param tree    树形数据
     * @param fileSuf 文件后缀，xls/xlsx
     * @return
     */
    public static Workbook tree2Worbook(Map tree, String fileSuf) {
        return tree2Worbook(tree, fileSuf, null, null);
    }

    /**
     * 树形结构数据生成Workbook对象
     *
     * @param tree         树形数据
     * @param fileSuf      文件后缀，xls/xlsx
     * @param lableName    标签Key名称
     * @param childrenName 子节点Key名称
     * @return
     */
    public static Workbook tree2Worbook(Map tree, String fileSuf, String lableName, String childrenName) {
        if (isBlank(fileSuf)) {
            System.err.println("必须指定文件后缀");
            return null;
        }
        try {
            doSame(tree, lableName, childrenName);
            return procesData(tree, fileSuf);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    //具体实现


    private static void doSame(Map tree, String lableName, String childrenName) {
        if (!isBlank(lableName)) {
            Tree2ExcelUtil.lableName = lableName;
        }
        if (!isBlank(childrenName)) {
            Tree2ExcelUtil.childrenName = childrenName;
        }
        coreAlgoCol(tree, 1);
        coreAlgoRow(tree);
    }

    /**
     * 主要算法，计算列的坐标，计算每个节点所占行
     *
     * @param tree  数据
     * @param col   递增的列
     * @param trees 把高级别向下传递计算递增的行高
     */
    private static void coreAlgoCol(Map tree, int col, Map... trees) {
        tree.put(COL, col);
        Object childrenObj = tree.get(childrenName);
        if (childrenObj != null) {
            List<Map> children = (List<Map>) childrenObj;
            if (children.size() > 0) {
                int size = children.size() * 2 - 1;
                tree.put(ROW_SIZE, size);
                int len = trees != null ? trees.length + 1 : 1;
                Map[] arrData = new Map[len];

                if (trees != null && trees.length > 0) {
                    for (int i = 0; i < trees.length; i++) {
                        Map tree1 = trees[i];
                        tree1.put(ROW_SIZE, toInt(tree1.get(ROW_SIZE), 1) + size - 1);
                        arrData[i] = tree1;
                    }
                }
                arrData[len - 1] = tree;
                for (Map tree1 : children) {
                    int newCol = col + 1;
                    if (newCol > maxCol) {
                        maxCol = newCol;
                    }
                    coreAlgoCol(tree1, newCol, arrData);
                }
            }
        }
    }

    /**
     * 主要算法，计算行的坐标
     *
     * @param tree
     */
    private static void coreAlgoRow(Map tree) {
        if (toInt(tree.get(ROW)) == 0) {
            tree.put(ROW, Math.round(toInt(tree.get(ROW_SIZE), 1) / 2.0f));
        }
        Object childrenObj = tree.get(childrenName);
        if (childrenObj != null) {
            List<Map> children = (List<Map>) childrenObj;
            if (children.size() > 0) {
                int tempOft = toInt(tree.get(ROW_OFT));
                for (Map tree1 : children) {
                    int rowSize = toInt(tree1.get(ROW_SIZE), 1);
                    tree1.put(ROW_OFT, tempOft);
                    tree1.put(ROW, tempOft + Math.round(rowSize / 2.0f));
                    tempOft += rowSize + 1;
                    coreAlgoRow(tree1);
                }
            }
        }
    }

    /**
     * 创建excel文件
     *
     * @param filePath 文件路径，具体路径到文件名
     * @param tree     数据
     * @throws IOException
     */
    private static void createExcel(String filePath, Map tree) throws IOException {
        File file = new File(filePath);
        boolean bfile = file.createNewFile();
        // 复制模板到新文件
        if (bfile) {
            Workbook wk = procesData(tree, filePath);
            if (wk != null) {
                FileOutputStream fos = null;
                try {
                    fos = new FileOutputStream(file);
                    wk.write(fos);

                    fos.flush();
                } finally {
                    closeStream(fos);
                    wk.close();
                }
            }
        }
    }


    /**
     * 处理excel数据
     *
     * @param tree 数据
     * @return 工作表对象
     */
    private static Workbook procesData(Map tree, String fileName) {

        Workbook wk = null;
        if (fileName.endsWith("xls")) {
            wk = new HSSFWorkbook();
        }
        if (fileName.endsWith("xlsx")) {
            wk = new XSSFWorkbook();
        }
        if (wk == null) {
            System.err.println("文件名称不正确");
            return null;
        }

        //创建一个sheet页
        Sheet sheet = wk.createSheet("Sheet1");

        int colSize = maxCol * 2 + 2;
        int rowSize = toInt(tree.get(ROW_SIZE), 1);
        for (int i = 0; i <= rowSize; i++) {
            Row row = sheet.createRow(i);
            for (int j = 0; j <= colSize; j++) {
                row.createCell(j);
            }
        }
        //配置单元格背景色
        CellStyle style1 = wk.createCellStyle();
        style1.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
        style1.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        CellStyle style2 = wk.createCellStyle();
        style2.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
        style2.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        dealCell(sheet, tree, style1, style2);

        return wk;
    }

    /**
     * 根据计算好的坐标填充每一个单元格
     *
     * @param sheet  #
     * @param tree   数据
     * @param style1 单元格格式
     * @param style2 单元格格式
     */
    private static void dealCell(Sheet sheet, Map tree, CellStyle style1, CellStyle style2) {
        Row row = sheet.getRow(toInt(tree.get(ROW)));
        int oftCol = (toInt(tree.get(COL)) - 1) * 2 + 1;
        Cell cell = row.getCell(oftCol);
        cell.setCellStyle(style1);
        cell.setCellValue(String.valueOf(tree.get(lableName)));

        sheet.setColumnWidth(oftCol, 256 * 20);

        Object childrenObj = tree.get(childrenName);
        if (childrenObj != null) {
            List<Map> children = (List<Map>) childrenObj;
            if (children.size() > 0) {
                int size = children.size();

                int startRow = toInt(children.get(0).get(ROW));
                int endRow = toInt(children.get(size - 1).get(ROW));
                int col = oftCol + 1;
                sheet.setColumnWidth(col, 256);
                for (; startRow <= endRow; startRow++) {
                    sheet.getRow(startRow).getCell(col).setCellStyle(style2);
                }

                for (Map child : children) {
                    dealCell(sheet, child, style1, style2);
                }
            }
        }
    }

    private static int toInt(Object val) {
        return toInt(val, 0);
    }

    private static int toInt(Object val, Integer defVal) {
        try {
            return Integer.parseInt(String.valueOf(val));
        } catch (NumberFormatException ignored) {
        }
        return defVal;
    }

    private static boolean isBlank(String str) {
        return str == null || str.trim().length() == 0;
    }

    /**
     * 关闭流
     *
     * @param closeables 不定长数组 流对象
     */
    public static void closeStream(Closeable... closeables) {
        for (Closeable closeable : closeables) {
            if (closeable != null) {
                try {
                    closeable.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}