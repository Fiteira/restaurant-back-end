CREATE TABLE tableFood(
id INT PRIMARY KEY NOT NULL UNIQUE,
positionX int NOT NULL,
positionY int NOT NULL
);


CREATE TABLE requestFood (
id INT PRIMARY KEY NOT NULL UNIQUE,
userId TEXT NOT NULL,
tableId INT NOT NULL,
FOREIGN KEY (userId) REFERENCES users(id),
FOREIGN KEY (tableId) REFERENCES tableFood(id)
);


CREATE TABLE menu(
id INT PRIMARY KEY NOT NULL UNIQUE,
name TEXT NOT NULL,
ingredients TEXT NOT NULL,
type TEXT NOT NULL,
restriction TEXT NOT NULL,
price FLOAT NOT NULL
);

CREATE TABLE requestMenu(
requestFoodID INT NOT NULL,
menuId INT NOT NULL,
PRIMARY KEY (requestFoodID, menuId),
FOREIGN KEY (requestFoodID) REFERENCES requestFood(id),
FOREIGN KEY (menuId) REFERENCES menu(id)
);