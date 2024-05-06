create database gdse69;

use gdse69;


CREATE TABLE supplier (
                          sup_id VARCHAR(100) PRIMARY KEY,
                          name VARCHAR(100)
);

CREATE TABLE orders (
                        or_id VARCHAR(100) PRIMARY KEY,
                        order_date VARCHAR(100),
                        sup_id VARCHAR(100),
                        FOREIGN KEY (sup_id) REFERENCES supplier(sup_id)
);


CREATE TABLE payment (
                         pay_id VARCHAR(100) PRIMARY KEY,
                         or_id VARCHAR(100),
                         date VARCHAR(100),
                         amount DOUBLE (10,2),
                         FOREIGN KEY (or_id) REFERENCES orders(or_id)

);


CREATE TABLE user (
                      user_id VARCHAR(100) PRIMARY KEY,
                      name VARCHAR(100),
                      password VARCHAR(255)
);

CREATE TABLE equipment (
                           eq_id VARCHAR(100) PRIMARY KEY,
                           name VARCHAR(100),
                           model VARCHAR(100),
                           cost DOUBLE(10, 2),
                           purchase VARCHAR(100),
                           warranty VARCHAR(100),
                           manufacture VARCHAR(100),
                           user_id VARCHAR(100),
                           FOREIGN KEY (user_id) REFERENCES user(user_id)
);

CREATE TABLE employee (
                          emp_id VARCHAR(100) PRIMARY KEY,
                          name VARCHAR(100),
                          job_title VARCHAR(100),
                          tel VARCHAR(100)
);

CREATE TABLE maintenance (
                             mm_id VARCHAR(100) PRIMARY KEY,
                             date VARCHAR(100),
                             description VARCHAR(50),
                             cost DOUBLE(10, 2),
                             emp_id VARCHAR(100),
                             FOREIGN KEY (emp_id) REFERENCES employee(emp_id)

);

CREATE TABLE spareparts (
                            sp_id VARCHAR(100) PRIMARY KEY,
                            name VARCHAR(100),
                            manufacture VARCHAR(100),
                            cost DOUBLE(10, 2),
                            qty INT(50),
                            purchase VARCHAR(100),
                            mm_id VARCHAR(100),
                            FOREIGN KEY (mm_id) REFERENCES maintenance(mm_id)
);

CREATE TABLE condemn (
                         c_id VARCHAR(100) PRIMARY KEY,
                         details VARCHAR(40),
                         date VARCHAR(100),
                         mm_id VARCHAR(100),
                         FOREIGN KEY (mm_id) REFERENCES maintenance(mm_id)
);

CREATE TABLE maintenance_equipment_details (
                                               type VARCHAR(100),

                                               mm_id VARCHAR(100),
                                               eq_id VARCHAR(100),
                                               FOREIGN KEY (mm_id) REFERENCES maintenance(mm_id),
                                               FOREIGN KEY (eq_id) REFERENCES equipment(eq_id)
);

CREATE TABLE order_details (
                               or_id VARCHAR(100),
                               sp_id VARCHAR(100),
                               qty INT(50),
                               cost DOUBLE(10, 2),
                               FOREIGN KEY (or_id) REFERENCES orders(or_id),
                               FOREIGN KEY (sp_id) REFERENCES spareparts(sp_id)
);


-- Generate 20 values for supplier table
INSERT INTO supplier (sup_id, name) VALUES
                                        ('SUP001', 'Supplier 1'),
                                        ('SUP002', 'Supplier 2'),
                                        ('SUP003', 'Supplier 3'),
                                        ('SUP004', 'Supplier 4'),
                                        ('SUP005', 'Supplier 5'),
                                        ('SUP006', 'Supplier 6'),
                                        ('SUP007', 'Supplier 7'),
                                        ('SUP008', 'Supplier 8'),
                                        ('SUP009', 'Supplier 9'),
                                        ('SUP010', 'Supplier 10'),
                                        ('SUP011', 'Supplier 11'),
                                        ('SUP012', 'Supplier 12'),
                                        ('SUP013', 'Supplier 13'),
                                        ('SUP014', 'Supplier 14'),
                                        ('SUP015', 'Supplier 15'),
                                        ('SUP016', 'Supplier 16'),
                                        ('SUP017', 'Supplier 17'),
                                        ('SUP018', 'Supplier 18'),
                                        ('SUP019', 'Supplier 19'),
                                        ('SUP020', 'Supplier 20');

