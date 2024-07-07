package lk.ijse.dao;

import lk.ijse.entity.tm.AddsTm;

import java.sql.SQLException;
import java.util.List;

public interface AddsDAO {
    public  List<AddsTm> getAllAddsTm() throws SQLException ;
}
