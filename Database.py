import sqlite3

# Class checklist:

class Database():
	def __init__(self):
		# Start connection to the database stored in the file db.sqlite3
		self.database = sqlite3.connect('./db.sqlite3')
		# Create a cursor to access the database using SQL queries
		self.cursor = self.database.cursor()

	# Execute SQL command using a cursor to the database
	def exec_sql(self, str, data=None):
		if(data == None):
			self.cursor.execute(str)
		else:
			self.cursor.execute(str, data)

		# Commit the changes made to the database
		self.database.commit()

	# Close the connection to the database (called when the program closes)
	def close(self):
		self.database.close()