-- Generate 20 values for orders table
INSERT INTO orders (or_id, order_date, sup_id) VALUES
                                                   ('ORD001', '2024-05-03', 'SUP001'),
                                                   ('ORD002', '2024-05-04', 'SUP002'),
                                                   ('ORD003', '2024-05-05', 'SUP003'),
                                                   ('ORD004', '2024-05-06', 'SUP004'),
                                                   ('ORD005', '2024-05-07', 'SUP005'),
                                                   ('ORD006', '2024-05-08', 'SUP006'),
                                                   ('ORD007', '2024-05-09', 'SUP007'),
                                                   ('ORD008', '2024-05-10', 'SUP008'),
                                                   ('ORD009', '2024-05-11', 'SUP009'),
                                                   ('ORD010', '2024-05-12', 'SUP010'),
                                                   ('ORD011', '2024-05-13', 'SUP011'),
                                                   ('ORD012', '2024-05-14', 'SUP012'),
                                                   ('ORD013', '2024-05-15', 'SUP013'),
                                                   ('ORD014', '2024-05-16', 'SUP014'),
                                                   ('ORD015', '2024-05-17', 'SUP015'),
                                                   ('ORD016', '2024-05-18', 'SUP016'),
                                                   ('ORD017', '2024-05-19', 'SUP017'),
                                                   ('ORD018', '2024-05-20', 'SUP018'),
                                                   ('ORD019', '2024-05-21', 'SUP019'),
                                                   ('ORD020', '2024-05-22', 'SUP020');

-- Generate 20 values for payment table
INSERT INTO payment (pay_id, or_id, date, amount) VALUES
                                                      ('PAY001', 'ORD001', '2024-05-03', 100.50),
                                                      ('PAY002', 'ORD002', '2024-05-04', 150.75),
                                                      ('PAY003', 'ORD003', '2024-05-05', 200.25),
                                                      ('PAY004', 'ORD004', '2024-05-06', 250.50),
                                                      ('PAY005', 'ORD005', '2024-05-07', 300.75),
                                                      ('PAY006', 'ORD006', '2024-05-08', 350.25),
                                                      ('PAY007', 'ORD007', '2024-05-09', 400.50),
                                                      ('PAY008', 'ORD008', '2024-05-10', 450.75),
                                                      ('PAY009', 'ORD009', '2024-05-11', 500.25),
                                                      ('PAY010', 'ORD010', '2024-05-12', 550.50),
                                                      ('PAY011', 'ORD011', '2024-05-13', 600.75),
                                                      ('PAY012', 'ORD012', '2024-05-14', 650.25),
                                                      ('PAY013', 'ORD013', '2024-05-15', 700.50),
                                                      ('PAY014', 'ORD014', '2024-05-16', 750.75),
                                                      ('PAY015', 'ORD015', '2024-05-17', 800.25),
                                                      ('PAY016', 'ORD016', '2024-05-18', 850.50),
                                                      ('PAY017', 'ORD017', '2024-05-19', 900.75),
                                                      ('PAY018', 'ORD018', '2024-05-20', 950.25),
                                                      ('PAY019', 'ORD019', '2024-05-21', 1000.50),
                                                      ('PAY020', 'ORD020', '2024-05-22', 1050.75);

-- Generate 20 values for user table
INSERT INTO user (user_id, name, password) VALUES
                                               ('USER001', 'ss', '1'),
                                               ('USER002', 'User 2', 'password2'),
                                               ('USER003', 'User 3', 'password3'),
                                               ('USER004', 'User 4', 'password4'),
                                               ('USER005', 'User 5', 'password5'),
                                               ('USER006', 'User 6', 'password6'),
                                               ('USER007', 'User 7', 'password7'),
                                               ('USER008', 'User 8', 'password8'),
                                               ('USER009', 'User 9', 'password9'),
                                               ('USER010', 'User 10', 'password10'),
                                               ('USER011', 'User 11', 'password11'),
                                               ('USER012', 'User 12', 'password12'),
                                               ('USER013', 'User 13', 'password13'),
                                               ('USER014', 'User 14', 'password14'),
                                               ('USER015', 'User 15', 'password15'),
                                               ('USER016', 'User 16', 'password16'),
                                               ('USER017', 'User 17', 'password17'),
                                               ('USER018', 'User 18', 'password18'),
                                               ('USER019', 'User 19', 'password19'),
                                               ('USER020', 'User 20', 'password20');

