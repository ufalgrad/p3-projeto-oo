import sqlite3

database = sqlite3.connect('./db.sqlite3')
cursor = self.database.cursor()

cursor.execute("""CREATE TABLE users (
			  	username text,
			  	first_name text,
			  	last_name text,
			  	password text,
			  	email text,
			  	age integer
			  )""")

database.commit()
database.close()
