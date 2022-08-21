package com.hui.springboot.poi;

import java.util.List;

/**
 * @author zhouhui
 * @Description desc
 * @date 2022-04-18 16:34
 */
public class Dto {
    private int level;
    private int sort;
    private Integer width;
    private String name;
    private List<Dto> children;
    private Integer cellStart = 0;
    private Integer cell = 0;
    private Integer cellEnd = 0;
    private Integer lineStart = 0;
    private Integer lineEnd = 0;

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Dto> getChildren() {
        return children;
    }

    public void setChildren(List<Dto> children) {
        this.children = children;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getCellStart() {
        return cellStart;
    }

    public void setCellStart(Integer cellStart) {
        this.cellStart = cellStart;
    }

    public Integer getCellEnd() {
        return cellEnd;
    }

    public void setCellEnd(Integer cellEnd) {
        this.cellEnd = cellEnd;
    }

    public Integer getLineStart() {
        return lineStart;
    }

    public void setLineStart(Integer lineStart) {
        this.lineStart = lineStart;
    }

    public Integer getLineEnd() {
        return lineEnd;
    }

    public void setLineEnd(Integer lineEnd) {
        this.lineEnd = lineEnd;
    }

    public Integer getCell() {
        return cell;
    }

    public void setCell(Integer cell) {
        this.cell = cell;
    }
}