-- Generate 20 values for equipment table
INSERT INTO equipment (eq_id, name, model, cost, purchase, warranty, manufacture, user_id) VALUES
                                                                                               ('EQ001', 'Equipment 1', 'Model 1', 500.00, '2024-05-01', '2025-05-01', 'Manufacturer 1', 'USER001'),
                                                                                               ('EQ002', 'Equipment 2', 'Model 2', 750.00, '2024-05-02', '2025-05-01','Manufacturer 2', 'USER002'),
                                                                                               ('EQ003', 'Equipment 3', 'Model 3', 1000.00, '2024-05-03', '2025-05-01','Manufacturer 3', 'USER003'),
                                                                                               ('EQ004', 'Equipment 4', 'Model 4', 1250.00, '2024-05-04', '2028-05-04','Manufacturer 4', 'USER004'),
                                                                                               ('EQ005', 'Equipment 5', 'Model 5', 1500.00, '2024-05-05', '2028-05-04', 'Manufacturer 5', 'USER005'),
                                                                                               ('EQ006', 'Equipment 6', 'Model 6', 1750.00, '2024-05-06', '2028-05-04', 'Manufacturer 6', 'USER006'),
                                                                                               ('EQ007', 'Equipment 7', 'Model 7', 2000.00, '2024-05-07', '2025-05-01',  'Manufacturer 7', 'USER007'),
                                                                                               ('EQ008', 'Equipment 8', 'Model 8', 2250.00, '2024-05-08', '2028-05-04', 'Manufacturer 8', 'USER008'),
                                                                                               ('EQ009', 'Equipment 9', 'Model 9', 2500.00, '2024-05-09', '2033-05-09','Manufacturer 9', 'USER009'),
                                                                                               ('EQ010', 'Equipment 10', 'Model 10', 2750.00, '2024-05-10', '2033-05-09', 'Manufacturer 10', 'USER010'),
                                                                                               ('EQ011', 'Equipment 11', 'Model 11', 3000.00, '2024-05-11', '2033-05-09','Manufacturer 11', 'USER011'),
                                                                                               ('EQ012', 'Equipment 12', 'Model 12', 3250.00, '2024-05-12', '2033-05-09','Manufacturer 12', 'USER012'),
                                                                                               ('EQ013', 'Equipment 13', 'Model 13', 3500.00, '2024-05-13', '2036-05-13','Manufacturer 13', 'USER013'),
                                                                                               ('EQ014', 'Equipment 14', 'Model 14', 3750.00, '2024-05-14', '2036-05-13', 'Manufacturer 14', 'USER014'),
                                                                                               ('EQ015', 'Equipment 15', 'Model 15', 4000.00, '2024-05-15', '2036-05-13', 'Manufacturer 15', 'USER015'),
                                                                                               ('EQ016', 'Equipment 16', 'Model 16', 4250.00, '2024-05-16', '2036-05-13','Manufacturer 16', 'USER016'),
                                                                                               ('EQ017', 'Equipment 17', 'Model 17', 4500.00, '2024-05-17', '2041-05-17','Manufacturer 17', 'USER017'),
                                                                                               ('EQ018', 'Equipment 18', 'Model 18', 4750.00, '2024-05-18', '2041-05-18', 'Manufacturer 18', 'USER018'),
                                                                                               ('EQ019', 'Equipment 19', 'Model 19', 5000.00, '2024-05-19', '2041-05-18','Manufacturer 19', 'USER019'),
                                                                                               ('EQ020', 'Equipment 20', 'Model 20', 5250.00, '2024-05-20', '2041-05-18','Manufacturer 20', 'USER020');

