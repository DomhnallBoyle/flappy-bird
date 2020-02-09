"""
    Filename: 
    Description:
    Author: Domhnall Boyle
    Maintained by: Domhnall Boyle
    Email: domhnallboyle@gmail.com
    Python Version: 3.6
"""
from flask import request
from flask_jwt_extended import jwt_required
from flask_restplus import Namespace, reqparse, Resource

from main import limiter
from main.models import User as UserModel
from main.utils.db import session
from main.resources.utils import error_response

user_namespace = Namespace('User', path='/users', description='Operations for the application users')


@user_namespace.route('/')
class UserList(Resource):

    parser = reqparse.RequestParser()
    parser.add_argument('username', location='json', required=True)
    parser.add_argument('password', location='json', required=True)

    @jwt_required
    def get(self):
        """
        Returns top 10 users sorted by highest score
        """
        users = session().query(UserModel).order_by(UserModel.high_score.desc()).all()[:10]

        return [user.as_dict() for user in users]

    @limiter.limit('1/day')
    @user_namespace.expect(parser)
    def post(self):
        """
        Creates a user
        """
        data = request.json
        username = data['username']
        password = data['password']

        s = session()
        _user = s.query(UserModel).filter(UserModel.username == username).all()

        if not _user:
            user = UserModel(username=username, password=password)
            s.add(user)
            s.commit()

            return user.as_dict(), 201

        return error_response(message='User already exists', code=400)


@user_namespace.route('/<id>')
class User(Resource):

    parser = reqparse.RequestParser()
    parser.add_argument('high_score', location='json', required=True)

    @jwt_required
    @user_namespace.expect(parser)
    def post(self, id):
        """
        Updates a user's high-score
        """
        data = request.json
        high_score = data['high_score']

        s = session()
        _user = s.query(UserModel).filter(UserModel.id == id).first()

        if _user:
            _user.high_score = high_score
            s.commit()
            return _user.as_dict(), 200

        return error_response('User not found', 404)
