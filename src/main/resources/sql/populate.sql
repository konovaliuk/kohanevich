INSERT INTO Users (userId, password, firstName, lastName, cash, email, phoneNumber, active, admin)
VALUES (1, 'admin', 'Yelisey', 'Kohanevich', 0, 'irunnie@gmail.com', '+380992337446', TRUE, TRUE ),
  (2, 'pwd', 'first', 'last', 0, 'email@email.com', '12345', TRUE, FALSE );
INSERT INTO Product(company, model, series, price, stock, product_type)
VALUES ('Motorola', 'MOTO G4', 'XT1622', 6495, 10, 'Mobile'),
  ('Samsung','Galaxy J7', 'J700H/DS', 4999, 10, 'Mobile'),
  ('Apple','iPhone', '5s', 9799, 10, 'Mobile'),
  ('Apple', 'MacBook Air', 'MMGF2UA/A', 27099, 10, 'Laptop'),
  ('HP', '250 G5', 'Z2Z93ES', 8499, 10, 'Laptop'),
  ('Asus', 'Vivobook', 'X556UQ', 22777, 10, 'Laptop'),

  ('LG', 'mobile1', 'A1', 5199, 10, 'Mobile'),
  ('LG', 'mobile2', '2017', 5199, 10, 'Mobile'),
  ('LG', 'mobile3', '2017', 5199, 10, 'Mobile'),
  ('LG', 'mobile4', '2017', 5199, 10, 'Mobile'),
  ('LG', 'mobile5', '2017', 5199, 10, 'Mobile'),
  ('LG', 'mobile6', '2017', 5199, 10, 'Mobile'),
  ('LG', 'mobile7', '2017', 5199, 10, 'Mobile'),
  ('LG', 'mobile8', '2017', 5199, 10, 'Mobile'),
  ('LG', 'mobile9', '2017', 5199, 10, 'Mobile'),
  ('LG', 'mobile10', '2017', 5199, 10, 'Mobile'),
  ('LG', 'mobile11', '2017', 5199, 10, 'Mobile'),
  ('LG', 'mobile12', '2017', 5199, 10, 'Mobile'),
  ('LG', 'mobile13', '2017', 5199, 10, 'Mobile'),
  ('LG', 'mobile14', '2017', 5199, 10, 'Mobile'),
  ('LG', 'mobile15', '2017', 5199, 10, 'Mobile'),
  ('LG', 'mobile16', '2017', 5199, 10, 'Mobile'),
  ('LG', 'mobile17', '2017', 5199, 10, 'Mobile'),
  ('LG', 'mobile18', '2017', 5199, 10, 'Mobile'),
  ('LG', 'mobile19', '2017', 5199, 10, 'Mobile'),
  ('LG', 'mobile20', '2017', 5199, 10, 'Mobile'),
  ('LG', 'mobile21', '2017', 5199, 10, 'Mobile'),
  ('LG', 'mobile22', '2017', 5199, 10, 'Mobile'),

  ('LG', 'laptop1', 'A1', 5199, 10, 'Laptop'),
  ('LG', 'laptop2', '2017', 5199, 10, 'Laptop'),
  ('LG', 'laptop3', '2017', 5199, 10, 'Laptop'),
  ('LG', 'laptop4', '2017', 5199, 10, 'Laptop'),
  ('LG', 'laptop5', '2017', 5199, 10, 'Laptop'),
  ('LG', 'laptop6', '2017', 5199, 10, 'Laptop'),
  ('LG', 'laptop7', '2017', 5199, 10, 'Laptop'),
  ('LG', 'laptop8', '2017', 5199, 10, 'Laptop'),
  ('LG', 'laptop9', '2017', 5199, 10, 'Laptop'),
  ('LG', 'laptop10', '2017', 5199, 10, 'Laptop'),
  ('LG', 'laptop11', '2017', 5199, 10, 'Laptop'),
  ('LG', 'laptop12', '2017', 5199, 10, 'Laptop'),
  ('LG', 'laptop13', '2017', 5199, 10, 'Laptop'),
  ('LG', 'laptop14', '2017', 5199, 10, 'Laptop'),
  ('LG', 'laptop15', '2017', 5199, 10, 'Laptop'),
  ('LG', 'laptop16', '2017', 5199, 10, 'Laptop'),
  ('LG', 'laptop17', '2017', 5199, 10, 'Laptop'),
  ('LG', 'laptop18', '2017', 5199, 10, 'Laptop'),
  ('LG', 'laptop19', '2017', 5199, 10, 'Laptop'),
  ('LG', 'laptop20', '2017', 5199, 10, 'Laptop'),
  ('LG', 'laptop21', '2017', 5199, 10, 'Laptop'),
  ('LG', 'laptop22', '2017', 5199, 10, 'Laptop');
INSERT INTO Orders(orderId, userId, totalPrice)
VALUES (1, 1, 100),
  (2, 1, 215),
  (3, 2, 300);
INSERT INTO Order_Entry(orderEntryId, orderId, productId, price, quantity)
VALUES (1, 1, 1, 50, 2),
  (2, 2, 1, 100, 2),
  (3, 2, 1, 15, 1),
  (4, 3, 1, 300, 1);