INSERT INTO employee (emp_id, name, job_title, tel) VALUES
                                                        ('EMP001', 'Employee 1', 'Job 1', '1234567890'),
                                                        ('EMP002', 'Employee 2', 'Job 2', '0987654321'),
                                                        ('EMP003', 'Employee 3', 'Job 3', '9876543210'),
                                                        ('EMP004', 'Employee 4', 'Job 4', '0123456789'),
                                                        ('EMP005', 'Employee 5', 'Job 5', '9876543210'),
                                                        ('EMP006', 'Employee 6', 'Job 6', '0123456789'),
                                                        ('EMP007', 'Employee 7', 'Job 7', '1234567890'),
                                                        ('EMP008', 'Employee 8', 'Job 8', '0987654321'),
                                                        ('EMP009', 'Employee 9', 'Job 9', '9876543210'),
                                                        ('EMP010', 'Employee 10', 'Job 10', '0123456789'),
                                                        ('EMP011', 'Employee 11', 'Job 11', '1234567890'),
                                                        ('EMP012', 'Employee 12', 'Job 12', '0987654321'),
                                                        ('EMP013', 'Employee 13', 'Job 13', '9876543210'),
                                                        ('EMP014', 'Employee 14', 'Job 14', '0123456789'),
                                                        ('EMP015', 'Employee 15', 'Job 15', '1234567890'),
                                                        ('EMP016', 'Employee 16', 'Job 16', '0987654321'),
                                                        ('EMP017', 'Employee 17', 'Job 17', '9876543210'),
                                                        ('EMP018', 'Employee 18', 'Job 18', '0123456789'),
                                                        ('EMP019', 'Employee 19', 'Job 19', '1234567890'),
                                                        ('EMP020', 'Employee 20', 'Job 20', '0987654321');

-- Generate 20 values for maintenance table
INSERT INTO maintenance (mm_id, date, description, cost, emp_id) VALUES
                                                                     ('MAINT001', '2025-05-01', 'Maintenance 1', 50.00, 'EMP001'),
                                                                     ('MAINT002', '2025-05-01', 'Maintenance 2', 75.00, 'EMP002'),
                                                                     ('MAINT003', '2025-05-01', 'Maintenance 3', 100.00, 'EMP003'),
                                                                     ('MAINT004', '2028-05-04', 'Maintenance 4', 125.00, 'EMP004'),
                                                                     ('MAINT005', '2028-05-04', 'Maintenance 5', 150.00, 'EMP005'),
                                                                     ('MAINT006', '2028-05-05', 'Maintenance 6', 175.00, 'EMP006'),
                                                                     ('MAINT007', '2025-05-01', 'Maintenance 7', 200.00, 'EMP007'),
                                                                     ('MAINT008', '2028-05-04', 'Maintenance 8', 225.00, 'EMP008'),
                                                                     ('MAINT009', '2033-05-09', 'Maintenance 9', 250.00, 'EMP009'),
                                                                     ('MAINT010', '2033-05-09', 'Maintenance 10', 275.00, 'EMP010'),
                                                                     ('MAINT011', '2033-05-09', 'Maintenance 11', 300.00, 'EMP011'),
                                                                     ('MAINT012', '2033-05-09', 'Maintenance 12', 325.00, 'EMP012'),
                                                                     ('MAINT013', '2036-05-13', 'Maintenance 13', 350.00, 'EMP013'),
                                                                     ('MAINT014', '2036-05-13', 'Maintenance 14', 375.00, 'EMP014'),
                                                                     ('MAINT015', '2035-05-15', 'Maintenance 15', 400.00, 'EMP015'),
                                                                     ('MAINT016', '2036-05-13', 'Maintenance 16', 425.00, 'EMP016'),
                                                                     ('MAINT017', '2041-05-18', 'Maintenance 17', 450.00, 'EMP017'),
                                                                     ('MAINT018', '2041-05-18', 'Maintenance 18', 475.00, 'EMP018'),
                                                                     ('MAINT019', '2041-05-18', 'Maintenance 19', 500.00, 'EMP019'),
                                                                     ('MAINT020', '2041-05-20', 'Maintenance 20', 525.00, 'EMP020'),
                                                                     ('MAINT021', '2025-11-01', 'Maintenance 21', 525.00, 'EMP001'),
                                                                     ('MAINT022', '2026-05-01', 'Maintenance 22', 200.00, 'EMP001'),
                                                                     ('MAINT023', '2025-11-01', 'Maintenance 23', 300.00, 'EMP002');

