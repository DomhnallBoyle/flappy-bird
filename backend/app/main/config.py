"""
    Filename: 
    Description:
    Author: Domhnall Boyle
    Maintained by: Domhnall Boyle
    Email: domhnallboyle@gmail.com
    Python Version: 3.6
"""
import os


class Config(object):
    PROJECT_PATH = os.path.dirname(os.path.dirname(os.path.dirname(__file__)))
    DATABASE_PATH = os.path.join(PROJECT_PATH, 'database.db')

    # JTW config
    JWT_SECRET_KEY = '1793'
    # JWT_ACCESS_TOKEN_EXPIRES = 10 - default = 15 minutes
    JWT_ERROR_MESSAGE_KEY = 'message'


class DevelopmentConfig(Config):
    ENV = 'development'
    DEBUG = True


class ProductionConfig(Config):
    ENV = 'production'


class TestConfig(DevelopmentConfig):
    ENV = 'testing'
    TESTING = True


def get_config(config_name):
    return {
        'development': DevelopmentConfig(),
        'production': ProductionConfig(),
        'testing': TestConfig()
    }[config_name]
