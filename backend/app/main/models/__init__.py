"""
    Filename: 
    Description:
    Author: Domhnall Boyle
    Maintained by: Domhnall Boyle
    Email: domhnallboyle@gmail.com
    Python Version: 3.6
"""
from sqlalchemy.ext.declarative import declarative_base

Base = declarative_base()

from .user import User
