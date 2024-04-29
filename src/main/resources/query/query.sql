CREATE DATABASE  MEMS;


USE  MEMS;


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


INSERT INTO supplier (sup_id, name) VALUES
                                        ('SUP001', 'ABC Supplier'),
                                        ('SUP002', 'XYZ Supplier'),
                                        ('SUP003', 'DEF Supplier'),
                                        ('SUP004', 'GHI Supplier'),
                                        ('SUP005', 'JKL Supplier');



INSERT INTO orders (or_id, order_date, sup_id) VALUES
                                                   ('ORD001', '2024-04-23', 'SUP001'),
                                                   ('ORD002', '2024-04-24', 'SUP002'),
                                                   ('ORD003', '2024-04-25', 'SUP003'),
                                                   ('ORD004', '2024-04-26', 'SUP004'),
                                                   ('ORD005', '2024-04-27', 'SUP005');



INSERT INTO payment (pay_id, or_id, date, amount) VALUES
                                                      ('PAY001', 'ORD001', '2024-04-25', 500.00),
                                                      ('PAY002', 'ORD002', '2024-04-26', 750.00),
                                                      ('PAY003', 'ORD003', '2024-04-27', 600.00),
                                                      ('PAY004', 'ORD004', '2024-04-28', 480.00),
                                                      ('PAY005', 'ORD005', '2024-04-29', 720.00);



INSERT INTO user (user_id, name, password) VALUES
                                               ('USER001', 'Sayuri', '111'),
                                               ('USER002', 'Laksiri', '222'),
                                               ('USER003', 'Mathiws', '333'),
                                               ('USER004', 'Benara', '444'),
                                               ('USER005', 'Emali', '555');



INSERT INTO equipment (eq_id, name, model, cost, purchase, warranty, manufacture, user_id) VALUES
                                                                                               ('EQ001', 'Laptop', 'Dell XPS 15', 1500.00, '2024-01-15', '2025-01-15', 'Dell', 'USER001'),
                                                                                               ('EQ002', 'Printer', 'HP LaserJet Pro', 300.00, '2024-02-20', '2025-02-20', 'HP', 'USER002'),
                                                                                               ('EQ003', 'Projector', 'Epson PowerLite', 800.00, '2024-03-10', '2025-03-10', 'Epson', 'USER003'),
                                                                                               ('EQ004', 'Smartphone', 'iPhone 13', 1000.00, '2024-04-05', '2025-04-05', 'Apple', 'USER004'),
                                                                                               ('EQ005', 'Desktop', 'HP Pavilion', 1200.00, '2024-05-01', '2025-05-01', 'HP', 'USER005');


INSERT INTO employee (emp_id, name, job_title, tel) VALUES
                                                        ('EMP001', 'Michael ', 'Technician', '123-456-7890'),
                                                        ('EMP002', 'Sarah ', 'Engineer', '234-567-8901'),
                                                        ('EMP003', 'David ', 'Laber', '345-678-9012'),
                                                        ('EMP004', 'Jennifer ', 'Assistant', '456-789-0123'),
                                                        ('EMP005', 'Christopher ', 'Specialist', '567-890-1234');


INSERT INTO maintenance (mm_id, date, description, cost, emp_id) VALUES
                                                                     ('MM001', '2024-04-01', 'Routine maintenance', 200.00, 'EMP001'),
                                                                     ('MM002', '2024-04-05', 'Repair work', 350.00, 'EMP002'),
                                                                     ('MM003', '2024-04-10', 'Equipment calibration', 150.00, 'EMP003'),
                                                                     ('MM004', '2024-04-15', 'Inspection', 100.00, 'EMP004'),
                                                                     ('MM005', '2024-04-20', 'Cleaning service', 120.00, 'EMP005');

INSERT INTO spareparts (sp_id, name, manufacture, cost, qty, purchase, mm_id) VALUES
                                                                                  ('SP001', 'RAM', 'Kingston', 50.00, 10, '2024-03-01', 'MM001'),
                                                                                  ('SP002', 'CPU Fan', 'Cooler Master', 30.00, 5, '2024-03-05', 'MM002'),
                                                                                  ('SP003', 'Printer Cartridge', 'HP', 25.00, 8, '2024-03-10', 'MM003'),
                                                                                  ('SP004', 'Phone Battery', 'Samsung', 40.00, 12, '2024-03-15', 'MM004'),
                                                                                  ('SP005', 'Monitor Stand', 'Dell', 20.00, 15, '2024-03-20', 'MM005');



INSERT INTO condemn (c_id, details, date, mm_id) VALUES
                                                     ('CON001', 'Faulty component', '2024-04-05', 'MM001'),
                                                     ('CON002', 'Broken part', '2024-04-10', 'MM002'),
                                                     ('CON003', 'Damaged beyond repair', '2024-04-15', 'MM003'),
                                                     ('CON004', 'Wear and tear', '2024-04-20', 'MM004'),
                                                     ('CON005', 'Obsolete technology', '2024-04-25', 'MM005');


INSERT INTO maintenance_equipment_details (type, mm_id, eq_id) VALUES
                                                                   ('Laptop maintenance', 'MM001', 'EQ001'),
                                                                   ('Printer maintenance', 'MM002', 'EQ002'),
                                                                   ('Projector maintenance', 'MM003', 'EQ003'),
                                                                   ('Smartphone maintenance', 'MM004', 'EQ004'),
                                                                   ('Desktop maintenance', 'MM005', 'EQ005');



INSERT INTO order_details (or_id, sp_id, qty, cost) VALUES
                                                        ('ORD001', 'SP001', 2, 50.00),
                                                        ('ORD002', 'SP002', 1, 30.00),
                                                        ('ORD003', 'SP003', 3, 25.00),
                                                        ('ORD004', 'SP004', 2, 40.00),
                                                        ('ORD005', 'SP005', 4, 20.00);




