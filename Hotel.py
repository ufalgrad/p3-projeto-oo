import bisect
import math

# Class checklist:
# Singleton Database instance [ ]
# save() method that implements Creation and Updating on database [x]
# delete() method that deletes instance from database [ ]
# fetch() method that returns a list containing instances in database (with filters) [ ]

class Hotel():
	def __init__(self, name, city, state):
		self.name = name
		self.city = city
		self.state = state

	# Update instance on database
	def save(self, database):
		# Try to get current Hotel instance from database
		try:
			query = database.exec_sql("SELECT * FROM hotels WHERE name = :name", {'name': self.name})
			instance = query.fetchone()
		except:
			print('Database failure')

		# If Hotel instance does not exist in database, create it
		if(instance == None):
			instance_dict = {
				'name': self.name,
				'city': self.city,
				'state': self.state,
			}
			database.exec_sql("""INSERT INTO hotels
								 VALUES (:name, :city, :state)""", instance_dict)
		# otherwise, update existing instance to contain the new values
		else:
			update_dict = {
				'name': self.name,
				'city': self.city,
				'state': self.state,
			}
			database.exec_sql("""UPDATE hotels
								 SET name = :name, city = :city, state = :state
								 WHERE name = :name""", update_dict)

	def rate(self, rating=5, database):
		# Try to add rating to the database
		try:
			instance_dict = {
				'user': username,
				'hotel': self.name,
				'rating': rating,
			}
			database.exec_sql("""INSERT INTO ratings
								 VALUES (:user, :hotel, :rating)""", instance_dict)
		except:
			print('Database failure')

	def book(self, user, initial_datetime, final_datetime):
		# PLACEHOLDER
		print("DB: create booking for user x om hotel "+self.name)

	def get_bookings(self, initial_datetime, final_datetime):
		# PLACEHOLDER
		print("DB: get bookings for hotel:")
		print(self.name+" on timespan "+str(initial_datetime),
			  str(final_datetime))

	def check_booking(self, user, initial_datetime, final_datetime):
		# PLACEHOLDER
		print("Getting bookings for hotel X")