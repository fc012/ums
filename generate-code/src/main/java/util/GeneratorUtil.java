package util;

import cn.org.rapid_framework.generator.provider.db.table.model.Column;
import cn.org.rapid_framework.generator.provider.db.table.model.Table;

import java.util.LinkedHashSet;
import java.util.List;

/**
 * Created by admin on 2016/3/7.
 */
public class GeneratorUtil {

    public static void removeTablePrefix(List<Table> tables, String[] prefixArr) {

        for (Table table : tables) {
            removeTablePrefix(table, prefixArr);
        }
    }

    public static void removeTablePrefix(Table table, String[] prefixArr) {
        String sqlName = "";
        String className = "";
        for (String prefix : prefixArr) {
            sqlName = table.getSqlName();
            className = table.getClassName();
            if (sqlName.split("_")[0].equals(prefix)) {
                //添加car前缀
                table.setClassName(StringUtil
                    .captureName(className.replaceFirst(StringUtil.captureName(prefix), "")));
                //对应字段映射去除相应的前缀
                removeColumnPrefix(table.getColumns(), prefixArr);
            }
        }
    }

    public static void removeColumnPrefix(Column column, String[] prefixArr) {
        String sqlName = "";
        String columnName = "";
        for (String prefix : prefixArr) {
            sqlName = column.getSqlName();
            columnName = column.getColumnName();
            if (sqlName.split("_")[0].equals(prefix)) {
                column.setColumnName(StringUtil
                    .captureName(columnName.replaceFirst(StringUtil.captureName(prefix), "")));
            }
        }
    }

    public static void removeColumnPrefix(LinkedHashSet<Column> columnSet, String[] prefixArr) {
        for (Column column : columnSet) {
            removeColumnPrefix(column, prefixArr);
        }
    }
}
