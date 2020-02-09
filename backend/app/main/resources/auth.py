"""
    Filename: 
    Description:
    Author: Domhnall Boyle
    Maintained by: Domhnall Boyle
    Email: domhnallboyle@gmail.com
    Python Version: 3.6
"""
import http
import requests
import flask
from flask import request
from flask_jwt_extended import create_access_token, create_refresh_token, jwt_refresh_token_required, get_jwt_identity
from flask_restplus import Namespace, reqparse, Resource

from main import app
from main.models import User
from main.resources.utils import error_response
from main.utils.db import session

auth_namespace = Namespace('Auth', path='/', description='Operations for application authentication')


@auth_namespace.route('/login')
class Login(Resource):

    parser = reqparse.RequestParser()
    parser.add_argument('username', location='json', required=True)
    parser.add_argument('password', location='json', required=True)

    @auth_namespace.expect(parser)
    def post(self):
        data = request.json
        username = data['username']
        password = data['password']

        user = session().query(User).filter(User.username == username).all()
        if not user:
            # create a new user
            create_user_url = f'{flask.request.host_url}users/'
            r = requests.post(create_user_url, json={
                'username': username,
                'password': password
            })
            if r.status_code == http.HTTPStatus.CREATED:
                response = {
                    'user_id': r.json()['id'],
                    'high_score': 0,
                    'access_token': create_access_token(identity=username),
                    'refresh_token': create_refresh_token(identity=username)
                }
                return response, 200
            else:
                return error_response(r.reason, r.status_code)
        else:
            # verify user password
            user = user[0]
            if user.verify_password(password=password):
                response = {
                    'user_id': user.id,
                    'high_score': user.high_score,
                    'access_token': create_access_token(identity=username),
                    'refresh_token': create_refresh_token(identity=username)
                }
                return response, 200
            else:
                return error_response('Incorrect password given', 400)


@auth_namespace.route('/refresh')
class Refresh(Resource):

    @jwt_refresh_token_required
    def post(self):
        current_user = get_jwt_identity()
        new_token = create_access_token(identity=current_user)

        return {'access_token': new_token}, 200
