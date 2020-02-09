"""
    Filename: 
    Description:
    Author: Domhnall Boyle
    Maintained by: Domhnall Boyle
    Email: domhnallboyle@gmail.com
    Python Version: 3.6
"""
import os
from sqlalchemy import create_engine
from sqlalchemy.orm import sessionmaker

from main import config
from main.models import Base, User


_session = None


def session():
    global _session
    return _session()


def setup_db():
    if os.path.exists(config.DATABASE_PATH):
        os.remove(config.DATABASE_PATH)

    db_engine = create_engine(f'sqlite:///{config.DATABASE_PATH}')
    global _session
    _session = sessionmaker(bind=db_engine)

    Base.metadata.create_all(db_engine)

    if config.ENV == 'development':
        user_objects = [User(username=f'test_user_{i + 1}', high_score=(i * 10)) for i in range(0, 20)]
        s = session()
        s.add_all(user_objects)
        s.commit()
