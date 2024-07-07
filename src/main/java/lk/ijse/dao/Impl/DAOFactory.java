package lk.ijse.dao.Impl;

import lk.ijse.dao.SuperDAO;

public class DAOFactory {

    private  static DAOFactory daoFactory;

    private DAOFactory(){

    }
    public static DAOFactory getDaoFactory(){
        if(daoFactory==null){
            daoFactory=new DAOFactory();
        }
        return daoFactory;
    }

    public SuperDAO getDAO(DAOTypes daoTypes){
        switch (daoTypes){
            case Condemned :
              return   new CondemnedDAOImpl();
            case Order:
              return   new OrdersDAOImpl();
            case Employee:
              return   new EmployeeDAOImpl();
            case Equipment:
               return new EquipmentDAOImpl();
            case EquipmentDetails:
              return   new EquipmentDetailsDAOImpl();
            case Maintenance:
               return new MaintenanceDAOImpl();
            case OrederDetail:
              return   new OrderDetailDAOImpl();
            case Payment:
               return new PaymentDAOImpl();
            case Query:
             return    new QueryDAOImpl();
            case Spareparts:
               return new SparepartsDAOImpl();
            case Supplier:
             return    new SupplierDAOImpl();
            case User:
                return new UserDAOImpl();
        }
        return null;
    }
}
