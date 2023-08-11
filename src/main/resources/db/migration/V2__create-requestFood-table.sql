CREATE TABLE tableFood (
id SERIAL PRIMARY KEY,
positionX INT NOT NULL,
positionY INT NOT NULL
);

CREATE TABLE requestFood (
id SERIAL PRIMARY KEY,
userId TEXT NOT NULL,
tableId INT NOT NULL,
FOREIGN KEY (userId) REFERENCES users(id),
FOREIGN KEY (tableId) REFERENCES tableFood(id)
);

CREATE TABLE menu (
id SERIAL PRIMARY KEY,
name TEXT NOT NULL,
ingredients TEXT NOT NULL,
type TEXT NOT NULL,
restriction TEXT NOT NULL,
price FLOAT NOT NULL,
image TEXT NOT NULL
);

CREATE TABLE requestMenu (
requestFoodID INT NOT NULL,
menuId INT NOT NULL,
PRIMARY KEY (requestFoodID, menuId),
FOREIGN KEY (requestFoodID) REFERENCES requestFood(id),
FOREIGN KEY (menuId) REFERENCES menu(id)
);