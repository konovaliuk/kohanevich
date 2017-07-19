create table Users (
  userId INT NOT NULL AUTO_INCREMENT,
  email VARCHAR(50) NOT NULL UNIQUE,
  password VARCHAR(30) NOT NULL,
  cash DECIMAL,
  firstName VARCHAR(255) NOT NULL,
  lastName VARCHAR(255) NOT NULL,
  phoneNumber VARCHAR(30),
  active BOOLEAN NOT NULL,
  admin BOOLEAN NOT NULL,
  PRIMARY KEY (userId),
);
create table Product (
  productId INT NOT NULL AUTO_INCREMENT,
  company VARCHAR(30) NOT NULL,
  model VARCHAR(30) NOT NULL,
  series VARCHAR(30),
  price DECIMAL NOT NULL,
  stock INT NOT NULL,
  product_type VARCHAR(30) NOT NULL,
  PRIMARY KEY (productId)
);
CREATE TABLE Orders (
  orderId INT NOT NULL AUTO_INCREMENT,
  userId INT NOT NULL,
  totalPrice DECIMAL NOT NULL,
  PRIMARY KEY (orderId),
  FOREIGN KEY (userId) REFERENCES Users(userId)
);
CREATE TABLE Order_Entry (
  orderEntryId INT NOT NULL AUTO_INCREMENT,
  orderId INT NOT NULL,
  productId INT NOT NULL,
  price DECIMAL NOT NULL,
  quantity INT NOT NULL,
  PRIMARY KEY (orderEntryId),
  FOREIGN KEY (orderId) REFERENCES Orders(orderId),
  FOREIGN KEY (productId) REFERENCES Product(productId) ON DELETE CASCADE
);

