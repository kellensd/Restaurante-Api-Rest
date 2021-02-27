package votacao.api.rest.utils;

import votacao.api.rest.common.exceptions.UnknownSQLException;
import org.springframework.jdbc.core.JdbcTemplate;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class ExtractResultDatabase {

    public static List<Map<String, String>> getListMapDeDados(JdbcTemplate jtm, String sql){
        List<Map<String, String>> rows = new ArrayList<>();
        ResultSet resultSet = null;
        try {
            resultSet = jtm.getDataSource().getConnection().prepareStatement(sql).executeQuery();
            int qtdColumns = resultSet.getMetaData().getColumnCount();

            while(resultSet.next()) {
                Map<String, String> cols = new HashMap<>();
                for(int col = 1; col <= qtdColumns; col++) {
                    cols.put(resultSet.getMetaData().getColumnLabel(col), resultSet.getString(col));
                }
                rows.add(cols);
            }
        } catch (SQLException e) {
            return (List<Map<String, String>>) new UnknownSQLException("Erro ao extrair dados do banco! " + e.getMessage());
        }
        return rows;
    }
}
