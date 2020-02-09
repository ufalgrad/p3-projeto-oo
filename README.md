# Refactored version of the java hotel management software.

## Patterns applied:
### 1.  Singleton:
 Limit the program to use only one instance (prohibit instantiation) of the database connection class, to avoid data inconsistency, also prohibit instantiation of Util class, to avoid unecessary memory allocation.
### Before:
 ![singleton antes](./img/singleton_antes.PNG)
### After:
 ![singleton depois](./img/singleton_depois.PNG)
### 2. Extract method:
 Group consecutive commands on a separate method
### Before:
 ![extract antes](./img/extract_method_antes.png)
### After:
 ![extract depois](./img/extract_method_antes.png)