-- Generate 20 values for spareparts table
INSERT INTO spareparts (sp_id, name, manufacture, cost, qty, purchase, mm_id) VALUES
                                                                                  ('SP001', 'Sparepart 1', 'Manufacturer 1', 10.00, 100, '2024-04-30', 'MAINT001'),
                                                                                  ('SP002', 'Sparepart 2', 'Manufacturer 2', 15.00, 150, '2024-05-01', 'MAINT002'),
                                                                                  ('SP003', 'Sparepart 3', 'Manufacturer 3', 20.00, 200, '2024-05-02', 'MAINT003'),
                                                                                  ('SP004', 'Sparepart 4', 'Manufacturer 4', 25.00, 250, '2024-05-03', 'MAINT004'),
                                                                                  ('SP005', 'Sparepart 5', 'Manufacturer 5', 30.00, 300, '2024-05-04', 'MAINT005'),
                                                                                  ('SP006', 'Sparepart 6', 'Manufacturer 6', 35.00, 350, '2024-05-05', 'MAINT006'),
                                                                                  ('SP007', 'Sparepart 7', 'Manufacturer 7', 40.00, 400, '2024-05-06', 'MAINT007'),
                                                                                  ('SP008', 'Sparepart 8', 'Manufacturer 8', 45.00, 450, '2024-05-07', 'MAINT008'),
                                                                                  ('SP009', 'Sparepart 9', 'Manufacturer 9', 50.00, 500, '2024-05-08', 'MAINT009'),
                                                                                  ('SP010', 'Sparepart 10', 'Manufacturer 10', 55.00, 550, '2024-05-09', 'MAINT010'),
                                                                                  ('SP011', 'Sparepart 11', 'Manufacturer 11', 60.00, 600, '2024-05-10', 'MAINT011'),
                                                                                  ('SP012', 'Sparepart 12', 'Manufacturer 12', 65.00, 650, '2024-05-11', 'MAINT012'),
                                                                                  ('SP013', 'Sparepart 13', 'Manufacturer 13', 70.00, 700, '2024-05-12', 'MAINT013'),
                                                                                  ('SP014', 'Sparepart 14', 'Manufacturer 14', 75.00, 750, '2024-05-13', 'MAINT014'),
                                                                                  ('SP015', 'Sparepart 15', 'Manufacturer 15', 80.00, 800, '2024-05-14', 'MAINT015'),
                                                                                  ('SP016', 'Sparepart 16', 'Manufacturer 16', 85.00, 850, '2024-05-15', 'MAINT016'),
                                                                                  ('SP017', 'Sparepart 17', 'Manufacturer 17', 90.00, 900, '2024-05-16', 'MAINT017'),
                                                                                  ('SP018', 'Sparepart 18', 'Manufacturer 18', 95.00, 950, '2024-05-17', 'MAINT018'),
                                                                                  ('SP019', 'Sparepart 19', 'Manufacturer 19', 100.00, 1000, '2024-05-18', 'MAINT019'),
                                                                                  ('SP020', 'Sparepart 20', 'Manufacturer 20', 105.00, 1050, '2024-05-19', 'MAINT020');

