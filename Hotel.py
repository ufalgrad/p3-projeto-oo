import bisect
import math

# Class checklist:
# Update methods to use Database class [ ]
# save() method that implements Creation and Updating on database
# delete() method that deletes instance from database
# fetch() method that returns a list containing instances in database (with filters)

class State():
	def __init__(self, name):
		self.name = name

class City():
	def __init__(self, name, state):
		self.name = name
		self.state = state

class Hotel():
	def __init__(self, name, city):
		self.name = name
		self.city = city
		self.state = state
		self.stars = []
		self.stars_median = 0.0

	def rate(self, rating=5):
		# Ordered insert new rating in the 'stars' list
		# the list must be sorted, so we can calculate the median
		# TODO: update to database and queries
		bisect.insort(self.stars, rating)
		# Recalculate rating median
		mid = math.floor(len(self.stars/2))
		try:
			self.stars_median = self.stars[mid]
		except:
			pass

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