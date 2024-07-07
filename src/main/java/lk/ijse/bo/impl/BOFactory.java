package lk.ijse.bo.impl;

import lk.ijse.bo.SuperBO;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory(){

    }

    public static BOFactory getBoFactory(){
        if(boFactory==null){
            boFactory=new BOFactory();
        }
        return boFactory;
    }

    public SuperBO getBO(BOTypes boTypes){
        switch (boTypes){
            case Condemnedbo :
             return  new CondemnedBOImpl();
            case Employeebo:
              return  new EmployeeBOImpl();
            case Equipmentbo:
              return  new EquipmentBOImpl();
            case Maintenancebo:
            return    new MaintenanaceBOImpl();
            case Orderbo:
             return   new PlaceOrderBOImpl();
            case Paymentbo:
              return  new PaymentBOImpl();
            case Sparepartsbo:
             return   new SparepartsBOImpl();
            case Supplierbo:
              return  new SupplierBOImpl();
            case Querybo:
                return new QueryBOImpl();
            case userbo:
                return new UserBOImpl();
        }
        return null;
    }
}
