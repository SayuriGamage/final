DROP DATABASE gdse69;

create database gdse69;

use gdse69;


CREATE TABLE supplier (
                          sup_id VARCHAR(100) PRIMARY KEY,
                          name VARCHAR(100),
                          tel VARCHAR(100)
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
                      password VARCHAR(255),
                      tel VARCHAR(100)
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
INSERT INTO supplier (sup_id, name,tel) VALUES
                                        ('SUP001', 'Supplier 1','0714561111'),
                                        ('SUP002', 'Supplier 2','0714562222'),
                                        ('SUP003', 'Supplier 3','0714563333'),
                                        ('SUP004', 'Supplier 4','0714564444'),
                                        ('SUP005', 'Supplier 5','0714565555'),
                                        ('SUP006', 'Supplier 6','0714566666'),
                                        ('SUP007', 'Supplier 7','0714567777'),
                                        ('SUP008', 'Supplier 8','0714568888'),
                                        ('SUP009', 'Supplier 9','0714569999'),
                                        ('SUP010', 'Supplier 10','0714560000'),
                                        ('SUP011', 'Supplier 11','07123231111'),
                                        ('SUP012', 'Supplier 12','07123232222'),
                                        ('SUP013', 'Supplier 13','07123233333'),
                                        ('SUP014', 'Supplier 14','07123234444'),
                                        ('SUP015', 'Supplier 15','07123235555'),
                                        ('SUP016', 'Supplier 16','07123236666'),
                                        ('SUP017', 'Supplier 17','07123237777'),
                                        ('SUP018', 'Supplier 18','07123238888'),
                                        ('SUP019', 'Supplier 19','07123239999'),
                                        ('SUP020', 'Supplier 20','07123230000');

-- Generate 20 values for orders table
INSERT INTO orders (or_id, order_date, sup_id) VALUES
                                                   ('ORD001', '2024-05-17', 'SUP001'),
                                                   ('ORD002', '2024-05-17', 'SUP002'),
                                                   ('ORD003', '2024-05-17', 'SUP003'),
                                                   ('ORD004', '2024-05-17', 'SUP004'),
                                                   ('ORD005', '2024-05-18', 'SUP005'),
                                                   ('ORD006', '2024-05-18', 'SUP006'),
                                                   ('ORD007', '2024-05-18', 'SUP007'),
                                                   ('ORD008', '2024-05-18', 'SUP008'),
                                                   ('ORD009', '2024-05-19', 'SUP009'),
                                                   ('ORD010', '2024-05-19', 'SUP010'),
                                                   ('ORD011', '2024-05-19', 'SUP011'),
                                                   ('ORD012', '2024-05-19', 'SUP012'),
                                                   ('ORD013', '2024-05-20', 'SUP013'),
                                                   ('ORD014', '2024-05-20', 'SUP014'),
                                                   ('ORD015', '2024-05-20', 'SUP015'),
                                                   ('ORD016', '2024-05-20', 'SUP016'),
                                                   ('ORD017', '2024-05-21', 'SUP017'),
                                                   ('ORD018', '2024-05-21', 'SUP018'),
                                                   ('ORD019', '2024-05-21', 'SUP019'),
                                                   ('ORD020', '2024-05-21', 'SUP020');

-- Generate 20 values for payment table
INSERT INTO payment (pay_id, or_id, date, amount) VALUES
                                                      ('PAY001', 'ORD001', '2024-05-17', 500.00),
                                                      ('PAY002', 'ORD002', '2024-05-17', 750.00),
                                                      ('PAY003', 'ORD003', '2024-05-17', 600.00),
                                                      ('PAY004', 'ORD004', '2024-05-17', 480.00),
                                                      ('PAY005', 'ORD005', '2024-05-18', 720.00),
                                                      ('PAY006', 'ORD006', '2024-05-18', 790.00),
                                                      ('PAY007', 'ORD007', '2024-05-18', 5000.00),
                                                      ('PAY008', 'ORD008', '2024-05-18', 600.00),
                                                      ('PAY009', 'ORD009', '2024-05-19', 820.00),
                                                      ('PAY010', 'ORD010', '2024-05-19', 8200.00),
                                                      ('PAY011', 'ORD011', '2024-05-19', 890.00),
                                                      ('PAY012', 'ORD012', '2024-05-19', 720.00),
                                                      ('PAY013', 'ORD013', '2024-05-20', 120.00),
                                                      ('PAY014', 'ORD014', '2024-05-20', 220.00),
                                                      ('PAY015', 'ORD015', '2024-05-20', 320.00),
                                                      ('PAY016', 'ORD016', '2024-05-20', 420.00),
                                                      ('PAY017', 'ORD017', '2024-05-21', 920.00),
                                                      ('PAY018', 'ORD018', '2024-05-21', 550.00),
                                                      ('PAY019', 'ORD019', '2024-05-21', 620.00),
                                                      ('PAY020', 'ORD020', '2024-05-21', 20.00);



-- Generate 20 values for user table
INSERT INTO user (user_id, name, password,tel) VALUES
                                                   ('USER001', 'Laksiri', '123', ' 071 123 4567'),
                                                   ('USER002', 'User 2', 'password2', '072 234 5678'),
                                                   ('USER003', 'User 3', 'password3', '073 345 6789'),
                                                   ('USER004', 'User 4', 'password4', '074 456 7890'),
                                                   ('USER005', 'User 5', 'password5', '075 567 8901'),
                                                   ('USER006', 'User 6', 'password6', '076 678 9012'),
                                                   ('USER007', 'User 7', 'password7', '077 789 0123'),
                                                   ('USER008', 'User 8', 'password8', '078 890 1234'),
                                                   ('USER009', 'User 9', 'password9', '070 901 2345'),
                                                   ('USER010', 'User 10', 'password10', '071 012 3456'),
                                                   ('USER011', 'User 11', 'password11', '072 123 4567'),
                                                   ('USER012', 'User 12', 'password12', '073 234 5678'),
                                                   ('USER013', 'User 13', 'password13', '074 345 6789'),
                                                   ('USER014', 'User 14', 'password14', '075 456 7890'),
                                                   ('USER015', 'User 15', 'password15', '076 567 8901'),
                                                   ('USER016', 'User 16', 'password16', '077 678 9012'),
                                                   ('USER017', 'User 17', 'password17', '078 789 0123'),
                                                   ('USER018', 'User 18', 'password18', '070 890 1234'),
                                                   ('USER019', 'User 19', 'password19', '071 901 2345'),
                                                   ('USER020', 'User 20', 'password20', '072 012 3456');

-- Generate 20 values for equipment table
INSERT INTO equipment (eq_id, name, model, cost, purchase, warranty, manufacture, user_id) VALUES
                                                                                               ('EQP001', 'Digital Thermometer', 'DT-100', 45.99, '2022-05-01', '2024-05-17', 'MedTech Solutions', 'USER001'),
                                                                                               ('EQP002', 'Patient Monitor', 'PM-2000', 1500.00, '2022-05-02', '2023-11-17', 'VitalSign Inc.', 'USER002'),
                                                                                               ('EQP003', 'Nebulizer Machine', 'NebuCare Pro', 299.99, '2021-05-03', '2023-05-08', 'RespireTech', 'USER003'),
                                                                                               ('EQP004', 'Portable Oxygen Concentrator', 'OxyGenius Plus', 2000.00, '2023-05-15', '2023-11-17', 'OxyLife Technologies', 'USER004'),
                                                                                               ('EQP005', 'Wheelchair', 'EasyGlide 2000', 899.99, '2021-05-05', '2024-05-15', 'Mobility Solutions Inc.', 'USER005'),
                                                                                               ('EQP006', 'Blood Pressure Monitor', 'BPX-500', 89.99, '2022-05-06', '2023-11-18', 'CardioTech', 'USER006'),
                                                                                               ('EQP007', 'Electrocardiogram (ECG) Machine', 'ECG-1000', 3500.00, '2022-05-16', '2024-05-16', 'HeartCare Solutions', 'USER007'),
                                                                                               ('EQP008', 'Portable Ultrasound Scanner', 'UltraScan Go', 5000.00, '2021-05-16', '2024-05-16', 'Imaging Technologies Inc.', 'USER008'),
                                                                                               ('EQP009', 'Infusion Pump', 'InfuMax 3000', 2500.00, '2021-05-09', '2023-11-18', 'MediPump Corporation', 'USER009'),
                                                                                               ('EQP010', 'Digital X-ray Machine', 'X-RayMaster 2025', 10000.00, '2021-05-17', '2023-11-18', 'MediImaging Solutions', 'USER010'),
                                                                                               ('EQP011', 'Surgical Robot', 'SurgiTech Pro', 150000.00, '2021-05-11', '2024-05-17', 'RoboSurgery Inc.', 'USER011'),
                                                                                               ('EQP012', 'Hearing Aid', 'HearClear 300', 799.99, '2020-05-12', '2024-05-17', 'AudioTech Innovations', 'USER012'),
                                                                                               ('EQP013', 'MRI Machine', 'MRI-Xpert 5000', 500000.00, '2019-05-13', '2023-11-18', 'MediScan Technologies', 'USER013'),
                                                                                               ('EQP014', 'Smart Inhaler', 'Inhalix Smart', 149.99, '2021-05-14', '2023-11-18', 'BreatheWell Inc.', 'USER014'),
                                                                                               ('EQP015', 'Robotic Exoskeleton', 'ExoGrip Pro', 25000.00, '2022-05-15', '2023-11-19', 'BioMotion Robotics', 'USER015'),
                                                                                               ('EQP016', 'Fetal Monitor', 'FetalCare Deluxe', 1200.00, '2022-05-16', '2023-11-19', 'NeoNatal Solutions', 'USER016'),
                                                                                               ('EQP017', 'Patient Lift', 'LiftEase 1000', 1499.99, '2022-05-17', '2023-11-19', 'AssistiveTech Inc.', 'USER017'),
                                                                                               ('EQP018', 'Ophthalmoscope', 'EyeTech 360', 399.99, '2021-05-18', '2023-11-19', 'VisionCare Systems', 'USER018'),
                                                                                               ('EQP019', 'Digital Blood Glucose Meter', 'GluCheck Pro', 79.99, '2021-05-09', '2024-11-18', 'DiabTech Innovations', 'USER019'),
                                                                                               ('EQP020', 'Pulse Oximeter', 'OxiSense Pro', 59.99, '2021-05-20', '2023-11-18', 'VitalHealth Technologies', 'USER020');

INSERT INTO employee (emp_id, name, job_title, tel) VALUES
                                                        ('EMP001', 'Ranil', 'Technical Officer', '0771234567'),
                                                        ('EMP002', 'Nimal', 'Laborer', '0719876543'),
                                                        ('EMP003', 'Samantha', 'Technician', '0763456789'),
                                                        ('EMP004', 'Kumari', 'Technical Officer', '0752345678'),
                                                        ('EMP005', 'Sunil', 'Laborer', '0708765432'),
                                                        ('EMP006', 'Kamala', 'Technician', '0724567890'),
                                                        ('EMP007', 'Ranjith', 'Technical Officer', '0789876543'),
                                                        ('EMP008', 'Deepani', 'Laborer', '0776543210'),
                                                        ('EMP009', 'Suresh', 'Technician', '0712345678'),
                                                        ('EMP010', 'Chaminda', 'Technical Officer', '0768765432'),
                                                        ('EMP011', 'Malani', 'Laborer', '0703456789'),
                                                        ('EMP012', 'Upul', 'Technician', '0729876543'),
                                                        ('EMP013', 'Chathurika', 'Technical Officer', '0782345678'),
                                                        ('EMP014', 'Nirosha', 'Laborer', '0778765432'),
                                                        ('EMP015', 'Ajitha', 'Technician', '0713456789'),
                                                        ('EMP016', 'Lasantha', 'Technical Officer', '0769876543'),
                                                        ('EMP017', 'Kamal', 'Laborer', '0702345678'),
                                                        ('EMP018', 'Anuradha', 'Technician', '0728765432'),
                                                        ('EMP019', 'Nadeesha', 'Technical Officer', '0783456789'),
                                                        ('EMP020', 'Kasun', 'Laborer', '0776543210');

INSERT INTO maintenance (mm_id, date, description, cost, emp_id) VALUES
                                                                     ('MAI001', '2023-11-17', 'Engine Maintenance', 50.00, 'EMP001'),
                                                                     ('MAI002', '2023-11-17', 'Transmission Service', 75.00, 'EMP002'),
                                                                     ('MAI003', '2023-11-17', 'Electrical System Check', 100.00, 'EMP003'),
                                                                     ('MAI004', '2023-11-17', 'Brake Inspection', 125.00, 'EMP004'),
                                                                     ('MAI005', '2023-11-18', 'Oil Change', 150.00, 'EMP005'),
                                                                     ('MAI006', '2023-11-18', 'Coolant Flush', 175.00, 'EMP006'),
                                                                     ('MAI007', '2023-11-18', 'Suspension Alignment', 200.00, 'EMP007'),
                                                                     ('MAI008', '2023-11-18', 'Tire Rotation', 225.00, 'EMP008'),
                                                                     ('MAI009', '2023-11-18', 'Battery Replacement', 250.00, 'EMP009'),
                                                                     ('MAI010', '2023-11-18', 'Air Filter Replacement', 275.00, 'EMP010'),
                                                                     ('MAI011', '2023-11-19', 'Brake Pad Replacement', 300.00, 'EMP011'),
                                                                     ('MAI012', '2023-11-19', 'Wheel Bearing Replacement', 325.00, 'EMP012'),
                                                                     ('MAI013', '2023-11-19', 'Fuel System Cleaning', 350.00, 'EMP013'),
                                                                     ('MAI014', '2023-11-16', 'Exhaust System Repair', 375.00, 'EMP014'),
                                                                     ('MAI015', '2023-11-20', 'Ignition System Tune-Up', 400.00, 'EMP015'),
                                                                     ('MAI016', '2023-11-20', 'Air Conditioning Service', 425.00, 'EMP016'),
                                                                     ('MAI017', '2023-11-20', 'Engine Rebuild', 450.00, 'EMP017'),
                                                                     ('MAI018', '2023-11-20', 'Electrical Wiring Replacement', 475.00, 'EMP018'),
                                                                     ('MAI019', '2023-11-20', 'Steering System Repair', 500.00, 'EMP019'),
                                                                     ('MAI020', '2023-11-20', 'Transmission Overhaul', 525.00, 'EMP020');

INSERT INTO spareparts (sp_id, name, manufacture, cost, qty, purchase, mm_id) VALUES
                                                                                  ('SP001', 'Oil Filter', 'ABC Auto Parts', 10.00, 100, '2024-04-30', 'MAI001'),
                                                                                  ('SP002', 'Air Filter', 'XYZ Motors', 15.00, 150, '2024-05-01', 'MAI002'),
                                                                                  ('SP003', 'Brake Pads', 'SuperBrakes Inc.', 20.00, 200, '2024-05-02', 'MAI003'),
                                                                                  ('SP004', 'Spark Plugs', 'Spark Plug Co.', 25.00, 250, '2024-05-03', 'MAI004'),
                                                                                  ('SP005', 'Engine Oil', 'Engine Oil Ltd.', 30.00, 300, '2024-05-04', 'MAI005'),
                                                                                  ('SP006', 'Transmission Fluid', 'Transmission Solutions', 35.00, 350, '2024-05-05', 'MAI006'),
                                                                                  ('SP007', 'Brake Fluid', 'Brake Fluids Inc.', 40.00, 400, '2024-05-06', 'MAI007'),
                                                                                  ('SP008', 'Coolant', 'Coolant Systems Ltd.', 45.00, 450, '2024-05-07', 'MAI008'),
                                                                                  ('SP009', 'Battery', 'Power Batteries', 50.00, 500, '2024-05-08', 'MAI009'),
                                                                                  ('SP010', 'Wiper Blades', 'Wiper Solutions', 55.00, 550, '2024-05-09', 'MAI010'),
                                                                                  ('SP011', 'Brake Rotors', 'Brake Rotor Co.', 60.00, 600, '2024-05-10', 'MAI011'),
                                                                                  ('SP012', 'Tires', 'Tire World', 65.00, 650, '2024-05-11', 'MAI012'),
                                                                                  ('SP013', 'Alternator', 'Alternator Works', 70.00, 700, '2024-05-12', 'MAI013'),
                                                                                  ('SP014', 'Starter Motor', 'Starter Motor Co.', 75.00, 750, '2024-05-13', 'MAI014'),
                                                                                  ('SP015', 'Fuel Pump', 'Fuel Pump Ltd.', 80.00, 800, '2024-05-14', 'MAI015'),
                                                                                  ('SP016', 'Radiator', 'Radiator Solutions', 85.00, 850, '2024-05-15', 'MAI016'),
                                                                                  ('SP017', 'Ignition Coil', 'Ignition Coils Inc.', 90.00, 900, '2024-05-16', 'MAI017'),
                                                                                  ('SP018', 'Oxygen Sensor', 'Oxygen Sensor Corp.', 95.00, 950, '2024-05-17', 'MAI018'),
                                                                                  ('SP019', 'Thermostat', 'Thermostat Works', 100.00, 1000, '2024-05-18', 'MAI019'),
                                                                                  ('SP020', 'Cabin Air Filter', 'Air Quality Filters', 105.00, 1050, '2024-05-19', 'MAI020');

INSERT INTO condemn (c_id, details, date, mm_id) VALUES
                                                     ('CON001', 'Worn out and unusable', '2024-05-01', 'MAI001'),
                                                     ('CON002', 'Corroded beyond repair', '2024-05-02', 'MAI002'),
                                                     ('CON003', 'Faulty electrical components', '2024-05-03', 'MAI003'),
                                                     ('CON004', 'Excessive wear and tear', '2024-05-04', 'MAI004'),
                                                     ('CON005', 'Broken beyond repair', '2024-05-05', 'MAI005'),
                                                     ('CON006', 'Leaking fluids and damaged internals', '2024-05-06', 'MAI006'),
                                                     ('CON007', 'Damaged due to overheating', '2024-05-07', 'MAI007'),
                                                     ('CON008', 'Severe structural damage', '2024-05-08', 'MAI008'),
                                                     ('CON009', 'Excessive vibration and noise', '2024-05-09', 'MAI009'),
                                                     ('CON010', 'Failure to meet safety standards', '2024-05-10', 'MAI010'),
                                                     ('CON011', 'Excessive rust and corrosion', '2024-05-11', 'MAI011'),
                                                     ('CON012', 'Physical damage beyond repair', '2024-05-12', 'MAI012'),
                                                     ('CON013', 'Software malfunction', '2024-05-13', 'MAI013'),
                                                     ('CON014', 'Exceeds operational limits', '2024-05-14', 'MAI014'),
                                                     ('CON015', 'Obsolete and incompatible ', '2024-05-15', 'MAI015'),
                                                     ('CON016', 'Environmental damage', '2024-05-16', 'MAI016'),
                                                     ('CON017', 'Severe contamination', '2024-05-17', 'MAI017'),
                                                     ('CON018', 'Insufficient performance', '2024-05-18', 'MAI018'),
                                                     ('CON019', 'Excessive power consumption', '2024-05-19', 'MAI019'),
                                                     ('CON020', 'Component failure', '2024-05-20', 'MAI020');

INSERT INTO maintenance_equipment_details (type, mm_id, eq_id) VALUES
                                                                   ('Type 1', 'MAI001', 'EQP001'),
                                                                   ('Type 2', 'MAI002', 'EQP002'),
                                                                   ('Type 3', 'MAI003', 'EQP003'),
                                                                   ('Type 4', 'MAI004', 'EQP004'),
                                                                   ('Type 5', 'MAI005', 'EQP005'),
                                                                   ('Type 6', 'MAI006', 'EQP006'),
                                                                   ('Type 7', 'MAI007', 'EQP007'),
                                                                   ('Type 8', 'MAI008', 'EQP008'),
                                                                   ('Type 9', 'MAI009', 'EQP009'),
                                                                   ('Type 10', 'MAI010', 'EQP010'),
                                                                   ('Type 11', 'MAI011', 'EQP011'),
                                                                   ('Type 12', 'MAI012', 'EQP012'),
                                                                   ('Type 13', 'MAI013', 'EQP013'),
                                                                   ('Type 14', 'MAI014', 'EQP014'),
                                                                   ('Type 15', 'MAI015', 'EQP015'),
                                                                   ('Type 16', 'MAI016', 'EQP016'),
                                                                   ('Type 17', 'MAI017', 'EQP017'),
                                                                   ('Type 18', 'MAI018', 'EQP018'),
                                                                   ('Type 19', 'MAI019', 'EQP019'),
                                                                   ('Type 20', 'MAI020', 'EQP020');



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
