"""
    Filename: 
    Description:
    Author: Domhnall Boyle
    Maintained by: Domhnall Boyle
    Email: domhnallboyle@gmail.com
    Python Version: 3.6
"""
import os
from flask import Flask
from flask_bcrypt import Bcrypt
from flask_jwt_extended import JWTManager
from flask_limiter import Limiter
from flask_limiter.util import get_remote_address
from flask_restplus import Api

from main.config import get_config

APP_NAME = 'Flappy-Bird Backend'

app = Flask(APP_NAME)
api = Api(
    title=APP_NAME,
    version='1.0',
    description='Backend for the Flappy-Bird mobile app'
)

limiter = Limiter(app=app, key_func=get_remote_address)
jwt = JWTManager(app=app)
b_crypt = Bcrypt(app=app)

api.init_app(app=app)

config = get_config(os.environ.get('CONFIG', 'development'))


def create_app():
    from main.resources import about_namespace, auth_namespace, user_namespace
    from main.utils.db import setup_db

    api.add_namespace(about_namespace)
    api.add_namespace(auth_namespace)
    api.add_namespace(user_namespace)

    app.config.from_object(config)

    app.url_map.strict_slashes = False

    setup_db()

    return app