-- Generate 20 values for condemn table
INSERT INTO condemn (c_id, details, date, mm_id) VALUES
                                                     ('COND001', 'Details 1', '2024-05-01', 'MAINT001'),
                                                     ('COND002', 'Details 2', '2024-05-02', 'MAINT002'),
                                                     ('COND003', 'Details 3', '2024-05-03', 'MAINT003'),
                                                     ('COND004', 'Details 4', '2024-05-04', 'MAINT004'),
                                                     ('COND005', 'Details 5', '2024-05-05', 'MAINT005'),
                                                     ('COND006', 'Details 6', '2024-05-06', 'MAINT006'),
                                                     ('COND007', 'Details 7', '2024-05-07', 'MAINT007'),
                                                     ('COND008', 'Details 8', '2024-05-08', 'MAINT008'),
                                                     ('COND009', 'Details 9', '2024-05-09', 'MAINT009'),
                                                     ('COND010', 'Details 10', '2024-05-10', 'MAINT010'),
                                                     ('COND011', 'Details 11', '2024-05-11', 'MAINT011'),
                                                     ('COND012', 'Details 12', '2024-05-12', 'MAINT012'),
                                                     ('COND013', 'Details 13', '2024-05-13', 'MAINT013'),
                                                     ('COND014', 'Details 14', '2024-05-14', 'MAINT014'),
                                                     ('COND015', 'Details 15', '2024-05-15', 'MAINT015'),
                                                     ('COND016', 'Details 16', '2024-05-16', 'MAINT016'),
                                                     ('COND017', 'Details 17', '2024-05-17', 'MAINT017'),
                                                     ('COND018', 'Details 18', '2024-05-18', 'MAINT018'),
                                                     ('COND019', 'Details 19', '2024-05-19', 'MAINT019'),
                                                     ('COND020', 'Details 20', '2024-05-20', 'MAINT020');

-- Generate 20 values for maintenance_equipment_details table
INSERT INTO maintenance_equipment_details (type, mm_id, eq_id) VALUES
                                                                   ('Type 1', 'MAINT001', 'EQ001'),
                                                                   ('Type 2', 'MAINT002', 'EQ002'),
                                                                   ('Type 3', 'MAINT003', 'EQ003'),
                                                                   ('Type 4', 'MAINT004', 'EQ004'),
                                                                   ('Type 5', 'MAINT005', 'EQ005'),
                                                                   ('Type 6', 'MAINT006', 'EQ006'),
                                                                   ('Type 7', 'MAINT007', 'EQ007'),
                                                                   ('Type 8', 'MAINT008', 'EQ008'),
                                                                   ('Type 9', 'MAINT009', 'EQ009'),
                                                                   ('Type 10', 'MAINT010', 'EQ010'),
                                                                   ('Type 11', 'MAINT011', 'EQ011'),
                                                                   ('Type 12', 'MAINT012', 'EQ012'),
                                                                   ('Type 13', 'MAINT013', 'EQ013'),
                                                                   ('Type 14', 'MAINT014', 'EQ014'),
                                                                   ('Type 15', 'MAINT015', 'EQ015'),
                                                                   ('Type 16', 'MAINT016', 'EQ016'),
                                                                   ('Type 17', 'MAINT017', 'EQ017'),
                                                                   ('Type 18', 'MAINT018', 'EQ018'),
                                                                   ('Type 19', 'MAINT019', 'EQ019'),
                                                                   ('Type 20', 'MAINT020', 'EQ020'),
                                                                   ('Type 21', 'MAINT021', 'EQ001'),
                                                                   ('Type 22', 'MAINT022', 'EQ001'),
                                                                   ('Type 23', 'MAINT023', 'EQ002');



-- Generate 20 values for order_details table
INSERT INTO order_details (or_id, sp_id, qty, cost) VALUES
                                                        ('ORD001', 'SP001', 5, 50.00),
                                                        ('ORD002', 'SP002', 10, 75.00),
                                                        ('ORD003', 'SP003', 15, 100.00),
                                                        ('ORD004', 'SP004', 20, 125.00),
                                                        ('ORD005', 'SP005', 25, 150.00),
                                                        ('ORD006', 'SP006', 30, 175.00),
                                                        ('ORD007', 'SP007', 35, 200.00),
                                                        ('ORD008', 'SP008', 40, 225.00),
                                                        ('ORD009', 'SP009', 45, 250.00),
                                                        ('ORD010', 'SP010', 50, 275.00),
                                                        ('ORD011', 'SP011', 55, 300.00),
                                                        ('ORD012', 'SP012', 60, 325.00),
                                                        ('ORD013', 'SP013', 65, 350.00),
                                                        ('ORD014', 'SP014', 70, 375.00),
                                                        ('ORD015', 'SP015', 75, 400.00),
                                                        ('ORD016', 'SP016', 80, 425.00),
                                                        ('ORD017', 'SP017', 85, 450.00),
                                                        ('ORD018', 'SP018', 90, 475.00),
                                                        ('ORD019', 'SP019', 95, 500.00),
                                                        ('ORD020', 'SP020', 100, 525.00);
