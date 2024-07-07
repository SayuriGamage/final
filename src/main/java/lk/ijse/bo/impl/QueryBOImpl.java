package lk.ijse.bo.impl;

import lk.ijse.bo.QueryBO;
import lk.ijse.bo.SuperBO;
import lk.ijse.dao.Impl.DAOFactory;
import lk.ijse.dao.Impl.DAOTypes;
import lk.ijse.dao.QueryDAO;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class QueryBOImpl implements QueryBO, SuperBO {
    QueryDAO queryDAO= (QueryDAO) DAOFactory.getDaoFactory().getDAO(DAOTypes.Query);

    public PreparedStatement getQuery() throws SQLException {
      return queryDAO.getQuery();

    }

}
